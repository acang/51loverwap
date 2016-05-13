package com.web.obj;

import java.io.Serializable;
import java.util.*;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * gen by HbmTools2, 2009-03-07 01:48:42

 *        @hibernate.class
 *         table="WTJD"
 *
 */
public class Wtjd implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String bianhao;

    /** nullable persistent field */
    private String ask;

    /** nullable persistent field */
    private Long answer;

    /** nullable persistent field */
    private Integer wttype;


    /** nullable persistent field */
    private Date stime;
    
    /** nullable persistent field */
    private Integer islink=0;
    
    /** nullable persistent field */
    private String linkurl;


    /** full constructor */
    public Wtjd(Long id, String bianhao, String ask, Long answer, Integer wttype,Date stime) {
        this.id = id;
        this.bianhao = bianhao;
        this.ask = ask;
        this.answer = answer;
        this.wttype = wttype;
        this.stime = stime;
    }

    /** default constructor */
    public Wtjd() {
    }

    /** minimal constructor */
    public Wtjd(Long id) {
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
     *             column="BIANHAO"
     *             length="10"
     *
     */
    public String getBianhao() {
        return this.bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao;
    }

    /**
     *            @hibernate.property
     *             column="ASK"
     *             length="200"
     *
     */
    public String getAsk() {
        return this.ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    /**
     *            @hibernate.property
     *             column="ANSWER"
     *             length="10"
     *
     */
    public Long getAnswer() {
        return this.answer;
    }

    public void setAnswer(Long answer) {
        this.answer = answer;
    }

    /**
     *            @hibernate.property
     *             column="WTTYPE"
     *             length="2"
     *
     */
    public Integer getWttype() {
        return this.wttype;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    /**
     *            @hibernate.property
     *             column="WTTYPE"
     *             length="2"
     *
     */
    public Date getStime() {
        return this.stime;
    }

    public void setWttype(Integer wttype) {
        this.wttype = wttype;
    }


    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

	public Integer getIslink() {
		return islink;
	}

	public void setIslink(Integer islink) {
		this.islink = islink;
	}

	public String getLinkurl() {
		return linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

}
