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
public class Uservisit implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private Long hyid;

    /** nullable persistent field */
    private Date vtime;

    /** nullable persistent field */
    private String vurl;
    
	/** full constructor */
    public Uservisit(Long id, Long hyid,Date vtime,String vurl) {
        this.id = id;
        this.hyid = hyid;
        this.vtime = vtime;
        this.vurl = vurl;
    }

    
	/** default constructor */
    public Uservisit() {
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


	public Long getHyid() {
		return hyid;
	}


	public void setHyid(Long hyid) {
		this.hyid = hyid;
	}


	public Date getVtime() {
		return vtime;
	}


	public void setVtime(Date vtime) {
		this.vtime = vtime;
	}


	public String getVurl() {
		return vurl;
	}


	public void setVurl(String vurl) {
		this.vurl = vurl;
	}
}
