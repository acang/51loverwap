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
        int flag = 5;
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
<title>51��������</title>
<meta name="description" content="51����������վ-�й�������ҵ���Ʒ�ơ����ܹ�ϵ���������¡�����ѵ����ȫ��λ֧������������ʮ����Ӫ��ǧ���Ա���������飬��51���ѿ�ʼ��" />
   <meta name="baidu-site-verification" content="XGXEHLUB1e" />
<meta name="keywords" content="51��������--���ܽ��� ��Ե���� ͬ�ǽ��� ֪������ ��Ů����" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script> 
<script type="text/javascript" src="js/search.js"></script>  

<script type="text/javascript">
<%
if(flag == SysDefine.SYSTEM_HY_TYPE_vip)
{%>
	$(document).ready(function() {
	    $.jqtab("#sszl_nav",".ss","s_n_in",1);
	});
<%}else{ %>
	 $(document).ready(function() {
	     $.jqtab("#sszl_nav",".ss","s_n_in",0);
	 });
<%}%>

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
            <div id="search2"  class="s_n_on f_r">
            	<li tab="divss2">�߼�����</li>
            </div>
        </div>
    </div>
   
    <form id="youkeSearch" name="form2" action="./searchjg01.jsp" method="post"  target="_self">
    <!-- <input type="hidden" value="1" name="isvcation"> -->
    <div id="divss1" class="ss">
         <div class="m-form">
			 <div class="ss_t item">
			      <div class="ss_tl">�Ա�</div>
			      <dl class="m-select" id="sex1">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="sex">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div>  
        
         <div class="item ss_t">
			      <div class="ss_tl">���</div>
			      <dl class="m-select" id="Marriage1">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="hyzk">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div>  
			    
			     <div class="item ss_t">
			      <div class="ss_tl">��Ƭ��</div>
			      <dl class="m-select" id="photo1">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="himg">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div>  
			    
			     <div  class="item ss_t">
			      <div class="ss_tl">������</div>
			      <dl class="m-select" id="area1">
			        <dt>��ѡ��</dt>
			        <dd>
			          <input type="hidden" id="s11"  name="s1">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div>  
			    <div class="item ss_t">
			      <div class="ss_tl">��֤��</div>
			      <dl class="m-select" id="isvcation1">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="isvcation">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div> 
      </div>     
        
        <div class="clearfix" style="padding-top:50px;"></div>
        <div class="btn tjan" id="ptss">����</div>
     </div>
    </form>
    
      <form id="personal" name="form2" action="./searchjg01.jsp" method="post"  >
       <input type="hidden" name="status" value="searchMoreUsers">
        <!-- <input type="hidden" value="1" name="isvcation">-->
       <div id="divss2" class="ss">
       
      <div class="ss_t item">
			      <div class="ss_tl">�Ա�</div>
			      <dl class="m-select" id="sex2">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="sex">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div>  
        
         <div class="item ss_t">
			      <div class="ss_tl">���</div>
			      <dl class="m-select" id="Marriage2">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="hyzk">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div>  
			    
			     <div class="item ss_t">
			      <div class="ss_tl">��Ƭ��</div>
			      <dl class="m-select" id="photo2">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="himg">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div>  
			     
			     <div class="item ss_t">
			      <div class="ss_tl">Ŀ�ģ�</div>
			      <dl class="m-select" id="jyyx">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="jyyx">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div> 
			      
			    
			     <div class="item ss_t">
			      <div class="ss_tl">���䣺</div>
				      <dl class="m-select m-select-w" id="agestart">
				        <dt></dt>
				        <dd>
				          <input type="hidden" name="agestart" value="24">
				        </dd>
				      </dl>
				      <dl class="m-select m-select-w" id="ageend">
				        <dt></dt>
				        <dd>
				          <input type="hidden" name="ageend" value="28">
				        </dd>
				      </dl>
			      <div class="clearfix"></div>
			    </div>  
       
        <div class="item ss_t">
			      <div class="ss_tl">ѧ����</div>
			      <dl class="m-select" id="whcd">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="whcd">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div>  
			    
		<div class="item ss_t">
			      <div class="ss_tl">ְҵ��</div>
			      <dl class="m-select" id="zy">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="zy">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div>  
        
       <div class="item ss_t">
			      <div class="ss_tl">Ѫ�ͣ�</div>
			      <dl class="m-select" id="sx">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="sx">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div>  
           
            <div class="item ss_t">
			      <div class="ss_tl">������</div>
			      <dl class="m-select" id="xz">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="xz">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div>  
            
            <div class="item ss_t">
			      <div class="ss_tl">��ߣ�</div>
			      <dl class="m-select" id="sg">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="sg">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div>  
         
          
            <div class="item ss_t">
			      <div class="ss_tl">���أ�</div>
			      <dl class="m-select" id="tz">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="tz">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div> 
         
         <div class="item ss_t">
			      <div class="ss_tl">��ɫ��</div>
			      <dl class="m-select" id="fs">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="fs">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div> 
           
         
         <div class="item ss_t">
			      <div class="ss_tl">��н��</div>
			      <dl class="m-select" id="user_income">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="user_income">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div> 
			    
			   
         <div class="item ss_t">
			      <div class="ss_tl">ס����</div>
			      <dl class="m-select" id="zf">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="zf">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div> 
		 
			        
         <div class="item ss_t">
			      <div class="ss_tl">סַ��</div>
			      <dl class="m-select m-select-w" id="zzstart">
			        <dt>��ѡ��</dt>
			        <dd>
			          <input type="hidden" id="s1" name="s1">
			        </dd>
			      </dl>
			      <dl class="m-select m-select-w" id="zzend">
			        <dt>��ѡ��</dt>
			        <dd>
			          <input type="hidden" id="s2" name="s2">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div> 
			     <div class="item ss_t">
			      <div class="ss_tl">��֤��</div>
			      <dl class="m-select" id="isvcation">
			        <dt>����</dt>
			        <dd>
			          <input type="hidden" name="isvcation">
			        </dd>
			      </dl>
			      <div class="clearfix"></div>
			    </div> 
        
        <div class="clearfix" style="padding-top:50px;"></div>
         <%if(grwhqUser == null)
         { %>
         	<div class="btn  tjan" id="gjss">�߼�����</div>
         <%}else if(flag == SysDefine.SYSTEM_HY_TYPE_vip)
         { %>
           <div class="btn  tjan" id="vipss">�߼�����</div>
         <% }else{ %>
           <div class="btn  tjan" id="hyss">�߼�����</div>
           <% } %>
        </form>
    </div>
   <%@ include file="bottom.jsp"%>
    

<!-- build:js scripts/lib/base.min.js -->
<script type="text/javascript" src="js/lib/zepto.min.js"></script>
<script type="text/javascript" src="js/lib/exp.js"></script>
<!-- endbuild -->  
<script type="text/javascript" src="js/hyss.js"></script>
    
</body>
</html>
