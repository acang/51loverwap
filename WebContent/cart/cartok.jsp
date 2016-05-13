<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.web.obj.*"%>
<%@ page import="com.web.obj.extend.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.web.common.*"%>
<%@ page import="com.web.servlet.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.lover.mng.*"%>
<%@ page import="java.util.*"%>
<%@ page import="hibernate.db.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>网上支付升级</title>
<link href="../style.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
<style type="text/css">
<!--
.style9 {	font-size: 14px;
	font-weight: bold;
}
-->
</style>
</head>
<%
int ret = CartMng.cartOk(request);
String s = CartMng.cartOkResponse(ret);
out.println("状态＋"+s+"+"+ret);
System.out.println("状态＋"+s+"+"+ret);

%>

<body>
<%
if(ret ==0 || ret==5)
{
   Userinfo utemp = (Userinfo)request.getAttribute("okuser");
   Cart     cart  = (Cart)request.getAttribute("okcart");
   request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME,utemp);
   out.println("<script language='javascript'>location.replace('../index.jsp')</script>");
}
%>

<%
if(ret != 0 && ret != 5)
{
%>
<table width="400" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:100px;">
  <tr>
    <td height="30" align="center" bgcolor="#1D5EA6" class="tit11"><strong>网上支付不成功</strong></td>
  </tr>
  <tr>
    <td height="4" align="center" bgcolor="#E7F0FA">&nbsp;</td>
  </tr>
  <tr>
    <td height="4" align="center" bgcolor="#E7F0FA">
      请检查并重新支付</td>
  </tr>
  <tr>
    <td align="center" bgcolor="#E7F0FA" class="bk02" style="padding:20px;"><input type="button" value="重新填写订单" onclick="location.replace('../sjhy.jsp')"></td>
  </tr>
</table>
<%
}
%>

</body>
</html>



