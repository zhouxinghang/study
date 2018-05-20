package com.zhouxinghang.study.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;

/**
 * Created by zhouxinghang on 2018/5/18.
 */
public class CharMatcherDemo {

    public static void main(String[] args) {
        byte[] bytes = "周星航".getBytes(Charsets.UTF_8);
//        for (byte b : bytes) {
//            System.out.println(b);
//        }
        System.out.println(run1('b'));
        System.out.println(run2('x'));
    }

    public static boolean run1(char c) {
        return CharMatcher.inRange('a', 'z').matches(c);
    }

    public static boolean run2(char c) {
        return CharMatcher.is('z').matches(c);
    }
}
