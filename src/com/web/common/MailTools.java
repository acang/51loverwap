package com.web.common;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import javax.activation.*;
import java.io.*;

import com.jit.util.GetMinConnect;
import com.sun.mail.smtp.*;
import com.sun.mail.imap.*;
import sun.misc.BASE64Encoder;
import javax.mail.*;
import com.lover.mng.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.web.obj.*;
import org.apache.commons.beanutils.DynaBean;


public class MailTools
{
//    private MimeMessage mimeMsg; //MIME邮件对象
//    private Session session; //邮件会话对象
//    private Properties props; //系统属性
//    private boolean needAuth = false; //smtp是否需要认证
//    private String username = ""; //smtp认证用户名和密码
//    private String password = "";
//    private Multipart mp; //Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成//MimeMessage对象

//    public final static String FROM     = "service@51lover.org";
//    public final static String NICKNAME = "51交友中心";
//
//    public final static String USERNAME = "service@51lover.org";
//    public final static String PASSWORD = "123456";
//    public final static String MAILIP   = "58.222.0.190";



    //public final static String FROM = "<service@51lover.net>";
	public final static String FROM = "<service@51lover.org>";
    //public final static String NICKNAME = "51交友中心";
    public final static String NICKNAME = "www.51lover.org";

    public final static StringBuffer bodytext = new StringBuffer();

    public final static String USERNAME = "service@51lover.org";
    //public final static String PASSWORD = "huang2000780116";
    public final static String PASSWORD = "51loverservice";
    //public final static String MAILIP   = "61.155.5.31";
    public final static String MAILIP   = "61.155.107.6";

    /*public final static String  adds="<br><br><br><br><br><br><br>51交友中心网站<br>" +
            "中国AAA大型情感类交友网站<br>" +
            "找到异性伴侣，解除情感寂寞！<br>" +
            "客服电话：4000680001<br>" +
            "地址：江苏南京沿江开发区科创6号楼B栋<br>" +
            "（本邮件不能回复，有事请在<a href='http://www.51lover.org/kfzx.jsp'>网站客服中心留言）<br>";*/
    
    public final static String  adds="<br><br><br><br><br><br><br>www.51lover.org<br>" +
    "找到异性伴侣，解除情感寂寞！<br>" +
    "客服电话：4000680001<br>" +
    "地址：江苏南京沿江开发区科创6号楼B栋<br>" +
    "（本邮件不能回复，有事请在<a href='http://www.51lover.org/kfzx.jsp'>网站客服中心留言）<br>";
    
    public final static String  adds2="<br><br><br><br><br><br><br>www.51lover.org<br>" +
    "找到异性伴侣，解除情感寂寞！<br>" +
    "客服电话：4000680001<br>" +
    "地址：江苏南京沿江开发区科创6号楼B栋<br>";
    
    static {
      setSmtpHost("smtp");
    }

    public MailTools(String smtp)
    {
      //this.setSmtpHost(smtp);
//      this.createMimeMessage();
    }


    public static void setSmtpHost(String hostName)
    {
       System.out.println("设置系统属性：mail.smtp.host = "+hostName);
       System.out.println("设置smtp身份认证：mail.smtp.auth = ");

       Properties  props = System.getProperties(); //获得系统属性对象
       props.put("mail.smtp.host",hostName); //设置SMTP主机
       props.put("mail.smtp.auth", "true");
     }

     public static boolean reMail(String title,String content,String to)
     {
       SMTPTransport t = null;
       boolean rs = false;
       try
       {
         Properties props = System.getProperties();
         Session session = Session.getInstance(props, null);
         session.setDebug(false);

         Message msg = new MimeMessage(session);
         String from1 = javax.mail.internet.MimeUtility.encodeText(MailTools.NICKNAME);
//         System.out.println(from);
         //msg.setFrom(new InternetAddress(from1+MailTools.FROM, MailTools.NICKNAME));


         //String from2 = new BASE64Encoder().encode(MailTools.FROM.getBytes());
         //System.out.println(from1+from2);

         msg.setFrom(new InternetAddress(from1+MailTools.FROM));

         msg.setRecipients(Message.RecipientType.TO,
                           InternetAddress.parse(to, false));
         msg.setSubject(title);
         Multipart mp = new MimeMultipart();
         MimeBodyPart mbp = new MimeBodyPart();

       // 设定邮件内容的类型为 text/plain 或 text/html

         mbp.setContent(content+MailTools.adds,"text/html ;charset=GB2312");
         mp.addBodyPart(mbp);
         msg.setContent(mp);




         //msg.setHeader("X-Mailer", "sss");
         msg.setSentDate(new Date());

         t = (SMTPTransport) session.getTransport("smtp");

         t.connect(MailTools.MAILIP, MailTools.USERNAME, MailTools.PASSWORD);

         t.sendMessage(msg, msg.getAllRecipients());
         rs = true;
       }catch(Exception e)
       {
         System.out.println(e.getMessage());
         rs = false;
       }finally
       {
           if(t != null)
           {
             try
             {
               t.close();
             }catch(Exception ee)
             {

             }
           }
       }
       return rs;
     }
     
     public static boolean reMail2(String title,String content,String to)
     {
       SMTPTransport t = null;
       boolean rs = false;
       try
       {
         Properties props = System.getProperties();
         Session session = Session.getInstance(props, null);
         session.setDebug(false);

         Message msg = new MimeMessage(session);
         String from1 = javax.mail.internet.MimeUtility.encodeText(MailTools.NICKNAME);
//         System.out.println(from);
         //msg.setFrom(new InternetAddress(from1+MailTools.FROM, MailTools.NICKNAME));


         //String from2 = new BASE64Encoder().encode(MailTools.FROM.getBytes());
         //System.out.println(from1+from2);

         msg.setFrom(new InternetAddress(from1+MailTools.FROM));

         msg.setRecipients(Message.RecipientType.TO,
                           InternetAddress.parse(to, false));
         msg.setSubject(title);
         Multipart mp = new MimeMultipart();
         MimeBodyPart mbp = new MimeBodyPart();

       // 设定邮件内容的类型为 text/plain 或 text/html

         mbp.setContent(content+MailTools.adds2,"text/html ;charset=GB2312");
         mp.addBodyPart(mbp);
         msg.setContent(mp);




         //msg.setHeader("X-Mailer", "sss");
         msg.setSentDate(new Date());

         t = (SMTPTransport) session.getTransport("smtp");

         t.connect(MailTools.MAILIP, MailTools.USERNAME, MailTools.PASSWORD);

         t.sendMessage(msg, msg.getAllRecipients());
         rs = true;
       }catch(Exception e)
       {
         System.out.println(e.getMessage());
         rs = false;
       }finally
       {
           if(t != null)
           {
             try
             {
               t.close();
             }catch(Exception ee)
             {

             }
           }
       }
       return rs;
     }
     
     
     public static void recevie(){
    	 String imapserver = MAILIP; // 邮件服务器
         String user = USERNAME;
         String pwd = PASSWORD;     // 根据自已的密码修改
         Properties props = new Properties(); 
         props.setProperty("mail.smtp.host", MAILIP);        
         props.setProperty("mail.smtp.auth", "true"); 
         Session session = Session.getDefaultInstance(props,null);        
         URLName urlname = new URLName("pop3",MAILIP,110,null,USERNAME,PASSWORD); 
         try {
        	 Store store = session.getStore(urlname);
             store.connect();
             Folder folder = store.getFolder("INBOX");       
             folder.open(Folder.READ_WRITE);
             Message msgs[] = folder.getMessages();        
             int count = msgs.length; 
             System.out.println("邮件数量:"+count);  
             for(int i=0;i<count;i++)
             {
            	 MimeMessage rm;
            	 rm=(MimeMessage)msgs[i];
                 
                 //获取发送地址
                 InternetAddress[] address = (InternetAddress[]) rm.getFrom();
                 String from = address[0].getAddress();
                 if(from == null){
                     from = "";
                 }          
                 
                 //获取邮件主题
                 String subject = "";
                 subject = MimeUtility.decodeText(rm.getSubject());
                 if(subject == null){
                     subject = "";
                 }
                 System.out.println(subject);
                 
                 //获取邮件内容
                 bodytext.setLength(0);
                 getMailContent(msgs[i]);
                 //System.out.println(bodytext.toString());
                 
                 //将邮件内容写入发送/回复记录表和客服中心
                 List list=UserInfoSendReplyMng.getUserInfoSendReplyList(from,1);
             	 DynaBean dbtnew=null;
             	 if (list!=null){
             		for(int j =0;j < list.size();j ++){
             			dbtnew=(DynaBean)list.get(j);
             			Userinfo userinfo=new Userinfo();
            			userinfo=HYRegMng.getUserinfoByHyid(String.valueOf(dbtnew.get("hyid")));
	             		UserInfoSendReply newUserInfoSendReply=new UserInfoSendReply();
	             		newUserInfoSendReply.setHyid(Long.parseLong(String.valueOf(dbtnew.get("hyid"))));
	             		if (userinfo!=null){
	        				newUserInfoSendReply.setUsername(userinfo.getUsername());
	        			}else{
	        				newUserInfoSendReply.setUsername("");
	        			}
	             		newUserInfoSendReply.setContent(bodytext.toString());
	             		newUserInfoSendReply.setHdate(rm.getSentDate());
	             		newUserInfoSendReply.setSendorreply(1);
	             		newUserInfoSendReply.setMobileoremailc(from);
	             		newUserInfoSendReply.setMobileoremail(1);
	             		newUserInfoSendReply.setSendtype(0);
	             		UserInfoSendReplyMng.insertUserInfoSendReply(newUserInfoSendReply);
	             		
	             		Kfzx kfzx=new Kfzx(); 
	             		if (userinfo!=null){
	             			kfzx.setUsername(userinfo.getUsername());
	        			}else{
	        				kfzx.setUsername("");
	        			}
	            		kfzx.setTel("");
	            		kfzx.setEmail("");
	            		kfzx.setNtitle(subject);
	            		kfzx.setNtext(bodytext.toString());
	            		kfzx.setNdate(rm.getSentDate());
	            	    kfzx.setNtype(new Integer(0));
	            	    kfzx.setZtai(new Integer(0));
	            	    kfzx.setIpaddress(from);
	            	    kfzx.setDeletename("");
	            	    kfzx.setEmail2(from);
	            	    kfzx.setHyid(Long.parseLong(String.valueOf(dbtnew.get("hyid"))));
	            	    kfzx.setSource(1);
	            	    kfzx.setMobileoremail(1);
	            	    KFZXMng.addKfzx(kfzx);
             		}
             	}
                 //删除邮件
                 rm.setFlag(Flags.Flag.DELETED, true); 
             }
             folder.close(true);
             store.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
              
          
     }
     
     
     /**
      * 解析邮件，将得到的邮件内容保存到一个stringBuffer对象中，解析邮件 主要根据MimeType的不同执行不同的操作，一步一步的解析
      * @param part
      * @throws MessagingException
      * @throws IOException
      */
     public static void getMailContent(Part part) throws MessagingException, IOException{
         
         String contentType = part.getContentType();
         int nameindex = contentType.indexOf("name");
         boolean conname = false;
         if(nameindex != -1){
             conname = true;
         }
         if(part.isMimeType("text/*")&&!conname){
             bodytext.append((String)part.getContent());
         }else if(part.isMimeType("multipart/*")){
             Multipart multipart = (Multipart) part.getContent();
             int count = multipart.getCount();
             for(int i=1;i<count;i++){
                 //getMailContent(multipart.getBodyPart(i));
            	 bodytext.append(multipart.getBodyPart(i).getContent().toString());
             }
         }
         
     }

public static void main(String[] args)
{

try
{
    long test = System.currentTimeMillis();
     boolean b =     MailTools.reMail("测试", "<br>测试fdsafdsafd中文标题111111111<br>fdsafdsaf<br>", "hwjxsy@163.com");
     System.out.println(b);
     System.out.println((System.currentTimeMillis()-test)/1000);
       }catch(Exception e)
       {
         e.printStackTrace();
       }
//     String from = "service@51lover.org";
//     //String sendname = "51交友中心";
//
//
//
//     String username =  "service@51lover.org";
//     String password = "123456";
//     String ip = "58.222.0.190";
//     String to = "hwjxsy@163.com";
//
//
//   String mailbody = "hello. This is a testing.";
//   MailTools themail = new MailTools(ip);
//   themail.setNeedAuth(true);
//   if(themail.setSubject("测试") == false)
//     return;
//   if(themail.setBody(mailbody) == false)
//     return;
//   if(themail.setTo(to) == false)
//     return;
//   if(themail.setFrom(from) == false)
//     return;
//   themail.setNamePass(username,password); //用户名与密码
//   if(themail.sendout() == false)
//     return;
}

}





