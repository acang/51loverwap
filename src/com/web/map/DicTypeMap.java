package com.web.map;
import hibernate.db.*;
import java.util.*;
import com.web.obj.*;
import com.common.SysDefine;

public class DicTypeMap {
  public DicTypeMap() {
  }

  public static List getAllTypeList()
  {
    return HbmOperator.list("from Dictype as o");
  }

  public static Dictype getDicType(String dictypeid)
  {
     String hsql = "from Dictype as o where o.typeid ='"+dictypeid+"'";
     List list = HbmOperator.list(hsql);
     if(list != null && list.size()>0)
       return (Dictype)list.get(0);
     return null;
  }

  public static String getDicTypeTree(String treename,String pareid,String url)
  {
    StringBuffer sb = new StringBuffer();
    List list = getAllTypeList();
    for(int i =0;list != null && i < list.size();i ++)
    {
      Dictype dt = (Dictype)list.get(i);
      sb.append(treename+".add("+pareid+dt.getTypeid()+","+pareid+",'"+dt.getTypename()+"','"+url+"?"+SysDefine.DIC_TYPE_NAME+"="+dt.getTypeid()+"');\n");
    }

    return sb.toString();
  }

  public static List getDicType(int typetypeId){
      String hsql=" from o in class "+Dictype.class.getName()+" where o.typetype="+typetypeId;
      return HbmOperator.list(hsql);
  }
}
