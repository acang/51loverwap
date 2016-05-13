<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.web.obj.*"%>
<%@ page import="com.web.obj.extend.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.web.common.*"%>
<%@ page import="com.web.servlet.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.lover.mng.*"%>
<%@ page import="java.util.*"%>
<%@ page import="hibernate.db.*"%>
<%
Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
if (loginUser!=null){
	String vurl="";
   	vurl=request.getRequestURL().toString();
    if (request.getQueryString()!=null){
   	   vurl+="?"+request.getQueryString();
    }
    UserVisitMng.insertUserVisit(loginUser.getHyid(),vurl);
}
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
</head>
<body class="cm_hyzf">
<%@ include file="head.jsp"%>
<div class="wrapper1250 cm_block01">
  
  <div class="block02">
      <div class="lm_name02">
      <h2>银行及支付宝支付</h2>
    </div>
       <div class="TabBox" style="position:relative">
       		<div style="width:180px; height:180px; position:absolute; top:200px; right:50px;"><img src="skin/zfbsk.png" width="180" height="180" /></div>
           <table width="880px" border="0" cellspacing="0" cellpadding="0" class="Tabc" style=" margin:auto;margin-top:8px;">
          <tr>
            <td style="width:300px; text-align:right;vertical-align:top"><img src="skin/icbc.gif" width="226" height="54" /></td>
            <td style="vertical-align:top">
            	<strong>收款银行：</strong>中国农业银行南京大厂支行营业部<br/>
                <strong>卡　号：</strong>6228480394121426016<br/>
                <strong>姓　名：</strong>马世强 
            </td>
          </tr>
          <tr>
            <td style="width:300px; text-align:right;;vertical-align:top"><img src="skin/icbc.gif" width="226" height="54" /></td>
            <td style=" height:80px;vertical-align:top">
            	<strong>收款单位：</strong>南京融亚网络技术有限公司<br/>
                <strong>收款银行：</strong>中国农业银行南京大厂支行<br/>
                <strong>账　号：</strong>10120001040209689 
            </td>
          </tr>
          <tr>
            <td style="width:300px; text-align:right; vertical-align:top"><img src="skin/zfb.jpg" width="226" height="54" /></td>
            <td style="height:110px;vertical-align:top">
            	<strong>支付宝收款账号：</strong>service@51lover.org<br/>
				或扫描右侧支付宝收款二维码支付
            </td>
          </tr>
        </table>
        <div style="height:30px; line-height:30px; width:868px; margin:auto; padding-left:10px; font-size:14px; font-weight:bold; background-color:#dbdbdb; border:#CCC 1px solid; border-top:0; border-bottom:0;">汇款须知：</div>
        <table width="880px" border="0" cellspacing="0" cellpadding="0" class="Tabf" style="margin:auto; margin-bottom:8px;">
          <tr>
            <td style="padding-left:70px;">
            	一、汇款成功后，请在<a href="/kfzx.htm" target="_blank" style="color: blue">联系我们</a>留言给我们，并在留言框的内容中注明以下内容： <br/>
                　　1、升级用户名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>例如：</strong>&nbsp;<span style="color:#156cb1	">升级用户名：zk00</span><br/>
                　　2、汇款时间&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#156cb1">汇款时间：2012年8月8日15时36分</span><br/>
                　　3、汇款金额&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#156cb1">汇款金额：1280元、980元或580元</span><br/>
                　　4、付费项目&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#156cb1">付费项目：白金VIP会员(三年期、二年期或一年期)</span><br/>
                　　5、付费银行网点名称&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#156cb1">银行网点：中国工商银行上海浦东支行</span><br/>
                　　6、收款账户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#156cb1">收款账户：马世强账户/公司账户/支付宝账户</span><br/>
                　　7、支付方式&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:#156cb1">支付方式：ATM机转账/柜台汇款/网银对卡支付/支付宝</span><br/>


               二、 马世强账户支持ATM机、银行柜台或网银7*24小时支付。<br/>
                　　公司账户不支持ATM机支付，须在银行柜台工作日支付，或者网银7*24小时支付。<br/>
                　　支付宝账户支持网银或手机7*24小时支付。 <br/>
                　　汇款时请在交易留言注明您要升级的用户名。<br/>
               三、我们将在收到留言后1个工作日内予以审核开通。
            </td>
          </tr>
        </table>
    </div>
  </div>
  <div class="block02t">
        <div class="lm_name">
          <h2>扫描二维码</h2>
        </div>
        <div class="erweima"><img src="images/weixin_erweima.jpg" alt="" width="120" height="120"/><br/>官方微信测试中</div>
        <div class="erweima"><img src="images/wap_erweima.jpg" alt="" width="120" height="120"/><br/>手机网站测试中</div>
      <div style="clear:both"></div>
    </div>
  
  <div style="clear:both"></div>
</div>
<%@ include file="bottom.jsp"%>
<!-- Google Code for 再营销代码 -->
<!-- Remarketing tags may not be associated with personally identifiable information or placed on pages related to sensitive categories. For instructions on adding this tag and more information on the above requirements, read the setup guide: google.com/ads/remarketingsetup -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 1057143543;
var google_conversion_label = "u6RhCK2u6wMQ9_WK-AM";
var google_custom_params = window.google_tag_params;
var google_remarketing_only = true;
/* ]]> */
</script>
<script type="text/javascript" src="//www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="//googleads.g.doubleclick.net/pagead/viewthroughconversion/1057143543/?value=0&amp;label=u6RhCK2u6wMQ9_WK-AM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>
</body>
</html>
