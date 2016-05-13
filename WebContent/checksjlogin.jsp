<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.lover.mng.*"%>
<%@ page import="com.web.obj.*"%>
<%@ page import="com.web.common.*"%>
<%@ page import="java.util.*"%> 
<%@ page import="sun.misc.BASE64Encoder" %>
<%@ page import="com.web.bean.*"%>
<%@ page import="org.apache.commons.beanutils.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.*"%>
<%
	String s = HYRegMng.updateSjdlNew(request);
    String goToUrl = (String)request.getParameter("goToUrl");
      	String url = (String)request.getParameter("url");
	   	if(url !=null && url.length()>0){
	   		goToUrl=url;
	   	}
	if(s == null){
		String sjTel = (String)request.getParameter("sjtel");
		Userinfo userinfo=HYRegMng.getUserinfoBySjtel(sjTel);
		/*if(userinfo != null && (userinfo.getAsk() == null || "".equals(userinfo.getAsk())) ){
				out.println("<script language='javascript'>alert('您的资料还没完善，请到电脑站完善您的资料提高交友成功率！');window.parent.location.href='"+goToUrl+"';</script>");
       	}else{
       		   out.println("<script language='javascript'>alert('登陆成功，请使用！');window.parent.location.href='"+goToUrl+"';</script>");
       	}*/
		out.println("<script language='javascript'>alert('登陆成功，请使用！');window.parent.location.href='"+goToUrl+"';</script>");
	}else if("1".equalsIgnoreCase(s)){
		out.println("<script language='javascript'>alert('您是游客，请注册后使用！');window.parent.location.href='reg.jsp';</script>");
	}else{
	 	out.println("<script language='javascript'>alert('"+s+"');window.close();</script>");
	}
%>
