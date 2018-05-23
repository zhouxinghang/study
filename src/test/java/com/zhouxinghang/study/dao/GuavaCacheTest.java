package com.zhouxinghang.study.dao;

import com.zhouxinghang.study.StudyApplicationTests;
import com.zhouxinghang.study.dao.guava.GuavaCache;

import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by zhouxinghang on 2018/5/22.
 */
public class GuavaCacheTest extends StudyApplicationTests {
    @Resource
    private GuavaCache guavaCache;

    @Test
    public void test() {
        guavaCache.put("name", "周星航");
        guavaCache.put("name", "java");
        System.out.println(guavaCache.get("name"));
        System.out.println(guavaCache.get("age"));
        guavaCache.refresh("name");
        guavaCache.refresh("age");
    }
}
