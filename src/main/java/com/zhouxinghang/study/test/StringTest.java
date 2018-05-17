package com.zhouxinghang.study.test;

/**
 * Created by zhouxinghang on 2018/5/16.
 */
public class StringTest {

    public static void main(String[] args) {
        String s1 = new String("a") + new String("b");
        s1.intern();
        String s2 = "ab";
        System.out.println(s1 == s2);
    }
}
