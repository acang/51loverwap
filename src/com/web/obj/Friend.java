package com.web.obj;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-02 12:20:17

 *        @hibernate.class
 *         table="FRIEND"
 *     
 */
public class Friend implements Serializable {

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private String bianhao;

    /** nullable persistent field */
    private String netname;

    /** nullable persistent field */
    private String nethttp;

    /** nullable persistent field */
    private String netlogo;

    /** nullable persistent field */
    private String memo;

    /** nullable persistent field */
    private String man;

    /** nullable persistent field */
    private String tel;

    /** nullable persistent field */
    private Date applydate;

    /** nullable persistent field */
    private String type;

    /** nullable persistent field */
    private String state;

    /** nullable persistent field */
    private String applyreason;

    private Integer addlink;

    /** full constructor */
    public Friend(Integer id, String bianhao, String netname, String nethttp, String netlogo, String memo,String man,String tel,Date applydate,String type,String state,String applyreason,Integer addlink) {
        this.id = id;
        this.bianhao = bianhao;
        this.netname = netname;
        this.nethttp = nethttp;
        this.netlogo = netlogo;
        this.memo = memo;
        this.man = man;
        this.tel = tel;
        this.applydate = applydate;
        this.type = type;
        this.state = state;
        this.applyreason = applyreason;
        this.addlink = addlink;
    }

    /** default constructor */
    public Friend() {
    }

    /** minimal constructor */
    public Friend(Integer id) {
        this.id = id;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Integer"
     *             column="ID"
     *         
     */
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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
     *             column="NETNAME"
     *             length="100"
     *         
     */
    public String getNetname() {
        return this.netname;
    }

    public void setNetname(String netname) {
        this.netname = netname;
    }

    /** 
     *            @hibernate.property
     *             column="NETHTTP"
     *             length="500"
     *         
     */
    public String getNethttp() {
        return this.nethttp;
    }

    public void setNethttp(String nethttp) {
        this.nethttp = nethttp;
    }

    /** 
     *            @hibernate.property
     *             column="NETLOGO"
     *             length="100"
     *         
     */
    public String getNetlogo() {
        return this.netlogo;
    }

    public void setNetlogo(String netlogo) {
        this.netlogo = netlogo;
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

    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getApplydate() {
        return applydate;
    }

    public void setApplydate(Date applydate) {
        this.applydate = applydate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getApplyreason() {
        return applyreason;
    }

    public void setApplyreason(String applyreason) {
        this.applyreason = applyreason;
    }

    public Integer getAddlink() {
        return addlink;
    }

    public void setAddlink(Integer addlink) {
        this.addlink = addlink;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
