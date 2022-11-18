package com.jinbiao.lintCode_subject.classLibrary.ArrayList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author com.jinbiao
 * @date 2022/2/10
 * @apiNote
 */
public class LintCode2380 {

    public List createList(String str1, String str2) {
        // write your code here
        List<String> list=new ArrayList<String>();
        list.add(str1);
        list.add(str2);
        return  list;

    }
    public static void main(String[] args) {
        List<String> list = new LintCode2380().createList("zhangsan", "lisa");
        System.out.println(list);
    }
}
