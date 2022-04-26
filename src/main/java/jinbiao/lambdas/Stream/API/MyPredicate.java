package jinbiao.lambdas.Stream.API;

import java.util.*;
import java.util.function.Predicate;

/**
 * Stream操作的三个步骤：
 *
 * 创建Stream：一个数据源（如集合、数组），获取一个流
 * 中间操作：一个操作链，对数据源的数据进行处理
 * 终止操作：一个终止操作，执行中间操作链并产生结果
 * 注意：“Stream流”其实是一个集合元素的函数模型，它并不是集合，也不是数据结构，其本身并不存储任何元素（或其地址值）。
 */
public class MyPredicate {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(10,20,3,-5,-8);
        Collection<Integer> positiveNum = filter(nums,num->num>0);
        Collection<Integer> negativeNum = filter(nums,num->num<0);
        System.out.println(positiveNum);
        System.out.println(negativeNum);

        boolean test = new Predicate<Integer>() {
            @Override
            public boolean test(Integer o) {
                return o > 0;
            }
        }.test(2);
        System.out.println(test);
    }

    private static <E> Collection<E> filter(Collection<E> source, Predicate<E> predicate){
        List<E> list = new ArrayList<>(source);
        Iterator<E> iterator = list.iterator();
        while (iterator.hasNext()){
            E element = iterator.next();
            if(!predicate.test(element)){
                iterator.remove();
            }
        }
        return Collections.unmodifiableList(list);
    }
}
