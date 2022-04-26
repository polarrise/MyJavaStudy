package jinbiao.lintCode_subject.classLibrary.HashSet;

import java.util.*;

/**
 * @author jinbiao
 * @date 2022/2/10
 * @apiNote
 */
public class LintCode2889 {
    /**
     描述
     给定一个数组 arr，请对数组进行求和，但是数组中可能会有重复的元素，在进行数组求和时，对于重复的数据只用计算一次即可。
     请在 Solution 类中的 getSum() 方法中编写您的代码。
     补充:Set继承于Collection接口，是一个不允许出现重复元素，并且无序的集合
     */
    public int getSum(int arr[]) {
       Set<Integer> set=new HashSet<Integer>();
        Arrays.stream(arr).forEach(a->{
            set.add(a);
        });
        Integer sum1 = set.stream().reduce(0,(a, b)->a+b);  //  reduce 第一个参数 0 代表起始值为 0，lambda (a, b) -> a + b 即将两值相加产生一个新值
        return sum1;
    }

    /**
     * 解决方案二:利用list里面的contains方法
     */
    public int getSum2(int arr[]) {
        List<Integer> list = new ArrayList<>(arr.length);
        int sum=0;
        for (int i = 0; i < arr.length; i++) {
            if(!list.contains(arr[i])){
                list.add(arr[i]);
                sum += arr[i];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int []arr={1,2,2,3,4,5};
        int sum = new LintCode2889().getSum(arr);
        System.out.println(sum);
    }
}
