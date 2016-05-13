package com.web.obj.extend;
import com.web.obj.Dictype;
import com.web.obj.Dic;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.List;
import com.web.map.DicMap;
import org.apache.commons.beanutils.PropertyUtils;
public class DicTypeWithDic extends Dictype{

  public Hashtable diclist = new Hashtable();
  public List      diclist1 = null;


  public DicTypeWithDic(Dictype dt) throws Exception
  {
    PropertyUtils.copyProperties(this,dt);
    //System.out.println(this.getTypeid());
    this.initDicList();
    //System.out.println(this.diclist.size());
    //System.out.println(this.diclist1.size());
  }

  public  Dic getSingDicObject()
  {
     Enumeration enu = diclist.elements();
     if(enu.hasMoreElements())
       return (Dic)enu.nextElement();
     return null;
  }

  public  String getSingDicValue(int type)
  {
    Enumeration enu = diclist.elements();
    if(enu.hasMoreElements())
    {
      Dic dic = (Dic)enu.nextElement();
      if(type ==1)
        return dic.getContent() == null?"":dic.getContent();
      if(type ==2)
        return dic.getExtend1() == null?"":dic.getExtend1();
      if(type ==3)
        return dic.getExtend2() == null?"":dic.getExtend2();
      if(type ==4)
        return dic.getExtend3() == null?"":dic.getExtend3();
    }
    return "";
  }

  public  Dic getDicByDicNumber(String dicnumber)
  {
    if(dicnumber == null || dicnumber.length() ==0)
      return null;
    return (Dic)diclist.get(dicnumber);
  }

  public  void initDicList()
  {
    this.diclist1 = DicMap.getDicList(this.getTypeid());
    for(int i=0;diclist1 != null && i < diclist1.size();i ++)
    {
      Dic dic = (Dic)diclist1.get(i);
      this.diclist.put(dic.getDicnumber(),dic);
    }
  }


}
