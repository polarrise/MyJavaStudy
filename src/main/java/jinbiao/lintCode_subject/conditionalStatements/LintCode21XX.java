package jinbiao.lintCode_subject.conditionalStatements;

/**
 * @author jinbiao
 * @date 2022/1/17
 * @apiNote
 */
public class LintCode21XX {
    /**
     * 2183 · 根据学生的成绩等级，判断学生的成绩范围
     描述:本题要根据学生的成绩等级，判断学生的成绩范围。我们规定 A 等级 代表 90~100 分，B 等级代表 80~89 分，C 等级代表 70~79 分，D 等级代表 60~69 分，
     E 等级代表 0~59 分，如果是 A~E 以外的字母，用 Unknown level 表示。
     */
    public String getRange(String level) {
        String str = "";
        switch (level) {
            case "A":
                str = "90~100";
                break;
            case "B":
                str = "80~89";
                break;
            case "C":
                str = "70~79";
                break;
            case "D":
                str = "60~69";
                break;
            case "E":
                str = "0~59";
                break;
            default:
                str = "Unknown level";
        }
        return str;
    }

    /**
     * LintCode.2165 · 计算 1+2+3+...+n 的值
     * 描述
     * 本题我们要编写代码计算 1+2+3+...+n 的值。实现该题的方法有很多种，我们推荐使用循环语句来计算。
     * 本题提供了一个 Solution 类， Solution 类中定义了一个 add 方法，该方法的返回值是 int 类型，该方法传递了一个 int 类型的参数 number，
     * 该方法中定义了两个 int 类型的变量 i 和 sum，sum 的初始值赋值为 0。最后方法 return sum。
     */
    public int add(int number) {
        int i=0;
        int sum = 0;
        // write your code here
        if(number<=0){
            return -1;
        }
        for(i=1;i<=number;i++){
            sum+=i;
        }
        return sum;
    }
    public static void main(String[] args) {
        System.out.println( new LintCode21XX().getRange("A"));
        System.out.println("--------------");
        System.out.println(new LintCode21XX().add(5));
    }
}
