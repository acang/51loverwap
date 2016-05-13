package com.web.obj;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HCG v1.0, create by dc, 2011-11-22 01:59:14

 *        @hibernate.class
 *         table="USER_BJD"
 *     
 */
public class UserBjd implements Serializable {

    /** identifier field */
    private Long hyid;

    /** persistent field */
    private long bjdnumber;

    /** full constructor */
    public UserBjd(Long hyid, long bjdnumber) {
        this.hyid = hyid;
        this.bjdnumber = bjdnumber;
    }

    /** default constructor */
    public UserBjd() {
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
     *             column="BJDNUMBER"
     *             length="10"
     *             not-null="true"
     *         
     */
    public long getBjdnumber() {
        return this.bjdnumber;
    }

    public void setBjdnumber(long bjdnumber) {
        this.bjdnumber = bjdnumber;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("hyid", getHyid())
            .toString();
    }

}
