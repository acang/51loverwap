package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-01 12:32:12

 *        @hibernate.class
 *         table="RECORD"
 *     
 */
public class Record implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String seename;

    /** nullable persistent field */
    private String toseename;

    /** nullable persistent field */
    private Date time;


    /** full constructor */
    public Record(Long id, String seename,String toseename, Date time) {
        this.id = id;
        this.seename = seename;
        this.toseename = toseename;
        this.time = time;
    }

    /** default constructor */
    public Record() {
    }

    /** minimal constructor */
    public Record(Long id) {
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
     *             column="SEENAME"
     *             length="50"
     *         
     */
    public String getSeename() {
        return this.seename;
    }

    public void setSeename(String seename) {
        this.seename = seename;
    }
    
    
    /** 
     *            @hibernate.property
     *             column="TOSEENAME"
     *             length="50"
     *         
     */
    public String getToseename() {
        return this.toseename;
    }

    public void setToseename(String toseename) {
        this.toseename = toseename;
    }

   
   

    /** 
     *            @hibernate.property
     *             column="TIME"
     *             length="7"
     *         
     */
    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
