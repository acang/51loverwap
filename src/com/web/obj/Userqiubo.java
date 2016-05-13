package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 *        @hibernate.class
 *         table="Userqiubo"
 *     
 */
public class Userqiubo implements Serializable {

    /** identifier field */
    private Long hyid;

    /** nullable persistent field */
    private Integer qiubo_number;

    /** full constructor */
    public Userqiubo(Long hyid, Integer qiubo_number) {
        this.hyid=hyid;
        this.qiubo_number=qiubo_number;
    }
    
    /** default constructor */
    public Userqiubo() {
    }

	public Long getHyid() {
		return hyid;
	}

	public void setHyid(Long hyid) {
		this.hyid = hyid;
	}

	public Integer getQiubo_number() {
		return qiubo_number;
	}

	public void setQiubo_number(Integer qiubo_number) {
		this.qiubo_number = qiubo_number;
	}
}
