package com.lover;

import com.common.SysDefine;
import com.lover.mng.HYRegMng;
import com.web.bean.QueryRecord;
import com.web.bean.QueryResult;
import com.web.common.DateTools;
import com.web.common.MailTools;
import com.web.common.SysCommonFunc;
import com.web.obj.*;
import com.web.servlet.InitEnvironment;

import hibernate.db.HbmOperator;

import org.apache.commons.beanutils.DynaBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class CacheToolsNew
{

    public static void setAll()
    {
        Thread t = new ThreadCityUserList();
        System.out.println("读取缓存信息");
        CacheToolsNew.loadLLSet();
        CacheToolsNew.setAllDayNumber();
        System.out.println(1);
        CacheToolsNew.setAllAccessToday();
        System.out.println(2);
        CacheToolsNew.getTopOneUserList();
        System.out.println(3);
        CacheToolsNew.getTopRqzwManList();
        System.out.println(4);
        CacheToolsNew.getTopRqzwWonList();
        System.out.println(5);
        CacheToolsNew.getNewUserList();
        System.out.println(6);
        CacheToolsNew.getNoCityList();
        System.out.println(7);
        t.start();
        //CacheTools.getCityUserList();
        System.out.println(8);
        CacheToolsNew.getGGList();
        CacheToolsNew.lastFriendCacheTime = 0;
        CacheToolsNew.lastFsiteCacheTime = 0;
        CacheToolsNew.delQiubo();

        System.out.println("更新短信状态");
        //SMSTools.getSendStatus();

        System.out.println("读取邮件内容");
        MailTools.recevie();

        System.out.println("读取缓存信息结束");

    }

    public CacheToolsNew()
    {
    }

    // /图标友情链接缓存

    public static List friendList = null;

    public static StringBuffer friendSb = new StringBuffer();

    public static long lastFriendCacheTime = System.currentTimeMillis();

    // 文字友情链接缓存
    public static List friendSiteList = null;

    public static StringBuffer friendSiteSb = new StringBuffer();

    public static long lastFsiteCacheTime = System.currentTimeMillis();

    // /重点会员推荐缓存
    public static List topOneUserList = null;

    // public static StringBuffer topOneUserSb = new StringBuffer();
    // public static long lastTopOneCacheTime = System.currentTimeMillis();

    public final static long steptime = 5 * 60 * 1000;

    // public final static long steptime = 5000;

    public final static long hoursteptime = 60 * 60 * 1000;

    // 每4小时更新一次
    public final static long cityUserUpdateTime = 4 * 60 * 60 * 1000;

    // public final static long noCityUserUpdateTime= 4 * 60 * 60 * 1000;

    public static long lastAllUserCacheTime = 0;

    public static long lastCityUserUpdatetime = 0;

    public static long lastNoCityUpdateTime = 0;

    public static long ALL_USER = 0;

    public static long ACCESS_VDAY = 0;

    public static long ALL_USER_VDAY = 0;

    public static long ACCESS_TODAY = 0;

    public static Hashtable adminOnline = new Hashtable();

    public static Hashtable userOnline = new Hashtable();

    public static Hashtable cityUser = new Hashtable();

    public static long ALL_ONLINE = 0;

    public static String ALL_VIEW_CDATE = "";

    public static String DEL_QIUBO_CDATE = "";

    // public static long lastViewAccessToday = 0;

    public static double v1 = 1;

    public static double v2 = 1;

    public static double v3 = 1;

    public static double v4 = 1;

    public static double v5 = 1;

    public static List newUserList = null;

    public static List topRqzwWonList = null;

    public static List topRqzwManList = null;

    public static List ggList = null;

    static
    {
        setAll();
    }

    public static void getGGList()
    {
        if (ggList != null)
        {
            ggList.clear();
            ggList = null;
        }

        ggList = QueryRecord.queryByHbm(
            "from Wtjd as o where o.wttype='2' order by o.bianhao desc", 5, 1,
            false, 0).resultList;

    }

    public static void delQiubo()
    {
        Date cdate = new Date(System.currentTimeMillis());
        String cdates = DateTools.DateToString(cdate,
            DateTools.FORMART_yyyyMMdd);
        if (cdates.equals(CacheToolsNew.DEL_QIUBO_CDATE))
            return;
        CacheToolsNew.DEL_QIUBO_CDATE = cdates;
        try
        {
            // System.out.println("开始删除秋波");
            // String sql[] = new String[1];
            // sql[0] =
            // "delete from db_qiubo where sendtime < (to_date('"+cdates+"','+YYYYMMDD') - 93)";
            // HbmOperator.executeSql(sql,"htdb");
            // System.out.println("新的删除秋波结束ok");

//            System.out.println("开始删除登陆日志");
//            HbmOperator
//                .executeSql("delete from ip_log where logintime < to_date('"
//                    + cdates + "','+YYYYMMDD') and isadmin=0");
//            System.out.println("删除登陆日志结束");
//
//            System.out.println("清除isdel is null");
//            HbmOperator
//                .executeSql("update userinfo set isdel = 0 where isdel is null");
//            System.out.println("清除isdel is null 结束");

        }
        catch (Exception e)
        {
            System.out.println("删除秋波出错：" + e.getMessage());
        }
    }

    public synchronized static void setAllAccessToday()
    {

        Date cdate = new Date(System.currentTimeMillis());
        String cdates = DateTools.DateToString(cdate,
            DateTools.FORMART_yyyyMMdd);
        Long aaccessT = SysCommonFunc
            .getNumberForSql("select count(distinct userstrip) from ip_log where logintimestr = '"
                + cdates + "'");
        CacheToolsNew.ACCESS_TODAY = aaccessT.longValue();
        System.out.println("acc today=" + CacheToolsNew.ACCESS_TODAY);

    }

    public synchronized static void setOnline(boolean add, Object o,
        String sessionid)
    {
        if (add)
        {
            // if(o instanceof String)
            // CacheTools.ALL_ONLINE = CacheTools.ALL_ONLINE + 1;
            // if(o instanceof Admininfo)
            // {
            // Admininfo admin = (Admininfo)o;
            // CacheTools.adminOnline.put(admin.getId().toString(),admin);
            // }
            // if(o instanceof Userinfo)
            // {
            // Userinfo user = (Userinfo)o;
            // CacheTools.userOnline.put(user.getHyid().toString(),user);
            // }
            CacheToolsNew.userOnline.put(sessionid, sessionid);
            CacheToolsNew.ALL_ONLINE = CacheToolsNew.userOnline.size();
        }
        else
        {
            // if(o instanceof String)
            // CacheTools.ALL_ONLINE = CacheTools.ALL_ONLINE - 1;
            // if(o instanceof Admininfo)
            // {
            // Admininfo admin = (Admininfo)o;
            // CacheTools.adminOnline.remove(admin.getId().toString());
            // }
            // if(o instanceof Userinfo)
            // {
            // Userinfo user = (Userinfo)o;
            // CacheTools.userOnline.remove(user.getHyid().toString());
            // }
            CacheToolsNew.userOnline.remove(sessionid);
            CacheToolsNew.ALL_ONLINE = CacheToolsNew.userOnline.size();

        }
    }

    public synchronized static void setAllDayNumber()
    {
        long system = System.currentTimeMillis();
        Date cdate = new Date(system);
        Date vdate = new Date(system);
        vdate.setDate(vdate.getDate() - 1);

        String cdates = DateTools.DateToString(cdate,
            DateTools.FORMART_yyyyMMdd);
        String vdates = DateTools.DateToString(vdate,
            DateTools.FORMART_yyyyMMdd);

        if ((system - CacheToolsNew.lastAllUserCacheTime) > CacheToolsNew.hoursteptime)
        {
            String sql = "select count(*) from userinfo where isdel = 0";
            Long auser = SysCommonFunc.getNumberForSql(sql);
            CacheToolsNew.ALL_USER = auser.longValue();
            System.out.println("new all user=" + CacheToolsNew.ALL_USER);
            CacheToolsNew.lastAllUserCacheTime = System.currentTimeMillis();

        }

        if (cdates.equals(CacheToolsNew.ALL_VIEW_CDATE))
            return;

        // Long aaccessvday =
        // SysCommonFunc.getNumberForSql("select count(*) from ip_log where logintimestr = '"+vdates+"'");
        // CacheTools.ACCESS_VDAY = aaccessvday.longValue();
        Long auservday = SysCommonFunc
            .getNumberForSql("select count(*) from userinfo t where t.regtime >= to_date('"
                + vdates
                + "','YYYYMMDD') and t.regtime < to_date('"
                + cdates
                + "','YYYYMMDD')");
        CacheToolsNew.ALL_USER_VDAY = auservday.longValue();
        System.out.println("reg vday=" + CacheToolsNew.ALL_USER_VDAY);
        CacheToolsNew.ALL_VIEW_CDATE = cdates;

    }

    public static void loadLLSet()
    {
        try
        {
            String filepath = InitEnvironment
                .getSystemProValue(SysDefine.ENV_WEB_PATH)
                + File.separator + "WEB-INF/setll.properties";
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            br.close();
            fr.close();
            if (s == null)
            {
                s = "1:1:1:1:1";
            }
            String[] array = s.split(":");
            v1 = Double.parseDouble(array[0]);
            v2 = Double.parseDouble(array[1]);
            v3 = Double.parseDouble(array[2]);
            v4 = Double.parseDouble(array[3]);
            v5 = Double.parseDouble(array[4]);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static long getAll_user()
    {
        return CacheToolsNew.ALL_USER;
    }

    public static long getAccessVDay()
    {
        return CacheToolsNew.ACCESS_VDAY;
    }

    public static long getAccessToday()
    {
        return CacheToolsNew.ACCESS_TODAY;
    }

    public static long getAllUserVday()
    {
        return CacheToolsNew.ALL_USER_VDAY;
    }

    public static long getAllOnline()
    {
        //return CacheTools.ALL_ONLINE;
    	List list = QueryRecord.query("select id from rphoto");
        if (list != null && list.size() > 0)
        {
            DynaBean db = (DynaBean) list.get(0);
            String id = db.get("id").toString();
            return Long.parseLong(id);

        }

        return 0;
    }

    public static long getAdminOnline()
    {
        return CacheToolsNew.adminOnline.size();
    }

    public static long getUserOnline()
    {
        // return CacheTools.userOnline.size();

        List list = QueryRecord.query("select id from rphoto");
        if (list != null && list.size() > 0)
        {
            DynaBean db = (DynaBean) list.get(0);
            String id = db.get("id").toString();
            return Long.parseLong(id);

        }

        return 0;
    }

    public static void getFriendList()
    {
        if (friendList != null)
        {
            friendList.clear();
            friendList = null;
        }

        friendList = QueryRecord.queryByHbm(
            "from Friend  o where o.type =1 and o.state=1  order by o.bianhao asc", 10, 1, false, 0).resultList;

    }

    public static void getFriendSiteList()
    {
        if (friendSiteList != null)
        {
            friendSiteList.clear();
            friendSiteList = null;
        }
        // select top 40 * from FriendSite where IsOK=1 and LinkType=2 order by
        // xuhao desc,id desc
        friendSiteList = QueryRecord
            .queryByHbm(
                "from Friend o  where o.type =0 and o.state=1  order by o.bianhao asc,o.id desc",
                40, 1, false, 0).resultList;
    }

    public static StringBuffer getFriendSiteLink()
    {
        long currentTime = System.currentTimeMillis();

        if (friendSiteList == null
            || (currentTime - CacheToolsNew.lastFsiteCacheTime) > CacheToolsNew.steptime)
        {
            System.out.println("frend" + CacheToolsNew.lastFsiteCacheTime);
            CacheToolsNew.getFriendSiteList();
            friendSiteSb = null;
            friendSiteSb = new StringBuffer();
            for (int i = 0; i < CacheToolsNew.friendSiteList.size(); i++)
            {
                Friend fr = (Friend) CacheToolsNew.friendSiteList.get(i);
                if (i > 0)
                    friendSiteSb.append("|&nbsp;&nbsp;");
                friendSiteSb.append("<a  href='" + fr.getNethttp()
                    + "' target='_blank' class='link01' title='网站名称："
                    + fr.getNetname() + "网站地址：" + fr.getNethttp() + "网站简介："
                    + fr.getMemo() + "'>" + fr.getNetname() + "</a>");
                if (i % 12 == 0 && i != 0){
                    friendSiteSb.append("<br>");
                }else{
                    friendSiteSb.append("&nbsp;&nbsp;");
                }

            }

            CacheToolsNew.lastFsiteCacheTime = currentTime;
        }
        return friendSiteSb;
    }

    public static StringBuffer getFriendLink()
    {
        long currentTime = System.currentTimeMillis();
        if (friendList == null
            || (currentTime - CacheToolsNew.lastFriendCacheTime) > CacheToolsNew.steptime)
        {
            CacheToolsNew.getFriendList();
            friendSb = null;
            friendSb = new StringBuffer();
            friendSb
                .append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
            for (int i = 0; i < CacheToolsNew.friendList.size(); i++)
            {
                Friend fr = (Friend) CacheToolsNew.friendList.get(i);
                // friendSb.append("<li><a href='"+fr.getNethttp()+"' target='_blank'><img src='"+fr.getNetlogo()+"'></a><li>\n");
                friendSb.append("<tr><td height=\"47\"><a href='"
                    + fr.getNethttp()
                    + "' target='_blank'  style=\"height:43\"><img src='"
                    + fr.getNetlogo() + "'></a></td></tr>\n");
            }
            friendSb.append("</table>");
            CacheToolsNew.lastFriendCacheTime = currentTime;
        }
        return friendSb;
    }

    public static StringBuffer getFriendLinkNew()
    {
        long currentTime = System.currentTimeMillis();
        StringBuffer friendSbNew = new StringBuffer();
        if (friendList == null
            || (currentTime - CacheToolsNew.lastFriendCacheTime) > CacheToolsNew.steptime)
        {
            CacheToolsNew.getFriendList();
            CacheToolsNew.lastFriendCacheTime = currentTime;
        }
        friendSbNew.append("<ul>");

        for (int i = 0; i < CacheToolsNew.friendList.size(); i++)
        {
            Friend fr = (Friend) CacheToolsNew.friendList.get(i);
            friendSbNew.append("<li><a href='" + fr.getNethttp()
                + "' target='_blank'><img src='" + fr.getNetlogo()
                + "' width='88' height='31' ></a></li>");
        }

        friendSbNew.append("</ul>");
        return friendSbNew;
    }

    public static void getNoCityList()
    {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - CacheToolsNew.lastNoCityUpdateTime) > CacheToolsNew.lastNoCityUpdateTime)
        {
            // cityUser.clear();
            setCityUserCache("无地区", 10, "00");
            setCityUserCache("无地区", 11, "00");
            CacheToolsNew.lastNoCityUpdateTime = System.currentTimeMillis();
        }
    }

    public static void getCityUserList()
    {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - CacheToolsNew.lastCityUserUpdatetime) > CacheToolsNew.cityUserUpdateTime)
        {
            CacheToolsNew.lastCityUserUpdatetime = System.currentTimeMillis();
            String city = "";
            List list = QueryRecord
                .queryByDynaResultSet(
                    "select s2"
                        + " from userinfo where img=1 "
                        + " and isdel=0 "
                        + " having sum(decode(sex,11,1,0))>=100 and sum(decode(sex,10,1,0))>=100"
                        + " group by s2 order by sum(decode(sex,11,1,0)) desc",
                    150, 1).resultList;
            List s1List = QueryRecord
                .queryByDynaResultSet(
                    "select s1"
                        + " from userinfo where img=1 "
                        + " and isdel=0 "
                        + " having sum(decode(sex,11,1,0))>=100 and sum(decode(sex,10,1,0))>=100"
                        + " group by s1 order by sum(decode(sex,11,1,0)) desc",
                    30, 1).resultList;


            for (int i = 0; i < s1List.size(); i++)
            {
                DynaBean db = (DynaBean) s1List.get(i);
                city = db.get("s1").toString();
                System.out.println(i + city);
                setCityUserCache(city, 10, "02");
                setCityUserCache(city, 11, "02");
            }
            for (int i = 0; i < list.size(); i++)
            {
                DynaBean db = (DynaBean) list.get(i);
                city = db.get("s2").toString();
                System.out.println(i + city);
                setCityUserCache(city, 10, "01");
                setCityUserCache(city, 11, "01");

            }
        }
    }

    // 同城会员缓存
    public static void setCityUserCache(String city, int sex, String type)
    {

        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        StringBuffer sb3 = new StringBuffer();
        StringBuffer sb4 = new StringBuffer();
        StringBuffer sb5 = new StringBuffer();
        StringBuffer sb6 = new StringBuffer();

        String age = "";
        int size = 0; // 查找条数
        String scity = "";
        Date date = new Date(System.currentTimeMillis());
        List userList = null;
        if (sex == 10)
        {
            size = 6;
        }
        else
        {
            size = 12;
        }

        HashSet<Integer> hs = new HashSet<Integer>();
        Random r = new Random();

        if (city.equals("无地区"))
        {
            userList = QueryRecord.queryByHbm("from Userinfo as u where "
                + " u.img=1 and u.isdel=0 and u.sex = " + sex
                + " and u.lysize>=30 and (u.jyyx like '"
                + LoverTools.getMinJyyx(3) + "' or u.jyyx like '"
                + LoverTools.getMinJyyx(4) + "') order by u.lasttime desc", 100,
                1, false, 0).resultList;
        }
        else if (type.equals("02"))
        {
            userList = QueryRecord.queryByHbm(
                "from Userinfo as u where u.s1 = '" + city
                    + "' and u.img=1 and u.isdel=0  " + "and u.sex = " + sex
                    + " and u.lysize>=30 and (u.jyyx like '"
                    + LoverTools.getMinJyyx(3) + "' or u.jyyx like '"
                    + LoverTools.getMinJyyx(4) + "') order by u.lasttime desc",
                100, 1, false, 0).resultList;

        }

        else
        {
            userList = QueryRecord.queryByHbm(
                "from Userinfo as u where u.s2 = '" + city
                    + "' and u.img=1 and u.isdel=0  " + "and u.sex = " + sex
                    + " and u.lysize>=30 and (u.jyyx like '"
                    + LoverTools.getMinJyyx(3) + "' or u.jyyx like '"
                    + LoverTools.getMinJyyx(4) + "') order by u.lasttime desc",
                100, 1, false, 0).resultList;
        }
        while (hs.size() < size)
        {
            hs.add(r.nextInt(userList.size()));
        }
//-------------------------------------------
        Iterator<Integer> f = hs.iterator();
        int n=size;

        int  ra[]=new int[n];
        int  temp;
        for(temp=0;temp<ra.length;temp++)
        {
            ra[temp]=(int)(f.next());
        }
        int i;
        for(i=0;i<ra.length;i++) 
        {
        	for(int j=i+1;j<ra.length;j++) 
        	{
        		if(ra[j]<ra[i]) 
        		{
        			int temp1 = ra[i];
        			ra[i] = ra[j];
        			ra[j] = temp1;
            
        		}
        	}
        }
        for (int j = 0; userList != null && j < size; j++)
        {
            Userinfo user = (Userinfo) userList.get(ra[j]);
//----------------------------------------------------
            Userother uother = HYRegMng.getUserOtherByHyid(user.getHyid()
                .toString());
            if (uother == null)
                uother = new Userother();
            String photo = null;
            if (uother.getUserphoto1() != null
                && uother.getUserphoto1().length() > 0)
                photo = uother.getUserphoto1();
            else if (uother.getUserphoto2() != null
                && uother.getUserphoto2().length() > 0)
                photo = uother.getUserphoto2();
            else if (uother.getUserphoto3() != null
                && uother.getUserphoto3().length() > 0)
                photo = uother.getUserphoto3();
            else if (uother.getUserphoto4() != null
                && uother.getUserphoto4().length() > 0)
                photo = uother.getUserphoto4();
            else if (uother.getUserphoto5() != null
                && uother.getUserphoto5().length() > 0)
                photo = uother.getUserphoto5();
            // 年龄
            age = (date.getYear() - user.getCsdate().getYear()) + "岁";
            if (user.getS1().equals("北京") || user.getS1().equals("上海")
                || user.getS1().equals("重庆") || user.getS1().equals("天津")
                || city.equals("河北") || city.equals("山西") || city.equals("内蒙古")
                || city.equals("辽宁") || city.equals("黑龙江") || city.equals("江苏")
                || city.equals("浙江") || city.equals("安徽") || city.equals("福建")
                || city.equals("江西") || city.equals("山东") || city.equals("河南")
                || city.equals("湖北") || city.equals("湖南") || city.equals("广东")
                || city.equals("广西") || city.equals("海南") || city.equals("四川")
                || city.equals("贵州") || city.equals("云南") || city.equals("西藏")
                || city.equals("陕西") || city.equals("甘肃") || city.equals("青海")
                || city.equals("宁夏") || city.equals("新疆") || city.equals("香港")
                || city.equals("台湾") || city.equals("澳门") || city.equals("无地区"))
            {
                scity = user.getS1();
            }
            else
            {
                scity = user.getS2();
            }

            if (j < 6)
            {
                sb1
                    .append("<td width=\"96\" align=\"center\">"
                        + "<a href='./perinfo-id-"
                        + user.getHyid()
                        + ".htm' target=\"_blank\"><img src='../"
                        + SysCommonFunc.getPhotoSmallView()
                        + File.separator
                        + photo
                        + "' "
                        + " width=\"84\" height=\"104\" border=\"0\" class=\"sytx\" /></a>"
                        + "</td>");
                sb2.append("<td height=\"24\" align=\"center\" "
                    + "class=\"name\">" + "<a href='./perinfo-id-"
                    + user.getHyid() + ".htm' target='_blank'><strong>"
                    + user.getLcname() + "</strong></a></td>");
                sb3
                    .append("<td height=\"20\" align=\"center\" class=\"rq\">"
                        + scity+"   "  + age
                        + "</td>");
            }
            if (j > 5)
            {
                sb4
                    .append("<td width=\"96\" align=\"center\">"
                        + "<a href='./perinfo-id-"
                        + user.getHyid()
                        + ".htm' target=\"_blank\"><img src='../"
                        + SysCommonFunc.getPhotoSmallView()
                        + File.separator
                        + photo
                        + "' "
                        + " width=\"84\" height=\"104\" border=\"0\" class=\"sytx\" /></a>"
                        + "</td>");
                sb5.append("<td height=\"24\" align=\"center\" "
                    + "class=\"name\">" + "<a href='./perinfo-id-"
                    + user.getHyid() + ".htm' target='_blank'><strong>"
                    + user.getLcname() + "</strong></a></td>");
                sb6
                    .append("<td height=\"20\" align=\"center\" class=\"rq\">"
                        + scity+"   " +   age
                        + "</td>");
            }

        }
        if (size == 6)
        {
            sb
                .append("<table width=\"576\" border=\"0\" "
                    + "align=\"center\" cellpadding=\"0\""
                    + " cellspacing=\"0\" style=\"margin:8px 0px 1px 0px; line-height:0px;\">"
                    + "<tr>" + sb1.toString() + "</tr>" + "<tr>"
                    + sb2.toString() + "</tr>" + "<tr>" + sb3.toString()
                    + "</tr></table>");
        }
        else
        {
            sb
                .append("<table width=\"576\" border=\"0\" "
                    + "align=\"center\" cellpadding=\"0\""
                    + " cellspacing=\"0\" style=\"margin:8px 0px 1px 0px; line-height:0px;\">"
                    + "<tr>"
                    + sb1.toString()
                    + "</tr>"
                    + "<tr>"
                    + sb2.toString()
                    + "</tr>"
                    + "<tr>"
                    + sb3.toString()
                    + "</tr></table><table width=\"576\" border=\"0\" "
                    + "align=\"center\" cellpadding=\"0\""
                    + " cellspacing=\"0\" style=\"margin:8px 0px 1px 0px; line-height:0px;\">"
                    + "<tr>" + sb4.toString() + "</tr>" + "<tr>"
                    + sb5.toString() + "</tr>" + "<tr>" + sb6.toString()
                    + "</tr></table>");
        }

        // 往hashtable里put需要显示的内容
        cityUser.put(city + String.valueOf(sex), sb.toString());

    }

    // 给主页调男会员
    public static String getCityUserHtml(String city, int sex)
    {
        String aa = (String) cityUser.get(city + String.valueOf(sex));
        if (aa == null)
        {
            aa = (String) cityUser.get("无地区" + String.valueOf(sex));
        }
        return aa;
    }

    public static void getTopOneUserList()
    {

        List list = QueryRecord
            .queryByHbm(
                "from Userinfo as o where o.img > 0 and o.isdel=0 and o.important >90 order by o.important asc",
                6, 1, false, 0).resultList;
        if (CacheToolsNew.topOneUserList != null)
        {
            CacheToolsNew.topOneUserList.clear();
            CacheToolsNew.topOneUserList = null;
        }
        CacheToolsNew.topOneUserList = list;
    }

    public static StringBuffer getTopOneSb()
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; CacheToolsNew.topOneUserList != null
            && i < CacheToolsNew.topOneUserList.size(); i++)
        {
            Userinfo user = (Userinfo) CacheToolsNew.topOneUserList.get(i);
            Userother uother = HYRegMng.getUserOtherByHyid(user.getHyid()
                .toString());
            if (uother == null)
                uother = new Userother();
            String photo = null;
            if (uother.getUserphoto1() != null
                && uother.getUserphoto1().length() > 0)
                photo = uother.getUserphoto1();
            else if (uother.getUserphoto2() != null
                && uother.getUserphoto2().length() > 0)
                photo = uother.getUserphoto2();
            else if (uother.getUserphoto3() != null
                && uother.getUserphoto3().length() > 0)
                photo = uother.getUserphoto3();
            else if (uother.getUserphoto4() != null
                && uother.getUserphoto4().length() > 0)
                photo = uother.getUserphoto4();
            else if (uother.getUserphoto5() != null
                && uother.getUserphoto5().length() > 0)
                photo = uother.getUserphoto5();

            sb.append("<li ><a href='perinfo-id-" + user.getHyid()
                + ".htm' target='_blank'><img src='"
                + SysCommonFunc.getPhotoSmallView() + File.separator + photo
                + "'></a><h5><a href='perinfo-id-" + user.getHyid()
                + ".htm' target='_blank'>" + user.getLcname()
                + "</a></h5></li>\n");
        }

        return sb;
    }

    public static StringBuffer getTopOneSbNew()
    {
    	String age = "";
        String scity = "";
        Date date = new Date(System.currentTimeMillis());
       StringBuffer sb = new StringBuffer();
        sb.append("<ul>");
        for (int i = 0; CacheToolsNew.topOneUserList != null
            && i < CacheToolsNew.topOneUserList.size(); i++)
        {
            Userinfo user = (Userinfo) CacheToolsNew.topOneUserList.get(i);
            Userother uother = HYRegMng.getUserOtherByHyid(user.getHyid()
                .toString());
            if (uother == null)
                uother = new Userother();
            String photo = null;
            if (uother.getUserphoto1() != null
                && uother.getUserphoto1().length() > 0)
                photo = uother.getUserphoto1();
            else if (uother.getUserphoto2() != null
                && uother.getUserphoto2().length() > 0)
                photo = uother.getUserphoto2();
            else if (uother.getUserphoto3() != null
                && uother.getUserphoto3().length() > 0)
                photo = uother.getUserphoto3();
            else if (uother.getUserphoto4() != null
                && uother.getUserphoto4().length() > 0)
                photo = uother.getUserphoto4();
            else if (uother.getUserphoto5() != null
                && uother.getUserphoto5().length() > 0)
                photo = uother.getUserphoto5();
            
            age = (date.getYear() - user.getCsdate().getYear()) + "岁";
            if (user.getS1().equals("北京") || user.getS1().equals("上海")
                || user.getS1().equals("重庆") || user.getS1().equals("天津")
                )
            {
                scity = user.getS1();
            }
            else
            {
                scity = user.getS1()+user.getS2();
            }
                // <li><a href="show.asp" target="_blank"><img src="images/01.jpg" alt=""/></a>
                // <strong><a href="show.asp" target="_blank">原来我是水瓶座</a></strong>
                // <span>江苏南京&nbsp;36岁</span></li>
            String username = user.getLcname();
            if(null != username && username.length()>5){
            	username = username.substring(0, 4)+"..";
            }
            String photoNew = photo ;
            if(photo!=null){
            	photoNew = photo.replace("\\","/");
            }
            sb.append("<li><a href='./perinfo-id-"+ user.getHyid()+ ".htm'><img src='" + SysCommonFunc.getPhotoSmallView()+ "/" + photoNew  + "'   />" +
                    "<span>"+ username +"</span><p>"+scity+"</p><p>"+age+"</p></a></li>");
        }
        sb.append("</ul>");
         return sb;
    }

    // 人气最旺男会员

    // public static StringBuffer topRqzwManSb = new StringBuffer();
    // public static long lastTopRqzwManCacheTime = System.currentTimeMillis();
    public static void getTopRqzwManList()
    {
        List list = QueryRecord
            .queryByHbm(
                "from Userinfo as o where o.sex='10' and o.img > 0 and o.isdel=0 and o.important>0 order by important asc",
                6, 1, false, 0).resultList;
        if (CacheToolsNew.topRqzwManList != null)
        {
            CacheToolsNew.topRqzwManList.clear();
            CacheToolsNew.topRqzwManList = null;
        }

        CacheToolsNew.topRqzwManList = list;
    }

    public static StringBuffer getTopRqzwManSb()
    {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; CacheToolsNew.topRqzwManList != null
            && i < CacheToolsNew.topRqzwManList.size(); i++)
        {
            Userinfo user = (Userinfo) CacheToolsNew.topRqzwManList.get(i);
            Userother uother = HYRegMng.getUserOtherByHyid(user.getHyid()
                .toString());
            if (uother == null)
                uother = new Userother();
            String photo = null;
            if (uother.getUserphoto1() != null
                && uother.getUserphoto1().length() > 0)
                photo = uother.getUserphoto1();
            else if (uother.getUserphoto2() != null
                && uother.getUserphoto2().length() > 0)
                photo = uother.getUserphoto2();
            else if (uother.getUserphoto3() != null
                && uother.getUserphoto3().length() > 0)
                photo = uother.getUserphoto3();
            else if (uother.getUserphoto4() != null
                && uother.getUserphoto4().length() > 0)
                photo = uother.getUserphoto4();
            else if (uother.getUserphoto5() != null
                && uother.getUserphoto5().length() > 0)
                photo = uother.getUserphoto5();

            String sql = "<li><a href='perinfo-id-" + user.getHyid()
                + ".htm' target='_blank'><img src='../"
                + SysCommonFunc.getPhotoSmallView() + File.separator + photo
                + "'></a>\n<h5><a href='perinfo-id-" + user.getHyid()
                + ".htm' target='_blank'>" + user.getLcname()
                + "</a></h5><br><h6>人气:" + user.getHots() + "</h6></li>";
            sb.append(sql);
        }




        return sb;

    }

    public static StringBuffer getTopRqzwManSbNew()
    {
        StringBuffer sb = new StringBuffer();
        String hots="0";
        sb.append("<ul>");
        for (int i = 0; CacheToolsNew.topRqzwManList != null
            && i < CacheToolsNew.topRqzwManList.size(); i++)
        {
            Userinfo user = (Userinfo) CacheToolsNew.topRqzwManList.get(i);
            Userother uother = HYRegMng.getUserOtherByHyid(user.getHyid()
                .toString());
            if (uother == null)
                uother = new Userother();
            String photo = null;
            if (uother.getUserphoto1() != null
                && uother.getUserphoto1().length() > 0)
                photo = uother.getUserphoto1();
            else if (uother.getUserphoto2() != null
                && uother.getUserphoto2().length() > 0)
                photo = uother.getUserphoto2();
            else if (uother.getUserphoto3() != null
                && uother.getUserphoto3().length() > 0)
                photo = uother.getUserphoto3();
            else if (uother.getUserphoto4() != null
                && uother.getUserphoto4().length() > 0)
                photo = uother.getUserphoto4();
            else if (uother.getUserphoto5() != null
                && uother.getUserphoto5().length() > 0)
                photo = uother.getUserphoto5();
               hots=user.getHots().toString();

            String age = "";
            int size = 0; // 查找条数
            String scity = "";
            Date date = new Date(System.currentTimeMillis());
             age = (date.getYear() - user.getCsdate().getYear()) + "岁";
            if (user.getS1().equals("北京") || user.getS1().equals("上海")
                    || user.getS1().equals("重庆") || user.getS1().equals("天津")
                    )
            {
                scity = user.getS1();
            }
            else
            {
                scity = user.getS1()+user.getS2();
            }
            String username = user.getLcname();
            if(null != username && username.length()>5){
            	username = username.substring(0, 4)+"..";
            }
            String photoNew = photo ;
            if(photo!=null){
            	photoNew = photo.replace("\\","/");
            }
            sb.append("<li><a href='./perinfo-id-"
                + user.getHyid() + ".htm'  ><img src='"  + SysCommonFunc.getPhotoSmallView() + "/"+ photoNew   + "'  /><span>" + username  + "</span> <p>"+scity+"</p><p>"+age+"</p></a></li>");
              }
        sb.append("</ul>");




        return sb;
    }



    // 人气最旺女会员

    public static void getTopRqzwWonList()
    {
        List list = QueryRecord
            .queryByHbm(
                "from Userinfo o where o.sex='11' and o.img > 0 and o.isdel=0  and o.important>0 order by o.important asc",
                6, 1, false, 0).resultList;
        if (CacheToolsNew.topRqzwWonList != null)
        {
            CacheToolsNew.topRqzwWonList.clear();
            CacheToolsNew.topRqzwWonList = null;
        }
        CacheToolsNew.topRqzwWonList = list;

    }

    public static StringBuffer getTopRqzwWonSb()
    {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; CacheToolsNew.topRqzwWonList != null
            && i < CacheToolsNew.topRqzwWonList.size(); i++)
        {
            Userinfo user = (Userinfo) CacheToolsNew.topRqzwWonList.get(i);
            Userother uother = HYRegMng.getUserOtherByHyid(user.getHyid()
                .toString());
            if (uother == null)
                uother = new Userother();
            String photo = null;
            if (uother.getUserphoto1() != null
                && uother.getUserphoto1().length() > 0)
                photo = uother.getUserphoto1();
            else if (uother.getUserphoto2() != null
                && uother.getUserphoto2().length() > 0)
                photo = uother.getUserphoto2();
            else if (uother.getUserphoto3() != null
                && uother.getUserphoto3().length() > 0)
                photo = uother.getUserphoto3();
            else if (uother.getUserphoto4() != null
                && uother.getUserphoto4().length() > 0)
                photo = uother.getUserphoto4();
            else if (uother.getUserphoto5() != null
                && uother.getUserphoto5().length() > 0)
                photo = uother.getUserphoto5();

            String sql = "<li><a href='perinfo-id-" + user.getHyid()
                + ".htm'  ><img src='"
                + SysCommonFunc.getPhotoSmallView() + File.separator + photo
                + "'></a>\n<h5><a href='perinfo-id-" + user.getHyid()
                + ".htm' target='_blank'>" + user.getLcname()
                + "</a></h5><h6>人气:" + user.getHots() + "</h6></li>\n";
            sb.append(sql);
        }

        return sb;
    }

    public static StringBuffer getTopRqzwWonSbNew()
    {
        String hots="0";
        StringBuffer sb = new StringBuffer();
        sb.append("<ul>");
        for (int i = 0; CacheToolsNew.topRqzwWonList != null
            && i < CacheToolsNew.topRqzwWonList.size(); i++)
        {
            Userinfo user = (Userinfo) CacheToolsNew.topRqzwWonList.get(i);
            Userother uother = HYRegMng.getUserOtherByHyid(user.getHyid()
                .toString());
            if (uother == null)
                uother = new Userother();
            String photo = null;
            if (uother.getUserphoto1() != null
                && uother.getUserphoto1().length() > 0)
                photo = uother.getUserphoto1();
            else if (uother.getUserphoto2() != null
                && uother.getUserphoto2().length() > 0)
                photo = uother.getUserphoto2();
            else if (uother.getUserphoto3() != null
                && uother.getUserphoto3().length() > 0)
                photo = uother.getUserphoto3();
            else if (uother.getUserphoto4() != null
                && uother.getUserphoto4().length() > 0)
                photo = uother.getUserphoto4();
            else if (uother.getUserphoto5() != null
                && uother.getUserphoto5().length() > 0)
                photo = uother.getUserphoto5();
            hots=user.getHots().toString();

            String age = "";
            int size = 0; // 查找条数
            String scity = "";
            Date date = new Date(System.currentTimeMillis());
            age = (date.getYear() - user.getCsdate().getYear()) + "岁";
            if (user.getS1().equals("北京") || user.getS1().equals("上海")
                    || user.getS1().equals("重庆") || user.getS1().equals("天津")
                    )
            {
                scity = user.getS1();
            }
            else
            {
                scity = user.getS1()+user.getS2();
            }
            String username = user.getLcname();
            if(null != username && username.length()>5){
            	username = username.substring(0, 4)+"..";
            }
            String photoNew = photo ;
            if(photo!=null){
            	photoNew = photo.replace("\\","/");
            }
            sb.append("<li><a href='./perinfo-id-"
                + user.getHyid() + ".htm' ><img src='"  + SysCommonFunc.getPhotoSmallView() + "/" + photoNew   + "'  /> <span>" + username
                    + "</span> <p>"+scity+"</p><p>"+age+"</p></a></li>");

        }
        sb.append("</ul>");

        return sb;

    }

    // 近日最新注册会员

    public static void getNewUserList()
    {
        System.out.println("开始读取最新注册会员");
        //List list = QueryRecord
            //.queryByHbm(
                //"from Userinfo as o where o.isdel=0 and regtime2 is not null and regtime2 > sysdate-2  order by regtime2 desc,hyid desc",
                //360, 1, false, 0).resultList;
        List list = QueryRecord
        .queryByHbm(
            "from Userinfo as o where o.isdel=0 and regtime2 is not null order by regtime2 desc,hyid desc",
            170, 1, false, 0).resultList;
        // System.out.println("from Userinfo as o where o.isdel=0  order by regtime2 desc,hyid desc");
        if (CacheToolsNew.newUserList != null)
        {
            CacheToolsNew.newUserList.clear();
            CacheToolsNew.newUserList = null;
        }
        CacheToolsNew.newUserList = list;
        System.out.println("开始读取最新注册会员结束");
    }

    public static StringBuffer getNewUserSb()
    {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; CacheToolsNew.newUserList != null
            && i < CacheToolsNew.newUserList.size(); i++)
        {
            Userinfo user = (Userinfo) CacheToolsNew.newUserList.get(i);

            sb.append("<a href='perinfo-id-" + user.getHyid()
                + ".htm' target='_blank'>" + user.getLcname()
                + "</a>");
        }

        return sb;
    }

    public static StringBuffer getNewUserSbNew()
    {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; CacheToolsNew.newUserList != null
            && i < CacheToolsNew.newUserList.size(); i++)
        {
            Userinfo user = (Userinfo) CacheToolsNew.newUserList.get(i);

            sb.append("<a href='./perinfo-id-" + user.getHyid()
                + ".htm' target='_blank'>" + user.getLcname()
                + "</a>");
        }

        return sb;
    }
    public static QueryResult getXWDT(int a){
        String sql = "from Bb as o where o.reId =0 and o.sortid="+a + " and  o.ischeck='1'  order by o.ontop desc,o.commend desc, o.reTime desc,o.stime desc";

        QueryResult qr  = QueryRecord.queryByHbm(sql,5,1);

        return qr;
    }


}
