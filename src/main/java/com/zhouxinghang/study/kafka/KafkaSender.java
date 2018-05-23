package com.zhouxinghang.study.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by zhouxinghang on 2018/5/22.
 */

@Component
public class KafkaSender {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 发送消息到kafka
     * @param channel
     * @param message
     */
    public void sendChannelMess(String channel, String message) {
        kafkaTemplate.send(channel, message);
    }


}
