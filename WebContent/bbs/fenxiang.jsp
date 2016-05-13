<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<meta content="telephone=no" name="format-detection"/>
<title>51交友中心</title> 
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div class="fenxiang">
    
                                   	<!-- Baidu Button BEGIN  百度分享-->
    
                                           <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare" >
    
                                               <span class="bds_more">分享到：</span>
    
                                               <a class="bds_qzone"></a>
    
                                               <a class="bds_tsina"></a>
    
                                               <a class="bds_tqq"></a>
    
                                               <a class="bds_renren"></a>
    
                                               <a class="bds_t163"></a>
    
                                               <a class="shareCount"></a>
    
                                           </div>
    
                                           <script type="text/javascript" id="bdshare_js" data="type=tools&uid=0" ></script>
    
                                           <script type="text/javascript" id="bdshell_js"></script>
    
                                           <script type="text/javascript">
    
                                               document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date() / 3600000)
    
                                           </script>
    
                                       <!-- Baidu Button END -->
    
                                   </div>
  </body>
</html>
