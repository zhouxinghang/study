package com.zhouxinghang.study.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by zhouxinghang on 2018/5/17.
 */
public class JoinerDemo {

    public static void main(String[] args) {
        Joiner joiner = Joiner.on(";").skipNulls();
        String str = joiner.join("Java", null, "C#");
        System.out.println(run1());
        System.out.println(run2());
        System.out.println(run3());
        System.out.println(str);
    }


    private static String run1() {
        return Joiner.on(";").skipNulls().join("Java", null, "C#");
    }

    private static String run2() {
        return Joiner.on(";").useForNull("未填写").join("湖北省", null,"河南省", "山东省");
    }

    private static String run3() {
        List<String> list = Lists.newArrayList();
        list.add("mafka");
        list.add("guava");
        list.add(null);
        return Joiner.on(";").skipNulls().join(list);
    }


}
