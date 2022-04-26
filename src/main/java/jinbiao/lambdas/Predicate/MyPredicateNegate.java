package jinbiao.lambdas.Predicate;

import java.util.function.Predicate;

/**
 negate方法：
 Predicate接口中有一个方法negate,也表示取反的意思
 */
public class MyPredicateNegate {
    public static boolean validateStr(String s, Predicate<String> pre){
//        return !pre.test(s);
        return pre.negate().test(s);
    }

    public static void main(String[] args) {
        String s = "acde";
        boolean b = validateStr(s,str->str.length()>5);

        System.out.println(b);

        //方法体里面完整写法:
        boolean test = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 5;
            }
        }.negate().test(s);
        System.out.println("方法体里面测试:"+test);
    }
}
