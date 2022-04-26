package jinbiao.lambdas.Predicate;

import java.util.function.Predicate;

/**
 or方法：
 Predicate接口中有一个方法or,表示或者关系,也可以用于连接两个判断条件
 */
public class MyPredicateOr {
    public static boolean validateStr(String s, Predicate<String> pre1, Predicate<String> pre2){
//        return pre1.test(s) || pre2.test(s);
        return pre1.or(pre2).test(s);
    }

    public static void main(String[] args) {
        String s = "acdef";
        boolean b = validateStr(s,str->str.length()>5,str->str.contains("a"));

        validateStr(s, new Predicate<String>() {
            @Override
            public boolean test(String str) {
                return s.length()>5;
            }
        }, new Predicate<String>() {
            @Override
            public boolean test(String str) {
                return s.contains("a");
            }
        });
        System.out.println(b);

        boolean a = new Predicate<String>() {
            @Override
            public boolean test(String str) {
                return s.length() > 5;
            }
        }.or(new Predicate<String>() {
            @Override
            public boolean test(String str) {
                return s.contains("a");
            }
        }).test(s);
        System.out.println("方法体里面测试:"+a);
    }
}
