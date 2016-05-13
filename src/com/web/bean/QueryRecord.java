package com.web.bean;

import hibernate.db.HbmUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.beanutils.ResultSetDynaClass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class QueryRecord
{
  //added by xiangqin
  static Log log = LogFactory.getLog(QueryRecord.class);
  //static Logger log = Logger.getLogger(QueryRecord.class.toString());	
	
  public static List query(String sql)
  {
    List list = new ArrayList();
    Session session = null;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      session = HbmUtil.getActiveSession();
      conn = session.connection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      ResultSetDynaClass rsdc = new ResultSetDynaClass(rs);
      Iterator rows = rsdc.iterator();
      while (rows.hasNext()) {
        DynaBean dynaBean = (DynaBean)rows.next();
        DynaBean lazyDynaBean = new LazyDynaBean();
        DynaProperty[] propertys = dynaBean.getDynaClass().getDynaProperties();
        for (int j = 0; j < propertys.length; j++) {
          DynaProperty property = propertys[j];
          if (dynaBean.get(property.getName()) != null)
            lazyDynaBean.set(property.getName(), dynaBean.get(property.getName()));
        }
        list.add(lazyDynaBean);
      }
      rs.close();
      stmt.close();
    }
    catch (Exception e)
    {
      System.err.println(sql);
      log.debug(e);
      //e.printStackTrace();
      try
      {
        if (rs != null)
          rs.close();
      } catch (Exception eee) {
    	  log.error(eee);
      }
      try {
        if (stmt != null)
          stmt.close();
      } catch (Exception eee) {
    	  log.error(eee);
    	  
      }
      return null;
    } 
    finally {
      HbmUtil.closeSession();
    }

    if (list.size() > 1000) {
      System.err.println("查询记录数超长:" + list.size());
      System.err.println("异常sql!!!!!!!!!!\n" + sql);
    }

    return list;
  }
  
  public static int queryCount(String sql)
  {
    Session session = null;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      session = HbmUtil.getActiveSession();
      conn = session.connection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      ResultSetDynaClass rsdc = new ResultSetDynaClass(rs);
      Iterator rows = rsdc.iterator();
      while (rows.hasNext()) {
        DynaBean dynaBean = (DynaBean)rows.next();
        DynaBean lazyDynaBean = new LazyDynaBean();
        DynaProperty[] propertys = dynaBean.getDynaClass().getDynaProperties();
        for (int j = 0; j < propertys.length; j++) {
          DynaProperty property = propertys[j];
          if (dynaBean.get(property.getName()) != null){
        	  String value = dynaBean.get(property.getName()).toString();
        	  return Integer.parseInt(value);
          }
        }
      }
      rs.close();
      stmt.close();
    }
    catch (Exception e)
    {
      System.err.println(sql);
      log.debug(e);
      //e.printStackTrace();
      try
      {
        if (rs != null)
          rs.close();
      } catch (Exception eee) {
    	  log.error(eee);
      }
      try {
        if (stmt != null)
          stmt.close();
      } catch (Exception eee) {
    	  log.error(eee);
    	  
      }
      return 0;
    }
    
    finally {
      HbmUtil.closeSession();
    }

    return 0;
  }
  

  public static QueryResult queryByDynaResultSet(String sql, int pagesize, int pagenum)
  {
    QueryResult qr = new QueryResult();
    List resultList = new Vector();

    qr.pageNum = pagenum;
    qr.pageSize = pagesize;
    qr.resultList = resultList;
    if ((pagenum < 1) || (pagesize < 1))
      return qr;
    Statement smt = null;
    ResultSet rs_temp = null;
    try
    {
      Connection conn = HbmUtil.getActiveSession().connection();
      smt = conn.createStatement(1004, 1007);

      String countSql = "select count(*) from (" + sql + ")";
      rs_temp = smt.executeQuery(countSql);
      if (rs_temp.next())
      {
        qr.rowCount = rs_temp.getInt(1);
      }
      qr.pageCount = ((qr.rowCount + pagesize) / pagesize);
      if (qr.rowCount % pagesize == 0) {
        qr.pageCount -= 1;
      }
      rs_temp.close();

      int startn = (pagenum - 1) * pagesize;
      int endn = pagenum * pagesize + 1;

      String sqlQ = "select * from (select tempa.*,rownum as rn from (" + sql + ") tempa ) tempb where rn < " + endn + " and rn >" + startn;

      rs_temp = smt.executeQuery(sqlQ);
      ResultSetDynaClass rsdc = new ResultSetDynaClass(rs_temp);

      Iterator rows = rsdc.iterator();

      while (rows.hasNext()) {
        DynaBean dynaBean = (DynaBean)rows.next();
        DynaBean lazyDynaBean = new LazyDynaBean();
        DynaProperty[] propertys = dynaBean.getDynaClass().getDynaProperties();
        for (int j = 0; j < propertys.length; j++) {
          DynaProperty property = propertys[j];
          if (dynaBean.get(property.getName()) != null)
            lazyDynaBean.set(property.getName(), dynaBean.get(property.getName()));
        }
        resultList.add(lazyDynaBean);
      }

      rs_temp.close();
      smt.close();

      if (resultList.size() > 10000) {
        System.err.println("查询记录数超长:" + resultList.size());
        System.err.println("异常sql!!!!!!!!!!\n" + sqlQ);
      }
    }
    catch (Exception e) {
      System.err.println(sql);
      //e.printStackTrace();
      log.error(e);
      try
      {
        if (rs_temp != null)
          rs_temp.close();
      } catch (Exception eee) {
    	  log.error(eee);
      }
      try {
        if (smt != null)
          smt.close();
      }
      catch (Exception eee)
      {
    	  log.error(eee);
    	 // eee.getStackTrace();
      }
    } finally {
      HbmUtil.closeSession();
    }

    return qr;
  }

  public static QueryResult queryByHbm(String hsql, int pagesize, int pagenum, boolean statAllcount, int allCount, String dbname)
  {
    Session session = null;
    List objectList = new Vector();
    QueryResult qr = new QueryResult();
    qr.pageSize = pagesize;
    qr.pageNum = pagenum;
    qr.resultList = objectList;
    // added by xiangqin
    String tmp= "";
    if ((pagenum < 1) || (pagesize < 1))
      return qr;
    try {
      session = HbmUtil.getActiveSession(dbname);

      if (statAllcount)
      {
        int orderpoint = hsql.toLowerCase().indexOf("order by");
        if (orderpoint > 0) {
          tmp = hsql.substring(0, orderpoint - 1);
        }
        else {
          tmp = hsql;
        }
        // added by xiangqin
        tmp = tmp.substring(tmp.toLowerCase().indexOf("from"));
        //String tmp = tmp.substring(tmp.toLowerCase().indexOf("from"));
        String totalSql = "select count(*) " + tmp + "";
        List list = session.createQuery(totalSql).list();
        if ((list != null) && (list.size() > 0)) {
          Integer tI = (Integer)list.get(0);
          qr.rowCount = tI.intValue();
        }
      }
      else {
        qr.rowCount = allCount;
      }
      qr.pageCount = ((qr.rowCount + pagesize) / pagesize);
      if (qr.rowCount % pagesize == 0) {
        qr.pageCount -= 1;
      }

      Query q = session.createQuery(hsql);
      q.setFirstResult((pagenum - 1) * pagesize);
      q.setMaxResults(pagesize);

      List listtmp = q.list();
      if (listtmp != null) {
        qr.resultList = listtmp;
      }

      if (listtmp.size() > 1000) {
        System.err.println("查询记录数超长:" + listtmp.size());
        System.err.println("异常sql!!!!!!!!!!\n" + hsql + " and pagesize=" + pagesize);
      }

    }
    catch (Exception e)
    {
      log.error(e);
    	//e.printStackTrace();
      System.out.println("查询出错：" + e.getMessage());
    } finally {
      HbmUtil.closeSession(dbname);
    }
    return qr;
  }

  public static QueryResult queryByHbm(String hsql, int pagesize, int pagenum, boolean statAllcount, int allCount)
  {
    Session session = null;
    List objectList = new Vector();
    QueryResult qr = new QueryResult();
    qr.pageSize = pagesize;
    qr.pageNum = pagenum;
    qr.resultList = objectList;
    //added by xiangqin
    String tmp = "";
    if ((pagenum < 1) || (pagesize < 1))
      return qr;
    try {
      session = HbmUtil.getActiveSession();

      if (statAllcount)
      {
        int orderpoint = hsql.toLowerCase().indexOf("order by");
        if (orderpoint > 0) {
          tmp = hsql.substring(0, orderpoint - 1);
        }
        else {
          tmp = hsql;
        }
        //added by xiangqin
        tmp = tmp.substring(tmp.toLowerCase().indexOf("from"));
        //String tmp = tmp.substring(tmp.toLowerCase().indexOf("from"));
        String totalSql = "select count(*) " + tmp + "";
        List list = session.createQuery(totalSql).list();
        if ((list != null) && (list.size() > 0)) {
          Integer tI = (Integer)list.get(0);
          qr.rowCount = tI.intValue();
        }
      }
      else {
        qr.rowCount = allCount;
      }
      qr.pageCount = ((qr.rowCount + pagesize) / pagesize);
      if (qr.rowCount % pagesize == 0) {
        qr.pageCount -= 1;
      }

      Query q = session.createQuery(hsql);
      q.setFirstResult((pagenum - 1) * pagesize);
      q.setMaxResults(pagesize);

      List listtmp = q.list();
      if (listtmp != null) {
        qr.resultList = listtmp;
      }

      if (listtmp.size() > 1000) {
        System.err.println("查询记录数超长:" + listtmp.size());
        System.err.println("异常sql!!!!!!!!!!\n" + hsql + " and pagesize=" + pagesize);
      }

    }
    catch (Exception e)
    {
      log.error(e);
    	//e.printStackTrace();
      System.out.println("查询出错：" + e.getMessage());
    } finally {
      HbmUtil.closeSession();
    }
    return qr;
  }

  public static QueryResult queryByHbm(String hsql, int pagesize, int pagenum, String dbname)
  {
    Session session = null;
    List objectList = new Vector();
    QueryResult qr = new QueryResult();
    qr.pageSize = pagesize;
    qr.pageNum = pagenum;
    qr.resultList = objectList;
    //added by xiangqin
    String tmp = "";
    if ((pagenum < 1) || (pagesize < 1))
      return qr;
    try {
      session = HbmUtil.getActiveSession(dbname);

      int orderpoint = hsql.toLowerCase().indexOf("order by");
      if (orderpoint > 0)
        tmp = hsql.substring(0, orderpoint - 1);
      else {
        tmp = hsql;
      }
      // added by xiangqin
      tmp = tmp.substring(tmp.toLowerCase().indexOf("from"));
      //String tmp = tmp.substring(tmp.toLowerCase().indexOf("from"));
      String totalSql = "select count(*) " + tmp + "";
      List list = session.createQuery(totalSql).list();
      if ((list != null) && (list.size() > 0))
      {
        Integer tI = (Integer)list.get(0);
        qr.rowCount = tI.intValue();
      }

      qr.pageCount = ((qr.rowCount + pagesize) / pagesize);
      if (qr.rowCount % pagesize == 0) {
        qr.pageCount -= 1;
      }

      Query q = session.createQuery(hsql);
      q.setFirstResult((pagenum - 1) * pagesize);
      q.setMaxResults(pagesize);

      List listtmp = q.list();
      if (listtmp != null) {
        qr.resultList = listtmp;
      }

      if (listtmp.size() > 1000) {
        System.err.println("查询记录数超长:" + listtmp.size());
        System.err.println("异常sql!!!!!!!!!!\n" + hsql + " and pagesize=" + pagesize);
      }

    }
    catch (Exception e)
    {
    	log.error(e);
      //e.printStackTrace();
      System.out.println("查询出错：" + e.getMessage());
    } finally {
      HbmUtil.closeSession(dbname);
    }
    return qr;
  }

  public static QueryResult queryByHbm(String hsql, int pagesize, int pagenum)
  {
    Session session = null;
    List objectList = new Vector();
    QueryResult qr = new QueryResult();
    qr.pageSize = pagesize;
    qr.pageNum = pagenum;
    qr.resultList = objectList;
    //added by xiangqin
    String tmp = "";
    if ((pagenum < 1) || (pagesize < 1))
      return qr;
    try {
      session = HbmUtil.getActiveSession();

      int orderpoint = hsql.toLowerCase().indexOf("order by");
      if (orderpoint > 0)
        tmp = hsql.substring(0, orderpoint - 1);
      else {
        tmp = hsql;
      }
      // added by xiangqin
      tmp = tmp.substring(tmp.toLowerCase().indexOf("from"));
      //String tmp = tmp.substring(tmp.toLowerCase().indexOf("from"));
      String totalSql = "select count(*) " + tmp + "";
      List list = session.createQuery(totalSql).list();
      if ((list != null) && (list.size() > 0))
      {
        Integer tI = (Integer)list.get(0);
        qr.rowCount = tI.intValue();
      }

      qr.pageCount = ((qr.rowCount + pagesize) / pagesize);
      if (qr.rowCount % pagesize == 0) {
        qr.pageCount -= 1;
      }

      Query q = session.createQuery(hsql);
      q.setFirstResult((pagenum - 1) * pagesize);
      q.setMaxResults(pagesize);

      List listtmp = q.list();
      if (listtmp != null) {
        qr.resultList = listtmp;
      }

      if (listtmp.size() > 1000) {
        System.err.println("查询记录数超长:" + listtmp.size());
        System.err.println("异常sql!!!!!!!!!!\n" + hsql + " and pagesize=" + pagesize);
      }

    }
    catch (Exception e)
    {
    	log.error(e);
      //e.printStackTrace();
      System.out.println("查询出错：" + e.getMessage());
    } finally {
      HbmUtil.closeSession();
    }
    return qr;
  }
}