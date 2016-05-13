package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-04-06 12:34:21

 *        @hibernate.class
 *         table="KFZX"
 *     
 */
public class Kfzx implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String username;

    /** nullable persistent field */
    private String tel;

    /** nullable persistent field */
    private String email;
    
    /** nullable persistent field */
    private String email2;

	/** persistent field */
    private String ntitle;

    /** nullable persistent field */
    private String ntext;

    /** nullable persistent field */
    private Integer ntype;

    /** nullable persistent field */
    private Integer ztai;

    /** nullable persistent field */
    private String ncontent;

    /** nullable persistent field */
    private Date ndate;

    /** nullable persistent field */
    private String ipaddress;
    
    /** nullable persistent field */
    private String deletename;
    
    /** nullable persistent field */
    private Integer source;
    
    /** identifier field */
    private Long hyid;
    
    /** nullable persistent field */
    private Integer mobileoremail;


    /** full constructor */
    public Kfzx(Long id, String username, String tel, String email, String email2,String ntitle, String ntext, Integer ntype, Integer ztai, String ncontent, Date ndate, String ipaddress, String deletename,Integer source,Long hyid,Integer mobileoremail) {
        this.id = id;
        this.username = username;
        this.tel = tel;
        this.email = email;
        this.email2 = email2;
        this.ntitle = ntitle;
        this.ntext = ntext;
        this.ntype = ntype;
        this.ztai = ztai;
        this.ncontent = ncontent;
        this.ndate = ndate;
        this.ipaddress = ipaddress;
        this.deletename = deletename;
        this.source=source;
        this.hyid=hyid;
        this.mobileoremail=mobileoremail;
    }

    /** default constructor */
    public Kfzx() {
    }

    /** minimal constructor */
    public Kfzx(Long id, String username, String ntitle) {
        this.id = id;
        this.username = username;
        this.ntitle = ntitle;
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
     *             column="USERNAME"
     *             length="50"
     *             not-null="true"
     *         
     */
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /** 
     *            @hibernate.property
     *             column="TEL"
     *             length="50"
     *         
     */
    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    /** 
     *            @hibernate.property
     *             column="EMAIL"
     *             length="50"
     *         
     */
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /** 
     *            @hibernate.property
     *             column="EMAIL2"
     *             length="50"
     *         
     */
    public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}
    /** 
     *            @hibernate.property
     *             column="N_TITLE"
     *             length="50"
     *             not-null="true"
     *         
     */
    public String getNtitle() {
        return this.ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    /** 
     *            @hibernate.property
     *             column="N_TEXT"
     *             length="500"
     *         
     */
    public String getNtext() {
        return this.ntext;
    }

    public void setNtext(String ntext) {
        this.ntext = ntext;
    }

    /** 
     *            @hibernate.property
     *             column="N_TYPE"
     *             length="2"
     *         
     */
    public Integer getNtype() {
        return this.ntype;
    }

    public void setNtype(Integer ntype) {
        this.ntype = ntype;
    }

    /** 
     *            @hibernate.property
     *             column="ZTAI"
     *             length="2"
     *         
     */
    public Integer getZtai() {
        return this.ztai;
    }

    public void setZtai(Integer ztai) {
        this.ztai = ztai;
    }

    /** 
     *            @hibernate.property
     *             column="N_CONTENT"
     *             length="500"
     *         
     */
    public String getNcontent() {
        return this.ncontent;
    }

    public void setNcontent(String ncontent) {
        this.ncontent = ncontent;
    }

    /** 
     *            @hibernate.property
     *             column="N_DATE"
     *             length="7"
     *         
     */
    public Date getNdate() {
        return this.ndate;
    }

    public void setNdate(Date ndate) {
        this.ndate = ndate;
    }

    /** 
     *            @hibernate.property
     *             column="IPADDRESS"
     *             length="30"
     *         
     */
    public String getIpaddress() {
        return this.ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    /** 
     *            @hibernate.property
     *             column="DELETENAME"
     *             length="50"
     *             not-null="true"
     *         
     */
    public String getDeletename() {
        return this.deletename;
    }

    public void setDeletename(String deletename) {
        this.deletename = deletename;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Long getHyid() {
		return hyid;
	}

	public void setHyid(Long hyid) {
		this.hyid = hyid;
	}

	public Integer getMobileoremail() {
		return mobileoremail;
	}

	public void setMobileoremail(Integer mobileoremail) {
		this.mobileoremail = mobileoremail;
	}

}
