package com;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


/**
 * Created with IntelliJ IDEA.
 * User: Aaron
 * Date: 15-5-10
 * Time: 下午7:31
 * To change this template use File | Settings | File Templates.
 */
public class GetPicPath {

    public static void Test() {
        Properties props = new Properties();
        props.put("user", "aa");
        props.put("password", "51lover20130216db");
        props.put("remarksReporting", "true");
        try {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@61.155.5.31:1521:orcl", props);
            String sql = "select a.hyid , b.userphoto1 , b.userphoto2 ,b.userphoto3 ,b.userphoto4 ,b.userphoto5 " +
                    "from userinfo a left join userother b on a.hyid=b.hyid " +
                    "where (b.userphoto1 ! = 'null'  or b.userphoto2 ! = 'null'  or b.userphoto3 ! = 'null' " +
                    "or b.userphoto4 ! = 'null' or b.userphoto5 ! = 'null')";
            // 使用Statement对象查询数据库
                       PreparedStatement ps = con.prepareStatement(sql);
                       ResultSet rs = ps.executeQuery();
            String hyid = "";
            String pic1 = "";
            String pic2 = "";
            String pic3 = "";
            String pic4 = "";
            String pic5 = "";
            FileOutputStream out = null;
            FileWriter fw = null;
            try {
                out = new FileOutputStream(new File("C:/test.txt"));
                long count = 0;
                while (rs.next()) {
                    count++;
                    hyid = rs.getString("hyid");
                    pic1 = rs.getString("userphoto1");
                    pic2 = rs.getString("userphoto2");
                    pic3 = rs.getString("userphoto3");
                    pic4 = rs.getString("userphoto4");
                    pic5 = rs.getString("userphoto5");
                    System.out.println(count + " = " + hyid);
                    out.write((hyid + "|" + pic1 + "|" + pic2 + "|" + pic3 + "|" + pic4 + "|" + pic5+"\r\n").getBytes());
                }
                out.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

                       //最后必须关闭ResultSet和Connection
                        rs.close();
                       con.close();

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public static void main2(String[] args) {
        try {
            FileReader reader = new FileReader("c://user.txt");
              BufferedReader br = new BufferedReader(reader);
           String str = null;
            long a =0;
            long b =0;
           while((str = br.readLine()) != null) {
               String[] paths = str.split("\\|");
               System.out.println("paths[0] = " + paths[0]);
               File one = new File("c:\\hyimg0408\\"+paths[1]);
               int bytesum = 0;
               int byteread = 0;
               if(one.exists()){
                   b++;
                   InputStream inStream = new FileInputStream("c:\\hyimg0408\\"+paths[1]); //读入原文件
                   File dirs = new File("c:\\0408\\"+paths[1].substring(0,8));
                   if(!dirs.exists()){
                        dirs.mkdirs();
                   }
                      File pic = new File("c:\\0408\\"+paths[1]);
                   if(!pic.exists()){
                       pic.createNewFile();
                   }

                   FileOutputStream fs = new FileOutputStream(pic);

                   byte[] buffer = new byte[3444];
                   while ((byteread = inStream.read(buffer)) != -1) {
                       bytesum += byteread; //字节数 文件大小
                       fs.write(buffer, 0, byteread);
                   }
                   inStream.close();
               }
               File two = new File("c:\\hyimg0408\\"+paths[2]);
               if(two.exists()){
                   b++;
                   InputStream inStream = new FileInputStream("c:\\hyimg0408\\"+paths[2]); //读入原文件
                   File dirs = new File("c:\\0408\\"+paths[2].substring(0,8));
                   if(!dirs.exists()){
                       dirs.mkdirs();
                   }
                   File pic = new File("c:\\0408\\"+paths[2]);
                   if(!pic.exists()){
                       pic.createNewFile();
                   }

                   FileOutputStream fs = new FileOutputStream(pic);
                   byte[] buffer = new byte[3444];
                   while ((byteread = inStream.read(buffer)) != -1) {
                       bytesum += byteread; //字节数 文件大小
                       fs.write(buffer, 0, byteread);
                   }
                   inStream.close();
               }
               File three = new File("c:\\hyimg0408\\"+paths[3]);
               if(three.exists()){
                   b++;
                   InputStream inStream = new FileInputStream("c:\\hyimg0408\\"+paths[3]); //读入原文件
                   File dirs = new File("c:\\0408\\"+paths[3].substring(0,8));
                   if(!dirs.exists()){
                       dirs.mkdirs();
                   }
                   File pic = new File("c:\\0408\\"+paths[3]);
                   if(!pic.exists()){
                       pic.createNewFile();
                   }

                   FileOutputStream fs = new FileOutputStream(pic);
                   byte[] buffer = new byte[3444];
                   while ((byteread = inStream.read(buffer)) != -1) {
                       bytesum += byteread; //字节数 文件大小
                       fs.write(buffer, 0, byteread);
                   }
                   inStream.close();
               }
               File four = new File("c:\\hyimg0408\\"+paths[4]);
               if(four.exists()){
                   b++;
                   InputStream inStream = new FileInputStream("c:\\hyimg0408\\"+paths[4]); //读入原文件
                   File dirs = new File("c:\\0408\\"+paths[4].substring(0,8));
                   if(!dirs.exists()){
                       dirs.mkdirs();
                   }
                   File pic = new File("c:\\0408\\"+paths[4]);
                   if(!pic.exists()){
                       pic.createNewFile();
                   }

                   FileOutputStream fs = new FileOutputStream(pic);
                   byte[] buffer = new byte[3444];
                   while ((byteread = inStream.read(buffer)) != -1) {
                       bytesum += byteread; //字节数 文件大小
                       fs.write(buffer, 0, byteread);
                   }
                   inStream.close();
               }
               File five =new File("c:\\hyimg0408\\"+paths[5]);
               if(five.exists()){
                   b++;
                   InputStream inStream = new FileInputStream("c:\\hyimg0408\\"+paths[5]); //读入原文件
                   File dirs = new File("c:\\0408\\"+paths[5].substring(0,8));
                   if(!dirs.exists()){
                       dirs.mkdirs();
                   }
                   File pic = new File("c:\\0408\\"+paths[5]);
                   if(!pic.exists()){
                       pic.createNewFile();
                   }

                   FileOutputStream fs = new FileOutputStream(pic);
                   byte[] buffer = new byte[3444];
                   while ((byteread = inStream.read(buffer)) != -1) {
                       bytesum += byteread; //字节数 文件大小
                       fs.write(buffer, 0, byteread);
                   }
                   inStream.close();
               }
               if(one.exists() || two.exists() || three.exists() || four.exists() || five.exists()){
                  a++;
               }
            }
            br.close();
           reader.close();

            System.out.println("共找到用户 = " + a+"个"+"   照片共"+b+"张");
        }

        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
