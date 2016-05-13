package com.web.obj;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-02 01:06:56

 *        @hibernate.class
 *         table="FRIENDSITE"
 *     
 */
public class Friendsite implements Serializable {

    /** identifier field */
    private Integer id;

    /** nullable persistent field */
    private Integer linktype;

    /** nullable persistent field */
    private String sitename;

    /** nullable persistent field */
    private String siteurl;

    /** nullable persistent field */
    private String siteintro;

    /** nullable persistent field */
    private String logourl;

    /** nullable persistent field */
    private String siteadmin;

    /** nullable persistent field */
    private String email;

    /** nullable persistent field */
    private String sitepassword;

    /** persistent field */
    private int isgood;

    /** persistent field */
    private int isok;

    /** nullable persistent field */
    private Integer xuhao;

    /** full constructor */
    public Friendsite(Integer id, Integer linktype, String sitename, String siteurl, String siteintro, String logourl, String siteadmin, String email, String sitepassword, int isgood, int isok, Integer xuhao) {
        this.id = id;
        this.linktype = linktype;
        this.sitename = sitename;
        this.siteurl = siteurl;
        this.siteintro = siteintro;
        this.logourl = logourl;
        this.siteadmin = siteadmin;
        this.email = email;
        this.sitepassword = sitepassword;
        this.isgood = isgood;
        this.isok = isok;
        this.xuhao = xuhao;
    }

    /** default constructor */
    public Friendsite() {
    }

    /** minimal constructor */
    public Friendsite(Integer id, int isgood, int isok) {
        this.id = id;
        this.isgood = isgood;
        this.isok = isok;
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
     *             column="LINKTYPE"
     *             length="2"
     *         
     */
    public Integer getLinktype() {
        return this.linktype;
    }

    public void setLinktype(Integer linktype) {
        this.linktype = linktype;
    }

    /** 
     *            @hibernate.property
     *             column="SITENAME"
     *             length="50"
     *         
     */
    public String getSitename() {
        return this.sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    /** 
     *            @hibernate.property
     *             column="SITEURL"
     *             length="100"
     *         
     */
    public String getSiteurl() {
        return this.siteurl;
    }

    public void setSiteurl(String siteurl) {
        this.siteurl = siteurl;
    }

    /** 
     *            @hibernate.property
     *             column="SITEINTRO"
     *             length="200"
     *         
     */
    public String getSiteintro() {
        return this.siteintro;
    }

    public void setSiteintro(String siteintro) {
        this.siteintro = siteintro;
    }

    /** 
     *            @hibernate.property
     *             column="LOGOURL"
     *             length="100"
     *         
     */
    public String getLogourl() {
        return this.logourl;
    }

    public void setLogourl(String logourl) {
        this.logourl = logourl;
    }

    /** 
     *            @hibernate.property
     *             column="SITEADMIN"
     *             length="50"
     *         
     */
    public String getSiteadmin() {
        return this.siteadmin;
    }

    public void setSiteadmin(String siteadmin) {
        this.siteadmin = siteadmin;
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
     *             column="SITEPASSWORD"
     *             length="50"
     *         
     */
    public String getSitepassword() {
        return this.sitepassword;
    }

    public void setSitepassword(String sitepassword) {
        this.sitepassword = sitepassword;
    }

    /** 
     *            @hibernate.property
     *             column="ISGOOD"
     *             length="1"
     *             not-null="true"
     *         
     */
    public int getIsgood() {
        return this.isgood;
    }

    public void setIsgood(int isgood) {
        this.isgood = isgood;
    }

    /** 
     *            @hibernate.property
     *             column="ISOK"
     *             length="1"
     *             not-null="true"
     *         
     */
    public int getIsok() {
        return this.isok;
    }

    public void setIsok(int isok) {
        this.isok = isok;
    }

    /** 
     *            @hibernate.property
     *             column="XUHAO"
     *             length="5"
     *         
     */
    public Integer getXuhao() {
        return this.xuhao;
    }

    public void setXuhao(Integer xuhao) {
        this.xuhao = xuhao;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
