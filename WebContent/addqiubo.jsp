<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.web.obj.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.web.common.*"%>
<%@ page import="com.lover.mng.*"%>


<%
if(request.getHeader("referer")==null)
			{
				response.setStatus(403);
				response.sendError(403);
				return;
			}
			
///未做功能
//判断是否在对方的黑名单中
Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
if(loginUser == null)
{
   out.println("<script language='javascript'>alert('您是游客，请先登录或注册');location.replace('login.jsp');</script>");
   return;
}
String hyid = SysCommonFunc.getStrParameter(request,"hyid");
if(hyid.length() == 0)
{
  out.println("<script language='javascript'>alert('请选择要发送秋波的会员！');window.close();</script>");
  return;
}

if(SysCommonFunc.getNumberFromString(hyid,0)==0)
{
   out.println("<script language='javascript'>alert('请选择要发送秋波的会员');window.close();</script>");
   System.out.println("sql注入"+hyid);
   return;
}

Userinfo hyinfo = new Userinfo();
hyinfo.setHyid(new Long(hyid));
boolean isBlack = GRZQMng.isHy(hyinfo,SysDefine.SYSTEM_HYGL_BLACK,loginUser);
%>

<%
String s = GRZQMng.addQiubo(loginUser,hyid);
if(s == null)
  out.println("<script language='javascript'>alert('您已经成功向该会员发送秋波！');window.close();</script>");
else
  out.println("<script language='javascript'>alert('"+s+"');window.close();</script>");
%>

