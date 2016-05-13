<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.web.common.*"%>
<%@ page import="com.lover.SMSTools" %>
<%
String sjtel = SysCommonFunc.getStrParameter(request,"sjtel2");
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
   String s = SMSTools.sendSjyz(sjtel,request);
%>

<%
if(s != null)
{
%>

<script language='javascript'>{alert('<%=s%>');window.close();}</script>
<%
}else{
	
%>

 <script language='javascript'>{alert('注册验证码已发送到您的手机！若2分钟内没有收到，请重新点击[获取短信验证码]按钮获得注册验证码，或更改手机号码验证');window.close();}</script>

<%
    }
%>.