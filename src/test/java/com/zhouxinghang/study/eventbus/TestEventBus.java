package com.zhouxinghang.study.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import com.zhouxinghang.study.BaseTest;
import com.zhouxinghang.study.guava.eventbus.EventEntity;
import com.zhouxinghang.study.guava.eventbus.EventListener;

import org.junit.Test;

import java.util.concurrent.Executors;

import javax.annotation.Resource;

/**
 * Created by zhouxinghang on 2018/5/24.
 */
public class TestEventBus extends BaseTest {

    @Resource
    private EventListener eventListener;

    @Test
    public void testSync() {
        //简单同步消息发送
        EventBus eventBus = new EventBus("testSync");
        eventBus.register(eventListener);
        eventBus.post(new EventEntity( 001L, "Entity消息"));

        long start = System.currentTimeMillis();
        for(int i = 0; i < 100; i++) {
            eventBus.post("String消息-" + i);
        }
        long end = System.currentTimeMillis();
        System.out.println("同步方式发送100条消息，花费时间:" +  (end - start) + "ms"); // 505ms




    }

    @Test
    public void testAsync() {
        //异步消息发送
        //定义包含10个线程的线程池的异步消息发送器
        AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(100));
        asyncEventBus.register(eventListener);
        long start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++) {
            asyncEventBus.post("String消息-" + i);
        }
        long end = System.currentTimeMillis();
        System.out.println("异步方式发送100条消息，花费时间:" +  (end - start) + "ms"); // 75ms

        // sleep一会，让异步消息发送完
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
