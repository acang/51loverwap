<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.web.common.*"%>
<%@ page import="com.lover.SMSTools" %>
<script type="text/javascript" src="js/lib/zepto.min.js"></script>
<script type="text/javascript" src="js/lib/exp.js"></script>
<!-- endbuild --> 
	
<%
String sjtel = SysCommonFunc.getStrParameter(request,"sjtel");
String sp = SysCommonFunc.getStrParameter(request,"sp");
String url = SysCommonFunc.getStrParameter(request,"url");
String gotoUrl = "index.jsp";
if(url !=null && url.length()>0){
	   		gotoUrl=url;
	   }


sjtel = sjtel.toLowerCase();
%>
<%
if(sjtel == null || sjtel.trim().length() ==0)
{
%>
<script language='javascript'>{alert('请输入手机号码！');window.close();}</script>
<%
  return;
} 
 %>
 

<%

   String s = SMSTools.sendSjmobdlval(sjtel,request);
%>

<%
if(s == null)
{
 
			String cc = SMSTools.sendSjdl(sjtel,request);
			if(cc != null)
			{
		%>
	       <script language='javascript'>{alert('<%=cc%>');window.parent.location.replace('<%=gotoUrl%>');}</script>
	    <%}else{ %>
	 	   <script language='javascript'>{alert('验证码已发送到您的手机！若2分钟内没有收到，请重新点击[获取验证码]按钮获得校验码');window.close();}</script>

		<%
		    }
}else{
     if("您的手机号码已被注册，请直接登陆！".equals(s)){
     		session.setAttribute("sjtel",sjtel);
		%>
			<script language='javascript'>{alert('<%=s%>');window.parent.location.replace('login.jsp?sjreg=1&url=<%=gotoUrl%>');}</script>
	  <%}else{ %>
	     <script language='javascript'>{alert('<%=s%>');window.close();}</script>
<%
   		}
    }
%>
 
