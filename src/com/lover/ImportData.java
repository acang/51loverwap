package com.lover;

import com.web.common.*;
import com.common.*;
import javax.servlet.http.HttpServletRequest;

import com.web.obj.*;
import com.web.common.beanutil.*;
import hibernate.db.*;
import java.util.*;
import com.web.bean.*;
import com.lover.mng.*;
import com.web.servlet.InitEnvironment;
import java.io.*;
import com.web.obj.*;
import com.microsoft.jdbc.sqlserver.SQLServerDriver;
import java.sql.*;
import java.io.ByteArrayInputStream;
import org.apache.commons.beanutils.DynaBean;



public class ImportData {

  public ImportData() {
  }

  public static void main(String args[]) throws Exception
  {
     ImportData id = new ImportData();
     id.getConnection();

//     String src = "1<script>fdsafdsafdsa</script>1";
//
//     String s = src.replaceAll("<script>.*</script>","");
//     System.out.println(s);

     ///导入用户信息 userinfo userother hyset userarticle
     //id.importUserinfo(); ok

     //导入论坛用户
     //id.importBbsUser();

     //导入论坛板块
     //id.importBbsSort();

     //导入论坛内容
     //id.importBbs();

     //导入订单
     //id.importCart(42212,13392);

     //导入客服中心
     //id.importKfzx(37276,83);

     //导入好友管理
     id.importHygl(6817503,107);

     //导入会员留言
     //id.importHyly(250084,45);

     //导入会员秋波
     id.importQiubo(35711772,1);

     //使用指南
     //id.importWtjd();

     //导入友情链接
     //id.importFriend();
     //id.importFriendsite();

     //白金会员终止时间
     //id.importVipUser();

     //导入照片
     //id.importPhoto("g:/img");
     //测试数据
     //id.testData();
  }


  public void importPhoto(String imgpath) throws Exception
  {

    String sql = "select t.hyid,regtime,userphoto1,userphoto2,userphoto3,userphoto4,userphoto5 "+
       " from userother t left join userinfo u on t.hyid = u.hyid where memo is null and  (userphoto1 is not null or userphoto2 is not null or "+
     " userphoto3 is not null or userphoto4 is not null or userphoto5 is not null) and rownum < 1000";
      List list = QueryRecord.query(sql);
      String bigpath = SysCommonFunc.getPhotoPath();
      String smallpath = SysCommonFunc.getPhotoSmalPath();
      while(list.size() > 0)
      {
        System.out.println("page="+list.size());
        for(int i =0;i < list.size();i ++)
        {
            try
            {
              java.util.Date regtime = null;
              DynaBean uother = (DynaBean) list.get(i);
              regtime = (java.util.Date) uother.get("regtime");
              String cdates = DateTools.DateToString(regtime,
                                                     DateTools.FORMART_yyyyMMdd);


              String[] uparray = new String[5];
              uparray[0] = (String) uother.get("userphoto1");
              uparray[1] = (String) uother.get("userphoto2");
              uparray[2] = (String) uother.get("userphoto3");
              uparray[3] = (String) uother.get("userphoto4");
              uparray[4] = (String) uother.get("userphoto5");

              String hyid = uother.get("hyid").toString();
              for (int j = 0; j < 5; j++) {
                if (uparray[j] != null && uparray[j].trim().length() > 0) {
                  try {

                    uparray[j] = uparray[j].toLowerCase().trim();
                    System.out.println("have file="+uparray[j]);
                    File file = new File(bigpath + File.separator + cdates);
                    if (!file.exists())
                      file.mkdirs();
                    file = new File(smallpath + File.separator + cdates);
                    if (!file.exists())
                      file.mkdirs();

                    file = new File(imgpath + File.separator + uparray[j]);
                    System.out.println("原始文件位置="+imgpath + File.separator + uparray[j]);
                    String filename = cdates + File.separator + hyid + "_" +
                        System.currentTimeMillis() + "_" +j+
                        uparray[j].substring(uparray[j].length() - 4);
                    if (file.exists() &&
                        (uparray[j].endsWith(".jpg") || uparray[j].endsWith(".gif"))) {
                      System.out.println("开始复制");
                      ImageUtil.copyFile(imgpath + file.separator + uparray[j],
                                         bigpath + File.separator + filename);
                      if (filename.endsWith("jpg")) {

                          ImageUtil.resizePhotoJpg(bigpath, filename, smallpath,
                                                   filename,
                                                   122, 140);
                      }
                      else {
                        ImageUtil.dealGif(bigpath, filename, smallpath, filename,
                                          122,
                                          140);
                      }
                      uparray[j] = filename;
                    }
                    else
                    {
                      System.out.println("文件名不存在"+uparray[j]);
                      uparray[j] = "";
                    }
                  }
                  catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("ERROR="+e.getMessage());
                  }
                }
                else {
                  uparray[j] = "";
                }
              }

              //修改
              String usql = "update userother set userphoto1 = '" + uparray[0] +
                  "',userphoto2='" + uparray[1] + "',userphoto3='" + uparray[2] +
                  "',userphoto4='" + uparray[3] + "',userphoto5='" + uparray[4] +
                  "',memo='1' where hyid="+hyid;
              HbmOperator.executeSql(usql);
            }catch(Exception e)
            {
              e.printStackTrace();
              System.out.println("error="+e.getMessage());
            }

        }

        list = QueryRecord.query(sql);
      }

  }

  public void importVipUser() throws Exception
  {
    Connection conn = this.getConnection();
    //Hashtable ht = this.getDic();
    Statement st = conn.createStatement();

    ResultSet rs = st.executeQuery("select * from vipuser1");
    while(rs.next())
    {
       int id = rs.getInt("id");
       int hyid = rs.getInt("hyid");
       String czry = rs.getString("czry");
       String sqhy = rs.getString("sqhy");
       java.util.Date yyxq = rs.getTimestamp("yyxq");
       String sqdj = rs.getString("sqdj");
       int sqfy = rs.getInt("sqfy");
       java.util.Date qssj = rs.getTimestamp("qssj");
       java.util.Date zzsj = rs.getDate("zzsj");
       java.util.Date czsj = rs.getTimestamp("czsj");
       java.util.Date jfrq = rs.getTimestamp("jfrq");
       java.util.Date cxsj = rs.getTimestamp("cxsj");
       String memo = rs.getString("memo");
       String httpurl = rs.getString("httpurl");

       Vipuserrecord vpu = new Vipuserrecord();
       vpu.setCzry(czry == null ? "":czry.trim());
       vpu.setCzsj(czsj);
       vpu.setDatetype(new Integer(0));
       vpu.setFlag(new Integer(30));
       vpu.setHttpurl(httpurl);
       vpu.setHyid((long)hyid);
       vpu.setId(new Long(id));
       vpu.setName("白金VIP会员(一年期)");
       vpu.setServiceyear(new Long(1));
       vpu.setSimplename("白金VIP会员(一年期)");
       vpu.setSqdj(sqdj == null ? "":sqdj.trim());
       vpu.setSqhy(sqhy == null ? "":sqhy.trim());
       //vpu.setTransamt(new Long(sqfy));
       vpu.setTransamt(new Double(sqfy));
       vpu.setYyxq(yyxq);
       vpu.setZzsj(zzsj);

       Vector saveList = new Vector();
       MutSeaObject mso  = new MutSeaObject();
       mso.setHbmSea(vpu,mso.SEA_HBM_INSERT);
       saveList.add(mso);

       String sql = "update userinfo set zzsj= to_date('"+DateTools.dateToString(zzsj,false) + "','YYYY-MM-DD') WHERE HYID="+vpu.getHyid();

       mso = new MutSeaObject();
       mso.setSqlSea(sql,mso.SEA_SQL_UPDATE);
       saveList.add(mso);


       try
       {
         HbmOperator.SeaMutData(saveList);
       }catch(Exception e)
       {
         System.out.println(e.getMessage());
       }
    }

     rs.close();
      st.close();
      conn.close();

  }


  public void importFriendsite() throws Exception
  {
    Connection conn = this.getConnection();
    //Hashtable ht = this.getDic();
    Statement st = conn.createStatement();

    ResultSet rs = st.executeQuery("select * from friendsite");
    while(rs.next())
    {
      int id = rs.getInt("ID");
      int liketype = rs.getInt("LinkType");
      String sitename = rs.getString("SiteName");
      String siteurl = rs.getString("SiteUrl");
      String siteintro = rs.getString("SiteIntro");
      String siteadmin = rs.getString("SiteAdmin");
      String email = rs.getString("Email");
      String sitepassword = rs.getString("SitePassword");
      int isgood = rs.getInt("IsGood");
      int isOk = rs.getInt("IsOK");
      int xuhao = rs.getInt("xuhao");
      Friendsite fsite = new Friendsite();
      fsite.setEmail(email);
      fsite.setId(new Integer(id));
      fsite.setIsgood(isgood);
      fsite.setIsok(isOk);
      fsite.setLinktype(new Integer(liketype));
      String logoUrl = rs.getString("LogoUrl");
      logoUrl = logoUrl == null ? "":logoUrl.replaceAll("<script.*</script>","");
      fsite.setLogourl(logoUrl);
      fsite.setSiteadmin(siteadmin);
      fsite.setSiteintro(siteintro);
      fsite.setSitename(sitename);
      fsite.setSitepassword(sitepassword);
      fsite.setSiteurl(siteurl);
      fsite.setXuhao(new Integer(xuhao));


       try
       {


         HbmOperator.insert(fsite);
       }catch(Exception e)
       {
         System.out.println(e.getMessage());
       }
    }

     rs.close();
      st.close();
      conn.close();


  }


  public void importFriend() throws Exception
  {
    Connection conn = this.getConnection();
    //Hashtable ht = this.getDic();
    Statement st = conn.createStatement();

    ResultSet rs = st.executeQuery("select * from friend ");
    while(rs.next())
    {
       int id = rs.getInt("id");
       String bianhao = rs.getString("bianhao");
       String netname = rs.getString("netname");
       netname = netname == null ? "":netname.replaceAll("<script.*</script>","");
       String nethttp = rs.getString("nethttp");
       String netlogo  = rs.getString("netlogo");
       String memo     = rs.getString("memo");


       Friend fr = new Friend();
       fr.setBianhao(bianhao == null ? "":bianhao.trim());
       fr.setId(new Integer(id));
       fr.setMemo(memo);

       nethttp = nethttp == null ? "":nethttp.replaceAll("<script.*</script>","");
       fr.setNethttp(nethttp);
       fr.setNetlogo(netlogo);
       fr.setNetname(netname);
       try
       {
         HbmOperator.insert(fr);
       }catch(Exception e)
       {
         System.out.println(e.getMessage());
       }
    }

     rs.close();
      st.close();
      conn.close();


  }


  public void importHYQB(String hyid) throws Exception
  {
    Connection conn = this.getConnection();
    //Hashtable ht = this.getDic();
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery("  SELECT * FROM db_qiubo WHERE (postuserid = "+hyid+") OR       (senduserid = "+hyid+")");
    while(rs.next())
    {
      int id = rs.getInt("id");
      int senduserid = rs.getInt("senduserid");
      String sendname = rs.getString("sendusername");
      int postid = rs.getInt("postuserid");
      String postusername = rs.getString("postusername");
      int zt = rs.getInt("zt");
      java.util.Date sendtime = rs.getTimestamp("sendtime");
      DbQiubo dq = new DbQiubo();
      dq.setId(new Long(id));
      dq.setPostuserid(new Long(postid));
      dq.setPostusername(postusername);
      dq.setSendtime(sendtime);
      dq.setSenduserid(new Long(senduserid));
      dq.setSendusername(sendname);
      dq.setZt(new Integer(zt));
      try{
        HbmOperator.delete(dq);
      }catch(Exception ee)
      {

      }
      try
      {
        HbmOperator.insert(dq);
      }catch(Exception ee)
      {

      }

    }
    rs.close();

    rs = st.executeQuery("SELECT id, RTRIM(sqname) AS sqname, sqid, RTRIM(sqlc) AS sqlc, sqtype, RTRIM(myname) AS myname, myid, RTRIM(mylc) AS mylc, riqi FROM hygl WHERE (myid = "+hyid+") OR       (sqid = "+hyid+") ");

    while(rs.next())
    {
      int id = rs.getInt("id");
      String sqname = rs.getString("sqname");
      int sqid = rs.getInt("sqid");
      String sqlc = rs.getString("sqlc");
      String sqtype = rs.getString("sqtype");
      String myname = rs.getString("myname");
      int myid = rs.getInt("myid");
      String mylc = rs.getString("mylc");
      java.util.Date riqi = rs.getTimestamp("riqi");

      if (sqname != null)
        sqname = sqname.trim();
      if (sqlc != null)
        sqlc = sqlc.trim();
      if (myname != null)
        myname = myname.trim();
      if (mylc != null)
        mylc = mylc.trim();
      if (sqtype != null)
        sqtype = sqtype.trim();

      Hygl hygl = new Hygl();
      hygl.setId(new Long(id));
      hygl.setMyid(new Long(myid));
      hygl.setMylc(mylc);
      hygl.setMyname(myname);
      hygl.setRiqi(riqi);
      hygl.setSqid(new Long(sqid));
      hygl.setSqlc(sqlc);
      hygl.setSqname(sqname);
      hygl.setSqtype(new Integer(SysCommonFunc.getNumberFromString(sqtype, 4)));

      try
      {
        HbmOperator.delete(hygl);
      }catch(Exception ee)
      {

      }

      try
      {
        HbmOperator.insert(hygl);
      }catch(Exception ee)
      {

      }
    }

    rs.close();



    st.close();
    conn.close();


  }


  public void importQiubo(int maxid,int minid) throws Exception
 {
   Connection conn = this.getConnection();
   //Hashtable ht = this.getDic();
   Statement st = conn.createStatement();


   int start = minid;
   int end =   start+1000;
   ResultSet rs = st.executeQuery("select * from db_qiubo where id >= " + start + " and id < " +end);
   start = end;

    while(end >=minid && end <=(maxid+2000))
    {

     while(rs.next())
     {
            int id = rs.getInt("id");
            int senduserid = rs.getInt("senduserid");
            String sendname = rs.getString("sendusername");
            int postid = rs.getInt("postuserid");
            String postusername = rs.getString("postusername");
            int zt = rs.getInt("zt");
            java.util.Date sendtime = rs.getTimestamp("sendtime");
            DbQiubo dq = new DbQiubo();
            dq.setId(new Long(id));
            dq.setPostuserid(new Long(postid));
            dq.setPostusername(postusername);
            dq.setSendtime(sendtime);
            dq.setSenduserid(new Long(senduserid));
            dq.setSendusername(sendname);
            dq.setZt(new Integer(zt));

            try
            {
              HbmOperator.insert(dq);
            }catch(Exception e)
            {
              System.out.println(e.getMessage());
            }




     }
     rs.close();
     end = start+1000;
       rs = st.executeQuery("select * from db_qiubo where id >= " + start + " and id < " +end);
       start = end;





   }


   rs.close();
   st.close();
   conn.close();

 }



  public void importHyly(int maxid,int minid) throws Exception
 {
   Connection conn = this.getConnection();
   //Hashtable ht = this.getDic();
   Statement st = conn.createStatement();


   int start = minid;
   int end =   start+1000;
   ResultSet rs = st.executeQuery("select * from hyly where id >= " + start + " and id < " +end);
   start = end;

    while(end >=minid && end <=(maxid+2000))
    {

     while(rs.next())
     {
            int id = rs.getInt("id");
            String hyname = rs.getString("hyname");
            int  hyid = rs.getInt("hyid");
            String fromname = rs.getString("fromname");
            int fromid = rs.getInt("fromid");
            String content = rs.getString("content");
            java.util.Date lytime = rs.getTimestamp("lytime");
            if(hyname != null)
              hyname = hyname.trim();
            if(fromname != null)
              fromname.trim();
            if(content != null)
            {
              int index = content.indexOf("<script");
              if(index > -1)
                content = content.substring(0,index);
            }
            if(hyid ==0 || content == null || lytime == null || fromname == null || fromname.length() ==0 || hyname == null || hyname.length() ==0)
              continue;


            Hyly hyly = new Hyly();
            hyly.setContent(content);
            hyly.setFromid(new Long(fromid));
            hyly.setFromname(fromname);
            hyly.setHyid(new Long(hyid));
            hyly.setHyname(hyname);
            hyly.setId(new Long(id));
            hyly.setLytime(lytime);
            try
            {
              HbmOperator.insert(hyly);
            }catch(Exception e)
            {
              System.out.println(e.getMessage());
            }




     }
     rs.close();
     end = start+1000;
       rs = st.executeQuery("select * from hyly where id >= " + start + " and id < " +end);
       start = end;





   }


   rs.close();
   st.close();
   conn.close();

 }




  public void importHygl(int maxid,int minid) throws Exception
 {
   Connection conn = this.getConnection();
   //Hashtable ht = this.getDic();
   Statement st = conn.createStatement();


   int start = minid;
   int end =   start+1000;
   ResultSet rs = st.executeQuery("select * from hygl where id >= " + start + " and id < " +end);
   start = end;

    while(end >=minid && end <=(maxid+2000))
    {

     while(rs.next())
     {


            int id = rs.getInt("id");
            String sqname = rs.getString("sqname");
            int sqid = rs.getInt("sqid");
            String sqlc = rs.getString("sqlc");
            String sqtype = rs.getString("sqtype");
            String myname = rs.getString("myname");
            int myid = rs.getInt("myid");
            String mylc = rs.getString("mylc");
            java.util.Date riqi = rs.getTimestamp("riqi");

            if(sqname != null)
              sqname = sqname.trim();
            if(sqlc != null)
              sqlc = sqlc.trim();
            if(myname != null)
              myname = myname.trim();
            if(mylc != null)
              mylc = mylc.trim();
            if(sqtype != null)
              sqtype = sqtype.trim();

             Hygl hygl = new Hygl();
             hygl.setId(new Long(id));
             hygl.setMyid(new Long(myid));
             hygl.setMylc(mylc);
             hygl.setMyname(myname);
             hygl.setRiqi(riqi);
             hygl.setSqid(new Long(sqid));
             hygl.setSqlc(sqlc);
             hygl.setSqname(sqname);
             hygl.setSqtype(new Integer(SysCommonFunc.getNumberFromString(sqtype,4)));

            try
            {
              HbmOperator.insert(hygl);
            }catch(Exception e)
            {
              System.out.println(e.getMessage());
            }




     }
     rs.close();
     end = start+1000;
       rs = st.executeQuery("select * from hygl where id >= " + start + " and id < " +end);
       start = end;





   }


   rs.close();
   st.close();
   conn.close();

 }



  public void importWtjd() throws Exception
  {
    Connection conn = this.getConnection();
    //Hashtable ht = this.getDic();
    Statement st = conn.createStatement();
    ResultSet rs = st.executeQuery("select * from wtjd");
    while(rs.next())
    {
       int id = rs.getInt("id");
       String bianhao = rs.getString("bianhao");
       if(bianhao != null)
         bianhao = bianhao.trim();
       String ask = rs.getString("ask");
       ask = LoverTools.getHtml(ask);
       String answer = rs.getString("answer");
       answer = LoverTools.getHtml(answer);

       Wtjd wtjd = new Wtjd();
       Bcb bcb = new Bcb(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB));
       wtjd.setAnswer(bcb.getId());
       wtjd.setAsk(ask);
       wtjd.setBianhao(bianhao);
       wtjd.setId(new Long(id));
       wtjd.setWttype(new Integer(0));

       Vector  saveList = new Vector();
       MutSeaObject mso = new MutSeaObject();
       mso.setHbmSea(wtjd,mso.SEA_HBM_INSERT);
       saveList.add(mso);

       mso = new MutSeaObject();
       mso.setHbmSea(bcb,mso.SEA_HBM_INSERT);
       mso.setBlobStream(new ByteArrayInputStream(answer.getBytes()),"content");
       saveList.add(mso);

       try
       {
         HbmOperator.SeaMutDataWithBlob(saveList);
       }catch(Exception e)
       {
         System.out.println(e.getMessage());
       }
    }
    rs.close();

    rs = st.executeQuery("select * from news");
    if(rs.next())
    {
      int id = rs.getInt("newsid");
      String newstitle = rs.getString("newstitle");
      newstitle = LoverTools.getHtml(newstitle);
      String newslr = rs.getString("newslr_new");
      newslr = LoverTools.getHtml(newslr);

      Wtjd wtjd = new Wtjd();
      Bcb bcb = new Bcb(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB));
      wtjd.setAnswer(bcb.getId());
      wtjd.setAsk(newstitle);
      wtjd.setBianhao("1");
      wtjd.setId(new Long(id));
      wtjd.setWttype(new Integer(1));

      Vector  saveList = new Vector();
      MutSeaObject mso = new MutSeaObject();
      mso.setHbmSea(wtjd,mso.SEA_HBM_INSERT);
      saveList.add(mso);

      mso = new MutSeaObject();
      mso.setHbmSea(bcb,mso.SEA_HBM_INSERT);
      mso.setBlobStream(new ByteArrayInputStream(newslr.getBytes()),"content");
      saveList.add(mso);
      try
      {
        HbmOperator.SeaMutDataWithBlob(saveList);
      }catch(Exception e)
      {
        System.out.println(e.getMessage());
      }

    }


    rs.close();
    st.close();
    conn.close();

  }
  public void importKfzx(int maxid,int minid) throws Exception
  {
    Connection conn = this.getConnection();
    //Hashtable ht = this.getDic();
    Statement st = conn.createStatement();


    int start = minid;
    int end =   start+1000;
    ResultSet rs = st.executeQuery("select * from kfzx where id >= " + start + " and id < " +end);
    start = end;

     while(end >=minid && end <=(maxid+2000))
     {

      while(rs.next())
      {

             int id = rs.getInt("id");
             String username = rs.getString("username");
             if(username != null)
               username = username.trim();
             String tel = rs.getString("tel");
             if(tel != null)
               tel = tel.trim();
             String email = rs.getString("email");
             if(email != null)
               email = email.trim();
             String title = rs.getString("n_title");
             if(title != null)
               title = title.trim();


             String n_text = rs.getString("n_text");
             int ntype = rs.getInt("n_type");
             int ztai = rs.getInt("ztai");
             String n_content = rs.getString("n_content");
             java.util.Date ndate = rs.getTimestamp("n_date");


             Kfzx kfzx = new Kfzx();
             kfzx.setEmail(this.getNumberValue(email));
             kfzx.setId(new Long(id));
             kfzx.setNcontent(n_content);
             kfzx.setNdate(ndate);
             kfzx.setNtext(n_text);
             kfzx.setNtitle(title);
             kfzx.setNtype(new Integer(ntype));
             kfzx.setTel(this.getNumberValue(tel));
             kfzx.setUsername(username);
             kfzx.setZtai(new Integer(ztai));





             try
             {
               HbmOperator.insert(kfzx);
             }catch(Exception e)
             {
               System.out.println(e.getMessage());
             }




      }
      rs.close();
      end = start+1000;
        rs = st.executeQuery("select * from kfzx where id >= " + start + " and id < " +end);
        start = end;





    }


    rs.close();
    st.close();
    conn.close();

  }


  public void importCart(int maxid,int minid) throws Exception
  {
    Connection conn = this.getConnection();
    //Hashtable ht = this.getDic();
    Statement st = conn.createStatement();


    int start = minid;
    int end =   start+1000;
    ResultSet rs = st.executeQuery("select * from cart where id >= " + start + " and id < " +end);
    start = end;

     while(end >=minid && end <=(maxid+2000))
     {

      while(rs.next())
      {

             int id = rs.getInt("id");
             String username = rs.getString("username");
             String lcname   = rs.getString("lcname");
             String commenduser = rs.getString("CommendUser");
             String ordid    = rs.getString("ordid");
             String zsname  = rs.getString("zsname");
             int  transamt  = rs.getInt("transamt");
             String product = rs.getString("product");
             String tel  = rs.getString("tel");
             String email = rs.getString("email");
             if(email != null)
             {
               byte[] emailxarray = email.getBytes();
               if(emailxarray.length > 50)
                 email = new String(emailxarray,0,50);
             }

             String address = rs.getString("address");
             String ztai    = rs.getString("ztai");
             int trans = rs.getInt("trans");
             int upgrade = rs.getInt("upgrade");
             int hit = rs.getInt("hit");
             java.util.Date sjtime = rs.getTimestamp("sjtime");
             tel = this.getNumberValue(tel);

             Cart cart = new Cart();
             cart.setAddress(address);
             cart.setCommenduser(commenduser);
             cart.setDatetype(new Integer(1));
             cart.setEmail(email);
             cart.setFlag(new Integer(30));
             cart.setId(new Long(id));
             cart.setLcname(lcname);
             cart.setName(product);
             cart.setOrdid(ordid);
             if(product.indexOf("一年") > 0)
                cart.setServiceyear(new Long(1));
             else if(product.indexOf("三年") >0)
                cart.setServiceyear(new Long(3));
             else
                cart.setServiceyear(new Long(0));
             cart.setSimplename(product);
             cart.setSjtime(sjtime);
             cart.setSqdj("白金");
             cart.setTel(tel);
             cart.setTrans(new Integer(trans));
             cart.setTransamt(new Double(transamt));
             cart.setUpgrade(new Integer(upgrade));
             cart.setUsername(username);
             cart.setZsname(zsname);
             cart.setZtai(ztai);






             try
             {
               HbmOperator.insert(cart);
             }catch(Exception e)
             {
               System.out.println(e.getMessage());
             }




      }
      rs.close();
      end = start+1000;
        rs = st.executeQuery("select * from cart where id >= " + start + " and id < " +end);
        start = end;





    }


    rs.close();
    st.close();
    conn.close();

  }

  public void importBbs() throws Exception
  {
    Connection conn = this.getConnection();
    //Hashtable ht = this.getDic();
    Statement st = conn.createStatement();


    int start = 0;
    int end =   start+1000;
    ResultSet rs = st.executeQuery("select * from bbs where id >= " + start + " and id < " +end);
    start = end;

      boolean have = true;
      while(have)
      {
         have = false;


      while(rs.next())
      {
             have=true;
             int id = rs.getInt("id");
             String author = rs.getString("author");
             java.util.Date stime = rs.getTimestamp("s_time");
             String title = rs.getString("title");
             String content = rs.getString("content");
             int recount = rs.getInt("re_count");
             int reid = rs.getInt("re_id");
             int commend = rs.getInt("commend");
             int hyid = rs.getInt("user_id");
             String username = rs.getString("user_name");
             int hits = rs.getInt("hits");
             String censor = rs.getString("censor");
             java.util.Date retime = rs.getTimestamp("re_time");
             int ontop = rs.getInt("ontop");
             int sortid = rs.getInt("sortid");

             Bb bbs = new Bb();
             Bcb bcb = new Bcb();
             bcb.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB));
             bbs.setAuthor(author);
             bbs.setCensor(censor);
             bbs.setCommend(new Integer(commend));
             bbs.setContent(bcb.getId());
             bbs.setHits(new Long(hits));
             bbs.setId(new Long(id));
             if(censor != null && censor.length() >0)
               bbs.setIscheck("1");
             else
               bbs.setIscheck("0");
             bbs.setOntop(new Integer(ontop));
             bbs.setReCount(new Long(recount));
             bbs.setReId(new Long(reid));
             bbs.setReTime(retime);
             bbs.setSortid(new Long(sortid));
             bbs.setStime(stime);
             bbs.setTitle(title);
             bbs.setUserId(new Long(hyid));
             bbs.setUserName(username);

             Vector saveList = new Vector();
             MutSeaObject mso = new MutSeaObject();
             mso.setHbmSea(bbs,mso.SEA_HBM_INSERT);
             saveList.add(mso);

             mso = new MutSeaObject();
             mso.setHbmSea(bcb,mso.SEA_HBM_INSERT);
             mso.setBlobStream(new ByteArrayInputStream(content.getBytes()),"content");
             saveList.add(mso);

             try
             {
               HbmOperator.SeaMutDataWithBlob(saveList);
             }catch(Exception e)
             {
               System.out.println(e.getMessage());
             }




      }
      end = start+1000;
        rs = st.executeQuery("select * from bbs where id >= " + start + " and id < " +end);
        start = end;





    }


    rs.close();
    st.close();
    conn.close();

  }


  public void importBbsSort() throws Exception
  {
    Connection conn = this.getConnection();
    //Hashtable ht = this.getDic();
    Statement st = conn.createStatement();

    ResultSet rs = st.executeQuery("select * from bbssort");
    while(rs.next())
    {
       int id = rs.getInt("id");
       int orders = rs.getInt("orders");
       String sortname = rs.getString("sortname");
       String content  = rs.getString("content");
       int topics = rs.getInt("topics");
       java.util.Date stime = rs.getTimestamp("s_time");

       Bbssort bsort = new Bbssort();
       bsort.setContent(content);
       bsort.setId(new Long(id));
       bsort.setOrders(new Integer(orders));
       bsort.setSortname(sortname);
       bsort.setStime(stime);
       bsort.setTopics(new Long(topics));
       try
       {
         HbmOperator.insert(bsort);
       }catch(Exception e)
       {
         System.out.println(e.getMessage());
       }

    }


    rs.close();
  st.close();
  conn.close();



  }

  ///导入ｂｂｓ　用户
  public void importBbsUser() throws Exception
  {
    Connection conn = this.getConnection();
    //Hashtable ht = this.getDic();
    Statement st = conn.createStatement();

    ResultSet rs = st.executeQuery("select * from bbsuser");
    while(rs.next())
    {
       int hyid = rs.getInt("userid");
       String username = rs.getString("user_name");
       if(username != null)
          username = username.trim();
       int grade = rs.getInt("grade");
       int topics = rs.getInt("topics");
       int commend = rs.getInt("commend");
       int resum   = rs.getInt("resum");
       int moneys  = rs.getInt("moneys");
       java.util.Date regtime = rs.getTimestamp("regtime");
       int staytime = rs.getInt("staytime");

       Bbsuser bu = new Bbsuser();
       bu.setCommend(new Long(commend));
       bu.setGrade(new Long(grade));
       bu.setHyid(new Long(hyid));
       bu.setLcname(username);
       bu.setMoneys(new Long(moneys));
       bu.setRegtime(regtime);
       bu.setResum(new Long(resum));
       bu.setStaytime(new Long(staytime));
       bu.setTopics(new Long(topics));
       bu.setUsername(username);
       try
       {
         HbmOperator.insert(bu);
       }catch(Exception e)
       {
         System.out.println(e.getMessage());
       }

    }


    rs.close();
  st.close();
  conn.close();


  }


  public void testData() throws Exception
  {
  //String s = id.getNumberValue("０１２３４５６７８９");
    //System.out.println(s);
    List list = HbmOperator.list("from Bc as o where rownum < 2");
    Bc bc = (Bc)list.get(0);
    StringBuffer sb = new StringBuffer(2000);
    for(int i =0;i < 2000;i ++)
      sb.append("黄");
    bc.setContent(sb.toString());
    HbmOperator.update(bc);
  }

  ///会员终止时间在导入vip的时候导入
  ///插入 userinfo userarticle userother hyset
  public void importUserinfo() throws Exception
  {
     Connection conn = this.getConnection();
     Hashtable ht = this.getDic();
     Statement st = conn.createStatement();



     List userList = HbmOperator.list("from Userinfo as o where o.isdel = 2 and o.flag is null and rownum < 2000");
     while(userList.size() >0)
     {
       for(int i =0;i < userList.size();i ++)
         disPoseImportUser(ht,st,(Userinfo)userList.get(i));
       userList = HbmOperator.list("from Userinfo as o where o.isdel == 2  and o.flag is null and rownum < 2000");
     }
  }

  public void disPoseImportUser(Hashtable dic,Statement st ,Userinfo user)
  {
     try
     {
        ///查询会员信息
        Vector saveList = new Vector();

        ResultSet rs = st.executeQuery("select * from userinfo where hyid = "+user.getHyid());
        if(!rs.next())
          return;
        String username = rs.getString("username");
        String lcname   = rs.getString("lcname");
        String password = rs.getString("password");
        String ask      = rs.getString("ask");
        String answer   = rs.getString("answer");
        String sex      = rs.getString("sex");

        java.util.Date csdate     = rs.getDate("csdate");

        String sx       = rs.getString("sx");
        String xz = rs.getString("xz");
        String sg   = rs.getString("sg");
        if(sg != null && !sg.equals("145cm以下") && !sg.equals("185cm以上"))
          sg = sg + "cm";

        String tz   = rs.getString("tz");
        if(tz != null && !tz.equals("70斤以下") && !tz.equals("160斤以上"))
          tz = tz + "斤";


        String fs   = rs.getString("fs");
        String whcd      = rs.getString("whcd");
        String xgtz       = rs.getString("xgtz");

        String yyap = rs.getString("yyap");
        String yx   = rs.getString("yx");
        String hyzk = rs.getString("hyzk");


        String jyyx      = rs.getString("jyyx");
        String qggx   = rs.getString("qggx");
        if(qggx != null)
        {
          byte[] qggxarray = qggx.getBytes();
          if(qggxarray.length > 666)
            qggx = new String(qggxarray,0,666);
        }

        String lxqr       = rs.getString("lxqr");
        if(lxqr != null)
        {
          byte[] lxqrxarray = lxqr.getBytes();
          if(lxqrxarray.length > 666)
            lxqr = new String(lxqrxarray,0,666);
        }

        String jyly = rs.getString("jyly");
        if(jyly != null)
        {
          byte[] jylyxarray = jyly.getBytes();
          if(jylyxarray.length > 666)
            jyly = new String(jylyxarray,0,666);
        }

        String zf   = rs.getString("zf");
        String email = rs.getString("email");
        if(email != null)
       {
         byte[] emailxarray = email.getBytes();
         if(emailxarray.length > 25)
           email = new String(emailxarray,0,25);
       }


        String oicq   = rs.getString("oicq");

          if(oicq != null)
         {
           byte[] oicqxarray = oicq.getBytes();
           if(oicqxarray.length > 25)
             oicq = new String(oicqxarray,0,24);
         }

        String sjtel      = rs.getString("sjtel");

        if(sjtel != null)
        {
          byte[] sjtelarray = sjtel.getBytes();
          if (sjtelarray.length > 50)
            sjtel = new String(sjtelarray, 0, 50);
        }

        String tel = rs.getString("tel");
        if(tel != null)
        {
          byte[] telarray = tel.getBytes();
          if (tel.length() > 50)
            tel = new String(telarray, 0, 50);
        }

        int flag = rs.getInt("flag");
        java.util.Date tjsj = rs.getTimestamp("tjsj");
        int hots = rs.getInt("hots");
        java.util.Date regtime = rs.getTimestamp("regtime");
        java.util.Date lasttime = rs.getTimestamp("lasttime");
        java.util.Date regtime2  = rs.getTimestamp("regtime2");
        int img = rs.getInt("img");


        int setzt   = rs.getInt("setzt");
        String shr = rs.getString("shr");
        String shqk   = rs.getString("shqk");
        String httpurl      = rs.getString("httpurl");
        String httpip       = rs.getString("httpip");
        int isdel = rs.getInt("isdel");
        String s1 = rs.getString("s1");
        String s2 = rs.getString("s2");
        rs.close();

        ///设置会员信息
        Userinfo inUser = new Userinfo();
        inUser.setAddress(s1+s2);
        inUser.setAnswer(answer);
        inUser.setAsk(ask);
        inUser.setCsdate(csdate);
        inUser.setEmail(email);
        inUser.setFlag(new Integer(flag));
        inUser.setFs(this.getDicValue(fs,dic));
        inUser.setHots(new Long(hots));
        inUser.setHttpip(httpip);
        inUser.setHttpurl(httpurl);
        inUser.setHyid(user.getHyid());
        inUser.setHyzk(this.getDicValue(hyzk,dic));
        inUser.setImg(new Integer(img));
        inUser.setIsdel(new Integer(isdel));
        inUser.setJyyx(new Long(this.getJyyx(jyyx)));
        inUser.setLasttime(lasttime);
        inUser.setLcname(lcname);
        inUser.setOicq(this.getNumberValue(oicq));
        inUser.setPassword(password);
        inUser.setRegtime(regtime);
        inUser.setRegtime2(regtime2);
        inUser.setS1(s1);
        inUser.setS2(s2);
        inUser.setSetzt(new Integer(setzt));
        inUser.setSex(this.getDicValue(sex,dic));
        inUser.setSg(this.getDicValue(sg,dic));
        inUser.setShqk(shqk);
        inUser.setShr(shr);
        inUser.setSjtel(this.getNumberValue(sjtel));
        inUser.setSx(this.getDicValue(sx,dic));
        inUser.setTel(this.getNumberValue(tel));
        inUser.setTz(this.getDicValue(tz,dic));
        inUser.setUsername(username);
        inUser.setVip(new Integer(0));
        inUser.setWhcd(this.getDicValue(whcd,dic));
        inUser.setXgtz(this.getXgtz(xgtz));
        inUser.setXz(this.getDicValue(xz,dic));
        inUser.setYx(this.getDicValue(yx,dic));
        inUser.setYyap(this.getYyap(yyap));
        inUser.setZf(this.getDicValue(zf,dic));
        inUser.setTjsj(tjsj);
        MutSeaObject mso = new MutSeaObject();
        mso.setHbmSea(inUser,mso.SEA_HBM_UPDATE);
        saveList.add(mso);


        ///设置终止时间等导入vip用户的时候导入

        Userother uother = new Userother();
        uother.setHyid(inUser.getHyid());
        uother.setJyly(jyly);
        uother.setLxqr(lxqr);
        uother.setQggx(qggx);
        uother.setUsername(inUser.getUsername());
        mso = new MutSeaObject();
        mso.setHbmSea(uother,mso.SEA_HBM_INSERT);
        saveList.add(mso);

        //查询会员其它信息
        rs = st.executeQuery("select * from userother where userid="+user.getHyid());
        if(rs.next())
        {
           String userphoto1 = rs.getString("userphoto1");
           String userphoto2 = rs.getString("userphoto2");
           String userphoto3 = rs.getString("userphoto3");
           String userphoto4 = rs.getString("userphoto4");
           String userphoto5 = rs.getString("userphoto5");
           userphoto1 = userphoto1 == null ? "":userphoto1.trim();
           userphoto2 = userphoto2 == null ? "":userphoto2.trim();
           userphoto3 = userphoto3 == null ? "":userphoto3.trim();
           userphoto4 = userphoto4 == null ? "":userphoto4.trim();
           userphoto5 = userphoto5 == null ? "":userphoto5.trim();
           uother.setUserphoto1(userphoto1);
           uother.setUserphoto2(userphoto2);
           uother.setUserphoto3(userphoto3);
           uother.setUserphoto4(userphoto4);
           uother.setUserphoto5(userphoto5);


           String title = rs.getString("title1");
           if(title != null && title.trim().length() > 0)
           {
              String article = rs.getString("article1");

              Userarticle uarticle = new Userarticle();
              Bcb bc = new Bcb();
              //bc.setContent(article);
              bc.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB));
              uarticle.setContentid(bc.getId());
              uarticle.setCreatetime(new java.util.Date(System.currentTimeMillis()));
              uarticle.setHyid(inUser.getHyid());
              uarticle.setHyname(inUser.getUsername());
              uarticle.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_HY_ARTICLE));
              uarticle.setLcname(inUser.getLcname());
              uarticle.setTitle(title.trim());
              mso = new MutSeaObject();
              mso.setHbmSea(uarticle,mso.SEA_HBM_INSERT);
              saveList.add(mso);
              mso = new MutSeaObject();
              mso.setHbmSea(bc,mso.SEA_HBM_INSERT);
              mso.setBlobStream(new ByteArrayInputStream(article.getBytes()),"content");
              saveList.add(mso);
           }

           title = rs.getString("title2");
           if(title != null && title.trim().length() > 0)
           {
              String article = rs.getString("article2");
              Userarticle uarticle = new Userarticle();
              Bcb bc = new Bcb();
              //bc.setContent(article);
              bc.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB));
              uarticle.setContentid(bc.getId());
              uarticle.setCreatetime(new java.util.Date(System.currentTimeMillis()));
              uarticle.setHyid(inUser.getHyid());
              uarticle.setHyname(inUser.getUsername());
              uarticle.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_HY_ARTICLE));
              uarticle.setLcname(inUser.getLcname());
              uarticle.setTitle(title.trim());
              mso = new MutSeaObject();
              mso.setHbmSea(uarticle,mso.SEA_HBM_INSERT);
              saveList.add(mso);
              mso = new MutSeaObject();
              mso.setHbmSea(bc,mso.SEA_HBM_INSERT);
              mso.setBlobStream(new ByteArrayInputStream(article.getBytes()),"content");
              saveList.add(mso);

           }
           title = rs.getString("title3");
           if(title != null && title.trim().length() > 0)
           {
              String article = rs.getString("article3");
              Userarticle uarticle = new Userarticle();
              Bcb bc = new Bcb();
              //bc.setContent(article);
              bc.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB));
              uarticle.setContentid(bc.getId());
              uarticle.setCreatetime(new java.util.Date(System.currentTimeMillis()));
              uarticle.setHyid(inUser.getHyid());
              uarticle.setHyname(inUser.getUsername());
              uarticle.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_HY_ARTICLE));
              uarticle.setLcname(inUser.getLcname());
              uarticle.setTitle(title.trim());
              mso = new MutSeaObject();
              mso.setHbmSea(uarticle,mso.SEA_HBM_INSERT);
              saveList.add(mso);
              mso = new MutSeaObject();
              mso.setHbmSea(bc,mso.SEA_HBM_INSERT);
              mso.setBlobStream(new ByteArrayInputStream(article.getBytes()),"content");
              saveList.add(mso);

           }
           title = rs.getString("title4");
           if(title != null && title.trim().length() > 0)
           {
              String article = rs.getString("article4");
              Userarticle uarticle = new Userarticle();
              Bcb bc = new Bcb();
              //bc.setContent(article);
              bc.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB));
              uarticle.setContentid(bc.getId());
              uarticle.setCreatetime(new java.util.Date(System.currentTimeMillis()));
              uarticle.setHyid(inUser.getHyid());
              uarticle.setHyname(inUser.getUsername());
              uarticle.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_HY_ARTICLE));
              uarticle.setLcname(inUser.getLcname());
              uarticle.setTitle(title.trim());
              mso = new MutSeaObject();
              mso.setHbmSea(uarticle,mso.SEA_HBM_INSERT);
              saveList.add(mso);
              mso = new MutSeaObject();
              mso.setHbmSea(bc,mso.SEA_HBM_INSERT);
              mso.setBlobStream(new ByteArrayInputStream(article.getBytes()),"content");
              saveList.add(mso);

           }
           title = rs.getString("title5");
           if(title != null && title.trim().length() > 0)
           {
              String article = rs.getString("article5");
              Userarticle uarticle = new Userarticle();
              Bcb bc = new Bcb();
              //bc.setContent(article);
              bc.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB));
              uarticle.setContentid(bc.getId());
              uarticle.setCreatetime(new java.util.Date(System.currentTimeMillis()));
              uarticle.setHyid(inUser.getHyid());
              uarticle.setHyname(inUser.getUsername());
              uarticle.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_HY_ARTICLE));
              uarticle.setLcname(inUser.getLcname());
              uarticle.setTitle(title.trim());
              mso = new MutSeaObject();
              mso.setHbmSea(uarticle,mso.SEA_HBM_INSERT);
              saveList.add(mso);
              mso = new MutSeaObject();
              mso.setHbmSea(bc,mso.SEA_HBM_INSERT);
              mso.setBlobStream(new ByteArrayInputStream(article.getBytes()),"content");
              saveList.add(mso);

           }
           title = rs.getString("title6");
           if(title != null && title.trim().length() > 0)
           {
              String article = rs.getString("article6");
              Userarticle uarticle = new Userarticle();
              Bcb bc = new Bcb();
              //bc.setContent(article);
              bc.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB));
              uarticle.setContentid(bc.getId());
              uarticle.setCreatetime(new java.util.Date(System.currentTimeMillis()));
              uarticle.setHyid(inUser.getHyid());
              uarticle.setHyname(inUser.getUsername());
              uarticle.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_HY_ARTICLE));
              uarticle.setLcname(inUser.getLcname());
              uarticle.setTitle(title.trim());
              mso = new MutSeaObject();
              mso.setHbmSea(uarticle,mso.SEA_HBM_INSERT);
              saveList.add(mso);
              mso = new MutSeaObject();
              mso.setHbmSea(bc,mso.SEA_HBM_INSERT);
              mso.setBlobStream(new ByteArrayInputStream(article.getBytes()),"content");
              saveList.add(mso);

           }
           title = rs.getString("title7");
           if(title != null && title.trim().length() > 0)
           {
              String article = rs.getString("article7");
              Userarticle uarticle = new Userarticle();
              Bcb bc = new Bcb();
              //bc.setContent(article);
              bc.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB));
              uarticle.setContentid(bc.getId());
              uarticle.setCreatetime(new java.util.Date(System.currentTimeMillis()));
              uarticle.setHyid(inUser.getHyid());
              uarticle.setHyname(inUser.getUsername());
              uarticle.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_HY_ARTICLE));
              uarticle.setLcname(inUser.getLcname());
              uarticle.setTitle(title.trim());
              mso = new MutSeaObject();
              mso.setHbmSea(uarticle,mso.SEA_HBM_INSERT);
              saveList.add(mso);
              mso = new MutSeaObject();
              mso.setHbmSea(bc,mso.SEA_HBM_INSERT);
              mso.setBlobStream(new ByteArrayInputStream(article.getBytes()),"content");
              saveList.add(mso);

           }
           title = rs.getString("title8");
           if(title != null && title.trim().length() > 0)
           {
              String article = rs.getString("article8");
              Userarticle uarticle = new Userarticle();
              Bcb bc = new Bcb();
              //bc.setContent(article);
              bc.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_BCB));
              uarticle.setContentid(bc.getId());
              uarticle.setCreatetime(new java.util.Date(System.currentTimeMillis()));
              uarticle.setHyid(inUser.getHyid());
              uarticle.setHyname(inUser.getUsername());
              uarticle.setId(SysCommonFunc.getSequenceIdForOracle(SysDefine.SEQ_HY_ARTICLE));
              uarticle.setLcname(inUser.getLcname());
              uarticle.setTitle(title.trim());
              mso = new MutSeaObject();
              mso.setHbmSea(uarticle,mso.SEA_HBM_INSERT);
              saveList.add(mso);
              mso = new MutSeaObject();
              mso.setHbmSea(bc,mso.SEA_HBM_INSERT);
              mso.setBlobStream(new ByteArrayInputStream(article.getBytes()),"content");
              saveList.add(mso);

           }

        }

        rs.close();

        ///查询会员设置信息
        rs = st.executeQuery("select * from hyset where hyid="+inUser.getHyid());
        if(rs.next())
        {
           String setbase= rs.getString("setbase");
           String setpic = rs.getString("setpic");
           String setmail= rs.getString("setmail");
           String setqq  = rs.getString("setqq");
           String setsjtel = rs.getString("setsjtel");
           String settel   = rs.getString("settel");
            setbase= setbase == null || setbase.trim().length()==0 ?"3":setbase.trim();
            setpic = setpic == null || setpic.trim().length()==0 ? "3":setpic.trim();
            setmail=setmail == null || setmail.trim().length()==0 ? "9":setmail.trim();
            setqq  = setqq == null || setqq.trim().length()==0 ?  "9":setqq.trim();
            setsjtel = setsjtel == null || setsjtel.trim().length() ==0 ? "9":setsjtel.trim();
            settel   = settel == null || settel.trim().length()==0 ?"9":settel.trim();
            Hyset hyset = new Hyset();
            hyset.setHyid(inUser.getHyid());
            hyset.setSetbase(new Integer(setbase));
            hyset.setSetmail(new Integer(setmail));
            hyset.setSetpic(new Integer(setpic));
            hyset.setSetqq(new Integer(setqq));
            hyset.setSetsjtel(new Integer(setsjtel));
            hyset.setSettel(new Integer(settel));
            mso = new MutSeaObject();
            mso.setHbmSea(hyset,mso.SEA_HBM_INSERT);
            saveList.add(mso);

        }
        rs.close();


        HbmOperator.SeaMutDataWithBlob(saveList);
        //System.out.println("import o");

     }catch(Exception e)
     {
        System.out.println("出错"+e.getMessage());
        e.printStackTrace();
        user.setIsdel(new Integer(3));
        try
        {
          HbmOperator.update(user);
        }catch(Exception ee)
        {
          System.exit(0);
          System.out.println("修复异常出错：程序退出"+ee.getMessage());
        }
     }
  }



  public Connection getConnection()
  {
    Connection conn = null;
    try
    {
      String server = "61.155.107.6";
      String port = "9433";
      String dbname = "infofanni";
      String user = "sa";
      String pass = "51lover";
      String driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
      String url = "jdbc:microsoft:sqlserver://" + server + ":" + port +
          ";DatabaseName=" + dbname + ";USER=" + user + ";PASSWORD=" + pass +
          "";
      Class.forName(driver);
      conn = DriverManager.getConnection(url);
    }catch(Exception e)
    {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  public Hashtable getDic()
  {
     Hashtable ht = new Hashtable();
     List dicList = HbmOperator.list("from Dic as o ");
     for(int i=0;i < dicList.size();i ++)
     {
        Dic dic = (Dic)dicList.get(i);
        ht.put(dic.getContent(),dic.getDicnumber());
     }
     return ht;
  }

  public String getYyap(String s)
  {

        StringBuffer sb = new StringBuffer("00000000000");
        if(s == null)
          s = "";
        String[] array = s.split(",");
        if(array != null)
        {
          for(int i =0;i < array.length;i ++)
          {

            if(i < sb.length() && array[i] != null && array[i].equals("1") )
            {
              sb.setCharAt(i,'1');
            }
          }
        }

        return sb.toString();

  }

  public String getXgtz(String s)
  {
     StringBuffer sb = new StringBuffer("000000000000000");
     if(s == null)
       s = "";
     String[] array = s.split(",");
     if(array != null)
     {
       for(int i =0;i < array.length;i ++)
       {

         if(i < sb.length() && array[i] != null && array[i].equals("1") )
         {
           sb.setCharAt(i,'1');
         }
       }
     }

     return sb.toString();
  }

  public String getJyyx(String jyyx)
  {
     StringBuffer sb = new StringBuffer("10000000");
     //System.out.println(sb.length());
     if(jyyx == null )
       return sb.toString();
     for(int i=0;i < jyyx.length();i ++)
     {
        if(jyyx.charAt(i) == '1')
        {
          sb.setCharAt(1,'1');
        }else if(jyyx.charAt(i) == '2')
        {
          sb.setCharAt(2,'1');
        }else if(jyyx.charAt(i) == '3')
        {
          sb.setCharAt(3,'1');
        }else if(jyyx.charAt(i) == '5')
        {
          sb.setCharAt(4,'1');
        }else if(jyyx.charAt(i) == '7')
        {
          sb.setCharAt(5,'1');
        }else if(jyyx.charAt(i) == '8')
        {
          sb.setCharAt(6,'1');
        }else if(jyyx.charAt(i) == '9')
        {
          sb.setCharAt(7,'1');
        }

     }
     return sb.toString();
  }

  public String getDicValue(String v,Hashtable ht)
  {
     String s = "";
     if(v == null)
       v = "";
     String vv = (String)ht.get(v);

     return vv==null?s:vv;
  }


  public String getNumberValue(String value)
  {
      String s = "０１２３４５６７８９";
      if(value == null)
        return "";
      StringBuffer sb = new StringBuffer(value);
      for(int i=0;i <sb.length();i ++)
      {
         char temp = sb.charAt(i);
         int index = s.indexOf(temp+"");
         if(index > -1)
           sb.setCharAt(i,(index+"").charAt(0));
      }


      return sb.toString();
  }



}
