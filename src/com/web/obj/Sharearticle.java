package com.web.obj;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-18 08:28:00

 *        @hibernate.class
 *         table="sharearticle"
 *     
 */
public class Sharearticle implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6068237264120675095L;

	private Long articleid;
    
    private Long hyid;
    
    private Long id;
    
    private Long gold;
    
    private Long readnumber;

    private String lname;

    private String title;

    private Date inserttime;

    private String name;
    
    public Sharearticle(){
    	
    }
    
    /** full constructor */
    public Sharearticle(Long articleid,Long hyid,Long id,String lname,String name,Date inserttime,String title,Long gold,Long readnumber) {
    	this.articleid=articleid;
        this.hyid=hyid;
        this.gold=gold;
        this.readnumber=readnumber;
        this.lname=lname;
        this.name=name;
        this.title=title;
        this.inserttime=inserttime;
        this.id=id;
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




	public void setHyid(Long hyid) {
		this.hyid = hyid;
	}




	public Long getGold() {
		return gold;
	}




	public void setGold(Long gold) {
		this.gold = gold;
	}




	public Long getReadnumber() {
		return readnumber;
	}




	public void setReadnumber(Long readnumber) {
		this.readnumber = readnumber;
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




	public Date getInserttime() {
		return inserttime;
	}




	public void setInserttime(Date inserttime) {
		this.inserttime = inserttime;
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




	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
        return new ToStringBuilder(this)
 .append("id", getName())
            .toString();
    }
}
