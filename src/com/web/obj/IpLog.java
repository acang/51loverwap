package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-23 03:43:43

 *        @hibernate.class
 *         table="IP_LOG"
 *     
 */
public class IpLog implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private Long userid;

    /** nullable persistent field */
    private String username;

    /** nullable persistent field */
    private Integer isadmin;

    /** nullable persistent field */
    private Long userip;

    /** nullable persistent field */
    private String userstrip;

    /** nullable persistent field */
    private Date logintime;

    /** nullable persistent field */
    private Date logouttime;

    /** nullable persistent field */
    private Long staytime;

    /** nullable persistent field */
    private String logintimestr;

    /** nullable persistent field */
    private String sessionid;

    /** full constructor */
    public IpLog(Long id, Long userid, String username, Integer isadmin, Long userip, String userstrip, Date logintime, Date logouttime, Long staytime, String logintimestr, String sessionid) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.isadmin = isadmin;
        this.userip = userip;
        this.userstrip = userstrip;
        this.logintime = logintime;
        this.logouttime = logouttime;
        this.staytime = staytime;
        this.logintimestr = logintimestr;
        this.sessionid = sessionid;
    }

    /** default constructor */
    public IpLog() {
    }

    /** minimal constructor */
    public IpLog(Long id) {
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
     *             column="USERID"
     *             length="10"
     *         
     */
    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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
     *             column="ISADMIN"
     *             length="2"
     *         
     */
    public Integer getIsadmin() {
        return this.isadmin;
    }

    public void setIsadmin(Integer isadmin) {
        this.isadmin = isadmin;
    }

    /** 
     *            @hibernate.property
     *             column="USERIP"
     *             length="10"
     *         
     */
    public Long getUserip() {
        return this.userip;
    }

    public void setUserip(Long userip) {
        this.userip = userip;
    }

    /** 
     *            @hibernate.property
     *             column="USERSTRIP"
     *             length="20"
     *         
     */
    public String getUserstrip() {
        return this.userstrip;
    }

    public void setUserstrip(String userstrip) {
        this.userstrip = userstrip;
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

    /** 
     *            @hibernate.property
     *             column="LOGOUTTIME"
     *             length="7"
     *         
     */
    public Date getLogouttime() {
        return this.logouttime;
    }

    public void setLogouttime(Date logouttime) {
        this.logouttime = logouttime;
    }

    /** 
     *            @hibernate.property
     *             column="STAYTIME"
     *             length="10"
     *         
     */
    public Long getStaytime() {
        return this.staytime;
    }

    public void setStaytime(Long staytime) {
        this.staytime = staytime;
    }

    /** 
     *            @hibernate.property
     *             column="LOGINTIMESTR"
     *             length="8"
     *         
     */
    public String getLogintimestr() {
        return this.logintimestr;
    }

    public void setLogintimestr(String logintimestr) {
        this.logintimestr = logintimestr;
    }

    /** 
     *            @hibernate.property
     *             column="SESSIONID"
     *             length="35"
     *         
     */
    public String getSessionid() {
        return this.sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
