package com.zhouxinghang.study.configuration;

import com.alibaba.druid.support.spring.stat.BeanTypeAutoProxyCreator;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.zhouxinghang.study.service.BootService;
import org.hibernate.validator.internal.xml.binding.BeanType;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhouxinghang on 2018/3/29.
 * Druid关于Spring的监控
 */


@Configuration
public class DruidConfig {


    /**
     * Spring和Jdbc的关联监控。
     * DruidStatInterceptor:标准的Spring MethodInterceptor。可以灵活进行AOP配置
     * Advice
     * @return
     */
    @Bean
    public DruidStatInterceptor interceptorNames(){
        DruidStatInterceptor inc = new DruidStatInterceptor();
        return inc;
    }

    /**
     * 按类型拦截监控
     * @return
     */
    @Bean
    public BeanTypeAutoProxyCreator beanTypeAutoProxyCreator() {
        BeanTypeAutoProxyCreator cut = new BeanTypeAutoProxyCreator();
        cut.setTargetBeanType(BootService.class);
        cut.setInterceptorNames("interceptorNames");
        return cut;
    }

    /**
     * 按方法名正则匹配拦截
     */

    /**
     * pointcut
     * @return
     */
    @Bean
    public JdkRegexpMethodPointcut jdkRegexpMethodPointcut(){
        JdkRegexpMethodPointcut cut = new JdkRegexpMethodPointcut();
        cut.setPatterns("com.zhouxinghang.study.*");
        return cut;
    }

    /**
     * Advisor
     * @param pointcut
     * @param interceptor
     * @return
     */
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(JdkRegexpMethodPointcut pointcut, DruidStatInterceptor interceptor){
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }

    /**
     * AOP proxy based on beans in Spring
     * @return
     */
    @Bean
    public ProxyFactoryBean proxyFactoryBean(){
        ProxyFactoryBean proxy = new ProxyFactoryBean();
        proxy.setInterceptorNames("defaultPointcutAdvisor");
        return proxy;
    }

}
