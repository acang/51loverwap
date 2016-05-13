package com.lover.mng;
import hibernate.db.HbmOperator;

import java.util.List;

import com.common.SysDefine;
import com.web.common.SysCommonFunc;
import com.web.obj.Checkinfo;

public class CheckinfoMng {

  public CheckinfoMng() {
  }

  public static void addCheckInfo(Checkinfo checkinfo)
  {
	  try
	    {
		  checkinfo.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_SHAREID));
	      HbmOperator.insert(checkinfo);
	    }catch(Exception e)
	    {
	       System.out.println(e.getMessage());
	    }
  }
  
  public static List getSharearticleList()
  {
     return HbmOperator.list("from checkinfo as o  order by o.time desc");
  }

  public static void main(String args[])
  {

  }
}
