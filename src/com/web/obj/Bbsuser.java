package com.web.obj;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-21 06:39:11

 *        @hibernate.class
 *         table="BBSUSER"
 *     
 */
public class Bbsuser implements Serializable {

    /** identifier field */
    private Long hyid;

    /** nullable persistent field */
    private String username;

    /** nullable persistent field */
    private String lcname;

    /** nullable persistent field */
    private Long grade;

    /** nullable persistent field */
    private Long topics;

    /** nullable persistent field */
    private Long commend;

    /** nullable persistent field */
    private Long resum;

    /** nullable persistent field */
    private Long moneys;
    
    /** nullable persistent field */
    private Date regtime;

    /** nullable persistent field */
    private Long staytime;
    private Long deltopics;
    private Long delresum;
    private Long topicsmoney;
    private Long commendmoney;
    private Long resummoney;
    private Long moneynew;
    private Long zans;
    private Long zanmoney;
    private Long replynum;
    private Long replymoney;
    private Long deltopicsmoney;
    private Long topicsnew;
    private Long resumnew;
    private Long sharenumber;
    /** full constructor */
    public Bbsuser(Long hyid, String username, String lcname, Long grade, Long topics,
                   Long commend, Long resum, Long moneys, Date regtime, Long staytime,
                   Long deltopics,Long delresum,Long topicsmoney,Long commendmoney,Long resummoney,
                   Long moneynew,Long zans,Long zanmoney,Long replynum,Long replymoney,Long deltopicsmoney,
                   Long topicsnew,Long resumnew

    ) {
        this.hyid = hyid;
        this.username = username;
        this.lcname = lcname;
        this.grade = grade;
        this.topics = topics;
        this.commend = commend;
        this.resum = resum;
        this.moneys = moneys;
        this.regtime = regtime;
        this.staytime = staytime;
        this.deltopics = deltopics;
        this.delresum = delresum;
        this.topicsmoney = topicsmoney;
        this.commendmoney = commendmoney;
        this.resummoney = resummoney;
        this.moneynew = moneynew;
        this.zans = zans;
        this.zanmoney = zanmoney;
        this.replynum = replynum;
        this.replymoney = replymoney;
        this.deltopicsmoney = deltopicsmoney;
        this.topicsnew = topicsnew;
        this.resumnew = resumnew;

    }

    /** default constructor */
    public Bbsuser() {
    }

    /** minimal constructor */
    public Bbsuser(Long hyid) {
        this.hyid = hyid;
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
     *            @hibernate.property
     *             column="USERNAME"
     *             length="50"
     *         
     */
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /** 
     *            @hibernate.property
     *             column="LCNAME"
     *             length="50"
     *         
     */
    public String getLcname() {
        return this.lcname;
    }

    public void setLcname(String lcname) {
        this.lcname = lcname;
    }

    /** 
     *            @hibernate.property
     *             column="GRADE"
     *             length="10"
     *         
     */
    public Long getGrade() {
        return this.grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
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
     *             column="COMMEND"
     *             length="10"
     *         
     */
    public Long getCommend() {
        return this.commend;
    }

    public void setCommend(Long commend) {
        this.commend = commend;
    }

    /** 
     *            @hibernate.property
     *             column="RESUM"
     *             length="10"
     *         
     */
    public Long getResum() {
        return this.resum;
    }

    public void setResum(Long resum) {
        this.resum = resum;
    }

    /** 
     *            @hibernate.property
     *             column="MONEYS"
     *             length="10"
     *         
     */
    public Long getMoneys() {
        return this.moneys;
    }

    public void setMoneys(Long moneys) {
        this.moneys = moneys;
    }

    /** 
     *            @hibernate.property
     *             column="REGTIME"
     *             length="7"
     *         
     */
    public Date getRegtime() {
        return this.regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    /** 
     *            @hibernate.property
     *             column="STAYTIME"
     *             length="10"
     *         
     */
    public Long getStaytime() {
        return this.staytime;
    }

    public void setStaytime(Long staytime) {
        this.staytime = staytime;
    }

    public Long getDeltopics() {
        return deltopics;
    }

    public void setDeltopics(Long deltopics) {
        this.deltopics = deltopics;
    }

    public Long getDelresum() {
        return delresum;
    }

    public void setDelresum(Long delresum) {
        this.delresum = delresum;
    }

    public Long getTopicsmoney() {
        return topicsmoney;
    }

    public void setTopicsmoney(Long topicsmoney) {
        this.topicsmoney = topicsmoney;
    }

    public Long getCommendmoney() {
        return commendmoney;
    }

    public void setCommendmoney(Long commendmoney) {
        this.commendmoney = commendmoney;
    }

    public Long getResummoney() {
        return resummoney;
    }

    public void setResummoney(Long resummoney) {
        this.resummoney = resummoney;
    }

    public Long getMoneynew() {
        return moneynew;
    }

    public void setMoneynew(Long moneynew) {
        this.moneynew = moneynew;
    }

    public Long getZans() {
        return zans;
    }

    public void setZans(Long zans) {
        this.zans = zans;
    }

    public Long getZanmoney() {
        return zanmoney;
    }

    public void setZanmoney(Long zanmoney) {
        this.zanmoney = zanmoney;
    }

    public Long getReplynum() {
        return replynum;
    }

    public void setReplynum(Long replynum) {
        this.replynum = replynum;
    }

    public Long getReplymoney() {
        return replymoney;
    }

    public void setReplymoney(Long replymoney) {
        this.replymoney = replymoney;
    }

    public Long getDeltopicsmoney() {
        return deltopicsmoney;
    }

    public void setDeltopicsmoney(Long deltopicsmoney) {
        this.deltopicsmoney = deltopicsmoney;
    }

    public Long getTopicsnew() {
        return topicsnew;
    }

    public void setTopicsnew(Long topicsnew) {
        this.topicsnew = topicsnew;
    }

    public Long getResumnew() {
        return resumnew;
    }

    public void setResumnew(Long resumnew) {
        this.resumnew = resumnew;
    }

	public Long getSharenumber() {
		return sharenumber;
	}

	public void setSharenumber(Long sharenumber) {
		this.sharenumber = sharenumber;
	}

	public String toString() {
        return new ToStringBuilder(this)
 .append("hyid", getHyid())
            .toString();
    }

}
