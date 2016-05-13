package com.web.common;

import javax.servlet.http.*;
import com.web.bean.*;
import com.web.obj.*;
import org.apache.commons.beanutils.*;
import hibernate.db.*;
import java.util.Enumeration;
import javax.servlet.*;
import com.common.SysDefine;
import java.util.*;
import com.lover.*;


public class SessionListener
    implements HttpSessionListener,HttpSessionAttributeListener  {

  /**
   * 有人登陆时
   * 2006-11-23修改,用户打开首页,但不登陆,此时session已创建,按需求不能加1,所以加1的操作只能
   * 放到密码验证后进行
   * @param httpSessionEvent HttpSessionEvent
   */
  public void sessionCreated(HttpSessionEvent httpSessionEvent) {
     HttpSession session = httpSessionEvent.getSession();

     CacheTools.setOnline(true,"add",session.getId());

  }

  //注销时
  public void sessionDestroyed(HttpSessionEvent httpSessionEvent)
  {
    HttpSession session = httpSessionEvent.getSession();
    CacheTools.setOnline(false,"remove",session.getId());

  }
  public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent)
  {
      try
      {
         HttpSession session = httpSessionBindingEvent.getSession();
         if(httpSessionBindingEvent.getName().equals("IPLOG"))
         {
           Date cdate = new Date(System.currentTimeMillis());
           IpLog iplog = new IpLog();
           iplog.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_IPLOG));
           iplog.setIsadmin(new Integer(0));
           iplog.setLogintime(cdate);
           iplog.setLogintimestr(DateTools.DateToString(cdate,DateTools.FORMART_yyyyMMddHHmmss));
           iplog.setUserip(new Long(1));
           iplog.setSessionid(session.getId());
           iplog.setUserstrip(httpSessionBindingEvent.getValue().toString());
           HbmOperator.insert(iplog);
         }

      }catch(Exception e)
      {
         System.out.println(e.getMessage()+"处理session增加出错");
      }

  }
  public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent)
  {

  }
  public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent)
  {

  }




}
