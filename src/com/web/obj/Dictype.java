package com.web.obj;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-02-27 05:52:09

 *        @hibernate.class
 *         table="DICTYPE"
 *     
 */
public class Dictype implements Serializable {

    /** identifier field */
    private String typeid;

    /** nullable persistent field */
    private String typename;

    /** nullable persistent field */
    private Integer typetype;

    /** nullable persistent field */
    private String extend1name;

    /** nullable persistent field */
    private String extend2name;

    /** nullable persistent field */
    private String extend3name;

    /** nullable persistent field */
    private String extenduse;

    /** full constructor */
    public Dictype(String typeid, String typename, Integer typetype, String extend1name, String extend2name, String extend3name, String extenduse) {
        this.typeid = typeid;
        this.typename = typename;
        this.typetype = typetype;
        this.extend1name = extend1name;
        this.extend2name = extend2name;
        this.extend3name = extend3name;
        this.extenduse = extenduse;
    }

    /** default constructor */
    public Dictype() {
    }

    /** minimal constructor */
    public Dictype(String typeid) {
        this.typeid = typeid;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="TYPEID"
     *         
     */
    public String getTypeid() {
        return this.typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    /** 
     *            @hibernate.property
     *             column="TYPENAME"
     *             length="100"
     *         
     */
    public String getTypename() {
        return this.typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    /** 
     *            @hibernate.property
     *             column="TYPETYPE"
     *             length="1"
     *         
     */
    public Integer getTypetype() {
        return this.typetype;
    }

    public void setTypetype(Integer typetype) {
        this.typetype = typetype;
    }

    /** 
     *            @hibernate.property
     *             column="EXTEND1NAME"
     *             length="20"
     *         
     */
    public String getExtend1name() {
        return this.extend1name;
    }

    public void setExtend1name(String extend1name) {
        this.extend1name = extend1name;
    }

    /** 
     *            @hibernate.property
     *             column="EXTEND2NAME"
     *             length="20"
     *         
     */
    public String getExtend2name() {
        return this.extend2name;
    }

    public void setExtend2name(String extend2name) {
        this.extend2name = extend2name;
    }

    /** 
     *            @hibernate.property
     *             column="EXTEND3NAME"
     *             length="20"
     *         
     */
    public String getExtend3name() {
        return this.extend3name;
    }

    public void setExtend3name(String extend3name) {
        this.extend3name = extend3name;
    }

    /** 
     *            @hibernate.property
     *             column="EXTENDUSE"
     *             length="3"
     *         
     */
    public String getExtenduse() {
        return this.extenduse;
    }

    public void setExtenduse(String extenduse) {
        this.extenduse = extenduse;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("typeid", getTypeid())
            .toString();
    }

}
