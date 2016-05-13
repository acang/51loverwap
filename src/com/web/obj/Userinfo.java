package com.web.obj;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * gen by HbmTools2, 2009-03-17 10:16:08

 *        @hibernate.class
 *         table="USERINFO"
 *
 */
public class Userinfo implements Serializable {

    /** identifier field */
    private Long hyid;

    /** nullable persistent field */
    private String username;

    /** nullable persistent field */
    private String lcname;

    /** nullable persistent field */
    private String password;

    /** nullable persistent field */
    private String ask;

    /** nullable persistent field */
    private String answer;

    /** nullable persistent field */
    private String sex;

    /** nullable persistent field */
    private String address;

    /** nullable persistent field */
    private Date csdate;

    /** nullable persistent field */
    private String sx;

    /** nullable persistent field */
    private String xz;

    /** nullable persistent field */
    private String sg;

    /** nullable persistent field */
    private String tz;

    /** nullable persistent field */
    private String fs;

    /** nullable persistent field */
    private String whcd;

    /** nullable persistent field */
    private String xgtz;

    /** nullable persistent field */
    private String yyap;

    /** nullable persistent field */
    private String yx;

    /** nullable persistent field */
    private String hyzk;

    /** nullable persistent field */
    private Long jyyx;

    /** nullable persistent field */
    private String zf;

    /** nullable persistent field */
    private String email;

    /** nullable persistent field */
    private String oicq;

    /** nullable persistent field */
    private String sjtel;

    /** nullable persistent field */
    private String tel;
    /** nullable persistent field */
    private String wx;

    /** nullable persistent field */
    private Integer flag;

    /** nullable persistent field */
    private Date tjsj;

    /** nullable persistent field */
    private Long hots;

    /** nullable persistent field */
    private Date regtime;

    /** nullable persistent field */
    private Date lasttime;

    /** nullable persistent field */
    private Date regtime2;

    /** nullable persistent field */
    private Integer img;

    /** nullable persistent field */
    private Integer setzt;

    /** nullable persistent field */
    private String shr;

    /** nullable persistent field */
    private String shqk;

    /** nullable persistent field */
    private String httpurl;

    /** nullable persistent field */
    private String httpip;

    /** nullable persistent field */
    private Integer isdel;

    /** nullable persistent field */
    private Integer vip;

    /** nullable persistent field */
    private String s1;

    /** nullable persistent field */
    private String s2;

    /** nullable persistent field */
    private String s3;

    /** nullable persistent field */
    private Date zzsj;
    
    private Long lysize; 
    
    private Long isvcation;
    
    /** nullable persistent field */
    private Integer vacsjtel;
    
    /** nullable persistent field */
    private Integer vacemail;
    
    private String shield;
    
    /** nullable persistent field */
    private Date regtime5;

    public Long getIsvcation() {
		return isvcation;
	}

	public void setIsvcation(Long isvcation) {
		this.isvcation = isvcation;
	}

	public Long getLysize()
    {
        return lysize;
    }

    public void setLysize(Long lysize)
    {
        this.lysize = lysize;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    /** nullable persistent field */
    private String zy;
    
    /** nullable persistent field */
    private Date regtime3;
    
    /** nullable persistent field */
    private Date regtime4;
    
    /** nullable persistent field */
    private String area;
    /** nullable persistent field */
    private Integer important;

    /** full constructor */
    public Userinfo(Long hyid, String username, String lcname, String password, String ask, String answer, String sex, String address, Date csdate, String sx, String xz, String sg, String tz, String fs, String whcd, String xgtz, String yyap, String yx, String hyzk, Long jyyx, String zf, String email, String oicq, String sjtel, String tel,String wx, Integer flag, Date tjsj, Long hots, Date regtime, Date lasttime, Date regtime2, Integer img, Integer setzt, String shr, String shqk, String httpurl, String httpip, Integer isdel, Integer vip, String s1, String s2, Date zzsj,String zy,Date regtime3,Date regtime4,Integer important) {
        this.hyid = hyid;
        this.username = username;
        this.lcname = lcname;
        this.password = password;
        this.ask = ask;
        this.answer = answer;
        this.sex = sex;
        this.address = address;
        this.csdate = csdate;
        this.sx = sx;
        this.xz = xz;
        this.sg = sg;
        this.tz = tz;
        this.fs = fs;
        this.whcd = whcd;
        this.xgtz = xgtz;
        this.yyap = yyap;
        this.yx = yx;
        this.hyzk = hyzk;
        this.jyyx = jyyx;
        this.zf = zf;
        this.email = email;
        this.oicq = oicq;
        this.sjtel = sjtel;
        this.tel = tel;
        this.wx = wx;
        this.flag = flag;
        this.tjsj = tjsj;
        this.hots = hots;
        this.regtime = regtime;
        this.lasttime = lasttime;
        this.regtime2 = regtime2;
        this.img = img;
        this.setzt = setzt;
        this.shr = shr;
        this.shqk = shqk;
        this.httpurl = httpurl;
        this.httpip = httpip;
        this.isdel = isdel;
        this.vip = vip;
        this.s1 = s1;
        this.s2 = s2;
        this.zzsj = zzsj;
        this.zy = zy;
        this.regtime3 = regtime3;
        this.regtime4 = regtime4;
        this.important = important;

    }

    /** default constructor */
    public Userinfo() {
    }

    /** minimal constructor */
    public Userinfo(Long hyid) {
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
     *             column="PASSWORD"
     *             length="50"
     *
     */
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *            @hibernate.property
     *             column="ASK"
     *             length="100"
     *
     */
    public String getAsk() {
        return this.ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    /**
     *            @hibernate.property
     *             column="ANSWER"
     *             length="100"
     *
     */
    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     *            @hibernate.property
     *             column="SEX"
     *             length="2"
     *
     */
    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     *            @hibernate.property
     *             column="ADDRESS"
     *             length="50"
     *
     */
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *            @hibernate.property
     *             column="CSDATE"
     *             length="7"
     *
     */
    public Date getCsdate() {
        return this.csdate;
    }

    public void setCsdate(Date csdate) {
        this.csdate = csdate;
    }

    /**
     *            @hibernate.property
     *             column="SX"
     *             length="2"
     *
     */
    public String getSx() {
        return this.sx;
    }

    public void setSx(String sx) {
        this.sx = sx;
    }

    /**
     *            @hibernate.property
     *             column="XZ"
     *             length="2"
     *
     */
    public String getXz() {
        return this.xz;
    }

    public void setXz(String xz) {
        this.xz = xz;
    }

    /**
     *            @hibernate.property
     *             column="SG"
     *             length="2"
     *
     */
    public String getSg() {
        return this.sg;
    }

    public void setSg(String sg) {
        this.sg = sg;
    }

    /**
     *            @hibernate.property
     *             column="TZ"
     *             length="2"
     *
     */
    public String getTz() {
        return this.tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    /**
     *            @hibernate.property
     *             column="FS"
     *             length="2"
     *
     */
    public String getFs() {
        return this.fs;
    }

    public void setFs(String fs) {
        this.fs = fs;
    }

    /**
     *            @hibernate.property
     *             column="WHCD"
     *             length="2"
     *
     */
    public String getWhcd() {
        return this.whcd;
    }

    public void setWhcd(String whcd) {
        this.whcd = whcd;
    }

    /**
     *            @hibernate.property
     *             column="XGTZ"
     *             length="50"
     *
     */
    public String getXgtz() {
        return this.xgtz;
    }

    public void setXgtz(String xgtz) {
        this.xgtz = xgtz;
    }

    /**
     *            @hibernate.property
     *             column="YYAP"
     *             length="50"
     *
     */
    public String getYyap() {
        return this.yyap;
    }

    public void setYyap(String yyap) {
        this.yyap = yyap;
    }

    /**
     *            @hibernate.property
     *             column="YX"
     *             length="2"
     *
     */
    public String getYx() {
        return this.yx;
    }

    public void setYx(String yx) {
        this.yx = yx;
    }

    /**
     *            @hibernate.property
     *             column="HYZK"
     *             length="2"
     *
     */
    public String getHyzk() {
        return this.hyzk;
    }

    public void setHyzk(String hyzk) {
        this.hyzk = hyzk;
    }

    /**
     *            @hibernate.property
     *             column="JYYX"
     *             length="10"
     *
     */
    public Long getJyyx() {
        return this.jyyx;
    }

    public void setJyyx(Long jyyx) {
        this.jyyx = jyyx;
    }

    /**
     *            @hibernate.property
     *             column="ZF"
     *             length="2"
     *
     */
    public String getZf() {
        return this.zf;
    }

    public void setZf(String zf) {
        this.zf = zf;
    }

    /**
     *            @hibernate.property
     *             column="EMAIL"
     *             length="50"
     *
     */
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *            @hibernate.property
     *             column="OICQ"
     *             length="25"
     *
     */
    public String getOicq() {
        return this.oicq;
    }

    public void setOicq(String oicq) {
        this.oicq = oicq;
    }

    /**
     *            @hibernate.property
     *             column="SJTEL"
     *             length="21"
     *
     */
    public String getSjtel() {
        return this.sjtel;
    }

    public void setSjtel(String sjtel) {
        this.sjtel = sjtel;
    }

    /**
     *            @hibernate.property
     *             column="TEL"
     *             length="50"
     *
     */
    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     *            @hibernate.property
     *             column="FLAG"
     *             length="2"
     *
     */
    public Integer getFlag() {
        return this.flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     *            @hibernate.property
     *             column="TJSJ"
     *             length="7"
     *
     */
    public Date getTjsj() {
        return this.tjsj;
    }

    public void setTjsj(Date tjsj) {
        this.tjsj = tjsj;
    }

    /**
     *            @hibernate.property
     *             column="HOTS"
     *             length="10"
     *
     */
    public Long getHots() {
        return this.hots;
    }

    public void setHots(Long hots) {
        this.hots = hots;
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
     *             column="LASTTIME"
     *             length="7"
     *
     */
    public Date getLasttime() {
        return this.lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    /**
     *            @hibernate.property
     *             column="REGTIME2"
     *             length="7"
     *
     */
    public Date getRegtime2() {
        return this.regtime2;
    }

    public void setRegtime2(Date regtime2) {
        this.regtime2 = regtime2;
    }
    
    /**
     *            @hibernate.property
     *             column="REGTIME3"
     *             length="7"
     *
     */
    public Date getRegtime3() {
        return this.regtime3;
    }

    public void setRegtime3(Date regtime3) {
        this.regtime3 = regtime3;
    }

    /**
     *            @hibernate.property
     *             column="REGTIME4"
     *             length="7"
     *
     */
    public Date getRegtime4() {
        return this.regtime4;
    }

    public void setRegtime4(Date regtime4) {
        this.regtime4 = regtime4;
    }


    /**
     *            @hibernate.property
     *             column="IMG"
     *             length="2"
     *
     */
    public Integer getImg() {
        return this.img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    /**
     *            @hibernate.property
     *             column="SETZT"
     *             length="2"
     *
     */
    public Integer getSetzt() {
        return this.setzt;
    }

    public void setSetzt(Integer setzt) {
        this.setzt = setzt;
    }

    /**
     *            @hibernate.property
     *             column="SHR"
     *             length="20"
     *
     */
    public String getShr() {
        return this.shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    /**
     *            @hibernate.property
     *             column="SHQK"
     *             length="20"
     *
     */
    public String getShqk() {
        return this.shqk;
    }

    public void setShqk(String shqk) {
        this.shqk = shqk;
    }

    /**
     *            @hibernate.property
     *             column="HTTPURL"
     *             length="100"
     *
     */
    public String getHttpurl() {
        return this.httpurl;
    }

    public void setHttpurl(String httpurl) {
        this.httpurl = httpurl;
    }

    /**
     *            @hibernate.property
     *             column="HTTPIP"
     *             length="50"
     *
     */
    public String getHttpip() {
        return this.httpip;
    }

    public void setHttpip(String httpip) {
        this.httpip = httpip;
    }

    /**
     *            @hibernate.property
     *             column="ISDEL"
     *             length="2"
     *
     */
    public Integer getIsdel() {
        return this.isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    /**
     *            @hibernate.property
     *             column="VIP"
     *             length="2"
     *
     */
    public Integer getVip() {
        return this.vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    /**
     *            @hibernate.property
     *             column="S1"
     *             length="20"
     *
     */
    public String getS1() {
        return this.s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    /**
     *            @hibernate.property
     *             column="S2"
     *             length="20"
     *
     */
    public String getS2() {
        return this.s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    /**
     *            @hibernate.property
     *             column="ZZSJ"
     *             length="7"
     *
     */
    public Date getZzsj() {
        return this.zzsj;
    }

    public void setZzsj(Date zzsj) {
        this.zzsj = zzsj;
    }


    public void setZy(String zy) {
        this.zy = zy;
    }

    /**
     *            @hibernate.property
     *             column="ZY"
     *             length="2"
     *
     */
    public String getZy() {
        return this.zy;
    }
    
    public Integer getVacsjtel() {
		return vacsjtel;
	}

	public void setVacsjtel(Integer vacsjtel) {
		this.vacsjtel = vacsjtel;
	}

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public Integer getVacemail() {
		return vacemail;
	}

	public void setVacemail(Integer vacemail) {
		this.vacemail = vacemail;
	}

	public String getShield() {
        return this.shield;
    }

    public void setShield(String shield) {
        this.shield = shield;
    }
    
    /** 
     *            @hibernate.property
     *             column="AREA"
     *             length="20"
     *         
     */
    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
    /**
     *            @hibernate.property
     *             column="REGTIME5"
     *             length="7"
     *
     */
    public Date getRegtime5() {
        return this.regtime5;
    }

    public void setRegtime5(Date regtime5) {
        this.regtime5= regtime5;
    }

    public Integer getImportant() {
        return important;
    }

    public void setImportant(Integer important) {
        this.important = important;
    }

    public String toString() {
        return new ToStringBuilder(this)
 .append("hyid", getHyid())
            .toString();
    }

}
