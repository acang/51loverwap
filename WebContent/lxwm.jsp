<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage=""%>
<%@ page import="com.common.SysDefine"%>
<%@ page import="com.web.obj.Userinfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta content="telephone=no" name="format-detection" />
<title>51��������</title>
<meta name="description"
	content="51����������վ-�й�������ҵ���Ʒ�ơ����ܹ�ϵ���������¡�����ѵ����ȫ��λ֧������������ʮ����Ӫ��ǧ���Ա���������飬��51���ѿ�ʼ��" />
<meta name="baidu-site-verification" content="XGXEHLUB1e" />
<meta name="keywords" content="51��������--���ܽ��� ��Ե���� ͬ�ǽ��� ֪������ ��Ů����" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>
<%
	String spp = System.currentTimeMillis() + "";
	session.setAttribute("spp", spp);
	Userinfo loginUser = (Userinfo) session
			.getAttribute(SysDefine.SESSION_LOGINNAME);

	String username = "�ֻ��ο�";
	String userTel = "";
	if (loginUser != null) {
		username = loginUser.getLcname();
		userTel = loginUser.getSjtel();
	}
	
	
%>
<body>

	<div id="header">
		<div class="h_nav">
			<a href="index.jsp"><div class="h_left">
					<img src="images/l.png" />
				</div></a> ��ϵ����
		</div>
	</div>

	<div id="inner">

		<div class="lxwm">
			<div class="lxwm_bg" style="height: 158px;">
				<div class="lxwm_nav">�绰��ϵ</div>
				<a href="tel:400-880-1856"><div class="lxwm_c_an">025-85519991</div></a>
				<a href="tel:0086-025-85519991"><div class="lxwm_c_an">18901580999</div></a>
			</div>


			<div class="lxwm_bg" style="height: 340px; margin-bottom: 40px;">
				<div class="lxwm_nav" style="margin-bottom:5px;">����</div>
				<form name="submitform" id="submitform" method="post"
					action="kfzxadd.jsp">
					<input name="username" type="hidden" value="<%=username%>">
						<input
							name="spp" value="<%=spp%>" type="hidden" /> <input type="hidden"
							name="ntitle" id="ntitle" value="51����������վ����" />


							<div class="lxwm_c" style="margin-bottom:5px;">
								<div class="lxwm_l">�ֻ����룺</div>
								<input name="tel" id="tel" type="tel" class="lxwm_t" value="<%=userTel%>"/>
							</div>
							
							<div class="lxwm_c">
								<div class="lxwm_l">�ظ����䣺</div>
								<input name="email" id="email" type="text" class="lxwm_t" />
							</div>
							<div class="lxwm_c1" style="margin-top:0px;">
								<div class="lxwm_l">�������ݣ�</div>
								<textarea name="ntext" id="ntext" class="lxwm_txt"></textarea>
							</div>
							<div class="clearfix"></div> <input id="boxSubmit" type="button"
							value="�ύ" class="lxwm_an"
							style="float: right; margin-right: 10%;" />
				</form>
				<div style="color: #fff">
					<a href="paymoney_zxltest.jsp"></a>
				</div>
			</div>
		</div>

		<%@ include file="bottom.jsp"%>


	</div>

	<!-- build:js scripts/lib/base.min.js -->
	<script type="text/javascript" src="js/lib/zepto.min.js"></script>
	<script type="text/javascript" src="js/lib/exp.js"></script>
	<!-- endbuild -->
	<script type="text/javascript" src="js/lxwm.js"></script>
</body>
</html>
