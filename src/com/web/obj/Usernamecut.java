package com.web.obj;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-02-27 05:52:09

 *        @hibernate.class
 *         table="USERNAMECUT"
 *     
 */
public class Usernamecut implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String usernamecut;

    /** nullable persistent field */
    private String reason;

    /** full constructor */
    public Usernamecut(Long id, String usernamecut, String reason) {
        this.id = id;
        this.usernamecut = usernamecut;
        this.reason = reason;
    }

    /** default constructor */
    public Usernamecut() {
    }

    /** minimal constructor */
    public Usernamecut(Long id) {
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
     *             column="USERNAMECUT"
     *             length="50"
     *         
     */
    public String getUsernamecut() {
        return this.usernamecut;
    }

    public void setUsernamecut(String usernamecut) {
        this.usernamecut = usernamecut;
    }
    
    /** 
     *            @hibernate.property
     *             column="REASON"
     *             length="100"
     *         
     */
    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }



    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
