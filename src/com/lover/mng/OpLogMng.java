package com.lover.mng;


import com.web.common.*;
import com.common.*;
import javax.servlet.http.HttpServletRequest;

import com.web.obj.*;
import com.web.common.beanutil.*;
import hibernate.db.*;
import java.util.*;
import java.io.*;
import com.web.obj.extend.*;
import com.lover.LoverTools;
import com.web.servlet.InitEnvironment;

public class OpLogMng {
  public OpLogMng() {
  }


  public static String SEQ_OPLOG = "SEQ_OPLOG";
  public static int OP_LOG_U_R_PHOTO = 1;
  public static int OP_LOG_U_R_ARTICLE = 2;

  public static int OP_LOG_U_R_FR      = 3;


  ////1 删除照片

  public static void userAdminRPhotoLog(Userinfo user,Admininfo admin,String photoname)
  {
    try
    {
      Oplog ol = new Oplog();
      ol.setAdid(admin.getId());
      ol.setAdname(admin.getAdminname());
      ol.setContentid(new Long(0));
      ol.setHyid(user.getHyid());
      ol.setHyname(user.getUsername());
      ol.setId(SysCommonFunc.getSequenceIdForOracle(OpLogMng.SEQ_OPLOG));
      ol.setOpdate(new Date(System.currentTimeMillis()));
      ol.setOptype(new Integer(1));
      ol.setPhotoname(photoname);
      HbmOperator.insert(ol);
    }catch(Exception e)
    {
      System.out.println("管理员删除照片日志出错"+e.getMessage());
    }
  }


  public static void userAdminAPhotoLog(Userinfo user,Admininfo admin,String photoname)
  {
    try
    {
      Date date = new java.util.Date(System.currentTimeMillis());
      String dates = DateTools.dateToString(date,false);

      String hsql = "from Oplog where optype = 4 and adid = "+admin.getId()+" and hyid = "+user.getHyid()+" and opdate > to_date('"+dates+"','YYYY-MM-DD')";
      List list = HbmOperator.list(hsql);
      if(list.size() > 0)
      {
        Oplog ol = (Oplog)list.get(0);
        ol.setOpdate(date);
        ol.setPhotoname(photoname);
        HbmOperator.update(ol);


      }else
      {
        Oplog ol = new Oplog();
        ol.setAdid(admin.getId());
        ol.setAdname(admin.getAdminname());
        ol.setContentid(new Long(0));
        ol.setHyid(user.getHyid());
        ol.setHyname(user.getUsername());
        ol.setId(SysCommonFunc.getSequenceIdForOracle(OpLogMng.SEQ_OPLOG));
        ol.setOpdate(new Date(System.currentTimeMillis()));
        ol.setOptype(new Integer(4));
        ol.setPhotoname(photoname);
        HbmOperator.insert(ol);
      }
    }catch(Exception e)
    {
      System.out.println("管理员删除照片日志出错"+e.getMessage());
    }
  }




  public static void userAdminRArticleLog(String hyid,Admininfo admin,String delid,String cid)
  {
    try
    {
      Userinfo user = HYRegMng.getAllUserinfoByHyid(hyid);
      Userarticle article = GRZQMng.getUserArticle(hyid,delid);
      Oplog ol = new Oplog();
      ol.setAdid(admin.getId());
      ol.setAdname(admin.getAdminname());
      ol.setContentid(new Long(0));
      ol.setHyid(user.getHyid());
      ol.setHyname(user.getUsername());
      ol.setId(SysCommonFunc.getSequenceIdForOracle(OpLogMng.SEQ_OPLOG));
      ol.setOpdate(new Date(System.currentTimeMillis()));
      ol.setOptype(new Integer(2));
      ol.setOpdes(article.getTitle());
      ol.setContentid(article.getContentid());
      HbmOperator.insert(ol);
    }catch(Exception e)
    {
      System.out.println("管理员删除文集日志出错");
    }
  }


  public static void userAdminModUserinfoLog(Userinfo user,Admininfo admin,String logs)
  {
    try
    {

      Oplog ol = new Oplog();
      ol.setAdid(admin.getId());
      ol.setAdname(admin.getAdminname());
      ol.setContentid(new Long(0));
      ol.setHyid(user.getHyid());
      ol.setHyname(user.getUsername());
      ol.setId(SysCommonFunc.getSequenceIdForOracle(OpLogMng.SEQ_OPLOG));
      ol.setOpdate(new Date(System.currentTimeMillis()));
      ol.setOptype(new Integer(3));
      ol.setOpdes("");
      ol.setContentid(new Long(0));

      String  upath = InitEnvironment.getSystemProValue("webPath");
      String  dates = DateTools.DateToString(ol.getOpdate(),DateTools.FORMART_yyyyMMdd);
      String  fpath = upath+File.separator+"uinfo"+File.separator+ dates;
      File file = new File(fpath);
      if(!file.exists())
        file.mkdirs();

      FileWriter fw = new FileWriter(fpath+File.separator+ol.getId()+".html");
      fw.write(logs);
      fw.flush();
      fw.close();

      HbmOperator.insert(ol);


    }catch(Exception e)
    {
      System.out.println("管理员修改用户信息日志出错");
    }
  }

  public static void logUserinfo(Userinfo user,Userother uother,StringBuffer logs)
  {
      logs.append("用户名:"+user.getUsername()+"<br>");
      logs.append("密码:"+user.getPassword()+"<br>");
      logs.append("密码问题:"+user.getAsk()+"<br>");
      logs.append("密码答案:"+user.getAnswer()+"<br>");
      logs.append("昵称:"+user.getLcname()+"<br>");
      logs.append("性别:"+DicList.getDicValue(SysDefine.DIC_SEX,user.getSex(),1)+"<br>");
      logs.append("生日:"+DateTools.dateToString(user.getCsdate(),false)+"<br>");
      logs.append("住址:"+user.getS1()+user.getS2()+"<br>");
      logs.append("月薪:"+DicList.getDicValue(SysDefine.DIC_YX,user.getYx(),1)+"<br>");
      logs.append("血型:"+DicList.getDicValue(SysDefine.DIC_XX,user.getSx(),1)+"<br>");
      logs.append("住房:"+DicList.getDicValue(SysDefine.DIC_ZF,user.getZf(),1)+"<br>");
      logs.append("星座:"+DicList.getDicValue(SysDefine.DIC_XZ,user.getXz(),1)+"<br>");
      logs.append("身高:"+DicList.getDicValue(SysDefine.DIC_SG,user.getSg(),1)+"<br>");
      logs.append("体重:"+DicList.getDicValue(SysDefine.DIC_TZ,user.getTz(),1)+"<br>");
      logs.append("肤色:"+DicList.getDicValue(SysDefine.DIC_FS,user.getFs(),1)+"<br>");
      logs.append("婚姻状况:"+DicList.getDicValue(SysDefine.DIC_HYZH,user.getHyzk(),1)+"<br>");
      logs.append("文化程度:"+DicList.getDicValue(SysDefine.DIC_WHCD,user.getWhcd(),1)+"<br>");
      logs.append("固定电话:"+user.getTel()+"<br>");
      logs.append("手机:"+user.getSjtel()+"<br>");
      logs.append("QQ:"+user.getOicq()+"<br>");
      logs.append("Email:"+user.getEmail()+"<br>");
      logs.append("性格特征:"+LoverTools.getXgtzdes(user)+"<br>");
      logs.append("业余安排:"+LoverTools.getYYapdes(user)+"<br>");
      logs.append("交友意向:"+LoverTools.getJYYXDes(user)+"<br>");
      logs.append("情爱关系:"+uother.getQggx()+"<br>");
      logs.append("理想情人:"+uother.getLxqr()+"<br>");
      logs.append("交友留言:"+uother.getJyly()+"<br>");
      logs.append("审核人："+user.getShr()+"<br>");
      logs.append("审核情况："+user.getShqk()+"<br><br><br><br>");
  }

  public static void logUserinfo(Userinfo user,Userother uother,StringBuffer logs,Userinfo srcUser,Userother srcother)
  {
      if(user.getUsername().equals(srcUser.getUsername()))
      {
        logs.append("用户名:" + user.getUsername() + "<br>");
      }else
      {
         logs.append("<strong>用户名:" + user.getUsername() + "</strong><br>");
      }

      if(user.getPassword().equals(srcUser.getPassword()))
      {
        logs.append("密码:"+user.getPassword()+"<br>");
      }else
      {
         logs.append("<strong>密码:"+user.getPassword()+"</strong><br>");
      }



      logs.append("密码问题:"+user.getAsk()+"<br>");
      logs.append("密码答案:"+user.getAnswer()+"<br>");

      if(user.getLcname().equals(srcUser.getLcname()))
      {
        logs.append("昵称:"+user.getLcname()+"<br>");
      }else
      {
         logs.append("<strong>昵称:"+user.getLcname()+"</strong><br>");
      }

      if(user.getSex().equals(srcUser.getSex()))
      {
        logs.append("性别:" +
                    DicList.getDicValue(SysDefine.DIC_SEX, user.getSex(), 1) +
                    "<br>");
      }else
      {
        logs.append("<strong>性别:" +
                    DicList.getDicValue(SysDefine.DIC_SEX, user.getSex(), 1) +
                    "</strong><br>");

      }

      if(DateTools.dateToString(user.getCsdate(),false).equals(DateTools.dateToString(srcUser.getCsdate(),false)))
      {
        logs.append("生日:" + DateTools.dateToString(user.getCsdate(), false) +
                    "<br>");
      }else
      {
          logs.append("<strong>生日:" + DateTools.dateToString(user.getCsdate(), false) +
                    "</strong><br>");
      }


      logs.append("住址:"+user.getS1()+user.getS2()+"<br>");

      if(user.getYx().equals(srcUser.getYx()))
      {
        logs.append("月薪:" + DicList.getDicValue(SysDefine.DIC_YX, user.getYx(), 1) +
                    "<br>");
      }else
      {
          logs.append("<strong>月薪:" + DicList.getDicValue(SysDefine.DIC_YX, user.getYx(), 1) +
                    "</strong><br>");
      }


      logs.append("血型:"+DicList.getDicValue(SysDefine.DIC_XX,user.getSx(),1)+"<br>");
      logs.append("住房:"+DicList.getDicValue(SysDefine.DIC_ZF,user.getZf(),1)+"<br>");
      logs.append("星座:"+DicList.getDicValue(SysDefine.DIC_XZ,user.getXz(),1)+"<br>");

      if(user.getSg().equals(srcUser.getSg()))
      {
        logs.append("身高:" + DicList.getDicValue(SysDefine.DIC_SG, user.getSg(), 1) +
                    "<br>");
      }else
      {
          logs.append("<strong>身高:" + DicList.getDicValue(SysDefine.DIC_SG, user.getSg(), 1) +
                    "</strong><br>");
      }

      if(user.getTz().equals(srcUser.getTz()))
      {
        logs.append("体重:" + DicList.getDicValue(SysDefine.DIC_TZ, user.getTz(), 1) +
                    "<br>");
      }else
      {
          logs.append("<strong>体重:" + DicList.getDicValue(SysDefine.DIC_TZ, user.getTz(), 1) +
                    "</strong><br>");
      }


      logs.append("肤色:"+DicList.getDicValue(SysDefine.DIC_FS,user.getFs(),1)+"<br>");

      if(user.getHyzk().equals(srcUser.getHyzk()))
      {
        logs.append("婚姻状况:" +
                    DicList.getDicValue(SysDefine.DIC_HYZH, user.getHyzk(), 1) +
                    "<br>");
      }else
      {
        logs.append("<strong>婚姻状况:" +
                    DicList.getDicValue(SysDefine.DIC_HYZH, user.getHyzk(), 1) +
                    "</strong><br>");

      }

      if(user.getWhcd().equals(srcUser.getWhcd()))
      {
        logs.append("文化程度:" +
                    DicList.getDicValue(SysDefine.DIC_WHCD, user.getWhcd(), 1) +
                    "<br>");
      }else
      {
        logs.append("<strong>文化程度:" +
                    DicList.getDicValue(SysDefine.DIC_WHCD, user.getWhcd(), 1) +
                    "</strong><br>");

      }

      if (srcUser.getTel()!=null)
      {
	      if((user.getTel()+"").equals(srcUser.getTel()+""))
	      {
	        logs.append("固定电话:" + user.getTel() + "<br>");
	      }else
	      {
	         logs.append("<strong>固定电话:" + user.getTel() + "</strong><br>");
	      }
      }else {
		  if (user.getTel()==null || user.getTel().length()==0)
		  {
			  logs.append("固定电话:" + user.getTel() + "<br>");
		  }else
	      {
		      logs.append("<strong>固定电话:" + user.getTel() + "</strong><br>");
		  }
	  }

      if((user.getSjtel()+"").equals(srcUser.getSjtel()+"") || (srcUser.getSjtel()==null && user.getSjtel()==null) || (srcUser.getSjtel()==null && user.getSjtel().length()==0))
      {
        logs.append("手机:" + user.getSjtel() + "<br>");
      }else
      {
         logs.append("<strong>手机:" + user.getSjtel() + "</strong><br>");
      }
      if((user.getOicq()+"").equals(srcUser.getOicq()+"") || (srcUser.getOicq()==null && user.getOicq()==null) || (srcUser.getOicq()==null && user.getOicq().length()==0))
      {
        logs.append("QQ:" + user.getOicq() + "<br>");
      }else
      {
        logs.append("<strong>QQ:" + user.getOicq() + "</strong><br>");
      }
      if((user.getEmail()+"").equals(srcUser.getEmail()+"") || (srcUser.getEmail()==null && user.getEmail()==null) || (srcUser.getEmail()==null && user.getEmail().length()==0))
      {
        logs.append("Email:" + user.getEmail() + "<br>");
      }else
      {
         logs.append("<strong>Email:" + user.getEmail() + "</strong><br>");
      }


      logs.append("性格特征:"+LoverTools.getXgtzdes(user)+"<br>");
      logs.append("业余安排:"+LoverTools.getYYapdes(user)+"<br>");

      if(LoverTools.getJYYXDes(user).equals(LoverTools.getJYYXDes(srcUser)))
      {
        logs.append("交友意向:" + LoverTools.getJYYXDes(user) + "<br>");
      }else
      {
          logs.append("<strong>交友意向:" + LoverTools.getJYYXDes(user) + "</strong><br>");
      }

      if((uother.getQggx()+"") .equals(srcother.getQggx()+""))
      {
        logs.append("情爱关系:" + uother.getQggx() + "<br>");
      }else
      {
         logs.append("<strong>情爱关系:" + uother.getQggx() + "</strong><br>");
      }

      if((uother.getLxqr()+"").equals(srcother.getLxqr()+""))
      {
        logs.append("理想情人:" + uother.getLxqr() + "<br>");
      }else
      {
         logs.append("<strong>理想情人:" + uother.getLxqr() + "</strong><br>");
      }

      if((uother.getJyly()+"").equals(srcother.getJyly()+""))
      {
        logs.append("交友留言:" + uother.getJyly() + "<br>");
      }else
      {
         logs.append("<strong>交友留言:" + uother.getJyly() + "</strong><br>");
      }

      if((user.getShr()+"").equals(srcUser.getShr()) || (srcUser.getShr()==null && user.getShr()==null) || (srcUser.getShr()==null && user.getShr().length()==0))
      {
        logs.append("审核人：" + user.getShr() + "<br>");
      }else
      {
         logs.append("<strong>审核人：" + user.getShr() + "</strong><br>");
      }

      if((user.getShqk()+"").equals(srcUser.getShqk()) || (srcUser.getShqk()==null && user.getShqk()==null) || (srcUser.getShqk()==null && user.getShqk().length()==0))
      {
        logs.append("审核情况：" + user.getShqk() + "<br>");
      }else
      {
         logs.append("<strong>审核情况：" + user.getShqk() + "</strong><br>");
      }

  }


///uploadarticle/upload_wj1.jsp?delid=<%=uarticle.getId()%>&cid=<%=uarticle.getContentid()%>
  public static void addOpLog(Userinfo user,Admininfo admin,HttpServletRequest request,int type)
  {
    try
    {
//      if (type == OpLogMng.OP_LOG_U_R_PHOTO) {
//        String des = "删除照片" + request.getParameter("bj");
//        Oplog ol = new Oplog();
//        ol.setHyid(user.getHyid().longValue());
//        if(admin != null)
//          ol.setAdid(admin.getId().longValue());
//        else
//          ol.setAdid(0);
//        ol.setId(SysCommonFunc.getSequenceIdForOracle(OpLogMng.SEQ_OPLOG));
//        ol.setOpdate(new Date(System.currentTimeMillis()));
//        ol.setOpdes(des);
//        HbmOperator.insert(ol);
//        return;
//      }
//      if (type == OpLogMng.OP_LOG_U_R_ARTICLE) {
//        String des = "删除文集" + request.getParameter("delid");
//        Oplog ol = new Oplog();
//        if(admin != null)
//          ol.setAdid(admin.getId().longValue());
//        else
//          ol.setAdid(0);
//        ol.setHyid(user.getHyid().longValue());
//        ol.setId(SysCommonFunc.getSequenceIdForOracle(OpLogMng.SEQ_OPLOG));
//        ol.setOpdate(new Date(System.currentTimeMillis()));
//        ol.setOpdes(des);
//        HbmOperator.insert(ol);
//        return;
//      }
//      if (type == OpLogMng.OP_LOG_U_R_FR) {
//        String des = "删除好友" + SysCommonFunc.getStrParameter(request,"id");
//        Oplog ol = new Oplog();
//        if(admin != null)
//          ol.setAdid(admin.getId().longValue());
//        else
//          ol.setAdid(0);
//        ol.setHyid(user.getHyid().longValue());
//        ol.setId(SysCommonFunc.getSequenceIdForOracle(OpLogMng.SEQ_OPLOG));
//        ol.setOpdate(new Date(System.currentTimeMillis()));
//        ol.setOpdes(des);
//        HbmOperator.insert(ol);
//        return;
//      }

    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}
