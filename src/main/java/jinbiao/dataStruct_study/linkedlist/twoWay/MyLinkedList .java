package jinbiao.dataStruct_study.linkedlist.twoWay;

import java.util.ArrayList;
import java.util.List;

class MyLinkedList <E> {
     int size;       //链表的长度
     Node<E> first;      //链表的头节点
     Node<E> last;      //指向最后一个节点的指针R

    /**
     * 链表 e 作为最后一个元素。
     */
    void add(E e) {
         Node<E> newNode = new Node<>(last, e, null);
        if (last == null) {
            first = newNode;
            last = newNode;
        }
        else {
            last.next = newNode;
            newNode.prev = last;
            last=newNode;
        }
        size++;
    }

    /*插入1,2,3,4,5,  形成一个5->4->3->2->1的链表。   也就是先进后出，后进先出
     * <p> 表头插入节点
     *  思路： 1 将新节点的前驱节点指向null，
     *        2新节点的后续节点指向表头
     *        3 将表头的前驱节点指向新节点
     */
    public void addFirst(int data){
        Node newNode = new Node(null,data,first); // 创建新节点
        if (first==null){    //如果表头为空直接将新节点作为头
            first=newNode;
            last=newNode;
        }else {
            // 将新节点的前驱节点指向null(声明的时候本来就是null)
            //新节点的后续节点指向表头
            newNode.next=first;    //新节点后面是前一个链表的头节点
            first.prev=newNode;
            first=newNode;
        }
        size++;
    }

    /**
     * <p> 指定位置插入节点
     * 思路： 假设在AB直接插入新节点N
     *       1 A 节点的后续节点指向 N
     *       2 N 节点 的前驱节点指向 A
     *       3 N 节点的后续节点指向 B
     *       4 B 节点的前驱节点指向 N
     * 重点也是找到A节点的位置
     */
    public void add(int data, int index){
        // 索引超出，非法
        if (index<0 || index>size){
            System.out.println("非法索引");
            return;
        }
        // 如果索引为0，调用addFirst方法
        if (index==0){
            addFirst(data);
            return;
        }
        // 如果索引等于链表的长度，调用addLast方法
        if (index==size){
            addLast(data);
            return;
        }
        // 创建新节点
        Node newNode = new Node(data);
        Node dummy=first;
        int tail=0;
        while(dummy!=null&&dummy.next!=null){
             if(tail==index){
                 dummy.prev.next=newNode;
                 newNode.prev=dummy.prev;
                 newNode.next=dummy;
                 dummy.prev=newNode;
                 size++;
                 break;
             }else {
                 dummy = dummy.next;
                 tail++;
             }
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("非法索引");
            return;
        }
        // 头节点
        if (index == 0) {
           first=first.next;
           first.prev=null;
           size--;
        }
        // 尾节点
        if (index == (size - 1)) {
           last=last.prev;
           last.next=null;
            size--;
        }
        //删除中间的节点
        int tail = 0;
        Node dummy=first.next;    //dummy直接从第二个节点开始
        while (dummy!=null&&dummy.next!=null){   //index=1,删除一号索引的节点
            if((index-1)==tail){
               dummy.prev.next=dummy.next;
               dummy.next.prev=dummy.prev;
               size--;
               break;
            }else{
                dummy=dummy.next;
            }
        }
    }

    public static void main(String[] args) {
        MyLinkedList doubleList = new MyLinkedList();
        for (int i = 1; i <=5 ; i++) {
            //doubleList.addFirst(i);     //双向链表-首插法
            doubleList.addLast(i);        //双向链表-尾插法
        }
        Node first = doubleList.first;
        while(first!=null){
            System.out.println(first);
            first=first.next;
        }
        //doubleList.add(0);          //往尾结点后插入0节点
        //doubleList.displayNext();
        //System.out.println(doubleList);
        //System.out.println("首节点:"+doubleList.first);
        //System.out.println("尾节点:"+doubleList.last);
        //System.out.println("链表长度为:"+doubleList.size);
        //System.out.println("==========================");
        //doubleList.add(55,1);    //在1号索引插入55节点
        //System.out.println(doubleList);
        //System.out.println("首节点:"+doubleList.first);
        //System.out.println("尾节点:"+doubleList.last);
        //System.out.println("链表长度为:"+doubleList.size);
        //
        //System.out.println("==========================");
        //doubleList.remove(6);         //删除6号索引的节点(尾结点)
        //System.out.println(doubleList);
        //System.out.println("首节点:"+doubleList.first);
        //System.out.println("尾节点:"+doubleList.last);
        //System.out.println("链表长度为:"+doubleList.size);
        //
        //System.out.println("==========================");
        //doubleList.remove(1);
        //System.out.println(doubleList);
        //System.out.println("首节点:"+doubleList.first);
        //System.out.println("尾节点:"+doubleList.last);
        //System.out.println("链表长度为:"+doubleList.size);
    }

    /**
     * 双向链表-尾插法:
     * 思路如下:
     * 表尾的后续节点指向新节点
     * 新节点的前驱节点指向表尾
     * 新节点的后续节点指向null
     */
    public void addLast(int data){
        Node newNode=new Node(last,data,null);
        if(last==null){
            first=newNode;
            last=newNode;
       }else{
            last.next=newNode;
            newNode.prev=last;
            last=newNode;
        }
        size++;
    }

    /**
     * <p>顺序打印链表
     *思路：从链表的头遍历到链表的尾巴
     * */
    public void displayNext(){
        // 将表头作为当前节点
        Node currentNode = first;
        // 遍历链表
        while (currentNode!=null){
            // 打印数据
            System.out.println(currentNode.value);
            System.out.println(currentNode.value+"的前驱为:"+currentNode.prev);    //打印每个节点的前驱
            System.out.println(currentNode.value+"的后继为:"+currentNode.next);    //打印每个节点的前驱
            // 将下一个节点作为当前节点
            currentNode = currentNode.next;
        }
    }


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


class Node <E>{
    Node<E>prev;
    E value;
    Node<E>next;
    Node(){

    }
    Node (E value){
       this.value = value;
   }
    Node(Node<E> prev, E element, Node<E> next) {
        this.value = element;
        this.next = next;
        this.prev = prev;
    }

    //@Override
    //public String toString() {
    //    return "Node{" +
    //
    //            "value=" + value +
    //            ", next=" + next +
    //            '}';
    //}
}



