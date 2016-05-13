package com.lover.mng;
import com.web.common.*;
import com.common.*;

import javax.servlet.http.HttpServletRequest;

import com.web.obj.*;
import com.web.common.beanutil.*;

import hibernate.db.*;

import java.util.*;

import com.web.bean.QueryRecord;
import com.lover.LoverTools;
import com.lover.mng.HYRegMng;
import com.web.servlet.InitEnvironment;
import java.io.File;
import java.math.BigDecimal;

public class UserVisitMng {
  public UserVisitMng() {
  }
  
  public static void insertUserVisit(Long hyid, String vurl){
  	String s = null;
	try {
		 Vector saveList = new Vector();
	     MutSeaObject mso = null;
	     Uservisit uservisit=new Uservisit();
	     uservisit.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_USERVISIT));
	     uservisit.setHyid(hyid);
	     uservisit.setVtime(new java.util.Date(System.currentTimeMillis()));
	     uservisit.setVurl(vurl);
	     mso = new MutSeaObject();
         mso.setHbmSea(uservisit,MutSeaObject.SEA_HBM_INSERT);
         saveList.add(mso);
         HbmOperator.SeaMutData(saveList);
	} catch (Exception e) {
		 System.out.println(e.getMessage());
         s = "添加用户访问出错，请与管理员联系！";
	}
  }
}