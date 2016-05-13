package com.lover;

import com.lover.mng.GRZQMng;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-6-21
 * Time: 下午2:48
 * To change this template use File | Settings | File Templates.
 */
public class TimeQueryDelQiuBo {
    public TimeQueryDelQiuBo() {
    }
    public void run(){
        System.out.println("定时清理3个月前的秋波");
        GRZQMng.delThreeMonthAgoDate();
        System.out.println("定时清理3个月前的秋波结束");
    }
}
