package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-01 07:31:33

 *        @hibernate.class
 *         table="DB_QIUBO"
 *     
 */
public class DbQiubo implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private Long senduserid;

    /** nullable persistent field */
    private String sendusername;

    /** nullable persistent field */
    private Long postuserid;

    /** nullable persistent field */
    private String postusername;

    /** nullable persistent field */
    private Integer zt;

    /** nullable persistent field */
    private Date sendtime;

    /** full constructor */
    public DbQiubo(Long id, Long senduserid, String sendusername, Long postuserid, String postusername, Integer zt, Date sendtime) {
        this.id = id;
        this.senduserid = senduserid;
        this.sendusername = sendusername;
        this.postuserid = postuserid;
        this.postusername = postusername;
        this.zt = zt;
        this.sendtime = sendtime;
    }

    /** default constructor */
    public DbQiubo() {
    }

    /** minimal constructor */
    public DbQiubo(Long id) {
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
     *             column="SENDUSERID"
     *             length="10"
     *         
     */
    public Long getSenduserid() {
        return this.senduserid;
    }

    public void setSenduserid(Long senduserid) {
        this.senduserid = senduserid;
    }

    /** 
     *            @hibernate.property
     *             column="SENDUSERNAME"
     *             length="50"
     *         
     */
    public String getSendusername() {
        return this.sendusername;
    }

    public void setSendusername(String sendusername) {
        this.sendusername = sendusername;
    }

    /** 
     *            @hibernate.property
     *             column="POSTUSERID"
     *             length="10"
     *         
     */
    public Long getPostuserid() {
        return this.postuserid;
    }

    public void setPostuserid(Long postuserid) {
        this.postuserid = postuserid;
    }

    /** 
     *            @hibernate.property
     *             column="POSTUSERNAME"
     *             length="50"
     *         
     */
    public String getPostusername() {
        return this.postusername;
    }

    public void setPostusername(String postusername) {
        this.postusername = postusername;
    }

    /** 
     *            @hibernate.property
     *             column="ZT"
     *             length="2"
     *         
     */
    public Integer getZt() {
        return this.zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    /** 
     *            @hibernate.property
     *             column="SENDTIME"
     *             length="7"
     *         
     */
    public Date getSendtime() {
        return this.sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
