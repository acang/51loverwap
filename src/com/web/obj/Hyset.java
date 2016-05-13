package com.web.obj;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-02-27 05:52:09

 *        @hibernate.class
 *         table="HYSET"
 *     
 */
public class Hyset implements Serializable {

    /** identifier field */
    private Long hyid;

    /** nullable persistent field */
    private Integer setbase;

    /** nullable persistent field */
    private Integer setpic;

    /** nullable persistent field */
    private Integer setmail;

    /** nullable persistent field */
    private Integer setqq;

    /** nullable persistent field */
    private Integer setsjtel;

    /** nullable persistent field */
    private Integer settel;

    /** nullable persistent field */
    private Integer setwx;

    /** nullable persistent field */
    private String memo;

    /** full constructor */
    public Hyset(Long hyid, Integer setbase, Integer setpic, Integer setmail, Integer setqq, Integer setsjtel, Integer settel, Integer setwx, String memo) {
        this.hyid = hyid;
        this.setbase = setbase;
        this.setpic = setpic;
        this.setmail = setmail;
        this.setqq = setqq;
        this.setsjtel = setsjtel;
        this.settel = settel;
        this.setwx = setwx;
        this.memo = memo;
    }

    /** default constructor */
    public Hyset() {
    }

    /** minimal constructor */
    public Hyset(Long hyid) {
        this.hyid = hyid;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Long"
     *             column="HYID"
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
     *             column="SETBASE"
     *             length="2"
     *         
     */
    public Integer getSetbase() {
        return this.setbase;
    }

    public void setSetbase(Integer setbase) {
        this.setbase = setbase;
    }

    /** 
     *            @hibernate.property
     *             column="SETPIC"
     *             length="2"
     *         
     */
    public Integer getSetpic() {
        return this.setpic;
    }

    public void setSetpic(Integer setpic) {
        this.setpic = setpic;
    }

    /** 
     *            @hibernate.property
     *             column="SETMAIL"
     *             length="2"
     *         
     */
    public Integer getSetmail() {
        return this.setmail;
    }

    public void setSetmail(Integer setmail) {
        this.setmail = setmail;
    }

    /** 
     *            @hibernate.property
     *             column="SETQQ"
     *             length="2"
     *         
     */
    public Integer getSetqq() {
        return this.setqq;
    }

    public void setSetqq(Integer setqq) {
        this.setqq = setqq;
    }

    /** 
     *            @hibernate.property
     *             column="SETSJTEL"
     *             length="2"
     *         
     */
    public Integer getSetsjtel() {
        return this.setsjtel;
    }

    public void setSetsjtel(Integer setsjtel) {
        this.setsjtel = setsjtel;
    }

    /** 
     *            @hibernate.property
     *             column="SETTEL"
     *             length="2"
     *         
     */
    public Integer getSettel() {
        return this.settel;
    }

    public void setSettel(Integer settel) {
        this.settel = settel;
    }

    public Integer getSetwx() {
        return setwx;
    }

    public void setSetwx(Integer setwx) {
        this.setwx = setwx;
    }

    /**
     *            @hibernate.property
     *             column="MEMO"
     *             length="100"
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
 .append("hyid", getHyid())
            .toString();
    }

}
