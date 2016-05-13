<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.web.common.SysCommonFunc" %>
<%@ page import="com.web.bean.QueryResult" %>
<%@ page import="com.web.bean.QueryRecord" %>
<%@ page import="com.lover.mng.BBSMng" %>
<%@ page import="com.common.SysDefine" %>
<%@ page import="com.lover.mng.UserVisitMng" %>
<%@ page import="com.web.obj.*" %>
<%

    String sortid = SysCommonFunc.getStrParameter(request,"sortid");
    String lanmu_name = "";
    if("13".equals(sortid)){
        lanmu_name = "交友动态";
    }
    if("3".equals(sortid)){
        lanmu_name = "交友观念";
    }
    if("12".equals(sortid)){
        lanmu_name = "交友攻略";
    }
    if("9".equals(sortid)){
        lanmu_name = "交友故事";
    }
    if(sortid.length() ==0)
    {
        out.println("请选择主题!");
        return;
    }


    if(SysCommonFunc.getNumberFromString(sortid,0)==0)
    {
        out.println("请选择主题!");

        System.out.println("sql注入"+sortid);
        return;
    }
    Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
    Userother uother = (Userother)session.getAttribute(SysDefine.SESSION_LOGINNAME_OTHER);
    String loginuserid="";
    if (loginUser!=null){
    loginuserid = loginUser.getHyid()+"";
        String vurl="";
        vurl=request.getRequestURL().toString();
        if (request.getQueryString()!=null){
            vurl+="?"+request.getQueryString();
        }
        UserVisitMng.insertUserVisit(loginUser.getHyid(), vurl);
    }
    Bbssort bsort = BBSMng.getBbssort(sortid);
    if(bsort == null)
    {
        out.println("您查看的主题不存在!");
        return;
    }
    

    String cpages = SysCommonFunc.getStrParameter(request,"cpages");
    int cpage = SysCommonFunc.getNumberFromString(cpages,1);
    String tcounts = SysCommonFunc.getStrParameter(request, "tcounts");
    int tcount  = SysCommonFunc.getNumberFromString(tcounts,0);
      int pagesize = 8;
    String sql = "from Bb as o where o.reId =0 and o.sortid="+sortid + " and o.ischeck=1 order by o.ontop desc,o.commend desc, o.reTime desc,o.stime desc";

    QueryResult qr = null;
    if(tcount > 0)
        qr = QueryRecord.queryByHbm(sql, pagesize, cpage);
    else
        qr = QueryRecord.queryByHbm(sql,pagesize,cpage);
    int totalPage = qr.pageCount;
    int totalCount = qr.rowCount;
    cpage = qr.pageNum;
    int prepage = cpage-1;
    int nextpage = cpage+1;
%>
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
<meta name="description" content="51交友中心网站--中国交友行业领军品牌。亲密关系、终生伴侣、情商训练，全方位支持您的情感生活。十年运营，千万会员，美满感情，从51交友开始！" />
<meta name="keywords" content="51交友中心--亲密交友 情缘交友 同城交友 知己交友 美女交友" />
<link rel="stylesheet" type="text/css" href="../css/style.css">
</head>

<body>

<div id="header">
    <div class="h_nav"> 
    <a href="index.jsp"><div class="h_left"><img src="../images/l.png" /></div></a>
                      <%=lanmu_name%></div>
</div>

<div class="inner">
    <div class="news">
        <ul>
        <%

            for(int i=0;i < qr.resultList.size();i ++)
            {
                Bb bb = (Bb)qr.resultList.get(i);
                String title = bb.getTitle();
                String zt    = "";
				Bbsuser buser = BBSMng.getBbsuserByHyid(bb.getUserId().toString());

                if(bb.getCommend() != null && bb.getCommend().intValue() ==1)
                {
                    zt ="[新帖]"; 
                }
				%>
				<li>
                <div class="nul_l"><img src="../images/img2.png" /></div>
                <div class="nul_c">
                	 <%
		                  if(bb.getUserId()!=749693){
		              %>
		                <h5 class="grey3"><a href="../perinfo-id-<%=bb.getUserId()%>.htm" ><%=bb.getAuthor()%></a>  &nbsp;<font color="red"><%=buser.getMoneys()+buser.getMoneynew()%>金币</font></h5>
		              <%
		                  }else{
		                      %>
		                <h5 class="grey3"><%=bb.getAuthor()%>&nbsp;</h5>
		              <%
		                  }
		              %>
                    <h4 class="grey9" ><a href="./disp_bbs-bbsid-<%=bb.getId()%>-sharehyid-<%=loginuserid %>-tjtype-11.htm" style="color:#000;"><%=zt%><%=title%></a></h4>
                </div>
                <div class="nul_r"><a href="./disp_bbs-bbsid-<%=bb.getId()%>-sharehyid-<%=loginuserid %>-tjtype-11.htm"><img src="../images/r.png" /></a></div>
            </li>	
	             <%
	            }
	        %>
              
        </ul>
    </div>
    
    <div class="clearfix"></div>
    <form name="go2to" id="go2to" method="post" action="bbsindex.jsp">
    
    <div class="number">
        <ul>
           
                  <input type="hidden" id="cpages" name="cpages" value="<%=cpage%>" />
                  <input type="hidden" name="tcounts" value="<%=qr.rowCount%>">
                  <input type="hidden" name="sortid" value="<%=sortid%>"/>
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
  			//   	out.println("<li data-id="+nextpage+">下一页</li>");
                }
            %>
 
        </ul>
    </div>
     </form>
    
</div>
<%@ include file="bottom2.jsp"%>
  <%@ include file="bottom.jsp"%>
  
<!-- build:js scripts/lib/base.min.js -->
<script type="text/javascript" src="../js/lib/zepto.min.js"></script>
<script type="text/javascript" src="../js/lib/exp.js"></script>
<!-- endbuild --> 
<script type="text/javascript" src="../js/btm.js"></script>  
<script type="text/javascript" src="../js/bbsindex.js"></script> 
</body>
</html>
