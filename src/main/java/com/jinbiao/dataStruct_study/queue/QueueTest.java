package com.jinbiao.dataStruct_study.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 队列是一种特殊的线性表，它只允许在表的前端进行删除操作，而在表的后端进行插入操作。
 LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用。
 */
public class QueueTest {

    public static void main(String[] args) {
         QueueTest.test();
    }
    public static void test() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        for(int e : queue) {    //遍历队列
            System.out.println(e);
        }
        System.out.println("----------");
        System.out.println("poll : " + queue.poll()); //remove() 和 poll() 方法都是从队列中删除第一个元素,队列为空时,poll()返回null,而remove会报空指针异常
        System.out.println("----------");

        for(int e : queue) {
            System.out.println(e);
        }
        System.out.println("ele is: " + queue.element()); //获取队列的头但不移除此队列的头
        System.out.println("----------");

        for(int e : queue) {
            System.out.println(e);
        }

        /**
         * 1、element()获取队列的头但不移除此队列的头。如果此队列为空，则将抛出NoSuchElementException异常。
         * 2、peek()获取队列的头但不移除此队列的头。如果此队列为空，则返回 null。
         */
        System.out.println("peek : " + queue.peek());
        System.out.println("----------");

        for(int e : queue) {
            System.out.println(e);
        }
    }

}
