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
<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.alipay.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk">
		<title>֧������ʱ���˽��׽ӿ�</title>
	</head>
	<%
		////////////////////////////////////�������//////////////////////////////////////

		//֧������
		String payment_type = "1";
		//��������޸�
		//�������첽֪ͨҳ��·��
		String notify_url = "http://www.51lover.org/alipay/notify_url.jsp";
		//��http://��ʽ������·�������ܼ�?id=123�����Զ������

		//ҳ����תͬ��֪ͨҳ��·��
		String return_url = "http://www.51lover.org/alipay/return_url.jsp";
		//��http://��ʽ������·�������ܼ�?id=123�����Զ������������д��http://localhost/

		//�̻�������
		String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("GBK"),"UTF-8");
		//�̻���վ����ϵͳ��Ψһ�����ţ�����

		//��������
		 String subject = new String(request.getParameter("WIDsubject").getBytes("GBK"),"UTF-8");
		//System.out.println("subject1��"+request.getParameter("WIDsubject"));
		// byte[] temp=request.getParameter("WIDsubject").getBytes("GBK");//����дԭ���뷽ʽ
	   // byte[] newtemp=new String(temp,"GBK").getBytes("utf-8");//����дת����ı��뷽ʽ
	   // String subject=new String(newtemp,"utf-8");//����дת����ı��뷽ʽ
	   // System.out.println("subject��"+subject);
	   // String subject=request.getParameter("WIDsubject"); 
	   // System.out.println("subject��"+subject);
		//����

		//������
		//String total_fee = new String(request.getParameter("WIDtotal_fee").getBytes("ISO-8859-1"),"UTF-8");
		String total_fee = new String("0.01".getBytes("ISO-8859-1"),"UTF-8");
		//����

		//��Ʒչʾ��ַ
		String show_url = new String(request.getParameter("WIDshow_url").getBytes("GBK"),"UTF-8");
		//����http://��ͷ������·�������磺http://www.�̻���ַ.com/myorder.html
		
		//��������

		String body = new String(request.getParameter("WIDbody").getBytes("GBK"),"UTF-8");
		
		
		//Ǯ��token
		String extern_token = "";
		//ѡ��
		
		//////////////////////////////////////////////////////////////////////////////////
		
		//������������������
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_id", AlipayConfig.seller_id);   
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee",total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		
		//��������
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","ȷ��");
		out.println(sHtmlText);
	%>
	<body>
	</body>
	
</html>