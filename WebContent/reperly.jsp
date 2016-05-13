<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.web.obj.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.web.common.*"%>
<%@ page import="com.lover.mng.*"%>
<%@ page import="java.util.*"%>

<%
/// 普通会员现在能进行回复
  Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
  if(loginUser == null)
  {
    out.println("<script language='javascript'>alert('登录已超时，请重新登录！');history.go(-1);</script>");
    return;
  }
//  if(loginUser.getFlag() == null || loginUser.getFlag().longValue() != SysDefine.SYSTEM_HY_TYPE_vip)
//  {
//    out.println("<script language='javascript'>alert('您是普通会员，您的权限不够，请升级为vip会员');parent.parent.location.replace('hyzf.jsp');</script>");
//    return;
//  }

//set rs1=server.createobject("adodb.recordset")
//rs1.open "select top 1 setzt from hyset where hyid="&request("fromid"),conn,1,1
//if rs1("setzt")=6 then
//<script language=javascript>
//alert("该会员请您暂勿打扰！")
//window.close()
//</script>





  String fromid = SysCommonFunc.getStrParameter(request,"fromid");
  String fromname = SysCommonFunc.getStrParameter(request,"fromname");
  String dates = DateTools.dateToString(new Date(System.currentTimeMillis()),true);

if(request.getParameter("bj") != null)
{
   String s = GRZQMng.addHyly(request,loginUser);
   if(s == null)
      out.println("<script language='javascript'>alert('您已经成功发送了留言!');window.parent.close();</script>");
   else
      out.println("<script language='javascript'>alert('"+s+"!');window.parent.close();</script>");
   return;
}
%>


<link href="style.css" rel="stylesheet" type="text/css">
<body>
<form name="form1" method="post" action="reperly.jsp?bj=1">
<table width="600" height="400" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr ><td height="100px"></td></tr>
  <tr>
    <td  ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" >&nbsp;</td>
        <td valign="top"><table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#234D74">
          <tr>
            <td colspan="2" align="center" bgcolor="#5B8EBD" class="tit13">51交友中心－会员留言系统</td>
            </tr>
          <tr>
            <td width="26%" align="right" bgcolor="#D6E6F3" class="tit14"><font color="#000000">发送留言者：</font></td>
            <td width="74%" bgcolor="#FFFFFF" class="tit12"><div align="left" class="tit06"><%=loginUser.getLcname()%></div></td>
          </tr>
          <tr>
            <td align="right" bgcolor="#D6E6F3" class="tit14"><font color="#000000">接收留言者：</font></td>
            <td bgcolor="#FFFFFF" class="tit12"><div align="left" class="tit03"><%=fromname%>
	   <input type="hidden" name="hyname" value="<%=fromname%>">
            <input type="hidden" name="hyid" value="<%=fromid%>"></div></td>
          </tr>
          <tr>
            <td align="right" bgcolor="#D6E6F3" class="tit14"><font color="#000000">留言时间：</font></td>
            <td bgcolor="#FFFFFF"><span style="font-size:12px"><%=dates%></span><input type="hidden" name="lytime" value="<%=dates%>"></td>
          </tr>
          <tr>
            <td align="right" bgcolor="#D6E6F3" class="tit14"><font color="#000000">留言内容：</font></td>
            <td bgcolor="#FFFFFF"><label>
              <textarea name="content" cols="35" rows="10" style="width:400px;"></textarea>
            </label></td>
          </tr>
          <tr>
            <td height="40" colspan="2" align="center" bgcolor="#FFFFFF">
              <input type="image" name="Submit" src="./images/btn_tjly.gif" />
</td>
            </tr>
        </table></td>
        <td width="11" valign="top">&nbsp;</td>
      </tr>
    </table></td>
  </tr>

</table>
</form>

</body>
</html>
