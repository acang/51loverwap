package com.lover;

public class ThreadCityUserList extends Thread
{
    public void run(){
        CacheToolsNew.getCityUserList();
    }
}
