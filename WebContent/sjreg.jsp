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
<script language='javascript'>{alert('�������ֻ����룡');window.close();}</script>
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
	 	   <script language='javascript'>{alert('��֤���ѷ��͵������ֻ�����2������û���յ��������µ��[��ȡ��֤��]��ť���У����');window.close();}</script>

		<%
		    }
}else{
     if("�����ֻ������ѱ�ע�ᣬ��ֱ�ӵ�½��".equals(s)){
     		session.setAttribute("sjtel",sjtel);
		%>
			<script language='javascript'>{alert('<%=s%>');window.parent.location.replace('login.jsp?sjreg=1&url=<%=gotoUrl%>');}</script>
	  <%}else{ %>
	     <script language='javascript'>{alert('<%=s%>');window.close();}</script>
<%
   		}
    }
%>
 
