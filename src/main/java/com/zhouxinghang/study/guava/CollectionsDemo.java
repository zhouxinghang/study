package com.zhouxinghang.study.guava;

import com.google.common.collect.Collections2;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouxinghang on 2018/5/21.
 * http://ifeve.com/google-guava-collectionutilities/
 */
public class CollectionsDemo {

    public static void main(String[] args) {
        iterables();
    }


    /**
     * 静态工厂方法，避免重复定义泛型。虽然jdk7也可以这样，但是guava还可以在定义的时候就定义初始值
     *
     集合接口	属于JDK还是Guava	对应的Guava工具类
     Collection	JDK	Collections2：不要和java.util.Collections混淆
     List	JDK	Lists
     Set	JDK	Sets
     SortedSet	JDK	Sets
     Map	JDK	Maps
     SortedMap	JDK	Maps
     Queue	JDK	Queues
     Multiset	Guava	Multisets
     Multimap	Guava	Multimaps
     BiMap	Guava	Maps
     Table	Guava	Tables
     */
    public static void staticFactory() {
        List<String> list = Lists.newArrayList("java", "c", "c#", "php");
        // gauva引入的新集合类，没有暴露原始构造器，也没有在集合类中提供，而是在集合类中提供构造方法create()
        Multiset<String> multiset = HashMultiset.create();
    }

    /**
     * Iterables 工具类,提供iterable操作和Collection操作
     * Iterable接口提供forEach，iterator方法
     * Collection接口继承Tterable接口
     */
    public static void iterables() {

        List<String> list = Lists.newArrayList("aaa");
        boolean result = Iterables.addAll(list, Lists.newArrayList("ccc", "bbb"));
        System.out.println(result + "-" + list.toString());
        System.out.println(Iterables.contains(list, "ccc"));
        System.out.println(Iterables.size(list));
        list.iterator();
    }



}
