package com.web.obj;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * gen by HbmTools2, 2009-02-27 05:52:09

 *        @hibernate.class
 *         table="BCS"
 *
 */
public class Bc implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String content;

    /** full constructor */
    public Bc(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    /** default constructor */
    public Bc() {
    }

    /** minimal constructor */
    public Bc(Long id) {
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
    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString()+":"+this.content.length();
    }

}
