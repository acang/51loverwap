package com.weixin.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;

public class WeixinCreateSign {
	
    public static void main(String[] args) {
        /*String jsapi_ticket = "jsapi_ticket";

        // 注意 URL 一定要动态获取，不能 hardcode
        String url = "http://example.com";
        Map<String, String> ret = sign(jsapi_ticket, url);
        for (Map.Entry entry : ret.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }*/
    };
    /*public static  Map WeiXinJsServer() {
  	   String url = "www.51lover.org";
         String access_token = getAccess_token();
         String jsapi_ticket = getJsapi_ticket(access_token);
         return sign(jsapi_ticket, url);
     }*/
    public static String getAccess_token(){
        System.out.println("重新请求access_token");
        String access_token = "";
        String str = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&";
        str += "appid=" + WeixinMng.appid + "&secret=" + WeixinMng.Appsecret;
        try {
            URL url = new URL(str);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            StringBuffer stringBuffer = new StringBuffer();
            String temp = "";
            while((temp = bufferedReader.readLine())!= null){
                stringBuffer.append(temp);
                System.out.println(temp);
            }
            String result = stringBuffer.toString();
            JSONObject root = new JSONObject(result);
            access_token = root.getString("access_token");
            bufferedReader.close();
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return access_token;
    }
    public static String getJsapi_ticket(String access_token){
        System.out.println("重新请求jsapi_ticket");
        String jsapi_ticket = "";
        String str = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
        try {
            URL url = new URL(str);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
            StringBuffer stringBuffer = new StringBuffer();
            String temp = "";
            while((temp = bufferedReader.readLine())!= null){
                stringBuffer.append(temp);
            }
            String result = stringBuffer.toString();
            JSONObject root = new JSONObject(result);
            jsapi_ticket = root.getString("ticket");
            bufferedReader.close();
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsapi_ticket;
    } 
    

    public static String sign(String nonce_str,String timestamp,String url) {
    	String access_token = getAccess_token();
         String jsapi_ticket = getJsapi_ticket(access_token);
    	Map<String, String> ret = new HashMap<String, String>();
       
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
        System.out.println(string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);

        return signature;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    public static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
