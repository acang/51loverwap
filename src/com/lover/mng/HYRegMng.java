package com.lover.mng;

import com.web.common.*;
import com.common.*;

import javax.servlet.http.HttpServletRequest;

import com.web.obj.*;
import com.web.common.beanutil.*;
import com.web.obj.extend.DicList;

import hibernate.db.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.lover.*;

import org.apache.commons.beanutils.BeanUtils;

import com.web.bean.QueryRecord;

import org.apache.commons.beanutils.*;

import java.math.*;

public class HYRegMng
{
    public HYRegMng()
    {
    }

    public static final String execute(HttpServletRequest request)
    {
        String action = SysCommonFunc.getStrParameter(request, "biz_action");
        if (action == null || action.trim().length() == 0)
            return "�޴˲�������!";
        if (action.equals("01")) // /�û�ע��
            return HYRegMng.userReg(request);
        return "�޴˲�������!";
    }

    public static String userRegNewEmail(HttpServletRequest request)
    {
        String s = null;
        Userinfo userinfo = (Userinfo) BeanInitializer.initBean(Userinfo.class,
            request);

        userinfo.setUsername(userinfo.getUsername().toLowerCase());

        if (userinfo.getUsername() == null
            || userinfo.getUsername().trim().length() == 0)
            return "�û�������Ϊ��!";
        userinfo.setUsername(userinfo.getUsername().trim());

        List list = HbmOperator.list("from Userinfo as o where o.username='"
            + userinfo.getUsername() + "' and o.isdel=0");
        if (list != null && list.size() > 0)
            return "�û���\"" + userinfo.getUsername() + "\"�Ѿ���ʹ��,�����������û�����";
        List list1 = HbmOperator.list("from Userinfo as o where o.httpip='"
                + request.getRemoteAddr() + "' and o.isdel=1 and o.regtime  >= sysdate - 1");
            if (list1 != null && list1.size() > 5)
                return "�������䲻��ע�ᣡ�뻻�������ʱ����ע�ᣡ";

        Date cdate = new Date(System.currentTimeMillis());
        userinfo.setRegtime(cdate);
        userinfo.setRegtime2(cdate);
        userinfo.setRegtime3(cdate);
        userinfo.setLasttime(cdate);
        // /����δ���õ���Ŀ
        Long hyid = SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_HYID);
        userinfo.setHyid(hyid);
        userinfo.setFlag(new Integer(SysDefine.SYSTEM_HY_TYPE_NOR));
        userinfo.setHots(new Long(0));
        userinfo.setImg(new Integer(0));
        userinfo.setSetzt(new Integer(SysDefine.SYSTEM_HYSET_HYZT_WAIT));
        userinfo.setHttpip(request.getRemoteAddr());
        userinfo.setIsdel(new Integer(1));
        userinfo.setEmail(userinfo.getUsername());
        userinfo.setIsvcation(new Long(1));
        String httpurl = (String) request.getSession().getAttribute("httpurl");
        String tjid = (String) request.getSession().getAttribute("tjid");
        if (httpurl != null && httpurl.getBytes().length > 50)
            httpurl = httpurl.substring(0, 50);

        String advid = (String) request.getSession().getAttribute("advid");
        if (advid != null && advid.trim().length() > 0)
        {
            int advidn = SysCommonFunc.getNumberFromString(advid, 0);
            userinfo.setVip(new Integer(advidn));
        }
        else
        {
            userinfo.setVip(new Integer(0));
        }

        userinfo.setHttpurl(httpurl);

        Userother userother = (Userother) BeanInitializer.initBean(
            Userother.class, request);
        userother.setHyid(hyid);
        String mc = "";
        if (tjid == null)
        {
            // �����ʼ�
            mc = "���ã�<br>&nbsp;&nbsp;&nbsp;&nbsp;��ӭ��ʹ��51����������վ������������µ�ַ������û�����֤��<br>&nbsp;&nbsp;&nbsp;&nbsp;<a href='http://"
                + request.getServerName()
//                + (request.getRemotePort() == 80 ? "" : ":"
//                    + request.getServerPort())
                + request.getContextPath()
                + "/reg2email.jsp?hyid="
                + userinfo.getHyid()
                + "&username="
                + userinfo.getUsername() + "'>����˴���֤</a>";
        }
        else
        {
            mc = "���ã�<br>&nbsp;&nbsp;&nbsp;&nbsp;��ӭ��ʹ��51����������վ������������µ�ַ������û�����֤��<br>&nbsp;&nbsp;&nbsp;&nbsp;<a href='http://"
                + request.getServerName()
//                + (request.getRemotePort() == 80 ? "" : ":"
//                    + request.getServerPort())
                + request.getContextPath()
                + "/reg2email.jsp?hyid="
                + userinfo.getHyid()
                + "&username="
                + userinfo.getUsername() + "&tjid=" + tjid + "'>����˴���֤</a>";
        }

        // /��ʼ���棺
        try
        {
            Vector saveList = new Vector();
            MutSeaObject mso = new MutSeaObject();

            mso.setHbmSea(userinfo, MutSeaObject.SEA_HBM_INSERT);
            saveList.add(mso);
            mso = new MutSeaObject();
            mso.setHbmSea(userother, MutSeaObject.SEA_HBM_INSERT);
            saveList.add(mso);
            HbmOperator.SeaMutData(saveList);

            // /���û���Ϣ���뵽��½��Ϣ��

        }
        catch (Exception e)
        {
            System.out.println("ע���û�����" + e.getMessage());
            return "ע������������Ա��ϵ��";
        }
        boolean ok = MailTools.reMail("51����������վ�û�����֤��", mc, userinfo
            .getUsername());
        if (ok == false)
        {
            s = "�ʼ����ͳ���";
        }
        return s;

    }

    public static String userRegNew(HttpServletRequest request)
    {
        String s = null;
        Userinfo userinfo = (Userinfo) BeanInitializer.initBean(Userinfo.class,
            request);

        userinfo.setUsername(userinfo.getUsername().toLowerCase());

        if (userinfo.getUsername() == null
            || userinfo.getUsername().trim().length() == 0)
            return "�û�������Ϊ��!";
        userinfo.setUsername(userinfo.getUsername().trim());

        List list = HbmOperator.list("from Userinfo as o where o.username='"
            + userinfo.getUsername() + "' and o.isdel=0");
        if (list != null && list.size() > 0)
            return "11�����ֻ������ѱ�ע�ᣬ��ʹ���ܱ������һ����룬�򻻸�����ע�ᣡ";

        // /������֤����
        String verycode = request.getParameter("verycode");
        List smsList = HbmOperator
            .list("from SendSms as o where o.mobileNumber='"
                + userinfo.getUsername() + "' and o.veryCode='" + verycode
                + "' order by id desc");
        SendSms ssms = (SendSms) smsList.get(0);
        if (smsList == null || smsList.size() == 0)
            return "������Ķ�����֤�벻��ȷ";
        else{
        	ssms.setVcation(new Long(1));
        	try {
				HbmOperator.update(ssms);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        Date cdate = new Date(System.currentTimeMillis());
        userinfo.setRegtime(cdate);
        userinfo.setRegtime2(cdate);
        userinfo.setRegtime3(cdate);
        userinfo.setLasttime(cdate);
        // /����δ���õ���Ŀ
        HashSet<Integer> hs1 = new HashSet<Integer>(); 
        Random r1 = new Random();
        hs1.add(r1.nextInt(35));
        Iterator<Integer> i1 = hs1.iterator();
        Long hyid = SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_HYID);
        userinfo.setHyid(hyid);
        userinfo.setFlag(new Integer(SysDefine.SYSTEM_HY_TYPE_NOR));
        userinfo.setHots(Long.valueOf(i1.next()));
        userinfo.setImg(new Integer(0));
        userinfo.setSetzt(new Integer(SysDefine.SYSTEM_HYSET_HYZT_WAIT));
        userinfo.setHttpip(request.getRemoteAddr());
        userinfo.setIsdel(new Integer(1));
        userinfo.setSjtel(userinfo.getUsername());
        userinfo.setIsvcation(new Long(1));
        userinfo.setVacsjtel(new Integer(1));
        userinfo.setVacemail(new Integer(0));
        
        String httpurl = (String) request.getSession().getAttribute("httpurl");
        String tjid = (String) request.getSession().getAttribute("tjid");

        if (httpurl != null && httpurl.getBytes().length > 50)
            httpurl = httpurl.substring(0, 50);

        String advid = (String) request.getSession().getAttribute("advid");
        if (advid != null && advid.trim().length() > 0)
        {
            int advidn = SysCommonFunc.getNumberFromString(advid, 0);
            userinfo.setVip(new Integer(advidn));
        }
        else
        {
            userinfo.setVip(new Integer(0));
        }

        userinfo.setHttpurl(httpurl);

        Userother userother = (Userother) BeanInitializer.initBean(
            Userother.class, request);
        userother.setHyid(hyid);
        // String mc="";
        // if(tjid==null)
        // {
        // //�����ʼ�
        // mc =
        // "���ã�<br>&nbsp;&nbsp;&nbsp;&nbsp;��ӭ��ʹ��51����������վ������������µ�ַ������û�����֤��<br>&nbsp;&nbsp;&nbsp;&nbsp;<a href='http://"+request.getServerName()+(request.getRemotePort()
        // == 80 ? "":":"+request.getServerPort())
        // +request.getContextPath()+"/reg2.jsp?hyid="+userinfo.getHyid()+"&username="+userinfo.getUsername()
        // + "'>����˴���֤</a>";
        // }
        // else
        // {
        // mc =
        // "���ã�<br>&nbsp;&nbsp;&nbsp;&nbsp;��ӭ��ʹ��51����������վ������������µ�ַ������û�����֤��<br>&nbsp;&nbsp;&nbsp;&nbsp;<a href='http://"+request.getServerName()+(request.getRemotePort()
        // == 80 ? "":":"+request.getServerPort())
        // +request.getContextPath()+"/reg2.jsp?hyid="+userinfo.getHyid()+"&username="+userinfo.getUsername()
        // + "&tjid="+tjid+"'>����˴���֤</a>";
        // }
        //

        // /��ʼ���棺
        try
        {
            Vector saveList = new Vector();
            MutSeaObject mso = new MutSeaObject();

            mso.setHbmSea(userinfo, MutSeaObject.SEA_HBM_INSERT);
            saveList.add(mso);
            mso = new MutSeaObject();
            mso.setHbmSea(userother, MutSeaObject.SEA_HBM_INSERT);
            saveList.add(mso);

            // //ע��ɹ��Լ�����һ���׽�

            //UserBjd ub = new UserBjd();
            //ub.setBjdnumber(1);
            //ub.setHyid(userinfo.getHyid());
            //mso = new MutSeaObject();
            //mso.setHbmSea(ub, MutSeaObject.SEA_HBM_INSERT);
            //saveList.add(mso);

            //UserBjdRecord ubr = new UserBjdRecord();
            //ubr.setBjddesc("ע��");
            //ubr.setBjdnumber(1);
            //ubr.setHyid(userinfo.getHyid().toString());
            //ubr.setId(SysCommonFunc.getSequenceIdForOracle("SEQ_WTJD"));
            //ubr.setRecodeTime(new Date(System.currentTimeMillis()));
            //mso = new MutSeaObject();
            //mso.setHbmSea(ubr, MutSeaObject.SEA_HBM_INSERT);
            //saveList.add(mso);

            HbmOperator.SeaMutData(saveList);

            // /���û���Ϣ���뵽��½��Ϣ��

        }
        catch (Exception e)
        {
            System.out.println("ע���û�����" + e.getMessage());
            return "ע������������Ա��ϵ��";
        }
        // boolean
        // ok=MailTools.reMail("51����������վ�û�����֤��",mc,userinfo.getUsername());
        // if(ok==false)
        // {
        // s="�ʼ����ͳ���";
        // }
        request.setAttribute("cu", userinfo);
        return s;

    }
    
    //add by gaojianhong 20120820 for �û���ע�� start
    public static String userRegNewUser(HttpServletRequest request)
    {
        String s = null;
        Userinfo userinfo = (Userinfo) BeanInitializer.initBean(Userinfo.class,
            request);

        userinfo.setUsername(userinfo.getUsername().toLowerCase());

        if (userinfo.getUsername() == null
            || userinfo.getUsername().trim().length() == 0)
            return "�û�������Ϊ��!";
        userinfo.setUsername(userinfo.getUsername().trim());

        List list = HbmOperator.list("from Userinfo as o where o.username='"
            + userinfo.getUsername() + "' and o.isdel=0");
        if (list != null && list.size() > 0)
            return "11�����û����ѱ�ע�ᣬ��ʹ���ܱ������һ����룬�򻻸��û���ע�ᣡ";

        Date cdate = new Date(System.currentTimeMillis());
        userinfo.setRegtime(cdate);
        userinfo.setRegtime2(cdate);
        userinfo.setRegtime3(cdate);
        userinfo.setLasttime(cdate);
        // /����δ���õ���Ŀ
        HashSet<Integer> hs1 = new HashSet<Integer>(); 
        Random r1 = new Random();
        hs1.add(r1.nextInt(35));
        Iterator<Integer> i1 = hs1.iterator();
        Long hyid = SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_HYID);
        userinfo.setHyid(hyid);
        userinfo.setFlag(new Integer(SysDefine.SYSTEM_HY_TYPE_NOR));
        userinfo.setHots(Long.valueOf(i1.next()));
        userinfo.setImg(new Integer(0));
        userinfo.setSetzt(new Integer(SysDefine.SYSTEM_HYSET_HYZT_WAIT));
        userinfo.setHttpip(request.getRemoteAddr());
        userinfo.setIsdel(new Integer(1));
        userinfo.setSjtel(userinfo.getUsername());
        userinfo.setIsvcation(new Long(0));
        userinfo.setVacsjtel(new Integer(0));
        userinfo.setVacemail(new Integer(0));
        userinfo.setShield("");
        
        String httpurl = (String) request.getSession().getAttribute("httpurl");
        String tjid = (String) request.getSession().getAttribute("tjid");

        if (httpurl != null && httpurl.getBytes().length > 50)
            httpurl = httpurl.substring(0, 50);

        String advid = (String) request.getSession().getAttribute("advid");
        if (advid != null && advid.trim().length() > 0)
        {
            int advidn = SysCommonFunc.getNumberFromString(advid, 0);
            userinfo.setVip(new Integer(advidn));
        }
        else
        {
            userinfo.setVip(new Integer(0));
        }

        userinfo.setHttpurl(httpurl);

        Userother userother = (Userother) BeanInitializer.initBean(
            Userother.class, request);
        userother.setHyid(hyid);

        // /��ʼ���棺
        try
        {
            Vector saveList = new Vector();
            MutSeaObject mso = new MutSeaObject();

            mso.setHbmSea(userinfo, MutSeaObject.SEA_HBM_INSERT);
            saveList.add(mso);
            mso = new MutSeaObject();
            mso.setHbmSea(userother, MutSeaObject.SEA_HBM_INSERT);
            saveList.add(mso);

            // //ע��ɹ��Լ�����һ���׽�

            //UserBjd ub = new UserBjd();
            //ub.setBjdnumber(1);
            //ub.setHyid(userinfo.getHyid());
            //mso = new MutSeaObject();
            //mso.setHbmSea(ub, MutSeaObject.SEA_HBM_INSERT);
            //saveList.add(mso);

            //UserBjdRecord ubr = new UserBjdRecord();
            //ubr.setBjddesc("ע��");
            //ubr.setBjdnumber(1);
            //ubr.setHyid(userinfo.getHyid().toString());
            //ubr.setId(SysCommonFunc.getSequenceIdForOracle("SEQ_WTJD"));
            //ubr.setRecodeTime(new Date(System.currentTimeMillis()));
            //mso = new MutSeaObject();
            //mso.setHbmSea(ubr, MutSeaObject.SEA_HBM_INSERT);
            //saveList.add(mso);

            HbmOperator.SeaMutData(saveList);

            // /���û���Ϣ���뵽��½��Ϣ��

        }
        catch (Exception e)
        {
            System.out.println("ע���û�����" + e.getMessage());
            return "ע������������Ա��ϵ��";
        }

        request.setAttribute("cu", userinfo);
        return s;

    }
    //add by gaojianhong 20120820 for �û���ע�� end
    
    public static String userVcation(HttpServletRequest request)
    {
        String s = null;
        String uname = request.getParameter("uname");
    	String vcode = request.getParameter("vcode");
    	String uuuuid = request.getParameter("uuuuid");

        List list = HbmOperator.list("from Userinfo as o where o.username='"
            + uname+ "' and o.isdel=0");
        List list1 = HbmOperator.list("from Userinfo as o where o.hyid='"
                + uuuuid + "' and o.isdel=0");
        if (list != null && list.size() > 0)
            return "11�����ֻ������ѱ�ע�ᣬ��ʹ���ܱ������һ����룬�򻻸�����ע�ᣡ";
        // /������֤����
        List smsList = HbmOperator
            .list("from SendSms as o where o.mobileNumber='"
                + uname + "' and o.veryCode='" + vcode
                + "' order by id desc");
        SendSms ssms = (SendSms) smsList.get(0);
        Userinfo uinfo = (Userinfo)list1.get(0);
        if (smsList == null || smsList.size() == 0)
            return "������Ķ�����֤�벻��ȷ";
        else{
        	uinfo.setUsername(uname);
        	ssms.setVcation(new Long(1));
        	try {
				HbmOperator.update(ssms);
				HbmOperator.update(uinfo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        
      
        return s;

    }

    public static final String userReg(HttpServletRequest request)
    {
        String s = null;

        Userinfo userinfo = (Userinfo) BeanInitializer.initBean(Userinfo.class,
            request);
        if (userinfo.getS1() == null || userinfo.getS1().trim().length() == 0)
            return "��ѡ��ʡ��";
        if (userinfo.getS2() == null || userinfo.getS2().trim().length() == 0)
            return "��ѡ�����";
        if (userinfo.getUsername() == null
            || userinfo.getUsername().trim().length() == 0)
            return "�û����ֲ���Ϊ��!";
        userinfo.setUsername(userinfo.getUsername().trim());

        List list = HbmOperator.list("from Userinfo as o where o.username='"
            + userinfo.getUsername() + "' and o.isdel=0");
        if (list != null && list.size() > 0)
            return "�û���\"" + userinfo.getUsername() + "\"�Ѿ���ʹ��,�����������û�����";
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("month");
        String xgtz01 = request.getParameter("xgtz01");
        String yyap01 = request.getParameter("yyap01");
        String jyyx01 = request.getParameter("jyyx01");

        // System.out.println(year+"-"+month+"-"+day+"-"+xgtz01+"-"+yyap01+"-"+jyyx01);

        userinfo.setCsdate(DateTools.stringToDate(year + "-" + month + "-"
            + day));
        Date cdate = new Date(System.currentTimeMillis());

        userinfo.setUsername(userinfo.getUsername().trim());
        userinfo.setRegtime(cdate);
        userinfo.setRegtime2(cdate);
        userinfo.setLasttime(cdate);
        userinfo.setRegtime3(cdate);
        // /����δ���õ���Ŀ
        Long hyid = SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_HYID);
        userinfo.setHyid(hyid);
        userinfo.setFlag(new Integer(SysDefine.SYSTEM_HY_TYPE_NOR));
        userinfo.setHots(new Long(0));
        userinfo.setImg(new Integer(0));
        userinfo.setSetzt(new Integer(SysDefine.SYSTEM_HYSET_HYZT_WAIT));
        userinfo.setHttpip(request.getRemoteAddr());
        userinfo.setIsdel(new Integer(0));
        String httpurl = (String) request.getSession().getAttribute("httpurl");
        if (httpurl != null && httpurl.getBytes().length > 50)
            httpurl = httpurl.substring(0, 50);

        String advid = (String) request.getSession().getAttribute("advid");
        if (advid != null && advid.trim().length() > 0)
        {
            int advidn = SysCommonFunc.getNumberFromString(advid, 0);
            userinfo.setVip(new Integer(advidn));
        }
        else
        {
            userinfo.setVip(new Integer(0));
        }

        userinfo.setHttpurl(httpurl);
        userinfo.setXgtz(xgtz01);
        userinfo.setYyap(yyap01);
        userinfo.setJyyx(new Long(jyyx01));

        Userother userother = (Userother) BeanInitializer.initBean(
            Userother.class, request);
        userother.setHyid(hyid);

        // ���Գ���
        userinfo.setLysize(Long.valueOf(userother.getJyly().length()));

        // /��ʼ���棺
        try
        {
            Vector saveList = new Vector();
            MutSeaObject mso = new MutSeaObject();

            mso.setHbmSea(userinfo, MutSeaObject.SEA_HBM_INSERT);
            saveList.add(mso);
            mso = new MutSeaObject();
            mso.setHbmSea(userother, MutSeaObject.SEA_HBM_INSERT);
            saveList.add(mso);
            HbmOperator.SeaMutData(saveList);

            // /���û���Ϣ���뵽��½��Ϣ��
            request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME,
                userinfo);
            request.getSession().setAttribute(
                SysDefine.SESSION_LOGINNAME_OTHER, userother);

        }
        catch (Exception e)
        {
            System.out.println("ע���û�����" + e.getMessage());
            return "ע������������Ա��ϵ��";
        }

        return s;
    }

    public static final String userReg2New(HttpServletRequest request)
    {
        String s = null;
        String hyid = SysCommonFunc.getStrParameter(request, "hyid");
        Userinfo userinfo = HYRegMng.getAllUserinfoByHyid(hyid);
        Userother uother = HYRegMng.getUserOtherByHyid(hyid);

        BeanInitializer.updateBean(userinfo, request);
        BeanInitializer.updateBean(uother, request);

        if (userinfo.getS1() == null || userinfo.getS1().trim().length() == 0)
            return "��ѡ��ʡ��";
        if (userinfo.getS2() == null || userinfo.getS2().trim().length() == 0)
            return "��ѡ�����";

        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");

        String xgtz01 = request.getParameter("xgtz01");
        String yyap01 = request.getParameter("yyap01");
        String jyyx01 = request.getParameter("jyyx01");

        userinfo.setCsdate(DateTools.stringToDate(year + "-" + month + "-"
            + day));
        Date cdate = new Date(System.currentTimeMillis());
        userinfo.setRegtime(cdate);
        userinfo.setRegtime2(cdate);
        userinfo.setRegtime3(cdate);
        userinfo.setLasttime(cdate);
        userinfo.setRegtime5(cdate);
        // /����δ���õ���Ŀ

        userinfo.setFlag(new Integer(SysDefine.SYSTEM_HY_TYPE_NOR));
        userinfo.setHots(new Long(0));
        userinfo.setImg(new Integer(0));
        userinfo.setSetzt(new Integer(SysDefine.SYSTEM_HYSET_HYZT_WAIT));
        userinfo.setHttpip(request.getRemoteAddr());
        userinfo.setIsdel(new Integer(0));

        userinfo.setXgtz(xgtz01);
        userinfo.setYyap(yyap01);
        userinfo.setJyyx(new Long(jyyx01));

        userinfo.setLysize(Long.valueOf(uother.getJyly().length()));
        
      //�ж��ֻ����̶��绰��QQ�������Ƿ����ɧ�����Σ�����λ��ֵ��ʾ��ÿλ���������֣�0��1���ֱ�����ǲ����ͺ���ɧ�Ŵʡ���λ���ֱַ����ֻ����̶��绰��QQ������
        String shield="0000";
        String s1="0"; //�ֻ�
        String s2="0"; //�̶��绰
        String s3="0"; //QQ
        String s4="0"; //����
        Usernamecut usernamecut=null;
        List listt =HbmOperator.list("from Usernamecut");
        for(int i=0;i<listt.size();i++)
        {
			usernamecut=(Usernamecut)listt.get(i);
        	if(userinfo.getSjtel().indexOf(usernamecut.getUsernamecut())!=-1)
        	{
        		s1="1";
        	}
        	if(userinfo.getTel().indexOf(usernamecut.getUsernamecut())!=-1)
        	{
        		s2="1";
        	}
        	if(userinfo.getOicq().indexOf(usernamecut.getUsernamecut())!=-1)
        	{
        		s3="1";
        	}
        	if(userinfo.getEmail().indexOf(usernamecut.getUsernamecut())!=-1)
        	{
        		s4="1";
        	}
        }
        shield=s1+s2+s3+s4;
        userinfo.setShield(shield);

        // /��ʼ���棺
        try
        {

            Vector saveList = new Vector();
            MutSeaObject mso = new MutSeaObject();

            mso.setHbmSea(userinfo, MutSeaObject.SEA_HBM_UPDATE);
            saveList.add(mso);
            mso = new MutSeaObject();
            mso.setHbmSea(uother, MutSeaObject.SEA_HBM_UPDATE);
            saveList.add(mso);

            mso = new MutSeaObject();
            mso
                .setSqlSea(
                    "update SEND_SMS set REGSTATUS=0 where MOBILE_NUMBER='"
                        + userinfo.getUsername() + "'",
                    MutSeaObject.SEA_SQL_UPDATE);
            saveList.add(mso);

            String tjid = (String) request.getSession().getAttribute("tjid");
            List temptempList = HbmOperator
                .list("from UserBjd as o where o.hyid=" + tjid);
            if (temptempList == null || temptempList.size() == 0)
            {
                Userinfo uu = HYRegMng.getAllUserinfoByHyid(tjid + "");
                if (uu != null)
                {
                    UserBjd tempub = new UserBjd();
                    tempub.setBjdnumber(0);
                    tempub.setHyid(uu.getHyid());
                    HbmOperator.insert(tempub);
                }
            }

            if (tjid != null && tjid.length() > 0)
            {
                UserBjdRecord ubrt = new UserBjdRecord();
                ubrt.setBjddesc("�Ƽ���" + userinfo.getLcname() + ")hyid="
                    + userinfo.getHyid());
                ubrt.setBjdnumber(1);
                ubrt.setHyid(tjid);
                ubrt.setId(SysCommonFunc.getSequenceIdForOracle("SEQ_WTJD"));
                ubrt.setRecodeTime(new Date(System.currentTimeMillis()));
                mso = new MutSeaObject();
                mso.setHbmSea(ubrt, MutSeaObject.SEA_HBM_INSERT);
                saveList.add(mso);

                mso = new MutSeaObject();
                mso.setSqlSea(
                    "update user_bjd set bjdnumber=bjdnumber+1 where hyid ="
                        + tjid, MutSeaObject.SEA_SQL_UPDATE);
                saveList.add(mso);

            }

            HbmOperator.SeaMutData(saveList);
            
            //ע��ɹ����Ͷ��� 20150131 start
            //24Сʱ�ڲ����ظ�ע��
            List listl=QueryRecord.query("select count(1) as totalcount from USERINFO_SEND_REPLY u where u.SENDORREPLY=0 and u.MOBILEOREMAIL=0 and u.SENDTYPE=0 and u.STATUS=0 and u.HYID="+userinfo.getHyid()+" and u.SENDER='' and u.HDATE>=sysdate - 1");
			Boolean blfs=true;
			if (listt!=null && listt.size()==1){
				DynaBean dbt =(DynaBean)listt.get(0);
				if (Integer.parseInt(dbt.get("totalcount").toString())>0){
					blfs=false;
				}
			}
		   String ssreg = (String)request.getSession().getAttribute("ssreg");
		   String gssreg = request.getParameter("ssreg");

		   //System.out.println("sp="+sp+"---"+"gsp="+gsp+"--"+request.getRemoteAddr());
		   if(ssreg == null)
			   blfs=false;
		   if(gssreg == null)
			   blfs=false;
		   if(ssreg.length() ==0)
			   blfs=false;
		   if(!ssreg.equals(gssreg))
			   blfs=false;
		   request.getSession().removeAttribute("ssreg");
			if (blfs){
	            if (userinfo.getSjtel()!=null && !userinfo.getSjtel().equals("") && LoverTools.isMobileNO(userinfo.getSjtel())){
		            String contentString="@1@���ã�51����������վ��@2@ע���@3@��Ա��@4@������������ͬ���������@5@��Ա��@6@�����Ͽ���ϵ@7@�ǰɣ�";
	            	StringBuilder content=new StringBuilder();
		            content.append("@1@="+userinfo.getLcname());
		            contentString=contentString.replace("@1@", userinfo.getLcname());
		            content.append(",@2@="+userinfo.getS1()+userinfo.getS2());
		            contentString=contentString.replace("@2@", userinfo.getS1()+userinfo.getS2());
		            String sex="11";
		            if (userinfo.getSex().equals("11")){
		            	content.append(",@3@=��");
		            	contentString=contentString.replace("@3@", "��");
		            	sex="10";
		            }else{
		            	content.append(",@3@=Ů");
		            	contentString=contentString.replace("@3@", "Ů");
		            }
		            String sqlwhere="";
		            DynaBean dbt =null;
		            List list;
		            //����S1S2�������У�Ů����Ա����
		            sqlwhere=" and u.S1='"+userinfo.getS1()+"' and u.S2='"+userinfo.getS2()+"'";
		            sqlwhere+=" and u.SEX='"+sex+"'";
		            list=QueryRecord.query("select count(1) as totalcount from USERINFO u where u.isdel=0 "+sqlwhere);
		            if (list!=null && list.size()==1){
		            	dbt =(DynaBean)list.get(0);
		            	content.append(",@4@="+dbt.get("totalcount").toString());
		            	contentString=contentString.replace("@4@", dbt.get("totalcount").toString());
		            }else{
		            	content.append(",@4@=0");
		            	contentString=contentString.replace("@4@", "0");
		            }
		            
		            if (userinfo.getSex().equals("11")){
		            	content.append(",@5@=��");
		            	contentString=contentString.replace("@5@", "��");
		            }else{
		            	content.append(",@5@=Ů");
		            	contentString=contentString.replace("@5@", "Ů");
		            }
		            
		            //����S1S2�������У�Ů�����ҽ�������һ���Ļ�Ա����
		            StringBuilder jyyx=new StringBuilder();
		            
		            if (userinfo.getJyyx().toString().charAt(1)=='1'){
		            	jyyx.append(" or u.jyyx like '"+LoverTools.getMinJyyx(1)+"'");
		            }
		            if (userinfo.getJyyx().toString().charAt(2)=='1'){
		            	jyyx.append(" or u.jyyx like '"+LoverTools.getMinJyyx(2)+"'");
		            }
		            if (userinfo.getJyyx().toString().charAt(3)=='1'){
		            	jyyx.append(" or u.jyyx like '"+LoverTools.getMinJyyx(3)+"'");
		            }
		            if (userinfo.getJyyx().toString().charAt(4)=='1'){
		            	jyyx.append(" or u.jyyx like '"+LoverTools.getMinJyyx(4)+"'");
		            }
		            if (userinfo.getJyyx().toString().charAt(5)=='1'){
		            	jyyx.append(" or u.jyyx like '"+LoverTools.getMinJyyx(5)+"'");
		            }
		            //if (userinfo.getJyyx().toString().charAt(6)=='1'){
		            	//jyyx.append(" or u.jyyx like '"+LoverTools.getMinJyyx(7)+"'");
		            //}
		            //if (userinfo.getJyyx().toString().charAt(7)=='1'){
		            	//jyyx.append(" or u.jyyx like '"+LoverTools.getMinJyyx(7)+"'");
		            //}
		            
		            list.clear();
		            list=QueryRecord.query("select count(1) as totalcount from USERINFO u where u.isdel=0 "+sqlwhere+" and ("+jyyx.toString().replaceFirst("or", "")+")");
		            if (list!=null && list.size()==1){
		            	dbt =(DynaBean)list.get(0);
		            	content.append(",@6@="+dbt.get("totalcount").toString());
		            	contentString=contentString.replace("@6@", dbt.get("totalcount").toString());
		            }else{
		            	content.append(",@6@=0");
		            	contentString=contentString.replace("@6@", "0");
		            }
		            if (userinfo.getSex().equals("11")){
		            	content.append(",@7@=��");
		            	contentString=contentString.replace("@7@", "��");
		            }else{
		            	content.append(",@7@=��");
		            	contentString=contentString.replace("@7@", "��");
		            }
		            
		            UserInfoSendReply userInfoSendReply=new UserInfoSendReply();
	   				userInfoSendReply.setHyid(userinfo.getHyid());
	   				userInfoSendReply.setUsername(userinfo.getUsername());
	   				userInfoSendReply.setContent(contentString);
	   				userInfoSendReply.setHdate(new Date(System.currentTimeMillis()));
	   				userInfoSendReply.setSendorreply(0);
	   				userInfoSendReply.setSender("");
	   				userInfoSendReply.setSendtype(0);
	   				userInfoSendReply.setMobileoremail(0);
					userInfoSendReply.setMobileoremailc(userinfo.getSjtel());
					userInfoSendReply.setStatus(1);
					//Long id=UserInfoSendReplyMng.insertUserInfoSendReply(userInfoSendReply);
					Long id=SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_USERINFOSENDREPLY);
					userInfoSendReply.setId(id);
					HbmOperator.insert(userInfoSendReply);
					String result=SMSTools.sendMobileMb(userinfo.getSjtel(),content.toString(),"MB-2015013148");
					UserInfoSendReply newuserInfoSendReply=new UserInfoSendReply();
					newuserInfoSendReply=UserInfoSendReplyMng.getUserInfoSendReply(id);
					if (newuserInfoSendReply!=null){
						if (result.indexOf("#")<=0){
							newuserInfoSendReply.setStatus(Integer.parseInt(result));
							
						}else{
							newuserInfoSendReply.setStatus(0);
						}
						HbmOperator.update(newuserInfoSendReply);
					}
	            }
			}
            //ע��ɹ����Ͷ��� 20150131 end
            
            // /���û���Ϣ���뵽��½��Ϣ��
            request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME,
                userinfo);
            request.getSession().setAttribute(
                SysDefine.SESSION_LOGINNAME_OTHER, uother);

        }
        catch (Exception e)
        {
            System.out.println("ע���û�����" + e.getMessage());
            return "ע������������Ա��ϵ��";
        }

        return s;

    }
    
    //add by gaojianhong 20120820 for �û���ע��--�����û����Ϻͼ��� start
    public static final String userReg2NewUser(HttpServletRequest request)
    {
        String s = null;
        String hyid = SysCommonFunc.getStrParameter(request, "hyid");
        Userinfo userinfo = HYRegMng.getAllUserinfoByHyid(hyid);
        Userother uother = HYRegMng.getUserOtherByHyid(hyid);

        BeanInitializer.updateBean(userinfo, request);
        BeanInitializer.updateBean(uother, request);

        if (userinfo.getS1() == null || userinfo.getS1().trim().length() == 0)
            return "��ѡ��ʡ��";
        if (userinfo.getS2() == null || userinfo.getS2().trim().length() == 0)
            return "��ѡ�����";
        
        //�ж��ֻ������Ƿ�ע���
        if (isexitsmobile(userinfo.getSjtel(),Long.parseLong("0"))){
        	return "12�����ֻ�������������ע�ᣬ��ֱ�����ֻ���½�����˻���";
        }

        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");

        String xgtz01 = request.getParameter("xgtz01");
        String yyap01 = request.getParameter("yyap01");
        String jyyx01 = request.getParameter("jyyx01");

        userinfo.setCsdate(DateTools.stringToDate(year + "-" + month + "-"
            + day));
        Date cdate = new Date(System.currentTimeMillis());
        userinfo.setRegtime(cdate);
        userinfo.setRegtime2(cdate);
        userinfo.setRegtime3(cdate);
        userinfo.setLasttime(cdate);
        userinfo.setRegtime5(cdate);
        // /����δ���õ���Ŀ

        userinfo.setFlag(new Integer(SysDefine.SYSTEM_HY_TYPE_NOR));
        userinfo.setHots(new Long(0));
        userinfo.setImg(new Integer(0));
        userinfo.setSetzt(new Integer(SysDefine.SYSTEM_HYSET_HYZT_WAIT));
        userinfo.setHttpip(request.getRemoteAddr());
        userinfo.setIsdel(new Integer(0));

        userinfo.setXgtz(xgtz01);
        userinfo.setYyap(yyap01);
        userinfo.setJyyx(new Long(jyyx01));

        userinfo.setLysize(Long.valueOf(uother.getJyly().length()));
        
        //�ж��ֻ����̶��绰��QQ�������Ƿ����ɧ�����Σ�����λ��ֵ��ʾ��ÿλ���������֣�0��1���ֱ�����ǲ����ͺ���ɧ�Ŵʡ���λ���ֱַ����ֻ����̶��绰��QQ������
        String shield="0000";
        String s1="0"; //�ֻ�
        String s2="0"; //�̶��绰
        String s3="0"; //QQ
        String s4="0"; //����
        Usernamecut usernamecut=null;
        List listt =HbmOperator.list("from Usernamecut");
        for(int i=0;i<listt.size();i++)
        {
			usernamecut=(Usernamecut)listt.get(i);
        	if(userinfo.getSjtel().indexOf(usernamecut.getUsernamecut())!=-1)
        	{
        		s1="1";
        	}
        	if(userinfo.getTel().indexOf(usernamecut.getUsernamecut())!=-1)
        	{
        		s2="1";
        	}
        	if(userinfo.getOicq().indexOf(usernamecut.getUsernamecut())!=-1)
        	{
        		s3="1";
        	}
        	if(userinfo.getEmail().indexOf(usernamecut.getUsernamecut())!=-1)
        	{
        		s4="1";
        	}
        }
        shield=s1+s2+s3+s4;
        userinfo.setShield(shield);

        // /��ʼ���棺
        try
        {

            Vector saveList = new Vector();
            MutSeaObject mso = new MutSeaObject();

            mso.setHbmSea(userinfo, MutSeaObject.SEA_HBM_UPDATE);
            saveList.add(mso);
            mso = new MutSeaObject();
            mso.setHbmSea(uother, MutSeaObject.SEA_HBM_UPDATE);
            saveList.add(mso);

            String tjid = (String) request.getSession().getAttribute("tjid");
            List temptempList = HbmOperator
                .list("from UserBjd as o where o.hyid=" + tjid);
            if (temptempList == null || temptempList.size() == 0)
            {
                Userinfo uu = HYRegMng.getAllUserinfoByHyid(tjid + "");
                if (uu != null)
                {
                    UserBjd tempub = new UserBjd();
                    tempub.setBjdnumber(0);
                    tempub.setHyid(uu.getHyid());
                    HbmOperator.insert(tempub);
                }
            }

            if (tjid != null && tjid.length() > 0)
            {
                UserBjdRecord ubrt = new UserBjdRecord();
                ubrt.setBjddesc("�Ƽ���" + userinfo.getLcname() + ")hyid="
                    + userinfo.getHyid());
                ubrt.setBjdnumber(0);
                ubrt.setHyid(tjid);
                ubrt.setId(SysCommonFunc.getSequenceIdForOracle("SEQ_WTJD"));
                ubrt.setRecodeTime(new Date(System.currentTimeMillis()));
                mso = new MutSeaObject();
                mso.setHbmSea(ubrt, MutSeaObject.SEA_HBM_INSERT);
                saveList.add(mso);

                mso = new MutSeaObject();
                mso.setSqlSea(
                    "update user_bjd set bjdnumber=bjdnumber where hyid ="
                        + tjid, MutSeaObject.SEA_SQL_UPDATE);
                saveList.add(mso);

            }

            HbmOperator.SeaMutData(saveList);
            
            //ע��ɹ����Ͷ��� 20150131 start
            //24Сʱ�ڲ����ظ�ע��
            List listl=QueryRecord.query("select count(1) as totalcount from USERINFO_SEND_REPLY u where u.SENDORREPLY=0 and u.MOBILEOREMAIL=0 and u.SENDTYPE=0 and u.STATUS=0 and u.HYID="+userinfo.getHyid()+" and u.SENDER='' and u.HDATE>=sysdate - 1");
			Boolean blfs=true;
			if (listt!=null && listt.size()==1){
				DynaBean dbt =(DynaBean)listt.get(0);
				if (Integer.parseInt(dbt.get("totalcount").toString())>0){
					blfs=false;
				}
			}
		   String ssreg = (String)request.getSession().getAttribute("ssreg");
		   String gssreg = request.getParameter("ssreg");

		   //System.out.println("sp="+sp+"---"+"gsp="+gsp+"--"+request.getRemoteAddr());
		   if(ssreg == null)
			   blfs=false;
		   if(gssreg == null)
			   blfs=false;
		   if(ssreg.length() ==0)
			   blfs=false;
		   if(!ssreg.equals(gssreg))
			   blfs=false;
		   request.getSession().removeAttribute("ssreg");
			if (blfs){
	            if (userinfo.getSjtel()!=null && !userinfo.getSjtel().equals("") && LoverTools.isMobileNO(userinfo.getSjtel())){
		            String contentString="@1@���ã�51����������վ��@2@ע���@3@��Ա��@4@������������ͬ���������@5@��Ա��@6@�����Ͽ���ϵ@7@�ǰɣ�";
	            	StringBuilder content=new StringBuilder();
		            content.append("@1@="+userinfo.getLcname());
		            contentString=contentString.replace("@1@", userinfo.getLcname());
		            content.append(",@2@="+userinfo.getS1()+userinfo.getS2());
		            contentString=contentString.replace("@2@", userinfo.getS1()+userinfo.getS2());
		            String sex="11";
		            if (userinfo.getSex().equals("11")){
		            	content.append(",@3@=��");
		            	contentString=contentString.replace("@3@", "��");
		            	sex="10";
		            }else{
		            	content.append(",@3@=Ů");
		            	contentString=contentString.replace("@3@", "Ů");
		            }
		            String sqlwhere="";
		            DynaBean dbt =null;
		            List list;
		            //����S1S2�������У�Ů����Ա����
		            sqlwhere=" and u.S1='"+userinfo.getS1()+"' and u.S2='"+userinfo.getS2()+"'";
		            sqlwhere+=" and u.SEX='"+sex+"'";
		            list=QueryRecord.query("select count(1) as totalcount from USERINFO u where u.isdel=0 "+sqlwhere);
		            if (list!=null && list.size()==1){
		            	dbt =(DynaBean)list.get(0);
		            	content.append(",@4@="+dbt.get("totalcount").toString());
		            	contentString=contentString.replace("@4@", dbt.get("totalcount").toString());
		            }else{
		            	content.append(",@4@=0");
		            	contentString=contentString.replace("@4@", "0");
		            }
		            
		            if (userinfo.getSex().equals("11")){
		            	content.append(",@5@=��");
		            	contentString=contentString.replace("@5@", "��");
		            }else{
		            	content.append(",@5@=Ů");
		            	contentString=contentString.replace("@5@", "Ů");
		            }
		            
		            //����S1S2�������У�Ů�����ҽ�������һ���Ļ�Ա����
		            StringBuilder jyyx=new StringBuilder();
		            
		            if (userinfo.getJyyx().toString().charAt(1)=='1'){
		            	jyyx.append(" or u.jyyx like '"+LoverTools.getMinJyyx(1)+"'");
		            }
		            if (userinfo.getJyyx().toString().charAt(2)=='1'){
		            	jyyx.append(" or u.jyyx like '"+LoverTools.getMinJyyx(2)+"'");
		            }
		            if (userinfo.getJyyx().toString().charAt(3)=='1'){
		            	jyyx.append(" or u.jyyx like '"+LoverTools.getMinJyyx(3)+"'");
		            }
		            if (userinfo.getJyyx().toString().charAt(4)=='1'){
		            	jyyx.append(" or u.jyyx like '"+LoverTools.getMinJyyx(4)+"'");
		            }
		            if (userinfo.getJyyx().toString().charAt(5)=='1'){
		            	jyyx.append(" or u.jyyx like '"+LoverTools.getMinJyyx(5)+"'");
		            }
		            //if (userinfo.getJyyx().toString().charAt(6)=='1'){
		            	//jyyx.append(" or u.jyyx like '"+LoverTools.getMinJyyx(7)+"'");
		            //}
		            //if (userinfo.getJyyx().toString().charAt(7)=='1'){
		            	//jyyx.append(" or u.jyyx like '"+LoverTools.getMinJyyx(7)+"'");
		            //}
		            
		            list.clear();
		            list=QueryRecord.query("select count(1) as totalcount from USERINFO u where u.isdel=0 "+sqlwhere+" and ("+jyyx.toString().replaceFirst("or", "")+")");
		            if (list!=null && list.size()==1){
		            	dbt =(DynaBean)list.get(0);
		            	content.append(",@6@="+dbt.get("totalcount").toString());
		            	contentString=contentString.replace("@6@", dbt.get("totalcount").toString());
		            }else{
		            	content.append(",@6@=0");
		            	contentString=contentString.replace("@6@", "0");
		            }
		            if (userinfo.getSex().equals("11")){
		            	content.append(",@7@=��");
		            	contentString=contentString.replace("@7@", "��");
		            }else{
		            	content.append(",@7@=��");
		            	contentString=contentString.replace("@7@", "��");
		            }
		            
		            UserInfoSendReply userInfoSendReply=new UserInfoSendReply();
	   				userInfoSendReply.setHyid(userinfo.getHyid());
	   				userInfoSendReply.setUsername(userinfo.getUsername());
	   				userInfoSendReply.setContent(contentString);
	   				userInfoSendReply.setHdate(new Date(System.currentTimeMillis()));
	   				userInfoSendReply.setSendorreply(0);
	   				userInfoSendReply.setSender("");
	   				userInfoSendReply.setSendtype(0);
	   				userInfoSendReply.setMobileoremail(0);
					userInfoSendReply.setMobileoremailc(userinfo.getSjtel());
					userInfoSendReply.setStatus(1);
					//Long id=UserInfoSendReplyMng.insertUserInfoSendReply(userInfoSendReply);
					Long id=SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_USERINFOSENDREPLY);
					userInfoSendReply.setId(id);
					HbmOperator.insert(userInfoSendReply);
					String result=SMSTools.sendMobileMb(userinfo.getSjtel(),content.toString(),"MB-2015013148");
					UserInfoSendReply newuserInfoSendReply=new UserInfoSendReply();
					newuserInfoSendReply=UserInfoSendReplyMng.getUserInfoSendReply(id);
					if (newuserInfoSendReply!=null){
						if (result.indexOf("#")<=0){
							newuserInfoSendReply.setStatus(Integer.parseInt(result));
							
						}else{
							newuserInfoSendReply.setStatus(0);
						}
						HbmOperator.update(newuserInfoSendReply);
					}
	            }
			}
            //ע��ɹ����Ͷ��� 20150131 end
			
            // /���û���Ϣ���뵽��½��Ϣ��
            request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME,
                userinfo);
            request.getSession().setAttribute(
                SysDefine.SESSION_LOGINNAME_OTHER, uother);

        }
        catch (Exception e)
        {
            System.out.println("ע���û�����" + e.getMessage());
            return "ע������������Ա��ϵ��";
        }

        return s;

    }
    //add by gaojianhong 20120820 for �û���ע��--�����û����Ϻͼ��� end

    public static Userinfo isHaveUser(String username)
    {
        Userinfo user = null;
        if (username == null || username.trim().length() == 0)
            return user;
        List list = HbmOperator.list("from Userinfo as o where o.username='"
            + username + "' and o.isdel=0");
        if (list == null || list.size() == 0)
            return user;
        return (Userinfo) list.get(0);
    }

    public static Userinfo getUserinfoByHyid(String hyid)
    {
        Userinfo user = null;

        List list = HbmOperator.list("from Userinfo as o where o.hyid=" + hyid
            + " and o.isdel =0");
        if (list == null || list.size() == 0)
            return user;
        return (Userinfo) list.get(0);
    }

    public static Userinfo getAllUserinfoByHyid(String hyid)
    {
        Userinfo user = null;

        List list = HbmOperator.list("from Userinfo as o where o.hyid=" + hyid);
        if (list == null || list.size() == 0)
            return user;
        return (Userinfo) list.get(0);
    }

    public static Userinfo getDelUserInfoByHyid(String hyid)
    {
        Userinfo user = null;

        List list = HbmOperator.list("from Userinfo as o where o.hyid=" + hyid
            + " and o.isdel =1");
        if (list == null || list.size() == 0)
            return user;
        return (Userinfo) list.get(0);

    }

    public static Userinfo getUserinfoByUsername(String username)
    {
        Userinfo user = null;

        List list = HbmOperator.list("from Userinfo as o where o.username='"
            + username + "' and o.isdel =0");
        if (list == null || list.size() == 0)
            return user;

        return (Userinfo) list.get(0);

    }
    public static Userinfo getAnyUserinfoByUsername(String username)
    {
    	Userinfo user = null;
    	
    	List list = HbmOperator.list("from Userinfo as o where o.username='"
    			+ username + "' and o.isdel in (0,2)");
    	if (list == null || list.size() == 0)
    		return user;
    	
    	return (Userinfo) list.get(0);
    	
    }
    
    //add by gaojianhong 20120820 for �ж��ֻ������Ƿ�ע�� start
    public static Userinfo getUserinfoBySjtel(String sjtel)
    {
        Userinfo user = null;

        List list = HbmOperator.list("from Userinfo as o where o.sjtel='"
            + sjtel + "' and o.isdel =0 and o.isvcation=1 and o.vacsjtel=1");
        if (list == null || list.size() == 0)
            return user;

        return (Userinfo) list.get(0);

    }
   //add by gaojianhong 20120820 for �ж��ֻ������Ƿ�ע�� end

    public static Userinfo getAllUserinfoByUsername(String username)
    {
        Userinfo user = null;

        List list = HbmOperator.list("from Userinfo as o where o.username='"
            + username + "'");
        if (list == null || list.size() == 0)
            return user;

        return (Userinfo) list.get(0);

    }

    public static Userother getUserOtherByHyid(String hyid)
    {
        Userother uother = null;

        List list = HbmOperator
            .list("from Userother as o where o.hyid=" + hyid);
        if (list == null || list.size() == 0)
            return uother;
        return (Userother) list.get(0);

    }

    public static String userLogin(String username, String password,
        HttpServletRequest request)
    {
        // /�����û����ֲ�ѯ�û���Ϣ ע���Ƿ���ɾ���û� ISDEL
        // /��½�ɹ��޸�����½ʱ��
        // /��ѯVIP �û���Ϣ

        String s = null;
        if (username.length() == 0 || password.length() == 0){
            
           return s;
        }
        List list = HbmOperator.list("from Userinfo as o where o.username='"
            + username + "' and o.isdel in (0,2)");
        if (list == null || list.size() == 0)
            //return "�û������󣡽���ע������û���ӦΪ��֤�����ֻ����룬�ϻ�Ա��ԭ�����û�����¼��";
        	return "�û�������򲻴��ڸ��û�����";
            
        Userinfo user = (Userinfo) list.get(0);
        if (user.getPassword() == null || !user.getPassword().equals(password))
            //return "������󣡽���ע������û���ӦΪ��֤�����ֻ����룬�ϻ�Ա��ԭ�����û�����¼��";
        	return "�������";
        Date cdate = new Date(System.currentTimeMillis());
        user.setLasttime(cdate);
        user.setRegtime5(cdate);

        if (user.getSex() != null && user.getSex().equals("11"))
        {
            // user.setRegtime(cdate);
            user.setRegtime2(cdate);
        }

        if (user.getFlag() != null
            && (user.getFlag().intValue() == SysDefine.SYSTEM_HY_TYPE_vip || user.getFlag().intValue() == SysDefine.SYSTEM_HY_TYPE_nvip))
        {
            // System.out.println("�����ж�");
            if (user.getZzsj() == null)
                user.setFlag(new Integer(SysDefine.SYSTEM_HY_TYPE_NOR));
            else if (user.getZzsj().before(cdate))
                user.setFlag(new Integer(SysDefine.SYSTEM_HY_TYPE_NOR));
        }

        Userlogin userLogin = null;
        Userlogin ulogin = null;
        List loginList = HbmOperator
            .list("from Userlogin as o where o.loginHyId='" + user.getHyid()
                + "'");
        if(loginList !=null && loginList.size()>0){
            ulogin  = (Userlogin) loginList.get(0);
        }
        if (loginList == null || loginList.size() == 0)
        {
            userLogin = new Userlogin();
            userLogin.setId(SysCommonFunc
                .getSequenceIdForOracle("seq_userlogin_id"));
            userLogin.setLoginHyId(user.getHyid());
            userLogin.setLoginLcname(user.getLcname());
            userLogin.setLoginTime(cdate);
            userLogin.setSex(user.getSex());
            userLogin.setS1(user.getS1());
            userLogin.setS2(user.getS2());
        }
        else
        {
            ulogin.setLoginTime(new Date(System.currentTimeMillis()));
        }

        Ipuserinfo ipuser = new Ipuserinfo();
        ipuser.setId(SysCommonFunc.getSequenceIdForOracle("SEQ_IPUSERINFO"));
        ipuser.setUsername(user.getUsername());
        ipuser.setIpaddress(request.getRemoteAddr());
        ipuser.setLogintime(new Date(System.currentTimeMillis()));

        try
        {
            HbmOperator.update(user);
            HbmOperator.insert(ipuser);
            if (loginList == null || loginList.size() == 0)
            {
                HbmOperator.insert(userLogin);
            }
            else
            {
                HbmOperator.update(ulogin);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        list = HbmOperator.list("from Userother as o where o.hyid="
            + user.getHyid().longValue());
        Userother userother = null;
        if (list != null && list.size() > 0)
            userother = (Userother) list.get(0);
        request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME, user);
        request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME_OTHER,
            userother);
        return s;
    }

    /**
     * ������Ϣ�޸�
     */
    public static String updateUserinfo(HttpServletRequest request)
    {
        String s = null;
        Userinfo sessionUserinfo = (Userinfo) request.getSession()
            .getAttribute(SysDefine.SESSION_LOGINNAME);
        if (sessionUserinfo == null)
            return "��¼ʧЧ�������µ�¼";
        Userinfo userinfoDb = getUserinfoByHyid(sessionUserinfo.getHyid()
            .longValue()
            + "");
        String oldsjtelString=userinfoDb.getSjtel();  //δ����ǰ�ֻ�����
        Date cdate = new Date(System.currentTimeMillis());

        userinfoDb.setRegtime3(cdate);
        userinfoDb.setRegtime2(cdate);
        Userother uother = HYRegMng.getUserOtherByHyid(sessionUserinfo.getHyid().longValue() + "");
        String csdate = request.getParameter("csdate2");
        userinfoDb.setCsdate(DateTools.stringToDate(csdate));
        BeanInitializer.updateBean(userinfoDb, request);
        if (userinfoDb.getS1() == null
            || userinfoDb.getS1().trim().length() == 0)
            return "��ѡ��ʡ��";
        if (userinfoDb.getS2() == null
            || userinfoDb.getS2().trim().length() == 0)
            return "��ѡ�����";
        if (userinfoDb.getIsdel() == null)
            userinfoDb.setIsdel(new Integer(0));
        
        //�ж��ֻ������Ƿ��ظ�
        if (!oldsjtelString.equals(userinfoDb.getSjtel())){
        	if (isexitsmobile(userinfoDb.getSjtel(), sessionUserinfo.getHyid().longValue())){
        		return "��������ֻ������ѱ���������ע�ᣬ���������ֻ����룡";
        	}
        }
        
        String yyap01 = request.getParameter("yyap01");
        String xgtz01 = request.getParameter("xgtz01");
        String jyyx01 = request.getParameter("jyyx01");
        userinfoDb.setYyap(yyap01);
        userinfoDb.setXgtz(xgtz01);
        userinfoDb.setJyyx(new Long(jyyx01));
        
        //�ж��ֻ����̶��绰��QQ�������Ƿ����ɧ�����Σ�����λ��ֵ��ʾ��ÿλ���������֣�0��1���ֱ�����ǲ����ͺ���ɧ�Ŵʡ���λ���ֱַ����ֻ����̶��绰��QQ������
        String shield="0000";
        String s1="0"; //�ֻ�
        String s2="0"; //�̶��绰
        String s3="0"; //QQ
        String s4="0"; //����
        Usernamecut usernamecut=null;
        List listt =HbmOperator.list("from Usernamecut");
        if(listt!=null && listt.size()>0){
        for(int i=0;i<listt.size();i++)
        {
			usernamecut=(Usernamecut)listt.get(i);
        	if(userinfoDb.getSjtel().indexOf(usernamecut.getUsernamecut())!=-1)
        	{
        		s1="1";
        	}
        	if(userinfoDb.getTel().indexOf(usernamecut.getUsernamecut())!=-1)
        	{
        		s2="1";
        	}
        	if(userinfoDb.getOicq().indexOf(usernamecut.getUsernamecut())!=-1)
        	{
        		s3="1";
        	}
        	if(userinfoDb.getEmail().indexOf(usernamecut.getUsernamecut())!=-1)
        	{
        		s4="1";
        	}
        }
        }
        shield=s1+s2+s3+s4;
        userinfoDb.setShield(shield);

        boolean uoNew = false;
        if (uother == null)
        {
            uoNew = true;
            uother = (Userother) BeanInitializer.initBean(Userother.class,
                request);
            uother.setHyid(userinfoDb.getHyid());
            uother.setUsername(userinfoDb.getUsername());
        }
        else
        {
            BeanInitializer.updateBean(uother, request);
        }

        userinfoDb.setLysize(Long.valueOf(uother.getJyly().length()));
        try
        {
            Vector saveList = new Vector();
            MutSeaObject mso = new MutSeaObject();
            mso.setHbmSea(userinfoDb, MutSeaObject.SEA_HBM_UPDATE);
            saveList.add(mso);

            mso = new MutSeaObject();
            if (uoNew)
                mso.setHbmSea(uother, MutSeaObject.SEA_HBM_INSERT);
            else
                mso.setHbmSea(uother, MutSeaObject.SEA_HBM_UPDATE);
            saveList.add(mso);

            HbmOperator.SeaMutData(saveList);

            // /���û���Ϣ���뵽��½��Ϣ��
            request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME,
                userinfoDb);
            request.getSession().setAttribute(
                SysDefine.SESSION_LOGINNAME_OTHER, uother);

        }
        catch (Exception e)
        {
            s = "�޸ĸ������ϳ����������Ա��ϵ��";
            System.out.println(e.getMessage());
        }

        return s;
    }
    
    /**
     * �ֻ���֤
     */
    public static String updateSjyz(HttpServletRequest request)
    {
        String s = null;
        Userinfo sessionUserinfo = (Userinfo) request.getSession()
            .getAttribute(SysDefine.SESSION_LOGINNAME);
        if (sessionUserinfo == null)
            return "��¼ʧЧ�������µ�¼";
        
        Userinfo userinfoDb = getUserinfoByHyid(sessionUserinfo.getHyid()
                .longValue()
                + "");
        userinfoDb.setIsvcation(new Long(1));
        userinfoDb.setVacsjtel(new Integer(1));
        userinfoDb.setSjtel(request.getParameter("sjtel"));
        
        // /������֤����
        String verycode = request.getParameter("verycode");
        List smsList = HbmOperator
            .list("from SendSms as o where o.mobileNumber='"
                + request.getParameter("sjtel") + "' and o.veryCode='" + verycode
                + "' and o.vcation=0 order by id desc");
        if (smsList == null || smsList.size() == 0)
            return "������Ķ�����֤�벻��ȷ";
        else{
        	
        	try {
        		Vector saveList = new Vector();
        		
        		//�����û���֤
                MutSeaObject mso = new MutSeaObject();
                mso.setHbmSea(userinfoDb, MutSeaObject.SEA_HBM_UPDATE);
                saveList.add(mso);
                
                //���¶�����֤״̬
                SendSms ssms = (SendSms) smsList.get(0);
        		ssms.setVcation(new Long(1));
        		mso = new MutSeaObject();
        		mso.setHbmSea(ssms, MutSeaObject.SEA_HBM_UPDATE);
        		saveList.add(mso);
        		
        		//�����Ƽ��˰׽�
        		List userbjdList=QueryRecord.query("select * from USER_BJD_RECORD o where o.bjdnumber=0 and o.BJDDESC like '%hyid="+sessionUserinfo.getHyid().longValue()+"%'");
	    		if (userbjdList!=null && userbjdList.size()==1){
	    			DynaBean dbt =null;
	    			dbt =(DynaBean)userbjdList.get(0);
	    			List userbjdrecordList = HbmOperator.list("from UserBjdRecord as o where o.id="+dbt.get("id"));
	    			
	    			if (userbjdrecordList!=null && userbjdrecordList.size()==1){
		    			UserBjdRecord userBjd=(UserBjdRecord)userbjdrecordList.get(0);
		    			userBjd.setBjdnumber(1);
		    			mso = new MutSeaObject();
		    			mso.setHbmSea(userBjd, MutSeaObject.SEA_HBM_UPDATE);
		        		saveList.add(mso);
		        		
		        		//�����Ƽ��˰׽�����
		        		mso = new MutSeaObject();
		                mso.setSqlSea(
		                    "update user_bjd set bjdnumber=bjdnumber+1 where hyid ="
		                        + userBjd.getHyid(), MutSeaObject.SEA_SQL_UPDATE);
		                saveList.add(mso);
	    			}
	    		}
                    
                
                HbmOperator.SeaMutData(saveList);
				//HbmOperator.update(userinfoDb);
				// /���û���Ϣ���뵽��½��Ϣ��
	            request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME,userinfoDb);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				s = "�ֻ���֤�����������Ա��ϵ��";
				e.printStackTrace();
			}
        }
        
        return s;
    }
    
    /**
     * �ֻ���¼  added by gaojianhong 20150308
     */
    public static String updateSjdl(HttpServletRequest request)
    {
        String s = null;
        // /������֤����
        String verycode = request.getParameter("verycode");
        List smsList = HbmOperator
            .list("from SendSms as o where o.mobileNumber='"
                + request.getParameter("sjtel") + "' and o.veryCode='" + verycode
                + "' and o.vcation=0 order by id desc");
        if (smsList == null || smsList.size() == 0)
            return "������Ķ�����֤�벻��ȷ";
        else{
        	
        	try {
        		Vector saveList = new Vector();
        		
        		//�ж��Ƿ��������֤�û�
        		List userList = HbmOperator
                .list("from Userinfo as o where o.sjtel='"
                    + request.getParameter("sjtel") + "' and o.isvcation=1 and o.vacsjtel=1 and o.isdel=0 order by regtime desc");
        		if (userList!=null && userList.size()>0){
        			Userinfo userinfo=(Userinfo)userList.get(0);
        			//���µ�¼ʱ��
        			Date cdate = new Date(System.currentTimeMillis());
        			userinfo.setLasttime(cdate);
        			userinfo.setRegtime5(cdate);

        	        if (userinfo.getSex() != null && userinfo.getSex().equals("11"))
        	        {
        	            // user.setRegtime(cdate);
        	        	userinfo.setRegtime2(cdate);
        	        }
        	        MutSeaObject mso = new MutSeaObject();
                    mso.setHbmSea(userinfo, MutSeaObject.SEA_HBM_UPDATE);
                    saveList.add(mso);
                    
                    //���¶�����֤״̬
                    SendSms ssms = (SendSms) smsList.get(0);
            		ssms.setVcation(new Long(1));
            		mso = new MutSeaObject();
            		mso.setHbmSea(ssms, MutSeaObject.SEA_HBM_UPDATE);
            		saveList.add(mso);
                    
                    HbmOperator.SeaMutData(saveList);
        			//��ȡ�ﲨ���ͱ����ƥ��Ļ�Ա
        			updateQiubo(userinfo);
        			// /���û���Ϣ���뵽��½��Ϣ��
    	            request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME,userinfo);
                    Userother userother = HYRegMng.getUserOtherByHyid(userinfo
                            .getHyid().longValue()
                            + "");
                    request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME_OTHER,
                            userother);
        		}else{
        			List userList1 = HbmOperator
                    .list("from Userinfo as o where o.sjtel='"
                        + request.getParameter("sjtel") + "' and o.vacsjtel=0 and o.isdel=0 order by regtime desc");
        			if (userList1!=null && userList1.size()>0){
            			Userinfo userinfo=(Userinfo)userList1.get(0);
            			
            			//�����û���֤״̬����¼ʱ��
            			userinfo.setIsvcation(new Long(1));
            			userinfo.setVacsjtel(new Integer(1));
            			Date cdate = new Date(System.currentTimeMillis());
            			userinfo.setLasttime(cdate);
            			userinfo.setRegtime5(cdate);

            	        if (userinfo.getSex() != null && userinfo.getSex().equals("11"))
            	        {
            	            // user.setRegtime(cdate);
            	        	userinfo.setRegtime2(cdate);
            	        }
            			
            			MutSeaObject mso = new MutSeaObject();
                        mso.setHbmSea(userinfo, MutSeaObject.SEA_HBM_UPDATE);
                        saveList.add(mso);
                        
                        //���¶�����֤״̬
                        SendSms ssms = (SendSms) smsList.get(0);
                		ssms.setVcation(new Long(1));
                		mso = new MutSeaObject();
                		mso.setHbmSea(ssms, MutSeaObject.SEA_HBM_UPDATE);
                		saveList.add(mso);

                        HbmOperator.SeaMutData(saveList);
                        
                        //��ȡ�ﲨ���ͱ����ƥ��Ļ�Ա
            			updateQiubo(userinfo);
                        
            			// /���û���Ϣ���뵽��½��Ϣ��
        	            request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME,userinfo);
                        Userother userother = HYRegMng.getUserOtherByHyid(userinfo
                                .getHyid().longValue()
                                + "");
                        request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME_OTHER,
                                userother);
        			} 
        		}
        		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				s = "�ֻ���¼�����������Ա��ϵ��";
				e.printStackTrace();
			}
        }
        
        return s;
    }
    

    /**
     * �ֻ���¼  added by linyu 20151001 �ֻ����û��ע��,���¼���̾���ע�����
     */
    public static String updateSjdlNew(HttpServletRequest request)
    {
        String s = null;
        // /������֤����
        String verycode = request.getParameter("verycode");
        List smsList = HbmOperator
            .list("from SendSms as o where o.mobileNumber='"
                + request.getParameter("sjtel") + "' and o.veryCode='" + verycode
                + "' and o.vcation=0 order by id desc");
        if (smsList == null || smsList.size() == 0)
            return "������Ķ�����֤�벻��ȷ";
        else{
        	
        	try {
        		Vector saveList = new Vector();
        		
        		//�ж��Ƿ��������֤�û�
        		List userList = HbmOperator
                .list("from Userinfo as o where o.sjtel='"
                    + request.getParameter("sjtel") + "' and o.isvcation=1 and o.vacsjtel=1 and o.isdel=0 order by regtime desc");
        		if (userList!=null && userList.size()>0){
        			Userinfo userinfo=(Userinfo)userList.get(0);
        			//���µ�¼ʱ��
        			Date cdate = new Date(System.currentTimeMillis());
        			userinfo.setLasttime(cdate);
        			userinfo.setRegtime5(cdate);

        	        if (userinfo.getSex() != null && userinfo.getSex().equals("11"))
        	        {
        	            // user.setRegtime(cdate);
        	        	userinfo.setRegtime2(cdate);
        	        }
        	        if(userinfo.getIsvcation() == null || userinfo.getIsvcation() !=1){
        	        	userinfo.setIsvcation(1l);
        	        }
        	        
        	        MutSeaObject mso = new MutSeaObject();
                    mso.setHbmSea(userinfo, MutSeaObject.SEA_HBM_UPDATE);
                    saveList.add(mso);
                    
                    //���¶�����֤״̬
                    SendSms ssms = (SendSms) smsList.get(0);
            		ssms.setVcation(new Long(1));
            		mso = new MutSeaObject();
            		mso.setHbmSea(ssms, MutSeaObject.SEA_HBM_UPDATE);
            		saveList.add(mso);
                    
                    HbmOperator.SeaMutData(saveList);
        			//��ȡ�ﲨ���ͱ����ƥ��Ļ�Ա
        			updateQiubo(userinfo);
        			// /���û���Ϣ���뵽��½��Ϣ��
    	            request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME,userinfo);
                    Userother userother = HYRegMng.getUserOtherByHyid(userinfo
                            .getHyid().longValue()
                            + "");
                    request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME_OTHER,
                            userother);
        		}else{
        			List userList1 = HbmOperator
                    .list("from Userinfo as o where o.sjtel='"
                        + request.getParameter("sjtel") + "' and o.vacsjtel=0 and o.isdel in (0,2) order by regtime desc");
        			if (userList1!=null && userList1.size()>0){
            			Userinfo userinfo=(Userinfo)userList1.get(0);
            			
            			//�����û���֤״̬����¼ʱ��
            			userinfo.setIsvcation(new Long(1));
            			userinfo.setVacsjtel(new Integer(1));
            			Date cdate = new Date(System.currentTimeMillis());
            			userinfo.setLasttime(cdate);
            			userinfo.setRegtime5(cdate);

            	        if (userinfo.getSex() != null && userinfo.getSex().equals("11"))
            	        {
            	            // user.setRegtime(cdate);
            	        	userinfo.setRegtime2(cdate);
            	        }
            			
            			MutSeaObject mso = new MutSeaObject();
                        mso.setHbmSea(userinfo, MutSeaObject.SEA_HBM_UPDATE);
                        saveList.add(mso);
                        
                        //���¶�����֤״̬
                        SendSms ssms = (SendSms) smsList.get(0);
                		ssms.setVcation(new Long(1));
                		mso = new MutSeaObject();
                		mso.setHbmSea(ssms, MutSeaObject.SEA_HBM_UPDATE);
                		saveList.add(mso);

                        HbmOperator.SeaMutData(saveList);
                        
                        //��ȡ�ﲨ���ͱ����ƥ��Ļ�Ա
            			updateQiubo(userinfo);
                        
            			// /���û���Ϣ���뵽��½��Ϣ��
        	            request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME,userinfo);
                        Userother userother = HYRegMng.getUserOtherByHyid(userinfo
                                .getHyid().longValue()
                                + "");
                        request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME_OTHER,
                                userother);
        			}
        			 //add by linyu 20151001 �ֻ����û��ע�ᣨ���û������ֻ����붼û�У���ֱ�Ӷ��ŵ�½ҳ����¼���̾���ע��Ĺ��̣��û���Ϊ�ֻ�����
        			else{
        				
        			  //s = sjRegNewUser(request,request.getParameter("sjtel"));
        				//zxlzdd is not regist please regist;
        			    s="1";
        			}
        		}
        		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				s = "�ֻ���¼�����������Ա��ϵ��";
				e.printStackTrace();
			}
        }
        
        return s;
    }
    
    /**
     * �û���¼ʱ��ȡ�ﲨ���ͱ����ƥ��Ļ�Ա
     * @param userinfo
     */
    public static void updateQiubo(Userinfo userinfo){
    	//��ȡ�ﲨ���ͱ����ƥ��Ļ�Ա
    	if (userinfo.getSex()!=null && userinfo.getSex().equals("10")){
    		List list = null;
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(userinfo.getCsdate());
    		int start = cal.get(Calendar.YEAR)-4;
    		int end = cal.get(Calendar.YEAR)+15;
    		String sDate=start+"-01-01";
    		String eDate=end+"-12-31";
    		
    		Date lastTime=null;
		    if (userinfo!=null){
		   	  lastTime=userinfo.getLasttime();
		    }
       		String sql = "select q.*,u.USERNAME,u.HYID from Db_QiuboFs q join USERINFO u on q.SENDUSERID=u.HYID where q.sendtime >= to_date('"+DateTools.dateToString(lastTime,true)+"','YYYY-MM-DD HH24:MI:SS') ";
       		sql = sql + " and u.CSDATE>=to_date('"+sDate+"','YYYY-MM-DD') and u.CSDATE<=to_date('"+eDate+"','YYYY-MM-DD') and u.s1='"+userinfo.getS1()+"' and u.sex='11' order by q.id asc";
       		list = QueryRecord.query(sql);
       		for(int i=0;i<list.size();i++){
       			DynaBean dbl=new LazyDynaBean();
       			Userinfo sendUser=new Userinfo();
       			dbl=(DynaBean)list.get(i);
       			sendUser.setHyid(Long.parseLong(((BigDecimal)dbl.get("hyid")).toString()));
       			sendUser.setUsername((String)dbl.get("username"));
       			GRZQMng.addQiuboAuto(sendUser,userinfo.getHyid().toString(),(Date)dbl.get("sendtime"));
       		}
    	}
    }

    public static String adminModUserInfo(HttpServletRequest request,
        Admininfo admin, Userinfo user, Userother uother)
    {
        String s = null;
        try
        {
            Vector saveList = new Vector();
            // // ��¼�޸���־��
            StringBuffer logs = new StringBuffer();
            logs.append("ԭʼ����<br>");
            OpLogMng.logUserinfo(user, uother, logs);

            Userinfo srcUser = new Userinfo();
            Userother srcOhter = new Userother();
            BeanUtils.copyProperties(srcUser, user);
            BeanUtils.copyProperties(srcOhter, uother);

            BeanInitializer.updateBean(user, request);
            user.setJyyx(new Long(request.getParameter("jyyx01")));
            Date cdate = new Date(System.currentTimeMillis());

            if (user.getSex() != null && user.getSex().equals("11"))
            {
                user.setRegtime2(cdate);
            }

            if (user.getIsdel() == null)
                user.setIsdel(new Integer(0));

            //�ж��Ƿ�������δ�
            String s1="0"; //�ֻ�
       	    String s2="0"; //�̶��绰
       	    String s3="0"; //QQ
       	    String s4="0"; //����
       	    String shield=user.getShield()==null?"":user.getShield();
       	    if (shield.length()==4){
       	       s1=shield.substring(0,1);
       	       s2=shield.substring(1,2);
       	       s3=shield.substring(2,3);
       	       s4=shield.substring(3,4);
       	    }
            Usernamecut usernamecut=null;
            s1="0";
            s2="0";
            s3="0";
            s4="0";
            List listt =HbmOperator.list("from Usernamecut");
            for(int i=0;i<listt.size();i++)
            {
    			usernamecut=(Usernamecut)listt.get(i);
            	if(user.getSjtel().indexOf(usernamecut.getUsernamecut())!=-1)
            	{
            		s1="1";
            	}
            	if(user.getTel().indexOf(usernamecut.getUsernamecut())!=-1)
            	{
            		s2="1";
            	}
            	if(user.getOicq().indexOf(usernamecut.getUsernamecut())!=-1)
            	{
            		s3="1";
            	}
            	if(user.getEmail().indexOf(usernamecut.getUsernamecut())!=-1)
            	{
            		s4="1";
            	}
            }
            shield=s1+s2+s3+s4;
            user.setShield(shield);
            
            MutSeaObject mso = new MutSeaObject();
            mso.setHbmSea(user, MutSeaObject.SEA_HBM_UPDATE);
            saveList.add(mso);
            if (uother.getHyid() == null) // ������
            {
                BeanInitializer.updateBean(uother, request);
                uother.setHyid(user.getHyid());
                uother.setUsername(user.getUsername());
                mso = new MutSeaObject();
                mso.setHbmSea(uother, MutSeaObject.SEA_HBM_INSERT);
                saveList.add(mso);
            }
            else
            {
                BeanInitializer.updateBean(uother, request);

                mso = new MutSeaObject();
                mso.setHbmSea(uother, MutSeaObject.SEA_HBM_UPDATE);
                saveList.add(mso);
            }

            // // ��¼�޸���־��
            logs.append("���º�����<br>");
            OpLogMng.logUserinfo(user, uother, logs, srcUser, srcOhter);
              Boolean a = false;
            System.out.println("aaaaaaaaa= " + uother.getQggx());
            System.out.println("bbbbbbbbb= " + srcOhter.getQggx());
            System.out.println(!(uother.getQggx()+"") .equals(srcOhter.getQggx()+""));

            if(!user.getUsername().equals(srcUser.getUsername())){
                System.out.println("11111111111");
                    a = true;
            }else if(!user.getPassword().equals(srcUser.getPassword())){
                System.out.println("222222222222");
                    a = true;
            }else if(!user.getLcname().equals(srcUser.getLcname())){
                System.out.println("333333333333333333");
                      a = true;
             }else if(!user.getSex().equals(srcUser.getSex())){
                System.out.println("44444444444444");
                a = true;
            }else if(!DateTools.dateToString(user.getCsdate(),false).equals(DateTools.dateToString(srcUser.getCsdate(),false))){
                System.out.println("5555555555555555555");
                a = true;
            }
else if(!user.getYx().equals(srcUser.getYx())){
                System.out.println("666666666666666");
                a = true;
            } else if(!user.getSg().equals(srcUser.getSg())){
                System.out.println("777777777777");
                a = true;
            } else if(!user.getTz().equals(srcUser.getTz())){
                System.out.println("88888888888888");
                a = true;
            } else if(!user.getHyzk().equals(srcUser.getHyzk())){
                System.out.println("9999999999999999");
                a = true;
            } else if(!user.getWhcd().equals(srcUser.getWhcd())){
                System.out.println("aaaaaaa");
                a = true;
            } else if(!(user.getTel()+"").equals(srcUser.getTel()+"")){
                    System.out.println("bbbbbbbbbbbbbbbb");
                    a = true;
            } else if(!((user.getSjtel()+"").equals(srcUser.getSjtel()+"") || (srcUser.getSjtel()==null && user.getSjtel()==null) || (srcUser.getSjtel()==null && user.getSjtel().length()==0))){
                System.out.println("ccccccccc");
                a = true;
            } else if(!((user.getOicq()+"").equals(srcUser.getOicq()+"") || (srcUser.getOicq()==null && user.getOicq()==null) || (srcUser.getOicq()==null && user.getOicq().length()==0))){
                System.out.println("dddddddddd");
                a = true;
            } else if(!((user.getEmail()+"").equals(srcUser.getEmail()+"") || (srcUser.getEmail()==null && user.getEmail()==null) || (srcUser.getEmail()==null && user.getEmail().length()==0))){
                System.out.println("eeeeeeeeeeee");
                a = true;
            }  else if(!((user.getEmail()+"").equals(srcUser.getEmail()+"") || (srcUser.getEmail()==null && user.getEmail()==null) || (srcUser.getEmail()==null && user.getEmail().length()==0))){
                System.out.println("fffffffffff");
                a = true;
            } else if(!LoverTools.getJYYXDes(user).equals(LoverTools.getJYYXDes(srcUser))){
                System.out.println("gggggggggggg");
                a = true;

            }else if(!((uother.getQggx()+"") .equals(srcOhter.getQggx()+""))){
                System.out.println("hhhhhhhh");
                a = true;
            }else if(!(uother.getLxqr()+"").equals(srcOhter.getLxqr()+"")){
                System.out.println("iiiiiiiiiiii");
                a = true;
            }else if(!(uother.getJyly()+"").equals(srcOhter.getJyly()+"")){
                System.out.println("jjjjjjjjjjjjjj");
                a = true;
            }

              if(a){
                  OpLogMng.userAdminModUserinfoLog(user, admin, logs.toString());
              }










            // Oplog ol = new Oplog();
            // ol.setAdid(admin.getId().longValue());
            // ol.setHyid(user.getHyid().longValue());
            // ol.setId(SysCommonFunc.getSequenceIdForOracle(OpLogMng.SEQ_OPLOG));
            // ol.setOpdate(cdate);
            // ol.setOpdes("�޸��û���Ϣ"+user.getHyid());
            // mso = new MutSeaObject();
            // mso.setHbmSea(ol,mso.SEA_HBM_INSERT);
            // saveList.add(mso);

            HbmOperator.SeaMutData(saveList);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            s = "�޸Ļ�Ա������Ϣ�������뿪������ϵ��";
        }

        return s;
    }

    public static String adminSHUserinfo(HttpServletRequest request,
        Admininfo admin, Userinfo user, Userother uother)
    {
        String s = null;
        try
        {
            String sql = "update userinfo set shr = '"
                + SysCommonFunc.getStrParameter(request, "shr") + "',shqk='"
                + SysCommonFunc.getStrParameter(request, "shqk")
                + "' where hyid = " + user.getHyid();
            HbmOperator.executeSql(sql);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            s = "�޸��������������뿪������ϵ��";
        }

        return s;
    }
    
    /**
     * �ж��ֻ������Ƿ��ѱ�ע���
     * @param mobile  �ֻ�����
     * @param hyid    ��ԱID
     * @return
     */
    public static final boolean isexitsmobile(String mobile,Long hyid){
    	boolean blexits=false;
    	try {
    		String sqlString="select hyid from USERINFO o where o.isdel=0 and o.sjtel='"+mobile+"'";
    		if (hyid>0){
    			sqlString+=" and o.hyid<>"+hyid;
    		}
    		List userList=QueryRecord.query(sqlString);
    		if (userList!=null && userList.size()>=1){
    			blexits=true;
    		}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
    	return blexits;
    }
    
    //lin�ֻ�ע��
    public static String sjRegNewUser(HttpServletRequest request,String username)
    {
        String s = null;
        Userinfo userinfo = (Userinfo) BeanInitializer.initBean(Userinfo.class,
            request);
        
        //�����ֻ���֤
        String verycode = request.getParameter("verycode");
        List smsList = HbmOperator
            .list("from SendSms as o where o.mobileNumber='"
                + request.getParameter("sjtel") + "' and o.veryCode='" + verycode
                + "' and o.vcation=0 order by id desc");
        if (smsList == null || smsList.size() == 0)
            return "������Ķ�����֤�벻��ȷ";

        userinfo.setUsername(username.toLowerCase());

        if (userinfo.getUsername() == null
            || userinfo.getUsername().trim().length() == 0)
            return "�ֻ��Ų���Ϊ��!";
        userinfo.setUsername(userinfo.getUsername().trim());

        List list = HbmOperator.list("from Userinfo as o where o.username='"
            + userinfo.getUsername() + "' and o.isdel in (0,2)");
        if (list != null && list.size() > 0)
            return "�����ֻ������ѱ�ע�ᣬ��ֱ�ӵ�½��";
        

        Date cdate = new Date(System.currentTimeMillis());
        userinfo.setRegtime(cdate);
        userinfo.setRegtime2(cdate);
        userinfo.setRegtime3(cdate);
        userinfo.setLasttime(cdate);
        // /����δ���õ���Ŀ
        HashSet<Integer> hs1 = new HashSet<Integer>(); 
        Random r1 = new Random();
        hs1.add(r1.nextInt(35));
        Iterator<Integer> i1 = hs1.iterator();
        Long hyid = SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_HYID);
        userinfo.setHyid(hyid);
        userinfo.setFlag(new Integer(SysDefine.SYSTEM_HY_TYPE_NOR));
        userinfo.setHots(Long.valueOf(i1.next()));
        userinfo.setImg(new Integer(0));
        userinfo.setSetzt(new Integer(SysDefine.SYSTEM_HYSET_HYZT_WAIT));
        userinfo.setHttpip(request.getRemoteAddr());
        userinfo.setIsdel(new Integer(0));
        userinfo.setUsername(userinfo.getSjtel());
        userinfo.setSjtel(userinfo.getSjtel());
        userinfo.setIsvcation(new Long(1));   //�ֻ�վע��ɹ�Ĭ������֤  update by linyu 20151020
        userinfo.setVacsjtel(new Integer(1));//�ֻ�վע��ɹ�Ĭ������֤  update by linyu 20151031
        userinfo.setVacemail(new Integer(0));
        userinfo.setShield("");
        
        String lcname =  "�ֻ���Ա"+username.substring(username.length()-4);
        userinfo.setLcname(lcname);
        
      //�ж��ֻ����̶��绰��QQ�������Ƿ����ɧ�����Σ�����λ��ֵ��ʾ��ÿλ���������֣�0��1���ֱ�����ǲ����ͺ���ɧ�Ŵʡ���λ���ֱַ����ֻ����̶��绰��QQ������
        String shield="0000";
        userinfo.setShield(shield);
        
        String httpurl = (String) request.getSession().getAttribute("httpurl");
        String tjid = (String) request.getSession().getAttribute("tjid");

        if (httpurl != null && httpurl.getBytes().length > 50)
            httpurl = httpurl.substring(0, 50);

        String isWeixin = request.getParameter("isWeixin");
        String advid = (String) request.getSession().getAttribute("advid");
        if (advid != null && advid.trim().length() > 0)
        {
            int advidn = SysCommonFunc.getNumberFromString(advid, 0);
            userinfo.setVip(new Integer(advidn));
        }
        else if("1".equals(isWeixin))
        {
            userinfo.setVip(new Integer(51));
        }else  
        {
            userinfo.setVip(new Integer(50));
        }

        userinfo.setHttpurl(httpurl);

        Userother userother = (Userother) BeanInitializer.initBean(
            Userother.class, request);
        userother.setHyid(hyid);

        // /��ʼ���棺
        try
        {
            Vector saveList = new Vector();
            MutSeaObject mso = new MutSeaObject();

            mso.setHbmSea(userinfo, MutSeaObject.SEA_HBM_INSERT);
            saveList.add(mso);
            mso = new MutSeaObject();
            mso.setHbmSea(userother, MutSeaObject.SEA_HBM_INSERT);
            saveList.add(mso);

            

          //�������Ƽ������û������Ƽ��û���ð׽�start by linyu 20151115 
            //�û��ֻ���У��ͨ�������Ӱ׽�update by linyu 20151120
          //�û��ڵ��Զ����ƹ�������Ϣ�����Ӱ׽�update by linyu 20151124
            //zxldelete delete bjd;
			String sharehyid = (String)request.getSession().getAttribute("sharehyid");
			 String tjtype = (String)request.getSession().getAttribute("tjtype");
			if(sharehyid!=null && !"".equals(sharehyid) && !"0".equals(sharehyid) && tjtype!=null && !"".equals(tjtype) ){
				Hytj hytj = new Hytj();
		          hytj.setId(SysCommonFunc.getSequenceIdForOracle("SEQ_HYTJ"));
		          Userinfo tjUser = HYRegMng.getUserinfoByHyid(sharehyid);//�Ƽ���

		          hytj.setMyid(tjUser.getHyid());
		          hytj.setMyname(tjUser.getUsername());
		          hytj.setTjdate(new Date(System.currentTimeMillis()));
		          hytj.setTjid(userinfo.getHyid());  //���Ƽ�ע����
		          hytj.setTjname(userinfo.getLcname());
		          hytj.setIscheck("1");  //�Ƿ���֤�ֻ���
		          hytj.setIsphone("1");  //�Ƿ��ֻ���
		          hytj.setTjtype(tjtype); //�ƹ����� 1��������΢����2������Ѷ΢����3����QQ���ѻ����Ⱥ��11�ֻ�
		          HbmOperator.insert(hytj);
		          /*UserBjd ub = null;
				Vector saveBJDList = new Vector();
		        List bjdlist = HbmOperator.list("from UserBjd as o where hyid="+sharehyid);
		          
		          if(bjdlist == null || bjdlist.size() ==0)
		          {
		              ub = new UserBjd();
		              ub.setBjdnumber(0l);
		              ub.setHyid(Long.parseLong(sharehyid));

		              mso = new MutSeaObject();
		              mso.setHbmSea(ub,MutSeaObject.SEA_HBM_INSERT);
		              saveBJDList.add(mso);
		          }
		          //���Ӱ׽𶹼�¼
		          UserBjdRecord ubr = new UserBjdRecord();
		            ubr.setBjddesc("�Ƽ���"+lcname+"���������ƣ�");
		            //ubr.setBjdnumber(0);
		            ubr.setTjid(userinfo.getHyid());  //���Ƽ�ע����
		            ubr.setTjtype("11");  //�Ƽ����ͣ�11�ֻ�δ�������ϣ�12�ֻ����������ϣ�1����δ��֤�ֻ���2��������֤�ֻ�
		            ubr.setHyid(sharehyid);
		            ubr.setBjdnumber(1l);
		            ubr.setId(SysCommonFunc.getSequenceIdForOracle("SEQ_WTJD"));
		            ubr.setRecodeTime(new Date(System.currentTimeMillis()));
		            mso = new MutSeaObject();
		            mso.setHbmSea(ubr, MutSeaObject.SEA_HBM_INSERT);
		            saveBJDList.add(mso);
 		          HbmOperator.SeaMutData(saveBJDList);
 		         MutSeaObject mso3 = new MutSeaObject();
	                mso3.setSqlSea(
	                    "update user_bjd set bjdnumber=bjdnumber+1 where hyid ="
	                        + hytj.getMyid(), MutSeaObject.SEA_SQL_UPDATE);
	                saveList.add(mso3);*/
			}
			//�������Ƽ������û������Ƽ��û���ð׽�end by linyu 20151115 
            
			HbmOperator.SeaMutData(saveList);
			
            // /���û���Ϣ���뵽��½��Ϣ��
            request.getSession().setAttribute(SysDefine.SESSION_LOGINNAME,
                    userinfo);
                request.getSession().setAttribute(
                    SysDefine.SESSION_LOGINNAME_OTHER, userother);
        }
        catch (Exception e)
        {
            System.out.println("ע���û�����" + e.getMessage());
            return "ע������������Ա��ϵ��";
        }

        request.setAttribute("cu", userinfo);
        return s;

    }

}
