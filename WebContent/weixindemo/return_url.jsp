<%
/* *
 ���ܣ�΢��ҳ����תͬ��֪ͨҳ��
 �汾��3.2
 ���ڣ�2011-03-17
 ˵����
 ���´���ֻ��Ϊ�˷����̻����Զ��ṩ���������룬�̻����Ը����Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
 �ô������ѧϰ���о�΢�Žӿ�ʹ�ã�ֻ���ṩһ���ο���

 //***********ҳ�湦��˵��***********
 ��ҳ����ڱ������Բ���
 �ɷ���HTML������ҳ��Ĵ��롢�̻�ҵ���߼��������
 TRADE_FINISHED(��ʾ�����Ѿ��ɹ��������������ٶԸý�������������);
 TRADE_SUCCESS(��ʾ�����Ѿ��ɹ����������ԶԸý����������������磺�����˿��);
 //********************************
 * */
%>
<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.alipay.util.*"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.lover.mng.CartMng" %>
<%@ page import="com.common.*"%>
<%@ page import="com.web.obj.Userinfo" %>
<html>
  <head>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk">
		<title>΢��ҳ����תͬ��֪ͨҳ��</title>
  </head>
  <body>
<%
	//��ȡ΢��GET����������Ϣ
	Map<String,String> params = new HashMap<String,String>();
	Map requestParams = request.getParameterMap();
	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		String name = (String) iter.next();
		String[] values = (String[]) requestParams.get(name);
		String valueStr = "";
		for (int i = 0; i < values.length; i++) {
			valueStr = (i == values.length - 1) ? valueStr + values[i]
					: valueStr + values[i] + ",";
		}
		//����������δ����ڳ�������ʱʹ�á����mysign��sign�����Ҳ����ʹ����δ���ת��
		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
		params.put(name, valueStr);
	}
	
	//��ȡ΢�ŵ�֪ͨ���ز������ɲο������ĵ���ҳ����תͬ��֪ͨ�����б�(���½����ο�)//
	//�̻�������

	String MerId = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"GBK");
	//΢�Ž��׺�

	String OrdId = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"GBK");
    System.out.println(OrdId);
	//����״̬
	String OrderStatus = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"GBK");
    System.out.println("OrderStatus =???? " + OrderStatus);
    if("TRADE_SUCCESS".equals(OrderStatus)){
       OrderStatus = "1001";
    }
	//��ȡ΢�ŵ�֪ͨ���ز������ɲο������ĵ���ҳ����תͬ��֪ͨ�����б�(���Ͻ����ο�)//

    int ret = CartMng.alipayOk(MerId,OrdId,"0.01","0","0","0",OrderStatus,"9");
    String s = CartMng.cartOkResponse(ret);
    out.println(s);

    if("�����ɹ�".equals(s)){

		// /���û�VIP��Ϣ���뵽��½��Ϣ��
       Userinfo grwhqUser = (Userinfo)request.getSession().getAttribute(SysDefine.SESSION_LOGINNAME);
       grwhqUser.setFlag(SysDefine.SYSTEM_HY_TYPE_vip);
       request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME,
    		   grwhqUser);
       response.sendRedirect("grzq.jsp");

    }

	//����ó�֪ͨ��֤���
	//boolean verify_result = AlipayNotify.verify(params);
	 
%>
  </body>
</html>
 