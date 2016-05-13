<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.lover.mng.*"%>
<%@ page import="com.web.obj.*"%>
<%@ page import="com.lover.mng.*"%>
<%@ page import="java.util.*"%>
<%@ page import="hibernate.db.*"%>
<%
	if(request.getHeader("referer")==null)
			{
				response.setStatus(403);
				response.sendError(403);
				return;
			}
			
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
    String s = null;
    try {
        s = HYRegMng.updateUserinfo(request);
    } catch (Exception e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
    System.out.println("xiug"+s);
if(s == null){
	
  out.println("<script language='javascript'>alert('您已经成功的修改了您的个人资料！');window.parent.location.href='grzq.jsp'; window.close();  </script>");
  }
else
 out.println("<script language='javascript'>alert('"+s+"');window.close();</script>");

%>
