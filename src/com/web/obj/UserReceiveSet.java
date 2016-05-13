package com.web.obj;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HCG v1.0, create by dc, 2011-11-22 01:59:14

 *        @hibernate.class
 *         table="USER_BJD"
 *     
 */
public class UserReceiveSet implements Serializable {

    /** identifier field */
    private Long hyid;

    /** persistent field */
    private Integer receiveyesno;
    
    /** persistent field */
    private Integer receivetype;

    /** full constructor */
    public UserReceiveSet(Long hyid, Integer receiveyesno,Integer receivetype) {
        this.hyid = hyid;
        this.receiveyesno = receiveyesno;
        this.receivetype=receivetype;
    }

    /** default constructor */
    public UserReceiveSet() {
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
     *             column="RECEIVE_YES_NO"
     *             length="2"
     *             not-null="true"
     *         
     */
    public Integer getReceiveyesno() {
        return this.receiveyesno;
    }

    public void setReceiveyesno(Integer receiveyesno) {
        this.receiveyesno = receiveyesno;
    }

    
    /** 
     *            @hibernate.property
     *             column="RECEIVE_TYPE"
     *             length="2"
     *             not-null="true"
     *         
     */
    public Integer getReceivetype() {
        return this.receivetype;
    }

    public void setReceivetype(Integer receivetype) {
        this.receivetype = receivetype;
    }
}
