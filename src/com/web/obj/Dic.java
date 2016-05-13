package com.web.obj;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-02-27 05:52:09

 *        @hibernate.class
 *         table="DIC"
 *     
 */
public class Dic implements Serializable {

    /** identifier field */
    private Long dicid;

    /** nullable persistent field */
    private String dicnumber;

    /** nullable persistent field */
    private String content;

    /** nullable persistent field */
    private String extend1;

    /** nullable persistent field */
    private String extend2;

    /** nullable persistent field */
    private String extend3;

    /** nullable persistent field */
    private String dictype;

    /** full constructor */
    public Dic(Long dicid, String dicnumber, String content, String extend1, String extend2, String extend3, String dictype) {
        this.dicid = dicid;
        this.dicnumber = dicnumber;
        this.content = content;
        this.extend1 = extend1;
        this.extend2 = extend2;
        this.extend3 = extend3;
        this.dictype = dictype;
    }

    /** default constructor */
    public Dic() {
    }

    /** minimal constructor */
    public Dic(Long dicid) {
        this.dicid = dicid;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Long"
     *             column="DICID"
     *         
     */
    public Long getDicid() {
        return this.dicid;
    }

    public void setDicid(Long dicid) {
        this.dicid = dicid;
    }

    /** 
     *            @hibernate.property
     *             column="DICNUMBER"
     *             length="50"
     *         
     */
    public String getDicnumber() {
        return this.dicnumber;
    }

    public void setDicnumber(String dicnumber) {
        this.dicnumber = dicnumber;
    }

    /** 
     *            @hibernate.property
     *             column="CONTENT"
     *             length="100"
     *         
     */
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /** 
     *            @hibernate.property
     *             column="EXTEND1"
     *             length="100"
     *         
     */
    public String getExtend1() {
        return this.extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    /** 
     *            @hibernate.property
     *             column="EXTEND2"
     *             length="100"
     *         
     */
    public String getExtend2() {
        return this.extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    /** 
     *            @hibernate.property
     *             column="EXTEND3"
     *             length="100"
     *         
     */
    public String getExtend3() {
        return this.extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }

    /** 
     *            @hibernate.property
     *             column="DICTYPE"
     *             length="5"
     *         
     */
    public String getDictype() {
        return this.dictype;
    }

    public void setDictype(String dictype) {
        this.dictype = dictype;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("dicid", getDicid())
            .toString();
    }

}
