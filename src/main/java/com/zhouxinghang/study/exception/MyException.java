package com.zhouxinghang.study.exception;

/**
 * Created by zhouxinghang on 2018/5/7.
 */
public class MyException extends  Exception {

    public MyException(String msg) {
        super("出错了！"+msg);
    }
}
