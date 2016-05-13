<%@ page contentType="text/html; charset=GBK" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.web.common.SysCommonFunc" %>

<%

	 String isAddFavorite = SysCommonFunc.getStrParameter(request,"isAddFavorite");
    if(isAddFavorite != null && "1".equals(isAddFavorite))
    {
       request.getSession().setAttribute("isAddFavorite",
    		   isAddFavorite);
    }
     
%>