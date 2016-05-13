package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-01 07:31:33

 *        @hibernate.class
 *         table="DB_QIUBOFS"
 *     
 */
public class DbQiuboFs implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private Long senduserid;

    /** nullable persistent field */
    private String sendusername;
    
    /** nullable persistent field */
    private Date sendtime;

    /** full constructor */
    public DbQiuboFs(Long id, Long senduserid, String sendusername, Long postuserid, Date sendtime) {
        this.id = id;
        this.senduserid = senduserid;
        this.sendusername = sendusername;
        this.sendtime = sendtime;
    }

    /** default constructor */
    public DbQiuboFs() {
    }

    /** minimal constructor */
    public DbQiuboFs(Long id) {
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
