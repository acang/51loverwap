<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.lover.mng.*"%>
<%@ page import="com.web.obj.*"%>
<%@ page import="com.web.common.*"%>
<%@ page import="com.lover.mng.*"%>
<%@ page import="java.util.*"%>
<%@ page import="hibernate.db.*"%>
<%

    String lcname = request.getParameter("lcname");
    String qggx = request.getParameter("qggx");
    String lxqr = request.getParameter("lxqr");
    String jyly = request.getParameter("jyly");

    
    	Sensitive sensitive =null;
        List listt =HbmOperator.list("from Sensitive");
        for(int i=0;i<listt.size();i++)
        {
        	sensitive=(Sensitive)listt.get(i);
        	
        	if(lcname.indexOf(sensitive.getSensitive())==-1)
        	{
        		out.println("<script language='javascript'>{window.top.document.getElementById('lcname1').style.display ='none';}</script>");
        	}
        	if(qggx.indexOf(sensitive.getSensitive())==-1)
        	{
        		out.println("<script language='javascript'>{window.top.document.getElementById('qggx1').style.display ='none';}</script>");
        	}
        	if(lxqr.indexOf(sensitive.getSensitive())==-1)
        	{
        		out.println("<script language='javascript'>{window.top.document.getElementById('lxqr1').style.display ='none';}</script>");
        	}
        	if(jyly.indexOf(sensitive.getSensitive())==-1)
        	{
        		out.println("<script language='javascript'>{window.top.document.getElementById('jyly1').style.display ='none';}</script>");
        	}
        	
        	if(lcname.indexOf(sensitive.getSensitive())!=-1)
        	{
        		out.println("<script language='javascript'>{window.alert('"+"（昵称）中含有敏感词“"+sensitive.getSensitive()+"”，请修改！"+"');window.top.document.personal.lcname.focus();window.top.document.getElementById('lcname1').style.display ='block';}</script>");
        		return;
        	}
        	if(qggx.indexOf(sensitive.getSensitive())!=-1)
        	{
        		out.println("<script language='javascript'>{window.alert('"+"（情爱关系）中含有敏感词“"+sensitive.getSensitive()+"”，请修改！"+"');window.top.document.personal.qggx.focus();window.top.document.getElementById('qggx1').style.display ='block'}</script>");
        		return;
        	}
        	if(lxqr.indexOf(sensitive.getSensitive())!=-1)
        	{
        		out.println("<script language='javascript'>{window.alert('"+"（理想情人）中含有敏感词“"+sensitive.getSensitive()+"”，请修改！"+"');window.top.document.personal.lxqr.focus();window.top.document.getElementById('lxqr1').style.display ='block'}</script>");
        		return;
        	}
        	if(jyly.indexOf(sensitive.getSensitive())!=-1)
        	{
        		out.println("<script language='javascript'>{window.alert('"+"（交友留言）中含有敏感词“"+sensitive.getSensitive()+"”，请修改！"+"');window.top.document.personal.jyly.focus();window.top.document.getElementById('jyly1').style.display ='block'}</script>");
        		return;
        	}

        }

String s = HYRegMng.userReg2NewUser(request);
if(s == null)
{
  session.setAttribute("reg","ok");
  out.println("<script language='javascript'>window.top.location.replace('welcome.jsp');</script>");
}
else if(s.startsWith("11"))
{
   out.println("<script language='javascript'>{window.alert('"+s.substring(2)+"');}</script>");
}else if(s.startsWith("12"))
{
   String mobile=SysCommonFunc.getStrParameter(request,"sjtel");
   out.println("<script language='javascript'>{window.alert('"+s.substring(2)+"');window.top.location.replace('login.jsp?mobile="+mobile+"');}</script>");
 //   out.println("<script language='javascript'>{window.alert('"+s.substring(2)+"');window.open('login.jsp?mobile="+mobile+"');}</script>");

}else
{
   out.println("<script language='javascript'>{window.alert('"+s+"');}</script>");
}


%>
