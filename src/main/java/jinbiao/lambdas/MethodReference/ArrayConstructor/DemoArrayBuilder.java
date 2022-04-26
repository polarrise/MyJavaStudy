package jinbiao.lambdas.MethodReference.ArrayConstructor;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Function;

/**
 * 数组的构造器引用
 * 数组也是 Object 的子类对象，所以同样具有构造器，只是语法稍有不同。
 * 格式：Type[]::new
 */
public class DemoArrayBuilder {
    @Test
    public void test5() {   //传入一个整型数字,得到一个该数字长度的数组
        Function<Integer, String[]> function = (x) -> new String[x];
        String[] strings = function.apply(5);
        System.out.println("数组长度:"+strings.length);

        Function<Integer,String[]> function1 = String[]::new;
        String[] strings1 = function1.apply(10);
        System.out.println("数组长度:"+strings1.length);
    }

    public static int[] createArray(int length,ArrayBuilder arrayBuilder){
        return arrayBuilder.builderArray(length);
    }

    public static void main(String[] args) {
        int[] arr1 = createArray(5,length -> new int[length]);

        System.out.println(arr1.length);

        /**
         * 1.已知创建的是int[]数组
         * 2.创建的数组长度也是已知的
         * 使用方法引用，int[]引用new，根据参数传递的长度创建数组
         */
        int[] arr2 = createArray(10,int[]::new);
        System.out.println(Arrays.toString(arr2));
        System.out.println(arr2.length);
        /**数组的构造器引用
         * 内部就是 1.lambda表达式 作为一个ArrayBuilder对象(实际是DemoArrayBuilder类型对象),传入到createArray方法中
         *         2.lambda表达式调用内部的方法体实现
         *         3.方法体(也就是接口里抽象方法的实现)。通过new int[length]得到了一个数组对象
         */
    }
}
