<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.lover.mng.*"%>
<%@ page import="com.lover.mng.*"%>
<%
	String s = HYRegMng.updateSjyz(request);
	if(s == null){
	  out.println("<script language='javascript'>alert('���Ѿ��ɹ��������ֻ���֤��');" +
              "window.parent.location.href='grzq.htm'; window.close();  </script>");
	  }
	else{
        out.println("<script language='javascript'>alert('"+s+"');window.close();</script>");
    }


%>
