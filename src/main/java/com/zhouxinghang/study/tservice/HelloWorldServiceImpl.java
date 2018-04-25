package com.zhouxinghang.study.tservice;


import com.zhouxinghang.study.thrift.tmodel.User;
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

    @Override
    public String getUser(User user) throws TException {
        return user.getName();
    }
}
