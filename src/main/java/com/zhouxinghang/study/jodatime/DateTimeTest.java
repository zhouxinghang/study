package com.zhouxinghang.study.jodatime;

import org.joda.time.DateTime;

/**
 * Created by zhouxinghang on 2018/5/17.
 */
public class DateTimeTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(new DateTime().minusDays(4) + "-" + new DateTime().minusDays(4).getMillis());
        Thread.sleep(5);
        System.out.println(new DateTime().minusDays(4) + "-" + new DateTime().minusDays(4).getMillis());
        DateTime dateTime = new DateTime();
        DateTime startTime =  dateTime.minusMinutes(3);
        System.out.println(startTime);
        Thread.sleep(5000);
        System.out.println(startTime.plusMinutes(5));
    }

}
