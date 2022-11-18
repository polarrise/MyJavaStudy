package com.jinbiao.lintCode_subject.lintCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author com.jinbiao
 * @date 2021/12/25
 * @apiNote
 */
public class Test {


    /**
     * 输入：
     * str = "abcdefg"
     * offset = 3
     * 输出：
     * "efgabcd"

     */
        public void rotateString(char[] str, int offset) {
            // write your code here
           List<Character> preList=new ArrayList<Character>();
           List<Character> nextList=new ArrayList<Character>();
            List<Character> reList=new ArrayList<Character>();
            for(int i=0;i<str.length;i++){
                if(i<offset+1){  //0<4
                    preList.add(str[i]);  //a,b,c,d
                }else{
                    nextList.add(str[i]);  //e,f,g
                }
            }
            reList.addAll(nextList);
            reList.addAll(preList);
            for (int i=0;i<reList.size();i++) {
                str[i]=reList.get(i);
            }
            System.out.println(str);
        }


    public static void main(String[] args) {
        char[] str = {'a', 'b', 'c','d', 'e', 'f','g'};
        new Test().rotateString(str,3);
    }
}
