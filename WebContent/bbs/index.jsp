<%@ page import="com.common.SysDefine" %>
<%@ page import="com.lover.mng.UserVisitMng" %>
<%@ page import="com.web.obj.*" %>
<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<meta content="telephone=no" name="format-detection"/>
<title>51��������</title>
<meta name="description" content="51����������վ--�й�������ҵ���Ʒ�ơ����ܹ�ϵ���������¡�����ѵ����ȫ��λ֧������������ʮ����Ӫ��ǧ���Ա���������飬��51���ѿ�ʼ��" />
<meta name="keywords" content="51��������--���ܽ��� ��Ե���� ͬ�ǽ��� ֪������ ��Ů����" />
<link rel="stylesheet" type="text/css" href="../css/style.css?v=111" />
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/lcmbase.js"></script>
</head>
 <body>
<%
Userinfo tempLoginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
if(tempLoginUser == null)
 {
   	 out.println("<script language='javascript'>alert('�����οͣ����ȵ�½��');</script>");
     out.println("<script langauge=javascript>location.href = '../login.jsp?url=bbs/index.jsp';</script>");
     return;
 }
%>
 
 
<div></div>
<div id="header">
    <div class="h_nav"> 
    <a href="../index.jsp"><div class="h_left"><img src="../images/l.png" /></div></a>
                      ��Ա����</div>
</div>
<div id="inner">
    <div class="hyjl">
        <a href="bbsindex.jsp?sortid=13"><img src="../images/jl_img1.png" /></a>
        <a href="bbsindex.jsp?sortid=3"><img src="../images/jl_img2.png" /></a>
        <a href="bbsindex.jsp?sortid=12"><img src="../images/jl_img3.png" /></a>
        <a href="bbsindex.jsp?sortid=9"><img src="../images/jl_img4.png" /></a>
    </div>
    
    <%@ include file="bottom2.jsp"%>
    <%@ include file="bottom.jsp"%>
</div>              
<script type="text/javascript" src="../js/hammer.js"></script>  
<script type="text/javascript" src="../js/hammerPluin.js"></script>  
</body>
</html>
