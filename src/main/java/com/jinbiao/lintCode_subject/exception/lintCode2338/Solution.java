package com.jinbiao.lintCode_subject.exception.lintCode2338;

/**
 我们需要使用自定义的异常，一般一个类继承自 Exception。
 所以在 MyException 类中，就可以直接调用父类的有参构造，
 根据题目，当输入的数字不在 0 到 100 之间时，将会抛出我们自定义的异常，且这个异常的内容要我们自己输出，
 再看 Main 文件中，当有异常时，打印的信息是 e.getMessage()，而如果我们直接打印输出，就会发现最终获取的 e.getMessage() 为 null，
 所以题目限定了只能调用父类传入字符串的有参构造函数。
 在 Solution 类中，我们可以使用 if-else 语句来判断输入的内容是否合法，注意数字的区间，0 和 100 都是闭合的，
 而如果输出的数字超过了范围，则要抛出我们自定义的异常 使用 throw 关键字即可，里面传入所需要的字符串。
 */
public class Solution {
    public void validate(int num) throws MyException {
            if(num>=0&&num<=100){
                System.out.println(num);
            }else{
                throw new MyException("The number you entered is not legal");
            }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int num=-1;
        try {
            solution.validate(num);
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
