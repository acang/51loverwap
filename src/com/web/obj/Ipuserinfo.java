package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2011-01-14 14:49:08

 *        @hibernate.class
 *         table="IPUSERINFO"
 *     
 */
public class Ipuserinfo implements Serializable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String username;

    /** persistent field */
    private String ipaddress;

    /** nullable persistent field */
    private Date logintime;

    /** full constructor */
    public Ipuserinfo(Long id, String username, String ipaddress, Date logintime) {
        this.id = id;
        this.username = username;
        this.ipaddress = ipaddress;
        this.logintime = logintime;
    }

    /** default constructor */
    public Ipuserinfo() {
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
     *             not-null="false"
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
     *             column="IPADDRESS"
     *             length="20"
     *             not-null="false"
     *         
     */
    public String getIpaddress() {
        return this.ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }


    /** 
     *            @hibernate.property
     *             column="LOGINTIME"
     *             length="7"
     *         
     */
    public Date getLogintime() {
        return this.logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }



    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
