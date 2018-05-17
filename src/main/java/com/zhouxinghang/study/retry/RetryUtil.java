package com.zhouxinghang.study.retry;

import com.google.common.collect.Maps;

import com.zhouxinghang.study.exception.MyException;

import org.springframework.classify.BinaryExceptionClassifier;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.RetryState;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.DefaultRetryState;
import org.springframework.retry.support.RetryTemplate;

import java.util.Collections;
import java.util.Map;

/**
 * Created by zhouxinghang on 2018/5/7.
 */
public class RetryUtil {

    public <T> T requestWithRetry() throws Exception {
        RetryTemplate template = new RetryTemplate();

        //设置重试策略 重试3次
        Map<Class<? extends Throwable>, Boolean> map = Maps.newHashMap();
        map.put(MyException.class, true);//当抛出次异常时，就会重试
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(5, map);
        template.setRetryPolicy(retryPolicy);

        //设置回避策略    1s后重试
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(1000);
        template.setBackOffPolicy(backOffPolicy);

        //设置重试监听器
        RetryListener listener = new RetryListener() {
            private int count = 0;
            @Override
            public <T, E extends Throwable> boolean open(RetryContext retryContext, RetryCallback<T, E> retryCallback) {
                System.out.println("第一次重试开始");
                return true;
            }

            @Override
            public <T, E extends Throwable> void close(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
                System.out.println("所有重试介绍");
            }

            @Override
            public <T, E extends Throwable> void onError(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
                System.out.println("产生异常：" + count++);
            }
        };
        RetryListener[] listeners = new RetryListener[]{listener};
        template.setListeners(listeners);

        //定义重试回调代码
        RetryCallback<T, Exception> retryCallback = new RetryCallback<T, Exception>() {
            @Override
            public T doWithRetry(RetryContext retryContext) throws MyException {
                System.out.println("重试代码开始执行");
                throw new MyException("运行出错了！");//定义的map是这个异常，只有抛出这个异常，才会重试

            }
        };
        //定义重试结束后的回调代码
        RecoveryCallback<T> recoveryCallback = new RecoveryCallback<T>() {
            @Override
            public T recover(RetryContext retryContext) throws Exception {
                System.out.println("所有重试都结束");
                return null;
            }
        };
        template.execute(retryCallback, recoveryCallback);
        return null;
    }

    public static void main(String[] args) {
        RetryUtil util = new RetryUtil();
        try {
            //util.requestWithRetry();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(util.test());
    }

    public int test() {
        try {
            RetryTemplate template = new RetryTemplate();
            return template.execute(retryContext -> {
                try {
                    return 1;
                } catch (Exception e) {
                    return 2;
                }
            });
        } catch (Exception e) {
            return 3;
        }

        //return 4;
    }
}
