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
    String login = System.currentTimeMillis()+"";
    session.setAttribute("login",login);
    session.setAttribute("sp",login);
    String username = (String)session.getAttribute("sjtel");
    if(username == null){
    	username= "";
    }
    String url = SysCommonFunc.getStrParameter(request,"url");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="stylesheet" type="text/css" href="css/style.css?v=<%=System.currentTimeMillis() %>">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>51��������</title>
<meta name="description" content="51����������վ-�й�������ҵ���Ʒ�ơ����ܹ�ϵ���������¡�����ѵ����ȫ��λ֧������������ʮ����Ӫ��ǧ���Ա���������飬��51���ѿ�ʼ��" />
<meta http-equiv=��Cache-Control�� content=��no-transform�� />
<meta http-equiv=��Cache-Control�� content=��no-siteapp�� />
<meta name="keywords" content="51��������--���ܽ��� ��Ե���� ͬ�ǽ��� ֪������ ��Ů����" />
</head>
<body>

<div id="header">
    <div class="h_nav"> <a href="index.jsp"><div class="h_left"><img src="images/l.png" /></div></a>  ����ע��</div>
</div>

<div id="inner">
    <div class="hyzl">
        <div class="hyzl_nav" id="hyzl_nav">
			<div id="login1"  class="h_n_on f_l bodo_r" style="width:33%;">
            	<li tab="dl1">����ע��</li>
			</div>
			<div id="login2" class="h_n_on f_l bodo_r" style="width:33%;">
            	<li tab="dl2">���ŵ�½</li>
			</div>
			<div id="login3" class="h_n_on f_l bodo_r" style="width:33%;">
            	<li tab="dl3">��ͨ��¼</li>
			</div>
        </div>
    </div>
	<div>
		<div id="dl1" class="dl">
			<iframe width="100%" height="720px" src="reg_inner.jsp" name="tgjl_frame" frameborder="0"></iframe>
		</div>
		<form name="sj" method="post" action="./checksjlogin.jsp">
		<input type="hidden" name="url" value="<%=url%>"/>
			<div id="dl2" class="dl">
				<input type="hidden" id="goToUrl" name="goToUrl" value="index.jsp" />
				<div class="input"><img src="images/sj.png" /> <input name="sjtel" type="text" class="inp_txt2" placeholder="�ֻ�����" value="<%=username %>" />  <a href="javascript:void(0);" onclick="sendSms();"><div class="inp_yz">��ȡ��֤��</div></a>   </div>
        		<div class="input"><img src="images/sr.png" /> <input name="verycode" type="text" class="inp_txt1" placeholder="��д��֤��" /></div>
				<button class="btn tjan"  >������½</button>
			</div>
		</form>
		<div id="dl3" class="dl">
		<form name="pt" method="post" action="./checkuser.jsp" onSubmit="return validate();" target="cname">
				<input type="hidden" name="isauto" value="" />
	            <input type="hidden" name="login" value="<%=login %>" />
	            <input type="hidden" id="goToUrl" name="goToUrl" value="index.jsp" />
	            <input type="hidden" name="url" value="<%=url%>"/>
	            <div class="input"><img src="images/yhm.png" /> <input name="username" type="text" class="inp_txt1" placeholder="�û���" />    </div>
        		<div class="input"><img src="images/mm.png" /> <input name="password" type="password" class="inp_txt1" placeholder="����" />   </div>
        		 <button class="btn tjan"  >������½</button> 
			</form>
		 </div>
    </div>
	<%@ include file="bottom.jsp"%>
</div>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script> 
<script type="text/javascript" src="js/login.js?v=1"></script> 
<script type="text/javascript" src="js/btm.js"></script>
<script type="text/javascript">
<%
String sjreg = SysCommonFunc.getStrParameter(request,"sjreg");
if(sjreg !=null && "1".equals(sjreg))
{%>
	$(document).ready(function() {
	    $.jqtab("#hyzl_nav",".dl","h_n_in",1);
	});
<%}else if(sjreg !=null && "2".equals(sjreg))
{%>
$(document).ready(function() {
    $.jqtab("#hyzl_nav",".dl","h_n_in",1);
});
<%}else{ %>
	 $(document).ready(function() {
		 $.jqtab("#hyzl_nav",".dl","h_n_in",0);
	 });
<%}%>


$(function(){

    var ZCDetail=(function(){
        return {
            init:function(){
                this.bindEvent(); 
            }, 
            bindEvent:function(){
                var root=this;
                
				 
                $("#dl1  .tjan").click(function(){ 
                	pt.submit(); 
                });
                
                $("#dl2  .tjan").click(function(){ 
                	sj.submit(); 
                });
            }
        }
    })();
    ZCDetail.init();
	
});

function validate(){
	if (checkspace(document.pt.username.value)){
		alert('����д�����û���!');
		document.pt.username.focus();
		return false;
	}
	if (checkspace(document.pt.password.value)){
		alert('����д��������!');
		document.pt.password.focus();
		return false;
	}
	return true;
}

function validatesj()
{
	if (checkspace(document.sj.sjtel.value)){
		alert('����д�����ֻ����롣�ƶ�����ͨ�������ֻ�������ѻ�ȡ��֤��!');
		document.sj.sjtel.focus();
		return false;
	}

	var reg = /^1[3|4|5|7|8]\d{9}$/;
	if(!reg.test(document.sj.sjtel.value))
	{
		alert("�ֻ����벻��ȷ!");
		return false;
	}
	
	if (checkspace(document.sj.verycode.value)){
		alert('����д���Ķ�����֤��');
		document.sj.verycode.focus();
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

function sendSms()
{
	if (checkspace(document.sj.sjtel.value)){
		alert('����д�����ֻ����롣�ƶ�����ͨ�������ֻ�������ѻ�ȡ��֤��!');
		document.sj.sjtel.focus();
		return;
	}

	var reg = /^1[3|4|5|7|8]\d{9}$/;
	if(!reg.test(document.sj.sjtel.value))
	{
		alert("�ֻ����벻��ȷ!");
		return;
	}else{
		document.csendsmsval.sjtel.value =  document.sj.sjtel.value;
		document.csendsmsval.submit();
	}
}

function callback(msg){
	if (msg="33"){
		document.csendsms.sjtel.value =  document.sj.sjtel.value;
		document.csendsms.submit();
	}
}

function selectTag(showContent,selfObj){
	// ������ǩ
	var tag = document.getElementsByName("loginTab");
	var taglength = tag.length;
	for(i=0; i<taglength; i++){
		tag[i].className = "";
	}
	selfObj.className = "current";
	// ��������
	for(var i=0; i<3; i++){
		j=document.getElementById("tagContent"+i);
		j.style.display = "none";
	}
	document.getElementById(showContent).style.display = "block";
}
</script> 
</body>
</html>

<form name="csendsmsval" action="sendsmsloginval_mustreg.jsp" method="post" target="cname">
    <input name="sp" value="<%=login%>" type="hidden"/>
    <input name="sjtel" value="" type="hidden"/>
</form>
<form name="csendsms" action="sendsmslogin.jsp" method="post" target="cname">
    <input name="sp" value="<%=login%>" type="hidden"/>
    <input name="sjtel" value="" type="hidden"/>
</form>
<iframe name="cname" id="cname" src="" height="0" width="0">
</iframe>