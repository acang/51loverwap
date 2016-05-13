<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>
<%@ page import="com.web.bean.QueryResult" %>
<%@ page import="com.web.common.SysCommonFunc" %>
<%@ page import="com.lover.mng.UserVisitMng" %>
<%@ page import="com.lover.LoverTools" %>
<%@ page import="com.web.bean.QueryRecord" %>
<%@ page import="com.web.obj.extend.DicList" %>
<%@ page import="com.lover.mng.HYRegMng" %>
<%@ page import="com.web.common.DateTools" %>
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
    if(isvcation==null){
        isvcation = "0";
    }
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
    int pagesize = 10;

    ///得到用户列表
    String sql = "from Userinfo as o where o.isdel = 0  and o.isvcation = "+isvcation;
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

    //10天，45天，4个月，1年半

//    String stemp = "";
//    int lday = 0;
//    if(lltime.equals("1"))
//    {
//        lday = 10;
//        stemp = "(最近一周登录)";
//    }
//    if(lltime.equals("2"))
//    {
//        lday = 45;
//        stemp = "(最近一月登录)";
//    }
//    if(lltime.equals("3"))
//    {
//        lday = 120;
//        stemp = "(最近三月登录)";
//    }
//    if(lltime.equals("4"))
//    {
//        lday = 540;
//        stemp = "(最近一年登录)";
//    }
//    if(lday > 0){
//        sql = sql + " and o.lasttime > sysdate-"+lday;
//    }


//    if(sex.length() > 1 && sex.equals("11") || address.equals("上海") || address.equals("广东") || address.equals("天津")){
//      //  sql = sql+" order by o.img desc,o.flag desc,o.lasttime desc";
//        sql = sql+" order by o.regtime5 desc";
//    }else{
//       // sql = sql+" order by o.img desc,o.lasttime desc";
//
//    }

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
    <title>51交友中心</title>
    <meta name="description" content="51交友中心--中国顶级情感类交友网站。会员遍布北京交友,广东交友,上海交友,江苏交友等全国各大省市。51交友中心稳健专业、快捷超值,已积累1000多万情感交友会员。" />
    <meta http-equiv=”Cache-Control” content=”no-transform” />
    <meta http-equiv=”Cache-Control” content=”no-siteapp” />
    <meta name="keywords" content="51交友中心--亲密交友 情缘交友 同城交友 知己交友 美女交友" />
    <link href="css/css.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="js/jquery.js"></script>
</head>
<body class="cm_search">
<%@ include file="head.jsp"%>
<div class="wrapper1250 cm_block01">
    <div class="lm_name">
        <h2>搜索结果</h2>
    </div>
    <div class="block01">
        <div class="jieguo">
            <strong>您要找的是：</strong>
            <p>
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
                        out.print("130-140");
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
                        out.print("机关企事业负责人");
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
          </p>
            <div style="clear:both"></div>
        </div>
        <div class="tab_name">

            <span  id="searchTab0" class="current" onClick="selectTag('searchTab0','1')">已验证会员</span>
            <span  id="searchTab1" onClick="selectTag('searchTab1','0')">未验证会员</span>

        </div>
        <div style="clear:both"></div>
        <ul class="show_ul">
                <%
			 int j = 0;
for(j=0;j < qr.resultList.size();j++)
{
   boolean isvery = false;
   Userinfo temp = (Userinfo)qr.resultList.get(j);
   Date cdate11 =  DateTools.stringToDate("2010-08-27");

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
            <li>
                <span class="tx">

                    <%
                        if(userother != null && userother.getUserphoto1() != null && userother.getUserphoto1().length() > 0)
                        {

                    %>
                   <a href="perinfo-id-<%=temp.getHyid()%>.htm" target="_blank" class="new_link06"><img src="../<%=afilepathsmall+File.separator+userother.getUserphoto1() %>" width="90" height="120" /></a>
                       <%
                       }else
                       {
                       %>
                          <a href="perinfo-id-<%=temp.getHyid()%>.htm" target="_blank" class="new_link06"><img src="images/nopic2.gif" width="90" height="120" border="0"></a>
                       <%
                           }
                       %>
                </span>
                <a href="perinfo-id-<%=temp.getHyid()%>.htm" target="_blank" class="name"><%=temp.getLcname()%></a><span class="yyz"><%if(isvery) {%>已验证<%}%></span><br />
                <%=temp.getSex().equals("11") ? "女" : "男" %>, <%= age %>岁,<%=vs1 %>
                <%
                    String ly="";
                    if(userother != null && userother.getJyly() != null && userother.getJyly().length()>50){
                        ly = userother.getJyly().substring(0,50);
                    }else if(userother != null && userother.getJyly() != null && userother.getJyly().length()>0 &&userother.getJyly().length()<=50 ){
                        ly = userother.getJyly();
                    }else{
                        ly = "";
                    }
                     if(ly!=null && !"".equals(ly)){
                         if(ly.length()>30){
                             ly =ly.substring(0,30);
                         }
                     }

                %>
                <p class="txt02"> <strong>交友留言：</strong><%=ly%></p>
                <%
                    if(loginUser==null){
                %>
                 <a href="javascript:alert('您是游客，请先登录或注册');showLogin('searchjg02.jsp','grzq.jsp');"  class="btn">
                    加TA为好友</a>&nbsp;
                <%
                    }else{
                 %>
                <a href="addhymd.jsp?bizaction=1&hyid=<%=temp.getHyid()%>" target="searchframe" class="btn">
                    加TA为好友</a>&nbsp;
                <%
                    }
                %>
                   <%
                   if(loginUser==null){
                   %>
                      <a href="javascript:alert('您是游客，请先登录或注册');showLogin('searchjg02.jsp','grzq.jsp');" class="btn">与TA联系</a>
                  <%
                  }else if(temp.getSetzt() != null && temp.getSetzt().intValue() == SysDefine.SYSTEM_HYSET_HYZT_NOT_WAIT){
                  %>
                <a href="javascript:alert('该会员资料交友状态设置为暂无打扰！');" class="btn">与TA联系</a>
                <%
                  }
                  else if(loginUser!=null && (flag == SysDefine.SYSTEM_HY_TYPE_nvip || flag == SysDefine.SYSTEM_HY_TYPE_vip)){
                   %>
                <a href="perinfoSearch-id-<%=temp.getHyid()%>.htm" target="_blank" class="btn">与TA联系</a>
                <%
                      }else if(loginUser!=null && flag == SysDefine.SYSTEM_HY_TYPE_NOR){
                %>
                <a href="javascript:alert('您是普通会员，请升级为白金VIP会员！');window.open('hyzf.htm');void(0);" class="btn">与TA联系</a>
                <%
                     }
                %>
            </li>
            <%
                }
            %>
        </ul>
        <div style="clear:both"></div>
        <div class="Paging">
            <%
                if(cpage == 1)
                    out.println("<a href='###'>首    页</a><a href='###'>上一页</a>");
                else
                {
                    out.println("<a href='javascript:turn(1)'>首    页</a>");
                    out.println("<a href='javascript:turn("+prepage+")'>上一页</a>");
                }
                int startpage = 0;
                int endpage = 12;
                if(cpage>=12){
                    startpage = cpage-5;
                    endpage =(cpage+7)>totalPage?totalPage:(cpage+7);
                }
                 if(totalPage<12){
                     endpage =totalPage;
                 }
                for(int i=startpage;i<endpage;i++){
                    if((i+1)==Integer.parseInt(cpages)){
                        out.println("<a href='javascript:turn("+(i+1)+")' class='Check'>"+(i+1)+"</a>");
                    }else{
                        out.println("<a href='javascript:turn("+(i+1)+")'>"+(i+1)+"</a>");
                    }

                }

                if(cpage >= totalPage ){
//                    out.println("<a href='###'>下一页</a><a href='###'>尾页</a>");
                }

                else
                {
                    out.println("<a href='javascript:turn("+nextpage+")'>下一页</a>");
//                    out.println("<a href='javascript:turn("+totalPage+")'>尾页</a>");
                }
            %>
            <%--<a>共计:<%=totalCount%>位</a>--%>
            <div style="clear:both;"></div>
        </div>
    </div>
    <div class="block02">
        <div class="lm_name02">
            <h2>普通会员搜索</h2>
        </div>
        <div class="erweima"><img src="images/weixin_erweima.jpg" alt="" width="120" height="120"/><br/>官方微信测试中</div>
        <div class="erweima"><img src="images/wap_erweima.jpg" alt="" width="120" height="120"/><br/>手机网站测试中</div>
        <div style="clear:both"></div>
    </div></div>
<div style="clear:both"></div>
<form id="advsearch" action="searchjg02.jsp" target="_self">
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
    <input type="hidden" value="<%=cpages%>" name="cpages">

 </form>
<%@ include file="bottom.jsp"%>
<iframe name="searchframe" frameborder="0"></iframe>
</body>
 <script>
function selectTag(a,b){
// 操作标签
for(i=0; i<2; i++){
var tag = document.getElementById("searchTab"+i);
tag.className = "";
}
 document.getElementById(a).className = "current";
    advsearch.isvcation.value = b;
 document.getElementById("advsearch").submit();
}

function turn(a){
    advsearch.cpages.value = a;
    document.getElementById("advsearch").submit();
}



     <%
     if("0".equals(isvcation)){
     %>
        for(i=0; i<2; i++){
            var tag = document.getElementById("searchTab"+i);
            tag.className = "";
        }
        document.getElementById("searchTab1").className = "current";
     <%
     }
     %>

</script>
</html>
