package com.lover;
import org.quartz.*;

public class TimeQuartz implements Job{
  public TimeQuartz() {
	  
  }

  public void execute(JobExecutionContext context) throws JobExecutionException
  {
    System.out.println("Æô¶¯");
  }
}
