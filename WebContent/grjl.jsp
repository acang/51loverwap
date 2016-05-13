<%@ page contentType="text/html; charset=GBK" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.web.obj.Userother" %>
<%@ page import="com.web.obj.Userinfo" %>
<%@ page import="com.lover.mng.UserVisitMng" %>
<%@ page import="com.web.common.SysCommonFunc" %>
<%@ page import="java.io.File" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>51交友中心-亲密交友 浪漫交友 同城交友 终生伴侣 情商训练 婚恋训练</title>
<meta name="description" content="51交友中心网站-中国交友行业领军品牌。亲密关系、终生伴侣、情商训练，全方位支持您的情感生活。十年运营，千万会员，美满感情，从51交友开始！" />
<meta http-equiv=”Cache-Control” content=”no-transform” />
<meta http-equiv=”Cache-Control” content=”no-siteapp” />
<meta name="keywords" content="51交友中心-亲密交友 浪漫交友 同城交友 终生伴侣 情商训练 婚恋训练" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
    <script>
        function selectTagHy(showContent,selfObj){
            // 操作标签
            var tag = document.getElementById("personTab").getElementsByTagName("span");
            for(var i=0; i<3; i++){
                tag[i].className = "";
            }
            selfObj.className = "current";
            // 操作内容
            for(var i=0; i<3; i++){
                j=document.getElementById("pertagContent"+i);
                j.style.display = "none";
            }
            document.getElementById(showContent).style.display = "block";
          }


        function changeClass(a){
            if(a.className!="current"){
                a.className = "current";
            }
        }
        function recoverClass(a){
            if(a.className =="current"){
                a.className = "";
            }
            for(var i=0; i<3; i++){
                var j=document.getElementById("pertagContent"+i);
                if(j.style.display == "block"){
                    var tag = document.getElementById("personTab").getElementsByTagName("span");
                    tag[i].className = "current";
                }

            }
        }
        </script>
</head>
<body class="cm_grzx">
<%@ include file="head.jsp"%>
<%
    Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
    Userother uother = (Userother)session.getAttribute(SysDefine.SESSION_LOGINNAME_OTHER);
    if(loginUser == null)
    {
        out.println("<script language='javascript'>alert('登录已超时，请重新登录！');history.go(-1);</script>");
        return;
    }else{
        String vurl="";
        vurl=request.getRequestURL().toString();
        if (request.getQueryString()!=null){
            vurl+="?"+request.getQueryString();
        }
        UserVisitMng.insertUserVisit(loginUser.getHyid(), vurl);
    }

    String cpages = SysCommonFunc.getStrParameter(request, "cpages");
    int cpage = SysCommonFunc.getNumberFromString(cpages,1);
    String tcounts = SysCommonFunc.getStrParameter(request,"tcounts");
    int tcount  = SysCommonFunc.getNumberFromString(tcounts,0);
    int pagesize = 20;
%>
<%
    String afilepath = SysCommonFunc.getPhotoView();
    String afilepathsmall = SysCommonFunc.getPhotoSmallView();

%>

<div class="wrapper1250 cm_block01">
  <div class="block01">
      <div class="box01"> <span class="tx">
            <%
                if(uother!=null && uother.getUserphoto1()!=null && !"".equals(uother.getUserphoto1())){
                    String left_small_pic ="../"+afilepathsmall+File.separator+uother.getUserphoto1();
            %>
      <img src="<%=left_small_pic%>" alt=""/>
       <%
       }else{
       %>
          <img src="images/nopic2.gif" alt=""/>
       <%
           }
       %>
      </span>
          <p class="zl"><strong><%=grwhqUser.getLcname()%></strong></p>
          <div class="lm_name">
              <h2>个人操作专区</h2>
          </div>
          <%@ include file="grleft_bjd.jsp"%>
      </div>
    <div class="box02">
      <div class="lm_name">
        <h2>推广奖励</h2>
      </div>
      <div style="clear:both"></div>
      <div class="tab" id="personTab">
          <span class="current" onClick="selectTagHy('pertagContent0',this)" onmouseover="changeClass(this)" onmouseout="recoverClass(this)">推广奖励办法</span>
          <span onClick="selectTagHy('pertagContent1',this)" onmouseover="changeClass(this)" onmouseout="recoverClass(this)">分享记录</span>
          <span onClick="selectTagHy('pertagContent2',this)" onmouseover="changeClass(this)" onmouseout="recoverClass(this)">白金豆使用情况</span>
		</div>
      <div class="mingdan show" id="pertagContent0">
          <iframe width="100%" height="670px" src="tgjlbf.jsp" name="tgjl_frame" frameborder="0"></iframe>

      </div>
      <div class="mingdan" id="pertagContent1">
          <iframe width="100%" height="670px" src="fxjl.jsp" name="tgjl_frame" frameborder="0" ></iframe>

      </div>
      <div class="mingdan"  id="pertagContent2">
          <iframe width="100%" height="670px" src="bjdsyqk.jsp" name="tgjl_frame" frameborder="0"></iframe>

      </div>
	</div>
	<div style="clear:both"></div>
  </div>
  <div class="block02">
    <div class="box02">
      <div class="lm_name">
        <h2>扫描二维码</h2>
      </div>
      <div class="erweima"><img src="images/weixin_erweima.jpg" alt="" width="120" height="120"/><br/>官方微信</div>
      <div class="erweima"><img src="images/wap_erweima.jpg" alt="" width="120" height="120"/><br/>手机网站</div>
    </div>
  </div>
  <div style="clear:both"></div>
</div>
<%@ include file="bottom.jsp"%>
</body>
</html>
