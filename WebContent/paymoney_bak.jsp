<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.web.obj.*"%>
<%@ page import="com.web.common.*"%>
<%@ page import="com.lover.mng.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.web.obj.Userinfo" %>
<%@ page import="com.common.SysDefine" %>
<%@ page import="com.weixin.util.*" %>
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
<meta name="description" content="51交友中心网站?中国交友行业领军品牌。亲密关系、终生伴侣、情商训练，全方位支持您的情感生活。十年运营，千万会员，美满感情，从51交友开始！" />
<meta name="keywords" content="51交友中心--亲密交友 情缘交友 同城交友 知己交友 美女交友" />
<link rel="stylesheet" type="text/css" href="css/style.css?v=12" />
</head>
<%
   Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
   String hasuser = "1";
   if(loginUser == null)
    {
        hasuser = "0";
    } 
    List plist = ProductMng.getProductList();
    String bjd1 = SysCommonFunc.getStrParameter(request, "bjd1"); 
%>
<body>

<div id="header">
    <div class="h_nav"> 
    <a href="index.jsp"><div class="h_left"><img src="images/l.png" /></div></a>  会员升级</div>
</div>

<div id="inner">
<!-- 
    <div class="hyzl">
        <div class="hyzl_nav">
            <div class="h_n_in f_l">付费升级</div>
            <div class="h_n_on f_r">分享升级</div>
        </div>
    </div>
 -->    
    <div class="sjhy">
       <ul> 
          <li><div class="sjhy_nav"> 普通会员权限 </div></li>
          <li> <div class="sjhy_tl">★</div> <div class="sjhy_tr">使用会员搜索，精准查找会员交友资料；</div> </li>
          <li> <div class="sjhy_tl">★</div> <div class="sjhy_tr">展示自己交友资料，已验证会员和未验证会员分别排列；</div> </li>
          <li> <div class="sjhy_tl">★</div> <div class="sjhy_tr">拥有个人专区，使用资料开放设置、好友名单/黑名单、查看留言、收到的秋波、我看过谁等权限。</div> </li>
          <li> <div class="sjhy_tl">★</div> <div class="sjhy_tr">论坛发帖回帖、获得分享交流奖励的权限。</div> </li>
        </ul>
        
        <ul> 
          <li><div class="sjhy_nav"> 白金VIP会员权限 </div></li>
          <li> <div class="sjhy_tl">★</div> <div class="sjhy_tr">普通会员的全部权限；</div> </li>
          <li> <div class="sjhy_tl">★</div> <div class="sjhy_tr">首页重点会员推荐（附照）；</div> </li>
          <li> <div class="sjhy_tl">★</div> <div class="sjhy_tr">查看全部会员详细联系办法；</div> </li>
          <li> <div class="sjhy_tl">★</div> <div class="sjhy_tr">给全部会员发送留言；</div> </li>
          <li> <div class="sjhy_tl">★</div> <div class="sjhy_tr">可以在资料留言区公开自己的联系办法。</div> </li>
          <li> <div class="sjhy_tl">★</div> <div class="sjhy_tr">白金豆:48小时内查看会员详细联系办法，查看未验证会员1颗/位，查看已验证会员2颗/位</div> </li>
        </ul>
        
          <ul> 
          <li><div class="sjhy_nav"> 收费标准 </div></li>
           <li> 
            <%
               /**
                       String amount = "";
                       for (int i = 0; i < plist.size(); i++) {
                           Product temp = (Product) plist.get(i);
                           if (temp.getId() != 201) {
                           //temp.getTransamt()
                           }
                       }
                       **/
                   %>
                   
            <em id="select33"><div class="sihy"><div class="select3" data-val="798" data-id="261"><img src="images/dotin.png"/></div> 三年 <span class="sihy-t"> <span class="sihy-txt">原价:1580元</span></span> / 优惠价: <b>798元 </b> </div></em>
            <em id="select22"><div class="sihy"><div class="select2" data-val="598" data-id="241"><img src="images/dotin.png"/></div>  二年 <span class="sihy-t"> <span class="sihy-txt">原价:1280元</span> </span> / 优惠价: <b>598元 </b> </div></em>
            <em id="select11"><div class="sihy"><div class="select1" data-val="398" data-id="221"><img src="images/dotin.png"/></div>  一年 <span class="sihy-t"> <span class="sihy-txt">原价:880元</span> </span> / 优惠价: <b>398元 </b> </div></em>
            
           <div class="clearfix" style="padding-top:5px;"></div> 
          </li>
          <li><div class="sjhy_nav1"> 
          
          <div data-id="3" id="zhifubao"   style="text-align:center" class="sj_n_on">支付宝支付</div> 
          <div data-id="1" id="" style="display:none;" class="sj_n_in">网银支付</div> 
          <div data-id="2" id="weixin" style="display:none;" class="sj_n_on">微信支付</div> 
          </div></li>
          <div class="clearfix" style="padding-top:5px;"></div>
        </ul>
    </div>
    <form action="pay_do.jsp" id="payform" name="payform" method="post" >  
    <input type="hidden" id="transamt" name="transamt" size="5" readonly  />
    <input type="hidden" id="product" name="product" value="">
    <input type="hidden" id="paymodetype" name="paymodetype" value="">
    <input type="hidden" id="hasuser" name="hasuser" value="<%=hasuser%>">
</form>
    
    
    
</div>
<%@ include file="bottom2.jsp"%>
<%@ include file="bottom.jsp"%>

<!-- build:js scripts/lib/base.min.js -->
<script type="text/javascript" src="js/lib/zepto.min.js"></script>
<script type="text/javascript" src="js/lib/exp.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script> 
<!-- endbuild --> 
<script type="text/javascript" src="js/btm.js"></script>
<script type="text/javascript" src="./js/sjhy.js?v=412"></script> 
 
</body>
</html>
