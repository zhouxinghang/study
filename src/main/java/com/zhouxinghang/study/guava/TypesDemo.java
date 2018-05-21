package com.zhouxinghang.study.guava;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.common.primitives.UnsignedInts;

import java.util.List;

/**
 * Created by zhouxinghang on 2018/5/21.
 * http://ifeve.com/google-guava-primitives/
 *
 *
 * 原生类型	Guava工具类（都在com.google.common.primitives包）
 byte	Bytes, SignedBytes, UnsignedBytes
 short	Shorts
 int	Ints, UnsignedInteger, UnsignedInts
 long	Longs, UnsignedLong, UnsignedLongs
 float	Floats
 double	Doubles
 char	Chars
 boolean	Booleans
 */
public class TypesDemo {

    public static void main(String[] args) {
        System.out.println("======ints======");
        ints();
        System.out.println("=======formate======");
        formate();
    }


    /**
     *  原生类型数组工具 下面以int数组工具类Ins为例
     方法签名	描述	类似方法	可用性
     List<Wrapper> asList(prim… backingArray)	把数组转为相应包装类的List	Arrays.asList	符号无关*
     prim[] toArray(Collection<Wrapper> collection)	把集合拷贝为数组，和collection.toArray()一样线程安全	Collection.toArray()	符号无关
     prim[] concat(prim[]… arrays)	串联多个原生类型数组	Iterables.concat	符号无关
     boolean contains(prim[] array, prim target)	判断原生类型数组是否包含给定值	Collection.contains	符号无关
     int indexOf(prim[] array, prim target)	给定值在数组中首次出现处的索引，若不包含此值返回-1	List.indexOf	符号无关
     int lastIndexOf(prim[] array, prim target)	给定值在数组最后出现的索引，若不包含此值返回-1	List.lastIndexOf	符号无关
     prim min(prim… array)	数组中最小的值	Collections.min	符号相关*
     prim max(prim… array)	数组中最大的值	Collections.max	符号相关
     String join(String separator, prim… array)	把数组用给定分隔符连接为字符串	Joiner.on(separator).join	符号相关
     Comparator<prim[]>   lexicographicalComparator()	按字典序比较原生类型数组的Comparator	Ordering.natural().lexicographical()	符号相关
     */
    public static void ints() {
        List<Integer> list = Ints.asList(1, 2, 3, 4, 5, 6);
        boolean contains = Ints.contains(Ints.toArray(list), 3);
        System.out.println(contains);
        // compare比较，-1,0,1
        System.out.println(Ints.compare(2,2));
        // 串联多个原生类型数组
        int[] arrays = Ints.concat(Ints.toArray(list), Ints.toArray(list));
        System.out.println("[" + Ints.join(", ", arrays) + "]");
        // 判断原生类型数组是否包含定值
        System.out.println(Ints.contains(arrays, 3));
        // 定值在数组中首次出现的位置，若没有返回-1
        System.out.println(Ints.indexOf(arrays, 5));
        // 将原生数组用给定字符分割为数组
        System.out.println(Ints.join("个苹果, ", arrays) + "个苹果");
        Ints.lexicographicalComparator();

    }

    /**
     * 字节转换方法
     */
    public static void formate() {
        int intSize = Ints.BYTES;
        int longSize = Longs.BYTES;
        /**
         * 使用字节数组的前Prims.BYTES个字节，按大字节序返回原生类型值；
         * 如果bytes.length <= Prims.BYTES，抛出IAE
         */
        int l = Ints.fromByteArray(new byte[]{'a','b','c','d','e'});
        System.out.println(l);
    }

}
