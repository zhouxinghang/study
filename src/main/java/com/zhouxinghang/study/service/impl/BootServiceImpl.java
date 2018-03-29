package com.zhouxinghang.study.service.impl;

import com.zhouxinghang.study.service.BootService;
import org.springframework.stereotype.Service;

/**
 * Created by zhouxinghang on 2018/3/29.
 */

@Service
public class BootServiceImpl implements BootService {

    @Override
    public String hello(String name) {
        return "hello, " + name;
    }
}
