package com.jinbiao.HelperClass;

import java.util.LinkedList;

public class Stack_Struct {
    public static void main(String[] args) {
        String str = "abcdefg";
        LinkedList<Character> list = new LinkedList<Character>();

        //栈，从头部插入。也就是先进后出了
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //首位插入(也就是后进来的排在首位):在此列表的开头插入指定的元素。
            //list.add() 与list.addLast() 方法一样都是尾部插入
            list.addFirst(c);
        }

        String newstr = "";
        while(list.size() > 0) {
            newstr += list.removeFirst();
        }
        System.out.println(newstr);

        //队列，从尾部插入。也就是先进先出了
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        System.out.println(linkedList);
    }



}
