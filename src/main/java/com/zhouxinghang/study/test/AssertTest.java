package com.zhouxinghang.study.test;

/**
 * Created by zhouxinghang on 2018/5/16.
 * 首先要开启断言assert    vm参数为： -ea 打开所有用户的assertion
 */
public class AssertTest {

    public static void main(String[] args) {
        boolean isRight = 1 > 2;
        try {
            assert isRight:"程序错误";//在:后写自定义的异常
            System.out.println("程序正常");
        } catch (AssertionError e) {
            // TODO: handle exception
            System.out.println(e.getMessage());//返回值：程序错误。(没错，这就是我们自定义的异常)
        }
    }
}
