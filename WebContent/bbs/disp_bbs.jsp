<%@ page contentType="text/html; charset=gb2312" language="java"  errorPage="" %>
<%@ page import="com.web.bean.QueryRecord" %>
<%@ page import="com.web.bean.QueryResult" %>
<%@ page import="com.web.common.SysCommonFunc" %>
<%@ page import="hibernate.db.HbmOperator" %>
<%@ page import="com.lover.mng.BBSMng" %>
<%@ page import="com.lover.mng.HYRegMng" %>
<%@ page import="com.lover.mng.UserVisitMng" %>
<%@ page import="com.web.obj.*" %>
<%@ page import="com.lover.mng.BCBMng" %>
<%@ page import="com.common.SysDefine" %>
<%@ page import="com.web.common.DateTools" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.beanutils.DynaBean" %>
<%@ page import="java.io.File" %>
<%@ page import="com.weixin.util.WeixinCreateSign" %>
<%@ page import="com.web.obj.Sharearticle" %>
<%@ page import="com.lover.mng.SharearticleMng" %>
<%@ page import="com.web.obj.Readarticle" %>
<%@ page import="com.lover.mng.ReadarticleMng" %>
<%@ page import="com.lover.LoverTools" %>

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
<meta name="desc" content="51交友中心网站-中国交友行业领军品牌。亲密关系、终生伴侣、情商训练，全方位支持您的情感生活。十年运营，千万会员，美满感情，

从51交友开始！" 

/>
<meta name="description" content="51交友中心网站-中国交友行业领军品牌。亲密关系、终生伴侣、情商训练，全方位支持您的情感生活。十年运营，千万会员，美满

感情，从51交友开

始！" />
<meta name="keywords" content="51交友中心--亲密交友 情缘交友 同城交友 知己交友 美女交友" />
<link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<%
    String bbsid = SysCommonFunc.getStrParameter(request,"bbsid");
    if(SysCommonFunc.getNumberFromString(bbsid,0)==0)
    {
        System.out.println("sql注入"+bbsid);
        return;
    }
    Long loginId = 0L;
    Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
     String sharehyid = SysCommonFunc.getStrParameter(request,"sharehyid");
	 String tjtype = SysCommonFunc.getStrParameter(request,"tjtype");
	 String sharetype = SysCommonFunc.getStrParameter(request,"sharetype");
	 request.getSession().setAttribute("tjtype", tjtype);
	 String vurl="http://www.51lover.org/mobile/bbs/disp_bbs-bbsid-"+bbsid;

 if("1".equals(sharetype) &&"11".equals(tjtype) &&sharehyid!=null &&sharehyid.trim().length()>0
			 && bbsid!=null &&bbsid!=null && bbsid.trim().length()>0){
		 //update readnumber and gold
//out.println("<script language='javascript'>alert('update numbers');</script>");
		 try
		    {
			 //readarticle表中不存在才会update,拒绝多次点击
			///得到用户列表
			String remoteip = request.getHeader("x-forwarded-for"); 
		    
		    if(remoteip == null || remoteip.length() == 0 || "unknown".equalsIgnoreCase(remoteip)) {   
		    	 remoteip = request.getHeader("Proxy-Client-IP");   
		    }   
		    if(remoteip == null || remoteip.length() == 0 || "unknown".equalsIgnoreCase(remoteip)) {   
		    	 remoteip = request.getHeader("WL-Proxy-Client-IP");   
		    }   
		    if(remoteip == null || remoteip.length() == 0 || "unknown".equalsIgnoreCase(remoteip)) {   
		    	 remoteip = request.getRemoteAddr();   
		    } 
   			if(remoteip!=null && remoteip.contains(",")){
   				 String[]ips = remoteip.split(",");
   				 remoteip = ips[0];
   			}
			Date ldate = new Date(new Date().getTime()-24*60*60*1000);
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			 String dateString = "to_date('"+formatter.format(ldate) + "','YYYY-MM-DD HH24:MI:SS')";
		if(remoteip!=null){
			   String sql = "select count(*) from readarticle where ip='" +remoteip
							+"' and articleid ="+bbsid 
							+" and hyid = "+sharehyid
							+" and readtime > " + dateString;
			   int count = QueryRecord.queryCount(sql);
			   if(count<=0){
				   //存在就不要进行了
				   Long lbid = Long.parseLong(bbsid);
				   Long lhyid = Long.parseLong(sharehyid);
				   Readarticle ra = new Readarticle(lbid,lhyid,"lname","name",new Date(),"title",remoteip);
				   ReadarticleMng.addReadarticle(ra);
		     	   HbmOperator.executeSql("update sharearticle set gold = gold+1, readnumber=readnumber+1 where hyid = "+sharehyid+" and articleid ="+bbsid);
try
		    	    {
		     		 HbmOperator.executeSql("update bbsuser set moneynew = moneynew+1 where hyid = " + sharehyid);
		    	    }catch(Exception e){}
			   }else{
					
				}
			}else{
				
			}
		    }catch(Exception e){}
		 
	 }
    if (loginUser!=null){
        loginId = loginUser.getHyid();
        if(sharehyid!=null && !sharehyid.equals(loginId)){
        	System.out.println("非当前用户"+sharehyid);
        	request.getSession().setAttribute("sharehyid", sharehyid);
        }
       
        vurl=request.getRequestURL().toString();
        if (request.getQueryString()!=null){
            vurl+="?"+request.getQueryString();
        }
        UserVisitMng.insertUserVisit(loginUser.getHyid(), vurl);
    }else{
    	System.out.println("无用户"+sharehyid); 
    	if(sharehyid!=null && !"".equals(sharehyid)&& !"0".equals(sharehyid)){
        	System.out.println("无用户"+sharehyid);
        	request.getSession().setAttribute("sharehyid", sharehyid);
        	/*out.println("<script language='javascript'>alert('请点击下方交友官网注册新会员！');setTimeout(window.close(),1000);</script>");*/
        }
    }
    
    Bb topicBb = BBSMng.getBBSById(bbsid);
    Bcb bcb = null;
    String articleContent = "";
    String lanmu_name = "";
    Long sortid = 0L;
    if(topicBb != null && topicBb.getContent() != null)
    {
        bcb = BCBMng.getBcbFromid(topicBb.getContent().toString());
        articleContent = SysCommonFunc.getStringFromBlob(bcb.getContent());
        //手机版取电脑端图片信息
         articleContent = articleContent.replace("/UserFiles/Image","http://www.51lover.org/UserFiles/Image");
         sortid = topicBb.getSortid();

        if(13 == sortid){
            lanmu_name = "交友动态";
        }
        if(3 == sortid){
            lanmu_name = "交友观念";
        }
        if(12 == sortid){
            lanmu_name = "交友攻略";
        }
        if(9 == sortid){
            lanmu_name = "交友故事";
        }
    }
    Bbsuser buser = BBSMng.getBbsuserByHyid(topicBb.getUserId().toString());
    Userother uother = null;
    if(buser != null)
        uother = HYRegMng.getUserOtherByHyid(buser.getHyid().toString());
    if(buser == null)
        buser = new Bbsuser();
    if(uother == null)
        uother = new Userother();

    Bbssort bsort = BBSMng.getBbssort(topicBb.getSortid().toString());

    try
    {
        HbmOperator.executeSql("update bbs set hits = hits+1 where id = " + topicBb.getId());
    }catch(Exception e){}


    /*  delete by linyu at 20150914 for 执行效率太低。数据库CPU占满100% 
    String sql = "select b.id,b.author,b.user_id,b.s_time,b.re_id,b.commend,b.zan,bu.grade,bu.moneys,bc.content,u.Userphoto1," +
            "ui.regtime,bu.moneynew  from bbs b  left join bbsuser bu on (b.user_id = bu.hyid) left join bcb bc on (b.content = bc.id) " +
            "left join Userother u on b.user_id=u.hyid " +
            "left join Userinfo ui on b.user_id=ui.hyid "+
            " where b.ischeck='0' and (b.re_id ="+ bbsid + "  or b.commend ="+ bbsid +") order by b.id desc";
       */      
       
     String sql = "select * from ( select b.id,b.author,b.user_id,b.s_time,b.re_id,b.commend,b.zan,bu.grade,bu.moneys,bc.content,u.Userphoto1," +
            "ui.regtime,bu.moneynew  from bbs b  left join bbsuser bu on (b.user_id = bu.hyid) left join bcb bc on (b.content = bc.id) " +
            "left join Userother u on b.user_id=u.hyid " +
            "left join Userinfo ui on b.user_id=ui.hyid "+
            " where b.ischeck='0' and  b.re_id ="+ bbsid   ;        
        sql +=  "union all  select b.id,b.author,b.user_id,b.s_time,b.re_id,b.commend,b.zan,bu.grade,bu.moneys,bc.content,u.Userphoto1," +
            "ui.regtime,bu.moneynew  from bbs b  left join bbsuser bu on (b.user_id = bu.hyid) left join bcb bc on (b.content = bc.id) " +
            "left join Userother u on b.user_id=u.hyid " +
            "left join Userinfo ui on b.user_id=ui.hyid "+
            " where b.ischeck='0' and  b.commend ="+ bbsid +" and  b.re_id !="+ bbsid +" ) order by  id desc ";   
            
            
    String cpages = SysCommonFunc.getStrParameter(request,"cpages");
    int cpage = SysCommonFunc.getNumberFromString(cpages, 1);
    int pagesize = 10;
    QueryResult qr = QueryRecord.queryByDynaResultSet(sql, pagesize, cpage);
    int totalPage = qr.pageCount;
    int totalCount = qr.rowCount;
    cpage = qr.pageNum;
    int prepage = cpage-1;
    int nextpage = cpage+1;

String nonceStr = WeixinCreateSign.create_nonce_str();
    String timestamp = WeixinCreateSign.create_timestamp();
    String url =  request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
    url=url.replace(".jsp","").replace('?','-').replace('=','-').replace('&','-');
    url+=".htm";

    String signature=WeixinCreateSign.sign( nonceStr, timestamp,url);

String imgUrl="http://www.51lover.org/mobile/images/img2.png";
//先找SPAN 再找P
 String weixinShowContent="";
 if(articleContent!=null){

/*
  if(articleContent.contains("span") || articleContent.contains("SPAN")){
   int spanPoint = articleContent.indexOf("span");
   if(spanPoint>0){
    weixinShowContent=articleContent.substring(spanPoint+4);
    int spanEndPoint =weixinShowContent.indexOf(">");
    if(spanEndPoint>0){
     weixinShowContent=weixinShowContent.substring(spanEndPoint+1);
     weixinShowContent=weixinShowContent.substring(0,weixinShowContent.indexOf("<"));
     if(weixinShowContent!=null ){
      weixinShowContent = weixinShowContent.trim().replaceAll("&nbsp;", "");
      weixinShowContent = weixinShowContent.replaceAll(" ", "");
      weixinShowContent = weixinShowContent.replaceAll("　", "");
      if(weixinShowContent!=null &&weixinShowContent.length()>40 ){
       weixinShowContent =weixinShowContent.substring(0,40)+"...";
      }
     }
    }
   }
  }
  if(weixinShowContent==null || weixinShowContent.trim().length()==0){
    if(articleContent.contains("<p")){
     int pPointb = articleContent.indexOf("<p");
     int pPointe = articleContent.indexOf("</p>");
     if(pPointe>0&& pPointe-pPointb<10){
      pPointb = articleContent.indexOf("<p", pPointe);
     }
    if(pPointb>=0){
     weixinShowContent=articleContent.substring(pPointb+2);
     int pEndPoint =weixinShowContent.indexOf(">");
     if(pEndPoint>0){
      weixinShowContent=weixinShowContent.substring(pEndPoint+1);
      weixinShowContent=weixinShowContent.substring(0,weixinShowContent.indexOf("<"));
      if(weixinShowContent!=null ){
       weixinShowContent = weixinShowContent.trim().replaceAll("&nbsp;", "");
       weixinShowContent = weixinShowContent.replaceAll(" ", "");
       weixinShowContent = weixinShowContent.replaceAll("　", "");
       if(weixinShowContent!=null &&weixinShowContent.length()>40 ){
        weixinShowContent =weixinShowContent.substring(0,40)+"...";
       }
      }
     }
    }
   }
  }
  if(weixinShowContent==null || weixinShowContent.trim().length()==0){
   //直接截取前40个字吧，不想多说了
   if(articleContent!=null){
    weixinShowContent = articleContent;
    weixinShowContent = weixinShowContent.trim().replaceAll("&nbsp;", "");
    weixinShowContent = weixinShowContent.replaceAll(" ", "");
    weixinShowContent = weixinShowContent.replaceAll("　", "");
    if(weixinShowContent!=null &&weixinShowContent.length()>40 ){
     weixinShowContent =weixinShowContent.substring(0,40)+"...";
    }
   }
   
  }
*/

weixinShowContent= LoverTools.delHTMLTag(articleContent);
if(weixinShowContent!=null){
		 weixinShowContent=weixinShowContent.replaceAll("&nbsp;", "").trim();
		weixinShowContent=weixinShowContent.replaceAll("\"", "").trim();
		 weixinShowContent=weixinShowContent.replaceAll(" ", "").trim();
		 weixinShowContent=weixinShowContent.replaceAll("\\r", "").trim();
		 weixinShowContent=weixinShowContent.replaceAll("\\n", "").trim();
	 }

	 if(weixinShowContent==null || weixinShowContent.trim().length()<1){
		 weixinShowContent=topicBb.getTitle();
	 }else if(weixinShowContent.length()>40){
		 weixinShowContent = weixinShowContent.substring(0, 40);
	 }
  //找图片找第一个<img
  int imgb = articleContent.indexOf("<img");
  if(imgb>=0){
   String imgStr = articleContent.substring(imgb+4);
   if(imgStr!=null && imgStr.length()>0){
    int imge = imgStr.indexOf("/>");
    int imgbb = imgStr.indexOf("http://www.51lover.org/UserFiles/Image");
    if(imge>10&& imgbb>=0){
     imgStr = imgStr.substring(imgbb,imge);
     if(imgStr!=null && imgStr.trim().length()>0){
      imgUrl = imgStr.trim().replaceAll("\"", "");
     }
    }
   }
  }
 }




%>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>

var timestamp = "<%=timestamp%>";//时间戳
var nonceStr = "<%=nonceStr%>";//随机串
var signature = "<%=signature%>";//签名


var imgUrl = "<%=imgUrl%>";   
var lineLink = "<%=vurl+"&sharetype=1"%>";  
var descContent = "<%=weixinShowContent%>";  
var shareTitle = "<%=topicBb.getTitle()%>";  
var appid = ''; 


wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: 'wxfb0c41fd39d33468', // 必填，公众号的唯一标识
    timestamp: timestamp, // 必填，生成签名的时间戳
    nonceStr: nonceStr, // 必填，生成签名的随机串
    signature: signature,// 必填，签名，见附录1
    jsApiList: ['onMenuShareTimeline',
                'onMenuShareAppMessage',
                "onMenuShareQQ"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});

wx.ready(function(){
  var shareData = {
    title: shareTitle, // 分享标题
    desc: descContent, // 分享描述
    link: lineLink,
    imgUrl: imgUrl
  };
var ShareAppData = {
      title: shareTitle, // 分享标题
      desc: descContent, // 分享描述
      link: lineLink,
      imgUrl: imgUrl,
      success:function(){
		//alert('<%=vurl+"&sharetype=1"%>');
    	   window.location.href="sharesuccess.jsp?articleid=<%=topicBb.getId()%>&topictitle=<%=topicBb.getTitle()%>";
      }
    };
 var ShareTimeLineData = {
	      title: shareTitle, // 分享标题
	      link: lineLink,
	      imgUrl: imgUrl,
	      success:function(){
			//alert('<%=vurl+"&sharetype=1"%>');
	    	   window.location.href="sharesuccess.jsp?articleid=<%=topicBb.getId()%>&topictitle=<%=topicBb.getTitle()%>";
	      },
          cancel: function () { 
          }
	    };
  wx.onMenuShareAppMessage(ShareAppData);
  wx.onMenuShareTimeline(ShareTimeLineData); 
wx.onMenuShareQQ(ShareAppData);
});
</script>

<body>
 

<div></div>
<div id="header">
    <div class="h_nav"> 
    <a href="bbsindex.jsp?sortid=<%=sortid%>"><div class="h_left"><img src="../images/l.png" /></div></a>
    <%=lanmu_name %></div>
</div>
<%
            String afilepathsmall = SysCommonFunc.getPhotoSmallView();
            Date now= new Date();
            Calendar cal = Calendar.getInstance();
	    Calendar compareCA = Calendar.getInstance();
                            compareCA.add(Calendar.DATE, -2);
                            compareCA.set(compareCA.get(Calendar.YEAR), compareCA.get(Calendar.MONTH), compareCA.get(Calendar.DAY_OF_MONTH), 00, 00, 

00);
        %>
<div id="inner">
    <div class="hty">
        <div class="hty_t" style="color:#000;"><%=topicBb.getTitle()%></div>
        <div class="hty_c">
            <h5 class="h_c_l">
			 <%
                        if(uother.getHyid()!=749693){
                    %>
			 <a href="./perinfo-id-<%=uother.getHyid()%>.htm"   > <%=topicBb.getAuthor()%></a>
		 <font color="red"><%=buser.getMoneys()+buser.getMoneynew()%>金币</font> 
		 
		  <%
                    }else{
                    %>
                <%=topicBb.getAuthor()%> &nbsp;
                  <%
                        }
                    %>   
                </h5>
            <div class="h_c_c">
				<%
                    cal.setTime(now);
                    cal.add(Calendar.MONTH,-3);
                %>
                    	<%
                            if(topicBb.getOntop() == null || (topicBb.getOntop() != null && topicBb.getOntop().intValue() !=1))
                        {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                             String dateString = formatter.format(topicBb.getStime());
                             if (!((Date)topicBb.getStime()).after(compareCA.getTime())){
                              dateString="2天前";
                             }
                        %>
                    		 <%=dateString%>&nbsp;
                    	<%
                            }
                        %>

			</div>
            <div class="h_c_r"><a href="./hty-bbsid-<%=bbsid%>.htm"><img src="../images/fhl.png" /> 
            <%=totalCount%>篇</a></div>
        </div>
        
        <div class="clearfix"></div>
        <div class="hty_txt">
            <p><%=articleContent%></p>
            
            <div class="hty_tqw" style="display:none;">
              <img src="../images/xq.png" />
              点开展示全文
            </div>
        </div>
        
        <div class="clearfix"></div>
        <div class="hty_pl">
            <div class="h_pl_nav">会员评论</div>
        </div>
        <form name="form1" method="post" class="h_pl_txt" style="height:140px" action="addretopic.jsp" >
        <input type="hidden" name="bizaction" value="01">
        <input type="hidden" name="isfb" value=""/>
        <input type="hidden" name="bbsid" value="<%=bbsid%>"/>
        <input type="hidden" name="url" value="disp_bbs-bbsid-<%=bbsid%>.htm"/>
				<textarea id="content" name="content" class="h_plt_t"  style="height:65px;" placeholder="发表留言"></textarea>			
             <div class="h_plt_fb">发表</div> 
          </form>
          
        <div class="hty_news">
            <img src="../images/line.png" style="width:100%;" /> 
            <ul>
             <%
                cal.setTime(now);
        		cal.add(Calendar.DATE,-7);
             for(int i=0;i < qr.resultList.size();i ++)
        		{
		            DynaBean db = (DynaBean)qr.resultList.get(i);
		            uother = HYRegMng.getUserOtherByHyid(db.get("user_id").toString());
		            uother.getUserphoto1();
            %>
            
                 <li>
                    <div class="hnul_l">
                    <%
                     if(uother.getUserphoto1() != null)
                     {
                      String photo = uother.getUserphoto1().replace("\\","/");
                 %>

         	<a href="./perinfo-id-<%=db.get("user_id")%>.htm" >
             <img src='<%=afilepathsmall+"/"+ photo%>'/>
             </a>
                <%
                 }else{
                %>
                   <img src="../images/img2.png" />
                   <%
                 }
                %> 
                    </div>
                    <div class="hnul_c">
                        <h5 class="grey3">
                <%
                   if(!"749693".equals(db.get("user_id").toString())){
                        Long moneys =  Long.parseLong(db.get("moneys").toString());
                        Long moneynew =  Long.parseLong(db.get("moneynew").toString());  
                        Long moneyAll = moneys+moneynew;
                        String author = db.get("author").toString();
                        if(author !=null && !author.startsWith("手机用户") && author.length()!=8){
	                    %>
	                   		<a href="./perinfo-id-<%=db.get("user_id")%>.htm"  style="color: #21648f" ><%=db.get("author")%></a>
	                   <% }else{ %>
	                   		<%=db.get("author")%>
	                   <% }%>
                    <font color="red"><%=moneyAll%>金币</font> 
                    <span style="color:#aaa;font-weight:normal;size:6"><%
                           if (!((Date)db.get("s_time")).after(compareCA.getTime())){%>
                   2天前
                  <%} else {%>
                     <%=DateTools.DateToString((Date)db.get("s_time"),"yyyy-MM-dd")%>
                  <% }%></span>

		  <%
                    }else{
                    %>
                    <%=db.get("author")%>&nbsp;
                    <%
                        }
                    %>
                        </h5>
                        <!-- <h4 class="grey9"><%=topicBb.getTitle()%></h4> -->
                    </div>
                     <%
	                if(!db.get("re_id").toString().equals(bbsid.toString()))
	                {
		            %>
		            <%
		                }else{
		            %>
                    <div class="hnul_r" data-id1='<%=db.get("id")%>' data-id2='<%=db.get("user_id")%>' data-id3='<%=loginId%>'>
                    <img src="../images/dz.png" /> <p>(<%=db.get("zan")==null?"0":db.get("zan")%>)</p>
                    </div>
                    <div class="hnul_txt"><%=SysCommonFunc.getStringFromBlob((java.sql.Blob)db.get("content"))%></div>
                    <%
		                }
		            %>
                </li>
                
                <%
                 }
                %>
                <div class="clearfix" style="padding:20px;"></div>
            </ul>
            <div class="clearfix"  style="padding-top:20px;"></div>
            <a href="./hty-bbsid-<%=bbsid%>.htm"><div class="hty_an">更多回复</div></a>
             <div class="clearfix"  style="padding-top:20px;"></div>
        </div>
    </div>
    
</div>

<%@ include file="bottom2.jsp"%>
  <%@ include file="bottom.jsp"%>
<% /*if (loginUser==null){
   if(sharehyid!=null && !"".equals(sharehyid)&& !"0".equals(sharehyid)){
       out.println("<script language='javascript'>function myfunction(){ alert('请点击下方交友官网注册新会员！'); } window.onload=myfunction;</script>"); 
      }
  }*/ %>
</body>
<form id="zanForm" name="zanForm"  method="post" action="../zan_do.jsp"  >
    <input type="hidden" id="bbsReId" name="bbsReId" value="">
    <input type="hidden"  name="bbsid" value="<%=bbsid%>">
    <input type="hidden"  id="userId"  name="userId" value="">
    <input type="hidden"  id="selfId"  name="selfId" value="">
</form>
<!-- build:js scripts/lib/base.min.js -->
<script type="text/javascript" src="../js/lib/zepto.min.js"></script>
<script type="text/javascript" src="../js/lib/exp.js"></script>
<!-- endbuild -->  
<script type="text/javascript" src="../js/bbs.js?v=114"></script> 
</html>
