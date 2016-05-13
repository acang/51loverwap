package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** 
 * gen by HbmTools2, 2009-03-20 03:08:22

 *        @hibernate.class
 *         table="BBS"
 *     
 */
public class Bb implements Serializable {

    /** identifier field */
    private Long id;

    /** nullable persistent field */
    private String author;

    /** nullable persistent field */
    private Date stime;

    /** nullable persistent field */
    private String title;

    /** nullable persistent field */
    private Long content;

    /** nullable persistent field */
    private Long reCount;

    /** nullable persistent field */
    private Long reId;

    /** nullable persistent field */
    private Integer commend;

    /** nullable persistent field */
    private Long userId;

    /** nullable persistent field */
    private String userName;

    /** nullable persistent field */
    private Long hits;

    /** nullable persistent field */
    private String censor;

    /** nullable persistent field */
    private String ischeck;

    /** nullable persistent field */
    private Date reTime;

    /** nullable persistent field */
    private Integer ontop;

    /** nullable persistent field */
    private Long sortid;
    
    /** nullable persistent field */
    private String reason;
    /** nullable persistent field */
    private Integer zan;
    private Integer topicMoney;
    private Integer replyMoney;
    /** full constructor */
    public Bb(Long id, String author, Date stime, String title, Long content, Long reCount, Long reId, Integer commend, Long userId, String userName, Long hits, String censor, String ischeck, Date reTime, Integer ontop, Long sortid, String reason,Integer zan,Integer topicMoney,Integer replyMoney) {
        this.id = id;
        this.author = author;
        this.stime = stime;
        this.title = title;
        this.content = content;
        this.reCount = reCount;
        this.reId = reId;
        this.commend = commend;
        this.userId = userId;
        this.userName = userName;
        this.hits = hits;
        this.censor = censor;
        this.ischeck = ischeck;
        this.reTime = reTime;
        this.ontop = ontop;
        this.sortid = sortid;
        this.reason = reason;
        this.zan = zan;
        this.topicMoney = topicMoney;
        this.replyMoney = replyMoney;
    }

    /** default constructor */
    public Bb() {
    }

    /** minimal constructor */
    public Bb(Long id) {
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
     *             column="AUTHOR"
     *             length="50"
     *         
     */
    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    /** 
     *            @hibernate.property
     *             column="TITLE"
     *             length="100"
     *         
     */
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /** 
     *            @hibernate.property
     *             column="CONTENT"
     *             length="10"
     *         
     */
    public Long getContent() {
        return this.content;
    }

    public void setContent(Long content) {
        this.content = content;
    }

    /** 
     *            @hibernate.property
     *             column="RE_COUNT"
     *             length="10"
     *         
     */
    public Long getReCount() {
        return this.reCount;
    }

    public void setReCount(Long reCount) {
        this.reCount = reCount;
    }

    /** 
     *            @hibernate.property
     *             column="RE_ID"
     *             length="10"
     *         
     */
    public Long getReId() {
        return this.reId;
    }

    public void setReId(Long reId) {
        this.reId = reId;
    }

    /** 
     *            @hibernate.property
     *             column="COMMEND"
     *             length="5"
     *         
     */
    public Integer getCommend() {
        return this.commend;
    }

    public void setCommend(Integer commend) {
        this.commend = commend;
    }

    /** 
     *            @hibernate.property
     *             column="USER_ID"
     *             length="10"
     *         
     */
    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /** 
     *            @hibernate.property
     *             column="USER_NAME"
     *             length="50"
     *         
     */
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** 
     *            @hibernate.property
     *             column="HITS"
     *             length="10"
     *         
     */
    public Long getHits() {
        return this.hits;
    }

    public void setHits(Long hits) {
        this.hits = hits;
    }

    /** 
     *            @hibernate.property
     *             column="CENSOR"
     *             length="50"
     *         
     */
    public String getCensor() {
        return this.censor;
    }

    public void setCensor(String censor) {
        this.censor = censor;
    }

    /** 
     *            @hibernate.property
     *             column="ISCHECK"
     *             length="1"
     *         
     */
    public String getIscheck() {
        return this.ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    /** 
     *            @hibernate.property
     *             column="RE_TIME"
     *             length="7"
     *         
     */
    public Date getReTime() {
        return this.reTime;
    }

    public void setReTime(Date reTime) {
        this.reTime = reTime;
    }

    /** 
     *            @hibernate.property
     *             column="ONTOP"
     *             length="2"
     *         
     */
    public Integer getOntop() {
        return this.ontop;
    }

    public void setOntop(Integer ontop) {
        this.ontop = ontop;
    }

    /** 
     *            @hibernate.property
     *             column="SORTID"
     *             length="10"
     *         
     */
    public Long getSortid() {
        return this.sortid;
    }

    public void setSortid(Long sortid) {
        this.sortid = sortid;
    }
    
    /** 
     *            @hibernate.property
     *             column="REASON"
     *             length="50"
     *         
     */
    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getZan() {
        return zan;
    }

    public void setZan(Integer zan) {
        this.zan = zan;
    }

    public Integer getTopicMoney() {
        return topicMoney;
    }

    public void setTopicMoney(Integer topicMoney) {
        this.topicMoney = topicMoney;
    }

    public Integer getReplyMoney() {
        return replyMoney;
    }

    public void setReplyMoney(Integer replyMoney) {
        this.replyMoney = replyMoney;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("id", getId())
            .toString();
    }

}
