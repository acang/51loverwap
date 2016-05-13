package com.lover;
import com.essiembre.library.scheduler.SchedulerTask;

public class TimeQuery extends SchedulerTask{
  public TimeQuery() {
  }
  public void run(){
                 System.out.println("定时启动");
              //   CacheTools.setAll();
                 CacheToolsNew.setAll();
                 System.out.println("定时结束");
          }

}
