package com.zhouxinghang.study.kafka;

import com.zhouxinghang.study.StudyApplicationTests;

import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by zhouxinghang on 2018/5/22.
 */
public class KafkaSenderTest extends StudyApplicationTests {

    @Resource
    private KafkaSender kafkaSender;
    @Test
    public void send() {
        kafkaSender.sendChannelMess("testTopic", "这个一个测试消息");
    }
}
