<%@ page contentType="text/html; charset=GBK" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.common.SysDefine" %>
<%@ page import="com.web.obj.Userinfo" %>
<%@ page import="com.web.common.SysCommonFunc" %>
<%@ page import="com.lover.mng.UserVisitMng" %>
<%@ page import="com.lover.mng.ProductMng" %>
<%@ page import="com.lover.mng.CartMng" %>
<%@ page import="com.web.obj.Product" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.web.common.DateTools" %>
<%@ page import="com.web.obj.Cart" %>
<%@ page import="com.weixin.util.*" %>  
<%
    String paymodes  = SysCommonFunc.getStrParameter(request,"paymodetype");
    Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
    if(loginUser == null)
    {
        out.println("<script language='javascript'>alert('您是游客，请先注册或登陆！');</script>");
        out.println("<script langauge=javascript>location.href = 'reg.jsp?url=paymoney.jsp';</script>");
        
        return;
    }
    if (loginUser!=null){
        String vurl="";
        vurl=request.getRequestURL().toString();
        if (request.getQueryString()!=null){
            vurl+="?"+request.getQueryString();
        }
        UserVisitMng.insertUserVisit(loginUser.getHyid(), vurl);
    }
    String MerId = CartMng.MerId;
    String zsname  = loginUser.getUsername();  // SysCommonFunc.getStrParameter(request,"zsname");
    String tel     = loginUser.getSjtel();   // SysCommonFunc.getStrParameter(request,"tel");
    String simplename =  loginUser.getLcname(); //SysCommonFunc.getStrParameter(request, "simplename");
    String OrdId = CartMng.getOrderId();
    String product = SysCommonFunc.getStrParameter(request, "product");

    Product p = ProductMng.getProduct(product);
    //add by gaojianghong 20120816 for 判断VIP会员升级白金VIP会员时，VIP会员时间要小于拟升级白金VIP会员时间。白金VIP没有到期不能升级VIP会员 start
    Date sdate = new Date(System.currentTimeMillis());
   if (loginUser.getFlag()==30 && loginUser.getZzsj().after(sdate)){
        if (p!=null && p.getFlag()==25){
            out.println("<script language='javascript'>{window.alert('您的白金VIP会员暂未到期限，不能降级为VIP会员，请选择白金VIP会员项目');}</script>");
            return;
        }
    }
    String area  = "全部";
    //支付宝以元为单位
    double transamt=0.01;
    
    //微信以分为单位
    int transamtWeixin = (int)transamt*100;
    String noncestr=WeixinTools.createNoncestr(10);
    DecimalFormat f=new DecimalFormat("0.00");
    String TransAmt =CartMng.getTransAmt(f.format(transamt));
    Date cdate = new Date(System.currentTimeMillis());
    String weixinTimeStart = WeixinTools.getDateTime(cdate);
    String weixinTimeExpire = WeixinTools.getExpireTime(cdate,3);
    String TransDate = DateTools.DateToString(cdate, DateTools.FORMART_yyyyMMdd);
    String ChkValue  = CartMng.getCheckValue(MerId,OrdId,TransAmt,CartMng.CuryId,TransDate,CartMng.TransType);
    Cart cart = new Cart();
    cart.setDatetype(p.getDatetype());
    cart.setFlag(p.getFlag());
    cart.setLcname(loginUser.getLcname());
    cart.setName(p.getName());
    cart.setOrdid(OrdId);
    cart.setServiceyear(p.getServiceyear());
    cart.setSimplename(simplename);
    cart.setSjtime(cdate);
    cart.setSqdj(p.getSqdj());
    cart.setTel(tel);
    cart.setTrans(new Integer(0));
    cart.setTransamt(transamt);
    cart.setUpgrade(new Integer(0));
    cart.setUsername(loginUser.getUsername());
    cart.setZsname(zsname);
    cart.setArea(area);
    String openid = "";
    //网银支付
     if("1".equals(paymodes)){
         cart.setPayMode(new Integer(1));
     }
     //微信支付
    if("2".equals(paymodes)){
        cart.setPayMode(new Integer(6));//标记微信站微信支付
        
        //微信支付先链接取code，在链接去operid，才能跳转支付
        String code =  SysCommonFunc.getStrParameter(request, "code");
        if(code !=null && !"".equals(code)){
        	openid = WeixinTools.createOauthUrlForOpenid(code);
		
        }else{
        	 String encodeUrl = "http://www.51lover.org/mobile/pay_do.jsp?paymodetype=2&product="+product;
	        String linkurl = WeixinTools.createOauthUrlForCode(encodeUrl);
			// response.sendRedirect(linkurl);
			//String code = WeixinHttpURLConnection.httpURLConectionGET(linkurl);
		    // System.out.println("code =: " + code);
		    // encodeUrl = encodeUrl+"&code="+code;
			
		   	  response.sendRedirect(linkurl);
	        return;
        }
    }
    //支付宝支付
    if("3".equals(paymodes)){
        cart.setPayMode(new Integer(5)); //标记手机支付宝 
    }
    if("4".equals(paymodes)){
        cart.setPayMode(new Integer(4));
    }
    System.out.println("paymodes =: " + cart.getPayMode());
    CartMng.addCart(cart);
%>

<form id="netBank" action="https://payment.chinapay.com/pay/TransGet " name="form1" method="post">
    <input type="hidden" name="MerId" value="<%=MerId%>">
    <input name="OrdId" type="hidden" value="<%=OrdId%>">
    <input type="hidden" name="TransAmt" value="<%=TransAmt%>">
    <input type="hidden" name="CuryId" value="<%=CartMng.CuryId%>">
    <input type="hidden" name="TransDate" value="<%=TransDate%>">
    <input type="hidden" name="TransType" value="<%=CartMng.TransType%>">
    <input type="hidden" name="Version" value="<%=CartMng.Version%>">
    <input type="hidden" name="BgRetUrl" value="<%=CartMng.BgRetUrl%>">
    <input type="hidden" name="PageRetUrl" value="<%=CartMng.PageRetUrl%>">
    <input type="hidden" name="ChkValue" value="<%=ChkValue%>">
</form>
<form id="weixin" action="weixinpay/wxpayapi.jsp" name="form1" method="post">
	<input type="hidden" name="appid" value="<%=WeixinMng.appid%>"/>
	<input type="hidden" name="mch_id" value="<%=WeixinMng.mchId%>"/>
    <input type="hidden" name="nonce_str" value="<%=noncestr%>"/>
    <input type="hidden" name="body" value="<%=p.getName()%>"/>
 	<input type="hidden" name="out_trade_no" value="<%=OrdId%>"/>
 	<input type="hidden" name="total_fee"  value="<%=transamtWeixin%>"/>
 	<input type="hidden" name="spbill_create_ip" value="<%=WeixinMng.create_ip%>"/>
 	<input type="hidden" name="time_start" value="<%=weixinTimeStart%>"/>
    <input type="hidden" name="time_expire" value="<%=weixinTimeExpire%>"/>
 	<input type="hidden" name="notify_url" value="http://www.51lover.org/mobile/weixinpay/return_url.jsp"/>
 	<input type="hidden" name="trade_type" value="<%=WeixinMng.tradeType%>"/> 
 	<input type="hidden" name="openId" value="<%=openid%>"/> 
</form>
<form id="alipay" name=alipayment action=alipay/alipayapi.jsp method=post target="_self">
    <input type="hidden" name="WIDout_trade_no" value="<%=OrdId%>"/>
    <input type="hidden" name="trade_no" value="<%=OrdId%>"/>
    <input type="hidden" name="WIDsubject" value="<%=p.getName()%>"/>
    <input type="hidden" name="WIDtotal_fee"  value="<%=transamt%>"/>
    <input type="hidden" name="WIDbody" value="ToBeMember"/>
    <input type="hidden" name="WIDshow_url"  value="http://www.51lover.org/mobile/alipay/return_url.jsp"/>
</form>

<form id="huikuan" action="payLast.jsp" name="form1" method="post">
</form>

  <script type="text/javascript">
<%
    if("1".equals(paymodes)){
%>
   document.getElementById("netBank").submit();
<%
    }
%>

<%
   if("2".equals(paymodes)){
%>
document.getElementById("weixin").submit();
<%
    }
%><%
   if("3".equals(paymodes)){
%>
document.getElementById("alipay").submit();
<%
    }
%>


<%
   if("4".equals(paymodes)){
%>
document.getElementById("huikuan").submit();
<%
    }
%>


  </script>