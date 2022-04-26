package jinbiao.lintCode_subject.exception.LintCode2845;

/**
 描述
 在 Solution 类中有一个 checkAge(int num) 方法，这个方法用来判定 num 是否是 非百岁老人 判断标准及要求如下：
 非百岁老人 是指 0 到 99 岁之间的人；
 如果不是 非百岁老人 ，需要抛出一个 NoAgesException 异常，并填写异常信息 Age Error!
 您需要实现 checkAge(int num) 方法，并且自定义异常处理类 NoAgesException。
 */
public class LintCode2845 {
    public boolean checkAge(int num) throws Exception {
        if (0 <= num && num <= 99) {              //非百岁老人
            return true;
        }
        throw new NoAgesException("Age Error!");  //不是非百岁老人 ,需要抛出一个 NoAgesException 异常，并填写异常信息 Age Error!
    }

    public static void main(String[] args) throws Exception {

        try {
            boolean rs = new LintCode2845().checkAge(110);
            if (rs) {
                System.out.println("Age value legal.");
            }
        } catch (Exception e) {         //110不在0-99之间，需要抛出一个NoAgesException异常,抛出异常之后被catch捕捉
            String msg = e.getMessage();
            if (e instanceof NoAgesException && "Age Error!".equals(msg)) {
                System.out.println("Age values are illegal.");
            }
        }
    }
}
