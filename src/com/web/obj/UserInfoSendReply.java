package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HCG v1.0, create by dc, 2011-11-22 01:59:14

 *        @hibernate.class
 *         table="USER_BJD"
 *     
 */
public class UserInfoSendReply implements Serializable {

    /** persistent field */
    private Long id;

    /** persistent field */
    private Long hyid;
    
    /** persistent field */
    private String username;
    
    /** persistent field */
    private String content;
    
    /** persistent field */
    private Date hdate;
    
    /** persistent field */
    private Integer sendorreply;
    
    /** persistent field */
    private String mobileoremailc;
    
    /** persistent field */
    private Integer mobileoremail;
    
    /** persistent field */
    private String sender;
    
    /** persistent field */
    private Integer sendtype;
    
    /** persistent field */
    private Integer status=0;
    
    /** persistent field */
    private Integer sendobject=0;
    
    /** persistent field */
    private Integer sendnumber=0;

    /** full constructor */
    public UserInfoSendReply(Long hyid, String username,Date hdate,Integer sendorreply,String mobileoremailc,Integer mobileoremail,String sender,Integer sendtype,Integer status,Integer sendobject,Integer sendnumber) {
        this.hyid = hyid;
        this.username = username;
        this.hdate= hdate;
        this.sendorreply=sendorreply;
        this.mobileoremailc=mobileoremailc;
        this.mobileoremail=mobileoremail;
        this.sender=sender;
        this.sendtype=sendtype;
        this.status=status;
        this.sendobject=sendobject;
        this.sendnumber=sendnumber;
    }

    /** default constructor */
    public UserInfoSendReply() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHyid() {
		return hyid;
	}

	public void setHyid(Long hyid) {
		this.hyid = hyid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getHdate() {
		return hdate;
	}

	public void setHdate(Date hdate) {
		this.hdate = hdate;
	}

	public Integer getSendorreply() {
		return sendorreply;
	}

	public void setSendorreply(Integer sendorreply) {
		this.sendorreply = sendorreply;
	}

	public String getMobileoremailc() {
		return mobileoremailc;
	}

	public void setMobileoremailc(String mobileoremailc) {
		this.mobileoremailc = mobileoremailc;
	}

	public Integer getMobileoremail() {
		return mobileoremail;
	}

	public void setMobileoremail(Integer mobileoremail) {
		this.mobileoremail = mobileoremail;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Integer getSendtype() {
		return sendtype;
	}

	public void setSendtype(Integer sendtype) {
		this.sendtype = sendtype;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSendobject() {
		return sendobject;
	}

	public void setSendobject(Integer sendobject) {
		this.sendobject = sendobject;
	}

	public Integer getSendnumber() {
		return sendnumber;
	}

	public void setSendnumber(Integer sendnumber) {
		this.sendnumber = sendnumber;
	}
}
