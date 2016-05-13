<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.web.common.*"%>
<%@ page import="com.lover.SMSTools" %>
<%
String sjtel = SysCommonFunc.getStrParameter(request,"sjtel");
sjtel = sjtel.toLowerCase();
%>
<%
if(sjtel == null || sjtel.trim().length() ==0)
{
%>
<script language='javascript'>{alert('ÇëÊäÈëÊÖ»úºÅÂë£¡');window.close();}</script>
<%
  return;
} 
 %>
 

<%
   String s = SMSTools.sendSjdlvalNew(sjtel,request);
%>

<%
if(s != null)
{
%>

<script language='javascript'>{alert('<%=s%>');window.close();}</script>
<%
}else{
%>
       <script language='javascript'>{parent.callback('33');window.close();}</script>
<%
    }
%>