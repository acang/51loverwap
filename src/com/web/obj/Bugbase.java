package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-10 07:27:32

 *        @hibernate.class
 *         table="BUGBASE"
 *     
 */
public class Bugbase implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String address;

    /** nullable persistent field */
    private Date starttime;

    /** nullable persistent field */
    private Date endtime;

    /** nullable persistent field */
    private String cjry;

    /** nullable persistent field */
    private String huizr;

    /** nullable persistent field */
    private String memo;

    /** full constructor */
    public Bugbase(Long id, String address, Date starttime, Date endtime, String cjry, String huizr, String memo) {
        this.id = id;
        this.address = address;
        this.starttime = starttime;
        this.endtime = endtime;
        this.cjry = cjry;
        this.huizr = huizr;
        this.memo = memo;
    }

    /** default constructor */
    public Bugbase() {
    }

    /** minimal constructor */
    public Bugbase(Long id) {
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
     *             column="ADDRESS"
     *             length="20"
     *         
     */
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /** 
     *            @hibernate.property
     *             column="STARTTIME"
     *             length="7"
     *         
     */
    public Date getStarttime() {
        return this.starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /** 
     *            @hibernate.property
     *             column="ENDTIME"
     *             length="7"
     *         
     */
    public Date getEndtime() {
        return this.endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /** 
     *            @hibernate.property
     *             column="CJRY"
     *             length="300"
     *         
     */
    public String getCjry() {
        return this.cjry;
    }

    public void setCjry(String cjry) {
        this.cjry = cjry;
    }

    /** 
     *            @hibernate.property
     *             column="HUIZR"
     *             length="20"
     *         
     */
    public String getHuizr() {
        return this.huizr;
    }

    public void setHuizr(String huizr) {
        this.huizr = huizr;
    }

    /** 
     *            @hibernate.property
     *             column="MEMO"
     *             length="300"
     *         
     */
    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
