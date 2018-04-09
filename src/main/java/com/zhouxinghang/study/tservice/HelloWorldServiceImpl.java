package com.zhouxinghang.study.tservice;

import com.zhouxinghang.study.thrift.tservice.HelloWorldService;

import org.apache.thrift.TException;

/**
 * Created by zhouxinghang on 2018/4/9.
 */
public class HelloWorldServiceImpl implements HelloWorldService.Iface{

    @Override
    public String sayHello(String username) throws TException {
        return "Hi, " + username;
    }
}
