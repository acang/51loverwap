package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-07 02:12:08

 *        @hibernate.class
 *         table="LYCART"
 *     
 */
public class Lycart implements Serializable {

    /** identifier field */
    private Long hyid;

    /** persistent field */
    private Long lyid;

    /** nullable persistent field */
    private Date updatetime;
    

    /** full constructor */
    public Lycart(Long hyid, Long lyid, Date updatetime) {
    	this.hyid = hyid;
    	this.lyid = lyid;
        this.updatetime = updatetime;
    }

    /** default constructor */
    public Lycart() {
    }


    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Long"
     *             column="HYID"
     *         
     */
    public Long getHyid() {
        return this.hyid;
    }

    public void setHyid(Long hyid) {
        this.hyid = hyid;
    }

    /** 
     *            @hibernate.id
     *             generator-class="assigned"
     *             type="java.lang.Long"
     *             column="LYID"
     *         
     */
    public Long getLyid() {
        return this.lyid;
    }

    public void setLyid(Long lyid) {
        this.lyid = lyid;
    }

    
    /** 
     *            @hibernate.property
     *             column="UPDATETIME"
     *             length="7"
     *         
     */
    public Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    
    

    public String toString() {
        return new ToStringBuilder(this)
 .append("hyid", getHyid())
            .toString();
    }

}
