package com.zhouxinghang.study.proxy.staticporxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;

/**
 * Created by zhouxinghang on 2018/5/23.
 */
public class ProxyUserDao implements IUserDao {

    private IUserDao target;

    private static final Logger LOG = LoggerFactory.getLogger(ProxyUserDao.class);

    public ProxyUserDao(IUserDao target) {
        this.target = target;
    }

    @Override
    public void selectById(Integer id) {
        LOG.info("执行代理类的selectById");
        target.selectById(id);
    }
}
