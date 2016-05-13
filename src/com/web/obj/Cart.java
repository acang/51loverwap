package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-18 08:28:00

 *        @hibernate.class
 *         table="CART"
 *     
 */
public class Cart implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String username;

    /** nullable persistent field */
    private String lcname;

    /** nullable persistent field */
    private String commenduser;

    /** nullable persistent field */
    private String ordid;

    /** nullable persistent field */
    private String zsname;

    /** nullable persistent field */
    private String tel;

    /** nullable persistent field */
    private String email;

    /** nullable persistent field */
    private String address;

    /** nullable persistent field */
    private String ztai;

    /** nullable persistent field */
    private Integer trans;

    /** nullable persistent field */
    private Integer upgrade;

    /** nullable persistent field */
    private Date sjtime;

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

    /** nullable persistent field */
    private Integer payMode;

    /** full constructor */
    public Cart(Long id, String username, String lcname, String commenduser, String ordid, String zsname, String tel, String email, String address, String ztai, Integer trans, Integer upgrade, Date sjtime, String name, Double transamt, Long serviceyear, Integer datetype, String simplename, String sqdj, Integer flag,Integer payMode) {
        this.id = id;
        this.username = username;
        this.lcname = lcname;
        this.commenduser = commenduser;
        this.ordid = ordid;
        this.zsname = zsname;
        this.tel = tel;
        this.email = email;
        this.address = address;
        this.ztai = ztai;
        this.trans = trans;
        this.upgrade = upgrade;
        this.sjtime = sjtime;
        this.name = name;
        this.transamt = transamt;
        this.serviceyear = serviceyear;
        this.datetype = datetype;
        this.simplename = simplename;
        this.sqdj = sqdj;
        this.flag = flag;
        this.payMode = payMode;
    }

    /** default constructor */
    public Cart() {
    }

    /** minimal constructor */
    public Cart(Long id) {
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

    /** 
     *            @hibernate.property
     *             column="COMMENDUSER"
     *             length="50"
     *         
     */
    public String getCommenduser() {
        return this.commenduser;
    }

    public void setCommenduser(String commenduser) {
        this.commenduser = commenduser;
    }

    /** 
     *            @hibernate.property
     *             column="ORDID"
     *             length="16"
     *         
     */
    public String getOrdid() {
        return this.ordid;
    }

    public void setOrdid(String ordid) {
        this.ordid = ordid;
    }

    /** 
     *            @hibernate.property
     *             column="ZSNAME"
     *             length="50"
     *         
     */
    public String getZsname() {
        return this.zsname;
    }

    public void setZsname(String zsname) {
        this.zsname = zsname;
    }

    /** 
     *            @hibernate.property
     *             column="TEL"
     *             length="50"
     *         
     */
    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    /** 
     *            @hibernate.property
     *             column="EMAIL"
     *             length="50"
     *         
     */
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /** 
     *            @hibernate.property
     *             column="ADDRESS"
     *             length="50"
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
     *             column="ZTAI"
     *             length="1"
     *         
     */
    public String getZtai() {
        return this.ztai;
    }

    public void setZtai(String ztai) {
        this.ztai = ztai;
    }

    /** 
     *            @hibernate.property
     *             column="TRANS"
     *             length="1"
     *         
     */
    public Integer getTrans() {
        return this.trans;
    }

    public void setTrans(Integer trans) {
        this.trans = trans;
    }

    /** 
     *            @hibernate.property
     *             column="UPGRADE"
     *             length="1"
     *         
     */
    public Integer getUpgrade() {
        return this.upgrade;
    }

    public void setUpgrade(Integer upgrade) {
        this.upgrade = upgrade;
    }

    /** 
     *            @hibernate.property
     *             column="SJTIME"
     *             length="7"
     *         
     */
    public Date getSjtime() {
        return this.sjtime;
    }

    public void setSjtime(Date sjtime) {
        this.sjtime = sjtime;
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

    public Integer getPayMode() {
        return payMode;
    }

    public void setPayMode(Integer payMode) {
        this.payMode = payMode;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
