package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-01 12:32:12

 *        @hibernate.class
 *         table="HYLY"
 *     
 */
public class Hyly implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String hyname;

    /** nullable persistent field */
    private Long hyid;

    /** nullable persistent field */
    private String fromname;

    /** nullable persistent field */
    private Long fromid;

    /** nullable persistent field */
    private String content;

    /** nullable persistent field */
    private Date lytime;

    /** full constructor */
    public Hyly(Long id, String hyname, Long hyid, String fromname, Long fromid, String content, Date lytime) {
        this.id = id;
        this.hyname = hyname;
        this.hyid = hyid;
        this.fromname = fromname;
        this.fromid = fromid;
        this.content = content;
        this.lytime = lytime;
    }

    /** default constructor */
    public Hyly() {
    }

    /** minimal constructor */
    public Hyly(Long id) {
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
     *             column="FROMNAME"
     *             length="50"
     *         
     */
    public String getFromname() {
        return this.fromname;
    }

    public void setFromname(String fromname) {
        this.fromname = fromname;
    }

    /** 
     *            @hibernate.property
     *             column="FROMID"
     *             length="10"
     *         
     */
    public Long getFromid() {
        return this.fromid;
    }

    public void setFromid(Long fromid) {
        this.fromid = fromid;
    }

    /** 
     *            @hibernate.property
     *             column="CONTENT"
     *             length="200"
     *         
     */
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /** 
     *            @hibernate.property
     *             column="LYTIME"
     *             length="7"
     *         
     */
    public Date getLytime() {
        return this.lytime;
    }

    public void setLytime(Date lytime) {
        this.lytime = lytime;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
