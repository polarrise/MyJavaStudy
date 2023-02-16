package com.jinbiao.javaStudy.lintCode_subject.exception.LintCode2690;

/**
 当 a = 5, b = 1 时，应该返回结果
 Calculated results are normal.
 样例二:
 当 a= 10, b = 0 时，应该返回结果
 The divisor cannot be 0.
 */
public class LintCode2690 {
    public String division(int a, int b) throws Exception {
        try {
            new Calculators().calc(a,b);
            return "Calculated results are normal";
        }catch (Exception e){
            return "The divisor cannot be 0";
        }
    }

    public static void main(String[] args) throws Exception {
        try {

            LintCode2690 lintCode2690 = new LintCode2690();
            String rs = lintCode2690.division(5,0);
            System.out.println(rs);
            // check
            Calculators calculators = new Calculators();
            boolean check = calculators.getCheck();
            if (check) {
                throw new Exception("division方法被访问过了");
            }
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
