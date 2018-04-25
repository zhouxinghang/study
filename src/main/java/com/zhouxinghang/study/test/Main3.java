package com.zhouxinghang.study.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zhouxinghang on 2018/4/11.
 */
@Component
public class Main3 {
    @Value("${test.name}")
    private String name;

    public  String getName() {
        return name;
    }

}
