package jinbiao.lintCode_subject.classLibrary.HashSet;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author jinbiao
 * @date 2022/2/10
 * @apiNote
 */
public class LintCode2888 {

    /**
     * 描述:给定两个数组 arr1 和 arr2，请您将两个数组合并成一个有序的数组，并去除数组中的重复数字。
     * 样例一:当 arr1 = [6, 2, 1]，arr2 = [9, 4, 5]时，返回数组结果为：
     * [1, 2, 4, 5, 6, 9]
     * 样例二:当 arr1 = [2, 1]，arr2 = [4, 1]时，返回数组结果为：
     * [1, 2, 4]
     */
    public int[] mergeArray(int arr1[], int arr2[]) {

        HashSet<Integer> hashSet = new HashSet<Integer>();
        Arrays.stream(arr1).forEach(a->{
            hashSet.add(a);
        });
        Arrays.stream(arr2).forEach(a->{
            hashSet.add(a);
        });
        Integer[]arr=new Integer[hashSet.size()];
        Integer [] reArr= hashSet.toArray(arr);        //HashSet转为数组
        Arrays.sort(reArr);                           //对数组进行排序
        int[] intArray = Arrays.stream(reArr).mapToInt(Integer::intValue).toArray();  //Integer数组转成int数组:可以使用Java 8的Stream API
        return intArray;
    }

    public static void main(String[] args) {
        int []arr1={84, 12, 42, 99, 28, 49, 56, 27, 26, 38};
        int []arr2={45, 69, 6, 53, 85, 11, 35, 21, 18, 59, 32, 6};
        int[] ints = new LintCode2888().mergeArray(arr1, arr2);
        Arrays.stream(ints).forEach(a->{
            System.out.println(a);
        });
    }
}
