package jinbiao.javaStudy.SystemOut;


import java.math.BigDecimal;

import static cn.hutool.core.util.NumberUtil.div;

/**
 * @author jinbiao
 * @date 2022/1/6
 * @apiNote
 */
public class Test {
    public static double round(double value, int scale) {
        double num = 0;
        try {
            num = div(value, 1, scale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }
    public double add2(String a, String b) {
        double d1=Double.parseDouble(a);
        double d2=Double.parseDouble(b);
        BigDecimal firBD = BigDecimal.valueOf(d1);
        BigDecimal sedBD = BigDecimal.valueOf(d2);
        System.out.println( round(firBD.add(sedBD).doubleValue(),2));
        return round(firBD.add(sedBD).doubleValue(),2);
    }

    public double add1(String a, String b) {
        double d1=Double.parseDouble(a);
        double d2=Double.parseDouble(b);
        double reslut=d1+d2;
        BigDecimal bg = new BigDecimal(reslut);
        double rateValue = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();    //保留2位小数
        System.out.println(rateValue);
        return rateValue;
    }
    public double add(String a, String b) {
        double d1=Double.parseDouble(a);
        double d2=Double.parseDouble(b);
        String str = String.format("%.2f",(d1+d2));
        double reslut=Double.parseDouble(str);
        System.out.println(reslut);
        return reslut;
    }
    public boolean equivalence(Integer a, Integer b) {
        if(a==b){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Integer a=100,b=100;
        Integer c=500,d=500;
        System.out.println(new Test().equivalence(a,b));
        System.out.println(new Test().equivalence(c,d));

        Double x=3.01;
        Double y=3.01;
        System.out.println(x.equals(y));


        new Test().add("7.136","3.009");
        new Test().add1("7.136","3.009");
        new Test().add2("7.136","3.009");
}
}
