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
    String loginuserid = "";
    Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
    if (loginUser!=null){
        loginId = loginUser.getHyid();
        loginuserid = loginId+"";
        String vurl="";
        vurl=request.getRequestURL().toString();
        if (request.getQueryString()!=null){
            vurl+="?"+request.getQueryString();
        }
        UserVisitMng.insertUserVisit(loginUser.getHyid(), vurl);
    }

    Bb topicBb = BBSMng.getBBSById(bbsid);
    Bcb bcb = null;
    String content = "";
    String lanmu_name = "";
    Long sortid = 0L;
    if(topicBb != null && topicBb.getContent() != null)
    {
        bcb = BCBMng.getBcbFromid(topicBb.getContent().toString());
        content = SysCommonFunc.getStringFromBlob(bcb.getContent());
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

/* delete by linyu at 20150914 for 执行效率太低。数据库CPU占满100% 
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
            " where b.ischeck='0' and  b.commend ="+ bbsid +" and  b.re_id !="+ bbsid+"  ) order by  id desc ";   
            
                   
            
    String cpages = SysCommonFunc.getStrParameter(request,"cpages");
    int cpage = SysCommonFunc.getNumberFromString(cpages, 1);
    int pagesize = 8;
    QueryResult qr = QueryRecord.queryByDynaResultSet(sql, pagesize, cpage);
    int totalPage = qr.pageCount;
    int totalCount = qr.rowCount;
    cpage = qr.pageNum;
    int prepage = cpage-1;
    int nextpage = cpage+1;

%>

 
<body>
<div></div>
<div id="header">
    <div class="h_nav"> 
    <a href="./disp_bbs-bbsid-<%=bbsid%>-sharehyid-<%=loginuserid %>-tjtype-11.htm"><div class="h_left"><img src="../images/l.png" /></div></a>  回帖页</div>
</div>
<%
            String afilepathsmall = SysCommonFunc.getPhotoSmallView();
            Date now= new Date();
            Calendar cal = Calendar.getInstance();
        %>
<div id="inner">
    <div class="hty">
        <div class="hty_t"><%=topicBb.getTitle()%></div>
        <div class="hty_c">
            <div class="h_c_l">
			 <%
                        if(uother.getHyid()!=749693){
                    %>
			<a href="./perinfo-id-<%=uother.getHyid()%>.htm"   > <%=topicBb.getAuthor()%></a>
		 &nbsp;<font color="red"><%=buser.getMoneys()+buser.getMoneynew()%>金币</font>
		 
		  <%
                    }else{
                    %>
                <%=topicBb.getAuthor()%> &nbsp;
                  <%
                        }
                    %>   
             </div>
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
                        %>
                    		 <%=dateString%>&nbsp;
                    	<%
                            }
                        %>
                </div>
            <div class="h_c_r"> <img src="../images/fhl.png" /> 
            <%=totalCount%>篇 </div>
            </div>
       
        <div class="clearfix"></div>
        <div class="hty_pl">
            <div class="h_pl_nav">会员评论</div>
        </div>
        <form name="form1" method="post" class="h_pl_txt" style="height:150px" action="addretopic.jsp" >
        <input type="hidden" name="bizaction" value="01">
        <input type="hidden" name="isfb" value=""/>
        <input type="hidden" name="bbsid" value="<%=bbsid%>"/>
        <input type="hidden" name="url" value="hty-bbsid-<%=bbsid%>.htm"/>
            <textarea id="content" name="content" class="h_plt_t" style="height:65px;"  placeholder="发表留言"></textarea>
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
                 %>

         	<a href="./perinfo-id-<%=db.get("user_id")%>.htm" >
             <img src="../<%=afilepathsmall+File.separator+uother.getUserphoto1()%>" /> 
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
                        if(author !=null && !author.startsWith("手机用户") && author.length()!=8 ){
	                    %>
	                   <a href="./perinfo-id-<%=db.get("user_id")%>.htm"  style="color: #21648f" ><%=db.get("author")%></a>
	                   <% }else{ %>
	                   <%=db.get("author")%>
	                   <% }%>
                    &nbsp;<font color="red"><%=moneyAll%>金币</font>
                    <%
                    }else{
                    %>
                    <%=db.get("author")%>&nbsp;
                    <%
                        }
                    %>
                        </h5>
                        <!--  <h4 class="grey9"><%=topicBb.getTitle()%></h4>-->
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
            </ul>
             <div class="clearfix"></div>
              </div>
    <form name="go2to" id="go2to" method="post" action="hty.jsp">
    
    <div class="number">
        <ul>
           
                  <input type="hidden" id="cpages" name="cpages" value="<%=cpage%>" />
                  <input type="hidden" name="tcounts" value="<%=qr.rowCount%>">
                  <input type="hidden" name="sortid" value="<%=sortid%>"/>
                  <input type="hidden" name="bbsid" value="<%=bbsid%>"/>
                      <%
                if(cpage == 1)
                    out.println("<li>首页</li>");
                else
                {
                    out.println("<li data-id='1'>首页</li>");
                }
                if(cpage >2)
                    out.println("<li data-id="+prepage+" style='width:19%;'>上一页</li>");
                
                
                int allpage = 5;
                int startpage = 0;
                int endpage = allpage;
                if(cpage>2){
                    startpage = cpage-1;
                    endpage =(cpage+3)>totalPage?totalPage:(cpage+3);
                }
                 if(totalPage<allpage){
                     endpage =totalPage;
                 }
                for(int i=startpage;i<endpage;i++){
                    if((i+1)==cpage){
                        out.println("<li data-id="+(i+1)+" class='check'>"+(i+1)+"</li>");
                    }else{
                        out.println("<li data-id="+(i+1)+">"+(i+1)+"</li>");
                    }

                }
  			  if(cpage < totalPage ){
  			 //  	out.println("<li data-id="+nextpage+">下一页</li>");
                }
            %>
 
        </ul>
    </div>
     </form>
    </div>
    </div>
    
</div>
<form id="zanForm" name="zanForm"  method="Post" action="../zan_do.jsp"  >
    <input type="hidden" id="bbsReId" name="bbsReId" value="">
    <input type="hidden"  name="bbsid" value="<%=bbsid%>">
    <input type="hidden"  id="userId"  name="userId" value="">
    <input type="hidden"  id="selfId"  name="selfId" value="">
</form>
<!-- build:js scripts/lib/base.min.js -->
<script type="text/javascript" src="../js/lib/zepto.min.js"></script>
<script type="text/javascript" src="../js/lib/exp.js"></script>
<!-- endbuild -->  
<script type="text/javascript" src="../js/hty.js?v=111"></script> 

<%@ include file="bottom2.jsp"%>
  <%@ include file="bottom.jsp"%>
</body>
</html>
