package com.web.obj;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-19 06:51:06

 *        @hibernate.class
 *         table="CARTORDERID"
 *     
 */
public class Cartorderid implements Serializable {

    /** identifier field */
    private String id;

    /** persistent field */
    private String corderid;

    /** full constructor */
    public Cartorderid(String id, String corderid) {
        this.id = id;
        this.corderid = corderid;
    }

    /** default constructor */
    public Cartorderid() {
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.String"
     *             column="ID"
     *         
     */
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /** 
     *            @hibernate.property
     *             column="CORDERID"
     *             length="16"
     *             not-null="true"
     *         
     */
    public String getCorderid() {
        return this.corderid;
    }

    public void setCorderid(String corderid) {
        this.corderid = corderid;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
