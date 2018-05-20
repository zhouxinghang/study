package com.zhouxinghang.study.guava;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by zhouxinghang on 2018/5/18.
 */
public class OptionalDemo {

    public static void main(String[] args) {
        run2();
    }

    /**
     * Optional.of(T)   只能是传入非空值，否则直接报错
     */
    public static void run1() {
        Integer integer = null;
        Optional<Integer> optional = Optional.of(integer);
        System.out.println(optional.isPresent());
    }

    /**
     * Optional.ofNullable(T)   可以传入空值，通过isPresent()来判断是否为空
     * Optional.orElse(T)   若为空，就返回默认值
     */
    public static void run2() {
        Integer integer = null;
        Optional<Integer> optional = Optional.ofNullable(integer);
        System.out.println(optional.isPresent());
        if(optional.isPresent()) {
            System.out.println(optional.get());
        } else {
            System.out.println(optional.orElse(-1));
        }
    }

    public static void run3() {
        List<String> strList = Lists.newArrayList();
        strList.add("zxh");
        strList.add(null);
        strList.add("ssy");
        Optional<List> strListOptional = Optional.ofNullable(strList);
        if(strListOptional.isPresent()) {
            List<String> strListTemp = strListOptional.get();
//            Set<String> strSet = strListOptional
        }
    }

}
