package com.zhouxinghang.study.proxy;

import com.zhouxinghang.study.BaseTest;
import com.zhouxinghang.study.proxy.staticporxy.IUserDao;
import com.zhouxinghang.study.proxy.staticporxy.ProxyUserDao;
import com.zhouxinghang.study.proxy.staticporxy.UserDao;

import org.junit.Test;

/**
 * Created by zhouxinghang on 2018/5/23.
 */
public class StaticProxyTest extends BaseTest {

    @Test
    public void test() {
        IUserDao userDao = new ProxyUserDao(new UserDao());
        userDao.selectById(1);
    }
}
