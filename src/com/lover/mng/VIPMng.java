package com.lover.mng;
import com.web.common.*;
import com.common.*;
import javax.servlet.http.HttpServletRequest;

import com.web.obj.*;
import com.web.common.beanutil.*;
import hibernate.db.*;
import java.util.*;
import com.web.bean.QueryRecord;
import com.lover.mng.HYRegMng;
import com.web.servlet.InitEnvironment;
import java.io.File;
import java.math.BigDecimal;

public class VIPMng {
  public VIPMng() {
  }



  public static Vipuserrecord getVipLastRecordFromHyid(String hyid)
  {
    Vipuserrecord vpu = null;
    String sql = "from Vipuserrecord as o where o.hyid = " + hyid + " order by o.zzsj desc";
    List list = HbmOperator.list(sql);
    if(list.size() >0)
      vpu =  (Vipuserrecord)list.get(0);
    return vpu;
  }
  public static String addVipRecord(HttpServletRequest request,Admininfo admin,String hyid,String zzsj,Product p,Userinfo user)
  {
    String s = null;
    String sffs = SysCommonFunc.getStrParameter(request,"sffs");
    String area = SysCommonFunc.getStrParameter(request,"area");
    if(sffs == null)
       sffs = "1";
    try
    {
      Vipuserrecord viprecord = (Vipuserrecord) BeanInitializer.initBean(
          Vipuserrecord.class, request);
      viprecord.setDatetype(p.getDatetype());
      viprecord.setCzry(admin.getAdminname());
      viprecord.setFlag(p.getFlag());
      viprecord.setHttpurl(user.getHttpurl());
      viprecord.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.
          SEQ_VIPRECORD));
      viprecord.setName(p.getName());
      viprecord.setServiceyear(p.getServiceyear());
      //viprecord.setSimplename(p.getSimplename());
      viprecord.setSqdj(p.getSqdj());
      //viprecord.setTransamt(sffs.equals("1") ? p.getTransamt() : new Long(0));
      if (!sffs.equals("1")){
    	  viprecord.setTransamt(new Double(0));
      }else{
    	  viprecord.setTransamt(new Double(SysCommonFunc.getStrParameter(request,"transamt")));
      }
      viprecord.setHyid(user.getHyid().longValue());
      Vector saveList = new Vector();
      MutSeaObject mso = new MutSeaObject();
      mso.setHbmSea(viprecord, MutSeaObject.SEA_HBM_INSERT);
      saveList.add(mso);


      if(p.getFlag().intValue() != 5.)
      {
      String sql = "update userinfo set zzsj = to_date('"+zzsj+"','YYYY-MM-DD'),area='"+area+"',";
      if(user.getImg() != null && user.getImg().intValue() > 0)
        sql =sql + " tjsj = sysdate,";

      sql = sql+" flag = "+p.getFlag()  + " WHERE hyid="+hyid;
      mso = new MutSeaObject();
      mso.setSqlSea(sql,MutSeaObject.SEA_SQL_UPDATE);
      saveList.add(mso);
      }else
      {
          ///增加用户白金豆
          UserBjd ub = null;
          List list = HbmOperator.list("from UserBjd as o where hyid="+user.getHyid());
          if(list == null || list.size() ==0)
          {
              ub = new UserBjd();
              ub.setBjdnumber(p.getServiceyear().longValue());
              ub.setHyid(user.getHyid());

              mso = new MutSeaObject();
              mso.setHbmSea(ub,MutSeaObject.SEA_HBM_INSERT);
              saveList.add(mso);
          }else
          {
              ub = (UserBjd)list.get(0);
              ub.setBjdnumber(ub.getBjdnumber()+p.getServiceyear().longValue());
              mso = new MutSeaObject();
              mso.setHbmSea(ub,MutSeaObject.SEA_HBM_UPDATE);
              saveList.add(mso);
          }
          ///增加充值记录

          UserBjdRecord ubr = new UserBjdRecord();
          ubr.setId(SysCommonFunc.getSequenceIdForOracle("SEQ_WTJD"));
          ubr.setBjddesc("购买"+p.getName());
          ubr.setBjdnumber(p.getServiceyear().longValue());
          ubr.setHyid(user.getHyid().toString());
          ubr.setRecodeTime(new Date(System.currentTimeMillis()));

               mso = new MutSeaObject();
              mso.setHbmSea(ubr,MutSeaObject.SEA_HBM_INSERT);
              saveList.add(mso);

      }



      HbmOperator.SeaMutData(saveList);


    }catch(Exception e)
    {
       System.out.println(e.getMessage());
       e.printStackTrace();
       s = "办理出错！";
    }

    return s;
  }
}
