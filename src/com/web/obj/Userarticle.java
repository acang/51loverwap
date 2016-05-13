package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-02-27 05:52:09

 *        @hibernate.class
 *         table="USERARTICLE"
 *     
 */
public class Userarticle implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String title;

    /** nullable persistent field */
    private Long contentid;

    /** nullable persistent field */
    private Date createtime;

    /** nullable persistent field */
    private Long hyid;

    /** nullable persistent field */
    private String hyname;

    /** nullable persistent field */
    private String lcname;

    /** full constructor */
    public Userarticle(Long id, String title, Long contentid, Date createtime, Long hyid, String hyname, String lcname) {
        this.id = id;
        this.title = title;
        this.contentid = contentid;
        this.createtime = createtime;
        this.hyid = hyid;
        this.hyname = hyname;
        this.lcname = lcname;
    }

    /** default constructor */
    public Userarticle() {
    }

    /** minimal constructor */
    public Userarticle(Long id) {
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
     *             column="TITLE"
     *             length="120"
     *         
     */
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /** 
     *            @hibernate.property
     *             column="CONTENTID"
     *             length="10"
     *         
     */
    public Long getContentid() {
        return this.contentid;
    }

    public void setContentid(Long contentid) {
        this.contentid = contentid;
    }

    /** 
     *            @hibernate.property
     *             column="CREATETIME"
     *             length="7"
     *         
     */
    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /** 
     *            @hibernate.property
     *             column="HYID"
     *             length="10"
     *         
     */
    public Long getHyid() {
        return this.hyid;
    }

    public void setHyid(Long hyid) {
        this.hyid = hyid;
    }

    /** 
     *            @hibernate.property
     *             column="HYNAME"
     *             length="50"
     *         
     */
    public String getHyname() {
        return this.hyname;
    }

    public void setHyname(String hyname) {
        this.hyname = hyname;
    }

    /** 
     *            @hibernate.property
     *             column="LCNAME"
     *             length="50"
     *         
     */
    public String getLcname() {
        return this.lcname;
    }

    public void setLcname(String lcname) {
        this.lcname = lcname;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
