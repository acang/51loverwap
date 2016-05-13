package com;

import java.sql.*;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-6-15
 * Time: 下午8:35
 * To change this template use File | Settings | File Templates.
 */
public class CountMoney {
    public static void main(String[] args){
        Properties props = new Properties();
        props.put("user", "aa");
        props.put("password", "51lover20130216db");
        props.put("remarksReporting", "true");

        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@61.155.5.31:1521:orcl", props);
            String sql = "select a.hyid , a.username  from bbsuser a where a.moneys>0";
            // 使用Statement对象查询数据库
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            long count = 0;
            String hyid = "";
            while (rs.next()) {
                count++;
                hyid = rs.getString("hyid");
                String bb_sql  ="select count(*) num from bbs  a where a.user_id = "+hyid +" and a.ischeck= 1 and a.re_id > 0 ";
                ps = con.prepareStatement(bb_sql);
                ResultSet bb_rs = ps.executeQuery();
                bb_rs.next();
                System.out.println("删回帖数= " + bb_rs.getString("num").toString());
                String update_sql = "update bbsuser set delresum ="+ new Long(bb_rs.getString("num").toString()) + " where hyid=" +hyid;
                ps.execute(update_sql);
                ps.close();
            }
            System.out.println("count = " + count);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
