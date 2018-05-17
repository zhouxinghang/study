package com.zhouxinghang.study.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

/**
 * Created by zhouxinghang on 2018/5/17.
 */
public class SplitterDemo {

    public static void main(String[] args) {
        Iterable<String> result = run2();
        for(String str : result) {
            System.out.println(str);
        }
    }

    /**
     * 忽略分隔符末尾的null
     * @return
     */
    private static Iterable<String> run1() {
        return Splitter.on(',').split("a,b,,c,");
    }

    private static Iterable<String> run2() {
        return Splitter.on(CharMatcher.JAVA_LETTER).split("java");
    }

}
