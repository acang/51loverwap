package com.web.obj;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-18 08:28:00

 *        @hibernate.class
 *         table="Readarticle"
 *     
 */
public class Readarticle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 723807710737232754L;

	private Long articleid;
    
    private Long hyid;
    
    private Long id;
    
    private String lname;
    
    private String name;

    private String title;

    private Date readtime;
    
    private String ip;

    public Readarticle(){
    	
    }
    
    /** full constructor */
    public Readarticle(Long articleid,Long hyid,String lname,String name,Date inserttime,String title,String ip) {
    	this.articleid=articleid;
        this.hyid=hyid;
        this.lname=lname;
        this.name=name;
        this.title=title;
        this.readtime=inserttime;
        this.ip=ip;
    }




	public Long getArticleid() {
		return articleid;
	}




	public void setArticleid(Long articleid) {
		this.articleid = articleid;
	}




	public Long getHyid() {
		return hyid;
	}




	public Date getReadtime() {
		return readtime;
	}

	public void setReadtime(Date readtime) {
		this.readtime = readtime;
	}

	public void setHyid(Long hyid) {
		this.hyid = hyid;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getName() {
		return name;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
        return new ToStringBuilder(this)
 .append("id", getName())
            .toString();
    }
}
