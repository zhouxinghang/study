package com.zhouxinghang.study.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouxinghang on 2018/5/16.
 */
public class HeapSpaceOomMock {

    public static void main(String[] args) {
        try {
            List<String> list = new ArrayList<String>();
            for(int i = 0; ; i++) {
                System.out.println(i);
                list.add(String.valueOf("String" + i).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
