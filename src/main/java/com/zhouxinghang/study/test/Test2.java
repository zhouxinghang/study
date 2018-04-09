package com.zhouxinghang.study.test;

/**
 * Created by zhouxinghang on 2018/4/8.
 */
public class Test2 {
    private String id;

    private Stu stu;
    public static void main(String[] args) {
        Test2 t2 = new Test2();
        t2.setId("100");
        Stu stu = new Stu();
        stu.setAge("15");
        stu.setName("zzz");
        t2.setStu(stu);
        System.out.println(t2.toString());
    }

    public Stu getStu() {
        return stu;
    }

    public void setStu(Stu stu) {
        this.stu = stu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Test2{" +
               "id='" + id + '\'' +
               ", stu=" + stu +
               '}';
    }
}


class Stu {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Stu{" +
               "name='" + name + '\'' +
               ", age='" + age + '\'' +
               '}';
    }
}
