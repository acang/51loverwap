<%
/* *
 *功能：即时到账交易接口接入页
 *版本：3.3
 *日期：2012-08-14
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *************************注意*****************
 *如果您在接口集成过程中遇到问题，可以按照下面的途径来解决
 *1、商户服务中心（https://b.alipay.com/support/helperApply.htm?action=consultationApply），提交申请集成协助，我们会有专业的技术工程师主动联系您协助解决
 *2、商户帮助中心（http://help.alipay.com/support/232511-16307/0-16307.htm?sh=Y&info_type=9）
 *3、支付宝论坛（http://club.alipay.com/read-htm-tid-8681712.html）
 *如果不想使用扩展功能请把扩展功能参数赋空值。
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
		<title>51交友中心</title>
	</head>
	<%
		////////////////////////////////////请求参数//////////////////////////////////////
		
		// 1 参数
		String appid = new String(request.getParameter("appid").getBytes("ISO-8859-1"),"UTF-8");
	
		String openId = new String(request.getParameter("openId").getBytes("ISO-8859-1"),"UTF-8");
		
		// 订单号
		String orderId = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		// 附加数据 原样返回
		String attach = "";
		// 总金额以分为单位，不带小数点
		String totalFee = new String("1".getBytes("ISO-8859-1"),"UTF-8");
		
		// 订单生成的机器 IP
		String spbill_create_ip = new String(request.getParameter("spbill_create_ip").getBytes("ISO-8859-1"),"UTF-8");
		// 这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
		String notify_url = new String(request.getParameter("notify_url").getBytes("ISO-8859-1"),"UTF-8");
		
		String trade_type = new String(request.getParameter("trade_type").getBytes("ISO-8859-1"),"UTF-8");

		// ---必须参数
		// 商户号
		String mch_id = new String(request.getParameter("mch_id").getBytes("ISO-8859-1"),"UTF-8");
		// 随机字符串
		String nonce_str = new String(request.getParameter("nonce_str").getBytes("ISO-8859-1"),"UTF-8");

		// 商品描述根据情况修改
		String body = new String(request.getParameter("body").getBytes("ISO-8859-1"),"UTF-8");

		String appsecret = WeixinMng.Appsecret;
		
		String partnerkey = WeixinMng.KEY;
		
		 
		//选填
		
		//////////////////////////////////////////////////////////////////////////////////
		
		//把请求参数打包成数组
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
		
		
		//建立请求
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
			if(res.err_msg == "get_brand_wcpay_request:ok" ) {}   // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回  ok，但并不保证它绝对可靠。 
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
