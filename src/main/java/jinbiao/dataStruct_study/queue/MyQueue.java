package jinbiao.dataStruct_study.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * 概念:队列是一种先进先出的数据结构,在头部添加元素,在尾部添加删除
 * lintCode:492 · 队列维护
 按链表实现队列。支持以下基本方法：
 enqueue(item).将新元素放入队列中。
 dequeue(). 将第一个元素移出队列，返回它。
 */
public class MyQueue<E> {
    public class Node{
        public E value;
        public Node next;

        public Node(E item){
            value = item;
            next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }
    public Node first;
    public Node last;
    public Node tail;
    /**
     *  enqueue(item).将新元素放入队列中。
     */
    public void enqueue(E item) {   //1->2->3->4->5
         Node node=new Node(item);   //构造节点
        if(last==null){
            first=node;
            last=node;
        }else{
            last.next=node;
            last=node;
        }
    }
    /**
     * dequeue(). 将第一个元素移出队列，返回它。
     */
    public E dequeue() {
        if(first.next!=null){   //如果首节点有后继
            Node deleteNode=first;
            first=first.next;
            return deleteNode.value;
        }else{                 //首节点等于尾结点
            Node deleteNode=first;
            first=null;
            last=null;
            return deleteNode.value;
        }
    }
    public static void main(String[] args) {
        MyQueue<Integer>queue=new MyQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        Integer integer = queue.dequeue();
        System.out.println("队列删除的首个元素:"+integer);
        System.out.println("队列移除首个元素后:"+queue);
        queue.enqueue(4);
        System.out.println("-----------------------");
        System.out.println("队列新增元素后:"+queue);
        Integer integer1 = queue.dequeue();
        System.out.println("-----------------------");
        System.out.println("队列删除的首个元素:"+integer1);
        System.out.println("队列移除首个元素后:"+queue);
    }
    @Override
    public String toString() {
        List<E> list=new ArrayList<E>();
        Node tail=first;
        while(tail!=null){
            list.add((E) tail.value);
            tail=tail.next;
        }
        StringBuilder str =new StringBuilder();
        str.append(list);
        return str.toString();
    }


}
