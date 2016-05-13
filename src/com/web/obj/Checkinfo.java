package com.web.obj;

import java.io.Serializable;
import java.util.Date;


/** 
 * gen by HbmTools2, 2009-03-18 08:28:00

 *        @hibernate.class
 *         table="checkinfo"
 *     
 */
public class Checkinfo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -817969515893703587L;
	
	private Long checkerid;
    
    private Long hyid;
    
    private Long id;
    
    private String status;
    
    private String hyname;

    private String lcname;

    private String checkername;

    private Date time;
    
    private Date nexttime;

    
    
    public Checkinfo(){
    	
    }
    
    /** full constructor */
    public Checkinfo(Long checkerid,Long hyid,Long id,String status,String hyname,String lcname,String checkername,Date time,Date nexttime) {
    	this.checkerid=checkerid;
    	this.status=status;
    	this.hyname=hyname;
    	
    	this.lcname=lcname;
    	this.checkername=checkername;
    	this.time=time;
    	
    	this.nexttime=nexttime;
        this.hyid=hyid;
        this.id=id;
    }

	public Long getCheckerid() {
		return checkerid;
	}

	public void setCheckerid(Long checkerid) {
		this.checkerid = checkerid;
	}

	public Long getHyid() {
		return hyid;
	}

	public void setHyid(Long hyid) {
		this.hyid = hyid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHyname() {
		return hyname;
	}

	public void setHyname(String hyname) {
		this.hyname = hyname;
	}

	public String getLcname() {
		return lcname;
	}

	public void setLcname(String lcname) {
		this.lcname = lcname;
	}

	public String getCheckername() {
		return checkername;
	}

	public void setCheckername(String checkername) {
		this.checkername = checkername;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getNexttime() {
		return nexttime;
	}

	public void setNexttime(Date nexttime) {
		this.nexttime = nexttime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
