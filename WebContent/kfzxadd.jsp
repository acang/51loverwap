<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.web.common.*" %>
<%@ page import="com.lover.mng.*" %>
<%@ page import="java.net.URL" %>
<%
   String strOne = request.getHeader("referer");
    String pathOne = "";
    if (strOne != null) {
        URL urlOne = new URL(strOne);
        pathOne = urlOne.getHost();
    }
    String strTwo = request.getRequestURL().toString();
    String pathTwo = "";
    if (strTwo != null) {
        URL urlTwo = new URL(strTwo);
        pathTwo = urlTwo.getHost();
    }
%>
<%
    System.out.println(!pathOne.equals(pathTwo));
    if (!pathOne.equals(pathTwo)) {
%>

      禁止网站外部提交表单！！！

<%}%>
<%
    String username = SysCommonFunc.getStrParameter(request, "username");
    if (username.length() == 0) {
%>

<script language=javascript>
    alert('用户名不能为空');
    window.history.go(-1);

</script>
<%
        return;
    }
    String tel = SysCommonFunc.getStrParameter(request, "tel");
    //tel.length() == 0
    if (false) {
%>
<script language=javascript>

    alert('电话号码不能为空');
    window.history.go(-1);
</script>

<%
        return;
    }
    String email = SysCommonFunc.getStrParameter(request, "email");
    if (email.length() == 0) {
%>
<script language=javascript>

    alert('电子邮件不能为空');
    window.history.go(-1);
</script>

<%
        return;
    }

    String ntitle = SysCommonFunc.getStrParameter(request, "ntitle");
    String ntext = SysCommonFunc.getStrParameter(request, "ntext");
    if (ntext.length() == 0) {
%>
<script language=javascript>
    alert('内容不能为空');
    window.history.go(-1);
</script>

<%
        return;
    }
    if (ntext.length() > 250 || ntext.toLowerCase().indexOf("href") > -1) {
%>


<script language=javascript>
   alert('内容不能超过250个汉字,内容中不允许插入超链接');
    window.history.go(-1);
</script>

<%
        return;
    }
    String s =  KFZXMng.userAddKfzx(request, email);
    if (s == null) {
 %>
		<script language=Javascript>
		    alert("提交成功，谢谢");
		    window.location.href = 'lxwm.jsp';
		</script>
<% 
} else {
%>
<script language=Javascript>
    alert("<%=s%>");
    window.history.go(-1);
</script>
<%
    }
%>
