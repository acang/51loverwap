<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.common.SysDefine" %>
<%@ page import="com.web.obj.Userinfo" %>
<%@ page import="com.web.obj.Wtjd" %>
<%@ page import="com.web.obj.extend.DicList" %>
<%@ page import="com.web.common.SysCommonFunc" %>
<%@ page import="com.lover.mng.WTJDMng" %>

<%
         //判断用户类型
        Userinfo grwhqUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
        int flag = 0;
        if(grwhqUser != null && grwhqUser.getFlag() != null && grwhqUser.getFlag().intValue() == SysDefine.SYSTEM_HY_TYPE_vip)
            flag = SysDefine.SYSTEM_HY_TYPE_vip;
            //vip会员
        else if (grwhqUser != null && grwhqUser.getFlag() != null && grwhqUser.getFlag().intValue() == SysDefine.SYSTEM_HY_TYPE_nvip)
            flag = SysDefine.SYSTEM_HY_TYPE_nvip;
        else if(grwhqUser != null)
            flag = SysDefine.SYSTEM_HY_TYPE_NOR;

        String memberGrade = "";
        if(flag == SysDefine.SYSTEM_HY_TYPE_NOR)
        {
            memberGrade = "普通会员";
        }
        if(flag == SysDefine.SYSTEM_HY_TYPE_vip)
        {
            memberGrade = "白金VIP";
        }
        String login2 = System.currentTimeMillis()+"";
        session.setAttribute("login",login2);
        session.setAttribute("sp",login2);
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
<title>51交友中心-亲密交友 浪漫交友 情缘交友 同城交友 知己交友 美女交友</title>
<meta name="description" content="51交友中心网站-中国交友行业领军品牌。亲密关系、终生伴侣、情商训练，全方位支持您的情感生活。十年运营，千万会员，美满感情，从51交友开始！" />
   <meta name="baidu-site-verification" content="XGXEHLUB1e" />
<meta name="keywords" content="51交友中心--亲密交友 情缘交友 同城交友 知己交友 美女交友" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script> 
<script type="text/javascript" src="js/wapLover.js"></script> 
<script src="js/tom_reg2.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $.jqtab("#sszl_nav",".ss","s_n_in");
});
</script> 
</head>

<body>

<div id="header">
    <div class="h_nav"> 
    <a href="index.jsp"><div class="h_left"><img src="images/l.png" /></div></a>  查找会员</div>
</div>

<div id="inner">
    <div class="sszl">
        <div class="sszl_nav" id="sszl_nav">
            <div class="s_n_on f_l">
   				<li tab="divss1">普通搜索</li>         
            </div>
            <div class="s_n_on f_r">
            	<li tab="divss2">高级搜索</li>
            </div>
        </div>
    </div>
    <form id="youkeSearch" name="form2" action="./searchjg01.jsp" method="post"  target="_self">
    <input type="hidden" value="1" name="isvcation">
    <div id="divss1" class="ss">
        <div class="ss_t">
            <div class="ss_tl">性别:</div>
            <div class="ss_tr">
            <select id="sex"  name="sex"> 
            	<option value="0" selected="">不限</option> 
            	<option value="10">男</option>
                <option value="11">女</option>
             </select>
                <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">婚否:</div>
            <div class="ss_tr1">
          		<select name="hyzk" size="1" class="border" id="select8">
		            <option selected value="0">不限</option>
		            <%=DicList.getDicListOption(SysDefine.DIC_HYZH, "-1")%>
		        </select>
          		<img src="./images/sxl.png" />
            
            </div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">地区:</div>
            <div class="ss_tr"><select id="province" name="s1">
                <option value="" selected>请选择</option> <option value=北京>北京</option> 
                <option value=天津>天津</option> <option value=河北>河北</option> 
                <option value=山西>山西</option> <option value=内蒙古>内蒙古</option> 
                <option value=辽宁>辽宁</option> <option value=吉林>吉林</option> 
                <option value=黑龙江>黑龙江</option> <option value=上海>上海</option> 
                <option value=江苏>江苏</option>  <option value=浙江>浙江</option> 
                <option value=安徽>安徽</option> <option value=福建>福建</option> 
                <option value=江西>江西</option> <option value=山东>山东</option> 
                <option value=河南>河南</option> <option value=湖北>湖北</option> 
                <option value=湖南>湖南</option> <option value=广东>广东</option> 
                <option value=广西>广西</option> <option value=海南>海南</option> 
                <option value=重庆>重庆</option> <option value=四川>四川</option> 
                <option value=贵州>贵州</option> <option value=云南>云南</option> 
                <option value=西藏>西藏</option> <option value=陕西>陕西</option> 
                <option value=甘肃>甘肃</option> <option value=青海>青海</option> 
                <option value=宁夏>宁夏</option> <option value=新疆>新疆</option>
            </select> <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">照片:</div>
            <div class="ss_tr">
				<select name="himg" id="himg">
	                <option selected="selected" value="0">不限</option>
	                <option value="1">有</option>
	                <option value="2">无</option>
	            </select>
				<img src="images/sxl.png" />
			</div>
            <div class="clearfix"></div>
        </div>
        
        <div class="clearfix" style="padding-top:50px;"></div>
        <div class="tjan"><input type="submit"  name="普通搜索"/></div>
        
    </div>
    <input type="hidden" value="1" name="isvcation"/>
    </form>
    
     <div id="divss2" class="ss">
        <div class="ss_t">
            <div class="ss_tl">性别:</div>
            <div class="ss_tr">
	            <select name="sex" size="1" class="border" id="select4">
	                <option selected value="0">不限</option>
	                <%=DicList.getDicListOption(SysDefine.DIC_SEX, "-1")%>
	            </select>
            <img src="images/sxl.png" />
            </div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">交友意向:</div>
            <div class="ss_tr">
            	<select name="jyyx" style="width:100px" class="cg_select">
                	<option value="">不限</option>
                    <%=DicList.getDicListOption(SysDefine.DIC_JYYX,"-1")%>
                </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">年龄:</div>
            <div class="ss_tr1">
            	<select name="agestart" size="1" class="border" id="agestart">
                <%
                    for(int i=16;i < 71;i ++)
                    {
                %>
                <option  value="<%=i%>" <%if(i==16) out.print("selected");%>><%=i%></option>
                <%
                    }
                %>
	            </select> 到
	            <select name="ageend" size="1" class="border" id="ageend">
                    <%
                        for(int i=16;i < 71;i ++)
                        {
                    %>
                    <option  value="<%=i%>" <%if(i==70) out.print("selected");%>><%=i%></option>
                    <%
                        }
                    %>
                </select>岁
            </div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">照片:</div>
            <div class="ss_tr">
            	<select name="himg" id="himg">
                    <option selected="selected" value="0">不限</option>
                    <option value="1">有</option>
                    <option value="2">无</option>
                </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">血型:</div>
            <div class="ss_tr">
            	<select name="sx" id="sx">
                  <option selected value="0">不限</option>
                  <%=DicList.getDicListOption(SysDefine.DIC_XX,"-1")%>
            	</select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">星座:</div>
            <div class="ss_tr">
            	<select name="xz" id="xz">
                  <option selected value="0">不限</option>
                  <%=DicList.getDicListOption(SysDefine.DIC_XZ,"-1")%>
            	</select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">婚否</div>
            <div class="ss_tr">
	            <select name="hyzk" size="1" class="border" id="select8">
	                <option selected value="0">不限</option>
	                <%=DicList.getDicListOption(SysDefine.DIC_HYZH,"-1")%>
	            </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">身高</div>
            <div class="ss_tr">
	            <select name="sg" size="1" class="border" id="sg">
	                  <option selected value="0">不限</option>
	                  <%=DicList.getDicListOption(SysDefine.DIC_SG,"-1")%>
	            </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">体重</div>
            <div class="ss_tr">
	            <select name="tz" size="1" class="border" id="select7">
	                  <option selected value="0">不限</option>
	                  <%=DicList.getDicListOption(SysDefine.DIC_TZ,"-1")%>
	            </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">肤色</div>
            <div class="ss_tr">
	            <select name="fs" size="1" class="border" id="fs">
	                <option selected value="0">不限</option>
	                <%=DicList.getDicListOption(SysDefine.DIC_FS,"-1")%>
	            </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">学历</div>
            <div class="ss_tr">
	            <select name="whcd" size="1" class="border" id="whcd">
	                <option selected value="0">不限</option>
	                <%=DicList.getDicListOption(SysDefine.DIC_WHCD,"-1")%>
	            </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">职业</div>
            <div class="ss_tr">
	            <select name="zy" size="1" class="border" id="zy">
	                <option selected="selected" value="0">不限</option>
	                <%=DicList.getDicListOption(SysDefine.DIC_ZY,"-1")%>
	            </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">年薪</div>
            <div class="ss_tr">
	            <select name="user_income" style="width:100px" class="cg_select">
	                <option value="">不限</option>
	                <option value="10">2万元以下</option>
	                <option value="11">2-5万元</option>
	                <option value="12">5-10万元</option>
	                <option value="13">10-100万元</option>
	                <option value="14">100万元以上</option>
	        	</select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">住房</div>
            <div class="ss_tr">
	            <select name="zf" size="1" class="border" id="select5">
	                <option selected value="0">不限</option>
	                <%=DicList.getDicListOption(SysDefine.DIC_ZF,"-1")%>
	            </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">住址</div>
            <div class="ss_tr1">
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
            <img src="images/sxl.png" /></span> </div>
            <div class="clearfix"></div>
        </div>
        
        
        <div class="clearfix" style="padding-top:50px;"></div>
        <div class="tjan">高级搜索</div>
        
    </div>
    <%@ include file="bottom2.jsp"%>
   <%@ include file="bottom.jsp"%>
    
</div>

<!-- build:js scripts/lib/base.min.js -->
<script type="text/javascript" src="js/lib/zepto.min.js"></script>
<script type="text/javascript" src="js/lib/exp.js"></script>
<!-- endbuild --> 
<script type="text/javascript" src="js/btm.js"></script>
</body>
</html>
