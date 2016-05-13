package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-17 10:16:08

 *        @hibernate.class
 *         table="VIPUSERRECORD"
 *     
 */
public class Vipuserrecord implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private long hyid;

    /** nullable persistent field */
    private String sqhy;

    /** nullable persistent field */
    private Date yyxq;

    /** nullable persistent field */
    private Date zzsj;

    /** nullable persistent field */
    private String czry;

    /** nullable persistent field */
    private Date czsj;

    /** nullable persistent field */
    private String httpurl;

    /** nullable persistent field */
    private String name;

    /** nullable persistent field */
    private Double transamt;

    /** nullable persistent field */
    private Long serviceyear;

    /** nullable persistent field */
    private Integer datetype;

    /** nullable persistent field */
    private String simplename;

    /** nullable persistent field */
    private String sqdj;

    /** nullable persistent field */
    private Integer flag;
    
    /** nullable persistent field */
    private String area;

    /** full constructor */
    public Vipuserrecord(Long id, long hyid, String sqhy, Date yyxq, Date zzsj, String czry, Date czsj, String httpurl, String name, Double transamt, Long serviceyear, Integer datetype, String simplename, String sqdj, Integer flag) {
        this.id = id;
        this.hyid = hyid;
        this.sqhy = sqhy;
        this.yyxq = yyxq;
        this.zzsj = zzsj;
        this.czry = czry;
        this.czsj = czsj;
        this.httpurl = httpurl;
        this.name = name;
        this.transamt = transamt;
        this.serviceyear = serviceyear;
        this.datetype = datetype;
        this.simplename = simplename;
        this.sqdj = sqdj;
        this.flag = flag;
    }

    /** default constructor */
    public Vipuserrecord() {
    }

    /** minimal constructor */
    public Vipuserrecord(Long id, long hyid) {
        this.id = id;
        this.hyid = hyid;
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
     *             not-null="true"
     *         
     */
    public long getHyid() {
        return this.hyid;
    }

    public void setHyid(long hyid) {
        this.hyid = hyid;
    }

    /** 
     *            @hibernate.property
     *             column="SQHY"
     *             length="50"
     *         
     */
    public String getSqhy() {
        return this.sqhy;
    }

    public void setSqhy(String sqhy) {
        this.sqhy = sqhy;
    }

    /** 
     *            @hibernate.property
     *             column="YYXQ"
     *             length="7"
     *         
     */
    public Date getYyxq() {
        return this.yyxq;
    }

    public void setYyxq(Date yyxq) {
        this.yyxq = yyxq;
    }

    /** 
     *            @hibernate.property
     *             column="ZZSJ"
     *             length="7"
     *         
     */
    public Date getZzsj() {
        return this.zzsj;
    }

    public void setZzsj(Date zzsj) {
        this.zzsj = zzsj;
    }

    /** 
     *            @hibernate.property
     *             column="CZRY"
     *             length="10"
     *         
     */
    public String getCzry() {
        return this.czry;
    }

    public void setCzry(String czry) {
        this.czry = czry;
    }

    /** 
     *            @hibernate.property
     *             column="CZSJ"
     *             length="7"
     *         
     */
    public Date getCzsj() {
        return this.czsj;
    }

    public void setCzsj(Date czsj) {
        this.czsj = czsj;
    }

    /** 
     *            @hibernate.property
     *             column="HTTPURL"
     *             length="100"
     *         
     */
    public String getHttpurl() {
        return this.httpurl;
    }

    public void setHttpurl(String httpurl) {
        this.httpurl = httpurl;
    }

    /** 
     *            @hibernate.property
     *             column="NAME"
     *             length="50"
     *         
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** 
     *            @hibernate.property
     *             column="TRANSAMT"
     *             length="10"
     *         
     */
    public Double getTransamt() {
        return this.transamt;
    }

    public void setTransamt(Double transamt) {
        this.transamt = transamt;
    }

    /** 
     *            @hibernate.property
     *             column="SERVICEYEAR"
     *             length="10"
     *         
     */
    public Long getServiceyear() {
        return this.serviceyear;
    }

    public void setServiceyear(Long serviceyear) {
        this.serviceyear = serviceyear;
    }

    /** 
     *            @hibernate.property
     *             column="DATETYPE"
     *             length="1"
     *         
     */
    public Integer getDatetype() {
        return this.datetype;
    }

    public void setDatetype(Integer datetype) {
        this.datetype = datetype;
    }

    /** 
     *            @hibernate.property
     *             column="SIMPLENAME"
     *             length="50"
     *         
     */
    public String getSimplename() {
        return this.simplename;
    }

    public void setSimplename(String simplename) {
        this.simplename = simplename;
    }

    /** 
     *            @hibernate.property
     *             column="SQDJ"
     *             length="10"
     *         
     */
    public String getSqdj() {
        return this.sqdj;
    }

    public void setSqdj(String sqdj) {
        this.sqdj = sqdj;
    }

    /** 
     *            @hibernate.property
     *             column="FLAG"
     *             length="2"
     *         
     */
    public Integer getFlag() {
        return this.flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
    
    /** 
     *            @hibernate.property
     *             column="AREA"
     *             length="20"
     *         
     */
    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
