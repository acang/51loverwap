<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.common.SysDefine" %>
<%@ page import="com.web.obj.Userinfo" %>
<%@ page import="com.web.obj.Wtjd" %>
<%@ page import="com.web.obj.extend.DicList" %>
<%@ page import="com.web.common.SysCommonFunc" %>
<%@ page import="com.lover.mng.WTJDMng" %>

<%
         //�ж��û�����
        Userinfo grwhqUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
        int flag = 0;
        if(grwhqUser != null && grwhqUser.getFlag() != null && grwhqUser.getFlag().intValue() == SysDefine.SYSTEM_HY_TYPE_vip)
            flag = SysDefine.SYSTEM_HY_TYPE_vip;
            //vip��Ա
        else if (grwhqUser != null && grwhqUser.getFlag() != null && grwhqUser.getFlag().intValue() == SysDefine.SYSTEM_HY_TYPE_nvip)
            flag = SysDefine.SYSTEM_HY_TYPE_nvip;
        else if(grwhqUser != null)
            flag = SysDefine.SYSTEM_HY_TYPE_NOR;

        String memberGrade = "";
        if(flag == SysDefine.SYSTEM_HY_TYPE_NOR)
        {
            memberGrade = "��ͨ��Ա";
        }
        if(flag == SysDefine.SYSTEM_HY_TYPE_vip)
        {
            memberGrade = "�׽�VIP";
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
<title>51��������-���ܽ��� �������� ��Ե���� ͬ�ǽ��� ֪������ ��Ů����</title>
<meta name="description" content="51����������վ-�й�������ҵ���Ʒ�ơ����ܹ�ϵ���������¡�����ѵ����ȫ��λ֧������������ʮ����Ӫ��ǧ���Ա���������飬��51���ѿ�ʼ��" />
   <meta name="baidu-site-verification" content="XGXEHLUB1e" />
<meta name="keywords" content="51��������--���ܽ��� ��Ե���� ͬ�ǽ��� ֪������ ��Ů����" />
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
    <a href="index.jsp"><div class="h_left"><img src="images/l.png" /></div></a>  ���һ�Ա</div>
</div>

<div id="inner">
    <div class="sszl">
        <div class="sszl_nav" id="sszl_nav">
            <div class="s_n_on f_l">
   				<li tab="divss1">��ͨ����</li>         
            </div>
            <div class="s_n_on f_r">
            	<li tab="divss2">�߼�����</li>
            </div>
        </div>
    </div>
    <form id="youkeSearch" name="form2" action="./searchjg01.jsp" method="post"  target="_self">
    <input type="hidden" value="1" name="isvcation">
    <div id="divss1" class="ss">
        <div class="ss_t">
            <div class="ss_tl">�Ա�:</div>
            <div class="ss_tr">
            <select id="sex"  name="sex"> 
            	<option value="0" selected="">����</option> 
            	<option value="10">��</option>
                <option value="11">Ů</option>
             </select>
                <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">���:</div>
            <div class="ss_tr1">
          		<select name="hyzk" size="1" class="border" id="select8">
		            <option selected value="0">����</option>
		            <%=DicList.getDicListOption(SysDefine.DIC_HYZH, "-1")%>
		        </select>
          		<img src="./images/sxl.png" />
            
            </div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">����:</div>
            <div class="ss_tr"><select id="province" name="s1">
                <option value="" selected>��ѡ��</option> <option value=����>����</option> 
                <option value=���>���</option> <option value=�ӱ�>�ӱ�</option> 
                <option value=ɽ��>ɽ��</option> <option value=���ɹ�>���ɹ�</option> 
                <option value=����>����</option> <option value=����>����</option> 
                <option value=������>������</option> <option value=�Ϻ�>�Ϻ�</option> 
                <option value=����>����</option>  <option value=�㽭>�㽭</option> 
                <option value=����>����</option> <option value=����>����</option> 
                <option value=����>����</option> <option value=ɽ��>ɽ��</option> 
                <option value=����>����</option> <option value=����>����</option> 
                <option value=����>����</option> <option value=�㶫>�㶫</option> 
                <option value=����>����</option> <option value=����>����</option> 
                <option value=����>����</option> <option value=�Ĵ�>�Ĵ�</option> 
                <option value=����>����</option> <option value=����>����</option> 
                <option value=����>����</option> <option value=����>����</option> 
                <option value=����>����</option> <option value=�ຣ>�ຣ</option> 
                <option value=����>����</option> <option value=�½�>�½�</option>
            </select> <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">��Ƭ:</div>
            <div class="ss_tr">
				<select name="himg" id="himg">
	                <option selected="selected" value="0">����</option>
	                <option value="1">��</option>
	                <option value="2">��</option>
	            </select>
				<img src="images/sxl.png" />
			</div>
            <div class="clearfix"></div>
        </div>
        
        <div class="clearfix" style="padding-top:50px;"></div>
        <div class="tjan"><input type="submit"  name="��ͨ����"/></div>
        
    </div>
    <input type="hidden" value="1" name="isvcation"/>
    </form>
    
     <div id="divss2" class="ss">
        <div class="ss_t">
            <div class="ss_tl">�Ա�:</div>
            <div class="ss_tr">
	            <select name="sex" size="1" class="border" id="select4">
	                <option selected value="0">����</option>
	                <%=DicList.getDicListOption(SysDefine.DIC_SEX, "-1")%>
	            </select>
            <img src="images/sxl.png" />
            </div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">��������:</div>
            <div class="ss_tr">
            	<select name="jyyx" style="width:100px" class="cg_select">
                	<option value="">����</option>
                    <%=DicList.getDicListOption(SysDefine.DIC_JYYX,"-1")%>
                </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">����:</div>
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
	            </select> ��
	            <select name="ageend" size="1" class="border" id="ageend">
                    <%
                        for(int i=16;i < 71;i ++)
                        {
                    %>
                    <option  value="<%=i%>" <%if(i==70) out.print("selected");%>><%=i%></option>
                    <%
                        }
                    %>
                </select>��
            </div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">��Ƭ:</div>
            <div class="ss_tr">
            	<select name="himg" id="himg">
                    <option selected="selected" value="0">����</option>
                    <option value="1">��</option>
                    <option value="2">��</option>
                </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">Ѫ��:</div>
            <div class="ss_tr">
            	<select name="sx" id="sx">
                  <option selected value="0">����</option>
                  <%=DicList.getDicListOption(SysDefine.DIC_XX,"-1")%>
            	</select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">����:</div>
            <div class="ss_tr">
            	<select name="xz" id="xz">
                  <option selected value="0">����</option>
                  <%=DicList.getDicListOption(SysDefine.DIC_XZ,"-1")%>
            	</select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">���</div>
            <div class="ss_tr">
	            <select name="hyzk" size="1" class="border" id="select8">
	                <option selected value="0">����</option>
	                <%=DicList.getDicListOption(SysDefine.DIC_HYZH,"-1")%>
	            </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">���</div>
            <div class="ss_tr">
	            <select name="sg" size="1" class="border" id="sg">
	                  <option selected value="0">����</option>
	                  <%=DicList.getDicListOption(SysDefine.DIC_SG,"-1")%>
	            </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">����</div>
            <div class="ss_tr">
	            <select name="tz" size="1" class="border" id="select7">
	                  <option selected value="0">����</option>
	                  <%=DicList.getDicListOption(SysDefine.DIC_TZ,"-1")%>
	            </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">��ɫ</div>
            <div class="ss_tr">
	            <select name="fs" size="1" class="border" id="fs">
	                <option selected value="0">����</option>
	                <%=DicList.getDicListOption(SysDefine.DIC_FS,"-1")%>
	            </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">ѧ��</div>
            <div class="ss_tr">
	            <select name="whcd" size="1" class="border" id="whcd">
	                <option selected value="0">����</option>
	                <%=DicList.getDicListOption(SysDefine.DIC_WHCD,"-1")%>
	            </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">ְҵ</div>
            <div class="ss_tr">
	            <select name="zy" size="1" class="border" id="zy">
	                <option selected="selected" value="0">����</option>
	                <%=DicList.getDicListOption(SysDefine.DIC_ZY,"-1")%>
	            </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">��н</div>
            <div class="ss_tr">
	            <select name="user_income" style="width:100px" class="cg_select">
	                <option value="">����</option>
	                <option value="10">2��Ԫ����</option>
	                <option value="11">2-5��Ԫ</option>
	                <option value="12">5-10��Ԫ</option>
	                <option value="13">10-100��Ԫ</option>
	                <option value="14">100��Ԫ����</option>
	        	</select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">ס��</div>
            <div class="ss_tr">
	            <select name="zf" size="1" class="border" id="select5">
	                <option selected value="0">����</option>
	                <%=DicList.getDicListOption(SysDefine.DIC_ZF,"-1")%>
	            </select>
            <img src="images/sxl.png" /></div>
            <div class="clearfix"></div>
        </div>
        
        <div class="ss_t">
            <div class="ss_tl">סַ</div>
            <div class="ss_tr1">
            <select name="s1" id="s1" onChange="SetCity(this,document.personal.s2)">
                   <option value=""
                        selected>��ѡ��</option><option value=����
                    >����</option> <option value=���>���</option> <option
                    value=�ӱ�>�ӱ�</option> <option value=ɽ��>ɽ��</option> <option
                    value=���ɹ�>���ɹ�</option> <option value=����>����</option> <option
                    value=����>����</option> <option value=������>������</option> <option
                    value=�Ϻ�>�Ϻ�</option> <option value=����>����</option> <option
                    value=�㽭>�㽭</option> <option value=����>����</option> <option
                    value=����>����</option> <option value=����>����</option> <option
                    value=ɽ��>ɽ��</option> <option value=����>����</option> <option
                    value=����>����</option> <option value=����>����</option> <option
                    value=�㶫>�㶫</option> <option value=����>����</option> <option
                    value=����>����</option> <option value=����>����</option> <option
                    value=�Ĵ�>�Ĵ�</option> <option value=����>����</option> <option
                    value=����>����</option> <option value=����>����</option> <option
                    value=����>����</option> <option value=����>����</option> <option
                    value=�ຣ>�ຣ</option> <option value=����>����</option> <option
                    value=�½�>�½�</option>

                    <%--<option value=���>���</option>--%>
                    <%--<option value=����>����</option>--%>
                    <%--<option value=̨��>̨��</option>--%>
                    <%--<option value=����>����</option>--%>
            	</select>

                <select name="s2" id="s2">
                    <option value="">��ѡ��</option>
                </select>
            <img src="images/sxl.png" /></span> </div>
            <div class="clearfix"></div>
        </div>
        
        
        <div class="clearfix" style="padding-top:50px;"></div>
        <div class="tjan">�߼�����</div>
        
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
