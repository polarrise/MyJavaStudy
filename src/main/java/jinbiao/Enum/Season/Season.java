package jinbiao.Enum.Season;

/**
 * @author jinbiao
 * @date 2022/1/11
 * @apiNote
 */
//使用enum自定义枚举类
enum Season implements info {
    //1. 提供当前枚举类的对象,多个对象之间用","隔开 ,末尾用";"结束
    SPRING("春天", "春暖花开") {
        public boolean show() {
            System.out.println("春天在哪里");
            return false;
        }
    },
    SUMMER("夏天", "夏日炎炎") {
        public boolean show() {
            System.out.println("宁夏");
            return false;
        }
    },
    AUTUMN("秋天", "秋高气爽") {
        public boolean show() {
            System.out.println("秋天不回来");
            return false;
        }
    },
    WINTER("冬天", "冰天雪地") {
        public boolean show() {
            System.out.println("大约在冬季");
            return false;
        }
    };
    //2.声明Season对象的属性:private final修饰
    private final String name;
    private final String value;
    Season(String name, String value) {
        this.name=name;
        this.value=value;
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(Season.WINTER);
        System.out.println("name:"+Season.WINTER.name);
        System.out.println("value:"+Season.WINTER.value);
        Season.WINTER.show();
        System.out.println(Season.class.getSuperclass());  //查Season继承的父类：class java.lang.Enum

        Season[] values = Season.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }
        System.out.println("*******************");
        //3.valuesOf():返回枚举类中对象名是objName的对象
        //如果没有objName的枚举类对象，则抛异常IllegalArgumentException
        //Season season1 = Season.valueOf("WINTER1");
        Season season1 = Season.valueOf("WINTER");
        System.out.println(season1);
        System.out.println(Season.valueOf("WINTER")==Season.WINTER);
    }
}

