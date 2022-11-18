package com.jinbiao.javaStudy;

import java.util.LinkedList;

public class linkedList {
    public static void main(String[] args) {
        String str = "abcdefg";
        LinkedList<Character> list = new LinkedList<Character>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            list.addFirst(c);
        }

        String newstr = "";
        while(list.size() > 0) {
            newstr += list.removeFirst();
        }
        System.out.println(newstr);

    }



}
