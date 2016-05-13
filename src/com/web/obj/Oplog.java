package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-08-14 08:24:14

 *        @hibernate.class
 *         table="OPLOG"
 *     
 */
public class Oplog implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private Long hyid;

    /** nullable persistent field */
    private String hyname;

    /** nullable persistent field */
    private Long adid;

    /** nullable persistent field */
    private String adname;

    /** nullable persistent field */
    private Date opdate;

    /** nullable persistent field */
    private String opdes;

    /** nullable persistent field */
    private Integer optype;

    /** nullable persistent field */
    private String photoname;

    /** nullable persistent field */
    private Long contentid;

    /** full constructor */
    public Oplog(Long id, Long hyid, String hyname, Long adid, String adname, Date opdate, String opdes, Integer optype, String photoname, Long contentid) {
        this.id = id;
        this.hyid = hyid;
        this.hyname = hyname;
        this.adid = adid;
        this.adname = adname;
        this.opdate = opdate;
        this.opdes = opdes;
        this.optype = optype;
        this.photoname = photoname;
        this.contentid = contentid;
    }

    /** default constructor */
    public Oplog() {
    }

    /** minimal constructor */
    public Oplog(Long id) {
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
     *             column="ADID"
     *             length="10"
     *         
     */
    public Long getAdid() {
        return this.adid;
    }

    public void setAdid(Long adid) {
        this.adid = adid;
    }

    /** 
     *            @hibernate.property
     *             column="ADNAME"
     *             length="50"
     *         
     */
    public String getAdname() {
        return this.adname;
    }

    public void setAdname(String adname) {
        this.adname = adname;
    }

    /** 
     *            @hibernate.property
     *             column="OPDATE"
     *             length="7"
     *         
     */
    public Date getOpdate() {
        return this.opdate;
    }

    public void setOpdate(Date opdate) {
        this.opdate = opdate;
    }

    /** 
     *            @hibernate.property
     *             column="OPDES"
     *             length="200"
     *         
     */
    public String getOpdes() {
        return this.opdes;
    }

    public void setOpdes(String opdes) {
        this.opdes = opdes;
    }

    /** 
     *            @hibernate.property
     *             column="OPTYPE"
     *             length="2"
     *         
     */
    public Integer getOptype() {
        return this.optype;
    }

    public void setOptype(Integer optype) {
        this.optype = optype;
    }

    /** 
     *            @hibernate.property
     *             column="PHOTONAME"
     *             length="50"
     *         
     */
    public String getPhotoname() {
        return this.photoname;
    }

    public void setPhotoname(String photoname) {
        this.photoname = photoname;
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

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
