package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HCG v1.0, create by dc, 2011-11-07 02:41:45

 *        @hibernate.class
 *         table="SEND_SMS"
 *     
 */
public class SendSms implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String mobileNumber;

    /** persistent field */
    private long reportStatus;

    /** nullable persistent field */
    private String rerrorCode;

    /** nullable persistent field */
    private Date submitDate;

    /** nullable persistent field */
    private Date sysSubmitDate;

    /** nullable persistent field */
    private Date receiveDate;

    /** nullable persistent field */
    private String veryCode;
    
    private String userIp;

     /** persistent field */
    private long regstatus;
    
    private Long comp;
    
    private Long vcation;
    
    private String returnStr;
    
    private Integer fromclass;

    


	/** full constructor */
    public SendSms(Long id, String mobileNumber, long reportStatus, String rerrorCode, Date submitDate, Date sysSubmitDate, Date receiveDate, String veryCode,String userIp,Long comp, Long vcation) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.reportStatus = reportStatus;
        this.rerrorCode = rerrorCode;
        this.submitDate = submitDate;
        this.sysSubmitDate = sysSubmitDate;
        this.receiveDate = receiveDate;
        this.veryCode = veryCode;
        this.userIp = userIp;
        this.comp = comp;
        this.vcation = vcation;
    }

    
	/** default constructor */
    public SendSms() {
    }

    /** minimal constructor */
    public SendSms(Long id, String mobileNumber, long reportStatus,String userIp,long comp, long vcation) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.reportStatus = reportStatus;
        this.userIp = userIp;
        this.comp = comp;
        this.vcation = vcation;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Long"
     *             column="ID"
     *         
     */
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** 
     *            @hibernate.property
     *             column="MOBILE_NUMBER"
     *             length="200"
     *             not-null="true"
     *         
     */
    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     *            @hibernate.property
     *             column="REPORT_STATUS"
     *             length="10"
     *             not-null="true"
     *
     */
    public long getReportStatus() {
        return this.reportStatus;
    }

    public void setReportStatus(long reportStatus) {
        this.reportStatus = reportStatus;
    }

    /** 
     *            @hibernate.property
     *             column="R_ERROR_CODE"
     *             length="40"
     *         
     */
    public String getRerrorCode() {
        return this.rerrorCode;
    }

    public void setRerrorCode(String rerrorCode) {
        this.rerrorCode = rerrorCode;
    }

    /** 
     *            @hibernate.property
     *             column="SUBMIT_DATE"
     *             length="11"
     *         
     */
    public Date getSubmitDate() {
        return this.submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    /** 
     *            @hibernate.property
     *             column="SYS_SUBMIT_DATE"
     *             length="11"
     *         
     */
    public Date getSysSubmitDate() {
        return this.sysSubmitDate;
    }

    public void setSysSubmitDate(Date sysSubmitDate) {
        this.sysSubmitDate = sysSubmitDate;
    }

    /** 
     *            @hibernate.property
     *             column="RECEIVE_DATE"
     *             length="11"
     *         
     */
    public Date getReceiveDate() {
        return this.receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    /** 
     *            @hibernate.property
     *             column="VERY_CODE"
     *             length="6"
     *         
     */
    public String getVeryCode() {
        return this.veryCode;
    }
    
    public void setVeryCode(String veryCode) {
        this.veryCode = veryCode;
    }
    
    public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}


    /**
     *            @hibernate.property
     *             column="REGSTATUS"
     *             length="10"
     *             not-null="true"
     *
     */
    public long getRegstatus() {
        return this.regstatus;
    }

    public void setRegstatus(long regstatus) {
        this.regstatus = regstatus;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

    public Long getComp() {
		return comp;
	}


	public void setComp(Long comp) {
		this.comp = comp;
	}


	public Long getVcation() {
		return vcation;
	}


	public void setVcation(Long vcation) {
		this.vcation = vcation;
	}
	
	public String getReturnStr() {
        return this.returnStr;
    }
    
    public void setReturnStr(String returnStr) {
        this.returnStr = returnStr;
    }


	public Integer getFromclass() {
		return fromclass;
	}


	public void setFromclass(Integer fromclass) {
		this.fromclass = fromclass;
	}
}
