package com.jinbiao.lambdas.study;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author com.jinbiao
 * @date 2021/10/27
 * @apiNote
 */
public class Test1 {
    public static void main(String[] args) {
         Scanner scanner=new Scanner(System.in);
         String next = scanner.next();
         String[] arr=next.split("");

        LinkedList<String>linkedList=new LinkedList<String>();
        Arrays.stream(arr).forEach(a->{
            linkedList.add(a);
        });
        System.out.println(linkedList);
        for (int i = linkedList.size()-1; i >= 0; i--) {
            System.out.print(linkedList.get(i));
        }

    }
}

