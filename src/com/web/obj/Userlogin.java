package com.web.obj;

import java.util.Date;

public class Userlogin
{
    private Long id;
    
    private Long loginHyId;
    
    private String loginLcname;
    
    private Date loginTime;
    
    private String sex;
    
    private String s1;
    
    private String s2;
    
    public Userlogin(){
        
    }
    
    public Userlogin(Long id, Long loginHyId, String loginLcname,Date loginTime, String sex, String s1,String s2){
        this.id = id;
        this.loginHyId = loginHyId;
        this.loginLcname = loginLcname;
        this.loginTime = loginTime;
        this.sex = sex;
        this.s1 = s1;
        this.s2 = s2;
    }
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getLoginHyId()
    {
        return loginHyId;
    }

    public void setLoginHyId(Long loginHyId)
    {
        this.loginHyId = loginHyId;
    }

    public String getLoginLcname()
    {
        return loginLcname;
    }

    public void setLoginLcname(String loginLcname)
    {
        this.loginLcname = loginLcname;
    }

    public Date getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Date loginTime)
    {
        this.loginTime = loginTime;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getS1()
    {
        return s1;
    }

    public void setS1(String s1)
    {
        this.s1 = s1;
    }

    public String getS2()
    {
        return s2;
    }

    public void setS2(String s2)
    {
        this.s2 = s2;
    }
    
    
}
