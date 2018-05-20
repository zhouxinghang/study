package com.zhouxinghang.study.guava;


import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * Created by zhouxinghang on 2018/5/18.
 */
public class ObjectsDemo {

    public static void main(String[] args) {
        run1();
        run2();
        run3();
    }

    public static void run1() {
        System.out.println(Objects.equal("a", "a"));
        System.out.println(Objects.equal("a", null));
        System.out.println(Objects.equal(null, null));
    }

    public static void run2() {
        String str = "guava";
        System.out.println(Objects.hashCode(str));
        System.out.println(Objects.hashCode("java", "guava", "google"));
        System.out.println(Objects.hashCode("google", "guava", "java"));
        System.out.println(str.hashCode());
        System.out.println(Objects.hashCode(str));
    }

    /**
     * toString方法没有了~
     */
    public static void run3() {
        Student stu = new Student("zxh", 23);


    }


    private static class Student {
        private String name;
        private Integer age;

        public Student(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                   "name='" + name + '\'' +
                   ", age=" + age +
                   '}';
        }
    }
}
