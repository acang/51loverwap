package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HCG v1.0, create by dc, 2011-11-22 01:59:14

 *        @hibernate.class
 *         table="USER_BJD_RECORD"
 *     
 */
public class UserBjdRecord implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String hyid;

    /** persistent field */
    private Date recodeTime;

    /** persistent field */
    private long bjdnumber;

    /** persistent field */
    private String bjddesc;
    
    /** persistent field */
    private Long tjid;

    /** persistent field */
    private String tjtype;

    /** full constructor */
    public UserBjdRecord(Long id, String hyid, Date recodeTime, long bjdnumber, String bjddesc, Long tjid, String tjtype) {
        this.id = id;
        this.hyid = hyid;
        this.recodeTime = recodeTime;
        this.bjdnumber = bjdnumber;
        this.bjddesc = bjddesc;
        this.tjid = tjid;
        this.tjtype = tjtype;
    }

    /** default constructor */
    public UserBjdRecord() {
    }

    /** minimal constructor */
    public UserBjdRecord(Long id, Date recodeTime, long bjdnumber, String bjddesc) {
        this.id = id;
        this.recodeTime = recodeTime;
        this.bjdnumber = bjdnumber;
        this.bjddesc = bjddesc;
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
     *             column="HYID"
     *             length="10"
     *         
     */
    public String getHyid() {
        return this.hyid;
    }

    public void setHyid(String hyid) {
        this.hyid = hyid;
    }

    /** 
     *            @hibernate.property
     *             column="RECODE_TIME"
     *             length="7"
     *             not-null="true"
     *         
     */
    public Date getRecodeTime() {
        return this.recodeTime;
    }

    public void setRecodeTime(Date recodeTime) {
        this.recodeTime = recodeTime;
    }

    /** 
     *            @hibernate.property
     *             column="BJDNUMBER"
     *             length="10"
     *             not-null="true"
     *         
     */
    public long getBjdnumber() {
        return this.bjdnumber;
    }

    public void setBjdnumber(long bjdnumber) {
        this.bjdnumber = bjdnumber;
    }

    /** 
     *            @hibernate.property
     *             column="BJDDESC"
     *             length="100"
     *             not-null="true"
     *         
     */
    public String getBjddesc() {
        return this.bjddesc;
    }

    public void setBjddesc(String bjddesc) {
        this.bjddesc = bjddesc;
    }

    public Long getTjid() {
		return tjid;
	}

	public void setTjid(Long tjid) {
		this.tjid = tjid;
	}

	public String getTjtype() {
		return tjtype;
	}

	public void setTjtype(String tjtype) {
		this.tjtype = tjtype;
	}

	public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
