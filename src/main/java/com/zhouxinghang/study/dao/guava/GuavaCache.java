package com.zhouxinghang.study.dao.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

/**
 * Created by zhouxinghang on 2018/5/22.
 * guava缓存类，内部实现是CacheLoading，实现了过期策略和remove监听器
 */

@Repository
public class GuavaCache {
    private static final Logger LOG = LoggerFactory.getLogger(GuavaCache.class);
    private static LoadingCache<String, Object> cache;

    @PostConstruct
    private void init() {
        cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterAccess(5, TimeUnit.MINUTES)
            .removalListener((RemovalListener<String, Object>) notification -> {
                LOG.info("GuavaCache key:{}, value:{} 过期原因:{}", notification.getKey(), notification.getValue(), notification.getCause());
            })
            .build(new CacheLoader<String, Object>() {
                @Override
                public Object load(String s) throws Exception {
                    LOG.info("GuavaCache缓存未命中，从DB中重新获取处理");
                    return "load at " + System.currentTimeMillis();
                }
            });
    }

    public Object get(String key) {
        Object obj = null;
        try {
            obj = cache.get(key);
        } catch (ExecutionException e) {
            LOG.error("GuaveDao get ERROR.key:{}", key,e);
        }
        return obj;
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public void putAll(Map<String, Object> data) {
        cache.putAll(data);
    }
}
