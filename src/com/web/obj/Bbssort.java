package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-19 03:18:12

 *        @hibernate.class
 *         table="BBSSORT"
 *     
 */
public class Bbssort implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private Integer orders;

    /** nullable persistent field */
    private String sortname;

    /** nullable persistent field */
    private String content;

    /** nullable persistent field */
    private Long topics;

    /** nullable persistent field */
    private Date stime;

    /** full constructor */
    public Bbssort(Long id, Integer orders, String sortname, String content, Long topics, Date stime) {
        this.id = id;
        this.orders = orders;
        this.sortname = sortname;
        this.content = content;
        this.topics = topics;
        this.stime = stime;
    }

    /** default constructor */
    public Bbssort() {
    }

    /** minimal constructor */
    public Bbssort(Long id) {
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
     *             column="ORDERS"
     *             length="5"
     *         
     */
    public Integer getOrders() {
        return this.orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    /** 
     *            @hibernate.property
     *             column="SORTNAME"
     *             length="50"
     *         
     */
    public String getSortname() {
        return this.sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    /** 
     *            @hibernate.property
     *             column="CONTENT"
     *             length="200"
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
     *             column="TOPICS"
     *             length="10"
     *         
     */
    public Long getTopics() {
        return this.topics;
    }

    public void setTopics(Long topics) {
        this.topics = topics;
    }

    /** 
     *            @hibernate.property
     *             column="S_TIME"
     *             length="7"
     *         
     */
    public Date getStime() {
        return this.stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
