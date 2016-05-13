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

	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";
    
    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple                  = "yyyy-MM-dd HH:mm:ss";
    
    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort                 = "yyyyMMdd";
	
	/**
	 * 	作用：产生随机字符串，不长于32位
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
     * 返回系统当前时间(精确到毫秒)
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public  static String getDateTime(Date date){
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	 /**
     * 返回订单失效时间(精确到毫秒),day几天后失效
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public  static String getExpireTime(Date date,int day){
	  Calendar calendar=Calendar.getInstance();   
	    calendar.setTime(date); 
	    calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+day);//让日期加对应天数  
		DateFormat df=new SimpleDateFormat(dtLong);
		Date newDate = calendar.getTime();
		return df.format(newDate);
	}
	
	 /** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
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
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
	public static String buildRequestMysign(Map<String, String> sPara) {
    	String prestr =  createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = MD5.sign(prestr, WeixinMng.KEY, AlipayConfig.input_charset);
        return mysign;
    }
	

    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /** 
     * 生成可以获得code的url
     * @param params 需要排序并参与字符拼接的参数组
     * @param returnUrl  返回跳转的地址
     * @return 拼接后字符串
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
    	 //把请求参数打包成数组
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
     * 生成可以获得openid的url
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createOauthUrlForOpenid(String code) {

    	 //把请求参数打包成数组
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
    
    //地址链接
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
