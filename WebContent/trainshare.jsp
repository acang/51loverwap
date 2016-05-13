<%@ page contentType="text/html; charset=gb2312" language="java" errorPage="" %>
<%@ page import="com.web.obj.Bcb" %>
<%@ page import="com.lover.mng.BCBMng" %>
<%@ page import="com.web.bean.QueryRecord" %>
<%@ page import="com.web.bean.QueryResult" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<%

    String cpages = SysCommonFunc.getStrParameter(request,"cpages");
    int cpage = SysCommonFunc.getNumberFromString(cpages,1);
    String tcounts = SysCommonFunc.getStrParameter(request,"tcounts");
    int tcount  = SysCommonFunc.getNumberFromString(tcounts, 0);

    String type = SysCommonFunc.getStrParameter(request,"type");
    if(type.length() ==0)
    {
        type = "0";
    }
    int pagesize = 6;

    ///得到用户列表
    String sql = "from Wtjd as o where o.wttype =5 and o.stime is not null order by o.stime desc";

    QueryResult qr = null;
    if(tcount > 0)
        qr = QueryRecord.queryByHbm(sql, pagesize, cpage);
    else
        qr = QueryRecord.queryByHbm(sql, pagesize, cpage);
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
    <title>51交友中心</title>
    <meta name="description" content="51交友中心网站-中国交友行业领军品牌。亲密关系、终生伴侣、情商训练，全方位支持您的情感生活。十年运营，千万会员，美满感情，从51交友开始！" />
    <meta http-equiv=”Cache-Control” content=”no-transform” />
    <meta http-equiv=”Cache-Control” content=”no-siteapp” />
    <meta name="keywords" content="51交友中心--亲密交友 情缘交友 同城交友 知己交友 美女交友" />
    <link href="css/css.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/lcmbase.js"></script>
    <style type="text/css">
        <!--
        .STYLE2 {color: #21648F}
        -->
    </style>
</head>
<body class="cm_gsjj">
<%@ include file="head.jsp"%>
<div class="wrapper1250 cm_block01">
    <div class="block01">
        <div class="lm_name"><h2>情商训练</h2></div>
        <ul>

            <li><a href="train.jsp?id=1773368">训练介绍</a></li>
            <li><a href="train.jsp?id=1773369">训练图片</a></li>
            <li><a href="trainshare.jsp" target="_self" class='current'>训练分享</a></li>
        </ul>
    </div>
    <div class="block02">
        <div class="lm_name">
            <h2>训练分享</h2>
        </div>
        <ul class="news">
            <%
                for(int i=0;i < qr.resultList.size();i ++)
                {
                    Wtjd wtjd1 = (Wtjd)qr.resultList.get(i);
                    String cdate = "";
                              Date cdatee=wtjd1.getStime();
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
                    cdate=sdf.format(cdatee)+"&nbsp;&nbsp";
                    wtjd1 = WTJDMng.getWtjdFromid(wtjd1.getId()+"");
                    Bcb bcb1 = null;
                    if(wtjd1 != null && wtjd1.getAnswer() != null)
                        bcb1 = BCBMng.getBcbFromid(wtjd1.getAnswer().toString());
                    String s = bcb1 == null? "":SysCommonFunc.getStringFromBlob(bcb1.getContent());
            %>
            <div class="box03">
                <table width="100%">
                <tr>
                    <td width="5%" align="right"><img src="images/dhk.jpg"></td>
                    <td width="95%" style="color:#999;"><%=wtjd1.getAsk()%></td>
                </tr>
                <tr>
                    <td style= "border-bottom:1px solid #ddd;"></td>
                    <td style= "border-bottom:1px solid #ddd;"><%=s%></td>
                </tr>
            </table>
            </div>
            <%
                }
            %>
        </ul>
        <div style="height:30px"></div>

        <div class="Paging" style="height:40px;">
                 <%
                    int startpage = 0;
                    if((cpage-5) <=0)
                        startpage = 1;
                    else
                        startpage = cpage-5;

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
    </div>
    <div style="clear:both"></div>
</div>
<%@ include file="bottom.jsp"%>
</body>
<form id="pageform" name="turn" method="Post" action="trainshare.jsp" target="_self">
    <input type="hidden" name="cpages" value="<%=cpage%>" />
    <input type="hidden" name="tcounts" value="<%=qr.rowCount%>">
</form>

<script>
    function turn(a){
        pageform.cpages.value=a;
        pageform.submit();
    }
</script>
</html>
