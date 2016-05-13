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

public class UserReceiveSetMng {
  public UserReceiveSetMng() {
  }
  
  /**
   * 修改个人信息接收设置
   * @param request HttpServletRequest
   * @param user Userinfo
   * @param hyset UserReceiveSet
   * @return String
   */
  public static String userupdateUserReceiveSet(HttpServletRequest request,Userinfo user)
  {
       String s =null;
       UserReceiveSet userReceiveSet = getUserReceiveSet(user.getHyid());
       if(userReceiveSet == null)
    	   userReceiveSet = (UserReceiveSet)BeanInitializer.initBean(UserReceiveSet.class,request);
       else
         BeanInitializer.updateBean(userReceiveSet,request);
       
       //added by gaojianhong 20150308 for 判断手机是否验证或邮箱是否正确 start
       //判断网站信息是否接收
       if (userReceiveSet.getReceiveyesno()==0){ 
    	   //接收方式不限
    	   if (userReceiveSet.getReceivetype()==0){
    		   //判断手机是否验证或邮箱是否正确
    		   if ((user.getSjtel().equals("") || user.getVacsjtel()==0) && (user.getEmail().equals("") || !LoverTools.isEmail(user.getEmail()))){
    			   return "11请先进行手机验证或输入正确的邮箱，以便能及时收到网站和会员互动信息";
    		   }
    	   }else if (userReceiveSet.getReceivetype()==1){ //接收方式为手机
			   if (user.getSjtel().equals("") || user.getVacsjtel()==0){
				   return "12请先进行手机验证，以便能及时收到网站和会员互动信息";
			   }
		   }else if (userReceiveSet.getReceivetype()==2){ //接收方式为邮箱
			   if (user.getEmail().equals("") || !LoverTools.isEmail(user.getEmail())){
				   return "13请先输入正确的邮箱地址，以便能及时收到网站和会员互动信息";
			   }
		   }
       }
       //added by gaojianhong 20150308 for 判断手机是否验证或邮箱是否正确 end
       
       Vector saveList = new Vector();

       try
       {
         MutSeaObject mso = new MutSeaObject();
         if (userReceiveSet.getHyid() == null) {
        	 userReceiveSet.setHyid(user.getHyid());
           mso.setHbmSea(userReceiveSet, MutSeaObject.SEA_HBM_INSERT);
         }
         else {
           mso.setHbmSea(userReceiveSet, MutSeaObject.SEA_HBM_UPDATE);
         }
         saveList.add(mso);
         HbmOperator.SeaMutData(saveList);

       }catch(Exception e)
       {
         System.out.println(e.getMessage());
         s = "更新信息接收设置出错，请与管理员联系！";
       }

       return s;
  }
  
  public static UserReceiveSet getUserReceiveSet(Long hyid){
	UserReceiveSet userReceiveSet=null;
	try {
		 List list = HbmOperator.list("from UserReceiveSet as o where o.hyid=" + hyid);
         if (list!=null && list.size()>0){
        	userReceiveSet= (UserReceiveSet) list.get(0);
         }
	} catch (Exception e) {
		 System.out.println(e.getMessage());
	}
	return userReceiveSet;
  }
  
  public static void insertUserReceiveSet(Long hyid, Integer receiveyesno,Integer receivetype){
  	String s = null;
	try {
		 Vector saveList = new Vector();
	     MutSeaObject mso = null;
	     UserReceiveSet userReceiveSet=new UserReceiveSet();
	     userReceiveSet.setHyid(hyid);
	     userReceiveSet.setReceiveyesno(receiveyesno);
	     userReceiveSet.setReceivetype(receivetype);
	     mso = new MutSeaObject();
         mso.setHbmSea(userReceiveSet,MutSeaObject.SEA_HBM_INSERT);
         saveList.add(mso);
         HbmOperator.SeaMutData(saveList);
	} catch (Exception e) {
		 System.out.println(e.getMessage());
         s = "添加个人信息接收设置出错，请与管理员联系！";
	}
  }
  
  public static void updateUserReceiveSet(UserReceiveSet userReceiveSet){
  	String s = null;
	try {
		 Vector saveList = new Vector();
	     MutSeaObject mso = null;
	     mso = new MutSeaObject();
         mso.setHbmSea(userReceiveSet,MutSeaObject.SEA_HBM_UPDATE);
         saveList.add(mso);
         HbmOperator.SeaMutData(saveList);
	} catch (Exception e) {
		 System.out.println(e.getMessage());
         s = "修改个人信息接收设置出错，请与管理员联系！";
	}
  }
}