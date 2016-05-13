package com.web.obj;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-02-27 05:52:09

 *        @hibernate.class
 *         table="USEROTHER"
 *     
 */
public class Userother implements Serializable {

    /** identifier field */
    private Long hyid;

    /** nullable persistent field */
    private String username;

    /** nullable persistent field */
    private String userphoto1;

    /** nullable persistent field */
    private String userphoto2;

    /** nullable persistent field */
    private String userphoto3;

    /** nullable persistent field */
    private String userphoto4;

    /** nullable persistent field */
    private String userphoto5;

    /** nullable persistent field */
    private String memo;

    /** nullable persistent field */
    private String qggx;

    /** nullable persistent field */
    private String lxqr;

    /** nullable persistent field */
    private String jyly;

    /** full constructor */
    public Userother(Long hyid, String username, String userphoto1, String userphoto2, String userphoto3, String userphoto4, String userphoto5, String memo, String qggx, String lxqr, String jyly) {
        this.hyid = hyid;
        this.username = username;
        this.userphoto1 = userphoto1;
        this.userphoto2 = userphoto2;
        this.userphoto3 = userphoto3;
        this.userphoto4 = userphoto4;
        this.userphoto5 = userphoto5;
        this.memo = memo;
        this.qggx = qggx;
        this.lxqr = lxqr;
        this.jyly = jyly;
    }

    /** default constructor */
    public Userother() {
    }

    /** minimal constructor */
    public Userother(Long hyid) {
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
     *             column="USERNAME"
     *             length="50"
     *         
     */
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /** 
     *            @hibernate.property
     *             column="USERPHOTO1"
     *             length="50"
     *         
     */
    public String getUserphoto1() {
        return this.userphoto1;
    }

    public void setUserphoto1(String userphoto1) {
        this.userphoto1 = userphoto1;
    }

    /** 
     *            @hibernate.property
     *             column="USERPHOTO2"
     *             length="50"
     *         
     */
    public String getUserphoto2() {
        return this.userphoto2;
    }

    public void setUserphoto2(String userphoto2) {
        this.userphoto2 = userphoto2;
    }

    /** 
     *            @hibernate.property
     *             column="USERPHOTO3"
     *             length="50"
     *         
     */
    public String getUserphoto3() {
        return this.userphoto3;
    }

    public void setUserphoto3(String userphoto3) {
        this.userphoto3 = userphoto3;
    }

    /** 
     *            @hibernate.property
     *             column="USERPHOTO4"
     *             length="50"
     *         
     */
    public String getUserphoto4() {
        return this.userphoto4;
    }

    public void setUserphoto4(String userphoto4) {
        this.userphoto4 = userphoto4;
    }

    /** 
     *            @hibernate.property
     *             column="USERPHOTO5"
     *             length="50"
     *         
     */
    public String getUserphoto5() {
        return this.userphoto5;
    }

    public void setUserphoto5(String userphoto5) {
        this.userphoto5 = userphoto5;
    }

    /** 
     *            @hibernate.property
     *             column="MEMO"
     *             length="50"
     *         
     */
    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    /** 
     *            @hibernate.property
     *             column="QGGX"
     *             length="255"
     *         
     */
    public String getQggx() {
        return this.qggx;
    }

    public void setQggx(String qggx) {
        this.qggx = qggx;
    }

    /** 
     *            @hibernate.property
     *             column="LXQR"
     *             length="666"
     *         
     */
    public String getLxqr() {
        return this.lxqr;
    }

    public void setLxqr(String lxqr) {
        this.lxqr = lxqr;
    }

    /** 
     *            @hibernate.property
     *             column="JYLY"
     *             length="666"
     *         
     */
    public String getJyly() {
        return this.jyly;
    }

    public void setJyly(String jyly) {
        this.jyly = jyly;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("hyid", getHyid())
            .toString();
    }

}
