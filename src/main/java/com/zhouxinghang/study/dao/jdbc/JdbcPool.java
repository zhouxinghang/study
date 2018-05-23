package com.zhouxinghang.study.dao.jdbc;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by zhouxinghang on 2018/5/23.
 */

@Component
public class JdbcPool implements DataSource {

    private static final Logger LOG = LoggerFactory.getLogger(JdbcPool.class);
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String url;

    private static ConcurrentLinkedDeque<Connection> connPool = new ConcurrentLinkedDeque<>();

    @PostConstruct
    private void init() throws SQLException, ClassNotFoundException {
        // 指定连接类型
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection;
        for(int i = 0; i < 20; i++) {
            connection = DriverManager.getConnection(url, username, password);
            LOG.info("初始化数据库连接池，获取connection:{},连接池数量:{}", connection.hashCode(), connPool.size());
            connPool.add(connection);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if(connPool.size() > 0) {
            Connection connection = connPool.removeFirst();
            LOG.info("从连接池获取连接成功，连接池数量为:{}", connPool.size());

            // 返回connection的代理对象，如果是执行close方法，这执行重写，实现将connection返回到连接池中去
//            return connection;
            return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), connection.getClass().getInterfaces(), new InvocationHandler() {
                /** 重写invoke，实现连接回收到连接池 **/
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if(!StringUtils.equals("close", method.getName())) {
                        return method.invoke(connection, args);
                    } else {
                        connPool.add(connection);
                        LOG.info("连接被连接池回收，连接池数量为:{}", connPool.size());
                        return null;
                    }
                }
            });
        } else {
            throw new RuntimeException("数据库繁忙，请稍后再试~");
        }

    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
