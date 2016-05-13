package com.web.obj;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-02-27 05:52:09

 *        @hibernate.class
 *         table="SENSITIVE"
 *     
 */
public class Sensitive implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String sensitive;


    /** full constructor */
    public Sensitive(Long id, String sensitive) {
        this.id = id;
        this.sensitive = sensitive;
    }

    /** default constructor */
    public Sensitive() {
    }

    /** minimal constructor */
    public Sensitive(Long id) {
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
     *             column="SENSITIVE"
     *             length="50"
     *         
     */
    public String getSensitive() {
        return this.sensitive;
    }

    public void setSensitive(String sensitive) {
        this.sensitive = sensitive;
    }



    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
