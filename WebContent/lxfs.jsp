<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.web.common.SysCommonFunc" %>
<%@ page import="hibernate.db.HbmOperator" %>
<%@ page import="com.web.bean.QueryResult" %>
<%@ page import="com.web.bean.QueryRecord" %>
<%@ page import="com.web.common.DateTools" %>
<%@ page import="com.web.obj.extend.DicList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.web.obj.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.lover.LoverTools" %>
<%@ page import="org.apache.commons.beanutils.DynaBean" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.io.File" %>
<%@ page import="com.lover.mng.*" %>
<%@ page import="com.common.*" %>

<%
    Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
    String ask = loginUser.getAsk();
    String isRel = "0";
    if(ask == null || "".equals(ask)){
    	out.println("<script language='javascript'>alert('�����ڵ��Զ���վwww.51lover.org�������ĸ������ϣ��Ա�ӵ�����ĸ���ר���������䣬�յ��ظ����ԣ�');</script>");
        isRel = "1";
    }
    
    String userid = SysCommonFunc.getStrParameter(request,"id");
    Userinfo hyInfo = HYRegMng.getUserinfoByHyid(userid);
    String username = "";
    if(hyInfo == null)
    {
        out.println("<script language='javascript'>alert('��Ա��Ϣ�����ڣ�'); window.parent.location.replace('index.jsp');</script>");
        return;
    }else{
    	username =hyInfo.getLcname();
    }
    
    boolean isHy = false;
    if(loginUser!=null){
        isHy = GRZQMng.isHy(hyInfo,SysDefine.SYSTEM_HYGL_FRIEND,loginUser);
    }
    Hyset hyset = GRZQMng.getHyset(hyInfo);
    if(hyset == null)
    {
        hyset = new Hyset();
        hyset.setSetbase(new Integer(SysDefine.SYSTEM_HYSET_OPEN_ALL));
        hyset.setSetpic(new Integer(SysDefine.SYSTEM_HYSET_OPEN_ALL));
        hyset.setSettel(new Integer(SysDefine.SYSTEM_HYSET_OPEN_VIP));
        hyset.setSetsjtel(new Integer(SysDefine.SYSTEM_HYSET_OPEN_VIP));
        hyset.setSetqq(new Integer(SysDefine.SYSTEM_HYSET_OPEN_VIP));
        hyset.setSetmail(new Integer(SysDefine.SYSTEM_HYSET_OPEN_VIP));
        hyset.setHyid(hyInfo.getHyid());
        try
        {
            HbmOperator.insert(hyset);
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    String tel = hyInfo.getTel() == null ? "":hyInfo.getTel();
    if(hyset.getSettel() != null && hyset.getSettel().intValue() == SysDefine.SYSTEM_HYSET_OPEN_FRIEND && !isHy)
        tel = "������ѣ���Ͽ���ϵ�Ұɣ�";
    String oicq = hyInfo.getOicq() == null ? "":hyInfo.getOicq();
    if(hyset.getSettel() != null && hyset.getSetqq().intValue() == SysDefine.SYSTEM_HYSET_OPEN_FRIEND && !isHy)
        oicq = "������ѣ���Ͽ���ϵ�Ұɣ�";
    String sjtel = hyInfo.getSjtel() == null ? "":hyInfo.getSjtel();
    if(hyset.getSetsjtel() != null && hyset.getSetsjtel().intValue() == SysDefine.SYSTEM_HYSET_OPEN_FRIEND && !isHy)
        sjtel = "������ѣ���Ͽ���ϵ�Ұɣ�";
    String email = hyInfo.getEmail() == null ? "":hyInfo.getEmail();
    if(hyset.getSetmail() != null && hyset.getSetmail().intValue() == SysDefine.SYSTEM_HYSET_OPEN_FRIEND && !isHy)
        email = "������ѣ���Ͽ���ϵ�Ұɣ�";
    String wx = hyInfo.getWx() == null ? "":hyInfo.getWx();
    if(hyset.getSetwx() != null && hyset.getSetwx().intValue() == SysDefine.SYSTEM_HYSET_OPEN_FRIEND && !isHy)
        wx = "������ѣ���Ͽ���ϵ�Ұɣ�";
%>

<%
// ��ͨ��Ա�����ܽ��лظ�
  if(loginUser == null)
  {
    out.println("<script language='javascript'>alert('��¼�ѳ�ʱ�������µ�¼��');history.go(-1);</script>");
    return;
  }

  String fromid = SysCommonFunc.getStrParameter(request,"fromid");
  String fromname = SysCommonFunc.getStrParameter(request,"fromname");
  String dates = DateTools.dateToString(new Date(System.currentTimeMillis()),true);

if(request.getParameter("bj") != null && "0".equals(isRel))
{
   String s = GRZQMng.addHyly(request,loginUser);
   if(s == null)
      out.println("<script language='javascript'>alert('�ɹ��������ԣ�');location.href = 'lxfs-id-"+userid+".htm';</script>");
   else
      out.println("<script language='javascript'>alert('"+s+"!');</script>");
   return;
}

// add Bjd �׽𶹹��� by  linyu 20151128 start
 int flag = 0;
    if(loginUser != null && loginUser.getFlag() != null && loginUser.getFlag().intValue() == SysDefine.SYSTEM_HY_TYPE_vip)
        flag = SysDefine.SYSTEM_HY_TYPE_vip;
        //vip��Ա
    else if (loginUser != null && loginUser.getFlag() != null && loginUser.getFlag().intValue() == SysDefine.SYSTEM_HY_TYPE_nvip)
        flag = SysDefine.SYSTEM_HY_TYPE_nvip;
    else if(loginUser != null)
        flag = SysDefine.SYSTEM_HY_TYPE_NOR;
        
  //��ͨ��Ա�ſ۰׽�      
 if(loginUser !=null && flag==SysDefine.SYSTEM_HY_TYPE_NOR){
 long needbjd = 1l;
    if(hyInfo.getIsvcation()==1)
    {
        needbjd = 2l; 
    }

//�׽��Ƿ�鿴�û�Ա��
boolean isMayView=false;   
 //��¼��Ա�İ׽���
 long bjd = 0l;
 if(loginUser !=null){
List listtemp = QueryRecord.queryByDynaResultSet("select * from user_bjd_viewrecord where hyid="+loginUser.getHyid()+" and viewhyid="+userid + "order by id asc",1,1).resultList;
		if(listtemp != null && listtemp.size() > 0)
		{
		
		   DynaBean db = (DynaBean)listtemp.get(0);
		   Date vdate = (Date)db.get("viewdate");
		   Date ccdate = new Date(System.currentTimeMillis());
		
		  if((ccdate.getTime()-vdate.getTime()) > 48*60*60*1000){
		     isMayView = false;
		   }else{
		     isMayView = true;
		   }
		}
		//������ܿ���ʱ���ڣ��Ͳ����ٲ�ѯ�׽���
		 if(!isMayView){
		   List listbjd = HbmOperator.list("from UserBjd as o where o.hyid="+loginUser.getHyid());
		    if(listbjd != null && listbjd.size() > 0)
		    {
		        UserBjd ub = (UserBjd)listbjd.get(0);
		        bjd = ub.getBjdnumber();
		    }
		    
		    if(loginUser!=null && bjd >= needbjd){
		    	  UserBjdRecord ubr = new UserBjdRecord();
			    ubr.setBjddesc("�鿴��"+hyInfo.getLcname()+")hyid="+hyInfo.getHyid());
			    ubr.setBjdnumber(0-needbjd);
			    ubr.setHyid(loginUser.getHyid().toString());
			    ubr.setRecodeTime(new Date(System.currentTimeMillis()));
			    ubr.setId(SysCommonFunc.getSequenceIdForOracle("SEQ_WTJD"));
			
			    HbmOperator.insert(ubr);
			
			    HbmOperator.executeSql("update user_bjd set bjdnumber=bjdnumber-"+needbjd+ " where hyid ="+loginUser.getHyid());
			
			    HbmOperator.executeSql("insert into user_bjd_viewrecord (id,hyid,viewhyid,viewdate) values (SEQ_WTJD.NEXTVAL,"+loginUser.getHyid()+","+hyInfo.getHyid()+",sysdate)");
		
		    }else{
				//�Ȳ��ǰ׽��Ա���׽�Ҳ�������鿴��ʱ�Ժ�����ʾ		    
		   		 out.println("<script language='javascript'>alert('������ͨ��Ա���������׽�VIP��Ա��ȡ���㹻�İ׽𶹣�');window.parent.location.replace('paymoney.jsp');</script>");
		   		return;
		    }
		 }
    }
 }

// add Bjd �׽𶹹��� by  linyu 20151128 end

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
</head>

<body>

<div id="header">
    <div class="h_nav"> 
    <a href="perinfo-id-5092108.htm"><div class="h_left"><img src="images/l.png" /></div></a>  <%=hyInfo.getLcname() %>����ϵ��ʽ</div>
</div>

<div id="inner">
	<div class="lxwm">
    	<div class="lxwm_bg">
           <div class="lxwm_nav">�绰��ϵ</div>
           <div class="lxwm_c"> <div class="lxwm_l">�ֻ���</div> <a href="tel:<%=sjtel%>"><div class="lxwm_r"><%=sjtel%> ( ����֤)</div></a>  </div>
           <div class="lxwm_c"> <div class="lxwm_l">�̻���</div> <a href="tel:<%=tel%>"><div class="lxwm_r"><%=tel%></div></a>  </div>
     	</div>
         
		<div class="lxwm_bg" style="height:260px;">
	    	<form action="lxfs.jsp?bj=1" method="post">
	    	<input type="hidden" name="id" value="<%=userid%>"/>
	    	<input type="hidden" name="hyid" value="<%=userid%>"/>
	    	<input type="hidden" name="hyname" value="<%=username%>"/>
	    	<input type="hidden" name="lytime" value="<%=dates%>"/>
	        	<div class="lxwm_nav">������ϵ��ʽ</div>	
	        	<div class="lxwm_c"> <div class="lxwm_l">QQ��</div> <div class="lxwm_r"><%=oicq%></div> </div>
	        	<div class="lxwm_c"> <div class="lxwm_l">E-mail��</div> <div class="lxwm_r"><%=email%></div> </div>
	        	<div class="lxwm_c"> <div class="lxwm_l">΢�ţ�</div> <div class="lxwm_r"><%=wx%></div> </div>
	       		<div class="lxwm_c1"> <div class="lxwm_l">�������ݣ�</div>
	          		<textarea id="content" name="content" class="lxwm_txt"></textarea> 
	          	</div>
	         	<div class="clearfix"></div>
	       		<input id="lytj" name="Submit" type="submit" value="�ύ" class="lxwm_an" style="float:right; margin-right:10%; margin-bottom:10px;"/>
	    	</form>
    	</div>
	</div>
</div>
<%@ include file="bottom2.jsp"%>
<%@ include file="bottom.jsp"%>
<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script>  
<script type="text/javascript" src="js/lxfs.js"></script>
</body>
</html>
