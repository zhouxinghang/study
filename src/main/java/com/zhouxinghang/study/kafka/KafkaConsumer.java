package com.zhouxinghang.study.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by zhouxinghang on 2018/5/22.
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"testTopic"})
    public void receiveMessage(String message) {
        System.out.println("收到了来自testTopic的消息: " + message);
    }

}
