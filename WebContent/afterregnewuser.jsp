<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.lover.mng.*"%>
<%@ page import="com.web.obj.*"%>
<%@ page import="com.lover.mng.*"%>
<%@ page import="java.util.*"%>
<%@ page import="hibernate.db.*"%>
<%@ page import="com.web.common.*"%>
<%
List list = HbmOperator.list("from Userinfo as o where o.regtime > sysdate-1 and o.httpip = '"+request.getRemoteAddr() + "' and o.isdel (0,2)");
if(list.size() > 0)
{
  Userinfo utemp = (Userinfo)list.get(0);
 // System.out.println("重复注册:"+list.size()+":"+utemp.getLcname() + ":"+utemp.getUsername() + ":"+utemp.getHttpip());
//  out.println("<script language='javascript'>{window.alert('"+"一天之内请不要重复注册!"+"');}</script>");
// return;
}
String url = SysCommonFunc.getStrParameter(request,"url");
String gotoUrl = "index.jsp";
if(url !=null && url.length()>0){
	   		gotoUrl=url;
	   }

String username = request.getParameter("sjtel");
Usernamecut usernamecut =null;
        List listt =HbmOperator.list("from Usernamecut");
        for(int i=0;i<listt.size();i++)
        {
			usernamecut=(Usernamecut)listt.get(i);
        	
        	if(username.indexOf(usernamecut.getUsernamecut())!=-1)
        	{
        		out.println("<script language='javascript'>{window.alert('您的手机无法注册，请联系管理员！');}</script>");
        		return;
        	}
        }
        
        
String s = HYRegMng.sjRegNewUser(request,username);
if(s == null)
{
	Userinfo cuser = (Userinfo)request.getAttribute("cu");
  	session.setAttribute("reg","ok");
  	out.println("<script language='javascript'>{window.alert('注册成功，请使用！');window.parent.location.replace('"+gotoUrl+"');}</script>");
}

else if(s.startsWith("11"))
{
   out.println("<script language='javascript'>{window.alert('"+s.substring(2)+"');}</script>");
}else if(s.startsWith("您的手机号码已被注册"))
{
	session.setAttribute("sjtel",username);
   out.println("<script language='javascript'>{window.alert('"+s+"');window.parent.location.replace('login.jsp?sjreg=1&url="+gotoUrl+"');}</script>");
}else
{
   out.println("<script language='javascript'>{window.alert('"+s+"');}</script>");
}

%>
