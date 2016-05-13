<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>
<%@ page import="com.web.bean.QueryResult" %>
<%@ page import="com.web.common.SysCommonFunc" %>
<%@ page import="com.lover.mng.UserVisitMng" %>
<%@ page import="com.lover.LoverTools" %>
<%@ page import="com.web.bean.QueryRecord" %>
<%@ page import="com.web.obj.extend.DicList" %>
<%@ page import="com.lover.mng.HYRegMng" %>
<%@ page import="com.web.common.DateTools" %>
<%@ page import="com.common.SysDefine" %>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.io.File" %>
<%@ page import="com.web.obj.*" %>
<%
    String afilepathsmall = SysCommonFunc.getPhotoSmallView();
    request.setCharacterEncoding("gb2312");
    Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);


    if (loginUser!=null){
        String vurl="";
        vurl=request.getRequestURL().toString();
        if (request.getQueryString()!=null){
            vurl+="?"+request.getQueryString();
        }
        UserVisitMng.insertUserVisit(loginUser.getHyid(), vurl);
    }
%>
<%
  //  System.out.println("高级查询"+request.getRemoteAddr()+"---"+loginUser.getUsername());
    String isvcation  = SysCommonFunc.getStrParameter(request,"isvcation");
    /*if(isvcation==null){
        isvcation = "0";
    }*/
    String sex = SysCommonFunc.getStrParameter(request,"sex");
    if(sex.length() ==0){
        sex = "0";
    }
    String address = request.getParameter("s1");
    HashMap hm = (HashMap)request.getAttribute("query");
    if(hm != null && hm.get("s1") != null)
    {
        address = hm.get("s1").toString();
    }
    if(address == null)
        address = "";
    String s2 = request.getParameter("s2");
    if(hm != null && hm.get("s2") != null)
        s2 = hm.get("s2").toString();
    if(s2 == null)
        s2 = "";
    int jyyxP =0;
    String jyyx = SysCommonFunc.getStrParameter(request,"jyyx");

    if(jyyx!=null){
         jyyxP = SysCommonFunc.getNumberFromString(jyyx,0);
  }

    String yx   = SysCommonFunc.getStrParameter(request,"yx");
    if(yx.length() ==0){
        yx = "0";
    }

    String hyzk  = SysCommonFunc.getStrParameter(request,"hyzk");
    if(hyzk.length() ==0){
        hyzk = "0";
    }


    String sx = SysCommonFunc.getStrParameter(request,"sx");
    if(sx.length() ==0){
        sx = "0";
    }

    String xz = SysCommonFunc.getStrParameter(request,"xz");
    if(xz.length() ==0)
        xz = "0";
    String sg = SysCommonFunc.getStrParameter(request,"sg");
    if(sg.length() ==0){
        sg = "0";
    }

    String tz = SysCommonFunc.getStrParameter(request, "tz");
    if(tz.length() ==0){
        tz = "0";
    }

    String fs = SysCommonFunc.getStrParameter(request,"fs");
    if(fs.length() ==0){
        fs = "0";
    }

    String whcd = SysCommonFunc.getStrParameter(request,"whcd");
    if(whcd.length() ==0){
        whcd = "0";
    }

    String zf = SysCommonFunc.getStrParameter(request,"zf");
    if(zf.length() ==0)
        zf = "0";


    int agestart = SysCommonFunc.getNumberFromString(SysCommonFunc.getStrParameter(request,"agestart"),0);
    int ageend = SysCommonFunc.getNumberFromString(SysCommonFunc.getStrParameter(request,"ageend"),0);

    String himg = SysCommonFunc.getStrParameter(request,"himg");

    String zy = SysCommonFunc.getStrParameter(request,"zy");
    if(zy.length() ==0)
        zy = "0";
    String lltime = SysCommonFunc.getStrParameter(request,"lltime");
    String user_income = SysCommonFunc.getStrParameter(request,"user_income");
    int income  = SysCommonFunc.getNumberFromString(user_income,0);

    String cpages = SysCommonFunc.getStrParameter(request,"cpages");
    if(cpages==null|| "".equals(cpages) ){
        cpages = "1";
    }
    int cpage = SysCommonFunc.getNumberFromString(cpages,1);
    String tcounts = SysCommonFunc.getStrParameter(request,"tcounts");
    int tcount  = SysCommonFunc.getNumberFromString(tcounts,0);
    int pagesize = 9;

    ///得到用户列表
    String sql = "from Userinfo as o where o.isdel = 0 ";
    if(isvcation!=null && isvcation!=""){
        sql=sql+" and o.isvcation = "+isvcation;
    }
    if(sex.length() > 1){
        sql = sql + " and o.sex ='"+sex+"'";
    }

    if(address.length() > 1)
        sql = sql + " and o.s1 ='"+address+"'";
    if(s2.length() > 1)
    {
        if(address.equals("北京") || address.equals("天津") || address.equals("重庆") || address.equals("上海"))
            sql = sql +" and  ( o.s2 = '"+address+"' or s2 = '" + s2+"')";
        else
            sql = sql + " and o.s2 ='"+s2+"'";
    }
    if(jyyxP > 0 && jyyxP < 8)
    {
        sql = sql + " and jyyx like '"+ LoverTools.getMinJyyx(jyyxP)+"'";
    }
    if(whcd.length() > 1){
        sql = sql + " and o.whcd = '"+whcd+"'";
    }

    if(agestart !=0 && ageend!=0)
    {
        int year = new Date(System.currentTimeMillis()).getYear() + 1900 - agestart;
        String csdate = year + "-12-31 23:59:59";
        sql = sql + " and o.csdate <= to_date('"+csdate+"','YYYY-MM-DD HH24:MI:SS')";

         year = new Date(System.currentTimeMillis()).getYear() + 1900 - ageend;
         csdate = year + "-01-01";
         sql = sql + " and o.csdate >= to_date('"+csdate+"','YYYY-MM-DD')";
    }

    if(zy.length() > 1){
        sql = sql + " and (o.zy is null or o.zy = '"+zy+"')";
    }
    if(himg.length() > 0 && !himg.equals("0"))
    {
        if(himg.equals("1"))
            sql = sql + " and o.img > 0";
        else if(himg.equals("2"))
            sql = sql + " and o.img = 0";
    }

    if(hyzk.length() > 1){
        sql = sql + " and o.hyzk = '"+hyzk+"'";
    }
    if(zf.length() > 1){
        sql = sql + " and o.zf = '"+zf+"'";
    }

    if(yx.length() > 1)
        sql = sql + " and o.yx = '"+yx+"'";

    if(sx.length() > 1){
        sql = sql + " and o.sx = '"+sx+"'";
    }
    if(sg.length() > 1){
        sql = sql + " and o.sg = '"+sg+"'";
    }
    if(xz.length() > 1){
        sql = sql + " and o.xz = '"+xz+"'";
    }
    if(tz.length() > 1){
        sql = sql + " and o.tz = '"+tz+"'";
    }
     if(fs.length() > 1){
        sql = sql + " and o.fs = '"+fs+"'";
    }

    if(income>0){
        sql = sql + " and o.yx = '"+income+"'";
    }
 
    sql = sql+" order by o.regtime5 desc";

    System.out.println("--"+sql);
    QueryResult qr = null;

    long start = System.currentTimeMillis();
    if(tcount > 0)
        qr = QueryRecord.queryByHbm(sql,pagesize,cpage,true,tcount);
    else
        qr = QueryRecord.queryByHbm(sql, pagesize, cpage);
    int totalPage = qr.pageCount;
    int totalCount = qr.rowCount;
    cpage = qr.pageNum;
    int prepage = cpage-1;
    int nextpage = cpage+1;
    long step = System.currentTimeMillis()-start;

    System.out.println("time="+step/1000);

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
<title>51交友中心</title>>
<meta name="description" content="51交友中心网站-中国交友行业领军品牌。亲密关系、终生伴侣、情商训练，全方位支持您的情感生活。十年运营，千万会员，美满感情，从51交友开始！" />
   <meta name="baidu-site-verification" content="XGXEHLUB1e" />
<meta name="keywords" content="51交友中心--亲密交友 情缘交友 同城交友 知己交友 美女交友" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div id="header">
    <div class="h_nav"> 
    <a href="search.jsp"><div class="h_left"><img src="images/l.png" /></div></a> 搜索结果</div>
</div>

<div id="inner">
    <div class="ssjg">
       <div class="ssjg_t">
            <div class="ssjg_tl">搜索：</div>
            <div class="ssjg_tr">
            <%
                    if("10".equals(sex)){
                       out.print("男");
                    }
                    if("11".equals(sex)){
                        out.print("女");
                    }
                %>
            <%
                    if("1".equals(jyyx)){
                        out.print("纯友谊");
                    }
                    if("2".equals(jyyx)){
                        out.print("亲密关系");
                    }
                    if("3".equals(jyyx)){
                        out.print("婚 姻");
                    }
                    if("4".equals(jyyx)){
                        out.print("陪伴");
                    }
                    if("5".equals(jyyx)){
                        out.print("其它");
                    }
                %>
                 <%
                    if(agestart>0 && ageend>0 && agestart!=16 && ageend!=70){
                        out.print("年龄" +agestart+" ~ " + ageend);
                    }

                %>

                <%
                    if("1".equals(himg)){
                        out.print("有照片");
                    }
                    if("2".equals(himg)){
                        out.print("无照片");
                    }
                %>

                <%
                       if("10".equals(hyzk)){
                            out.print("未婚");
                        }
                            if("11".equals(hyzk)){
                                out.print("已婚");
                            }
                            if("12".equals(hyzk)){
                                out.print("单身");
                            }
                 %>
                <%
                    if("10".equals(fs)){
                        out.print("白皙");
                    }
                    if("11".equals(fs)){
                        out.print("一般");
                    }
                    if("12".equals(fs)){
                        out.print("偏黑");
                    }
                %>


                <%

                            if("10".equals(sg)){
                                out.print("145cm以下");
                            }
                            if("11".equals(sg)){
                                out.print("145-150cm");
                            }
                            if("12".equals(sg)){
                                out.print("150-155cm");
                            }
                            if("13".equals(sg)){
                                out.print("155-160cm");
                            }
                            if("14".equals(sg)){
                                out.print("160-165cm");
                            }
                            if("15".equals(sg)){
                                out.print("165-170cm");
                            }
                            if("16".equals(sg)){
                                out.print("170-175cm");
                            }
                            if("17".equals(sg)){
                                out.print("175-180cm");
                            }
                            if("18".equals(sg)){
                                out.print("180-185cm");
                            }
                            if("19".equals(sg)){
                                out.print("185cm以上");
                            }
                        %>


                    <%
                    if("12".equals(tz)){
                        out.print("90斤以下");
                    }
                    if("13".equals(tz)){
                        out.print("90-100斤");
                    }
                    if("14".equals(tz)){
                        out.print("100-110斤");
                    }
                    if("15".equals(tz)){
                        out.print("110-120斤");
                    }
                    if("16".equals(tz)){
                        out.print("120-130斤");
                    }
                    if("17".equals(tz)){
                        out.print("130-140斤");
                    }
                    if("18".equals(tz)){
                        out.print("140-150斤");
                    }
                    if("19".equals(tz)){
                        out.print("150-160斤");
                    }
                    %>

                <%
                    out.print(address);
                %>
                <%
                    out.print(s2);
                %>
                <%
                    if("12".equals(whcd)){
                        out.print("高中/中专及以下");
                    }
                    if("13".equals(whcd)){
                        out.print("大学/大专");
                    }
                    if("14".equals(whcd)){
                        out.print("硕士研究生");
                    }
                    if("15".equals(whcd)){
                        out.print("博士研究生及以上");
                    }
                %>
                <%
                    if("10".equals(zy)){
                        out.print("专家技术人员");
                    }
                    if("11".equals(zy)){
                        out.print("单位负责人");
                    }
                    if("12".equals(zy)){
                        out.print("办事人员");
                    }
                    if("13".equals(zy)){
                        out.print("销售服务业");
                    }
                    if("14".equals(zy)){
                        out.print("农林牧渔水利业");
                    }
                    if("15".equals(zy)){
                        out.print("生产运输业");
                    }
                    if("16".equals(zy)){
                        out.print("军人");
                    }
                    if("17".equals(zy)){
                        out.print("其他");
                    }
                %>
                <%
                    if("10".equals(user_income)){
                        out.print("年收入:2万元以下");
                    }
                    if("11".equals(user_income)){
                        out.print("年收入:2-5万元");
                    }
                    if("12".equals(user_income)){
                        out.print("年收入:5-10万元");
                    }
                    if("13".equals(user_income)){
                        out.print("年收入:10-100万元");
                    }
                    if("14".equals(user_income)){
                        out.print("年收入:100万元以上");
                    }

                %>
                <%
                    if("10".equals(zf)){
                        out.print("自有住房");
                    }
                    if("11".equals(zf)){
                        out.print("同父母合住");
                    }
                    if("12".equals(zf)){
                        out.print("在外租房");
                    }
                %>

                <%
                    if("10".equals(sx)){
                        out.print("A型");
                    }
                    if("11".equals(sx)){
                        out.print("B型");
                    }
                    if("12".equals(sx)){
                        out.print("AB型");
                    }
                    if("13".equals(sx)){
                        out.print("O型");
                    }
                %>
                <%
                    if("10".equals(xz)){
                        out.print("白羊座");
                    }
                    if("11".equals(xz)){
                        out.print("双子座");
                    }
                    if("12".equals(xz)){
                        out.print("金牛座");
                    }
                    if("13".equals(xz)){
                        out.print("巨蟹座");
                    }
                    if("14".equals(xz)){
                        out.print("狮子座");
                    }
                    if("15".equals(xz)){
                        out.print("处女座");
                    }
                    if("16".equals(xz)){
                        out.print("天秤座");
                    }
                    if("17".equals(xz)){
                        out.print("天蝎座");
                    }
                    if("18".equals(xz)){
                        out.print("射手座");
                    }
                    if("19".equals(xz)){
                        out.print("魔羯座");
                    }
                    if("20".equals(xz)){
                        out.print("双鱼座");
                    }
                    if("21".equals(xz)){
                        out.print("水瓶座");
                    }
                %>
              </div>
        </div>
        <div class="hyzl_nav" id="hyzl_nav">
        <!-- 
           <%if("0".equals(isvcation)){%>
	            <div id="divqs1" class="h_n_on f_l"><li tab="divqs1">已验证</li></div>
	            <div id="divqs2" class="h_n_on  h_n_in  f_r"><li tab="divqs2">未验证</li></div>
           
          <% }else{ %>
           		<div id="divqs1" class="h_n_on h_n_in f_l"><li tab="divqs1">已验证</li></div>
	            <div id="divqs2" class="h_n_on f_r"><li tab="divqs2">未验证</li></div>
            <% } %>
        </div> -->
        
       
         <div class="ssjg_txt">
         
            <%
			 int j = 0;
			 
   Date cdate11 =  DateTools.stringToDate("2010-08-27");
for(j=0;j < qr.resultList.size();j++)
{
   boolean isvery = false;
   Userinfo temp = (Userinfo)qr.resultList.get(j);

   if(temp.getIsvcation()==1)
   	isvery = true;
   Userother userother = HYRegMng.getUserOtherByHyid(temp.getHyid().toString());
   Date csdate = temp.getCsdate();
   Date cdate  = new Date(System.currentTimeMillis());
   String age = "未知";
   if(csdate != null)
     age  = (cdate.getYear()-csdate.getYear())+"";
   String vs1 = (temp.getS1() == null ? "":temp.getS1()) + (temp.getS2() == null ? "":temp.getS2());
   if(vs1.equals("北京北京"))
     vs1 = "北京";
   if(vs1.equals("上海上海"))
     vs1 = "上海";
   if(vs1.equals("天津天津"))
     vs1 = "天津";
   if(vs1.equals("重庆重庆"))
     vs1 = "重庆";
   if(vs1.equals("香港香港"))
     vs1 = "香港";
   if(vs1.equals("澳门澳门"))
     vs1 = "澳门";
   String vjyyx = LoverTools.getJYYXDes(temp);
   String jyzt = LoverTools.getJYZTDes(temp);
  String vhyzk = DicList.getDicValue(SysDefine.DIC_HYZH, temp.getHyzk(), 1);
  %>
         <% if(j==0){   %>
         <ul style="padding-top:20px;">
         <% }else  if((j+1)%3==1){  %>
         <ul>
         <%} %>
         <li> 
         
          <%
               if(userother != null && userother.getUserphoto1() != null && userother.getUserphoto1().length() > 0)
               {
						 String photo = userother.getUserphoto1().replace("\\","/");
           %>
          <!-- <a href="perinfo-id-<%=temp.getHyid()%>.htm" ><img src="<%=afilepathsmall+"/"+photo %>" width="90" height="120" /> --> 
          <a href="perinfo-id-<%=temp.getHyid()%>.htm" ><img src="<%=afilepathsmall+"/"+photo %>" width="90" height="120" />
          
              <%
              }else
              {
              %>
                 <a href="perinfo-id-<%=temp.getHyid()%>.htm" ><img src="./images/nopic2.gif" width="90" height="120"  > 
              <%
                  }
              %>
              <%
               String username = temp.getLcname();
            if(null != username && username.length()>5){
            	username = username.substring(0, 4)+"..";
            }
              
              
               %>
              <span><%=username%></span> <p><%=vs1 %></p><p><%= age %>岁
              <%if (isvery==true) {%>
              	(已验证)
              <%} %>
              </p></a></li>
               
               <% if((j+1)%3==0){  %>
         </ul>
         <%} %>
             <%
                }
             %>
        </div>
        
         
        
    </div>
    
    <div class="clearfix"></div>
    <div class="ssjg_txt1">
    <form id="advsearch" action="searchjg01.jsp" target="_self">
    <input type="hidden" value="<%=isvcation%>" name="isvcation" id="isvcation">
    <input type="hidden" value="<%=sex%>" name="sex">
    <input type="hidden" value="<%=address%>" name="s1">
    <input type="hidden" value="<%=s2%>" name="s2">
    <input type="hidden" value="<%=jyyx%>" name="jyyx">
    <input type="hidden" value="<%=yx%>" name="yx">
    <input type="hidden" value="<%=hyzk%>" name="hyzk">
    <input type="hidden" value="<%=sx%>" name="sx">
    <input type="hidden" value="<%=xz%>" name="xz">
    <input type="hidden" value="<%=xz%>" name="xz">
    <input type="hidden" value="<%=sg%>" name="sg">
    <input type="hidden" value="<%=tz%>" name="tz">
    <input type="hidden" value="<%=fs%>" name="fs">
    <input type="hidden" value="<%=whcd%>" name="whcd">
    <input type="hidden" value="<%=zf%>" name="zf">
    <input type="hidden" value="<%=agestart%>" name="agestart">
    <input type="hidden" value="<%=ageend%>" name="ageend">
    <input type="hidden" value="<%=himg%>" name="himg">
    <input type="hidden" value="<%=zy%>" name="zy">
    <input type="hidden" value="<%=user_income%>" name="user_income">
    <input type="hidden" id="cpages" value="<%=cpages%>" name="cpages">
    <div class="number"  >
     <ul>
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

<%@ include file="bottom2.jsp"%>
<%@ include file="bottom.jsp"%>
<!-- build:js scripts/lib/base.min.js -->
<script type="text/javascript" src="js/lib/zepto.min.js"></script>
<script type="text/javascript" src="js/lib/exp.js"></script>
<!-- endbuild --> 
<script type="text/javascript" src="js/searchjg01.js"></script>
</body>
</html>
