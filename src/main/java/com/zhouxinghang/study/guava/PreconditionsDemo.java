package com.zhouxinghang.study.guava;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by zhouxinghang on 2018/5/18.
 *
 方法声明（不包括额外参数）	描述	检查失败时抛出的异常
 checkArgument(boolean)	检查boolean是否为true，用来检查传递给方法的参数。	IllegalArgumentException
 checkNotNull(T)	检查value是否为null，该方法直接返回value，因此可以内嵌使用checkNotNull。	NullPointerException
 checkState(boolean)	用来检查对象的某些状态。	IllegalStateException
 checkElementIndex(int index, int size)	检查index作为索引值对某个列表、字符串或数组是否有效。index>=0 && index<size *	IndexOutOfBoundsException
 checkPositionIndex(int index, int size)	检查index作为位置值对某个列表、字符串或数组是否有效。index>=0 && index<=size *	IndexOutOfBoundsException
 checkPositionIndexes(int start, int end, int size)	检查[start, end]表示的位置范围对某个列表、字符串或数组是否有效*	IndexOutOfBoundsException
 */
public class PreconditionsDemo {

    public static void main(String[] args) {
        run1(true, Maps.newHashMap(), true);
    }

    /**
     * IllegalArgumentException   如果布尔值为false，就报非法参数异常
     * checkNotNull直接返回检查的参数，让你可以在构造函数中保持字段的单行赋值风格：this.field = checkNotNull(field)   方法名意思简单明了，便于阅读
     */
    public static void run1(boolean flag, Map<String, Object> msg, boolean state) {
        Preconditions.checkArgument(flag);
        msg = Preconditions.checkNotNull(msg);
        Preconditions.checkState(state);
        Preconditions.checkElementIndex(3, 4);
        Preconditions.checkPositionIndex(4, 4);
        Preconditions.checkPositionIndexes(3, 6, 7);
    }

}
