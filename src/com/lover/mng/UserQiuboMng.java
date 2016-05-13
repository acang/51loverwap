package com.lover.mng;
import com.web.common.*;
import com.web.obj.*;
import hibernate.db.*;
import java.util.*;
import com.lover.SMSTools;

public class UserQiuboMng {
  public UserQiuboMng() {
  }
  
  /**
   * 
   * @param hyid
   * @return
   */
  public  Userqiubo getUserqiubo(String hyid){
	  Userqiubo userqiubo=null;
	try {
		 List list = HbmOperator.list("from Userqiubo as o where o.hyid=" + hyid);
         if (list!=null && list.size()>0){
        	 userqiubo= (Userqiubo) list.get(0);
         }
	} catch (Exception e) {
		 System.out.println(e.getMessage());
	}
	return userqiubo;
  }
  
  public void insertUserqiubo(Long hyid, Integer qiubo_number){
  	String s = null;
	try {
		 Vector saveList = new Vector();
	     MutSeaObject mso = null;
	     Userqiubo userqiubo=new Userqiubo();
	     userqiubo.setHyid(hyid);
	     userqiubo.setQiubo_number(qiubo_number);
	     mso = new MutSeaObject();
         mso.setHbmSea(userqiubo,MutSeaObject.SEA_HBM_INSERT);
         saveList.add(mso);
         HbmOperator.SeaMutData(saveList);
	} catch (Exception e) {
		 System.out.println(e.getMessage());
         s = "添加个人秋波数量出错，请与管理员联系！";
	}
  }
  
  public void updateUserqiubo(Userqiubo userqiubo){
  	String s = null;
	try {
		 Vector saveList = new Vector();
	     MutSeaObject mso = null;
	     mso = new MutSeaObject();
         mso.setHbmSea(userqiubo,MutSeaObject.SEA_HBM_UPDATE);
         saveList.add(mso);
         HbmOperator.SeaMutData(saveList);
	} catch (Exception e) {
		 System.out.println(e.getMessage());
         s = "修改个人秋波数量出错，请与管理员联系！";
	}
  }
  
  /**
   * 根据用户ID获取累计秋波数量（包含秋波匹配）
   * @param hyid  用户ID
   * @return   累计秋波数量（包含秋波匹配）
   */
  public int getUserqiubo_number(Long hyid){
	  int qiubo_number=0;
	  //秋波表
	  qiubo_number=qiubo_number+SysCommonFunc.getNumberForSql("select count(1) from db_qiubo where postuserid="+hyid).intValue();
	  //取秋波匹配表（取该会员登录时间之后的秋波匹配表中数据）,且该会员为女会员
	  Userinfo userinfo=HYRegMng.getUserinfoByHyid(hyid.toString());
	  if (userinfo!=null && userinfo.getSex()!=null && userinfo.getSex().equals("10")){
		  Date lastTime=userinfo.getLasttime();
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(userinfo.getCsdate());
		  int start = cal.get(Calendar.YEAR)-15;
		  int end = cal.get(Calendar.YEAR)+5;
		  String sDate=start+"-01-01";
		  String eDate=end+"-12-31";
		  String sql = "select count(1) from Db_QiuboFs q join USERINFO u on q.SENDUSERID=u.HYID where q.sendtime >= to_date('"+DateTools.dateToString(lastTime,true)+"','YYYY-MM-DD HH24:MI:SS') ";
	      sql = sql + " and u.CSDATE>=to_date('"+sDate+"','YYYY-MM-DD') and u.CSDATE<=to_date('"+eDate+"','YYYY-MM-DD') and u.s1='"+userinfo.getS1()+"' and u.sex='11'";
	      qiubo_number=qiubo_number+SysCommonFunc.getNumberForSql(sql).intValue();
	  }
	  return qiubo_number;
  }
  
  /**
   * 
   * @param hyid   用户ID
   * @param username   触发者用户名
   * @param nickname  触发者昵称
   * @param sex  性别
   * @param qiubo_number  累计秋波数量
   */
  public void sendqiubowarn(Long hyid,String username,String nickname,String sex,int qiubo_number){
	  int fsfs=2; // 0:手机  1：邮箱 2：不发送
	  Userinfo userinfo=HYRegMng.getUserinfoByHyid(hyid.toString());
	  int i=0;
	  try {
		  if (userinfo!=null){
			  boolean blfs=false;
			  String contentString="亲爱的@1@,@2@等会员给您发送了@3@条秋波，看看@4@们是否合您的心意！www.51lover.org 51交友中心网站";
			  if (qiubo_number>10){
				  contentString="亲爱的@1@,又有@2@等众多会员给您发送了秋波，看看@3@们是否合您的心意！www.51lover.org 51交友中心网站";
			  }
			  StringBuilder content=new StringBuilder();
	          content.append("@1@="+userinfo.getLcname());
	          contentString=contentString.replace("@1@", userinfo.getLcname());
	          content.append(",@2@="+nickname);
	          contentString=contentString.replace("@2@", nickname);
			  if (qiubo_number==10){
				  content.append(",@3@=10");
		          contentString=contentString.replace("@3@", "10");
		          i=1;
		          blfs=true;
			  }/*else if (qiubo_number==25){
				  //content.append(",@3@=15");
		          //contentString=contentString.replace("@3@", "15");
		          i=2;
		          blfs=true;
			  }else if (qiubo_number>=75){
				  //若秋波累计数除以50能整除
				  if (((qiubo_number-25)%50)==0){
					  //content.append(",@3@=50");
			          //contentString=contentString.replace("@3@", "50");
			          i=(qiubo_number-25)/50+2;
			          blfs=true;
				  }
			  }*/
			  //content.append(",@4@="+sex);
	          //contentString=contentString.replace("@4@", sex);
			  if (qiubo_number>10){
				  content.append(",@3@="+sex);
		          contentString=contentString.replace("@3@", sex);
			  }else{
				  content.append(",@4@="+sex);
		          contentString=contentString.replace("@4@", sex);
			  }
			  
			  //判断是手机发送还是邮箱发送
			  if (blfs){
				  fsfs=UserInfoSendReplyMng.getuserReceiveSet(userinfo);
				  if (fsfs==0){
					  UserInfoSendReply userInfoSendReply=UserInfoSendReplyMng.setuserInfoSendReply(
							  	userinfo.getHyid(), userinfo.getUsername(), 0, username, 0, 0, userinfo.getSjtel(), 1, 1, i);
					  userInfoSendReply.setContent(contentString);
			        //  Long id=UserInfoSendReplyMng.insertUserInfoSendReply(userInfoSendReply);
			          String result="";
			          if (qiubo_number>10){
			        	//  result=SMSTools.sendMobileMb(userinfo.getSjtel(),content.toString(),"MB-2015021138");
			          }else{
			        	//  result=SMSTools.sendMobileMb(userinfo.getSjtel(),content.toString(),"MB-2015020822");
			          }
					  UserInfoSendReply newuserInfoSendReply=new UserInfoSendReply();
					//  newuserInfoSendReply=UserInfoSendReplyMng.getUserInfoSendReply(id);
					  if (newuserInfoSendReply!=null){
						  if (result.indexOf("#")<=0){
					    	 newuserInfoSendReply.setStatus(Integer.parseInt(result));
								
						  }else{
						     newuserInfoSendReply.setStatus(0);
						  }
						  HbmOperator.update(newuserInfoSendReply);
					  }
				  }else if (fsfs==1) {
					  UserInfoSendReply userInfoSendReply=UserInfoSendReplyMng.setuserInfoSendReply(
							  	userinfo.getHyid(), userinfo.getUsername(), 0, username, 1, 0, userinfo.getEmail(), 0, 1, i);
					  userInfoSendReply.setContent(contentString);
			     //     Long id=UserInfoSendReplyMng.insertUserInfoSendReply(userInfoSendReply);
			          //发送邮件内容
	 			//	  MailTools.reMail2("51交友中心网站提示信息",userInfoSendReply.getContent(),userinfo.getEmail());
				  }
			  }
		  }
		
	   } catch (Exception e) {
			// TODO: handle exception
		   System.out.println("发送秋波提醒出错" + e.getMessage());
	   }
  }
}