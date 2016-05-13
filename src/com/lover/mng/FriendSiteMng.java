package com.lover.mng;
import com.web.common.*;
import com.common.*;
import com.web.obj.*;
import com.web.common.beanutil.*;
import java.util.*;
import com.web.servlet.InitEnvironment;

import java.util.*;
import javax.servlet.http.*;
import java.io.*;
import org.apache.commons.fileupload.*;
import hibernate.db.*;

public class FriendSiteMng {
  public FriendSiteMng() {
  }


  public static String userAddFriendSiteMng(HttpServletRequest request)
  {
     String s = null;
     Friendsite fdr = (Friendsite)BeanInitializer.initBean(Friendsite.class,request);
     if(fdr.getSiteurl() == null || fdr.getSiteurl().trim().length() ==0)
       return "请输入网站地址";
     if(fdr.getSitename() == null || fdr.getSitename().trim().length() ==0)
       return "请输入网站名称";
     if(fdr.getLogourl() == null || fdr.getLogourl().trim().length() ==0)
       return "请输入联系电话";
     if(fdr.getSiteadmin() == null || fdr.getSiteadmin().trim().length() ==0)
       return "请输入站长姓名";
     if(fdr.getEmail() == null || fdr.getEmail().trim().length() ==0)
       return "请输入邮箱地址";

     Long id = SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_FRIEND_SITE);
     fdr.setId(new Integer(id.intValue()));
     fdr.setIsgood(0);
     fdr.setIsok(0);
     fdr.setXuhao(new Integer(0));
     fdr.setSitepassword(DateTools.dateToString(new java.util.Date(System.currentTimeMillis()),true));
     try
     {

       HbmOperator.insert(fdr);
     }catch(Exception e)
     {
        s = "申请链接出错，请与管理员联系！";
        System.out.println(e.getMessage());
     }
     return s;
  }


  public static String  modFriendsite(HttpServletRequest request)
  {
     String s = null;
     String id = SysCommonFunc.getStrParameter(request,"id");
     Friendsite fr = null;
     if(id.length() > 0)
     {
       fr = FriendSiteMng.getFriendsite("from Friendsite as o where o.id=" + id);
       BeanInitializer.updateBean(fr,request);
     }
     else
       fr = (Friendsite)BeanInitializer.initBean(Friendsite.class,request);
     if(fr.getXuhao() == null)
       fr.setXuhao(new Integer(0));
     try
     {
        if(fr.getId() == null || fr.getId().intValue() ==0)
        {
           fr.setId(new Integer(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_FRIEND_SITE).intValue()));
           HbmOperator.insert(fr);
        }else
          HbmOperator.update(fr);
     }catch(Exception e)
     {
        s = "保存友情链接出错，请与开发商联系！";
        System.out.println(e.getMessage());
     }
     return s;
  }

  public static Friendsite  getFriendsite(String sql)
  {
     Friendsite fr = null;
     List list = HbmOperator.list(sql);
     if(list.size() >0)
        return (Friendsite)list.get(0);
     return fr;
  }


}
