package com.zhouxinghang.study.proxy;

import com.zhouxinghang.study.BaseTest;
import com.zhouxinghang.study.proxy.staticporxy.IUserDao;
import com.zhouxinghang.study.proxy.staticporxy.UserDao;

import org.junit.Test;

/**
 * Created by zhouxinghang on 2018/5/23.
 */
public class ProxyFactoryTest extends BaseTest {

    @Test
    public void test() {
        IUserDao userDao = (IUserDao) new ProxyFactory(new UserDao()).getProxyInstance();
        userDao.selectById(1);


    }
}
