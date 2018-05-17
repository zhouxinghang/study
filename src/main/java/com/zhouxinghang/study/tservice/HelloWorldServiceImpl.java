package com.zhouxinghang.study.tservice;



import com.zhouxinghang.thrift.model.User;
import com.zhouxinghang.thrift.service.HelloWorldService;

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
