package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-02-27 01:24:25

 *        @hibernate.class
 *         table="HYGL"
 *     
 */
public class Hygl implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String sqname;

    /** nullable persistent field */
    private Long sqid;

    /** nullable persistent field */
    private String sqlc;

    /** nullable persistent field */
    private Integer sqtype;

    /** nullable persistent field */
    private String myname;

    /** nullable persistent field */
    private Long myid;

    /** nullable persistent field */
    private String mylc;

    /** nullable persistent field */
    private Date riqi;

    /** full constructor */
    public Hygl(Long id, String sqname, Long sqid, String sqlc, Integer sqtype, String myname, Long myid, String mylc, Date riqi) {
        this.id = id;
        this.sqname = sqname;
        this.sqid = sqid;
        this.sqlc = sqlc;
        this.sqtype = sqtype;
        this.myname = myname;
        this.myid = myid;
        this.mylc = mylc;
        this.riqi = riqi;
    }

    /** default constructor */
    public Hygl() {
    }

    /** minimal constructor */
    public Hygl(Long id) {
        this.id = id;
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
     *             column="SQNAME"
     *             length="50"
     *         
     */
    public String getSqname() {
        return this.sqname;
    }

    public void setSqname(String sqname) {
        this.sqname = sqname;
    }

    /** 
     *            @hibernate.property
     *             column="SQID"
     *             length="10"
     *         
     */
    public Long getSqid() {
        return this.sqid;
    }

    public void setSqid(Long sqid) {
        this.sqid = sqid;
    }

    /** 
     *            @hibernate.property
     *             column="SQLC"
     *             length="50"
     *         
     */
    public String getSqlc() {
        return this.sqlc;
    }

    public void setSqlc(String sqlc) {
        this.sqlc = sqlc;
    }

    /** 
     *            @hibernate.property
     *             column="SQTYPE"
     *             length="2"
     *         
     */
    public Integer getSqtype() {
        return this.sqtype;
    }

    public void setSqtype(Integer sqtype) {
        this.sqtype = sqtype;
    }

    /** 
     *            @hibernate.property
     *             column="MYNAME"
     *             length="50"
     *         
     */
    public String getMyname() {
        return this.myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }

    /** 
     *            @hibernate.property
     *             column="MYID"
     *             length="10"
     *         
     */
    public Long getMyid() {
        return this.myid;
    }

    public void setMyid(Long myid) {
        this.myid = myid;
    }

    /** 
     *            @hibernate.property
     *             column="MYLC"
     *             length="50"
     *         
     */
    public String getMylc() {
        return this.mylc;
    }

    public void setMylc(String mylc) {
        this.mylc = mylc;
    }

    /** 
     *            @hibernate.property
     *             column="RIQI"
     *             length="7"
     *         
     */
    public Date getRiqi() {
        return this.riqi;
    }

    public void setRiqi(Date riqi) {
        this.riqi = riqi;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
