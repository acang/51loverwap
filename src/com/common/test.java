package com.common;

import com.common.*;

import com.sun.image.codec.jpeg.*;
import com.web.bean.QueryRecord;
import com.web.bean.QueryResult;

import javax.imageio.event.*;
import com.web.obj.*;

import java.util.List;
import java.util.regex.*;
public class test {
  public test() {
  }

  public static void p()
  {
    //			创建上传的图片的最小缩略图
//
//			创建上传的图片的中等缩略图
 //                       ImageUtil.getFixedBoundIcon(dirpath + photoPath,height_mid,width_mid, outPathMid);
       try
       {
          //ImageUtil.getFixedBoundIcon("d:/phsrc/2.jpg", 594,594, "d:/phdesc");
       }catch(Exception e)
       {
           System.out.println(e.getMessage());
       }
  }

  public static void main(String args[])
  {
//    String str="ceponline163@yahoo.com.cn";
//    Pattern pattern = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+",Pattern.CASE_INSENSITIVE);
//    Matcher matcher = pattern.matcher(str);
//    System.out.println(matcher.matches());


//    String str = "\\d{6,}";
//    Pattern pattern = Pattern.compile(str,Pattern.CASE_INSENSITIVE);
//    Matcher matcher = pattern.matcher("123578990");
//    System.out.println(matcher.matches());

    String sql = "from Userinfo as u where u.sex  = 11 and u.isdel = 0 and u.img=1 and u.lysize>=30 and u.s2='南京'  and u.csdate >= to_date('1976-12-31','YYYY-MM-DD') and u.csdate<= to_date('1995-01-01','YYYY-MM-DD') and (u.jyyx like '1____1__' or u.jyyx like '1_____1_') order by u.lasttime desc";
    QueryResult qrxttj = null;
    
    System.out.println(sql);
    qrxttj = QueryRecord.queryByHbm(sql,100,1,false,0);
    List xttjList = qrxttj.resultList; 
    
    for(int i =0;i < xttjList.size();i ++)
    {
        Userinfo ui = (Userinfo)xttjList.get(i);
        System.out.println(ui.getUsername()+"----"+ui.getLcname()+"---"+ui.getLasttime());
    }

  }
}
