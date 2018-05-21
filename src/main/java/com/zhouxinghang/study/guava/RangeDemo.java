package com.zhouxinghang.study.guava;

import com.google.common.collect.Range;
import com.google.common.primitives.Ints;

/**
 * Created by zhouxinghang on 2018/5/21.
 * http://ifeve.com/google-guava-ranges/
 */
public class RangeDemo {

    public static void main(String[] args) {
        System.out.println("======product=====");
        product();
    }

    /**
     * 通过Range类静态方法获取
     (a..b)	open(C, C)
     [a..b]	closed(C, C)
     [a..b)	closedOpen(C, C)
     (a..b]	openClosed(C, C)
     (a..+∞)	greaterThan(C)
     [a..+∞)	atLeast(C)
     (-∞..b)	lessThan(C)
     (-∞..b]	atMost(C)
     (-∞..+∞)	all()
     */
    public static void product() {
        Range<Integer> range = Range.open(1, 3);
        Range<Integer> range2 = Range.openClosed(1, 3);
        // (1, +∞)
        Range<Integer> range3 = Range.greaterThan(1);
        // [1, +∞)
        Range<Integer> range4 = Range.atLeast(1);
        // (-∞, 1)
        Range<Integer> range5 = Range.lessThan(1);
        // (-∞, 1]
        Range<Integer> range6 = Range.atMost(1);
        // (-∞, +∞)
        Range<Integer> range7 = Range.all();
        Boolean contain = range.contains(2);
        System.out.println(contain);
        // 区间计算
        System.out.println(range7.containsAll(Ints.asList(1,2,3,4,5)));
    }



}
