package com.lover.mng;
import com.web.common.*;
import com.common.*;

import javax.servlet.http.HttpServletRequest;

import com.web.obj.*;
import com.web.common.beanutil.*;

import hibernate.db.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

import com.web.bean.QueryRecord;
import com.lover.mng.HYRegMng;
import com.web.servlet.InitEnvironment;

import java.io.File;

import chinapay.*;

public class SharearticleMng {

  public SharearticleMng() {
  }

  public static void addSharearticle(Sharearticle sharearticle)
  {
	  try
	    {
		  sharearticle.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_SHAREID));
	      HbmOperator.insert(sharearticle);
	    }catch(Exception e)
	    {
	       System.out.println(e.getMessage());
	    }
  }

  public static void main(String args[])
  {

  }
}
