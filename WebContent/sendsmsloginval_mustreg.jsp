<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.web.common.*"%>
<%@ page import="com.lover.SMSTools" %>
<%@ page import="java.util.*"%>
<%@ page import="hibernate.db.HbmOperator" %>
<%
String sjtel = SysCommonFunc.getStrParameter(request,"sjtel");
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


List userList = HbmOperator
.list("from Userinfo as o where o.sjtel='"
    + request.getParameter("sjtel") + "' and o.isdel=0 order by regtime desc");
if (userList==null || userList.size()==0){
	out.println("<script language='javascript'>alert('�����οͣ���ע���ʹ�ã�');window.parent.location.href='reg.jsp';</script>");
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