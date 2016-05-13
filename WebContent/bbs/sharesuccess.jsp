<%@ page contentType="text/html; charset=gb2312" language="java"  errorPage="" %>
<%@ page import="com.web.bean.QueryRecord" %>
<%@ page import="com.web.bean.QueryResult" %>
<%@ page import="com.web.common.SysCommonFunc" %>
<%@ page import="hibernate.db.HbmOperator" %>
<%@ page import="com.lover.mng.BBSMng" %>
<%@ page import="com.lover.mng.HYRegMng" %>
<%@ page import="com.lover.mng.UserVisitMng" %>
<%@ page import="com.web.obj.*" %>
<%@ page import="com.lover.mng.BCBMng" %>
<%@ page import="com.common.SysDefine" %>
<%@ page import="com.web.common.DateTools" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.beanutils.DynaBean" %>
<%@ page import="java.io.File" %>
<%@ page import="com.weixin.util.WeixinCreateSign" %>
<%@ page import="com.web.obj.Sharearticle" %>
<%@ page import="com.lover.mng.SharearticleMng" %>
<%@ page import="com.web.obj.Readarticle" %>
<%@ page import="com.lover.mng.ReadarticleMng" %>
<%@ page import="java.util.Vector" %>
<%@ page import="hibernate.db.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<%
       	Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
		String articleid = request.getParameter("articleid");
		String topictitle = request.getParameter("topictitle");
		//out.println("<script language='javascript'>alert('"+request.getRequestURL()+articleid+topictitle+"');</script>");
       	if(loginUser!=null){
    		Long articleID=Long.parseLong(articleid);
	    	Long hyId=loginUser.getHyid();
	    	String lcName = loginUser.getLcname();
	    	String userName = loginUser.getUsername();
			String tempsql = "select count(*) from sharearticle where articleid ="+articleID 
					+" and hyid = "+hyId;
	   		int count = QueryRecord.queryCount(tempsql);
	   		if(count<=0){
	    		Sharearticle sharearticle = new Sharearticle(articleID,hyId,0L,lcName,userName,new Date(),topictitle,0L,0L);
	        	SharearticleMng.addSharearticle(sharearticle);
    		}
			Vector saveList = new Vector();
	   		Bbsuser tempBuser = BBSMng.getBbsuserByHyid(loginUser.getHyid().toString());
			if(tempBuser==null){
				tempBuser = new Bbsuser();
				tempBuser.setCommend(new Long(0));
				tempBuser.setGrade(new Long(0));
				tempBuser.setHyid(loginUser.getHyid());
				tempBuser.setLcname(loginUser.getLcname());
				tempBuser.setMoneys(new Long(0));
				tempBuser.setRegtime(loginUser.getRegtime());
				tempBuser.setResum(new Long(0));
				tempBuser.setStaytime(new Long(0));
				tempBuser.setTopics(new Long(0));
				tempBuser.setUsername(loginUser.getUsername());
				tempBuser.setReplynum(new Long("0"));
				tempBuser.setMoneynew(new Long("0"));
				tempBuser.setSharenumber(new Long(1));
				MutSeaObject mso = new MutSeaObject();
				mso.setHbmSea(tempBuser, mso.SEA_HBM_INSERT);
				saveList.add(mso);
				HbmOperator.SeaMutDataWithBlob(saveList);
	    	}else{//update
	    		 if(count<=0){
		    		try
		    	    {
		    	        HbmOperator.executeSql("update bbsuser set sharenumber = sharenumber+1 where hyid = " + hyId);
		    	    }catch(Exception e){}
	    		 }
	    	}
    	}
       	out.println("<script language='javascript'>window.history.back(-1);</script>");
   %>
</html>
