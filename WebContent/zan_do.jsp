<%@ page import="com.web.common.SysCommonFunc" %>
<%@ page import="com.lover.mng.BBSMng" %>
<%@ page contentType="text/html; charset=gb2312" %>

<%
    String bbsReId = SysCommonFunc.getStrParameter(request, "bbsReId");
    String bbsid = SysCommonFunc.getStrParameter(request, "bbsid");
    String userId = SysCommonFunc.getStrParameter(request, "userId");
    String selfId = SysCommonFunc.getStrParameter(request, "selfId");
    String result= BBSMng.userAddZan(request,bbsReId,bbsid,userId,selfId);
    if("ok".equals(result)){
        out.println("<script language='javascript'>window.parent.location.href='bbs/disp_bbs-bbsid-"+bbsid+".htm';</script>");
    }else{
        out.println("<script language='javascript'>alert('"+result+"');window.parent.location.href='bbs/disp_bbs-bbsid-"+bbsid+".htm';</script>");

    }
%>