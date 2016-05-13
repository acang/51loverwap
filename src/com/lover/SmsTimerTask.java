package com.lover;
import hibernate.db.HbmOperator;
import java.util.*;
import java.text.*;
import com.lover.mng.UserInfoSendReplyMng;
import com.web.obj.UserInfoSendReply;
import com.web.obj.Userinfo;
import com.web.common.*;

public class SmsTimerTask extends TimerTask{
  public SmsTimerTask() {
	  
  }

  public void run()
  {
    System.out.println("每天9点定时任务开始");
    //发送第三天和15天后未上传头像信息的
//    sendNoheadimg();
    //发送3个月内未登录的
//    sendNologin(3);
    //发送7个月内未登录的
//    sendNologin(7);
    //发送12个月内未登录的
//    sendNologin(12);
    //发送2、5、10个月内容未登录的用户（以秋波内容为引子）
    //sendNologinQiubo(2);
    //sendNologinQiubo(5);
    //sendNologinQiubo(10);
    //发送9个月内未登录的
    //sendNologin(9);
    //发送12个月内未登录的
    //sendNologin(12);
    //发送15个月内未登录的
    //sendNologin(15);
    //发送18个月内未登录的
    //sendNologin(18);
    //发送21个月内未登录的
    //sendNologin(21);
    //发送24个月内未登录的
    //sendNologin(24);
    System.out.println("每天9点定时任务结束");
  }
  
  //发送第三天和15天后未上传头像信息的
  public void sendNoheadimg(){
	  List templist=new ArrayList();
	  //获取前一天日期
	  Calendar calendar = Calendar.getInstance();
	  calendar.add(Calendar.DATE, -3);    //得到前一天
	  Date date = calendar.getTime();
	  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	  String day=df.format(date);
	  int fsfs=2; // 0:手机  1：邮箱 2：不发送
	  try {
		  //获取前二天注册且未上传照片的会员
		  templist = HbmOperator.list("from Userinfo as o where o.img = 0 and o.isdel = 0 and o.regtime >= to_date('"+day+"','YYYY-MM-DD') and o.regtime<= to_date('"+day+" 23:59:59','YYYY-MM-DD HH24:MI:SS')");
		  for (int i = 0; i < templist.size(); i++) {
			  Userinfo userinfo=new Userinfo();
			  userinfo=(Userinfo)templist.get(i);
			  if (userinfo!=null){
				  fsfs=UserInfoSendReplyMng.getuserReceiveSet(userinfo);
			  }
			  if (fsfs==0){
				  UserInfoSendReply userInfoSendReply=UserInfoSendReplyMng.setuserInfoSendReply(
						  	userinfo.getHyid(), userinfo.getUsername(), 0, "", 0, 0, userinfo.getSjtel(), 1, 0, 0);
				  String contentString="亲爱的@1@，请尽快上传您的照片，提交您的交友效果和效率，找到满意伴侣！www.51lover.org 51交友中心网站";
	          	  StringBuilder content=new StringBuilder();
		          content.append("@1@="+userinfo.getLcname());
		          contentString=contentString.replace("@1@", userinfo.getLcname());
		          userInfoSendReply.setContent(contentString);
		          Long id=UserInfoSendReplyMng.insertUserInfoSendReply(userInfoSendReply);
		          String result=SMSTools.sendMobileMb(userinfo.getSjtel(),content.toString(),"MB-2015020813");
				  UserInfoSendReply newuserInfoSendReply=new UserInfoSendReply();
				  newuserInfoSendReply=UserInfoSendReplyMng.getUserInfoSendReply(id);
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
						  	userinfo.getHyid(), userinfo.getUsername(), 0, "", 1, 0, userinfo.getEmail(), 0, 0, 0);
				  String contentString="亲爱的@1@，请尽快上传您的照片，提交您的交友效果和效率，找到满意伴侣！www.51lover.org 51交友中心网站";
		          contentString=contentString.replace("@1@", userinfo.getLcname());
		          userInfoSendReply.setContent(contentString);
		          Long id=UserInfoSendReplyMng.insertUserInfoSendReply(userInfoSendReply);
		          //发送邮件内容
 				  MailTools.reMail2("51交友中心网站提示信息",userInfoSendReply.getContent(),userinfo.getEmail());
			  }
		  }
	  } catch (Exception e) {
			System.out.println("发送上传照片提醒出错" + e.getMessage());
	  }
	  
	  //获取前15天日期
	  calendar.add(Calendar.DATE, -12);    //得到前一天
	  date = calendar.getTime();
	  day=df.format(date);
	  try {
		  //获取前15天注册且未上传照片的会员
		  templist = HbmOperator.list("from Userinfo as o where o.img = 0 and o.isdel = 0 and o.regtime >= to_date('"+day+"','YYYY-MM-DD') and o.regtime<= to_date('"+day+" 23:59:59','YYYY-MM-DD HH24:MI:SS')");
		  for (int i = 0; i < templist.size(); i++) {
			  Userinfo userinfo=new Userinfo();
			  userinfo=(Userinfo)templist.get(i);
			  if (userinfo!=null){
				  fsfs=UserInfoSendReplyMng.getuserReceiveSet(userinfo);
			  }
			  if (fsfs==0){
				  UserInfoSendReply userInfoSendReply=UserInfoSendReplyMng.setuserInfoSendReply(
						  	userinfo.getHyid(), userinfo.getUsername(), 0, "", 0, 0, userinfo.getSjtel(), 1, 0, 0);
				  String contentString="亲爱的@1@，请尽快上传您的照片，提交您的交友效果和效率，找到满意伴侣！www.51lover.org 51交友中心网站";
	          	  StringBuilder content=new StringBuilder();
		          content.append("@1@="+userinfo.getLcname());
		          contentString=contentString.replace("@1@", userinfo.getLcname());
		          userInfoSendReply.setContent(contentString);
		          Long id=UserInfoSendReplyMng.insertUserInfoSendReply(userInfoSendReply);
		          String result=SMSTools.sendMobileMb(userinfo.getSjtel(),content.toString(),"MB-2015020813");
				  UserInfoSendReply newuserInfoSendReply=new UserInfoSendReply();
				  newuserInfoSendReply=UserInfoSendReplyMng.getUserInfoSendReply(id);
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
						  	userinfo.getHyid(), userinfo.getUsername(), 0, "", 1, 0, userinfo.getEmail(), 0, 0, 0);
				  String contentString="亲爱的@1@，请尽快上传您的照片，提交您的交友效果和效率，找到满意伴侣！www.51lover.org 51交友中心网站";
		          contentString=contentString.replace("@1@", userinfo.getLcname());
		          userInfoSendReply.setContent(contentString);
		          Long id=UserInfoSendReplyMng.insertUserInfoSendReply(userInfoSendReply);
		          //发送邮件内容
				  MailTools.reMail2("51交友中心网站提示信息",userInfoSendReply.getContent(),userinfo.getEmail());
			  }
		  }
	  } catch (Exception e) {
			System.out.println("发送上传照片提醒出错" + e.getMessage());
	  }
  }
  
  //发送未登录信息的
  public void sendNologin(int number){
	  List templist=new ArrayList();
	  Calendar calendar = Calendar.getInstance();
	  calendar.add(Calendar.MONTH, -number); //得到前number月
	  calendar.add(Calendar.DATE, -1); 
	  Date date = calendar.getTime();
	  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	  String day=df.format(date);
	  int fsfs=2; // 0:手机  1：邮箱 2：不发送
	  try {
		  templist = HbmOperator.list("from Userinfo as o where o.img = 0 and o.isdel = 0 and o.lasttime >= to_date('"+day+"','YYYY-MM-DD') and o.lasttime<= to_date('"+day+" 23:59:59','YYYY-MM-DD HH24:MI:SS')");
		  for (int i = 0; i < templist.size(); i++) {
			  Userinfo userinfo=new Userinfo();
			  userinfo=(Userinfo)templist.get(i);
			  if (userinfo!=null){
				  fsfs=UserInfoSendReplyMng.getuserReceiveSet(userinfo);
			  }
			  if (fsfs==0){
				  UserInfoSendReply userInfoSendReply=UserInfoSendReplyMng.setuserInfoSendReply(
						  	userinfo.getHyid(), userinfo.getUsername(), 0, "", 0, 0, userinfo.getSjtel(), 1, 2, 0);
				  String contentString="亲爱的@1@，51交友中心网站最近又注册了很多符合您要求的帅哥佳人，快回来看看吧！www.51lover.org";
	          	  StringBuilder content=new StringBuilder();
		          content.append("@1@="+userinfo.getLcname());
		          contentString=contentString.replace("@1@", userinfo.getLcname());
		          //content.append(",@2@="+number);
		          //contentString=contentString.replace("@2@", String.valueOf(number));
		          userInfoSendReply.setContent(contentString);
		          Long id=UserInfoSendReplyMng.insertUserInfoSendReply(userInfoSendReply);
		          String result=SMSTools.sendMobileMb(userinfo.getSjtel(),content.toString(),"MB-2015020846");
				  UserInfoSendReply newuserInfoSendReply=new UserInfoSendReply();
				  newuserInfoSendReply=UserInfoSendReplyMng.getUserInfoSendReply(id);
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
						  	userinfo.getHyid(), userinfo.getUsername(), 0, "", 1, 0, userinfo.getEmail(), 0, 2, 0);
				  String contentString="亲爱的@1@，51交友中心网站最近又注册了很多符合您要求的帅哥佳人，快回来看看吧！www.51lover.org";
		          contentString=contentString.replace("@1@", userinfo.getLcname());
		          //contentString=contentString.replace("@2@", String.valueOf(number));
		          userInfoSendReply.setContent(contentString);
		          Long id=UserInfoSendReplyMng.insertUserInfoSendReply(userInfoSendReply);
		          //发送邮件内容
 				  MailTools.reMail2("51交友中心网站提示信息",userInfoSendReply.getContent(),userinfo.getEmail());
			  }
		  }
	  } catch (Exception e) {
			System.out.println("发送未登录提醒出错" + e.getMessage());
	  }
  }
  
  //发送未登录信息的(内容是秋波)
  public void sendNologinQiubo(int number){
	  List templist=new ArrayList();
	  Calendar calendar = Calendar.getInstance();
	  calendar.add(Calendar.MONTH, -number); //得到前number月
	  calendar.add(Calendar.DATE, -1); 
	  Date date = calendar.getTime();
	  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	  String day=df.format(date);
	  int fsfs=2; // 0:手机  1：邮箱 2：不发送
	  String sex="";
	  try {
		  templist = HbmOperator.list("from Userinfo as o where o.img = 0 and o.isdel = 0 and o.lasttime >= to_date('"+day+"','YYYY-MM-DD') and o.lasttime<= to_date('"+day+" 23:59:59','YYYY-MM-DD HH24:MI:SS')");
		  for (int i = 0; i < templist.size(); i++) {
			  Userinfo userinfo=new Userinfo();
			  userinfo=(Userinfo)templist.get(i);
			  sex="他";
			  if (userinfo.getSex().equals("10"))
				  sex="她";
			  if (userinfo!=null){
				  fsfs=UserInfoSendReplyMng.getuserReceiveSet(userinfo);
			  }
			  if (fsfs==0){
				  UserInfoSendReply userInfoSendReply=UserInfoSendReplyMng.setuserInfoSendReply(
						  	userinfo.getHyid(), userinfo.getUsername(), 0, "", 0, 0, userinfo.getSjtel(), 1, 2, 0);
				  String contentString="亲爱的@1@,有很多会员给您发送了新秋波，看看@2@们是否合您的心意！www.51lover.org 51交友中心网站";
	          	  StringBuilder content=new StringBuilder();
		          content.append("@1@="+userinfo.getLcname());
		          contentString=contentString.replace("@1@", userinfo.getLcname());
		          content.append(",@2@="+sex);
		          contentString=contentString.replace("@2@", sex);
		          userInfoSendReply.setContent(contentString);
		          Long id=UserInfoSendReplyMng.insertUserInfoSendReply(userInfoSendReply);
		          String result=SMSTools.sendMobileMb(userinfo.getSjtel(),content.toString(),"MB-2015030835");
				  UserInfoSendReply newuserInfoSendReply=new UserInfoSendReply();
				  newuserInfoSendReply=UserInfoSendReplyMng.getUserInfoSendReply(id);
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
						  	userinfo.getHyid(), userinfo.getUsername(), 0, "", 1, 0, userinfo.getEmail(), 0, 2, 0);
				  String contentString="亲爱的@1@,有很多会员给您发送了新秋波，看看@2@们是否合您的心意！www.51lover.org 51交友中心网站";
		          contentString=contentString.replace("@1@", userinfo.getLcname());
		          contentString=contentString.replace("@2@", sex);
		          userInfoSendReply.setContent(contentString);
		          Long id=UserInfoSendReplyMng.insertUserInfoSendReply(userInfoSendReply);
		          //发送邮件内容
 				  MailTools.reMail2("51交友中心网站提示信息",userInfoSendReply.getContent(),userinfo.getEmail());
			  }
		  }
	  } catch (Exception e) {
			System.out.println("发送未登录提醒出错" + e.getMessage());
	  }
  }
}
