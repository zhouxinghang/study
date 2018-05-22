package com.zhouxinghang.study.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhouxinghang on 2018/5/21.
 * http://ifeve.com/guava-ratelimiter/
 *
 *
 修饰符和类型	方法和描述
 double	acquire()
 从RateLimiter获取一个许可，该方法会被阻塞直到获取到请求
 double	acquire(int permits)
 从RateLimiter获取指定许可数，该方法会被阻塞直到获取到请求
 static RateLimiter	create(double permitsPerSecond)
 根据指定的稳定吞吐率创建RateLimiter，这里的吞吐率是指每秒多少许可数（通常是指QPS，每秒多少查询）
 static RateLimiter	create(double permitsPerSecond, long warmupPeriod, TimeUnit unit)
 根据指定的稳定吞吐率和预热期来创建RateLimiter，这里的吞吐率是指每秒多少许可数（通常是指QPS，每秒多少个请求量），在这段预热时间内，RateLimiter每秒分配的许可数会平稳地增长直到预热期结束时达到其最大速率。（只要存在足够请求数来使其饱和）
 double	getRate()
 返回RateLimiter 配置中的稳定速率，该速率单位是每秒多少许可数
 void	setRate(double permitsPerSecond)
 更新RateLimite的稳定速率，参数permitsPerSecond 由构造RateLimiter的工厂方法提供。
 String	toString()
 返回对象的字符表现形式
 boolean	tryAcquire()
 从RateLimiter 获取许可，如果该许可可以在无延迟下的情况下立即获取得到的话
 boolean	tryAcquire(int permits)
 从RateLimiter 获取许可数，如果该许可数可以在无延迟下的情况下立即获取得到的话
 boolean	tryAcquire(int permits, long timeout, TimeUnit unit)
 从RateLimiter 获取指定许可数如果该许可数可以在不超过timeout的时间内获取得到的话，或者如果无法在timeout 过期之前获取得到许可数的话，那么立即返回false （无需等待）
 boolean	tryAcquire(long timeout, TimeUnit unit)
 从RateLimiter 获取许可如果该许可可以在不超过timeout的时间内获取得到的话，或者如果无法在timeout 过期之前获取得到许可的话，那么立即返回false（无需等待）
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
