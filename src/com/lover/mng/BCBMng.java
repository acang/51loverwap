package com.lover.mng;
import com.web.common.*;
import com.common.*;
import javax.servlet.http.HttpServletRequest;

import com.web.obj.*;
import com.web.common.beanutil.*;
import hibernate.db.*;
import java.util.*;


public class BCBMng {
  public BCBMng() {
  }

  public static Bcb getBcbFromid(String id)
  {
    List list = HbmOperator.list("from Bcb as o where o.id = "+id);
    Bcb b = null;
    if(list.size() > 0)
       b= (Bcb)list.get(0);
    return b;
  }

}
