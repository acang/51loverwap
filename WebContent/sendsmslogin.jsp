<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.web.common.*"%>
<%@ page import="com.lover.SMSTools" %>
<%
	String sjtel = SysCommonFunc.getStrParameter(request,"sjtel");
	sjtel = sjtel.toLowerCase();
	String s = SMSTools.sendSjdl(sjtel,request);
	if(s != null)
	{
%>
       <script language='javascript'>{alert('<%=s%>');window.parent.location.replace('grzq.jsp');}</script>
    <%}else{ %>
 	   <script language='javascript'>{alert('校验码已发送到您的手机！若2分钟内没有收到，请重新点击[免费获取校验码]按钮获得校验码');window.close();}</script>

<%
    }
%>