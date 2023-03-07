package com.jinbiao.Enum;

/**
 理解枚举类型
 枚举类型是Java 5中新增特性的一部分，它是一种特殊的数据类型，之所以特殊是因为它既是一种类(class)类型却又比类类型多了些特殊的约束，但是这些约束的存在也造就了枚举类型的简洁性、安全性以及便捷性。
 */
public enum Day {
    MONDAY("星期一",1),
    TUESDAY("星期二",2),
    WEDNESDAY("星期三",3),
    THURSDAY("星期四",4),
    FRIDAY("星期五",5),
    SATURDAY("星期六",6),
    SUNDAY("星期日",7);//记住要用分号结束

    private String desc;//文字描述
    private Integer code; //对应的代码

    /**
     * 私有构造,防止被外部调用
     * @param desc
     */
    private Day(String desc,Integer code){
        this.desc=desc;
        this.code=code;
    }
    /**
     * 定义方法,返回描述,跟常规类的定义没区别
     * @return
     */
    public String getDesc(){
        return desc;
    }

    /**
     * 定义方法,返回代码,跟常规类的定义没区别
     * @return
     */
    public int getCode(){
        return code;
    }
    public static void main(String[] args){
        for (Day day:Day.values()) {
            System.out.println("name:"+day.name()+ ",desc:"+day.getDesc()+",code:"+day.code);  //name()方法与toString()几乎是等同的，都是输出变量的字符串形式。
        }

    }

}
