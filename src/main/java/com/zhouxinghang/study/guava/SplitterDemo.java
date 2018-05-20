package com.zhouxinghang.study.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhouxinghang on 2018/5/17.
 */
public class SplitterDemo {

    public static void main(String[] args) {
        Iterable<String> result = run4();
        for(String str : result) {
            System.out.println(str);
        }
    }

    /**
     * 忽略分隔符末尾的null
     * @return
     */
    public static Iterable<String> run1() {
        return Splitter.on(',').split("a,b,,c,");
    }

    /**
     * 按照字符匹配器拆分
     * @return
     */
    public static Iterable<String> run2() {
        return Splitter.on(CharMatcher.JAVA_LETTER).split("java");
    }

    /**
     * 按照定长划分，最后一段比定长短，不能忽略
     * @return
     */
    public static Iterable<String> run3() {
        return Splitter.fixedLength(3).split("aaabbbcccdd");
    }

    /**
     * 按照正则表达式划分，符合正则表达式的部分会变为null？？
     * @return
     */
    public static Iterable<String> run4() {
        return Splitter.onPattern("\\d").split("aaa22bbb33ddd");
    }



}
