<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.web.obj.*"%>
<%@ page import="com.web.common.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lover.mng.*"%>
<%@ page import="sun.misc.BASE64Encoder" %>
<%@ page import="com.web.bean.*"%>
<%@ page import="org.apache.commons.beanutils.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.Date"%>

<%
   /* String login = System.currentTimeMillis()+"";
    session.setAttribute("login",login);
    session.setAttribute("sp",login);
   // String vurl =  request.getRequestURL().toString();*/
    String login = (String)session.getAttribute("login");
    session.setAttribute("login",login);
    session.setAttribute("sp",login);
   String vurl =  request.getRequestURL().toString();
    String url = SysCommonFunc.getStrParameter(request,"url");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<title>51交友中心</title>
<meta name="description" content="51交友中心网站-中国交友行业领军品牌。亲密关系、终生伴侣、情商训练，全方位支持您的情感生活。十年运营，千万会员，美满感情，从51交友开始！" />
<meta http-equiv=”Cache-Control” content=”no-transform” />
<meta http-equiv=”Cache-Control” content=”no-siteapp” />
<meta name="keywords" content="51交友中心--亲密交友 情缘交友 同城交友 知己交友 美女交友" />
<link rel="stylesheet" type="text/css" href="css/style.css?v=<%=System.currentTimeMillis() %>">
</head>
<body>
<div id="inner">
        <form name="personal" method="post" action="afterregnewuser.jsp" onSubmit="return test()" target="cname">
        	<input type="hidden" name="url"  value="<%=url%>"/>
        	<input type="hidden" id="isWeixin" name="isWeixin"  value="0"/>
			<div class="input"> <img src="images/sj.png" /> <input name="sjtel" id="sjtel" type="text" class="inp_txt2" placeholder="输入手机号码" value="" />  <a href="javascript:void(0);" onclick="sendSms();"><div class="inp_yz" style="text-align:center;">获取验证码</div></a>   </div>
        	<div class="input">  <img src="images/sr.png" /> <input name="verycode" id="verycode" type="text" class="inp_txt1" placeholder="输入验证码" value="" /></div>
        	<div class="input"><img src="images/mm.png" /> <input name="password" type="password" class="inp_txt1" placeholder="设定密码（8位以上字符）" />   </div>
        	<div class="input"><img src="images/mm.png" /> <input name="repassword" type="password" class="inp_txt1" placeholder="重复密码" />   </div>
        	<div style="text-align:center;"><button class="btn tjan">免费注册</button></div>
		</form>
</div>
    <%@ include file="bottom.jsp"%>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/lcmbase.js"></script>
<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script> 
<script type="text/javascript" src="js/btm.js"></script>
<script language="JavaScript">
	function tag_byteLen(str) {
    	var len = 0;

    	for (var i = 0; i < str.length; i++) {
        	var code = str.charCodeAt(i);
       		if (code > 255) {
            	len += 2;
          	}else {
            	len += 1;
        	}
    	}
		return len;
	}
	
	function validate(){
		if (checkspace(document.personal.sjtel.value)){
			alert('请填写您的手机号!');
			document.personal.sjtel.focus();
			return false;
		}
		if (checkspace(document.personal.password.value)){
			alert('请填写您的密码!');
			document.personal.password.focus();
			return false;
		}
		return true;
	}

	function validatesj(){
		if (checkspace(document.personal.sjtel.value)){
			alert('请填写您的手机号码。移动、联通、电信手机均可免费获取验证码!');
			document.personal.sjtel.focus();
			return false;
		}
	
		var reg = /^1[3|4|5|7|8]\d{9}$/;
		if(!reg.test(document.personal.sjtel.value))
		{
			alert("手机号码不正确!");
			return false;
		}
		
		if (checkspace(document.personal.verycode.value)){
			alert('请填写您的短信验证码');
			document.personal.verycode.focus();
			return false;
		}
		return true;
	}
	
	function checkspace(checkstr) {
    	var str = '';
    	for(i = 0; i < checkstr.length; i++) {
    		str = str + ' ';
    	}
    	return (str == checkstr);
	}
	function Check(){
    	document.cform.verycode.value=document.personal.verycode.value;
    	document.cform.submit();

	}
	function sendSms(){
		if (checkspace(document.personal.sjtel.value)){
			alert('请填写您的手机号码。移动、联通、电信手机均可免费获取验证码!');
			document.personal.sjtel.focus();
			return;
		}
	
		var reg = /^1[3|4|5|7|8]\d{9}$/;
		if(!reg.test(document.personal.sjtel.value))
		{
			alert("手机号码不正确!");
			return;
		}else{
			document.csendsmsval.sjtel.value =  document.personal.sjtel.value;
			document.csendsmsval.submit();
		}
	}
	
	function callback(msg){
		if (msg="33"){
			document.csendsms.sjtel.value =  document.personal.sjtel.value;
			document.csendsms.submit();
		}
	}
	
	function isWeiXin(){
	    var ua = window.navigator.userAgent.toLowerCase();
	    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
	        return true;
	    }else{
	        return false;
	    }
	}
	
	function test(){
        if (checkspace(document.personal.password.value)){
            alert('请输入密码!');
            document.personal.password.focus();
            return false;
        }
        if(document.personal.password.value.length<8)
        {
            alert("密码由8位以上字符组成!");
            document.personal.password.focus();
            return false;
        }
        if (checkspace(document.personal.repassword.value)){
            alert('请输入密码确认!');
            document.personal.repassword.focus();
            return false;
        }
        if( document.personal.repassword.value.length<8)
        {

            alert("密码确认由8位以上字符组成!");
            return false;
        }

        if (document.personal.password.value!=document.personal.repassword.value)
        {
            alert('您两次输入的密码不一致，请重新输入!');
            document.personal.repassword.focus();
            return false;
        }
        if(isWeiXin()){
        	document.personal.isWeixin.value="1";
        }
        return true;
    }
</script>
</body>
</html>

<form name="csendsmsval" action="sjreg.jsp" method="post" target="cname">
    <input name="sp" value="<%=login%>" type="hidden"/>
    <input name="sjtel" value="" type="hidden"/>
    <input type="hidden" name="url"  value="<%=url%>"/>
</form>
