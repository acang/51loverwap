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
<%@ page import="org.apache.commons.beanutils.DynaBean" %>
<%@ page import="java.io.File" %>

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
<body class="cm_bbsxq">
<%@ include file="head.jsp"%>
<%
    String bbsid = SysCommonFunc.getStrParameter(request,"bbsid");
    if(SysCommonFunc.getNumberFromString(bbsid,0)==0)
    {
        System.out.println("sql注入"+bbsid);
        return;
    }
    Long loginId = 0L;
    Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
    if (loginUser!=null){
        loginId = loginUser.getHyid();
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


    String sql = "select b.id,b.author,b.user_id,b.s_time,b.re_id,b.commend,b.zan,bu.grade,bu.moneys,bu.moneynew,bc.content,u.Userphoto1," +
            "ui.regtime  from bbs b  left join bbsuser bu on (b.user_id = bu.hyid) left join bcb bc on (b.content = bc.id) " +
            "left join Userother u on b.user_id=u.hyid " +
            "left join Userinfo ui on b.user_id=ui.hyid "+
            " where b.ischeck='0' and (b.re_id ="+ bbsid + "  or b.commend ="+ bbsid +") order by b.id desc";
    String cpages = SysCommonFunc.getStrParameter(request,"cpages");
    int cpage = SysCommonFunc.getNumberFromString(cpages, 1);
    int pagesize = 10;
    QueryResult qr = QueryRecord.queryByDynaResultSet(sql, pagesize, cpage);
    int totalPage = qr.pageCount;
    int totalCount = qr.rowCount;
    cpage = qr.pageNum;
    int prepage = cpage-1;
    int nextpage = cpage+1;

%>


<%
    String bizaction = SysCommonFunc.getStrParameter(request,"bizaction");
    String isfb   = SysCommonFunc.getStrParameter(request,"isfb");
    if(bizaction.equals("01"))
    {
        if(loginUser == null)
        {
            out.println("<script language='javascript'>alert('您是游客，您的权限不够，请先登陆!'); showLogin();</script>");
            return;
        }


        String mailreg = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";
        String preg    = "\\d{6,}";

        String s = null;
        if(content == null)
            content = "";
        String tempc=content.replaceAll("<p>","");
        tempc = tempc.replaceAll("</p>","");
        //System.out.println(tempc);
        if(tempc == null || tempc.length() < 10)
        {

            s = "回帖请多于10个字！";
        }
        else
        {
            java.util.regex.Pattern pattern1 = java.util.regex.Pattern.compile(mailreg,java.util.regex.Pattern.CASE_INSENSITIVE);
            java.util.regex.Pattern pattern2 = java.util.regex.Pattern.compile(preg,java.util.regex.Pattern.CASE_INSENSITIVE);
            if(pattern1.matcher(content).matches() || pattern2.matcher(content).matches())
                s = "回帖中有疑似联系方式，请重新填写";
            else
                s = BBSMng.userAddReTopic(request,loginUser,content,bbsid);
        }


        if(s == null)
        {
            out.println("<script langauge=javascript>alert('发送成功');</script>");
            out.println("<script langauge=javascript>parent.location.reload();</script>");
            isfb = "isfb";
            content = "";
        }
        else{
            out.println("<script langauge=javascript>alert('"+s+"！');</script>");
        }


    }
%>

<div class="wrapper1250 cm_block01" style="margin-top:8px;">
    <div class="box01" style="background-color:#f7f7f7; padding-left:15px; width:1085px;font-size:14px; height:40px; line-height:40px; margin-bottom:8px;">
        <span style=" float:left"><a href="bbs/index.htm" style="font-size:14px">
            <strong>会员交流</strong></a>&nbsp;&gt;&nbsp;
            <a href="bbs/bbsindex.jsp?sortid=<%=sortid%>" style="font-size:14px"><strong><%=lanmu_name%></strong></a>&nbsp;&gt;&nbsp;
            <strong><%=topicBb.getTitle()%></strong></span></div>
    <div class="block01">
        <%
            String afilepathsmall = SysCommonFunc.getPhotoSmallView();
            Date now= new Date();
            Calendar cal = Calendar.getInstance();
        %>
        <div class="box01">
            <span class="tx">
                <%
                    if (uother.getHyid() != 749693) {
                        if (uother.getUserphoto1() != null) {
                %>

            <a href="./perinfo-id-<%=uother.getHyid()%>.htm" target="_blank" style="color: #21648f" >
                <img src="../<%=afilepathsmall+File.separator+uother.getUserphoto1()%>" width="130" height="150" border="0">
                </a>
                 <%
                         }
                     }
                 %>
            </span>
            <ul>

                    <%
                        if(uother.getHyid()!=749693){
                    %>

                <li><strong>发 帖 人：<a href="./perinfo-id-<%=uother.getHyid()%>.htm" target="_blank" style="color: #21648f" > <%=topicBb.getAuthor()%></a></strong>
                </li>

                <li><strong>论坛等级：</strong><%=BBSMng.getGradeDes(buser)%></li>
                <li><strong>主 题 帖：</strong><%=buser.getTopics()%>篇</li>
                <li><strong>推 荐 贴：</strong><%=buser.getCommend()%>篇</li>
                <li><strong>跟　　帖：</strong><%=buser.getResum()%>篇 </li>
                <%
                    long moneyold = buser.getMoneys()==null?0:buser.getMoneys();
                    long moneynew = buser.getMoneynew()==null?0:buser.getMoneynew();
                %>
                <li><strong>金　　币：</strong><%=moneyold+moneynew%>金币</li>
                <li><strong>加入时间：</strong><%=DateTools.dateToString(buser.getRegtime(), false)%></li>
                    <%
                    }else{
                    %>
                <br/>      <br/>
                <div style="text-align: center"><strong>发 帖 人：<span  style="color: #21648f" ><%=topicBb.getAuthor()%></span></strong></div>

                    <%
                        }
                    %>


            </ul>
        </div>
        <div class="box02">
            <div class="lm_name">
                <span style=" float:left; font-size:14px;">&nbsp;&nbsp;&nbsp; <%
                    cal.setTime(now);
                    cal.add(Calendar.MONTH,-3);
                %>
                    	<%
                            if(topicBb.getOntop() == null || (topicBb.getOntop() != null && topicBb.getOntop().intValue() !=1))
                        {
                        %>
                    		发布时间：<%=DateTools.dateToString(topicBb.getStime(),true)%>
                    	<%
                            }
                        %>
                </span>
                <span style=" float:right; font-size:14px; padding-right:20px;"><a href="bbs_huifu.jsp?bbsid=<%=bbsid%>">回帖：<strong style="color:#F00; font-size:18px"><%=totalCount%></strong>篇</a></span>
            </div>
            <div class="content" style="font-size:16px">
                <h1 style="text-align:center; font-size:24px;line-height:30px;margin-bottom:10px;"><%=topicBb.getTitle()%></h1>
                <%=content%></div>
        </div>
        <div style="clear:both"></div>
    </div>
    <iframe src="addretopic.jsp?bbsid=<%=topicBb.getId()%>"  width="100%" height="205" frameborder="0" scrolling="no"></iframe>
    <div style="clear:both"></div>
       <div id="bbs_re_area">
        <%
        cal.setTime(now);
        cal.add(Calendar.DATE,-7);
        for(int i=0;i < qr.resultList.size();i ++)
        {
            DynaBean db = (DynaBean)qr.resultList.get(i);
            uother = HYRegMng.getUserOtherByHyid(db.get("user_id").toString());
            uother.getUserphoto1();
        %>
      <div class="block01" style="padding:0;">
        <div class="box01">
            <span class="tx">

                 <%
                     if(uother.getUserphoto1() != null)
                     {
                 %>

         <a href="./perinfo-id-<%=db.get("user_id")%>.htm" target=_blank  style="color: #21648f" >
             <img src="../<%=afilepathsmall+File.separator+uother.getUserphoto1()%>" width="130" height="150" border="0">
             </a>
                <%
                 }
                %>

            </span>
            <ul>
                  <%
                        if(!"749693".equals(db.get("user_id").toString())){
                    %>
                   <li><strong>回 帖 人：<a href="./perinfo-id-<%=db.get("user_id")%>.htm" target=_blank  style="color: #21648f" ><%=db.get("author")%></a>   </strong></li>
                   <li><strong>论坛等级：</strong>
                    <%=BBSMng.getGradeDes(SysCommonFunc.getNumberFromString(db.get("moneys") + "", 0))%>
                   </li>
                <%
                    long moneyold = db.get("moneys")==null ? 0:new Long(db.get("moneys").toString());
                    long moneynew =  db.get("moneynew")==null ? 0:new Long(db.get("moneynew").toString());
                %>
                    <li><strong>金　　币：</strong><%=moneyold+moneynew%>金币</li>
                    <li><strong>加入时间：</strong><%=db.get("regtime")%></li>
                    <%
                    }else{
                    %>
                    <strong>回 帖 人：<span  style="color: #21648f" ><%=db.get("author")%></span>   </strong>
                    <%
                        }
                    %>



            </ul>
        </div>
        <div class="box02">

            <%
                if(!db.get("re_id").toString().equals(bbsid.toString()))
                {
            %>
            <%
                }else{
            %>
            <div class="lm_name02">
                <span style=" float:left; font-size:14px">&nbsp;&nbsp;&nbsp;回复时间：
                        <%
                         //   if (!((Date)db.get("s_time")).after(cal.getTime())){%>
              		   <%--2天前回复--%>
              		  <%//} else {%>
              		     <%=DateTools.dateToString((Date)db.get("s_time"),true)%>
              		  <%// }%>
                </span>
                <a href="javascript:dianZan(<%=db.get("id")%>,<%=db.get("user_id")%>,<%=loginId%>)" style="float:right; display:block;height:30px;
                 background:url(skin/zan.jpg) 40px 0 no-repeat; margin:5px 0; line-height:30px; font-size:16px; margin-right:80px;">
                    点赞&nbsp;&nbsp;&nbsp;&nbsp;<strong style="color:#F00; font-size:18px">(<%=db.get("zan")==null?"0":db.get("zan")%>)</strong></a>
            </div>
            <div class="content" style="font-size:16px;"><%=SysCommonFunc.getStringFromBlob((java.sql.Blob)db.get("content"))%></div>
            <%
                }
            %>
        </div>
        <div style="clear:both"></div>
    </div>
        <%

        }
    %>
       </div>
    <div class="Paging" style="height:60px; margin-top:30px;"><%
        int startpage = 0;
        if((cpage-5) <=0){
            startpage = 1;
        }else{
            startpage = cpage-5;
        }
        if(cpage == 1)
            out.println("<a href='###'>首    页</a>&nbsp;&nbsp;<a href='###'>上一页</a>");
        else
        {
            out.println("<a href='javascript:turn(1)'>首    页</a>");
            out.println("<a href='javascript:turn("+prepage+")'>上一页</a>");
        }
        for (int j = startpage; j < startpage + 9; j++) {
            if (j <= totalPage) {
                if (j == cpage) {
                    out.println("<a href='javascript:turn(" + j + ")' class='Check'>" + j + "</a>");
                } else {
                    out.println("<a href='javascript:turn(" + j + ")'>" + j + "</a>");
                }
            }
        }

        if(cpage >= totalPage )
            out.println("<a href='###'>下一页</a>&nbsp;&nbsp;<a href='###'>尾页</a>");
        else
        {
            out.println("<a href='javascript:turn("+nextpage+")'>下一页</a>");
            out.println("<a href='javascript:turn("+totalPage+")'>尾页</a>");
        }
    %>
        <div style="clear:both;"></div>
    </div>

    <div style="clear:both"></div>

    <%@ include file="bottom.jsp"%>
</body>

<form id="pageform" name="pageform"  method="Post" action="disp_bbs.jsp" target="_self">
    <input type="hidden" name="cpages" value="<%=cpage%>" />
    <input type="hidden" name="tcounts" value="<%=qr.rowCount%>">
    <input type="hidden" name="bbsid" value="<%=bbsid%>">
</form>

<form id="zanForm" name="zanForm" name="turn" method="Post" action="zan_do.jsp" target="zanIframe">
    <input type="hidden" name="bbsReId" value="">
    <input type="hidden" name="bbsid" value="<%=bbsid%>">
    <input type="hidden" name="userId" value="">
    <input type="hidden" name="selfId" value="">
</form>
   <iframe name="zanIframe" width="0" height="0" frameborder="0"></iframe>
<script>
    function turn(a){
        document.pageform.cpages.value=a;
        document.pageform.submit();
    }

    function dianZan(a,b,c){
        if(c==0){
          alert("您是游客，请先登陆或注册！");
            showLogin("","");
            return;
        }
        document.zanForm.bbsReId.value = a;
        document.zanForm.userId.value = b;
        document.zanForm.selfId.value = c;
        document.zanForm.submit();

    }
</script>


</html>
