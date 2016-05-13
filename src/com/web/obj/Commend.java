package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-01 12:32:12

 *        @hibernate.class
 *         table="COMMEND"
 *     
 */
public class Commend implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private Long bbsid;

    /** nullable persistent field */
    private Long commend;
    
    /** nullable persistent field */
    private Long userid;
    


	/** full constructor */
    public Commend(Long id, Long bbsid,Long commend,Long userid) {
        this.id = id;
        this.bbsid = bbsid;
        this.commend = commend;
        this.userid=userid;
    }

    /** default constructor */
    public Commend() {
    }

    /** minimal constructor */
    public Commend(Long id) {
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
     *             column="BBSID"
     *             length="50"
     *         
     */
    public Long getBbsid() {
        return this.bbsid;
    }

    public void setBbsid(Long bbsid) {
        this.bbsid = bbsid;
    }
    
    
    /** 
     *            @hibernate.property
     *             column="USERID"
     *             length="50"
     *         
     */
    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
    
    
    /** 
     *            @hibernate.property
     *             column="COMMEND"
     *             length="50"
     *         
     */
    public Long getCommend() {
        return this.commend;
    }

    public void setCommend(Long commend) {
        this.commend = commend;
    }

   
    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
