package jinbiao.lambdas.MethodReference.ClassReStaticMethod;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * 通过类名称引用静态方法
 * 类已经存在，静态方法已经存在，则可以通过类名直接引用静态成员方法
 */
public class MethodRerference2 {
    @Test
    public void test1(){
        Comparator<Integer> comparator = (x, y)->Integer.compare(x,y);
        Comparator<Integer> comparator1 = Integer::compare;    //类已经存在，静态方法已经存在，则可以省略参数列表,return关键字。 通过类名直接引用静态成员方法
        List<Integer> list = Lists.newArrayList(1, 3, 2);
        list.sort(comparator1);
        System.out.println(list);

        int compare = comparator1.compare(1, 2);   //-1则a<b  ,  1则a>b
        System.out.println("类名直接引用静态成员方法:"+compare);

        int compare1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);    //类已经存在，静态方法已经存在，则可以通过类名直接引用静态成员方法
            }
        }.compare(1, 2);
        System.out.println("完整写法:"+compare1);
    }

    @Test
    public void test2(){
        BiPredicate<String,String> biPredicate = (x, y) -> x.equals(y);
        BiPredicate<String,String> biPredicate1 = String::equals;
        boolean result = new BiPredicate<String, String>() {
            @Override
            public boolean test(String a, String b) {
                return a.equals(b);
            }
        }.test("a", "a");
        System.out.println(result);

        boolean result2 = biPredicate.test("b", "b");
        System.out.println(result2);

        boolean result3 = biPredicate1.test("c", "c");
        System.out.println(result3);
    }
    public static int method(int num,MyCalc c){
        return c.calc(num);
    }

    public static void main(String[] args) {
        //：如果{}中的代码只有一行，无论是否有返回值，都可以省略（{}，return，分号）
        int number = method(-10, num -> Math.abs(num));   //类已经存在，静态方法已经存在，则可以通过类名直接引用静态成员方法

        int number1 = method(-10, Math::abs);   //类已经存在，静态方法已经存在，则可以省略参数列表,通过类名直接引用静态成员方法
        System.out.println(number);
        System.out.println(number1);

        int calc = new MyCalc() {
            @Override
            public int calc(int num) {
                return Math.abs(num);
            }
        }.calc(-10);
        System.out.println(calc);
    }
}

