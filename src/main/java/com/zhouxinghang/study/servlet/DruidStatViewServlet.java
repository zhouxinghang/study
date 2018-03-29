package com.zhouxinghang.study.servlet;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * Created by zhouxinghang on 2018/3/29.
 */

@WebServlet(urlPatterns = "/druid/*",
    initParams = {
        //@WebInitParam(name="allow", value="127.0.0.1"),//ip白名单，为空或不配置允许所有
        //@WebInitParam(name="deny", value = "127.0.0.2"),//ip黑名单，优先级高于白名单
        @WebInitParam(name="loginUsername", value="admin"),
        @WebInitParam(name="loginPassword",value="123456"),
        @WebInitParam(name="resetEnable", value="false")//// 禁用HTML页面上的“Reset All”功能
    })
public class DruidStatViewServlet extends StatViewServlet{
    private static final long serialVersionUID = -123456789876453424L;
}
