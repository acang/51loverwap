package com.web.common;

import com.web.bean.QueryRecord;
import com.web.servlet.InitEnvironment;

import hibernate.db.HbmUtil;
import javazoom.upload.MultipartFormDataRequest;
import net.sf.hibernate.Session;

import org.apache.commons.beanutils.DynaBean;

import javax.servlet.http.HttpServletRequest;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Vector;

import com.common.SysDefine;

import java.io.*;
import java.util.regex.Matcher;  
import java.util.regex.Pattern; 


public class SysCommonFunc {
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
	private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
  public SysCommonFunc() {
  }
  /**
   * 将request.getParameter("$")作字符串处理
   *
   * @param request HttpServletRequest
   * @param name    String 待转换参数
   * @return String 如果request.getParameter("$")为空则返回空字符串
   */
  public static String getStrParameter(HttpServletRequest request,
                                       String name) {

      if (name == null || name.equals("")) {
          return "";
      }
      String param = request.getParameter(name);
      if (param == null)
          return "";

      try {
//            P.p("request.Method=" + request.getMethod());
          /*如果为GET方式,则进行“iso-8859-1”的转换，解决中文乱码问题*/
          if (request.getMethod().toLowerCase().equals("get")) {
              String encode = InitEnvironment.getSystemProValue(
                      "requestgetcode");
              if (encode != null && encode.length() > 0)
                  param = new String(request.getParameter(name).
                          getBytes(encode));
          }
      }
      catch (Exception e) {
          e.printStackTrace();
          return "";
      }

      return param;
  }

  public static String getMultiStrParameter(MultipartFormDataRequest request,
                                            String name) {
      String param = "";
      if (name == null || name.equals("")) {
          return param;
      }
      if (request.getParameter(name) == null) {
          return param;
      }

      try {
          param = new String(request.getParameter(name).trim().getBytes(
                  "iso-8859-1"));

      }
      catch (Exception e) {
          e.printStackTrace();
          return "";
      }

      return param;
  }


  public  static Long getSequenceIdForOracle(String sequencename) {
      long ps = 0;
      Connection conn = null;
      Statement st = null;
      ResultSet rs = null;
      try {
          Session session = HbmUtil.getActiveSession();
          conn = session.connection();
          st = conn.createStatement();
          rs = st.executeQuery("select " + sequencename +
                  ".nextval as snumber from dual");
          if (rs.next()) {
              ps = rs.getLong(1);
          }
          rs.close();
          st.close();
      }
      catch (Exception e) {
          System.out.println(e.getMessage());
          if(rs != null)
          {
            try {
              rs.close();
            }
            catch (Exception ee) {}
          }
          if(st != null)
          {
            try {
              st.close();
            }
            catch (Exception ee) {}
          }

      }
      finally {

          HbmUtil.closeSession();
      }
      return ps == 0 ? null : new Long(ps);
  }


  public  static Long getNumberForSql(String sql) {
      long ps = 0;
      Connection conn = null;
      Statement st = null;
      ResultSet rs = null;
      try {
          Session session = HbmUtil.getActiveSession();
          conn = session.connection();
          st = conn.createStatement();
          rs = st.executeQuery(sql);
          if (rs.next()) {
              ps = rs.getLong(1);
          }
          rs.close();
          st.close();
      }
      catch (Exception e) {
          System.out.println(e.getMessage());
          if(rs != null)
          {
            try {
              rs.close();
            }
            catch (Exception ee) {}
          }
          if(st != null)
          {
            try {
              st.close();
            }
            catch (Exception ee) {}
          }

      }
      finally {

          HbmUtil.closeSession();
      }
      return new Long(ps);
  }
  
  public  static Double getDoubleForSql(String sql) {
	  Double ps = 0.00;
      Connection conn = null;
      Statement st = null;
      ResultSet rs = null;
      try {
          Session session = HbmUtil.getActiveSession();
          conn = session.connection();
          st = conn.createStatement();
          rs = st.executeQuery(sql);
          if (rs.next()) {
              ps = rs.getDouble(1);
          }
          rs.close();
          st.close();
      }
      catch (Exception e) {
          System.out.println(e.getMessage());
          if(rs != null)
          {
            try {
              rs.close();
            }
            catch (Exception ee) {}
          }
          if(st != null)
          {
            try {
              st.close();
            }
            catch (Exception ee) {}
          }

      }
      finally {

          HbmUtil.closeSession();
      }
      return new Double(ps);
  }

  public  static Long getNumberForSql(String sql,String dbname) {
      long ps = 0;
      Connection conn = null;
      Statement st = null;
      ResultSet rs = null;
      try {
          Session session = HbmUtil.getActiveSession(dbname);
          conn = session.connection();
          st = conn.createStatement();
          rs = st.executeQuery(sql);
          if (rs.next()) {
              ps = rs.getLong(1);
          }
          rs.close();
          st.close();
      }
      catch (Exception e) {
          System.out.println(e.getMessage());
          if(rs != null)
          {
            try {
              rs.close();
            }
            catch (Exception ee) {}
          }
          if(st != null)
          {
            try {
              st.close();
            }
            catch (Exception ee) {}
          }

      }
      finally {

          HbmUtil.closeSession(dbname);
      }
      return new Long(ps);
  }


  public static int getNumberFromString(String s,int deNumber)
  {
     int i = deNumber;
     if(s != null && s.length() > 0)
     {
        try
        {
           i =  Integer.parseInt(s);
        }catch(Exception e)
        {
           System.out.println("getnumb"+e.getMessage());
        }
     }
     return i;
  }
  public static String getStringFromBlob(Blob blob) {
      if (blob == null) {
          return "";
      }
      //StringBuffer sb = new StringBuffer();
      try {

          byte[] array = new byte[1000];
          InputStream in = blob.getBinaryStream();

          /**
           * hjs 为了修改乱码的问题
           */
          //保存每次读取的字节列表
          Vector v = new Vector();

          //保存每次读取的字节长度
          Vector v1 = new Vector();
          int len = in.read(array, 0, 1000);

          //保存所有字节的长度
          int alllen = 0;
          while (len > 0) {
              //sb.append(new String(array, 0, len));
              alllen = alllen + len;
              v.add(array);
              v1.add(new Integer(len));
              array = new byte[1000];
              len = in.read(array, 0, 1000);
          }
          if (alllen > 0 && v.size() > 0) {
              byte[] arraytemp = new byte[alllen];
              int index = 0;
              for (int i = 0; i < v.size(); i++) {
                  int lentemp = ((Integer) v1.get(i)).intValue();
                  byte[] arraytt = (byte[]) v.get(i);
                  System.arraycopy(arraytt, 0, arraytemp, index, lentemp);
                  index = index + lentemp;
              }
              return new String(arraytemp);

          }

      }
      catch (Exception e) {
          System.out.println("得到blob字符串出错：" + e.getMessage());
      }
      return "";
      //return new String(sb.toString().getBytes());
  }

  public static String getDateStep(long time)
  {
     StringBuffer sb = new StringBuffer();
     long ctime = time;
     long allsecend = ctime/1000;
     long secend = allsecend % 60;
     long allmi = allsecend / 60;
     long mi = allmi % 60;
     long allh = allmi / 60;

       sb.append(allh +":");
       if(mi < 10)
         sb.append("0");
       sb.append(mi +":");
       if(secend < 10)
         sb.append("0");
       sb.append(secend);
     return sb.toString();
  }

  public static String getPhotoPath()
  {
    String path = InitEnvironment.getSystemProValue("webPath");
    String afilepath = path + File.separator+InitEnvironment.getSystemProValue(SysDefine.ENV_ANNEXPATH_PATH);
    return afilepath;
  }

  public static String getPhotoSmalPath()
  {
    String path = InitEnvironment.getSystemProValue("webPath");
    String afilepath = path + File.separator+InitEnvironment.getSystemProValue(SysDefine.ENV_ANNEXPATH_PATH);
    return afilepath+"small";
  }

  public static String getPhotoView()
  {
//     return InitEnvironment.getSystemProValue(SysDefine.ENV_ANNEXPATH_PATH);
     return "http://www.51lover.org/upload/hyimg";
  }

  public static String getPhotoSmallView()
  {
//    return InitEnvironment.getSystemProValue(SysDefine.ENV_ANNEXPATH_PATH)+"small";
    return "http://www.51lover.org/upload/hyimg"+"small";
  }

  public static String textToHtml(String s)
  {
     String txt = s;
     if(s == null)
       return "";

             txt=txt.replaceAll("&","&amp;");
             txt=txt.replaceAll("<","&lt;");
             txt=txt.replaceAll(">","&gt;");
             txt=txt.replaceAll("\"","&quot;");
             txt=txt.replaceAll("'","&#146;");
             txt=txt.replaceAll(" ","&nbsp;");
             txt=txt.replaceAll("\n","<br>");
      return txt;
  }
  public static String htmlToText(String s)
  {
     String txt = s;
     if(s == null)
       return "";

             txt=txt.replaceAll("&amp;","&");
             txt=txt.replaceAll("&lt;","<");
             txt=txt.replaceAll("&gt;",">");
             txt=txt.replaceAll("&quot;","\"");
             txt=txt.replaceAll("&#146;","'");
             txt=txt.replaceAll("&nbsp;"," ");
             txt=txt.replaceAll("<br>","\n");
      return txt;
  }
  
  /** 
   * @param htmlStr 
   * @return 
   *  删除Html标签 
   */  
  public static String delHTMLTag(String htmlStr) {  
      Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
      Matcher m_script = p_script.matcher(htmlStr);  
      htmlStr = m_script.replaceAll(""); // 过滤script标签  

      Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
      Matcher m_style = p_style.matcher(htmlStr);  
      htmlStr = m_style.replaceAll(""); // 过滤style标签  

      Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
      Matcher m_html = p_html.matcher(htmlStr);  
      htmlStr = m_html.replaceAll(""); // 过滤html标签  

      Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
      Matcher m_space = p_space.matcher(htmlStr);  
      htmlStr = m_space.replaceAll(""); // 过滤空格回车标签  
      return htmlStr.trim(); // 返回文本字符串  
  }  
    
  public static String getTextFromHtml(String htmlStr){  
      htmlStr = delHTMLTag(htmlStr);  
      htmlStr = htmlStr.replaceAll("&nbsp;", "");  
      //htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);  
      return htmlStr;  
  }  

}
