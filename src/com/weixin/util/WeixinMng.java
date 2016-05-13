/**
 * 
 */
package com.weixin.util;

/**
 * @author lin
 *
 */
public class WeixinMng {

		// 微信分配的公众账号 ID
		public static String appid = "wxfb0工39d33468";
		
		// 微信支付分配的商户号 
		public static String mchId = "1239a002";
			
		// 订单生成的机器 IP
		public static String create_ip = "61.a1";
		 
		//JSAPI 接口中获取 openid
		public static String Appsecret = "99ac28caa0bfc3f54f265b54c4c";
		
		//商户支付密钥Key。审核通过后，在微信发送的邮件中查看
		public static String KEY = "NBoWzflcDrNszaZnTeRt6ri";
		
		//交易类型：JSAPI、NATIVE、APP
	    public static String tradeType = "JSaAPI";
	    
	    //获取CODE类型
	    public static String responseType = "code";
	    
	    //获取code范围：snsapi_base  snsapi_userinfo    snsapi_login
	    public static String snsapiBase = "snsa_base";
	    
	    //获取code地址
	    public static String codeAddress = "https://open.weixian.qq.com/connect/oauth2/authorize";
	    
	    //授权类型
	    public static String grantType = "authoriazation_code";
	    
	    //获取openid地址
	    public static String openidAddress = "https://api.weaixin.qq.com/sns/oauth2/access_token";
	    
	    
}
