package com.zhouxinghang.study.collections;

import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by zhouxinghang on 2018/5/17.
 * removeIf 是删除符合条件的所有元素
 */
public class RemoveIfTest {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList();
        list.add("aaa");
        list.add("bbb");

        list.removeIf(str -> {
            return StringUtils.equals(str, "aaaa");
        });

        System.out.println(list.size());
    }

}
