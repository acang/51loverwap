/**
 * 
 */
package com.weixin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import org.jfree.util.Log;
import org.json.JSONObject;

import com.alipay.config.AlipayConfig;
import com.alipay.sign.MD5;
import com.alipay.util.AlipayCore;

/**
 * @author lin
 *
 */
public class WeixinTools {

	/** ������ʱ����(���»���) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";
    
    /** ����ʱ�� yyyy-MM-dd HH:mm:ss */
    public static final String simple                  = "yyyy-MM-dd HH:mm:ss";
    
    /** ������(���»���) yyyyMMdd */
    public static final String dtShort                 = "yyyyMMdd";
	
	/**
	 * 	���ã���������ַ�����������32λ
	 */
	public static String createNoncestr(int length) 
	{
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";  
		String str ="";
		Random rand = new Random();
		 for ( int i = 0; i < length; i++ )  {  
			int startIndex = rand.nextInt(chars.length()-1);
			str += chars.substring(startIndex,startIndex+1);  
		 }  
		return str;
	}
	
	 /**
     * ����ϵͳ��ǰʱ��(��ȷ������)
     * @return
     *      ��yyyyMMddHHmmssΪ��ʽ�ĵ�ǰϵͳʱ��
     */
	public  static String getDateTime(Date date){
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	 /**
     * ���ض���ʧЧʱ��(��ȷ������),day�����ʧЧ
     * @return
     *      ��yyyyMMddHHmmssΪ��ʽ�ĵ�ǰϵͳʱ��
     */
	public  static String getExpireTime(Date date,int day){
	  Calendar calendar=Calendar.getInstance();   
	    calendar.setTime(date); 
	    calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+day);//�����ڼӶ�Ӧ����  
		DateFormat df=new SimpleDateFormat(dtLong);
		Date newDate = calendar.getTime();
		return df.format(newDate);
	}
	
	 /** 
     * ��ȥ�����еĿ�ֵ��ǩ������
     * @param sArray ǩ��������
     * @return ȥ����ֵ��ǩ�����������ǩ��������
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
    
    /**
     * ����ǩ�����
     * @param sPara Ҫǩ��������
     * @return ǩ������ַ���
     */
	public static String buildRequestMysign(Map<String, String> sPara) {
    	String prestr =  createLinkString(sPara); //����������Ԫ�أ����ա�����=����ֵ����ģʽ�á�&���ַ�ƴ�ӳ��ַ���
        String mysign = MD5.sign(prestr, WeixinMng.KEY, AlipayConfig.input_charset);
        return mysign;
    }
	

    /** 
     * ����������Ԫ�����򣬲����ա�����=����ֵ����ģʽ�á�&���ַ�ƴ�ӳ��ַ���
     * @param params ��Ҫ���򲢲����ַ�ƴ�ӵĲ�����
     * @return ƴ�Ӻ��ַ���
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//ƴ��ʱ�����������һ��&�ַ�
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /** 
     * ���ɿ��Ի��code��url
     * @param params ��Ҫ���򲢲����ַ�ƴ�ӵĲ�����
     * @param returnUrl  ������ת�ĵ�ַ
     * @return ƴ�Ӻ��ַ���
     */
    public static String createOauthUrlForCode(String returnUrl) {

    	String encodeUrl = returnUrl;
        try {
        	encodeUrl =URLEncoder.encode(returnUrl,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("createOauthUrlForOpenid:bizString:="+e);
		}
    	 //������������������
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("appid", WeixinMng.appid);
        sParaTemp.put("redirect_uri", encodeUrl);
        sParaTemp.put("response_type", WeixinMng.responseType);   
        sParaTemp.put("scope", WeixinMng.snsapiBase);
        String bizString = createLinkString(sParaTemp);
        System.out.println("bizString:="+bizString);
        String url = WeixinMng.codeAddress+"?"+bizString+"&state=123#wechat_redirect";
        System.out.println("url:="+encodeUrl);
        return url;
    }
    
    /** 
     * ���ɿ��Ի��openid��url
     * @param params ��Ҫ���򲢲����ַ�ƴ�ӵĲ�����
     * @return ƴ�Ӻ��ַ���
     */
    public static String createOauthUrlForOpenid(String code) {

    	 //������������������
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("appid", WeixinMng.appid);
        sParaTemp.put("secret", WeixinMng.Appsecret);
        sParaTemp.put("code", code);   
        sParaTemp.put("grant_type", WeixinMng.grantType);
        String bizString = WeixinTools.createLinkString(sParaTemp);
        String url = WeixinMng.openidAddress+"?"+bizString;
        System.out.println("createOauthUrlForOpenid:bizString:="+url);
        String encodeUrl = url;
        String result = send(encodeUrl,"POST","UTF-8"); 
        String openid  = "";
		try {
			Map  map = JsonHelper.toMap(result);
			openid  = (String) map.get("openid");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("createOauthUrlForOpenid:result:="+result);
        System.out.println("openid:="+openid);
        return openid;
    }
    
    //��ַ����
	private static HttpURLConnection conn = null;	
	
    private static  String send(String urlAddr, String Mode,String chartSet )  {
		String str = "";
		StringBuffer params = new StringBuffer();

		if (params.length() > 0) {
			params.deleteCharAt(params.length() - 1);
		}

		try {
			URL url = new URL(urlAddr); 
			conn = (HttpURLConnection) url.openConnection();

			conn.setDoOutput(true);
			conn.setRequestMethod(Mode);
			conn.setUseCaches(false);
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length",
					String.valueOf(params.length()));
			conn.setDoInput(true);
			conn.connect();

			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), chartSet);
			out.write(params.toString());
			out.flush();
			out.close();
			InputStream is = conn.getInputStream();

			byte[] echo = new byte[10 * 1024];
			int len = is.read(echo);

			str = (new String(echo, 0, len)).trim();

			int code = conn.getResponseCode();
			if (code != 200) {
				str = "ERROR" + code;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			conn.disconnect();
		}

		return str;
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result = "{\"access_token\":\"OezXcEiiBSKSxW0eoylIeLDA3V4HdFmYsz5_2GHNmEjUZc9D6WzvGxoHQRDdbUlQmdV-L41keMTEIX_WWkkb1iD3UJhJwtIt9RzCEaZmlUJaq8qnCUFt_RRtWeNFcpLYb-iWNTjM7qB3jAnB7S0sWg\",\"expires_in\":7200,\"refresh_token\":\"OezXcEiiBSKSxW0eoylIeLDA3V4HdFmYsz5_2GHNmEjUZc9D6WzvGxoHQRDdbUlQDTD3S79DhP_LAF5FuME2kku_oUdo_ZFSggIjjJ7P1qIEetCwi2DWmv2kLUc76LuGnVUHlgwml8j6f5OYGnzgBQ\",\"openid\":\"oOW0Ss1ONwI3XHNsYZz8qXaeXLXU\",\"scope\":\"snsapi_base\"}";
		 String openid  = "";
			try {
				Map  map = JsonHelper.toMap(result);
				openid  = (String) map.get("openid");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("openid:="+openid);
	}

	
	
	
}
