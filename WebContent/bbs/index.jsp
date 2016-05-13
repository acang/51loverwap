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
<title>51交友中心</title>
<meta name="description" content="51交友中心网站--中国交友行业领军品牌。亲密关系、终生伴侣、情商训练，全方位支持您的情感生活。十年运营，千万会员，美满感情，从51交友开始！" />
<meta name="keywords" content="51交友中心--亲密交友 情缘交友 同城交友 知己交友 美女交友" />
<link rel="stylesheet" type="text/css" href="../css/style.css?v=111" />
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/lcmbase.js"></script>
</head>
 <body>
<%
Userinfo tempLoginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
if(tempLoginUser == null)
 {
   	 out.println("<script language='javascript'>alert('您是游客，请先登陆！');</script>");
     out.println("<script langauge=javascript>location.href = '../login.jsp?url=bbs/index.jsp';</script>");
     return;
 }
%>
 
 
<div></div>
<div id="header">
    <div class="h_nav"> 
    <a href="../index.jsp"><div class="h_left"><img src="../images/l.png" /></div></a>
                      会员交流</div>
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
