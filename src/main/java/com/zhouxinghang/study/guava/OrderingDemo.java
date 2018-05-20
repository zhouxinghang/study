package com.zhouxinghang.study.guava;


import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by zhouxinghang on 2018/5/18.
 * http://ifeve.com/google-guava-ordering/
 */
public class OrderingDemo {

    public static void main(String[] args) {
        Ordering<Integer> ordering = product1();
        Ordering<String> ordering2 = product2();
        Ordering<Object> order = Ordering.usingToString();

        Object maxObj = order.max(new ArrayList<>(), new HashMap<>(), new Hashtable<>());
        System.out.println(maxObj.getClass());

        String max = ordering2.max("a", "bb", "aaa", "dd");
        System.out.println(max);

        // 自然排序
        Ordering<String> order2 = Ordering.natural();
        System.out.println(order2.max("aaa,", "d"));

        // 链式调用 衍生其他构造器
        /**
         *
         方法	描述
         reverse()	获取语义相反的排序器
         nullsFirst()	使用当前排序器，但额外把null值排到最前面。
         nullsLast()	使用当前排序器，但额外把null值排到最后面。
         compound(Comparator)	合成另一个比较器，以处理当前排序器中的相等情况。
         lexicographical()	基于处理类型T的排序器，返回该类型的可迭代对象Iterable<T>的排序器。
         onResultOf(Function)	对集合中元素调用Function，再按返回值用当前排序器排序。
         */
        // 反转
        Ordering<String> order3 = Ordering.natural().reverse();
        System.out.println(order3.max("aaa", "d"));
        // 使用当前构造器，但吧null放在前面
        Ordering<String> order4 = Ordering.natural().nullsFirst();
        System.out.println(order4.min("aaa", null, "d", "ccc", null, "ef"));
        //反转反转
        Ordering<String> order5 = Ordering.natural().reverse().reverse();
        System.out.println(order5.max("aaa", "d"));

    }

    /**
     * 创建Ordering，跳过实现Conparator
     * @return
     */
    public static Ordering<Integer> product1() {
        Ordering<Integer> order = new Ordering<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return Ints.compare(integer, t1);
            }
        };
        return order;
    }

    /**
     * 通过静态方法创建
     * natural()	对可排序类型做自然排序，如数字按大小，日期按先后排序
     * usingToString()	按对象的字符串形式做字典排序[lexicographical ordering]
     * from(Comparator)	把给定的Comparator转化为排序器
     * @return
     */
    public static Ordering product2() {
        Ordering<String> ordering = Ordering.natural();
        Ordering<Object> ordering1 = Ordering.usingToString();
        Ordering<String> ordering2 = Ordering.from((o1,o2) -> o1.length() < o2.length() ? -1 : (o1.length() > o2.length() ? 1 : 0));
        return ordering;
    }
}
