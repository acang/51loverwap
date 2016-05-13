package com.lover.mng;


import com.web.common.*;
import com.common.*;
import javax.servlet.http.HttpServletRequest;

import com.web.obj.*;
import com.web.common.beanutil.*;
import hibernate.db.*;
import java.util.*;
import java.io.ByteArrayInputStream;

public class WTJDMng {
  public WTJDMng() {
  }

  public static Wtjd getWtjdFromid(String id)
  {
    List list = HbmOperator.list("from Wtjd as o where o.id = "+id);
    Wtjd wtjd = null;
    if(list.size() > 0)
      wtjd = (Wtjd)list.get(0);
    return wtjd;
  }

  public static Wtjd getGgNews()
  {
    List list = HbmOperator.list("from Wtjd as o where o.wttype=1  order by o.bianhao asc");
    Wtjd wtjd = null;
    if(list.size() > 0)
     wtjd = (Wtjd)list.get(0);
    return wtjd;
  }
    public static String addGgNewslt(HttpServletRequest request)
    {
        String s = null;
        Wtjd wtjd = getGgNews();
        Bcb bcb = null;
        if(wtjd != null && wtjd.getAnswer() != null)
            bcb = BCBMng.getBcbFromid(wtjd.getAnswer().toString());
        String title     = "";
        String linkurl= SysCommonFunc.getStrParameter(request,"linkurl");
        //获取论坛文章标题
        int start=0;
        int end=0;
        if (linkurl.indexOf("bbsid")>=0){
            start=linkurl.indexOf("bbsid");
        }
        //if (linkurl.indexOf(".htm")>=0){
        //end=linkurl.indexOf(".htm");
        //}
        String bbsid="0";
        //bbsid=linkurl.substring(start+6, end);
        bbsid=linkurl.substring(start+6);
        Bb topicBb = BBSMng.getBBSById(bbsid);
        if(topicBb != null){
            title=topicBb.getTitle();
        }
        String islinks= SysCommonFunc.getStrParameter(request,"islink");
        int islink=1;
        //if (islinks.equals("on")){
        //islink=1;
        //}
        Vector saveList = new Vector();
        try
        {
            if (wtjd != null) {
                wtjd.setAsk(title);
                wtjd.setStime(new java.util.Date(System.currentTimeMillis()));
                wtjd.setIslink(islink);
                wtjd.setLinkurl(linkurl);
                MutSeaObject mso = new MutSeaObject();
                mso.setHbmSea(wtjd, MutSeaObject.SEA_HBM_UPDATE);
                saveList.add(mso);
            }
            else {
                wtjd = new Wtjd();
                wtjd.setAsk(title);
                wtjd.setBianhao("0");
                wtjd.setStime(new java.util.Date(System.currentTimeMillis()));
                wtjd.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_WTJD));
                wtjd.setWttype(new Integer(1));
                wtjd.setIslink(islink);
                wtjd.setLinkurl(linkurl);
                MutSeaObject mso = new MutSeaObject();
                mso.setHbmSea(wtjd, MutSeaObject.SEA_HBM_INSERT);
                saveList.add(mso);
            }

            if (bcb == null) {
                Long id = SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB);
                bcb = new Bcb();
                bcb.setId(id);
                wtjd.setAnswer(id);
                MutSeaObject mso = new MutSeaObject();
                mso.setHbmSea(bcb, MutSeaObject.SEA_HBM_INSERT);
                saveList.add(mso);
            }
            else {
                MutSeaObject mso = new MutSeaObject();
                mso.setHbmSea(bcb, MutSeaObject.SEA_HBM_UPDATE);
                saveList.add(mso);
            }
            HbmOperator.SeaMutDataWithBlob(saveList);
        }catch(Exception e)
        {
            s = "增加公告出错，请与开发商联系！";
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
        return s;
    }
  public static String addGgNews(HttpServletRequest request)
  {
      String s = null;
      Wtjd wtjd = getGgNews();
      Bcb bcb = null;
      if(wtjd != null && wtjd.getAnswer() != null)
          bcb = BCBMng.getBcbFromid(wtjd.getAnswer().toString());
      String content   = SysCommonFunc.getStrParameter(request,"content");
      String title     = SysCommonFunc.getStrParameter(request,"title");
      Vector saveList = new Vector();
      try
      {
        if (wtjd != null) {
          wtjd.setAsk(title);
          wtjd.setStime(new Date(System.currentTimeMillis()));
          MutSeaObject mso = new MutSeaObject();
          mso.setHbmSea(wtjd, MutSeaObject.SEA_HBM_UPDATE);
          saveList.add(mso);
        }
        else {
          wtjd = new Wtjd();
          wtjd.setAsk(title);
          wtjd.setBianhao("0");
          wtjd.setStime(new Date(System.currentTimeMillis()));
          wtjd.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_WTJD));
          wtjd.setWttype(new Integer(1));
          MutSeaObject mso = new MutSeaObject();
          mso.setHbmSea(wtjd, MutSeaObject.SEA_HBM_INSERT);
          saveList.add(mso);
        }

        if (bcb == null) {
          Long id = SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB);
          bcb = new Bcb();
          bcb.setId(id);
          wtjd.setAnswer(id);
          MutSeaObject mso = new MutSeaObject();
          mso.setHbmSea(bcb, MutSeaObject.SEA_HBM_INSERT);
          mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),
                            "content");
          saveList.add(mso);
        }
        else {
          MutSeaObject mso = new MutSeaObject();
          mso.setHbmSea(bcb, MutSeaObject.SEA_HBM_UPDATE);
          mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),
                            "content");
          saveList.add(mso);
        }
        HbmOperator.SeaMutDataWithBlob(saveList);
      }catch(Exception e)
      {
         s = "增加公告出错，请与开发商联系！";
         System.out.println(e.getMessage());
         //e.printStackTrace();
      }
      return s;
  }
  
  public static String preGgNews(HttpServletRequest request)
  {
      String s = null;
      Wtjd wtjd = getGgNews();
      Bcb bcb = null;
      if(wtjd != null && wtjd.getAnswer() != null)
          bcb = BCBMng.getBcbFromid(wtjd.getAnswer().toString());
      String content   = SysCommonFunc.getStrParameter(request,"content");
      String title     = SysCommonFunc.getStrParameter(request,"title");
      Vector saveList = new Vector();
      try
      {
        if (wtjd != null) {
          wtjd.setAsk(title);
          wtjd.setStime(null);
          MutSeaObject mso = new MutSeaObject();
          mso.setHbmSea(wtjd, MutSeaObject.SEA_HBM_UPDATE);
          saveList.add(mso);
        }
        else {
          wtjd = new Wtjd();
          wtjd.setAsk(title);
          wtjd.setBianhao("0");
          wtjd.setStime(null);
          wtjd.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_WTJD));
          wtjd.setWttype(new Integer(1));
          MutSeaObject mso = new MutSeaObject();
          mso.setHbmSea(wtjd, MutSeaObject.SEA_HBM_INSERT);
          saveList.add(mso);
        }

        if (bcb == null) {
          Long id = SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB);
          bcb = new Bcb();
          bcb.setId(id);
          wtjd.setAnswer(id);
          MutSeaObject mso = new MutSeaObject();
          mso.setHbmSea(bcb, MutSeaObject.SEA_HBM_INSERT);
          mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),
                            "content");
          saveList.add(mso);
        }
        else {
          MutSeaObject mso = new MutSeaObject();
          mso.setHbmSea(bcb, MutSeaObject.SEA_HBM_UPDATE);
          mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),
                            "content");
          saveList.add(mso);
        }
        HbmOperator.SeaMutDataWithBlob(saveList);
      }catch(Exception e)
      {
         s = "增加公告出错，请与开发商联系！";
         System.out.println(e.getMessage());
         //e.printStackTrace();
      }
      return s;
  }

  public static String addOrModWtjd(HttpServletRequest request)
  {
     String s = null;
     String id = SysCommonFunc.getStrParameter(request,"id");

     if(SysCommonFunc.getStrParameter(request, "title").getBytes().length > 201)
       return "标题请少于100个汉字！";

     Wtjd wtjd = WTJDMng.getWtjdFromid(id);
     Bcb bcb = null;
     if(wtjd != null && wtjd.getAnswer() != null)
        bcb = BCBMng.getBcbFromid(wtjd.getAnswer().toString());
     if(wtjd == null)
       id = "";

     Vector saveList = new Vector();
     try
     {
       if (id.length() == 0) { //新增加
         Long wLong = SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_WTJD);
         Long bcbid = SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB);
         wtjd = new Wtjd();
         wtjd.setAnswer(bcbid);
         wtjd.setAsk(SysCommonFunc.getStrParameter(request, "title"));
         wtjd.setBianhao(SysCommonFunc.getStrParameter(request, "bianhao"));
         wtjd.setId(wLong);
         wtjd.setWttype(new Integer(SysCommonFunc.getStrParameter(request, "wttype")));

         wtjd.setStime(DateTools.stringToDate(SysCommonFunc.getStrParameter(request,"stime")));
         if(wtjd.getStime() == null)
             wtjd.setStime(new Date(System.currentTimeMillis()));


         bcb = new Bcb();
         bcb.setId(bcbid);

         MutSeaObject mso = new MutSeaObject();
         mso.setHbmSea(wtjd, MutSeaObject.SEA_HBM_INSERT);
         saveList.add(mso);
         mso = new MutSeaObject();
         mso.setHbmSea(bcb,MutSeaObject.SEA_HBM_INSERT);
         String content = SysCommonFunc.getStrParameter(request, "content");
         mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),"content");
         saveList.add(mso);
         HbmOperator.SeaMutDataWithBlob(saveList);
       }else
       {
           wtjd.setAsk(SysCommonFunc.getStrParameter(request, "title"));
           wtjd.setBianhao(SysCommonFunc.getStrParameter(request, "bianhao"));
           wtjd.setWttype(new Integer(SysCommonFunc.getStrParameter(request, "wttype")));
           wtjd.setStime(DateTools.stringToDate(SysCommonFunc.getStrParameter(request,"stime")));
           if(wtjd.getStime() == null)
               wtjd.setStime(new Date(System.currentTimeMillis()));

           MutSeaObject mso = new MutSeaObject();
           mso.setHbmSea(wtjd,MutSeaObject.SEA_HBM_UPDATE);
           saveList.add(mso);
           if(bcb == null)
           {
               Long bcbid = SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB);
               bcb = new Bcb();
               bcb.setId(bcbid);
               wtjd.setAnswer(bcbid);
               mso = new MutSeaObject();
               mso.setHbmSea(bcb,MutSeaObject.SEA_HBM_INSERT);
               String content = SysCommonFunc.getStrParameter(request, "content");
               mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),"content");
               saveList.add(mso);
           }else
           {
             mso = new MutSeaObject();
             mso.setHbmSea(bcb,MutSeaObject.SEA_HBM_UPDATE);
             String content = SysCommonFunc.getStrParameter(request, "content");
             mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),"content");
             saveList.add(mso);
           }
            HbmOperator.SeaMutDataWithBlob(saveList);
       }
     }catch(Exception e)
     {
       System.out.println(e.getMessage());
       s= "保存信息出错，请与开发商联系！";
     }

     return s;
  }
  
  
  public static String preWtjd(HttpServletRequest request)
  {
     String s = null;
     String id = SysCommonFunc.getStrParameter(request,"id");

     if(SysCommonFunc.getStrParameter(request, "title").getBytes().length > 201)
       return "标题请少于100个汉字！";
     Wtjd wtjd=null;
     if(id != null)
     {
      wtjd = WTJDMng.getWtjdFromid(id);
     }
     Bcb bcb = null;
     if(wtjd != null && wtjd.getAnswer() != null)
        bcb = BCBMng.getBcbFromid(wtjd.getAnswer().toString());
     if(wtjd == null)
       id = "";

     Vector saveList = new Vector();
     try
     {
       if (id.length() == 0) { //新增加
         Long wLong = SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_WTJD);
           s=String.valueOf(wLong);
         Long bcbid = SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB);
         wtjd = new Wtjd();
         wtjd.setAnswer(bcbid);
         wtjd.setAsk(SysCommonFunc.getStrParameter(request, "title"));
         wtjd.setBianhao(SysCommonFunc.getStrParameter(request, "bianhao"));
         wtjd.setId(wLong);
         wtjd.setWttype(new Integer(SysCommonFunc.getStrParameter(request, "wttype")));

         wtjd.setStime(null);


         bcb = new Bcb();
         bcb.setId(bcbid);

         MutSeaObject mso = new MutSeaObject();
         mso.setHbmSea(wtjd, MutSeaObject.SEA_HBM_INSERT);
         saveList.add(mso);
         mso = new MutSeaObject();
         mso.setHbmSea(bcb,MutSeaObject.SEA_HBM_INSERT);
         String content = SysCommonFunc.getStrParameter(request, "content");
         mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),"content");
         saveList.add(mso);
         HbmOperator.SeaMutDataWithBlob(saveList);
       }else
       {
           wtjd.setAsk(SysCommonFunc.getStrParameter(request, "title"));
           wtjd.setBianhao(SysCommonFunc.getStrParameter(request, "bianhao"));
           wtjd.setWttype(new Integer(SysCommonFunc.getStrParameter(request, "wttype")));
           wtjd.setStime(null);
             s=String.valueOf(id);
           MutSeaObject mso = new MutSeaObject();
           mso.setHbmSea(wtjd,MutSeaObject.SEA_HBM_UPDATE);
           saveList.add(mso);
           if(bcb == null)
           {
               Long bcbid = SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB);
               bcb = new Bcb();
               bcb.setId(bcbid);
               wtjd.setAnswer(bcbid);
               mso = new MutSeaObject();
               mso.setHbmSea(bcb,MutSeaObject.SEA_HBM_INSERT);
               String content = SysCommonFunc.getStrParameter(request, "content");
               mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),"content");
               saveList.add(mso);
           }else
           {
             mso = new MutSeaObject();
             mso.setHbmSea(bcb,MutSeaObject.SEA_HBM_UPDATE);
             String content = SysCommonFunc.getStrParameter(request, "content");
             mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),"content");
             saveList.add(mso);
           }
            HbmOperator.SeaMutDataWithBlob(saveList);
       }
     }catch(Exception e)
     {
       System.out.println(e.getMessage());
       s= "保存信息出错，请与开发商联系！";
     }

     return s;
   }
  
}
