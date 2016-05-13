<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.web.obj.*"%>
<%@ page import="com.web.common.*"%>
<%@ page import="com.lover.mng.*"%>
<%
String step = SysCommonFunc.getStrParameter(request,"step");


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>找回密码</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
</head>

<body>












<table width="620" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>

<%
if(step.length() ==0)
{
%>
<form name="form1" method="post" action="forget.jsp?step=2">
<table width="400" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" align="center" bgcolor="#1D5EA6" class="tit11"><strong>找回密码。。。。</strong></td>
  </tr>
  <tr>
    <td height="4"></td>
  </tr>
  <tr>
    <td bgcolor="#E7F0FA" class="bk02" style="padding:20px;"><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="34%" align="right"><strong>您的用户名：</strong></td>
        <td width="66%"><input name="username" type="text" id="username">
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center" style="padding-top:30px;"><input type="submit" name="Submit" value="  提    交  "></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
<%
}
%>
<%
if(step.equals("2"))
{
   String username = SysCommonFunc.getStrParameter(request,"username");
   Userinfo user = HYRegMng.getUserinfoByUsername(username);
   if(user == null)
   {
      out.println("<script language='javascript'>alert('对不起，您输入的用户名不存在，请查实！');window.close();</script>");
      return;
   }
%>
<form name="form2" method="post" action="forget.jsp?step=3">
<table width="400" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" align="center" bgcolor="#1D5EA6" class="tit11"><strong>找回密码。。。。</strong></td>
  </tr>
  <tr>
    <td height="4"></td>
  </tr>
  <tr>
    <td bgcolor="#E7F0FA" class="bk02" style="padding:20px;"><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="34%" align="right"><strong>您的用户名：</strong></td>
        <td width="66%"><%=username%>
          <input type="hidden" name="username" value="<%=username%>" /></td>
      </tr>
      <tr>
        <td align="right"><strong>您的问题是：</strong></td>
        <td align="left"><%=user.getAsk()%></td>
      </tr>
      <tr>
        <td align="right"><strong>您的答案是：</strong></td>
        <td align="left"><input name="answer" type="text" id="answer" class="input02" /></td>
      </tr>
      <tr>
        <td colspan="2" align="center" style="padding-top:30px;"><input type="submit" name="Submit" value="  提    交  "></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
<%
}
%>
<%
if(step.equals("3"))
{
   String username = SysCommonFunc.getStrParameter(request,"username");
   String answer   = SysCommonFunc.getStrParameter(request,"answer");
   Userinfo user = HYRegMng.getUserinfoByUsername(username);
   String password = "";
   if(user != null && user.getAnswer() != null && user.getAnswer().length() > 0 && user.getAnswer().equals(answer))
     password = user.getPassword();
   else
   {
     out.println("<script language='javascript'>alert('您输入的提示答案不正确，请查实！');window.close();</script>");
     return;
   }


//set rs=server.CreateObject("adodb.recordset")
//rs.open "select * from userinfo where username='"&request("username")&"' and answer='"&request("answer")&"'",conn,3,3
//if rs.eof then
//response.Write "<script language='javascript'>alert('您输入的提示答案不正确，请查实！');window.close();</script>"
//else
%>
<table width="400" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" align="center" bgcolor="#1D5EA6" class="tit11"><strong>找回密码。。。。</strong></td>
  </tr>
  <tr>
    <td height="4"></td>
  </tr>
  <tr>
    <td bgcolor="#E7F0FA" class="bk02" style="padding:20px;"><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="34%" align="right" valign="top"><strong>您的用户名：</strong></td>
        <td width="66%" valign="top"><strong><%=username%></strong></td>
      </tr>
      <tr>
        <td height="22" align="right"><strong>您的密码是：</strong></td>
        <td><strong><%=password%></strong></td>
      </tr>
      <tr>
        <td colspan="2" align="center" style="padding-top:30px;"><input type="button" name="Submit32" value="关闭窗口" onclick="javascript:window.close()" /></td>
      </tr>
    </table></td>
  </tr>
</table>
<%
}
%>
</body>
</html>



