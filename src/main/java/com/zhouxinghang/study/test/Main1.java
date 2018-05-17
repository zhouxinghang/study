package com.zhouxinghang.study.test;

import java.util.Scanner;

/**
 * Created by zhouxinghang on 2018/4/7.
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        char c = str.charAt(0);
        if(c <= 'Z' && c >= 'A') {
            System.out.println("大写");
        } else if (c <= 'z' && c >= 'a') {
            System.out.println("小写");
        } else if (c <= '9' && c >= '0') {
            System.out.println("数字");
        } else {
            System.out.println("其他字符");
        }

        // TODO Auto-generated method stub
        boolean isOk = false;

        assert isOk = true;//如果开启则会调用assert，那么isOk就为true

        System.out.println(isOk);//返回结果：false。一般结果都是返回false,表示assert未开启
    }


}
