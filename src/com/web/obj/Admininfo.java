package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-07 02:12:08

 *        @hibernate.class
 *         table="ADMININFO"
 *     
 */
public class Admininfo implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String adminname;

    /** persistent field */
    private String adminpass;

    /** nullable persistent field */
    private Integer flag;

    /** nullable persistent field */
    private Date lasttime;

    /** nullable persistent field */
    private Date regtime;

    /** nullable persistent field */
    private Long dlcs;

    /** nullable persistent field */
    private String memo;

    /** full constructor */
    public Admininfo(Long id, String adminname, String adminpass, Integer flag, Date lasttime, Date regtime, Long dlcs, String memo) {
        this.id = id;
        this.adminname = adminname;
        this.adminpass = adminpass;
        this.flag = flag;
        this.lasttime = lasttime;
        this.regtime = regtime;
        this.dlcs = dlcs;
        this.memo = memo;
    }

    /** default constructor */
    public Admininfo() {
    }

    /** minimal constructor */
    public Admininfo(Long id, String adminname, String adminpass) {
        this.id = id;
        this.adminname = adminname;
        this.adminpass = adminpass;
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
     *             column="ADMINNAME"
     *             length="20"
     *             not-null="true"
     *         
     */
    public String getAdminname() {
        return this.adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    /** 
     *            @hibernate.property
     *             column="ADMINPASS"
     *             length="20"
     *             not-null="true"
     *         
     */
    public String getAdminpass() {
        return this.adminpass;
    }

    public void setAdminpass(String adminpass) {
        this.adminpass = adminpass;
    }

    /** 
     *            @hibernate.property
     *             column="FLAG"
     *             length="2"
     *         
     */
    public Integer getFlag() {
        return this.flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /** 
     *            @hibernate.property
     *             column="LASTTIME"
     *             length="7"
     *         
     */
    public Date getLasttime() {
        return this.lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    /** 
     *            @hibernate.property
     *             column="REGTIME"
     *             length="7"
     *         
     */
    public Date getRegtime() {
        return this.regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    /** 
     *            @hibernate.property
     *             column="DLCS"
     *             length="10"
     *         
     */
    public Long getDlcs() {
        return this.dlcs;
    }

    public void setDlcs(Long dlcs) {
        this.dlcs = dlcs;
    }

    /** 
     *            @hibernate.property
     *             column="MEMO"
     *             length="50"
     *         
     */
    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
