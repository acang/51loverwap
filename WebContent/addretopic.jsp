<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/FCKeditor.tld.tld" prefix="FCK" %>
<%@ page import="com.web.obj.*"%>
<%@ page import="com.common.*"%>
<%@ page import="com.web.common.*"%>
<%@ page import="com.lover.mng.*"%>

<%
    Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
    String bizaction = SysCommonFunc.getStrParameter(request,"bizaction");
    String bbsid = SysCommonFunc.getStrParameter(request,"bbsid");
    String isfb   = SysCommonFunc.getStrParameter(request,"isfb");
    String content = SysCommonFunc.getStrParameter(request,"content");
    
    if(bizaction.equals("01"))
    {
        if(loginUser == null)
        {
            out.println("<script language='javascript'>alert('您是游客，您的权限不够，请先登录或注册');parent.showLogin('disp_bbs.jsp?bbsid="+bbsid+"','disp_bbs.jsp?bbsid="+bbsid+"');</script>");
            return;
        }

        String mailreg = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";
        String preg    = "\\d{6,}";

        String s = null;
        if(content == null)
            content = "";
        String tempc=content.replaceAll("<p>","");
        tempc = tempc.replaceAll("</p>","");
        if(tempc == null || tempc.length() < 10)
        {

            s = "回帖请多于10个字！";
        }
        else
        {
            java.util.regex.Pattern pattern1 = java.util.regex.Pattern.compile(mailreg,java.util.regex.Pattern.CASE_INSENSITIVE);
            java.util.regex.Pattern pattern2 = java.util.regex.Pattern.compile(preg,java.util.regex.Pattern.CASE_INSENSITIVE);
            if(pattern1.matcher(content).matches() || pattern2.matcher(content).matches()){
                s = "回帖中有疑似联系方式，请重新填写";
            }else{
                s = BBSMng.userAddReTopic(request,loginUser,content,bbsid);
            }


        }

		String url = SysCommonFunc.getStrParameter(request,"url");
        if(s == null)
        {
        	
            out.println("<script langauge=javascript>alert('发送成功');</script>");
            out.println("<script langauge=javascript>location.href = '"+url+"';</script>");
            isfb = "isfb";
            content = "";
        }
        else
            out.println("<script langauge=javascript>alert('"+s+"！');</script>");
             out.println("<script langauge=javascript>location.href = '"+url+"';</script>");

    }

%>

 
