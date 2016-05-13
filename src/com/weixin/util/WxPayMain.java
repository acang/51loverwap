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
	 * ��ȡ����Ԥ֧��id����
	 * @return
	 */
	public static String getPackage(Map<String, String> sParaTemp) {
		
		String openId = sParaTemp.get("openId");
		// 1 ����
		// ������
		String orderId = sParaTemp.get("orderId");
		// �������� ԭ������
		String attach = "";
		// �ܽ���Է�Ϊ��λ������С����
		String totalFee = sParaTemp.get("totalFee");
		
		// �������ɵĻ��� IP
		String spbill_create_ip = sParaTemp.get("spbill_create_ip");
		// ����notify_url�� ֧����ɺ�΢�ŷ�����������Ϣ�������жϻ�Ա�Ƿ�֧���ɹ����ı䶩��״̬�ȡ�
		String notify_url = sParaTemp.get("notify_url");
		String trade_type = sParaTemp.get("trade_type");

		// ---�������
		// �̻���
		String mch_id = sParaTemp.get("mch_id");
		// ����ַ���
		String nonce_str = sParaTemp.get("nonce_str");

		String appid = sParaTemp.get("appid");
		// ��Ʒ������������޸�
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

		// ����д�Ľ��Ϊ1 �ֵ�ʱ�޸�
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

		System.out.println("��ȡ����Ԥ֧��ID��" + prepay_id);
		
		
		//��ȡprepay_id��ƴ���������֧������Ҫ��package
		
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		String timestamp = Sha1Util.getTimeStamp();
		String packages = "prepay_id="+prepay_id;
		finalpackage.put("appId", appid);  
		finalpackage.put("timeStamp", timestamp);  
		finalpackage.put("nonceStr", nonce_str);  
		finalpackage.put("package", packages);  
		finalpackage.put("signType", "MD5");
		//Ҫǩ��
		String finalsign = reqHandler.createSign(finalpackage);
		
		String finaPackage = "\"appId\":\"" + appid + "\",\"timeStamp\":\"" + timestamp
		+ "\",\"nonceStr\":\"" + nonce_str + "\",\"package\":\""
		+ packages + "\",\"signType\" : \"MD5" + "\",\"paySign\":\""
		+ finalsign + "\"";

		System.out.println("V3 jsApi package:"+finaPackage);
		return finaPackage;
	}
	
	 
		
}
