package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-10 07:27:32

 *        @hibernate.class
 *         table="BUGRECORD"
 *     
 */
public class Bugrecord implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private Long baseid;

    /** nullable persistent field */
    private Date jldate;

    /** nullable persistent field */
    private String tcr;

    /** nullable persistent field */
    private String wtdes;

    /** nullable persistent field */
    private String yyfx;

    /** nullable persistent field */
    private String clqk;

    /** nullable persistent field */
    private String jjqk;

    /** nullable persistent field */
    private String wtfl;

    /** nullable persistent field */
    private String xgzt;

    /** nullable persistent field */
    private String fzr;

    /** nullable persistent field */
    private String xgqk;

    /** full constructor */
    public Bugrecord(Long id, Long baseid, Date jldate, String tcr, String wtdes, String yyfx, String clqk, String jjqk, String wtfl, String xgzt, String fzr, String xgqk) {
        this.id = id;
        this.baseid = baseid;
        this.jldate = jldate;
        this.tcr = tcr;
        this.wtdes = wtdes;
        this.yyfx = yyfx;
        this.clqk = clqk;
        this.jjqk = jjqk;
        this.wtfl = wtfl;
        this.xgzt = xgzt;
        this.fzr = fzr;
        this.xgqk = xgqk;
    }

    /** default constructor */
    public Bugrecord() {
    }

    /** minimal constructor */
    public Bugrecord(Long id) {
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
     *             column="BASEID"
     *             length="10"
     *         
     */
    public Long getBaseid() {
        return this.baseid;
    }

    public void setBaseid(Long baseid) {
        this.baseid = baseid;
    }

    /** 
     *            @hibernate.property
     *             column="JLDATE"
     *             length="7"
     *         
     */
    public Date getJldate() {
        return this.jldate;
    }

    public void setJldate(Date jldate) {
        this.jldate = jldate;
    }

    /** 
     *            @hibernate.property
     *             column="TCR"
     *             length="20"
     *         
     */
    public String getTcr() {
        return this.tcr;
    }

    public void setTcr(String tcr) {
        this.tcr = tcr;
    }

    /** 
     *            @hibernate.property
     *             column="WTDES"
     *             length="1000"
     *         
     */
    public String getWtdes() {
        return this.wtdes;
    }

    public void setWtdes(String wtdes) {
        this.wtdes = wtdes;
    }

    /** 
     *            @hibernate.property
     *             column="YYFX"
     *             length="1000"
     *         
     */
    public String getYyfx() {
        return this.yyfx;
    }

    public void setYyfx(String yyfx) {
        this.yyfx = yyfx;
    }

    /** 
     *            @hibernate.property
     *             column="CLQK"
     *             length="1000"
     *         
     */
    public String getClqk() {
        return this.clqk;
    }

    public void setClqk(String clqk) {
        this.clqk = clqk;
    }

    /** 
     *            @hibernate.property
     *             column="JJQK"
     *             length="1000"
     *         
     */
    public String getJjqk() {
        return this.jjqk;
    }

    public void setJjqk(String jjqk) {
        this.jjqk = jjqk;
    }

    /** 
     *            @hibernate.property
     *             column="WTFL"
     *             length="20"
     *         
     */
    public String getWtfl() {
        return this.wtfl;
    }

    public void setWtfl(String wtfl) {
        this.wtfl = wtfl;
    }

    /** 
     *            @hibernate.property
     *             column="XGZT"
     *             length="20"
     *         
     */
    public String getXgzt() {
        return this.xgzt;
    }

    public void setXgzt(String xgzt) {
        this.xgzt = xgzt;
    }

    /** 
     *            @hibernate.property
     *             column="FZR"
     *             length="20"
     *         
     */
    public String getFzr() {
        return this.fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    /** 
     *            @hibernate.property
     *             column="XGQK"
     *             length="20"
     *         
     */
    public String getXgqk() {
        return this.xgqk;
    }

    public void setXgqk(String xgqk) {
        this.xgqk = xgqk;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
