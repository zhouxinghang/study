package com.zhouxinghang.study.proxy.staticporxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhouxinghang on 2018/5/23.
 */
public class UserDao implements IUserDao {
    private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);
    @Override
    public void selectById(Integer id) {
        LOG.info("执行目标类的selectById");
    }

    public void test(String a, String b, String c, String d, String e, String f) {

    }
}
