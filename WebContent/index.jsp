<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.web.common.SysCommonFunc" %>
<%@ page import="com.common.SysDefine" %>
<%@ page import="com.web.obj.Userinfo" %>
<%@ page import="com.web.bean.QueryResult" %>
<%@ page import="com.web.obj.Bb" %>
<%@ page import="com.lover.CacheToolsNew" %>
<%
    Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
    String login = System.currentTimeMillis()+"";
    session.setAttribute("login",login);
    if(request.getRequestURL().toString().toLowerCase().indexOf("www.51lover.org") < 0&& request.getRequestURL().toString().toLowerCase().indexOf("localhost") < 0)
    {
        response.setStatus(301);
        response.setHeader("Location","http://www.51lover.org" );
        response.setHeader("Connection","close");
    }
    if (request.getRequestURL().toString().toLowerCase().indexOf(
            "kp56.net") > -1) {
        response.setStatus(403);
        response.sendError(403);
        return;
    }  
    request.setCharacterEncoding("gb2312");


    String url = (String) session.getAttribute("httpurl");
    if (url == null) {
        url = request.getHeader("referer");
        session.setAttribute("httpurl", url);
    }
    String advid = SysCommonFunc.getStrParameter(request, "advid");
    if (advid != null && advid.length() > 0)
        session.setAttribute("advid", advid);
    String tjid = SysCommonFunc.getStrParameter(request, "tjid");
    if (tjid != null && tjid.length() > 0)
        session.setAttribute("tjid", tjid);
%>
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
<meta name="description" content="51交友中心网站-中国交友行业领军品牌。亲密关系、终生伴侣、情商训练，全方位支持您的情感生活。十年运营，千万会员，美满感情，从51交友开始！" />
   <meta name="baidu-site-verification" content="XGXEHLUB1e" />
<meta name="keywords" content="51交友中心--亲密交友 情缘交友 同城交友 知己交友 美女交友" />
<link href="css/style.css?v=<%=System.currentTimeMillis() %>" rel="stylesheet" type="text/css" />
</head>


<body>

<div id="header">
    <div class="h_nav">51交友中心</div>
</div>
<div class="inner">

    <div class="i_nav">
        <ul>
            <li><a href="login.jsp"><img src="images/menu1.png" alt="" title="" /><span>登陆</span></a></li>
             <li><a href="reg.jsp"><img src="images/menu2.png" alt="" title="" /><span>简易注册</span></a></li>
             <li><a href="paymoney.jsp"><img src="images/menu3.png" alt="" title="" /><span>升级会员</span></a></li>
             <div class="line"><div class="l_p_l"></div> <div class="l_p_c"></div> <div class="l_p_r"></div></div>
             <li><a href="search.jsp"><img src="images/menu4.png" alt="" title="" /><span>查找会员</span></a></li>
             <li><a href="bbs/index.jsp"><img src="images/menu5.png" alt="" title="" /><span>交流分享</span></a></li>
             <li><a href="train.jsp"><img src="images/menu6.png" alt="" title="" /><span>情商训练</span></a></li>
        </ul>
    </div>
    <div class="fg"></div>
    
    <div class="i_con">
        <div class="ic_nav zla"><img src="images/ioe.png" /> <div class="icn_p">诚意新会员</div> </div>
        <%=CacheToolsNew.getTopOneSbNew()%>
    </div>
    <div class="fg"></div>
    
    <div class="i_con">
        <div class="ic_nav"><img src="images/ir.png" /> <div class="icn_p red">重点男会员</div> </div>
        <%=CacheToolsNew.getTopRqzwManSbNew()%>   
    </div>
    <div class="fg"></div>
    
    <div class="i_con">
        <div class="ic_nav"><img src="images/ir.png" /> <div class="icn_p red">重点女会员</div> </div>
        <%=CacheToolsNew.getTopRqzwWonSbNew()%>  
    </div>
    <%@ include file="bottom2.jsp"%>
    <%@ include file="bottom.jsp"%>
 
</div>

</body>
</html>
