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
    
<%
    Userinfo loginUser = (Userinfo)session.getAttribute(SysDefine.SESSION_LOGINNAME);
    String userid = SysCommonFunc.getStrParameter(request,"id");
    boolean isvery = false;
    if(SysCommonFunc.getNumberFromString(userid,0)==0)
    {
        out.println("<script language='javascript'>alert('��Ա��Ϣ�����ڣ�');window.close();</script>");
        return;
    }
    String vurl="";
    if (loginUser!=null){
        
        vurl=request.getRequestURL().toString();
        if (request.getQueryString()!=null){
            vurl+="?"+request.getQueryString();
        }
        UserVisitMng.insertUserVisit(loginUser.getHyid(), vurl);
    }

    String qid = SysCommonFunc.getStrParameter(request,"qid");
    if(SysCommonFunc.getNumberFromString(qid,0)>0)
    {
        HbmOperator.executeSql("update db_qiubo set isread='1' where id = " + qid);
    }
        Userinfo hyInfo = HYRegMng.getUserinfoByHyid(userid);

    if(hyInfo == null)
    {
        out.println("<script language='javascript'>alert('��Ա��Ϣ�����ڣ�');window.close();</script>");
        return;
    }
    Date cdate =  DateTools.stringToDate("2010-08-27");

    //modify by gaojianhong 20120820 for ����ISVCATION�Ƿ���֤�ֶ� start
    //if((hyInfo.getUsername().equals(hyInfo.getEmail()) || hyInfo.getUsername().equals(hyInfo.getSjtel()))&&hyInfo.getRegtime().after(cdate))
    if(hyInfo.getIsvcation()==1){
        isvery = true;
    }
    long needbjd = 1l;
    if(isvery)
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

  if((ccdate.getTime()-vdate.getTime()) > 48*60*60*1000)
     isMayView = false;
   else
     isMayView = true;
}

   List listbjd = HbmOperator.list("from UserBjd as o where o.hyid="+loginUser.getHyid());
    if(listbjd != null && listbjd.size() > 0)
    {
        UserBjd ub = (UserBjd)listbjd.get(0);
        bjd = ub.getBjdnumber();
    }
  }
    
    HashSet<Integer> hs1 = new HashSet<Integer>();
    Random r1 = new Random();
    hs1.add(r1.nextInt(20));
    Iterator<Integer> i1 = hs1.iterator();

///�����û�������ָ��
    Long stime=System.currentTimeMillis();
    if (session.getAttribute("viewuser")!=null && !session.getAttribute("viewuser").equals("")){
        stime=Long.parseLong(session.getAttribute("viewuser").toString());
    }else{
        session.setAttribute("viewuser",stime);
    }
    if ((System.currentTimeMillis()-stime)>10000){
        session.setAttribute("viewuser",System.currentTimeMillis());
        String sqlstr = "update userinfo set hots = hots + "+i1.next()+" where hyid="+userid;
        HbmOperator.executeSql(sqlstr);
    }

    Userother uother = HYRegMng.getUserOtherByHyid(userid);
    Userinfo ui = null;
    List loginList = HbmOperator
            .list("from Userinfo as o where o.hyid=" + uother.getHyid());
    long datetime2010 = 0;
    boolean last_login_is_2010 = false;
    if(loginList !=null && loginList.size()>0){
        ui  = (Userinfo) loginList.get(0);
    }


    String jyyx = "";
    String jyzt ="";
    if(hyInfo.getSetzt().intValue() == SysDefine.SYSTEM_HYSET_HYZT_WAIT)
        jyzt = "�ȴ���";
    else if(hyInfo.getSetzt().intValue() == SysDefine.SYSTEM_HYSET_HYZT_NOT_WAIT)
        jyzt = "�������";
    String jyyx01 = "00000000";
    if(hyInfo.getJyyx() != null && hyInfo.getJyyx().toString().length() ==6)
        jyyx01 = hyInfo.getJyyx().toString();

    for(int i=0;i < 5;i ++)
    {
        if(jyyx01.charAt(i+1) == '1')
            jyyx = jyyx + SysDefine.jyyxArray[i]+"&nbsp;&nbsp;";
    }


    String sex = DicList.getDicValue(SysDefine.DIC_SEX,hyInfo.getSex(),1);
    String hyzk = DicList.getDicValue(SysDefine.DIC_HYZH,hyInfo.getHyzk(),1);
    String zz   = (hyInfo.getS1() == null? "":hyInfo.getS1()) + (hyInfo.getS2()==null ? "":hyInfo.getS2());
    if(zz.equals("��������"))
        zz = "����";
    if(zz.equals("�Ϻ��Ϻ�"))
        zz = "�Ϻ�";
    if(zz.equals("������"))
        zz = "���";
    if(zz.equals("��������"))
        zz = "����";
    if(zz.equals("������"))
        zz = "���";
    if(zz.equals("���Ű���"))
        zz = "����";
    String age = "δ֪";

    if(hyInfo.getCsdate() != null)
    {
        Date date = new Date(System.currentTimeMillis());

        age=(date.getYear()-hyInfo.getCsdate().getYear())+"��";
    }
    Date datea = new Date(System.currentTimeMillis());

    int time = 0;
    int yy=0;
    time = datea.getMonth()-hyInfo.getRegtime().getMonth();
    yy = datea.getYear()-hyInfo.getRegtime().getYear();
    String whcd = DicList.getDicValue(SysDefine.DIC_WHCD,hyInfo.getWhcd(),1);
    String yx = DicList.getDicValue(SysDefine.DIC_YX,hyInfo.getYx(),1);
    String zf = DicList.getDicValue(SysDefine.DIC_ZF,hyInfo.getZf(),1);
    String sg = DicList.getDicValue(SysDefine.DIC_SG,hyInfo.getSg(),1);
    String tz = DicList.getDicValue(SysDefine.DIC_TZ,hyInfo.getTz(),1);
    String fs = DicList.getDicValue(SysDefine.DIC_FS,hyInfo.getFs(),1);
    String xz = DicList.getDicValue(SysDefine.DIC_XZ, hyInfo.getXz(), 1);
    String xx = DicList.getDicValue(SysDefine.DIC_XX,hyInfo.getSx(),1);
    String zy = hyInfo.getZy() == null ? "û�з���":DicList.getDicValue(SysDefine.DIC_ZY,hyInfo.getZy(),1);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String xgtz = "";
    String xgtz01= "000000000000000";
    if(hyInfo.getXgtz() != null && hyInfo.getXgtz().length() ==15)
        xgtz01 = hyInfo.getXgtz();
    for(int i=0;i < xgtz01.length();i ++)
    {
        if(xgtz01.charAt(i) == '1')
            xgtz = xgtz + SysDefine.xgtzArray[i]+"&nbsp;&nbsp;";
    }

     String yyap = "";
    String yyap01= "00000000000";
    if(hyInfo.getYyap() != null && hyInfo.getYyap().length() ==11)
        yyap01 = hyInfo.getYyap();
    for(int i=0;i < yyap01.length();i ++)
    {
        if(yyap01.charAt(i) == '1')
            yyap = yyap + SysDefine.yyapArray[i]+"&nbsp;&nbsp;";
    }
    String afilepath = SysCommonFunc.getPhotoView();
    String afilepathsmall = SysCommonFunc.getPhotoSmallView();
    String cpages = SysCommonFunc.getStrParameter(request,"cpages");
    int cpage = SysCommonFunc.getNumberFromString(cpages,1);
    String tcounts = SysCommonFunc.getStrParameter(request,"tcounts");
    int tcount  = SysCommonFunc.getNumberFromString(tcounts,0);
    int pagesize = 10;
    String wjfy = SysCommonFunc.getStrParameter(request,"wjfy");
    QueryResult qr = null;
    String sql = "select '1' as wtype,id,title,contentid from userarticle where hyid = " + hyInfo.getHyid() + " and title not like '%script%' and title not like '%iframe%' and title not like '%onload%' and title not like '%url%' and title not like '%src%' and title not like '%href%' and title not like '%load%' and title not like '%frame%' " +
            " union  " +
            "select '2' as wtype,id,title,content as contentid from bbs where user_id = " +hyInfo.getHyid()+ " and re_id=0 and ischeck = '1' order by wtype asc, id desc";
    qr = QueryRecord.queryByDynaResultSet(sql, pagesize, cpage);
    int totalPage =0;
    int totalCount =0;
    if(qr!=null){
         totalPage = qr.pageCount;
         totalCount = qr.rowCount;
    }

    cpage = qr.pageNum;
    int prepage = cpage-1;
    int nextpage = cpage+1;
    List alist = qr.resultList;
%>

<%
    if(null != loginUser ){
        List viewList = HbmOperator.list("from Viewme as v where v.myId = '"+loginUser.getHyid()+"' and v.viewId ='"+hyInfo.getHyid()+"' ");

        List mvList = HbmOperator.list("from Meview as v where v.myId = '" + loginUser.getHyid() + "' and v.viewId ='" + hyInfo.getHyid() + "' ");
        Date viewtime = new Date(System.currentTimeMillis());
        Viewme viewme = null;
        Viewme view = null;

        Meview mv = null;
        Meview mvUp = null;
        if(viewList!=null && viewList.size()>0){
            view = (Viewme)viewList.get(0);
        }
        if(mvList!=null && mvList.size()>0)
            mvUp = (Meview)mvList.get(0);

        if(viewList.size()==0){

            viewme = new Viewme();
            viewme.setId(SysCommonFunc
                    .getSequenceIdForOracle("seq_viewme_id"));
            viewme.setMyId(loginUser.getHyid());
            viewme.setMyLcname(loginUser.getLcname());
            viewme.setViewId(hyInfo.getHyid());
            viewme.setViewLcname(hyInfo.getLcname());
            viewme.setViewTime(viewtime);
        }
        else
        {
            view.setViewTime(new Date(System.currentTimeMillis()));
        }


        if(mvList==null || mvList.size()==0){
            mv = new Meview();

            mv.setId(SysCommonFunc
                    .getSequenceIdForOracle("seq_viewme_id"));
            mv.setMyId(loginUser.getHyid());
            mv.setMyLcname(loginUser.getLcname());
            mv.setViewId(hyInfo.getHyid());
            mv.setViewLcname(hyInfo.getLcname());
            mv.setViewTime(viewtime);
        }
        else
        {

            mvUp.setViewTime(new Date(System.currentTimeMillis()));
        }
        try
        {
            if (viewList == null || viewList.size() == 0)
            {
                HbmOperator.insert(viewme);
            }
            else
            {
                HbmOperator.update(view);
            }
            if (mvList == null || mvList.size() == 0)
            {
                HbmOperator.insert(mv);
            }
            else
            {
                HbmOperator.update(mvUp);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
 
%>

<%
     int photoNum = 0;
    if(uother != null && uother.getUserphoto1() != null && uother.getUserphoto1().length() > 0){
        photoNum +=1;
    }
    if(uother != null && uother.getUserphoto2() != null && uother.getUserphoto2().length() > 0){
        photoNum +=1;
    }
    if(uother != null && uother.getUserphoto3() != null && uother.getUserphoto3().length() > 0){
        photoNum +=1;
    }
    if(uother != null && uother.getUserphoto4() != null && uother.getUserphoto4().length() > 0){
        photoNum +=1;
    }
    if(uother != null && uother.getUserphoto5() != null && uother.getUserphoto5().length() > 0){
        photoNum +=1;
    }
    String isAddFavorite =  (String)session.getAttribute("isAddFavorite");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<title>51��������</title>
<meta name="description" content="51����������վ-�й�������ҵ���Ʒ�ơ����ܹ�ϵ���������¡�����ѵ����ȫ��λ֧������������ʮ����Ӫ��ǧ���Ա���������飬��51���ѿ�ʼ��" />
<meta name="keywords" content="51��������--���ܽ��� ��Ե���� ͬ�ǽ��� ֪������ ��Ů����" />
<link rel="stylesheet" type="text/css" href="css/style.css?v=<%=System.currentTimeMillis() %>"> 
</head>

<body>
<div id="header">
    <div class="h_nav"> <a href="index.jsp"><div class="h_left"><img src="images/l.png" /></div></a>  ��Ա����</div>
</div>

<div id="inner">
    <div class="hyzl">
        <div class="hyzl_nav" id="hyzl_nav">
            <div class="h_n_on f_l">
				<li tab="yj1">��������</li>
			</div>
            <div class="h_n_on f_l">
				<li id="photos" tab="yj2">Ӱ�� (<%=photoNum%>)</li>
			</div>
        </div>
    </div>
    <div>
		<div id="yj1" class="hyzl_yj" align="center">
			  <%
                     if(uother.getUserphoto1() != null)
                     {
                      String photo = uother.getUserphoto1().replace("\\","/");
                 %>
			 <img src='<%=afilepathsmall+"/"+photo %>'   border="0" />  
			 <%
                     }else  
                     {
                 %>
			 <img src="./images/img2.png" />
			  <%
                     } 
                 %>
			<div class="h_yj_nav">�ǳƣ�<span class="yew"><%=hyInfo.getLcname()%></span> </div>
			<div class="h_yj_txt"><div class="h_yj_tl">�Ա�<span class="grey9"><%=sex%></span></div><div class="h_yj_tl">���䣺<span class="grey9"><%=age%></span></div></div>
			<div class="h_yj_txt"><div class="h_yj_tl">���<span class="grey9"><%=hyzk%></span></div><div class="h_yj_tl">ְҵ��<span class="grey9"><%=zy%></span></div></div>
			<div class="h_yj_txt">��������<span class="orange"><%=jyyx%></span> </div>
			<div class="h_yj_txt">סַ��<%=zz+(hyInfo.getS3() == null ? "":hyInfo.getS3())%></div>
			<div class="h_yj_txt"><div class="h_yj_tl">ѧ����<span class="grey9"><%=whcd%></span></div><div class="h_yj_tl">��н��<span class="grey9"><%=yx%></span></div></div>
			<div class="h_yj_txt"><div class="h_yj_tl">ס����<span class="grey9"><%=zf%></span></div><div class="h_yj_tl">��ߣ�<span class="grey9"><%=sg%></span></div></div>
			<div class="h_yj_txt"><div class="h_yj_tl">���أ�<span class="grey9"><%=tz%></span></div><div class="h_yj_tl">��ɫ��<span class="grey9"><%=fs%></span></div></div>
			<div class="h_yj_txt"><div class="h_yj_tl">������<span class="grey9"><%=xz%></span></div><div class="h_yj_tl">Ѫ�ͣ�<span class="grey9"><%=xx%></span></div></div>
			<div class="h_yj_txt"><div class="hytl">�Ը�������</div><span class="grey9"><div class="hytr2"><%=xgtz%></div></span> </div>
			<div class="h_yj_txt"><div class="hytl">ҵ�ల�ţ�</div><span class="grey9"><div class="hytr2"><%=yyap%></div></span> </div>
			<div class="clearfix"></div>
			<div class="h_yj_nav" style="padding-top:15px;"><span class="yew"><%=hyInfo.getSex().equals("11")?"��":"��" %>�Ĳ�������</span></div>
			<div class="h_yj_txt"><div class="hytl">�鰮��ϵ��</div><span class="grey9"><div class="hytr"><%=uother.getQggx()%></div></span></div>
			<div class="h_yj_txt"><div class="hytl">�������ˣ�</div><span class="grey9"><div class="hytr"><%=uother.getLxqr()%></div></span></div>
			<div class="h_yj_txt"><div class="hytl">�������ԣ�</div><span class="grey9"><div class="hytr"><%=uother == null ? "":uother.getJyly()%></div></span> </div>
			<div class="clearfix"></div>
			<%
				if(loginUser !=null && (flag==SysDefine.SYSTEM_HY_TYPE_nvip || flag==SysDefine.SYSTEM_HY_TYPE_vip ) && hyInfo.getSetzt() != null && hyInfo.getSetzt().intValue() == SysDefine.SYSTEM_HYSET_HYZT_NOT_WAIT){
			%>
					<div onClick="javascript:alert('�û�Ա״̬Ϊ������ţ��벻Ҫ�����磡');" class="btn tjan" style="margin-top:20px;margin-left:7%;">��TA��ϵ</div>
			<%
				}else if(loginUser !=null && (flag==SysDefine.SYSTEM_HY_TYPE_nvip || flag==SysDefine.SYSTEM_HY_TYPE_vip ) && hyInfo.getSetzt() != null && hyInfo.getSetzt().intValue() == SysDefine.SYSTEM_HYSET_HYZT_NOT_WAIT){
			%>
					<div onClick="javascript:alert('�û�Ա�ѽ���������������벻Ҫ�����磡');" class="btn tjan" style="margin-top:20px;margin-left:7%;">��TA��ϵ</div>
			<%
				}else if(loginUser !=null && (flag==SysDefine.SYSTEM_HY_TYPE_nvip|| flag==SysDefine.SYSTEM_HY_TYPE_vip ||  isMayView || (bjd >=  needbjd)) ){
			%>
					<div class="btn tjan" data-id="<%=hyInfo.getHyid() %>" id="lxfs" style="margin-top:20px;margin-left:7%;">��TA��ϵ</div>
			<%
				}else if(loginUser ==null){
			%>
					<div onClick="javascript:alert('�����οͣ�����ע����½��'); window.parent.location.replace('reg.jsp?url=<%=vurl %>');" class="btn tjan" style="margin-top:20px;margin-left:7%;">��TA��ϵ</div>
			<%
				}else if(loginUser !=null && flag==SysDefine.SYSTEM_HY_TYPE_NOR){
			%>
					<div onClick="javascript:alert('������ͨ��Ա���������׽�VIP��Ա��ȡ���㹻�İ׽𶹣�');window.parent.location.replace('paymoney.jsp');" class="btn tjan" style="margin-top:20px;margin-left:7%;">��TA��ϵ</div>
			<%
				}
			%>
		</div>
		<div id="yj2" class="hyzl_yj">
			<h3><%=hyInfo.getLcname()%> &nbsp; &nbsp; <%=age%></h3>
			<%
               if(uother != null && uother.getUserphoto1() != null && uother.getUserphoto1().length() > 0){
            	   String photo = uother.getUserphoto1().replace("\\","/");
                    %>

                        <img src='<%=afilepath+"/"+photo %>'  width="100%"  border="0" />

                 <% }else
                        {
                	 %> 
                        <img src='images/nopic2.gif' width="100%"   border='0'> 
                    <%
                    	}
                    %>
                     <!-- ----------------------------------------------- -->
                  <%  if(uother != null && uother.getUserphoto2() != null && uother.getUserphoto2().length() > 0){
                  		   String photo = uother.getUserphoto2().replace("\\","/");
                    %>

                        <img src='<%=afilepath+"/"+photo %>' width="100%"   border="0" />

                 <% }else
                        {
                	 %> 
                        <!--   <img src='images/nopic2.gif'   border='0'>  --> 
                    <%
                    	}
                    %>   
                    <!-- ----------------------------------------------- -->
                      <%  if(uother != null && uother.getUserphoto3() != null && uother.getUserphoto3().length() > 0){
                  		   String photo = uother.getUserphoto3().replace("\\","/");
                    %>

                        <img src='<%=afilepath+"/"+photo %>' width="100%"   border="0" />

                 <% }else
                        {
                	 %> 
                        <!--   <img src='images/nopic2.gif'   border='0'>  --> 
                    <%
                    	}
                    %>  
                     <!-- ----------------------------------------------- -->
                      <%  if(uother != null && uother.getUserphoto4() != null && uother.getUserphoto4().length() > 0){
                  		   String photo = uother.getUserphoto4().replace("\\","/");
                    %>

                        <img src='<%=afilepath+"/"+photo %>' width="100%"   border="0" />

                 <% }else
                        {
                	 %> 
                        <!--   <img src='images/nopic2.gif'   border='0'>  --> 
                    <%
                    	}
                    %>  
                     <!-- ----------------------------------------------- -->
                      <%  if(uother != null && uother.getUserphoto5() != null && uother.getUserphoto5().length() > 0){
                  		   String photo = uother.getUserphoto5().replace("\\","/");
                    %>

                        <img src='<%=afilepath+"/"+photo %>' width="100%"  border="0" />

                 <% }else
                        {
                	 %> 
                     <!--   <img src='images/nopic2.gif'   border='0'>  --> 
                    <%
                    	}
                    %>      
			<div id="bigImg" style="display:none">
				<div calss="target">
			      <!--    <img src="images/aaa.jpg"  />-->
			     </div>
			</div>
			<%
				if(loginUser !=null && (flag==SysDefine.SYSTEM_HY_TYPE_nvip || flag==SysDefine.SYSTEM_HY_TYPE_vip ) && hyInfo.getSetzt() != null && hyInfo.getSetzt().intValue() == SysDefine.SYSTEM_HYSET_HYZT_NOT_WAIT){
			%>
					<div onClick="javascript:alert('�û�Ա״̬Ϊ������ţ��벻Ҫ�����磡');" class="btn tjan" style="margin-left:7%;">��TA��ϵ</div>
			<%
				}else if(loginUser !=null && (flag==SysDefine.SYSTEM_HY_TYPE_nvip || flag==SysDefine.SYSTEM_HY_TYPE_vip ) && hyInfo.getSetzt() != null && hyInfo.getSetzt().intValue() == SysDefine.SYSTEM_HYSET_HYZT_NOT_WAIT){
			%>
					<div onClick="javascript:alert('�û�Ա�ѽ���������������벻Ҫ�����磡');" class="btn tjan" style="margin-left:7%;">��TA��ϵ</div>
			<%
				}else if(loginUser !=null && (flag==SysDefine.SYSTEM_HY_TYPE_nvip|| flag==SysDefine.SYSTEM_HY_TYPE_vip ) ){
			%>
					<div class="btn tjan" data-id="<%=hyInfo.getHyid() %>" id="lxfs" style="margin-left:7%;">��TA��ϵ</div>
			<%
				}else if(loginUser ==null){
			%>
					<div onClick="javascript:alert('�����οͣ�����ע����½��'); window.parent.location.replace('reg.jsp?url=<%=vurl %>');" class="btn tjan" style="margin-left:7%;">��TA��ϵ</div>
			<%
				}else if(loginUser !=null && flag==SysDefine.SYSTEM_HY_TYPE_NOR){
			%>
					<div onClick="javascript:alert('������ͨ��Ա��������Ϊ�׽�VIP��Ա��');window.parent.location.replace('paymoney.jsp');" class="btn tjan" style="margin-left:7%;">��TA��ϵ</div>
			<%
				}
			%>
			
			
			
		</div>
    </div>
</div>
<%@ include file="bottom2.jsp"%>
<%@ include file="bottom.jsp"%>
<input id="isAddFavorite"  type="hidden" value="<%=isAddFavorite%>">
<iframe id="addFavoriteframe" name="addFavoriteframe" frameborder="0" width="0" height="0"></iframe>
<form id="addFavoriteform" action="addFavorite.jsp" target="addFavoriteframe">
       <input name="isAddFavorite"  type="hidden" value="1">
   </form>
<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script> 

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/lcmbase.js"></script>
<script type="text/javascript" src="js/jquery-1.10.1.min.js"></script> 
<script type="text/javascript" src="js/wapLover.js"></script> 
<script type="text/javascript" src="js/perinfo.js?v=114"></script>  
<script type="text/javascript" src="js/hammer.js"></script>  
<script type="text/javascript" src="js/hammerPluin.js"></script>  
<script type="text/javascript">
$(document).ready(function() {
    $.jqtab("#hyzl_nav",".hyzl_yj","h_n_in");
});
 
</script> 
</body>
</html>
