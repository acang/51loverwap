<%
/* *
 *���ܣ���ʱ���˽��׽ӿڽ���ҳ
 *�汾��3.3
 *���ڣ�2012-08-14
 *˵����
 *���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
 *�ô������ѧϰ���о�֧�����ӿ�ʹ�ã�ֻ���ṩһ���ο���

 *************************ע��*****************
 *������ڽӿڼ��ɹ������������⣬���԰��������;�������
 *1���̻��������ģ�https://b.alipay.com/support/helperApply.htm?action=consultationApply�����ύ���뼯��Э�������ǻ���רҵ�ļ�������ʦ������ϵ��Э�����
 *2���̻��������ģ�http://help.alipay.com/support/232511-16307/0-16307.htm?sh=Y&info_type=9��
 *3��֧������̳��http://club.alipay.com/read-htm-tid-8681712.html��
 *�������ʹ����չ���������չ���ܲ�������ֵ��
 **********************************************
 */
%>
<%@ page language="java" contentType="text/html; charset=gb2312" pageEncoding="gb2312"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.weixin.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk">
		<title>51��������</title>
	</head>
	<%
		////////////////////////////////////�������//////////////////////////////////////
		
		// 1 ����
		String appid = new String(request.getParameter("appid").getBytes("ISO-8859-1"),"UTF-8");
	
		String openId = new String(request.getParameter("openId").getBytes("ISO-8859-1"),"UTF-8");
		
		// ������
		String orderId = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		// �������� ԭ������
		String attach = "";
		// �ܽ���Է�Ϊ��λ������С����
		String totalFee = new String("1".getBytes("ISO-8859-1"),"UTF-8");
		
		// �������ɵĻ��� IP
		String spbill_create_ip = new String(request.getParameter("spbill_create_ip").getBytes("ISO-8859-1"),"UTF-8");
		// ����notify_url�� ֧����ɺ�΢�ŷ�����������Ϣ�������жϻ�Ա�Ƿ�֧���ɹ����ı䶩��״̬�ȡ�
		String notify_url = new String(request.getParameter("notify_url").getBytes("ISO-8859-1"),"UTF-8");
		
		String trade_type = new String(request.getParameter("trade_type").getBytes("ISO-8859-1"),"UTF-8");

		// ---�������
		// �̻���
		String mch_id = new String(request.getParameter("mch_id").getBytes("ISO-8859-1"),"UTF-8");
		// ����ַ���
		String nonce_str = new String(request.getParameter("nonce_str").getBytes("ISO-8859-1"),"UTF-8");

		// ��Ʒ������������޸�
		String body = new String(request.getParameter("body").getBytes("ISO-8859-1"),"UTF-8");

		String appsecret = WeixinMng.Appsecret;
		
		String partnerkey = WeixinMng.KEY;
		
		 
		//ѡ��
		
		//////////////////////////////////////////////////////////////////////////////////
		
		//������������������
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("openId", openId);
        sParaTemp.put("orderId", orderId);
        sParaTemp.put("totalFee", totalFee);   
        sParaTemp.put("spbill_create_ip", spbill_create_ip);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("trade_type", trade_type);
		sParaTemp.put("mch_id", mch_id);
		sParaTemp.put("nonce_str", nonce_str);
		sParaTemp.put("appid", appid);
		sParaTemp.put("body",body);
		sParaTemp.put("appsecret", appsecret);
		sParaTemp.put("partnerkey", partnerkey);
		
		
		//��������
		String sHtmlText = WxPayMain.getPackage(sParaTemp);
	%>
	<body>
	</body>
	<script type="text/javascript">
	
	
	function onBridgeReady(){
   WeixinJSBridge.invoke(
    'getBrandWCPayRequest', {
          <%=sHtmlText %>
  		},
 		function(res){   
			if(res.err_msg == "get_brand_wcpay_request:ok" ) {}   // ʹ�����Ϸ�ʽ�ж�ǰ�˷���,΢���Ŷ�֣����ʾ��res.err_msg�����û�֧���ɹ��󷵻�  ok����������֤�����Կɿ��� 
	    }
	); 
}
if (typeof WeixinJSBridge == "undefined"){
  if( document.addEventListener ){
    document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
  }else if (document.attachEvent){
		document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
 		document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	 }
}else{
  onBridgeReady();
}
	</script>
</html>
