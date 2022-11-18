package com.jinbiao.Enum.Season;

/**
 * @author com.jinbiao
 * @date 2022/1/11
 * @apiNote
 */
public class SeasonTest implements info{
    //1.声明Season对象的属性:private final修饰
    private final String seasonName;
    private final String seasonDesc;

    //2.私有化类的构造器，并给对象属性赋值
    private SeasonTest(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }
    //3.提供当前枚举类的多个对象：public static final 的
    public static final SeasonTest SPRING=new SeasonTest("春天","春暖花开");
    public static final SeasonTest SUMMER=new SeasonTest("夏天","夏季炎炎");
    public static final SeasonTest AUTUMN=new SeasonTest("秋天","秋高气爽");
    public static final SeasonTest WINTER=new SeasonTest("冬天","冰天雪地");
    public static final SeasonTest WINTER2=new SeasonTest("冬天2","冰天雪地2"){  //模拟使用enum关键字定义的枚举类实现接口的情况,一个意思
        public boolean show() {
            System.out.println("冬天2真冷啊--");
            return false;
        }
    };

    //4.其他诉求1：获取枚举类对象的属性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
    @Override
    public boolean show() {
        return false;
    }
    //5.其他诉求2：提供toString()方法
    public String toString() {
        return "SeasonTest{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(SeasonTest.WINTER2);
        SeasonTest.WINTER2.show();
    }
}
