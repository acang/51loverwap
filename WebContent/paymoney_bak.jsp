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
<title>51��������</title>
<meta name="description" content="51����������վ?�й�������ҵ���Ʒ�ơ����ܹ�ϵ���������¡�����ѵ����ȫ��λ֧������������ʮ����Ӫ��ǧ���Ա���������飬��51���ѿ�ʼ��" />
<meta name="keywords" content="51��������--���ܽ��� ��Ե���� ͬ�ǽ��� ֪������ ��Ů����" />
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
    <a href="index.jsp"><div class="h_left"><img src="images/l.png" /></div></a>  ��Ա����</div>
</div>

<div id="inner">
<!-- 
    <div class="hyzl">
        <div class="hyzl_nav">
            <div class="h_n_in f_l">��������</div>
            <div class="h_n_on f_r">��������</div>
        </div>
    </div>
 -->    
    <div class="sjhy">
       <ul> 
          <li><div class="sjhy_nav"> ��ͨ��ԱȨ�� </div></li>
          <li> <div class="sjhy_tl">��</div> <div class="sjhy_tr">ʹ�û�Ա��������׼���һ�Ա�������ϣ�</div> </li>
          <li> <div class="sjhy_tl">��</div> <div class="sjhy_tr">չʾ�Լ��������ϣ�����֤��Ա��δ��֤��Ա�ֱ����У�</div> </li>
          <li> <div class="sjhy_tl">��</div> <div class="sjhy_tr">ӵ�и���ר����ʹ�����Ͽ������á���������/���������鿴���ԡ��յ����ﲨ���ҿ���˭��Ȩ�ޡ�</div> </li>
          <li> <div class="sjhy_tl">��</div> <div class="sjhy_tr">��̳������������÷�������������Ȩ�ޡ�</div> </li>
        </ul>
        
        <ul> 
          <li><div class="sjhy_nav"> �׽�VIP��ԱȨ�� </div></li>
          <li> <div class="sjhy_tl">��</div> <div class="sjhy_tr">��ͨ��Ա��ȫ��Ȩ�ޣ�</div> </li>
          <li> <div class="sjhy_tl">��</div> <div class="sjhy_tr">��ҳ�ص��Ա�Ƽ������գ���</div> </li>
          <li> <div class="sjhy_tl">��</div> <div class="sjhy_tr">�鿴ȫ����Ա��ϸ��ϵ�취��</div> </li>
          <li> <div class="sjhy_tl">��</div> <div class="sjhy_tr">��ȫ����Ա�������ԣ�</div> </li>
          <li> <div class="sjhy_tl">��</div> <div class="sjhy_tr">���������������������Լ�����ϵ�취��</div> </li>
          <li> <div class="sjhy_tl">��</div> <div class="sjhy_tr">�׽�:48Сʱ�ڲ鿴��Ա��ϸ��ϵ�취���鿴δ��֤��Ա1��/λ���鿴����֤��Ա2��/λ</div> </li>
        </ul>
        
          <ul> 
          <li><div class="sjhy_nav"> �շѱ�׼ </div></li>
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
                   
            <em id="select33"><div class="sihy"><div class="select3" data-val="798" data-id="261"><img src="images/dotin.png"/></div> ���� <span class="sihy-t"> <span class="sihy-txt">ԭ��:1580Ԫ</span></span> / �Żݼ�: <b>798Ԫ </b> </div></em>
            <em id="select22"><div class="sihy"><div class="select2" data-val="598" data-id="241"><img src="images/dotin.png"/></div>  ���� <span class="sihy-t"> <span class="sihy-txt">ԭ��:1280Ԫ</span> </span> / �Żݼ�: <b>598Ԫ </b> </div></em>
            <em id="select11"><div class="sihy"><div class="select1" data-val="398" data-id="221"><img src="images/dotin.png"/></div>  һ�� <span class="sihy-t"> <span class="sihy-txt">ԭ��:880Ԫ</span> </span> / �Żݼ�: <b>398Ԫ </b> </div></em>
            
           <div class="clearfix" style="padding-top:5px;"></div> 
          </li>
          <li><div class="sjhy_nav1"> 
          
          <div data-id="3" id="zhifubao"   style="text-align:center" class="sj_n_on">֧����֧��</div> 
          <div data-id="1" id="" style="display:none;" class="sj_n_in">����֧��</div> 
          <div data-id="2" id="weixin" style="display:none;" class="sj_n_on">΢��֧��</div> 
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