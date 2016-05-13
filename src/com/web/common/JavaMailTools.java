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
import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaMailTools{
	private MimeMessage msg = null;
    private String saveAttchPath = "";
    private StringBuffer bodytext = new StringBuffer();
    private String dateformate = "yy-MM-dd HH:mm";
    
    public JavaMailTools(MimeMessage msg){
        this.msg = msg;
        }
    public void setMsg(MimeMessage msg) {
        this.msg = msg;
    }
    
    /**
     * 获取发送邮件者地址
     * @return
     * @throws MessagingException
     */
    public String getFromaddr() throws MessagingException{
        InternetAddress[] address = (InternetAddress[]) msg.getFrom();
        String from = address[0].getAddress();
        if(from == null){
            from = "";
        }
        return from;
    }
    
    /**
     * 获取发送邮件者信息
     * @return
     * @throws MessagingException
     */
    public String getFrom() throws MessagingException{
        InternetAddress[] address = (InternetAddress[]) msg.getFrom();
        String from = address[0].getAddress();
        if(from == null){
            from = "";
        }
        String personal = address[0].getPersonal();
        if(personal == null){
            personal = "";
        }
        String fromaddr = personal +"<"+from+">";
        return fromaddr;
    }
    
    /**
     * 获取邮件收件人，抄送，密送的地址和信息。根据所传递的参数不同 "to"-->收件人,"cc"-->抄送人地址,"bcc"-->密送地址
     * @param type
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public String getMailAddress(String type) throws MessagingException, UnsupportedEncodingException{
        String mailaddr = "";
        String addrType = type.toUpperCase();
        InternetAddress[] address = null;
        
        if(addrType.equals("TO")||addrType.equals("CC")||addrType.equals("BCC")){
            if(addrType.equals("TO")){
                address = (InternetAddress[]) msg.getRecipients(Message.RecipientType.TO);
            }
            if(addrType.equals("CC")){
                address = (InternetAddress[]) msg.getRecipients(Message.RecipientType.CC);
            }
            if(addrType.equals("BCC")){
                address = (InternetAddress[]) msg.getRecipients(Message.RecipientType.BCC);
            }
            
            if(address != null){
                for(int i=0;i<address.length;i++){
                    String mail = address[i].getAddress();
                    if(mail == null){
                        mail = "";
                    }else{
                        mail = MimeUtility.decodeText(mail);
                    }
                    String personal = address[i].getPersonal();
                    if(personal == null){
                        personal = "";
                    }else{
                        personal = MimeUtility.decodeText(personal);
                    }
                    String compositeto = personal +"<"+mail+">";
                    mailaddr += ","+compositeto; 
                }
                mailaddr = mailaddr.substring(1);
            }
        }else{
            throw new RuntimeException("Error email Type!");
        }
        return mailaddr;
    }
    
    /**
     * 获取邮件主题
     * @return
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    public String getSubject() throws UnsupportedEncodingException, MessagingException{
        String subject = "";
        subject = MimeUtility.decodeText(msg.getSubject());
        if(subject == null){
            subject = "";
        }
        return subject;
    }
    
    /**
     * 获取邮件发送日期
     * @return
     * @throws MessagingException
     */
    public String getSendDate() throws MessagingException{
        Date sendDate = msg.getSentDate();
        SimpleDateFormat smd = new SimpleDateFormat(dateformate);
        return smd.format(sendDate);
    }
    
    /**
     * 获取邮件正文内容
     * @return
     */
    public String getBodyText(){
        
        return bodytext.toString();
    }
    
    /**
     * 解析邮件，将得到的邮件内容保存到一个stringBuffer对象中，解析邮件 主要根据MimeType的不同执行不同的操作，一步一步的解析
     * @param part
     * @throws MessagingException
     * @throws IOException
     */
    public void getMailContent(Part part) throws MessagingException, IOException{
        
        String contentType = part.getContentType();
        int nameindex = contentType.indexOf("name");
        boolean conname = false;
        if(nameindex != -1){
            conname = true;
        }
        System.out.println("CONTENTTYPE:"+contentType);
        if(part.isMimeType("text/plain")&&!conname){
            bodytext.append((String)part.getContent());
        }else if(part.isMimeType("text/html")&&!conname){
            bodytext.append((String)part.getContent());
        }else if(part.isMimeType("multipart/*")){
            Multipart multipart = (Multipart) part.getContent();
            int count = multipart.getCount();
            for(int i=0;i<count;i++){
                getMailContent(multipart.getBodyPart(i));
            }
        }else if(part.isMimeType("message/rfc822")){
            getMailContent((Part) part.getContent()); 
        }
        
    }
    
    /**
     * 判断邮件是否需要回执，如需回执返回true，否则返回false
     * @return
     * @throws MessagingException
     */
    public boolean getReplySign() throws MessagingException{
        boolean replySign = false;
        String needreply[] = msg.getHeader("Disposition-Notification-TO");
        if(needreply != null){
            replySign = true;
        }
        return replySign;
    }
    
    /**
     * 获取此邮件的message-id
     * @return
     * @throws MessagingException
     */
    public String getMessageId() throws MessagingException{
        return msg.getMessageID();
    }
    
    /**
     * 判断此邮件是否已读，如果未读则返回false，已读返回true
     * @return
     * @throws MessagingException
     */
    public boolean isNew() throws MessagingException{
        boolean isnew = false;
        Flags flags = ((Message)msg).getFlags();
        Flags.Flag[] flag = flags.getSystemFlags();
        System.out.println("flags's length:"+flag.length);
        for(int i=0;i<flag.length;i++){
            if(flag[i]==Flags.Flag.SEEN){
                isnew = true;
                System.out.println("seen message .......");
                break;
            }
        }
        
        return isnew;
    }
    
    /**
     * 判断是是否包含附件
     * @param part
     * @return
     * @throws MessagingException
     * @throws IOException
     */
    public boolean isContainAttch(Part part) throws MessagingException, IOException{
        boolean flag = false;
        
        String contentType = part.getContentType();
        if(part.isMimeType("multipart/*")){
            Multipart multipart = (Multipart) part.getContent();
            int count = multipart.getCount();
            for(int i=0;i<count;i++){
                BodyPart bodypart = multipart.getBodyPart(i);
                String dispostion = bodypart.getDisposition();
                if((dispostion != null)&&(dispostion.equals(Part.ATTACHMENT)||dispostion.equals(Part.INLINE))){
                    flag = true;
                }else if(bodypart.isMimeType("multipart/*")){
                    flag = isContainAttch(bodypart);
                }else{
                    String conType = bodypart.getContentType();
                    if(conType.toLowerCase().indexOf("appliaction")!=-1){
                        flag = true;
                    }
                    if(conType.toLowerCase().indexOf("name")!=-1){
                        flag = true;
                    }
                }
            }
        }else if(part.isMimeType("message/rfc822")){
            flag = isContainAttch((Part) part.getContent());
        }
        
        return flag;
    }
    
    /**
     * 保存附件
     * @param part
     * @throws MessagingException
     * @throws IOException
     */
    public void saveAttchMent(Part part) throws MessagingException, IOException{
        String filename = "";
        if(part.isMimeType("multipart/*")){
            Multipart mp = (Multipart) part.getContent();
            for(int i=0;i<mp.getCount();i++){
                BodyPart mpart = mp.getBodyPart(i);
                String dispostion = mpart.getDisposition();
                if((dispostion != null)&&(dispostion.equals(Part.ATTACHMENT)||dispostion.equals(Part.INLINE))){
                    filename = mpart.getFileName();
                    if(filename.toLowerCase().indexOf("gb2312")!=-1){
                        filename = MimeUtility.decodeText(filename);
                    }
                    saveFile(filename,mpart.getInputStream());
                }else if(mpart.isMimeType("multipart/*")){
                    saveAttchMent(mpart);
                }else{
                    filename = mpart.getFileName();
                    if(filename != null&&(filename.toLowerCase().indexOf("gb2312")!=-1)){
                        filename = MimeUtility.decodeText(filename);
                    }
                    saveFile(filename,mpart.getInputStream());
                }
            }
            
        }else if(part.isMimeType("message/rfc822")){
            saveAttchMent((Part) part.getContent());
        }
    }
    /**
     * 获得保存附件的地址
     * @return
     */
    public String getSaveAttchPath() {
        return saveAttchPath;
    }
    /**
     * 设置保存附件地址
     * @param saveAttchPath
     */
    public void setSaveAttchPath(String saveAttchPath) {
        this.saveAttchPath = saveAttchPath;
    }
    /**
     * 设置日期格式
     * @param dateformate
     */
    public void setDateformate(String dateformate) {
        this.dateformate = dateformate;
    }
    /**
     * 保存文件内容
     * @param filename
     * @param inputStream
     * @throws IOException
     */
    private void saveFile(String filename, InputStream inputStream) throws IOException {
        String osname = System.getProperty("os.name");
        String storedir = getSaveAttchPath();
        String sepatror = "";
        if(osname == null){
            osname = "";
        }
        
        if(osname.toLowerCase().indexOf("win")!=-1){
            sepatror = "//";
            if(storedir==null||"".equals(storedir)){
                storedir = "d://temp";
            }
        }else{
            sepatror = "/";
            storedir = "/temp";
        }
        
        File storefile = new File(storedir+sepatror+filename);
        System.out.println("storefile's path:"+storefile.toString());
        
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        
        try {
            bos = new BufferedOutputStream(new FileOutputStream(storefile));
            bis = new BufferedInputStream(inputStream);
            int c;
            while((c= bis.read())!=-1){
                bos.write(c);
                bos.flush();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            bos.close();
            bis.close();
        }
    }
    
    public static void recevie(){
   	 	String imapserver = "61.155.5.31"; // 邮件服务器
        String user = "service@51lover.net";
        String pwd = "51loverservice";     // 根据自已的密码修改
        Properties props = new Properties(); 
        props.setProperty("mail.smtp.host", "61.155.5.31");        
        props.setProperty("mail.smtp.auth", "true"); 
        Session session = Session.getDefaultInstance(props,null);        
        URLName urlname = new URLName("pop3","61.155.5.31",110,null,"service","51loverservice"); 
        try {
       	 Store store = session.getStore(urlname);
            store.connect();
            Folder folder = store.getFolder("INBOX");       
            folder.open(Folder.READ_WRITE);
            Message msgs[] = folder.getMessages();        
            int count = msgs.length; 
            System.out.println("Message Count:"+count);  
            for(int i=0;i<count;i++)
            {
           	 MimeMessage rm;
           	 rm=(MimeMessage)msgs[i];
           	 
           	 //是否已读
           	 Flags flags = ((Message)rm).getFlags();
                Flags.Flag[] flag = flags.getSystemFlags();
                System.out.println("flags's length:"+flag.length);
                for(int j=0;j<flag.length;j++){
                    if(flag[j]==Flags.Flag.SEEN){
                        System.out.println("seen message .......");
                        break;
                    }
                }
                
                //获取发送地址
                InternetAddress[] address = (InternetAddress[]) rm.getFrom();
                String from = address[0].getAddress();
                if(from == null){
                    from = "";
                }
                String personal = address[0].getPersonal();
                if(personal == null){
                    personal = "";
                }
                String fromaddr = personal +"<"+from+">";
                System.out.println(fromaddr);
                
                //获取邮件主题
                String subject = "";
                subject = MimeUtility.decodeText(rm.getSubject());
                if(subject == null){
                    subject = "";
                }
                System.out.println(subject);
                
                //获取邮件内容
                //getMailContent(msgs[i]);
                //System.out.println(bodytext.toString());
                //删除邮件
                if (i==0)
                {
                rm.setFlag(Flags.Flag.DELETED, true);                  
                //rm.saveChanges(); 
                }
            }
            folder.close(true);
            store.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
}