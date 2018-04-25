package com.zhouxinghang.study.test;

/**
 * Created by zhouxinghang on 2018/4/17.
 */
public class FinalDemo {
    public static final String a = "hello";
    public static final String b = "world";

    public static void main(String[] args) {
        String c = "helloworld";
        String d = a  + b;

        System.out.println(c == d);
    }

}
