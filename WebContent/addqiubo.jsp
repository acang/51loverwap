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
			
///δ������
//�ж��Ƿ��ڶԷ��ĺ�������
Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
if(loginUser == null)
{
   out.println("<script language='javascript'>alert('�����οͣ����ȵ�¼��ע��');location.replace('login.jsp');</script>");
   return;
}
String hyid = SysCommonFunc.getStrParameter(request,"hyid");
if(hyid.length() == 0)
{
  out.println("<script language='javascript'>alert('��ѡ��Ҫ�����ﲨ�Ļ�Ա��');window.close();</script>");
  return;
}

if(SysCommonFunc.getNumberFromString(hyid,0)==0)
{
   out.println("<script language='javascript'>alert('��ѡ��Ҫ�����ﲨ�Ļ�Ա');window.close();</script>");
   System.out.println("sqlע��"+hyid);
   return;
}

Userinfo hyinfo = new Userinfo();
hyinfo.setHyid(new Long(hyid));
boolean isBlack = GRZQMng.isHy(hyinfo,SysDefine.SYSTEM_HYGL_BLACK,loginUser);
%>

<%
String s = GRZQMng.addQiubo(loginUser,hyid);
if(s == null)
  out.println("<script language='javascript'>alert('���Ѿ��ɹ���û�Ա�����ﲨ��');window.close();</script>");
else
  out.println("<script language='javascript'>alert('"+s+"');window.close();</script>");
%>
