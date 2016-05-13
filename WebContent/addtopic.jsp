<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="/FCKeditor.tld.tld" prefix="FCK"%>
<%@ page import="com.web.common.SysCommonFunc" %>
<%@ page import="com.common.SysDefine" %>
<%@ page import="com.web.obj.Userinfo" %>
<%@ page import="com.lover.mng.BBSMng" %>
<%
    Userinfo loginUser = (Userinfo) session
            .getAttribute(SysDefine.SESSION_LOGINNAME);

    String bizaction = SysCommonFunc.getStrParameter(request,
            "bizaction");
    String sortid = SysCommonFunc.getStrParameter(request, "sortid");
    String isfb = SysCommonFunc.getStrParameter(request, "isfb");
    String content = SysCommonFunc.getStrParameter(request, "content");
    String title = SysCommonFunc.getStrParameter(request, "title");
    if (bizaction.equals("01")) {
        if (loginUser == null) {
            out.println("<script language='javascript'>alert('您是游客，您的权限不够，请先登录或注册');parent.showLogin()</script>");
            return;
        }

        String s = null;
        if (content != null ){
            s = BBSMng.userAddTopic(request, loginUser, content,sortid, title);
        }


        if (s == null) {
            out.println("<script langauge=javascript>alert('您发表的主题正在审核中');window.close()</script>");
            title = "";
            isfb = "isfb";
            content = "";
        } else
            out.println("<script langauge=javascript>alert('" + s
                    + "！');</script>");

    }

    String contextPath = request.getContextPath();
    String basePath = contextPath + "/commonlib/fckeditor/";
    String skinPath = contextPath
            + "/commonlib/fckeditor/editor/skins/office2003/";
    String imageBrowserURL = contextPath
            + "/commonlib/fckeditor/editor/filemanager/browser/default/browser.jsp?Type=Image&Connector=connectors/jsp/connector";
    String linkBrowserURL = contextPath
            + "/commonlib/fckeditor/editor/filemanager/browser/default/browser.jsp?Connector=connectors/jsp/connector";
    String flashBrowserURL = contextPath
            + "/commonlib/fckeditor/editor/filemanager/browser/default/browser.jsp?Type=Flash&Connector=connectors/jsp/connector";
    String imageUploadURL = contextPath
            + "/fckeditor/editor/filemanager/upload/simpleuploader?Type=Image";
    String linkUploadURL = contextPath
            + "/fckeditor/editor/filemanager/upload/simpleuploader?Type=File";
    String flashUploadURL = contextPath
            + "/fckeditor/editor/filemanager/upload/simpleuploader?Type=Flash";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>51交友中心</title>
    <meta name="description" content="51交友中心-—中国AAA大型情感类交友网站。会员遍布北京交友，广东交友，上海交友，江苏交友等全国各大省市。51交友中心稳健专业、快捷超值,已积累400多万情感交友会员。" />
    <meta http-equiv=”Cache-Control” content=”no-transform” />
    <meta http-equiv=”Cache-Control” content=”no-siteapp” />
    <meta name="keywords" content="51交友中心--亲密交友 情缘交友 同城交友 知己交友 美女交友" />
    <link href="css/css.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/lcmbase.js"></script>
    <script language="javascript">
        function check()
        {
            title = document.form1.title.value;
            var editor = FCKeditorAPI.GetInstance("content");
            var con = editor.EditorDocument.body.innerHTML;
            if(con.indexOf("www.") >= 0 ||con.indexOf(".net") >= 0
                    ||con.indexOf(".cn") >= 0 || con.indexOf(".com") >= 0
                    || con.indexOf("ftp://") >= 0 || con.indexOf("http://") >= 0
                    || con.indexOf(".org") >= 0||con.indexOf("<a href") >= 0)
            {
                alert("发贴请不要带网址链接！");
                return false;
            }
            if(title == "")
            {
                alert("标题不能为空!");
                return false;
            }
            isfb = document.form1.isfb.value;
            if(!(isfb == ""))
            {
                alert("请不要重复发布!");
                return false;
            }

            return true;
        }
    </script>

</head>
<body class="cm_bbs" style="text-align: center">
<div class="wrapper1250 cm_block01">
<div class="block01">
    <div class="box03">
        <div class="lm_name">
            <h2>发布主题帖</h2>
        </div>
        <form name="form1" method="post" action="addtopic.jsp"
              onSubmit=" return check();">
            <input type="hidden" name="bizaction" value="01">
            <input type="hidden" name="sortid" value="<%=sortid%>">
            <input type="hidden" name="isfb" value="<%=isfb%>" />
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="80" align="right">帖子主题：</td>
                    <td align="left"><input name="title" type="text"
                               size="35" class="txt01" value="<%=title%>"> </td>
                </tr>
                <tr>
                    <td align="right" valign="top">帖子内容：</td>
                    <td style="padding-left:10px;">
                        <FCK:editor id="content"
                                    basePath="<%=basePath%>"
                                    skinPath="<%=skinPath%>" width="100%"
                                    height="260" toolbarSet="ALLARTICLE"
                                    imageBrowserURL="<%=imageBrowserURL%>"
                                    linkBrowserURL="<%=linkBrowserURL%>"
                                    flashBrowserURL="<%=flashBrowserURL%>"
                                    imageUploadURL="<%=imageUploadURL%>"
                                    linkUploadURL="<%=linkUploadURL%>"
                                    flashUploadURL="<%=flashUploadURL%>"><%=content%> </FCK:editor>  </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td style="height:60px;">
                        <a href="javascript:yulan()" style="background-color:#21648f; height:40px; line-height:40px; text-align:center; color:#FFF; width:100px; display:inline-block">预&nbsp;&nbsp;&nbsp;览</a>
                        <a href="javascript:fabu()" style="background-color:#21648f; height:40px; line-height:40px; text-align:center; color:#FFF; width:150px; display:inline-block">发布主题帖</a></td>
                </tr>
            </table>
        </form>
    </div>
    <div style="clear:both"></div>
</div>
</div>
  <script type="text/javascript">
      function fabu(){
          document.form1.action = "addtopic.jsp";
          document.form1.target = "";
          document.form1.submit();
      }
      function yulan(){
          document.form1.action = "bbs_preview.jsp";
          document.form1.target = "_blank";
          document.form1.submit();
      }
  </script>
</body>