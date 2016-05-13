package com.web.obj;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2010-02-27 09:08:51

 *        @hibernate.class
 *         table="HYTJ"
 *     
 */
public class Hytj implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private Long myid;

    /** nullable persistent field */
    private String myname;

    /** nullable persistent field */
    private Long tjid;

    /** nullable persistent field */
    private String tjname;

    /** nullable persistent field */
    private Date tjdate;
    /** nullable persistent field */
    private String ischeck;
    
    /** nullable persistent field */
    private String tjtype;
    
    /** nullable persistent field */
    private String isphone;

    /** full constructor */
    public Hytj(Long id, Long myid, String myname, Long tjid, String tjname, Date tjdate, String ischeck, String tjtype, String isphone) {
        this.id = id;
        this.myid = myid;
        this.myname = myname;
        this.tjid = tjid;
        this.tjname = tjname;
        this.tjdate = tjdate;
        this.ischeck = ischeck;
        this.tjtype = tjtype;
        this.isphone = isphone;
    }

    /** default constructor */
    public Hytj() {
    }

    /** minimal constructor */
    public Hytj(Long id) {
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
     *             column="TJID"
     *             length="10"
     *         
     */
    public Long getTjid() {
        return this.tjid;
    }

    public void setTjid(Long tjid) {
        this.tjid = tjid;
    }

    /** 
     *            @hibernate.property
     *             column="TJNAME"
     *             length="50"
     *         
     */
    public String getTjname() {
        return this.tjname;
    }

    public void setTjname(String tjname) {
        this.tjname = tjname;
    }

    /** 
     *            @hibernate.property
     *             column="TJDATE"
     *             length="7"
     *         
     */
    public Date getTjdate() {
        return this.tjdate;
    }

    public void setTjdate(Date tjdate) {
        this.tjdate = tjdate;
    }


    public String getIscheck() {
		return ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}

	public String getTjtype() {
		return tjtype;
	}

	public void setTjtype(String tjtype) {
		this.tjtype = tjtype;
	}

	public String getIsphone() {
		return isphone;
	}

	public void setIsphone(String isphone) {
		this.isphone = isphone;
	}

	
    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
