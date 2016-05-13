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

public class ProductMng {
  public ProductMng() {
  }

  public static String  addProduct(HttpServletRequest request)
  {
     String s = null;
     Product p = (Product)BeanInitializer.initBean(Product.class,request);

     try
     {
        p.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_PRODUCT));
        p.setIsdel(new Integer(0));
        HbmOperator.insert(p);
     }catch(Exception e)
     {
        s = "增加服务项目出错，请与开发商联系！";
        System.out.println(e.getMessage());
     }
     return s;
  }

  public static String modProduct(HttpServletRequest request,Product pp)
  {
      String s = null;
      try
      {
          BeanInitializer.updateBean(pp,request);
          HbmOperator.update(pp);

      }catch(Exception e)
      {
        System.out.println(e.getMessage());
        s = "修改服务项目出错！";
      }

      return s;
  }

  public static Product getProduct(String id)
  {
     List list = HbmOperator.list("from Product as o where o.id="+id);
     if(list.size() >0)
        return (Product)list.get(0);
      return null;
  }

  public static List getProductList()
  {
     return HbmOperator.list("from Product as o where o.isdel = 0 order by orderno asc");
  }

}
