package com.zhouxinghang.study.cron;

import com.apple.eawt.AppEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zhouxinghang on 2018/3/29.
 * 测试的时候只能开一个task来测试，因为是单线程的，如果加上了@Async就可以一起测试
 */

@Component
public class ScheduledDemo {

    /**
     * 时间间隔是两次任务开始的时间间隔（如果上次任务执行的时间超过了时间间隔，那本次任务也会等到上次任务执行结束再开始执行，
     * 除非这个Job用@Async注解了）
     */
    @Async
    //@Scheduled(fixedRate = 5000)
    public void task() {
        System.out.println("task1 is begin at " + new Date().toString());
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task1 is end at " + new Date().toString());
    }


    /**
     * 单位是毫秒，时间间隔是上次任务的结束与本次任务开始的时间间隔
     */
    @Async
    @Scheduled(fixedDelay = 50000)
    public void task2() {
        System.out.println("task2 is begin at " + new Date().toString());
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task2 is end at " + new Date().toString());
    }

    @Async
    @Scheduled(fixedDelay = 50000)
    public void task3() {
        System.out.println("task3 is begin at " + new Date().toString());
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task3 is end at " + new Date().toString());
    }
}
