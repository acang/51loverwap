package com.web.obj;

import java.io.Serializable;
import java.sql.Blob;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-02-27 06:02:13

 *        @hibernate.class
 *         table="BCB"
 *     
 */
public class Bcb implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private Blob content;

    /** full constructor */
    public Bcb(Long id, Blob content) {
        this.id = id;
        this.content = content;
    }

    /** default constructor */
    public Bcb() {
    }

    /** minimal constructor */
    public Bcb(Long id) {
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
     *             column="CONTENT"
     *             length="4000"
     *         
     */
    public Blob getContent() {
        return this.content;
    }

    public void setContent(Blob content) {
        this.content = content;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
