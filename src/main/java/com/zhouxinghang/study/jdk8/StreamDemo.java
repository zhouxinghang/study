package com.zhouxinghang.study.jdk8;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zhouxinghang on 2018/5/23.
 */
public class StreamDemo {

    public static void main(String[] args) {
        demo();
        lazyseq();
        System.out.println("====fibonacci====");
        //List<Long> fibonacci = getFibonacci(50);

        getPiByEuler();

    }


    /**
     * map() 计算转换
     * filter() 过滤
     * skip() 跳过
     * limit() 取前多少个
     * forEach() 迭代
     */
    public static void demo() {
        List<Integer> list = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
        Stream<Integer> stream = list.stream();
        stream.filter(i -> i % 3 == 0)
            .map(i -> i + 100)
            .skip(3)
            .limit(10)
            .forEach(System.out::println);
    }


    /**
     * Stream的延迟计算特性，他是在访问的时候才会去计算的，这样就会较少大量的内存占用（当集合非常大的时候）
     * 集合类的迭代调用是调用者负责，通常是for循环。而Stream迭代是隐藏在Stream各种操作中的，如map()
     *
     * 我们通过获取自然数来模拟Stream的延迟计算
     */
    public static void lazyseq() {
        Stream<Long> stream = Stream.generate(new NaturalSupplier());
        System.out.println("Stream=====>");
        stream.filter(i -> i % 3 == 0)
            .map(i -> i * 2)
            .limit(100)
            .forEach(System.out::println);
    }

    /**
     * 获取斐波那契数列
     * @param length
     */
    public static List<Long> getFibonacci(int length) {
        Stream<Long> stream = Stream.generate(new FibonacciSupplier());
        List<Long> list = stream.limit(length)
            .collect(Collectors.toList());
        list.forEach(System.out::println);
        return list;
    }

    /**
     * 通过欧拉级数来计算圆周率
     */
    public static void getPiByEuler() {
        Stream<Double> stream = Stream.generate(new PiSupplier());
        stream.map(new EulerTransform())
            //可多次应用欧拉级数来加速计算圆周率
            .map(new EulerTransform())
            .map(new EulerTransform())
            .map(new EulerTransform())
            .map(new EulerTransform())
            .limit(100)
            .forEach(System.out::println);
    }

    private static class NaturalSupplier implements Supplier<Long> {
        private long value = 0;

        @Override
        public Long get() {
            System.out.println("get===>");
            return this.value++;
        }
    }

    private static class FibonacciSupplier implements Supplier<Long> {
        private long a = 0;
        private long b = 1;


        @Override
        public Long get() {
            long temp = a + b;
            a = b;
            b = temp;
            return b;
        }

    }

    private static class PiSupplier implements Supplier<Double> {

        double sum = 0.0;
        double current = 1.0;
        boolean sign = true;

        @Override
        public Double get() {
            sum += (sign ? 4 : -4) / this.current;
            this.current = this.current + 2.0;
            this.sign = ! this.sign;
            return sum;
        }
    }


    private static class EulerTransform implements Function<Double, Double> {

        double n1 = 0.0;
        double n2 = 0.0;
        double n3 = 0.0;

        @Override
        public Double apply(Double t) {
            n1 = n2;
            n2 = n3;
            n3 = t;
            if (n1 == 0.0) {
                return 0.0;
            }
            return calc();
        }

        double calc() {
            double d = n3 - n2;
            return n3 - d * d / (n1 - 2 * n2 + n3);
        }
    }
}
