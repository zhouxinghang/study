package com.zhouxinghang.study.configuration;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by zhouxinghang on 2018/5/23.
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    public Executor backgroundRefreshExecutor() {
        ListeningExecutorService backgroundRefreshPools =
            MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(20));
        return backgroundRefreshPools;
    }
}
