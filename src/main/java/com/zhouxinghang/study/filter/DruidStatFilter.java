package com.zhouxinghang.study.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by zhouxinghang on 2018/3/29.
 * WebStatFilter实现类
 */

@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = {
        //忽略静态资源
        @WebInitParam(name="exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")
})
public class DruidStatFilter extends WebStatFilter {

}
