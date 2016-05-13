<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.web.obj.Bcb" %>
<%@ page import="com.lover.mng.BCBMng" %>
<%@ page import="com.web.bean.QueryRecord" %>
<%@ page import="com.web.bean.QueryResult" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.web.common.SysCommonFunc" %>
<%@ page import="com.web.obj.Wtjd" %>
<%@ page import="com.lover.mng.WTJDMng" %>
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
    //得到用户列表
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

<%
	String id = SysCommonFunc.getStrParameter(request, "id");
    if(id==null || "".equals(id)){
      id="1773368";
    }
    Wtjd wtjd1 = null;
    Bcb bcb1  = null;
    wtjd1 = WTJDMng.getWtjdFromid(id);
    bcb1 = null;
    if(wtjd1 != null && wtjd1.getAnswer() != null)
        bcb1 = BCBMng.getBcbFromid(wtjd1.getAnswer().toString());
    String s = bcb1 == null? "":SysCommonFunc.getStringFromBlob(bcb1.getContent());
    String pic = "1773369";
    Wtjd wtjd2 = null;
    Bcb bcb2  = null;
    wtjd2 = WTJDMng.getWtjdFromid(pic);
    bcb2 = null;
    if(wtjd2 != null && wtjd2.getAnswer() != null)
        bcb2 = BCBMng.getBcbFromid(wtjd2.getAnswer().toString());
    String pics = bcb2 == null? "":SysCommonFunc.getStringFromBlob(bcb2.getContent());
    String picContent = SysCommonFunc.getStringFromBlob(bcb2.getContent());;
    if(picContent != null ){
     picContent = picContent.replace("/UserFiles/Image","http://www.51lover.org/UserFiles/Image");
     picContent = picContent.replace("width: 245px;","width: 263px;");
     picContent = picContent.replace("width: 242px;","width: 263px;");
     picContent = picContent.replace("width: 264px;","width: 263px;");
     picContent = picContent.replace("width: 249px;","width: 263px;");
     picContent = picContent.replace("width: 239px;","width: 263px;");
     picContent = picContent.replace("width: 262px;","width: 263px;");
     picContent = picContent.replace("width: 265px;","width: 263px;");
     picContent = picContent.replace("width: 255px;","width: 263px;");
     picContent = picContent.replace("width: 246px;","width: 263px;");
     
     picContent = picContent.replace("height: 177px","height: 179px");
     picContent = picContent.replace("height: 178px","height: 179px");
     picContent = picContent.replace("height: 181px","height: 179px");
     picContent = picContent.replace("height: 182px","height: 179px");
     picContent = picContent.replace("height: 183px","height: 179px");
     picContent = picContent.replace("height: 184px","height: 179px");
     picContent = picContent.replace("height: 185px","height: 179px");
     
     picContent = picContent.replace("<br>","");
     picContent = picContent.replace("&nbsp;&nbsp;&nbsp;&nbsp;","");
     picContent = picContent.replace("&nbsp;&nbsp;&nbsp;","");
     picContent = picContent.replace("    ","");
    }
    
    //由于情商训练内容保存数据库中，无法在页面进行处理，只能暂时用代码处理。add by linyu 2015.09
    if(s != null ){
     s = s.replace("<p><span style=\"font-size: 12pt\">","<p><div style='font-size: 12pt' align='left'>");
     s = s.replace("</span></p>","</div></p>");
    }
    
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
<meta name="description" content="51交友中心网站?中国交友行业领军品牌。亲密关系、终生伴侣、情商训练，全方位支持您的情感生活。十年运营，千万会员，美满感情，从51交友开始！" />
<meta name="keywords" content="51交友中心--亲密交友 情缘交友 同城交友 知己交友 美女交友" />
<link rel="stylesheet" type="text/css" href="css/style.css">
</body>
</head>

<body>
<div id="header">
<div class="h_nav"> 
    <a href="index.jsp"><div class="h_left"><img src="images/l.png" /></div></a>
                      情商训练</div>
</div>
<div id="inner">
    <div class="qsxl">
        <div class="qsxl_nav" id="qsxl_nav">
			<div class="q_n_on f_l bodo_r">
				<li tab="divqs1">训练介绍</li>
			</div>
            <div class="q_n_on f_l bodo_r">
				<li tab="divqs2">训练图片</li>
			</div>
            <div id="tab3" class="q_n_on f_l">
				<li tab="divqs3">训练分享</li>
			</div>
        </div>
    </div>
                
    <div   id="qsxl_msg">
		<div id="divqs1" class="qsxl_txt">
			<div class="qsxl_js">
				<%=s%>
			</div>
		</div>
		<div id="divqs2" class="qsxl_txt">
			<div class="qsxl_tj">
				<%=bcb2 == null? "": picContent%>
			</div>
		</div>
		<div id="divqs3" class="qsxl_txt">
				<%
                for(int i=0;i < qr.resultList.size();i ++)
                {
                    Wtjd wtjd3 = (Wtjd)qr.resultList.get(i);
                    String cdate = "";
                              Date cdatee=wtjd3.getStime();
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
                    cdate=sdf.format(cdatee)+"&nbsp;&nbsp";
                    wtjd3 = WTJDMng.getWtjdFromid(wtjd3.getId()+"");
                    Bcb bcb3 = null;
                    if(wtjd3 != null && wtjd3.getAnswer() != null)
                        bcb3 = BCBMng.getBcbFromid(wtjd3.getAnswer().toString());
                    String fxs = bcb3 == null? "":SysCommonFunc.getStringFromBlob(bcb3.getContent());
            	%>
            	<ul>
		            <li>
		                <h4><%=wtjd3.getAsk()%></h4>
		                <h5><%=fxs%></h5>
		            </li>
				</ul>
            <%
                }
            %>
		</div>
    </div>
</div>
<%@ include file="bottom2.jsp"%>
<%@ include file="bottom.jsp"%>

<!-- build:js scripts/lib/base.min.js -->
<script type="text/javascript" src="js/lib/zepto.min.js"></script>
<script type="text/javascript" src="js/lib/exp.js"></script>
<!-- endbuild --> 
<script type="text/javascript" src="js/btm.js"></script>
<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script> 
<script type="text/javascript" src="js/wapLover.js"></script> 
<script type="text/javascript">
$(document).ready(function() {
    $.jqtab("#qsxl_nav",".qsxl_txt","q_n_in");
});
</script> 
</html>