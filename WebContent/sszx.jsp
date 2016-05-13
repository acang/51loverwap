<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.web.obj.extend.DicList" %>
<%
Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
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
    <script src="js/tom_reg2.js"></script>
</head>
<body class="cm_search_gj">
<%@ include file="head.jsp"%>

<div class="wrapper1250 cm_block01">
  <div class="block01">
    
    <div class="box04">
    <div class="lm_name"><h2>高级搜索</h2></div>
    <form  name="personal" method="post" action="searchjg02.jsp"  target="_blank">
    <div>
        <input type="hidden" name="" value="">
      </div>
      <input type="hidden" name="status" value="searchMoreUsers">
       <table border="0" cellpadding="0" cellspacing="0" class="seach_tab" style="margin-top:30px;margin-left:180px">
          <tr>
        	<td style="width:140px;"><div align="right">性别：</div></td>
            <td style="width:140px;"> <select name="sex" size="1" class="border" id="select4">
                <option selected value="0">不限</option>
                <%=DicList.getDicListOption(SysDefine.DIC_SEX, "-1")%>
            </select></td>
            <td style="width:100px;"><div align="right">常住地址：</div></td>
            <td>
                <select name="s1" id="s1" onChange="SetCity(this,document.personal.s2)">
                   <option value=""
                        selected>请选择</option><option value=北京
                    >北京</option> <option value=天津>天津</option> <option
                    value=河北>河北</option> <option value=山西>山西</option> <option
                    value=内蒙古>内蒙古</option> <option value=辽宁>辽宁</option> <option
                    value=吉林>吉林</option> <option value=黑龙江>黑龙江</option> <option
                    value=上海>上海</option> <option value=江苏>江苏</option> <option
                    value=浙江>浙江</option> <option value=安徽>安徽</option> <option
                    value=福建>福建</option> <option value=江西>江西</option> <option
                    value=山东>山东</option> <option value=河南>河南</option> <option
                    value=湖北>湖北</option> <option value=湖南>湖南</option> <option
                    value=广东>广东</option> <option value=广西>广西</option> <option
                    value=海南>海南</option> <option value=重庆>重庆</option> <option
                    value=四川>四川</option> <option value=贵州>贵州</option> <option
                    value=云南>云南</option> <option value=西藏>西藏</option> <option
                    value=陕西>陕西</option> <option value=甘肃>甘肃</option> <option
                    value=青海>青海</option> <option value=宁夏>宁夏</option> <option
                    value=新疆>新疆</option>

                    <%--<option value=香港>香港</option>--%>
                    <%--<option value=澳门>澳门</option>--%>
                    <%--<option value=台湾>台湾</option>--%>
                    <%--<option value=国外>国外</option>--%>
            </select>

                <select name="s2" id="s2">
                    <option value="">请选择</option>
                </select>
            </td>
          </tr>
          <tr>
            <td><div align="right">交友意向：</div></td>
            <td>
                <select name="jyyx" style="width:100px" class="cg_select">
                	<option value="">不限</option>
                    <%=DicList.getDicListOption(SysDefine.DIC_JYYX,"-1")%>
                </select>
            </td>
            <td><div align="right">学历：</div></td>
            <td>  <select name="whcd" size="1" class="border" id="whcd">
                <option selected value="0">不限</option>
                <%=DicList.getDicListOption(SysDefine.DIC_WHCD,"-1")%>
            </select></td>
          </tr>
          <tr>
            <td><div align="right">年龄：</div></td>
            <td>  <select name="agestart" size="1" class="border" id="agestart">
                <%
                    for(int i=16;i < 71;i ++)
                    {
                %>
                <option  value="<%=i%>" <%if(i==16) out.print("selected");%>><%=i%></option>
                <%
                    }
                %>
            </select>
                到
                <select name="ageend" size="1" class="border" id="ageend">
                    <%
                        for(int i=16;i < 71;i ++)
                        {
                    %>
                    <option  value="<%=i%>" <%if(i==70) out.print("selected");%>><%=i%></option>
                    <%
                        }
                    %>
                </select>
              岁</td>
            <td><div align="right">职业：</div></td>
            <td><select name="zy" size="1" class="border" id="zy">
                <option selected="selected" value="0">不限</option>
                <%=DicList.getDicListOption(SysDefine.DIC_ZY,"-1")%>
            </select></td>
          </tr>
          <tr>
          	<td><div align="right">有无照片：</div></td>
            <td>  <select name="himg" id="himg">
                    <option selected="selected" value="0">不限</option>
                    <option value="1">有</option>
                    <option value="2">无</option>
                </select></td>
            <td><div align="right">年收入：</div></td>
            <td><select name="user_income" style="width:100px" class="cg_select">
                <option value="">不限</option>
                <option value="10">2万元以下</option>
                <option value="11">2-5万元</option>
                <option value="12">5-10万元</option>
                <option value="13">10-100万元</option>
                <option value="14">100万元以上</option>
              </select></td>
          </tr>
          <tr>
          <td><div align="right">婚否：</div></td>
            <td><select name="hyzk" size="1" class="border" id="select8">
                <option selected value="0">不限</option>
                <%=DicList.getDicListOption(SysDefine.DIC_HYZH,"-1")%>
            </select>
            </td>
            <td><div align="right">有无住房：</div></td>
            <td>  <select name="zf" size="1" class="border" id="select5">
                <option selected value="0">不限</option>
                <%=DicList.getDicListOption(SysDefine.DIC_ZF,"-1")%>
            </select></td>
          </tr>
          <tr>


              <td><div align="right">身高：</div></td>
              <td>  <select name="sg" size="1" class="border" id="sg">
                  <option selected value="0">不限</option>
                  <%=DicList.getDicListOption(SysDefine.DIC_SG,"-1")%>
              </select>
              </td>
              <td><div align="right">血型：</div></td>
              <td>  <select name="sx" id="sx">
                  <option selected value="0">不限</option>
                  <%=DicList.getDicListOption(SysDefine.DIC_XX,"-1")%>
              </select>
              </td>
          </tr>
          <tr>


              <td><div align="right">体重：</div></td>
              <td> <select name="tz" size="1" class="border" id="select7">
                  <option selected value="0">不限</option>
                  <%=DicList.getDicListOption(SysDefine.DIC_TZ,"-1")%>
              </select></td>
              <td><div align="right">星座：</div></td>
              <td> <select name="xz" id="xz">
                  <option selected value="0">不限</option>
                  <%=DicList.getDicListOption(SysDefine.DIC_XZ,"-1")%>
              </select></td>
          </tr>
          <tr>

            <td><div align="right">肤色：</div></td>
            <td> <select name="fs" size="1" class="border" id="fs">
                <option selected value="0">不限</option>
                <%=DicList.getDicListOption(SysDefine.DIC_FS,"-1")%>
            </select></td>
              <td></td>
              <td> </td>
          </tr>
      </table>
         <input type="hidden" value="1" name="isvcation">

      <div class="MyBtn" style="clear:both; text-align:left; padding-left:380px;">
      	<input type="button" onclick="tijiaoSerach()" class="btn" value="搜　　索" style="width:200px; height:35px; line-height:35px;" />
      </div>
    </form>
      
    </div>
    
    <div style="clear:both"></div>
  </div>
  <div class="block02">
    <div class="box02">
      <div class="lm_name">
        <h2>扫描二维码</h2>
      </div>
      <div class="erweima"><img src="images/weixin_erweima.jpg" alt="" width="120" height="120"/><br/>官方微信测试中</div>
      <div class="erweima"><img src="images/wap_erweima.jpg" alt="" width="120" height="120"/><br/>手机网站测试中</div>
    </div>
  </div>
  <div style="clear:both"></div>
</div>

<%@ include file="bottom.jsp"%>
<script type="text/javascript">
    function tijiaoSerach(){
        <%
             if(loginUser == null)
          {
              out.println("alert('您是游客，请先登录或注册！');showLogin('sszx.jsp','grzq.htm');");
           }else if(loginUser != null && flag == SysDefine.SYSTEM_HY_TYPE_NOR){
           %>
             alert('您是普通会员，请升级白金VIP会员！');
             window.location.href = "hyzf.htm";
         <%  }else{
        %>
        if(document.getElementById("s1").value==""){
            alert("请选择一个地区!");
            return;
        }
        document.personal.submit();
        <%
        }
        %>
    }
</script>
</body>
</html>
