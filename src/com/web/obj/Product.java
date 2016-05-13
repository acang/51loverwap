package com.web.obj;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-17 05:31:07

 *        @hibernate.class
 *         table="PRODUCT"
 *     
 */
public class Product implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String name;

    /** nullable persistent field */
    private Long transamt;

    /** nullable persistent field */
    private String promotion;

    /** nullable persistent field */
    private Long promotionint;

    /** nullable persistent field */
    private String explan;

    /** nullable persistent field */
    private Long serviceyear;

    /** nullable persistent field */
    private Integer isdel;

    /** nullable persistent field */
    private Integer datetype;

    /** nullable persistent field */
    private String sqdj;

    /** nullable persistent field */
    private Integer flag;

    /** nullable persistent field */
    private String simplename;
    
    /** nullable persistent field */
    private Integer orderno;

    /** full constructor */
    public Product(Long id, String name, Long transamt, String promotion, Long promotionint, String explan, Long serviceyear, Integer isdel, Integer datetype, String sqdj, Integer flag, String simplename) {
        this.id = id;
        this.name = name;
        this.transamt = transamt;
        this.promotion = promotion;
        this.promotionint = promotionint;
        this.explan = explan;
        this.serviceyear = serviceyear;
        this.isdel = isdel;
        this.datetype = datetype;
        this.sqdj = sqdj;
        this.flag = flag;
        this.simplename = simplename;
    }

    /** default constructor */
    public Product() {
    }

    /** minimal constructor */
    public Product(Long id) {
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
    public Long getTransamt() {
        return this.transamt;
    }

    public void setTransamt(Long transamt) {
        this.transamt = transamt;
    }

    /** 
     *            @hibernate.property
     *             column="PROMOTION"
     *             length="200"
     *         
     */
    public String getPromotion() {
        return this.promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    /** 
     *            @hibernate.property
     *             column="PROMOTIONINT"
     *             length="10"
     *         
     */
    public Long getPromotionint() {
        return this.promotionint;
    }

    public void setPromotionint(Long promotionint) {
        this.promotionint = promotionint;
    }

    /** 
     *            @hibernate.property
     *             column="EXPLAN"
     *             length="200"
     *         
     */
    public String getExplan() {
        return this.explan;
    }

    public void setExplan(String explan) {
        this.explan = explan;
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
     *             column="ISDEL"
     *             length="1"
     *         
     */
    public Integer getIsdel() {
        return this.isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
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
     *             column="ORDERNO"
     *             length="1"
     *         
     */
    public Integer getOrderno() {
        return this.orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
