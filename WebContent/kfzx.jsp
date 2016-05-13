<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
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
.STYLE1 {font-weight: bold}
-->
</style>
<%
	String spp = System.currentTimeMillis()+"";
    session.setAttribute("spp",spp);
 %>
 <script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
<script language="javascript">
function checkspace(checkstr) {


  var str = '';
  for(i = 0; i < checkstr.length; i++) {
    str = str + ' ';
  }
  return (str == checkstr);
}

function check()
{
    var nts = document.getElementsByName("nt");
    var nts_val = "咨询";
    for(var i=0;i<nts.length;i++){
              if(nts[i].checked == true){
                  nts_val =nts[i].value;
              }
    }
    document.submitform.ntitle.value= nts_val;
 if (checkspace(document.submitform.username.value))
{
alert('请输入姓名');
document.submitform.username.focus();
return false;
}


 if (checkspace(document.submitform.tel.value))
{
alert('请输入联系电话');
document.submitform.tel.focus();
return false;
}

 if (checkspace(document.submitform.email.value))
{
alert('请准确填写您的邮箱，以便收到回复！');
document.submitform.email.focus();
return false;
}


// if (checkspace(document.submitform.ntitle.value))
//{
//alert('请输入标题');
//document.submitform.ntitle.focus();
//
//return false;
//}

      if   (document.submitform.ccc.value=='0')
          {
		   document.submitform.submit.disabled;
           document.submitform.ccc.value='1';
          return   true;}
      else
      {
	history.go(0);
	alert("请刷新后重新输入!");
          return   false;}

}
function chanasdf(){
	var aa=document.getElementById("email").value;
	var index=aa.indexOf("@");
	var bb=aa.substr(index);
   }
</script>
</head>
<body class="cm_gsjj">
<%@ include file="head.jsp"%>

<div class="wrapper1250 cm_block01">
  <div class="block01">
    <div class="lm_name">
      <h2>关于我们</h2>
    </div>
    <ul>
      <li><a href="introduce.jsp?id=1773446" >网站介绍</a></li>
	  <li><a href="introduce.jsp?id=1773447">网站证照</a></li>
      <li><a href="aboutus.jsp">网站动态</a></li>
      <li><a href="kfzx.htm" class="current">联系我们</a></li>
    </ul>
  </div>
  <div class="block02">
    <div class="lm_name">
      <h2>联系我们</h2>
    </div>
    <div class="content">
    <strong>公司地址：</strong>江苏省南京市沿江工业开发区科创中心6号楼B栋&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>邮证编码：</strong>210009 <br />
    <strong>全国客服电话:</strong>025-85519991&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>联系电话：</strong>18901580999&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>邮箱：</strong>service@51lover.org</div>
    <div class="lm_name">
      <h2>留言板</h2>
      ( 需回复的用户请准确填写您的邮箱，我们会在1个工作日内回复您！) </div>
    <form name="submitform" method="post" action="kfzxadd.jsp" onSubmit="return check()">
    <input name="spp" value="<%=spp%>" type="hidden"/>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="contactlist_tab">
        <tbody>
          <tr>
            <td width="150" align="right"><strong>留言人姓名：</strong></td>
            <td width="160"><input name="username" id="username" type="text" class="cg_input" style="width:150px;"></td>
            <td width="98" align="right"><div align="right" class="STYLE1">联系电话：</div></td>
            <td width="160"><input name="tel" type="text" class="cg_input" style="width:150px;"></td>
            <td id="liuyan"  align="right"><strong>回复邮箱：</strong></td>
            <td><input name="email" id="email" type="text" class="input_long" maxlength="50" value=""></td>
          </tr>
          <tr>
            <td align="right"><span class="red"></span><strong>留言分类：</strong></td>
            <td colspan="5" class="radiochek">
                <input type="hidden" name="ntitle" id="ntitle" value=""/>
             <input  type="radio" value="咨询" name="nt" checked="checked"><span>咨询</span>
			<input  type="radio" value="升级" name="nt"><span>升级</span>
			<input  type="radio" value="情商训练" name="nt"><span>情商训练</span>
			<input  type="radio" value="建议" name="nt"><span>建议</span>
			<input type="radio" value="合作" name="nt"><span>合作</span>
			<input type="radio" value="投诉" name="nt"><span>投诉</span>
			<input  type="radio" value="其他" name="nt"><span>其他</span>
            </td>
          </tr>
          <tr>
            <td align="right"><strong>留言内容：</strong><br></td>
            <td colspan="5"><textarea name="ntext" style="width:92%;" rows="8"></textarea></td>
          </tr>
        </tbody>
      </table>
      <div class="MyBtn">
          <input type="submit" name="button" class="btn" value="提  交"  style="width:200px; height:30px; line-height:30px;" />
      </div>
    </form>
  </div>
  <div style="clear:both"></div>
</div>
<%@ include file="bottom.jsp"%>
</body>
</html>
