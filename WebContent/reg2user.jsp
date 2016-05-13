<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.web.common.*"%>
<%@ page import="com.lover.mng.*"%>
<%@ page import="com.web.obj.*"%>
<%@ page import="com.common.SysDefine" %>
<%@ page import="com.web.obj.extend.DicList" %>
<%
    String username = SysCommonFunc.getStrParameter(request,"username");
    String hyid     = SysCommonFunc.getStrParameter(request,"hyid");
    String tjid     = SysCommonFunc.getStrParameter(request,"tjid");
    if(tjid != null && tjid.length() > 0)
    {
        session.setAttribute("tjid",tjid);
    }

    Userinfo userinfo = HYRegMng.getDelUserInfoByHyid(hyid);
    Userinfo userinfo2=HYRegMng.getUserinfoByUsername(username);
    if(userinfo != null && !userinfo.getUsername().toLowerCase().equals(username.toLowerCase()))
    {
        userinfo = null;
    }

    if(userinfo == null)
    {
        out.println("<script>alert('没有找到用户信息，或者已经被激活！')</script>");
        return;
    }
    if(userinfo2!=null)
    {
        out.println("<script>alert('您的信息已注册！');location.href='reg.jsp';</script>");
        return;
    }
    String ssreg = System.currentTimeMillis()+"";
    session.setAttribute("ssreg",ssreg);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>51交友中心</title>
<meta name="description" content="51交友中心网站-中国交友行业领军品牌。亲密关系、终生伴侣、情商训练，全方位支持您的情感生活。十年运营，千万会员，美满感情，从51交友开始！" />
<meta http-equiv=”Cache-Control” content=”no-transform” />
<meta http-equiv=”Cache-Control” content=”no-siteapp” />
<meta name="keywords" content="51交友中心--亲密交友 情缘交友 同城交友 知己交友 美女交友" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/lcmbase.js"></script>
<script src="js/Popup.js"></script>
<script src="js/tom_reg2.js"></script>
<script language="JavaScript">
function tan()
{
    document.getElementById('lcname1').style.display ='none';
    document.getElementById('qggx1').style.display ='none';
    document.getElementById('lxqr1').style.display ='none';
    document.getElementById('jyly1').style.display ='none';
    alert("恭喜您，验证成功! 请完善您的资料.");
}
function tag_byteLen(str) {
    var len = 0;

    for (var i = 0; i < str.length; i++) {
        var code = str.charCodeAt(i);
        if (code > 255) {
            len += 2;
        }
        else {
            len += 1;
        }
    }
    return len;
}

function askCheck(){
    if (checkspace(document.personal.ask.value)){
        document.getElementById("askinfo").innerHTML="<font color='red'>（请输入密码保护的问题！）</font>";
        return;
    }
    document.getElementById("askinfo").innerHTML="";
}
function answerCheck(){
          askCheck();
    if (checkspace(document.personal.answer.value)){
        document.getElementById("answerinfo").innerHTML="<font color='red'>（请输入密码保护的答案！）</font>";
        return;
    }
    document.getElementById("answerinfo").innerHTML="";
    if (document.personal.answer.value==document.personal.ask.value){
        document.getElementById("answerinfo").innerHTML="<font color='red'>（密码保护答案不能和密码保护问题相同！）</font>";
        return;
    }
    document.getElementById("answerinfo").innerHTML="";
}

function lcnameCheck(){
    answerCheck();
    if (checkspace(document.personal.lcname.value)){
        document.getElementById("lcnameinfo").innerHTML="<font color='red'>（请输入您的昵称！）</font>";
        return;
    }
    document.getElementById("lcnameinfo").innerHTML="";
    var s =document.personal.lcname.value ;
    if (tag_byteLen(s)>12)
    {
        document.getElementById("lcnameinfo").innerHTML="<font color='red'>（您输入的昵称不能超过6个字,请重填！）</font>";
        return ;
    }
    document.getElementById("lcnameinfo").innerHTML="";
    var shu="0123456789";
    var s =document.personal.lcname.value ;
    if (((s.indexOf("http")>=0)||(s.indexOf("www")>=0)||(s.indexOf("@")>=0)))
    {
        document.getElementById("lcnameinfo").innerHTML="<font color='red'>（您输入的昵称含有疑似联系办法的内容,请重填！）</font>";
        return ;
    }
    document.getElementById("lcnameinfo").innerHTML="";
    j=0;
    var x=0;
    for (i=0;i<s.length;i++)
    {
        if ((shu.indexOf(s.charAt(i)))>=0 )
        {
            j=j+1;
            if (j==6)
            {x=1;
            }
        }
        else
        {   j=0;
        }
    }

    if (x==1) {
        document.getElementById("lcnameinfo").innerHTML="<font color='red'>（您输入的昵称含有疑似联系办法的内容,请重填！）</font>";
        return ;
    }
    document.getElementById("lcnameinfo").innerHTML="";

}


function addressCheck(){
    lcnameCheck();
    if (document.personal.s1.value=='-1')
    {
        document.getElementById("addressinfo").innerHTML="<font color='red'>（地址不能有空项！）</font>";
        return;
    }
    document.getElementById("addressinfo").innerHTML="";
    if(document.personal.s2.value=="-1" || document.personal.s2.value=="")
    {
        document.getElementById("addressinfo").innerHTML="<font color='red'>（城市地址不能有空项！）</font>";
        return;
    }
    document.getElementById("addressinfo").innerHTML="";
    if(document.personal.s3.value=="" || document.personal.s3.value=="县区")
    {
        document.getElementById("addressinfo").innerHTML="<font color='red'>（请输入您的地址县区名称！）</font>";
        return;
    }
    document.getElementById("addressinfo").innerHTML="";
}

function xgtzCheck(){
    addressCheck();
    var xgtz01="";
   var  i=0;
   var j=0;
    for (i=0;i<document.personal.xgtz.length;i++) {
        if (document.personal.xgtz[i].checked)
        {
            j=1+j;
            xgtz01=xgtz01+"1"
        }
        else{
            xgtz01=xgtz01+"0"
        }

    }

    if (j==0||j>=5) {
          document.getElementById("xgtzinfo").innerHTML="<font color='red'>（请选择您的性格特征！并且不超过四项！）</font>";
        return;
    }


    document.personal.xgtz01.value=xgtz01;
    document.getElementById("xgtzinfo").innerHTML="";
}
function yyshCheck(){
    xgtzCheck();
var yyap01="";
    var i=0;
    var j=0;
    for (i=0;i<document.personal.yyap.length;i++) {
        if (document.personal.yyap[i].checked)
        {
            j=1+j;
            yyap01=yyap01+"1"
        }
        else{
            yyap01=yyap01+"0"
        }
    }
    if (j==0||j>=5) {
         document.getElementById("yyshinfo").innerHTML="<font color='red'>（请选择您的业余安排！并且不超过四项！）</font>";
        return;
    }
    document.personal.yyap01.value=yyap01;
    document.getElementById("yyshinfo").innerHTML="";
}
function jyyxCheck(){
    yyshCheck();
    var jyyx01="";
    var i=0;
    var j=0;
    for (i=0;i<document.personal.jyyx.length;i++) {
        if (document.personal.jyyx[i].checked)
        { j=1+j;
            jyyx01=jyyx01+"1";
        }else
        {
            jyyx01=jyyx01+"0";
        }

    }
    if (j==0||j>=5) {
        document.getElementById("jyyxinfo").innerHTML="<font color='red'>（请选择交友意向！并且不超过四项！）</font>";
        return;
    }
    document.personal.jyyx01.value="1"+jyyx01;
    document.getElementById("jyyxinfo").innerHTML="";
}

function qggxCheck(){
     jyyxCheck();
    var qggx1 = document.personal.qggx.value;
    if (checkspace(document.personal.qggx.value)){
        document.getElementById("qggxinfo").innerHTML="<font color='red'>（请输入您对情爱关系的看法！）</font>";
        return;
    }
    document.getElementById("qggxinfo").innerHTML="";
    if (document.personal.qggx.value.length>200){
        document.getElementById("qggxinfo").innerHTML="<font color='red'>（情爱关系长度不能超过200字符！）</font>";
        return ;
    }
    document.getElementById("qggxinfo").innerHTML="";
    var shu="0123456789";
    var s =document.personal.qggx.value ;
    if (((s.indexOf("http")>=0)||(s.indexOf("www")>=0)||(s.indexOf("@")>=0)))
    {
        document.getElementById("qggxinfo").innerHTML="<font color='red'>（您输入的情爱关系含有疑似联系办法的内容！）</font>";
        return ;
    }
    document.getElementById("qggxinfo").innerHTML="";
    j=0;
    var x=0;
    for (i=0;i<s.length;i++)
    {
        if ((shu.indexOf(s.charAt(i)))>=0 )
        {
            j=j+1;
            if (j==6)
            {x=1;
            }
        }
        else
        {   j=0;
        }
    }

    if (x==1) {
        document.getElementById("qggxinfo").innerHTML="<font color='red'>（您输入的情爱关系含有疑似联系办法的内容！）</font>";
        return;
    }
    document.getElementById("qggxinfo").innerHTML="";

    var result = getRepeatData(qggx1);
    if (result!="")
    {
        document.getElementById("qggxinfo").innerHTML="<font color='red'>（请输入有意义的情爱关系留言！）</font>";
        return false;
    }
    document.getElementById("qggxinfo").innerHTML="";
}


function lxqrCheck(){
    qggxCheck();
    var lxqr1 = document.personal.lxqr.value;
    if (checkspace(document.personal.lxqr.value)){
        document.getElementById("lxqrinfo").innerHTML="<font color='red'>（请输入您的理想情人！）</font>";
        return;
    }
    document.getElementById("lxqrinfo").innerHTML="";
    if (document.personal.lxqr.value.length>600){
        document.getElementById("lxqrinfo").innerHTML="<font color='red'>（理想情人长度不能超过600字符！）</font>";
        return ;
    }
    document.getElementById("lxqrinfo").innerHTML="";
    if (((lxqr1.indexOf("script")>=0)||(lxqr1.indexOf("<")>=0)||(lxqr1.indexOf(">")>=0)))
    {
        document.getElementById("lxqrinfo").innerHTML="<font color='red'>（您输入的理想情人含有违法内容,请重填！）</font>";
        return;
    }
    document.getElementById("lxqrinfo").innerHTML="";
    s =document.personal.lxqr.value ;
    if (((s.indexOf("http")>=0)||(s.indexOf("www")>=0)||(s.indexOf("@")>=0)))
    {
        document.getElementById("lxqrinfo").innerHTML="<font color='red'>（您输入的留言中含有疑似联系办法的内容,请重填！）</font>";
        return;
    }
    document.getElementById("lxqrinfo").innerHTML="";
    var result = getRepeatData(lxqr1);
    if (result!="")
    {
        document.getElementById("lxqrinfo").innerHTML="<font color='red'>（请输入有意义的理想情人描述）</font>";
        return;
    }
    document.getElementById("lxqrinfo").innerHTML="";
}



function jylyCheck(){
    lxqrCheck();
    var jyly1 = document.personal.jyly.value;
    if (checkspace(document.personal.jyly.value)){
        document.getElementById("jylyinfo").innerHTML="<font color='red'>（请输入您的交友留言！）</font>";
        return;
    }
    document.getElementById("jylyinfo").innerHTML="";
    if (document.personal.jyly.value.length>600){
        document.getElementById("jylyinfo").innerHTML="<font color='red'>（交友留言长度不能超过600字符！）</font>";
        return;
    }
    document.getElementById("jylyinfo").innerHTML="";
    if ((document.personal.jyly.value.length<20) || (document.personal.jyly.value.length>300))
    {
        document.getElementById("jylyinfo").innerHTML="<font color='red'>（为保证交友效果，留言请在20字以上，并请勿填写任何联系方式）</font>";
        return;
    }
    document.getElementById("jylyinfo").innerHTML="";
      var s =document.personal.jyly.value ;
    if (((s.indexOf("http")>=0)||(s.indexOf("www")>=0)||(s.indexOf("@")>=0)))
    {
        document.getElementById("jylyinfo").innerHTML="<font color='red'>（您输入的留言中含有疑似联系办法的内容,请重填）</font>";
        return;
    }
    document.getElementById("jylyinfo").innerHTML="";
    var result = getRepeatData(jyly1);
    if (result!="")
    {
        document.getElementById("jylyinfo").innerHTML="<font color='red'>（请输入有意义的交友留言）</font>";
        return;
    }
    document.getElementById("jylyinfo").innerHTML="";
}

function test()
{
    var jyyx01,i,j=0;
    if (checkspace(document.personal.answer.value)){
        alert('请输入密码保护的问题！');
        document.personal.answer.focus();
        return;
    }

    if (document.personal.answer.value==document.personal.ask.value){
        alert('密码保护答案不能和密码保护问题相同！');
        document.personal.answer.focus();
        return;
    }
    if (checkspace(document.personal.answer.value)){
        alert('请输入密码保护的答案！');
        document.personal.answer.focus();
        return;
    }

    if (document.personal.answer.value==document.personal.ask.value){
        alert('密码保护答案不能和密码保护问题相同！');
        document.personal.answer.focus();
        return;
    }

    if (checkspace(document.personal.lcname.value)){
        alert('请输入您的昵称！');
        document.personal.lcname.focus();
        return;
    }
    var s =document.personal.lcname.value ;
    if (tag_byteLen(s)>12)
    {
        alert('您输入的昵称不能超过6个字,请重填');
        document.personal.lcname.focus();
        return ;
    }
    var shu="0123456789";
    var s =document.personal.lcname.value ;
    if (((s.indexOf("http")>=0)||(s.indexOf("www")>=0)||(s.indexOf("@")>=0)))
    {
        alert('您输入的昵称含有疑似联系办法的内容,请重填');
        document.personal.lcname.focus();
        return ;
    }
    j=0;
    var x=0;
    for (i=0;i<s.length;i++)
    {
        if ((shu.indexOf(s.charAt(i)))>=0 )
        {
            j=j+1;
            if (j==6)
            {x=1;
            }
        }
        else
        {   j=0;
        }
    }

    if (x==1) {
        alert('您输入的昵称含有疑似联系办法的内容,请重填');
        document.personal.lcname.focus();
        return ;
    }
    if (document.personal.s1.value=='-1')
    {
        alert('地址不能有空项。');
        document.personal.s1.focus();
        return;
    }
    if(document.personal.s2.value=="-1" || document.personal.s2.value=="")
    {
        alert('城市地址不能有空项。');
        document.personal.s1.focus();
        return;
    }

    if(document.personal.s3.value=="" || document.personal.s3.value=="县区")
    {
        alert('请输入您的地址县区名称！');
        document.personal.s1.focus();
        return;
    }

    var xgtz01="",yyap01="",jyyx01=""
    i=0;
    j=0;
    for (i=0;i<document.personal.xgtz.length;i++) {
        if (document.personal.xgtz[i].checked)
        {
            j=1+j;
            xgtz01=xgtz01+"1"
        }
        else{
            xgtz01=xgtz01+"0"
        }

    }

    if (j==0||j>=5) {
        alert("请选择您的性格特征！并且不超过四项！")
        return;
    }

    document.personal.xgtz01.value=xgtz01;

    i=0;
    j=0;
    for (i=0;i<document.personal.yyap.length;i++) {
        if (document.personal.yyap[i].checked)
        {
            j=1+j;
            yyap01=yyap01+"1"
        }
        else{
            yyap01=yyap01+"0"
        }
    }
    if (j==0||j>=5) {
        alert("请选择您的业余安排！并且不超过四项！")
        return;
    }
    document.personal.yyap01.value=yyap01;

    i=0;
    j=0;
    for (i=0;i<document.personal.jyyx.length;i++) {
        if (document.personal.jyyx[i].checked)
        { j=1+j;
            jyyx01=jyyx01+"1";
        }else
        {
            jyyx01=jyyx01+"0";
        }

    }
    if (j==0||j>=5) {
        alert("请选择交友意向！并且不超过四项！")
        return;
    }
    document.personal.jyyx01.value="1"+jyyx01;

    if (checkspace(document.personal.qggx.value)){
        alert('请输入您对情爱关系的看法！');
        document.personal.qggx.focus();
        return;
    }

    if (document.personal.qggx.value.length>200){
        alert('情爱关系长度不能超过200字符！');
        document.personal.qggx.focus();
        return ;
    }

    var shu="0123456789";
    var s =document.personal.qggx.value ;
    if (((s.indexOf("http")>=0)||(s.indexOf("www")>=0)||(s.indexOf("@")>=0)))
    {
        alert('您输入的情爱关系含有疑似联系办法的内容,请重填');
        document.personal.qggx.focus();
        return ;
    }
    j=0;
    var x=0;
    for (i=0;i<s.length;i++)
    {
        if ((shu.indexOf(s.charAt(i)))>=0 )
        {
            j=j+1;
            if (j==6)
            {x=1;
            }
        }
        else
        {   j=0;
        }
    }

    if (x==1) {
        alert('您输入的情爱关系含有疑似联系办法的内容,请重填');
        document.personal.qggx.focus();
        return;
    }

    if (checkspace(document.personal.lxqr.value)){
        alert('请输入您的理想情人！');
        document.personal.lxqr.focus();
        return;
    }

    if (document.personal.lxqr.value.length>600){
        alert('理想情人长度不能超过600字符！');
        document.personal.lxqr.focus();
        return ;
    }

    var shu="0123456789";
    var s =document.personal.qggx.value ;
    if (((s.indexOf("http")>=0)||(s.indexOf("www")>=0)||(s.indexOf("@")>=0)))
    {
        alert('您输入的情性关系含有疑似联系办法的内容,请重填');
        document.personal.qggx.focus();
        return;
    }
    var m = document.personal.lxqr.value;
    if (((m.indexOf("http")>=0)||(m.indexOf("www")>=0)||(m.indexOf("@")>=0)))
    {
        alert('您输入的理想情人含有疑似联系办法的内容,请重填');
        document.personal.lxqr.focus();
        return;
    }
    j=0;
    var x=0;
    for (i=0;i<m.length;i++)
    {
        if ((shu.indexOf(m.charAt(i)))>=0 )
        {
            j=j+1;
            if (j==6)
            {x=1;
            }
        }
        else
        {   j=0;
        }
    }

    if (x==1) {
        alert('您输入的理想情人含有疑似联系办法的内容,请重填');
        document.personal.lxqr.focus();
        return;
    }


    if (checkspace(document.personal.jyly.value)){
        alert('请输入您的交友留言！');
        document.personal.jyly.focus();
        return;
    }
    if (document.personal.jyly.value.length>600){
        alert('交友留言长度不能超过600字符！');
        document.personal.jyly.focus();
        return;
    }
    if ((document.personal.jyly.value.length<20) || (document.personal.jyly.value.length>300))
    {
        alert('为保证交友效果，留言请在20字以上，并请勿填写任何联系方式');
        document.personal.jyly.focus();
        return;
    }
    s =document.personal.jyly.value ;
    if (((s.indexOf("http")>=0)||(s.indexOf("www")>=0)||(s.indexOf("@")>=0)))
    {
        alert('您输入的留言中含有疑似联系办法的内容,请重填');
        document.personal.jyly.focus();
        return;
    }
    var qggx1 = document.personal.qggx.value;
    var lxqr1 = document.personal.lxqr.value;
    var jyly1 = document.personal.jyly.value;

    if (((qggx1.indexOf("script")>=0)||(qggx1.indexOf("<")>=0)||(qggx1.indexOf(">")>=0)||(qggx1.indexOf("null")>=0|| (qggx1==""))))
    {
        alert('您输入的情爱关系含有违法内容,请重填');
        document.personal.qggx.focus();
        return false;
    }
      var result = getRepeatData(qggx1);
    if (result!="")
    {
        alert('请输入有意义的情爱关系留言！');
        document.personal.qggx.focus();
        return false;
    }

    if (((lxqr1.indexOf("script")>=0)||(lxqr1.indexOf("<")>=0)||(lxqr1.indexOf(">")>=0)))
    {
        alert('您输入的理想情人含有违法内容,请重填');
        document.personal.lxqr.focus();
        return false;
    }
     result = getRepeatData(lxqr1);
    if (result!="")
    {
        alert('请输入有意义的理想情人描述！');
        document.personal.lxqr.focus();
        return false;
    }
    if (((jyly1.indexOf("script")>=0)||(jyly1.indexOf("<")>=0)||(jyly1.indexOf(">")>=0)))
    {
        alert('您输入的交友留言含有违法内容,请重填');
        document.personal.jyly.focus();
        return false;
    }
    result = getRepeatData(jyly1);
    if (result!="")
    {
        alert('请输入有意义的交友留言！');
        document.personal.jyly.focus();
        return false;
    }
    j=0;
    x=0;
    for (i=0;i<s.length;i++)
    {
        if ((shu.indexOf(s.charAt(i)))>=0 )
        {
            j=j+1;
            if (j==6)
            {x=1;
            }
        }
        else
        {   j=0;
        }
    }

    if (x==1) {
        alert('您输入的留言中含有疑似联系办法的内容,请重填');
        document.personal.jyly.focus();
        return;
    }

    if (checkspace(document.personal.sjtel.value))
    {
        alert("请输入手机号码");
        document.personal.sjtel.focus();
        return ;
    }
    var reg = /^1[3|4|5|7|8]\d{9}$/;
    if(!reg.test(document.personal.sjtel.value))
    {
        alert("手机号码不正确!");
        document.personal.sjtel.focus();
        return;
    }
    document.personal.submit();
 }

function checkspace(checkstr) {
    var str = '';
    for(i = 0; i < checkstr.length; i++) {
        str = str + ' ';
    }
    return (str == checkstr);
}
function Check(){
    document.cform.username.value=document.personal.username.value;
    document.cform.submit();

}

function getRepeatData(data){
    var data_array=data.split("");
    var result ="";
    for(var i=0;i<data_array.length;i++){
        if(data_array[i]!="" &&data_array[i+1]!="" &&data_array[i+2]!="" &&data_array[i+3]!="" ){
            if(data_array[i]==data_array[i+1] &&data_array[i]==data_array[i+2] &&data_array[i]==data_array[i+3]){
                result = data_array[i]+"出现超过4次";
                break;
            }
        }
    }
    return result;
}
</script>
</head>
<body class="reg"  onpaste="return false;" onselectstart="return false;" oncopy="return false;" oncut="return false;">
<%@ include file="head.jsp"%>
<div class="wrapper1250 cm_block01">
  <div class="lm_name">
    <h2>5分钟完成注册，找到倾心的朋友！</h2>
  </div>
  <div class="step_box"><span>1、用户名、密码设置</span><span class="current">2、完善基本资料及联系方式</span><span>3、完成</span></div>
  <div style="clear:both"></div>
  <form name="personal" method="post" action="afterreg2newuser.jsp" target="_blank">
  <input name="biz_action" value="01" type="hidden">
  <input name="username" value="<%=userinfo.getUsername()%>" type="hidden">
  <input name="hyid" value="<%=userinfo.getHyid()%>" type="hidden">
  <input name="ssreg" value="<%=ssreg%>" type="hidden"/>
<table width="888" border="0" align="center" cellpadding="4" cellspacing="0" class="reg_tab02" style="margin-bottom:0">
              <tbody>
                <tr>
                  <td width="200" align="right" bgcolor="#D6E6F3" class="bk04"><strong>用户名：</strong></td>
                  <td bgcolor="#FFFFFF"><strong><%=userinfo.getUsername()%></strong></td>
                </tr>
                <tr>
                  <td align="right" bgcolor="#D6E6F3" class="bk04"><strong>密码保护问题：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04">
                      <input name="ask" type="text" class="txt01" id="ask" value="" onblur="askCheck()">
                    （如：我的母亲叫什么？）<div id="askinfo"></div></td>
                </tr>
                <tr>
                  <td align="right" bgcolor="#D6E6F3" class="bk04"><strong>密码保护答案：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04">
                      <input name="answer" type="text" class="txt01" id="answer" value="" onblur="answerCheck()">
                      <div  id="answerinfo"></div>（如：秦丽）</td>
                </tr>
                <tr>
                  <td align="right"  bgcolor="#D6E6F3" class="bk04" style="padding-top:5px;"><strong>昵　称：</strong></td>
                  <td  bgcolor="#FFFFFF" class="bk04">
                      <input name="lcname" type="text" class="txt01" id="lcname" size="12" maxlength="12" width="12" value="" onblur="lcnameCheck()">
                      （起一个好听的交友昵称）<div id="lcnameinfo"></div></td>
                </tr>
                <tr>
                  <td align="right" bgcolor="#D6E6F3" class="bk04"><strong>您的性别：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04"><select name="sex" id="sex">
                      <%=DicList.getDicListOption(SysDefine.DIC_SEX, "-1")%>
                  </select>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <strong>生日：</strong>
                      <select name="year">
                          <%
                              Date cDate = new Date(System.currentTimeMillis());
                              int year = cDate.getYear()+1900;
                              for(int i =year-70;i <(year-15);i ++)
                              {
                          %>
                           <option value="<%=i%>"><%=i%></option>
                          <%
                              }
                          %>
                      </select>
                      年
                      <select name="month">
                          <%
                              for(int i =1;i <13;i ++)
                              {
                          %>

                          <option value="<%=i%>"><%=i%></option>
                          <%
                              }
                          %>
                      </select>
                      月
                      <select name="day">
                          <%
                              for(int i =1;i <32;i ++)
                              {
                          %>

                          <option value="<%=i%>"><%=i%></option>
                          <%
                              }
                          %>
                      </select>
                      日
                      <!-- （性别一旦填写提交，不能修改，只能查看异性资料，请准确填写！） --></td>
                </tr>
                <tr>
                  <td align="right" bgcolor="#D6E6F3" class="bk04"><strong>住址：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04">
                      <select name="s1" id="s1" onchange="SetCity(this,document.personal.s2)">
                          <option value="-1" selected>请选择：</option>
                          <option value="北京">北京</option>
                          <option value="天津">天津</option>
                          <option value="河北">河北</option>
                          <option value="山西">山西</option>
                          <option value="内蒙古">内蒙古</option>
                          <option value="辽宁">辽宁</option>
                          <option value="吉林">吉林</option>
                          <option value="黑龙江">黑龙江</option>
                          <option value="上海">上海</option>
                          <option value="江苏">江苏</option>
                          <option value="浙江">浙江</option>
                          <option value="安徽">安徽</option>
                          <option value="福建">福建</option>
                          <option value="江西">江西</option>
                          <option value="山东">山东</option>
                          <option value="河南">河南</option>
                          <option value="湖北">湖北</option>
                          <option value="湖南">湖南</option>
                          <option value="广东">广东</option>
                          <option value="广西">广西</option>
                          <option value="海南">海南</option>
                          <option value="重庆">重庆</option>
                          <option value="四川">四川</option>
                          <option value="贵州">贵州</option>
                          <option value="云南">云南</option>
                          <option value="西藏">西藏</option>
                          <option value="陕西">陕西</option>
                          <option value="甘肃">甘肃</option>
                          <option value="青海">青海</option>
                          <option value="宁夏">宁夏</option>
                          <option value="新疆">新疆</option>
                          <option value="香港">香港</option>
                          <option value="澳门">澳门</option>
                          <option value="台湾">台湾</option>
                          <option value="国外">国外</option>
                      </select>
                      <select name="s2" id="s2" style="width:80px;">
                          <option value="-1" selected>请选择：</option>
                      </select>
                      <input name="s3" type="text" class="input02" id="s3" value="县区" size="12" maxlength="5" onclick="this.value=''" onblur="addressCheck()"/>
                      <div id="addressinfo"></div>
                    </td>
                </tr>
                <tr>
                  <td align="right" bgcolor="#D6E6F3" class="bk04"><strong>身高：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04">
                      <select name="sg" size="1" class="border" id="sg" onclick="addressCheck()">
                      <%=DicList.getDicListOption(SysDefine.DIC_SG,"-1")%>
                  </select>
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>体重</strong>：
                          <select name="tz" size="1" class="border" id="select7">
                              <%=DicList.getDicListOption(SysDefine.DIC_TZ,"-1")%>
                          </select>
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>肤色</strong>：
                              <select name="fs" size="1" class="border" id="fs">
                                  <%=DicList.getDicListOption(SysDefine.DIC_FS,"-1")%>
                              </select>               </td>
                </tr>
                
                <tr>
                  <td align="right" bgcolor="#D6E6F3" class="bk04"><strong>血型：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04"><select name="sx" id="sx">
                      <%=DicList.getDicListOption(SysDefine.DIC_XX,"-1")%>
                  </select>
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>星座：</strong>
                      <select name="xz" id="xz">
                          <%=DicList.getDicListOption(SysDefine.DIC_XZ,"-1")%>
                      </select>
                   </td>
                </tr>
                <tr>
                  <td align="right" bgcolor="#D6E6F3" class="bk04"><strong>婚姻状况：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04"><select name="hyzk" size="1" class="border" id="hyzk">
                      <%=DicList.getDicListOption(SysDefine.DIC_HYZH,"-1")%>
                  </select></td>
                </tr>
                <tr>
                  <td align="right" bgcolor="#D6E6F3" class="bk04"><strong>文化程度：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04"><select name="whcd" size="1" class="border" id="whcd">
                      <%=DicList.getDicListOption(SysDefine.DIC_WHCD,"-1")%>
                  </select>
                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <strong>职业：</strong>
                      <select name="zy" size="1" class="border" id="zy">
                          <%=DicList.getDicListOption(SysDefine.DIC_ZY,"-1")%>
                      </select></td>
                </tr>
                <tr>
                  <td align="right" bgcolor="#D6E6F3" class="bk04"><strong>年薪：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04"><select name="yx" size="1" class="border" id="yx">
                      <%=DicList.getDicListOption(SysDefine.DIC_YX,"-1")%>
                  </select>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>住房：</strong>
                      <select name="zf" size="1" class="border" id="zf">
                          <%=DicList.getDicListOption(SysDefine.DIC_ZF,"-1")%>
                      </select></td>
                </tr>
                <tr>
                  <td align="right"  bgcolor="#D6E6F3" class="bk04" style="padding-top:7px;"><strong>性格特征：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tbody>
                        <tr>
                          <td style="border-bottom:none; width:100px; padding:0; height:20px;">
                              <input name="xgtz" type="checkbox" id="xgtz00" value="1">外向开朗</td>
                          <td style="border-bottom:none; width:100px;padding:0; height:20px;">
                            <input name="xgtz" type="checkbox" id="xgtz19" value="1">积极乐观</td>
                          <td style="border-bottom:none; width:100px;padding:0; height:20px;">
                              <input name="xgtz" type="checkbox" id="xgtz02" value="1" />风趣幽默</td>
                          <td style="border-bottom:none; width:100px;padding:0; height:20px;">
                              <input name="xgtz" type="checkbox" id="xgtz03" value="1" />直爽干脆</td>
                          <td style="border-bottom:none;padding:0; height:20px;">
                              <input name="xgtz" type="checkbox" id="xgtz04" value="1" />追求刺激</td>
                        </tr>
                        <tr>
                          <td style="border-bottom:none;padding:0; height:20px;">
                              <input name="xgtz" type="checkbox" id="xgtz05" value="1">不拘小节</td>
                          <td style="border-bottom:none;padding:0; height:20px;">
                              <input name="xgtz" type="checkbox" id="xgtz06" value="1" />诚实正直</td>
                          <td style="border-bottom:none;padding:0; height:20px;">
                              <input name="xgtz" type="checkbox" id="xgtz07" value="1" />温柔体贴</td>
                          <td style="border-bottom:none;padding:0; height:20px;">
                              <input name="xgtz" type="checkbox" id="xgtz08" value="1" />成熟稳重</td>
                          <td style="border-bottom:none;padding:0; height:20px;">
                              <input name="xgtz" type="checkbox" id="xgtz09" value="1" />小心谨慎</td>
                        </tr>
                        <tr>
                          <td style="border-bottom:none;padding:0; height:20px;">
                              <input name="xgtz" type="checkbox" id="xgtz10" value="1">浪漫多情</td>
                          <td style="border-bottom:none;padding:0; height:20px;">
                              <input name="xgtz" type="checkbox" id="xgtz11" value="1" />多愁善感</td>
                          <td style="border-bottom:none;padding:0; height:20px;">
                              <input name="xgtz" type="checkbox" id="xgtz12" value="1" />循规蹈矩</td>
                          <td style="border-bottom:none;padding:0; height:20px;">
                              <input name="xgtz" type="checkbox" id="xgtz13" value="1" />少言寡语</td>
                          <td style="border-bottom:none;padding:0; height:20px;">
                              <input name="xgtz" type="checkbox" id="xgtz14" value="1" />
                              <input type="hidden" name="xgtz01"></td>
                        </tr>
                      </tbody>
                    </table>
                      <div id="xgtzinfo"></div>
                  </td>
                </tr>
                <tr>
                  <td align="right" bgcolor="#D6E6F3" class="bk04" style="padding-top:7px;border-bottom:#21648f 1px dashed"><strong>业余生活安排：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04" style="border-bottom:#21648f 1px dashed"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tbody>
                        <tr>
                          <td style="border-bottom:none;width:100px;padding:0; height:20px;">
                              <input name="yyap" type="checkbox" id="yyap00" value="1" onclick="xgtzCheck()"/>看 电 视</td>
                          <td style="border-bottom:none;width:100px;padding:0; height:20px;">
                              <input name="yyap" type="checkbox" id="yyap13" value="1"  onclick="xgtzCheck()"/>泡 酒 吧</td>
                          <td style="border-bottom:none;width:100px;padding:0; height:20px;">
                              <input name="yyap" type="checkbox" id="yyap02" value="1"  onclick="xgtzCheck()"/>看　　书</td>
                          <td style="border-bottom:none;width:100px;padding:0; height:20px;">
                              <input name="yyap" type="checkbox" id="yyap03" value="1"  onclick="xgtzCheck()"/>上　　网</td>
                          <td style="border-bottom:none;padding:0; height:20px;">
                              <input name="yyap" type="checkbox" id="yyap04" value="1"  onclick="xgtzCheck()"/>卡 拉OK</td>
                        </tr>
                        <tr>
                          <td style="border-bottom:none; padding:0; height:20px;">
                            <input name="yyap" type="checkbox" id="yyap05" value="1" onclick="xgtzCheck()">做 家 务</td>
                          <td style="border-bottom:none; padding:0; height:20px;"> 
                            <input name="yyap" type="checkbox" id="yyap06" value="1" onclick="xgtzCheck()">喝茶聊天</td>
                          <td style="border-bottom:none; padding:0; height:20px;">
                            <input name="yyap" type="checkbox" id="yyap07" value="1" onclick="xgtzCheck()">体育运动</td>
                          <td style="border-bottom:none; padding:0; height:20px;"> 
                            <input name="yyap" type="checkbox" id="yyap08" value="1" onclick="xgtzCheck()">打工赚钱</td>
                          <td style="border-bottom:none; padding:0; height:20px;">
                            <input name="yyap" type="checkbox" id="yyap09" value="1" onclick="xgtzCheck()">读书进修
                            <input type="hidden" name="yyap01"></td>
                        </tr>
                        <tr>
                          <td style="border-bottom:none; padding:0; height:20px;">
                              <input name="yyap" type="checkbox" id="yyap10" value="1"  onclick="xgtzCheck()"/>结交朋友</td>
                          <td style="border-bottom:none; padding:0; height:20px;">&nbsp;</td>
                          <td style="border-bottom:none; padding:0; height:20px;">&nbsp;</td>
                          <td style="border-bottom:none; padding:0; height:20px;">&nbsp;</td>
                          <td style="border-bottom:none; padding:0; height:20px;">&nbsp;</td>
                          <td style="border-bottom:none; padding:0; height:20px;">&nbsp;</td>
                          <td style="border-bottom:none; padding:0; height:20px;">&nbsp;</td>
                        </tr>
                      </tbody>
                    </table>
                      <div id="yyshinfo"></div>
                  </td>
                </tr>
                </tbody>
                </table>
                <table width="888" border="0" align="center" cellpadding="4" cellspacing="0" class="reg_tab02" style="margin-top:1px; padding:0; margin-bottom:0">
                <tbody>
                <tr>
                  <td align="right" width="200"  bgcolor="#D6E6F3" class="bk04" style="border-top:#21648f 1px solid"><strong>交友意向：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04"  style="border-top:#21648f 1px solid"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tbody>
                        <tr>
                          <td style="border-bottom:none;width:100px; padding:0; height:20px;"><input name="jyyx" type="checkbox" id="jyyx002" value="1"  onclick="yyshCheck()"/>
                            纯 友 谊</td>
                          <td style="border-bottom:none;width:110px; padding:0; height:20px;"><input name="jyyx" type="checkbox" id="jyyx012" value="2"  onclick="yyshCheck()"/>
                            亲 密 关 系</td>
                          <td style="border-bottom:none;width:100px; padding:0; height:20px;"><input name="jyyx" type="checkbox" id="jyyx022" value="3"  onclick="yyshCheck()"/>
                            婚　姻</td>
                          <td style="border-bottom:none;width:100px; padding:0; height:20px;"><input name="jyyx" type="checkbox" id="jyyx032" value="4"  onclick="yyshCheck()"/>
                            陪　伴</td>
                          <td style="border-bottom:none; padding:0; height:20px;"><input name="jyyx" type="checkbox" id="jyyx042" value="5"  onclick="yyshCheck()"/>
                            其　它
                              <input type="hidden" name="jyyx01" />
                          </td>
                        </tr>
                      </tbody>
                    </table>
                      <div id="jyyxinfo"></div>
                  </td>
                </tr>
                <tr>
                  <td align="right"  bgcolor="#D6E6F3" class="bk04"><strong>情爱关系：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04">您认为感情、婚姻与性爱的关系是什么？请勿填写任何联系方式
                  <br/>
                      <textarea name="qggx" cols="58" rows="4" value="" style="width:98%" onblur="qggxCheck()" onclick="jyyxCheck()"></textarea>
                      <div id="qggxinfo"></div>
                  </td>
                </tr>
                <tr>
                  <td align="right"  bgcolor="#D6E6F3" class="bk04"><strong>理想情人：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04">为保证交友效果，请仔细描绘你的理想情人，请勿填写任何联系方式<br/>
                          <textarea name="lxqr" cols="" rows="4" value="" style="width:98%" onblur="lxqrCheck()"></textarea>
                          <div id="lxqrinfo"></div></td>
                </tr>
                <tr>
                  <td align="right"  bgcolor="#D6E6F3" class="bk04" style="border-bottom:#21648f 1px dashed"><strong>交友留言：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04" style="border-bottom:#21648f 1px dashed">为保证交友效果，留言请在20字以上，请勿填写任何联系方式<br/>
                          <textarea name="jyly" cols="" rows="6" value="" style="width:98%" onblur="jylyCheck()"></textarea>
                          <div id="jylyinfo"></div></td>
                </tr>
              </tbody>
                </table>
                <table width="888" border="0" align="center" cellpadding="4" cellspacing="0" class="reg_tab02" style="margin-top:1px; padding:0; margin-bottom:0">
                <tbody>
                <tr>
                  <td align="right" width="200"  bgcolor="#FFFFFF" class="bk04" style="border-top:#21648f 1px solid"><strong>联系方式：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04" style="border-top:#21648f 1px solid">(请正确填写，以便其他会员与您联系。注册后请到个人专区进行开放设定。)</td>
                </tr>
                <tr>
                  <td align="right"  bgcolor="#D6E6F3" class="bk04"><strong><strong>手机：</strong></strong></td>
                  <td bgcolor="#FFFFFF" class="bk04"><input name="sjtel" type="text" class="input02" size="30" value="">
                    （必填，同时支持手机短信登陆）</td>
                </tr>
                <tr>
                  <td align="right"  bgcolor="#D6E6F3" class="bk04"><strong>固定电话：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04"><input name="tel" type="text" class="input02" size="30" value=""></td>
                </tr>
                <tr>
                  <td align="right"  bgcolor="#D6E6F3" class="bk04"><strong>QQ：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04"><input name="oicq" type="text" class="input02" size="30">
                  </td>
                </tr>
                <tr>
                  <td align="right"  bgcolor="#D6E6F3" class="bk04" style="border:0;"><strong>E-mail：</strong></td>
                  <td bgcolor="#FFFFFF" class="bk04" style="border:0;"><input name="email" type="text" class="input02" size="30" value="@">
                  </td>
                </tr>
                <tr>
                    <td align="right" valign="top" bgcolor="#D6E6F3" class="bk04"><strong>微信：</strong></td>
                    <td bgcolor="#FFFFFF" class="bk04"><input name="wx" type="text" class="input02" size="30" value="" />
                    </td>
                </tr>
                <tr>
                  <td colspan="2" align="center" bgcolor="#D6E6F3" style="border:0; padding:30px 0;"><input name="bsendsms" type="button" id="bsendsms"  onClick="javascript:test();" value="激活上线" class="btn"></td>
                </tr>
              </tbody>
            </table>
  </form>
  <div style="clear:both"></div>
  <div class="erweima_box" style="top:108px">
    <div class="lm_name02">
      <h2>扫描二维码</h2>
    </div>
    <div class="erweima"><img src="images/weixin_erweima.jpg" alt="" width="120" height="120"/><br/>官方微信测试中</div>
    <div class="erweima"><img src="images/wap_erweima.jpg" alt="" width="120" height="120"/><br/>手机网站测试中</div>
  </div>
</div>
<%@ include file="bottom.jsp"%>
</body>
</html>

<form name="cform" action="checkuname.jsp" method="post" target="cname">
    <input name="username" value="" type="hidden"/>
</form>
<iframe name="cname" id="cname" src="" height="0" width="0">
</iframe>

<script language="javascript">

    SetCity(document.personal.s1,document.personal.s2);

</script>

