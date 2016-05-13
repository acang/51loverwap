<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.web.obj.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.web.common.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.lover.mng.*"%>

<%
//未做功能，判断各种情况包括好友设置等 没有根据状态判断是否显示详细联系方式按钮
Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
String number= SysCommonFunc.getStrParameter(request,"number");
String hyid = SysCommonFunc.getStrParameter(request,"id");
if(hyid.length() ==0)
{
  out.println("<script language='javascript'>alert('会员信息不存在！');window.close();</script>");
  return;
}

if(SysCommonFunc.getNumberFromString(hyid,0)==0)
{
      out.println("<script language='javascript'>alert('会员信息不存在！');window.close();</script>");

   System.out.println("sql注入"+hyid);
   return;
}

Userinfo hyInfo = HYRegMng.getUserinfoByHyid(hyid);
if(hyInfo ==null)
{
  out.println("<script language='javascript'>alert('会员信息不存在！');window.close();</script>");
  return;
}

Userother uother = HYRegMng.getUserOtherByHyid(hyid);

          String jyzt ="";
          if(hyInfo.getSetzt().intValue() == SysDefine.SYSTEM_HYSET_HYZT_WAIT)
            jyzt = "等待中";
          else if(hyInfo.getSetzt().intValue() == SysDefine.SYSTEM_HYSET_HYZT_NOT_WAIT)
            jyzt = "暂勿打扰";
          String jyyx01 = "00000000";
          if(hyInfo.getJyyx() != null && hyInfo.getJyyx().toString().length() ==8)
             jyyx01 = hyInfo.getJyyx().toString();
          String jyyx = "";
          for(int i=0;i < 7;i ++)
          {
             if(jyyx01.charAt(i+1) == '1')
               jyyx = jyyx + SysDefine.jyyxArray[i]+"&nbsp;&nbsp;";
          }

String afilepath = SysCommonFunc.getPhotoView();
String afilepathsmall = SysCommonFunc.getPhotoSmallView();

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title><%=hyInfo.getLcname()%>-个人影集-51交友中心</title>
<META content="<%=uother == null ? "":uother.getJyly()%> <%=uother == null ? "":uother.getQggx()%>" name="description">
<META content="<%=jyyx%>，影集文集，<%=hyInfo.getS1() == null? "":hyInfo.getS1()%>交友，<%=hyInfo.getLcname()%>" name="keywords">

<script>
<!--

function MM_openBrWindow1(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}

function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->


function click() {
alert('禁止复制！') }
function click1() {
if (event.button==2) {alert('禁止复制！') }}
function CtrlKeyDown(){
if (event.ctrlKey) {alert('禁止复制！') }}
document.onkeydown=CtrlKeyDown;
document.onselectstart=click;
document.onmousedown=click1;


</script>
</head>

<body onpaste="return false;" onselectstart="return false;" oncopy="return false;" oncut="return false;">

                        <%
                        if(number.equals("1"))
                        {
                        %>
           <a href="getphoto.jsp?number=1&id=<%=hyid%>"><img src="../<%=afilepath+File.separator+uother.getUserphoto1()%>" border="0"></a>
                       <%
                        }
                       %>
                       
                        <%
                        if(number.equals("2"))
                        {
                        %>
           <a href="getphoto.jsp?number=2&id=<%=hyid%>"><img src="../<%=afilepath+File.separator+uother.getUserphoto2()%>" border="0"></a>
                       <%
                        }
                       %>
                        <%
                        if(number.equals("3"))
                        {
                        %>
           <a href="getphoto.jsp?number=3&id=<%=hyid%>"><img src="../<%=afilepath+File.separator+uother.getUserphoto3()%>" border="0"></a>
                       <%
                        }
                       %>
                        <%
                        if(number.equals("4"))
                        {
                        %>
           <a href="getphoto.jsp?number=4&id=<%=hyid%>"><img src="../<%=afilepath+File.separator+uother.getUserphoto4()%>" border="0"></a>
                       <%
                        }
                       %>
                        <%
                        if(number.equals("5"))
                        {
                        %>
           <a href="getphoto.jsp?number=5&id=<%=hyid%>"><img src="../<%=afilepath+File.separator+uother.getUserphoto5()%>" border="0"></a>
                       <%
                        }
                       %>
</body>
</html>

