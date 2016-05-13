package com.lover;

import java.util.*;

public class BlackIPMng {

  private static Hashtable bht = new Hashtable();

  public BlackIPMng() {
  }

  public boolean isBlackIp(String ip)
  {
     if(bht.containsKey(ip))
       return true;
     return false;
  }

  public void addBlackIp(String ip)
  {
    bht.put(ip,ip);
  }

  public void removeBlackIp(String ip)
  {
    bht.remove(ip);
  }

  public void clearIp()
  {
    bht.clear();
  }
}
