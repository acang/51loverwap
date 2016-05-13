/**
 * 
 */
package com.weixin.util;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.wxap.RequestHandler;
import com.wxap.util.Sha1Util;

/**
 * @author lin
 *
 */
public class WxPayMain {


	/**
	 * 获取请求预支付id报文
	 * @return
	 */
	public static String getPackage(Map<String, String> sParaTemp) {
		
		String openId = sParaTemp.get("openId");
		// 1 参数
		// 订单号
		String orderId = sParaTemp.get("orderId");
		// 附加数据 原样返回
		String attach = "";
		// 总金额以分为单位，不带小数点
		String totalFee = sParaTemp.get("totalFee");
		
		// 订单生成的机器 IP
		String spbill_create_ip = sParaTemp.get("spbill_create_ip");
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = sParaTemp.get("notify_url");
		String trade_type = sParaTemp.get("trade_type");

		// ---必须参数
		// 商户号
		String mch_id = sParaTemp.get("mch_id");
		// 随机字符串
		String nonce_str = sParaTemp.get("nonce_str");

		String appid = sParaTemp.get("appid");
		// 商品描述根据情况修改
		String body = sParaTemp.get("body");

		String appsecret = sParaTemp.get("appsecret");
		
		String partnerkey = sParaTemp.get("partnerkey");
		
		String app_key = "";

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("attach", attach);
		packageParams.put("out_trade_no", orderId);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", totalFee);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);
		packageParams.put("openid", openId);

		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		System.out.println("sign:"+sign);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign>" + sign + "</sign>"
				+ "<body><![CDATA[" + body + "]]></body>" 
				+ "<out_trade_no>" + orderId
				+ "</out_trade_no>" + "<attach>" + attach + "</attach>"
				+ "<total_fee>" + totalFee + "</total_fee>"
				+ "<spbill_create_ip>" + spbill_create_ip
				+ "</spbill_create_ip>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<trade_type>" + trade_type
				+ "</trade_type>" + "<openid>" + openId + "</openid>"
				+ "</xml>";
		System.out.println("xml:"+xml);
		String prepay_id = "";
		String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		
		
		prepay_id = new GetWxOrderno().getPayNo(createOrderURL, xml);

		System.out.println("获取到的预支付ID：" + prepay_id);
		
		
		//获取prepay_id后，拼接最后请求支付所需要的package
		
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String timestamp = Sha1Util.getTimeStamp();
		String packages = "prepay_id="+prepay_id;
		finalpackage.put("appId", appid);  
		finalpackage.put("timeStamp", timestamp);  
		finalpackage.put("nonceStr", nonce_str);  
		finalpackage.put("package", packages);  
		finalpackage.put("signType", "MD5");
		//要签名
		String finalsign = reqHandler.createSign(finalpackage);
		
		String finaPackage = "\"appId\":\"" + appid + "\",\"timeStamp\":\"" + timestamp
		+ "\",\"nonceStr\":\"" + nonce_str + "\",\"package\":\""
		+ packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\""
		+ finalsign + "\"";

		System.out.println("V3 jsApi package:"+finaPackage);
		return finaPackage;
	}
	
	 
		
}
