package com.zhouxinghang.study.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouxinghang on 2018/5/21.
 * 类似于ConcurrentMap,但是多了过期删除机制
 * http://ifeve.com/google-guava-cachesexplained/
 */
public class CacheDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("=====cacheBuilder=====");
        cacheBuilder();
        System.out.println("=======cacheLoader======");
        cacheLoader();
        System.out.println("=======callable=======");
        callable();
        System.out.println("=====asMap=====");
        asMap();
        System.out.println("======refresh=======");
        refresh();
        System.out.println("=======recordStats======");
        recordStats();
    }

    /**
     * CacheBuilder.newBuilder()  用来创建Cache或LoadingCache
     * 以创建Cache为例
     * 监听机制removalListener https://blog.csdn.net/aitangyong/article/details/53127605
     */
    public static void cacheBuilder() throws ExecutionException, InterruptedException {
        Cache<String, Object> cache = CacheBuilder.newBuilder()
            // 设置容量大小
            .maximumSize(1000)
            //开启统计功能，开启后，Cache.stats()方法会返回CacheStats对象
            .recordStats()
            // 这段时间内没有被读写访问就会过期回收
            .expireAfterAccess(100, TimeUnit.MILLISECONDS)
            // 这段时间内没有被写访问就会被过期回收
            .expireAfterWrite(500, TimeUnit.MILLISECONDS)
            // 监听事件，元素被删除时，调用此方法
            .removalListener(new RemovalListener<String, Object>() {
                @Override
                public void onRemoval(RemovalNotification<String, Object> removalNotification) {
                    System.out.println("key：" + removalNotification.getKey() + ", value:" + removalNotification.getValue() + "被移除了，原因是：" + removalNotification.getCause());
                }
            })
            .build();
        cache.put("name", "周星航");
        cache.get("name", () -> "没有这个值，通过访问DB处理得到结果，将结果存入缓存");
        //cache.invalidate("name");
        Thread.sleep(1000);
        /*key过期了不会被马上清理，它会在写操作时顺带做少量的维护工作，或者偶尔在读操作时做——如果写操作实在太少的话。（官方文档）
            通过打印信心发现，removalListener信息会在get时之前打印而不是在key过期的时候
        */
        System.out.println(cache.get("name", () -> "没有这个值，过期了,需要重新从DB获取"));
    }


    /**
     * build()中传入CacheLoader，实现load方法来创建LoadingCache。
     * 通过CacheBuilder创建LoadingCache，实现load方法,当key不存在时，会调用此方法
     */
    public static void cacheLoader() {
        LoadingCache<String, Object> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .build(new CacheLoader<String, Object>() {
                /** 当缓存未命中时，调用load获取结果并将结果缓存 **/
                @Override
                public Object load(String s) throws Exception {
                    System.out.println("当缓存未命中时，调用load获取结果并将结果缓存");
                    return "缓存中没有这个key值";
                }
            });

        cache.put("name", "zhouxinghang");
        try {
            System.out.println(cache.get("name1"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //如果cache声明了检查型异常，就不能使用getUnchecked()方法
        System.out.println(cache.getUnchecked("name"));
        //批量查询
        try {
            //返回一个不可变的map
            ImmutableMap<String, Object> values = cache.getAll(Lists.newArrayList("name", "name1"));
            System.out.println(values);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * get(k,Callable) 如果存在就返回，不存在调用callable中call方法，将返回的结果加入到cache。
     * 实现有缓存则返回，无缓存则计算、存入缓存、返回
     * 这个方法是在Cache接口中定义的，Cache接口只有这个get方法，没有LoadingCache的get(key)方法
     * 每次get都要重写call方法，推荐只用LoadingCache
     */
    public static void callable() {
        Cache<String, Object> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .build();

        try {
            Object result = cache.get("name", new Callable<Object>() {
                /** 缓存不命中时候调用 **/
                @Override
                public Object call() throws Exception {
                    //do something
                    System.out.println("这个key缓存中没有，通过访问DB并处理得到结果，这个结果会存入缓存");
                    return "新value";
                }
            });
            System.out.println(result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //再调用一次get同样的key，发现key已经存入缓存
        try {
            Object result = cache.get("name", new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    //do something
                    return "这个key已近存入缓存了，所以不会调用call方法";
                }
            });
            System.out.println(result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * asMap视图，提供了缓存的ConcurrentMap形式，但asMap只是包含加到缓存中的key。asMap().get(key)等同于cache.getIfPresent()
     * 显示插入put
     * 视图提供的插入cache.asMap().putIfAbsent()//插入如果key不存在
     */
    public static void asMap() throws ExecutionException {
        Cache<String, Object> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .build();
        cache.put("name", "周星航");
        cache.asMap().putIfAbsent("age", "23");
        System.out.println(cache.get("age", () -> "key未命中"));
        ConcurrentMap<String, Object> cacheMap = cache.asMap();
        System.out.println(cacheMap.get("sex"));
    }


    /**
     * 刷新，为key加载新值。异步，缓存然可以向其他线程返回旧值，而不像回收操作，读缓存的线程必须等待新值加载完成（官方文档）
     * 可以重写reload方法，来扩展刷新行为
     */
    public static void refresh() throws ExecutionException {
        LoadingCache<String, Object> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .refreshAfterWrite(1, TimeUnit.MINUTES) // 定时刷新
            .build(
                new CacheLoader<String, Object>() {

                    @Override
                    public Object load(String s) throws Exception {
                        System.out.println("缓存为命中，重新加载，");
                        return "新value";
                    }

                    @Override
                    public ListenableFuture<Object> reload(String key, Object oldValue) throws Exception {
                        //异步实现。。。
                        System.out.println("执行缓存刷新操作，异步实现");
                        return super.reload(key, oldValue);
                    }
                }
            );
        cache.put("name", "周星航");
        System.out.println(cache.get("name"));
        cache.refresh("name");
        System.out.println(cache.get("name"));
    }

    /**
     * 开启统计功能，开启后，Cache.stats()方法会返回CacheStats对象，
     */
    public static void recordStats() throws ExecutionException {
        LoadingCache<String, Object> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .recordStats()
            .build(new CacheLoader<String, Object>() {

                @Override
                public Object load(String s) throws Exception {
                    System.out.println("缓存未命中，重新加载");
                    return "新的value";
                }
            });
        cache.put("name", "周星航");
        System.out.println(cache.get("name"));
        System.out.println(cache.get("key"));
        System.out.println(cache.get("key2"));
        System.out.println(cache.get("key3"));
        System.out.println(cache.get("key4"));
        CacheStats stats = cache.stats();
        System.out.println(stats);
    }
}
