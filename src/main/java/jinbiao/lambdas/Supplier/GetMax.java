package jinbiao.lambdas.Supplier;

import java.util.function.Supplier;

/**
 常用函数式接口
 ①提供类型：Supplier<T>接口
 特点：只出不进(get方法无入参有返回)，作为方法/构造参数、方法返回值
 java.util.function.Supplier<T>接口仅包含一个无参的方法：T get()。
 用来获取一个泛型参数指定类型的对象数据。

 Supplier<T>接口被称之为生产型接口,指定接口的泛型是什么类型,那么接口中的get方法就会生产什么类型的数据
 */
public class GetMax {

    public static int getMaxNum(Supplier<Integer> supplier){
        return supplier.get();
    }

    public static void main(String[] args) {
        int[] arr = {-1,0,1,2,3};
        int maxValue = getMaxNum(()->{
            int max = arr[0];
            for(int i=0;i<arr.length;i++){
                if(arr[i]>max){
                    max = arr[i];
                }
            }
            return max;
        });
        System.out.println("数组元素的最大值是："+maxValue);
    }

}
