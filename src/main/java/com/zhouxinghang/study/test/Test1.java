package com.zhouxinghang.study.test;

/**
 * Created by zhouxinghang on 2018/4/4.
 */
public class Test1 {
    private String str;

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        //test1.run1();
        test1.run2();
        test1.run3();
    }

    private void run1() {
        str = "hello";
    }

    private void run2() {
        System.out.println(str + "world");
    }

    private void run3() {
        System.out.println("hello".equals(str));
    }
}
