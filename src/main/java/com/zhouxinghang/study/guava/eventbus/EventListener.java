package com.zhouxinghang.study.guava.eventbus;

import com.google.common.eventbus.Subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zhouxinghang on 2018/5/24.
 */
@Component
public class EventListener {
    private static final Logger LOG = LoggerFactory.getLogger(EventListener.class);

    private EventEntity entity;

    private String msg;

    @Subscribe
    public void listenEntity(EventEntity entity) {
        LOG.info("监听到一条Entity新消息:{}", entity.toString());
        this.entity = entity;
    }

    @Subscribe
    public void listenStr(String msg) {
        LOG.info("监控到一条String新消息:{}", msg);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.msg = msg;
    }


}
