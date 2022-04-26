package jinbiao.lintCode_subject.conditionalStatements;

/**
 * @author jinbiao
 * @date 2022/1/17
 * @apiNote
 */
public class LintCode25XX {
    /**
     * LintCode2515 唯一存在的数字.描述:
     * 给定一个非空的整数数组，在该数组中，有一个元素只出现了一次，其余元素均出现了两次，请找出只出现过一次的元素。请在 Solution 类中的 uniqueNumber 中写下你的代码。
     */
    public int uniqueNumber(int nums[]) {
        boolean flag=true;
        if(nums.length==1){
            return nums[0];
        }
        for (int i=0;i<nums.length;i++){
            int count=0;
            for (int j=0;j<nums.length;j++) {
                if(i==j){      //这时代表第二层循环遍历到索引等于第一层循环的索引，也就是相同数字时候,跳过这个循环,继续下一次循环 索引为0=0,1=1....跳过
                    continue;
                }
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
            if(count==0){
                return nums[i];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int [] arr={2,3,4,5,4,2,3};
        int i = new LintCode25XX().uniqueNumber(arr);
        System.out.println(i);

    }
}
