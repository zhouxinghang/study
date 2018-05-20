package com.zhouxinghang.study.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.SortedMultiset;
import com.google.common.collect.Table;
import com.google.common.collect.TreeMultiset;

import org.eclipse.jetty.util.MultiMap;

import sun.security.provider.certpath.Vertex;

import java.awt.peer.SystemTrayPeer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhouxinghang on 2018/5/18.
 * http://ifeve.com/google-guava-newcollectiontypes/
 */
public class NewCollectionDemo {

    public static void main(String[] args) {
        multiset();
        sortedMultiset();
        multimap();
        biMap();
        hashBasedTable();
    }

    /**
     * MultiSet 可以看做是Map<E,Integer>可用来计数。可以看做是没有元素顺序限制的ArrayList
     *
     方法	描述
     count(E)	给定元素在Multiset中的计数
     elementSet()	Multiset中不重复元素的集合，类型为Set<E>
     entrySet()	和Map的entrySet类似，返回Set<Multiset.Entry<E>>，其中包含的Entry支持getElement()和getCount()方法
     add(E, int)	增加给定元素在Multiset中的计数
     remove(E, int)	减少给定元素在Multiset中的计数
     setCount(E, int)	设置给定元素在Multiset中的计数，不可以为负数
     size()	返回集合元素的总个数（包括重复的元素）
     */
    public static void multiset() {
        //MultiSet是接口，HashMultiSet是实现类
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("java");
        multiset.add("guava");
        multiset.add("java",3);
        System.out.println(multiset.count("java"));
        // 获取不重复元素Set，【java，guava】
        System.out.println(multiset.elementSet().size());

    }

    /**
     * 高效的获取指定范围内的子集  怎么弄？？
     */
    public static void sortedMultiset() {
        SortedMultiset<String> sortedMultiset = TreeMultiset.create();
        sortedMultiset.add("java",4);
        sortedMultiset.add("guava", 5);
        Set<Multiset.Entry<String>> entrySet = sortedMultiset.entrySet();
        for (Multiset.Entry<String> entry : entrySet) {
            System.out.println(entry.getElement() + "-" + entry.getCount());
        }

    }

    /**
     * 为了替换 Map<K,List<V>>或 Map<K,Set<V>>
     * Multimap是把键映射到任意多个值的一般方法。
     * asMap()  是将listMultimap转换为Map<String, Collection<Obj>>
     */
    public static void multimap() {
        ListMultimap<String, Object> listMultimap = ArrayListMultimap.create();
        listMultimap.put("java", "oracle");
        listMultimap.put("java", "sun");
        listMultimap.put("java", new HashMap<>());
        Map<String, Collection<Object>> map = listMultimap.asMap();
        Collection collection = map.get("java");
        for (Object obj : collection) {
            System.out.println(obj);
        }

        List<Object> objectList = listMultimap.get("java");
        System.out.println(objectList);
    }

    /**
     * 维持map的双向映射，即Map<a,b>和Map<b,a>
     * 通过inverse反转
     */
    public static void biMap() {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("ping", "pong");
        biMap.put("zhong", "guo");
        System.out.println(biMap.get("ping"));
        BiMap<String, String> biMapFan = biMap.inverse();
        System.out.println(biMapFan.get("pong"));
    }

    /**
     * 本质上用HashMap<R, HashMap<C, V>>实现
     * 实现多索引功能，下面实现的是坐标上点的值
     */
    public static void hashBasedTable() {
        Table<Integer, Integer, Integer> weightedGraph = HashBasedTable.create();
        weightedGraph.put(1, 1,4);
        weightedGraph.put(1 ,2,5);
        weightedGraph.put(2, 1,5);
        Integer i = weightedGraph.row(1).get(2);
        System.out.println(i);
    }

    /**
     * 键是类型，值是符合键所指类型的对象
     */
    public static void classToInstanceMap() {

    }

    /**
     * 不连续，非空的空间
     */
    public static void RangeSet() {

    }
}
