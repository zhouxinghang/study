package com.zhouxinghang.study.test;

import java.util.Scanner;

/**
 * Created by zhouxinghang on 2018/4/7.
 */
public class Main2 {
    //每月的天数
    private static final int[]  TABLE = {31,28,31,30,31,30,31,31,30,31,30,31};


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String dateStr = scan.next();//日子格式为：4月7号
        char[] date = dateStr.toCharArray();// date[0]-'0'为几月。 date[2]-'0'为几号
        int days = 0;
        for (int i = 0; i < date[0] - 1 - '0'; i ++) {
            days += TABLE[i];
        }
        days += date[2] - '0';
        System.out.println(days);


    }

}
