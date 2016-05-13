package com.lover.mng;

import com.web.common.*;
import com.common.*;
import com.web.obj.*;
import com.web.common.beanutil.*;
import java.util.*;
import com.web.servlet.InitEnvironment;

import java.util.*;
import javax.servlet.http.*;
import java.io.*;
import org.apache.commons.fileupload.*;
import hibernate.db.*;

public class FriendMng {
  public FriendMng() {
  }
  public static String  addFriend(HttpServletRequest request)
  {
     String s = null;
      Friend fr = new Friend();
        DiskFileUpload df = new DiskFileUpload();
      Date cdate = new Date(System.currentTimeMillis());
      String cdates = DateTools.DateToString(cdate,DateTools.FORMART_yyyyMMdd);
      df.setSizeMax(1024*200);

//      if(!DiskFileUpload.isMultipartContent(request)){
//          return "请上传图片！";
//      }
      List list = null;
      try {
          list = df.parseRequest(request);
      } catch (FileUploadException e) {
          e.printStackTrace();
      }

           FileItem fi0 = (FileItem)list.get(0);
          if(list!=null && list.size()==8){
              String netname=fi0.getString();
              fr.setNetname(netname);
          }
      FileItem fi1 = (FileItem)list.get(1);
      if(list!=null && list.size()==8){
          String nethttp=fi1.getString();
          fr.setNethttp(nethttp);
      }
      FileItem fi2 = (FileItem)list.get(2);
          if(!fi2.isFormField()) ///有 文件
          {
              double fileSize =  fi2.getSize()/1024.0;
//              if(fileSize<5){
//                  return "请上传大于5K的图片！";
//              }
              String filename = fi2.getName();
//              if(filename == null || filename.trim().length() ==0){
//                  return "请上传图片！";
//              }
              System.out.println("filename = " + filename);
              if(filename != null && !"".equals(filename)){
              if(!(filename.toLowerCase().endsWith(".jpg") || filename.toLowerCase().endsWith(".gif"))){
                  return "请上传JPG或GIF格式的图片！";
              }

             String afilename = cdates +System.currentTimeMillis()+filename.toLowerCase().substring(filename.length()-4);
              String afilenamepath = "e:\\web\\lover51\\newwebpage\\linkpic" + File.separator + afilename;
              fr.setNetlogo(afilename);
              try {
                  fi2.write(new File(afilenamepath));
              } catch (Exception e) {
                  e.printStackTrace();
              }
              fi2.delete();
              }
          }
      FileItem fi3 = (FileItem)list.get(3);
      if(list!=null && list.size()==8){
          String memo=fi3.getString();
          fr.setMemo(memo);
      }
      FileItem fi4 = (FileItem)list.get(4);
      if(list!=null && list.size()==8){
          String applyreason=fi4.getString();
          fr.setApplyreason(applyreason);
      }
      FileItem fi5 = (FileItem)list.get(5);
      if(list!=null && list.size()==8){
          String man=fi5.getString();
          fr.setMan(man);
      }
      FileItem fi6 = (FileItem)list.get(6);
      if(list!=null && list.size()==8){
          String tel=fi6.getString();
          fr.setTel(tel);
      }
      FileItem fi7 = (FileItem)list.get(7);
      if(list!=null && list.size()==8){
          String addlink=fi7.getString();
          fr.setAddlink(new Integer(addlink));
      }
      fr.setBianhao("11");
      fr.setType("1");
      fr.setState("0");
      fr.setApplydate(new Date());
     try
     {
        fr.setId(new Integer(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_FRIEND).intValue()));
        HbmOperator.insert(fr);
     }catch(Exception e)
     {
        s = "增加友情链接出错，请与开发商联系！";
        System.out.println(e.getMessage());
     }
     return s;
  }


  public static String  modFriend(HttpServletRequest request)
  {
     String s = null;
     Friend fr = (Friend)BeanInitializer.initBean(Friend.class,request);
     try
     {
        HbmOperator.update(fr);
     }catch(Exception e)
     {
        s = "修改友情链接出错，请与开发商联系！";
        System.out.println(e.getMessage());
     }
     return s;
  }

  public static Friend  getFriend(String sql)
  {
     Friend fr = null;
     List list = HbmOperator.list(sql);
     if(list.size() >0)
        return (Friend)list.get(0);
     return fr;
  }



}
