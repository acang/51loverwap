<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.web.obj.Bcb" %>
<%@ page import="com.web.obj.Wtjd" %>
<%@ page import="com.web.common.SysCommonFunc" %>
<%@ page import="com.lover.mng.WTJDMng" %>
<%@ page import="com.lover.mng.BCBMng" %>
<%
    String id = SysCommonFunc.getStrParameter(request, "id");
    Wtjd wtjd1 = null;
    Bcb bcb1  = null;
    wtjd1 = WTJDMng.getWtjdFromid(id);
    bcb1 = null;
    if(wtjd1 != null && wtjd1.getAnswer() != null)
        bcb1 = BCBMng.getBcbFromid(wtjd1.getAnswer().toString());
    String s = bcb1 == null? "":SysCommonFunc.getStringFromBlob(bcb1.getContent());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<style type="text/css">
    <!--
    .STYLE2 {color: #21648F}
    -->
</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>51��������</title> 
    <meta name="description" content="51����������վ-�й�������ҵ���Ʒ�ơ����ܹ�ϵ���������¡�����ѵ����ȫ��λ֧������������ʮ����Ӫ��ǧ���Ա���������飬��51���ѿ�ʼ��" />
    <meta http-equiv=��Cache-Control�� content=��no-transform�� />
    <meta http-equiv=��Cache-Control�� content=��no-siteapp�� />
    <meta name="keywords" content="51��������--���ܽ��� ��Ե���� ͬ�ǽ��� ֪������ ��Ů����" />
    <link href="css/css.css" rel="stylesheet" type="text/css" />
</head>
<body class="cm_gsjj">
<div class="wrapper1250 cm_block01">
    <div class="block02" style="float: left">

    </div>
</div>
</body>
</html>