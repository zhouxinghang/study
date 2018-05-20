package com.zhouxinghang.study.guava;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by zhouxinghang on 2018/5/18.
 * 不可变集合，不支持null。可以当做是常量，安全，高效.一旦创建，不可改变，
 * http://ifeve.com/google-guava-immutablecollections/
 */
public class ImmutableDemo {

    public static void main(String[] args) {
        ImmutableSet<String> set = builder();
        ImmutableList<String> list = set.asList();
        System.out.println(list.get(1));

        Set<Integer> set1 = Sets.newHashSet();
        set1.add(1);
        set1.add(2);
        Iterator<Integer> iterator = set1.iterator();
        System.out.println(iterator.next());
        Set<Integer> set2 = set1.stream().map(i -> i*2).collect(Collectors.toSet());
        System.out.println(set2.toArray()[0]);

        copyOf(set);
    }

    public static ImmutableSet<String> builder() {
        Set<String> set = Sets.newHashSet();
        set.add("java");
        set.add("guava");
        System.out.println(set.toArray()[1]);
        ImmutableSet<String> immutableSet = ImmutableSet.copyOf(set);
        Set<String> strSet = immutableSet.stream().map(i -> i + "study").collect(Collectors.toSet());
        for(String str : strSet) {
            System.out.println(str);
        }
        return immutableSet;
    }

    public static void copyOf(ImmutableSet<String> set) {
        ImmutableSet<String> set1 = ImmutableSet.copyOf(set);
        System.out.println(Objects.equal(set, set1));

        try {

        } catch(RuntimeException | Error e){
            throw e;
        }
    }
}
