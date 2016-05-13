<%@ page contentType="text/html; charset=GBK" %>

<%
  session.invalidate();
%>
<% 
Cookie[] cookies=request.getCookies(); 
try 
{ 
     for(int i=0;i<cookies.length;i++)   
     { 
      Cookie cookie = new Cookie("51up",null); 
      cookie.setMaxAge(0); 
      //cookie.setPath("/");//根据你创建cookie的路径进行填写     
      response.addCookie(cookie); 
     } 
}catch(Exception ex) 
{ 
     out.println("清空Cookies发生异常！"); 
}
%>


<script language="javascript">
 alert('欢迎再次光临51交友中心！');
 	window.parent.location.href='index.htm';
</script>
