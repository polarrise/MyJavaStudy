package com.jinbiao.manyThreads.tulinSchool.CASDemo;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Author：Jinbiao
 * @Date：2023/3/5 20:57
 * @Desc：
 */
public class AtomicArray {
    static int[] value = new int[] { 1, 2 };
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);
    public static void main(String[] args) {
        ai.getAndSet(0, 3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);//原数组不会变化
    }
}
