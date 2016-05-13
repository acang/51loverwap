<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.lover.mng.*"%>
<%@ page import="com.lover.mng.*"%>
<%
	String s = HYRegMng.updateSjyz(request);
	if(s == null){
	  out.println("<script language='javascript'>alert('您已经成功进行了手机验证！');" +
              "window.parent.location.href='grzq.htm'; window.close();  </script>");
	  }
	else{
        out.println("<script language='javascript'>alert('"+s+"');window.close();</script>");
    }


%>
