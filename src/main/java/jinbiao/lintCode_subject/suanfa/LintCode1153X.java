package jinbiao.lintCode_subject.suanfa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 描述
 给定一些由,隔开的字符串，请将他们按字典序排列。
 微信加 jiuzhang15 发送验证信息【1153】领最新大厂高频题

 字符串仅包含小写字母。
 字符串数量\leq 1\,000≤1000且总长度\leq 10^5≤10
 5
 */
public class LintCode1153X {
    public String sorting(String s) {
        String[] split = s.split(",");
        List<String> list = Arrays.asList(split);
        Collections.sort(list);
        String last=list.get(list.size()-1);
        StringBuilder str=new StringBuilder();
        list.stream().forEach(a->{
            if(a.equals(last)){
                str.append(a);
            }else
            str.append(a+",");
        });
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(new LintCode1153X().sorting("bb,aa,lintcode,c"));
    }
}
