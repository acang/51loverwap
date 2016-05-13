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
import java.math.BigDecimal;
import com.lover.*;

public class UserInfoSendReplyMng {
  public UserInfoSendReplyMng() {
  }
  
  public static Long insertUserInfoSendReply(UserInfoSendReply userInfoSendReply){
  	String s = null;
  	Long id;
  	id=Long.parseLong("0");
	try {
		 Vector saveList = new Vector();
	     MutSeaObject mso = new MutSeaObject();
	     id=SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_USERINFOSENDREPLY);
	     userInfoSendReply.setId(id);
         mso.setHbmSea(userInfoSendReply,MutSeaObject.SEA_HBM_INSERT);
         saveList.add(mso);
         HbmOperator.SeaMutData(saveList);
	} catch (Exception e) {
		 System.out.println(e.getMessage());
         s = "添加信息发送记录出错，请与管理员联系！";
	}
	return id;
  }
  
  public static UserInfoSendReply getUserInfoSendReply(String mobileoremailc,Integer mobileoremail){
	  UserInfoSendReply userInfoSendReply=null;
		try {
			 List list = HbmOperator.list("from UserInfoSendReply as o where o.mobileoremail=" + mobileoremail +" and o.mobileoremailc='"+mobileoremailc+"'");
	         if (list!=null && list.size()>0){
	        	 userInfoSendReply= (UserInfoSendReply) list.get(0);
	         }
		} catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		return userInfoSendReply;
	  }
  
  public static List getUserInfoSendReplyList(String mobileoremailc,Integer mobileoremail){
	  List list=null;
		try {
			 list = QueryRecord.query("select distinct hyid from USERINFO_SEND_REPLY o where o.mobileoremail=" + mobileoremail +" and o.mobileoremailc='"+mobileoremailc+"'");
		} catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		return list;
  }
  
  public static UserInfoSendReply getUserInfoSendReply(Long id){
	  UserInfoSendReply userInfoSendReply=null;
		try {
			 List list = HbmOperator.list("from UserInfoSendReply as o where o.id=" + id);
	         if (list!=null && list.size()>0){
	        	 userInfoSendReply= (UserInfoSendReply) list.get(0);
	         }
		} catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		return userInfoSendReply;
	  }
  
  //判断会员信息发送方式(0:手机  1：邮箱 2：不发送)
  public static int getuserReceiveSet(String hyid){
	  int fsfs=2; // 0:手机  1：邮箱 2：不发送
	  List templist=new ArrayList();
	  templist = HbmOperator.list("from Userinfo as o where o.hyid="+hyid+" and o.isdel = 0");
	  if(templist != null && templist.size() == 1){
		  Userinfo userinfo=new Userinfo();
		  userinfo=(Userinfo)templist.get(0);
		  UserReceiveSet userReceiveSet=new UserReceiveSet();
		  templist.clear();
		  templist = HbmOperator.list("from UserReceiveSet as o where o.hyid="+hyid);
			//判断会员个人信息设置
	   		if(templist != null && templist.size() ==1){
	   			userReceiveSet=(UserReceiveSet)templist.get(0);
	   			if (userReceiveSet.getReceiveyesno()==0){
	   				if (userReceiveSet.getReceivetype()==0){
	   					//判断手机号码
	   					if (userinfo.getSjtel()!=null && !userinfo.getSjtel().equals("") && LoverTools.isMobileNO(userinfo.getSjtel())){
	   						fsfs=0;
	   					}else if (userinfo.getEmail()!=null && !userinfo.getEmail().equals("") && LoverTools.isEmail(userinfo.getEmail())){  //判断邮箱
	   						fsfs=1;
	   					}
	   				}else if (userReceiveSet.getReceivetype()==1){
	   					if (userinfo.getSjtel()!=null && !userinfo.getSjtel().equals("") && LoverTools.isMobileNO(userinfo.getSjtel())){
	   						fsfs=0;
	   					}
	   				}else if (userReceiveSet.getReceivetype()==2){
	   					if (userinfo.getEmail()!=null && !userinfo.getEmail().equals("") && LoverTools.isEmail(userinfo.getEmail())){  //判断邮箱
	   						fsfs=1;
	   					}
	   				}
	   			}
	   		}else{
	   			//判断手机号码
					if (userinfo.getSjtel()!=null && !userinfo.getSjtel().equals("") && LoverTools.isMobileNO(userinfo.getSjtel())){
						fsfs=0;
					}else if (userinfo.getEmail()!=null && !userinfo.getEmail().equals("") && LoverTools.isEmail(userinfo.getEmail())){  //判断邮箱
						fsfs=1;
					}else{
						fsfs=2;
					}
	   		}
	  }
	  return fsfs;
  }
  
  //判断会员信息发送方式(0:手机  1：邮箱 2：不发送)
  public static int getuserReceiveSet(Userinfo userinfo){
	  int fsfs=2; // 0:手机  1：邮箱 2：不发送
	  List templist=new ArrayList();
	  UserReceiveSet userReceiveSet=new UserReceiveSet();
	  templist.clear();
	  templist = HbmOperator.list("from UserReceiveSet as o where o.hyid="+userinfo.getHyid());
		//判断会员个人信息设置
   		if(templist != null && templist.size() ==1){
   			userReceiveSet=(UserReceiveSet)templist.get(0);
   			if (userReceiveSet.getReceiveyesno()==0){
   				if (userReceiveSet.getReceivetype()==0){
   					//判断手机号码
   					if (userinfo.getSjtel()!=null && !userinfo.getSjtel().equals("") && LoverTools.isMobileNO(userinfo.getSjtel())){
   						fsfs=0;
   					}else if (userinfo.getEmail()!=null && !userinfo.getEmail().equals("") && LoverTools.isEmail(userinfo.getEmail())){  //判断邮箱
   						fsfs=1;
   					}
   				}else if (userReceiveSet.getReceivetype()==1){
   					if (userinfo.getSjtel()!=null && !userinfo.getSjtel().equals("") && LoverTools.isMobileNO(userinfo.getSjtel())){
   						fsfs=0;
   					}
   				}else if (userReceiveSet.getReceivetype()==2){
   					if (userinfo.getEmail()!=null && !userinfo.getEmail().equals("") && LoverTools.isEmail(userinfo.getEmail())){  //判断邮箱
   						fsfs=1;
   					}
   				}
   			}
   		}else{
   			//判断手机号码
				if (userinfo.getSjtel()!=null && !userinfo.getSjtel().equals("") && LoverTools.isMobileNO(userinfo.getSjtel())){
					fsfs=0;
				}else if (userinfo.getEmail()!=null && !userinfo.getEmail().equals("") && LoverTools.isEmail(userinfo.getEmail())){  //判断邮箱
					fsfs=1;
				}else{
					fsfs=2;
				}
   		}
	  return fsfs;
  }
  
  /**
   * 
   * @param hyid  发送给会员ID
   * @param username  发送给会员名
   * @param sendorreply  发送或接收  0：发送  1：接收
   * @param sender   发送者会员名/管理员账户名
   * @param sendtype  发送方式   0：自动  1：手动
   * @param mobileoremail  发送手机或邮箱  0：手机  1：邮箱
   * @param mobileoremailc  手机或邮箱  0：手机  1：邮箱
   * @param status  发送状态  0：成功   1：未审核  2:失败
   * @param sendobject   程序发送者  0：其他   1：秋波   2：登录
   * @param sendnumber   发送次数
   * @return
   */
  public static UserInfoSendReply setuserInfoSendReply(Long hyid,String username,int sendorreply,String sender,int sendtype,int mobileoremail,String mobileoremailc,int status,int sendobject,int sendnumber){
	  UserInfoSendReply userInfoSendReply=new UserInfoSendReply();
	  userInfoSendReply.setHyid(hyid);
	  userInfoSendReply.setUsername(username);
	  userInfoSendReply.setHdate(new Date(System.currentTimeMillis()));
	  userInfoSendReply.setSendorreply(sendorreply);
	  userInfoSendReply.setSender(sender);
	  userInfoSendReply.setSendtype(sendtype);
	  userInfoSendReply.setMobileoremail(mobileoremail);
	  userInfoSendReply.setMobileoremailc(mobileoremailc);
	  userInfoSendReply.setStatus(status);
	  userInfoSendReply.setSendobject(sendobject);
	  userInfoSendReply.setSendnumber(sendnumber);
	  return userInfoSendReply;
  }
	
}