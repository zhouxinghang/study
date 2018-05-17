package com.zhouxinghang.study.test;

import sun.applet.Main;

/**
 * Created by zhouxinghang on 2018/4/27.
 */
public class ClassTest {
    class A {}


     class B {}

    interface C {}


    public static void main(String[] args) {
        A a = (A) new Object();


    }

    private  void  run() {
        C c = (C) new Main() ;

    }
}
