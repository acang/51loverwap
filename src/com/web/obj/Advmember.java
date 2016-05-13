package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-07 02:12:08

 *        @hibernate.class
 *         table="ADV_MEMBER"
 *     
 */
public class Advmember implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String advmembername;

    /** persistent field */
    private Integer advmembercode;

    /** nullable persistent field */
    private Integer flag;
    
    /** nullable persistent field */
    private Date createtime;
    
    /** nullable persistent field */
    private String oper;


    /** full constructor */
    public Advmember(Long id, String advmembername, Integer advmembercode, Integer flag, Date createtime, String oper) {
        this.id = id;
        this.advmembername = advmembername;
        this.advmembercode = advmembercode;
        this.flag = flag;
        this.createtime = createtime;
        this.oper = oper;
    }

    /** default constructor */
    public Advmember() {
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
     *             column="ADV_MEMBER_NAME"
     *             length="20"
     *             not-null="true"
     *         
     */
    public String getAdvmembername() {
        return this.advmembername;
    }

    public void setAdvmembername(String advmembername) {
        this.advmembername = advmembername;
    }

    /** 
     *            @hibernate.property
     *             column="ADV_MEMBER_CODE"
     *             length="2"
     *         
     */
    public Integer getAdvmembercode() {
        return this.advmembercode;
    }

    public void setAdvmembercode(Integer advmembercode) {
        this.advmembercode = advmembercode;
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
     *             column="CREATETIME"
     *             length="7"
     *         
     */
    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    
    /** 
     *            @hibernate.property
     *             column="OPER"
     *             length="50"
     *         
     */
    public String getOper() {
        return this.oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }
    

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
