package com.web.obj;

import java.util.Date;

public class Viewme
{
    private Long id;
    
    private Long myId;
    
    private String myLcname;
    
    private Long viewId;
    
    private String viewLcname;
    
    private Date viewTime;
    
    public Viewme()
    {
        
    }
    
    public Viewme(Long id, Long myId, String myLcname,Long viewId, String viewLcname,Date viewTime){
        this.id = id;
        this.myId = myId;
        this.myLcname = myLcname;
        this.viewId = viewId;
        this.viewLcname = viewLcname;
        this.viewTime = viewTime;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getMyId()
    {
        return myId;
    }

    public void setMyId(Long myId)
    {
        this.myId = myId;
    }

    public String getMyLcname()
    {
        return myLcname;
    }

    public void setMyLcname(String myLcname)
    {
        this.myLcname = myLcname;
    }

    public Long getViewId()
    {
        return viewId;
    }

    public void setViewId(Long viewId)
    {
        this.viewId = viewId;
    }

    public String getViewLcname()
    {
        return viewLcname;
    }

    public void setViewLcname(String viewLcname)
    {
        this.viewLcname = viewLcname;
    }

    public Date getViewTime()
    {
        return viewTime;
    }

    public void setViewTime(Date viewTime)
    {
        this.viewTime = viewTime;
    }
    
    
    
    
}
