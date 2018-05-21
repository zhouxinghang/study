package com.zhouxinghang.study.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhouxinghang on 2018/5/21.
 * https://blog.csdn.net/jiesa/article/details/50412027
 */
public class RateLimiterDemo {

    public static void main(String[] args) {
        execute();
        executeWithRateLimiter();
    }

    /**
     * 不用限流
     */
    public static void execute() {
        Long start = System.currentTimeMillis();
        for(int i = 0; i < 20; i++) {
            System.out.println("call execute.." + i);
        }
        Long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
    /**
     *  通过令牌桶算法限流
     */
    public static void executeWithRateLimiter() {
        Long  start = System.currentTimeMillis();
        // 根据指定的吞吐量来创建RateLimiter  参数为每秒可执行数量，也指每秒放入多少个令牌
        RateLimiter limiter = RateLimiter.create(5);
        // 根据指定参数和预热器创建RateLimiter， 第二个参数是预热期，在预热期5min内，RateLimiter吞吐量会平稳增加到最大值5000 个/s
        RateLimiter limiter2 = RateLimiter.create(5000, 5, TimeUnit.MINUTES);
        // 设置固定频率， 每秒固定吞吐量
        limiter.setRate(5);
        for(int i = 0; i < 20; i++) {
            //获取令牌，阻塞知道获取令牌
            double token = limiter.acquire();
            System.out.println(token + "-call execute.." + i);
        }
        Long end = System.currentTimeMillis();
        System.out.println(end-start);

        // tryAcquire 尝试获取令牌，返回false | true
        boolean execute = limiter.tryAcquire();
        if(execute) {
            System.out.println("获取到了token，可以被执行");
        } else {
            System.out.println("没有获取到token，不能被执行，继续等待");
        }
    }


}
