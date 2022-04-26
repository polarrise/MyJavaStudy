package jinbiao.lambdas.MethodReference;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

/**
 * 方法引用：
 * 若lambda体中的内容，有方法已经实现了，则可以使用方法引用。方法引用是对lambda的简化
 方法引用
 双冒号::为引用运算符，而它所在的表达式被称为方法引用。
 如果Lambda要表达的函数方案已经存在于某个方法的实现中，那么则可以通过双冒号来引用该方法作为Lambda的替代者。
 三种格式：
 对象::实例方法名
 类::静态方法名
 类::实例方法名
 分析Lambda表达式写法： s -> System.out.println(s);
 方法引用写法： System.out::println
 */
public class DemoPrint {
    /**
     * 通过对象名引用成员方法
     */
    @Test
    public void test(){
        Person person = new Person();
        Supplier<String> supplier =() -> person.getName();   // Supplier<T>接口被称之为生产型接口,指定接口的泛型是什么类型,那么接口中的get方法就会生产什么类型的数据
        System.out.println(supplier.get());

        Supplier<Integer> supplier1 = person::getAge;   //Supplier<T>接口被称之为生产型接口,指定接口的泛型是什么类型,那么接口中的get方法就会生产什么类型的数据
        System.out.println(supplier1.get());

        //完成写法:
        Supplier<Integer> supplier2=new Supplier<Integer>(){
            @Override
            public Integer get() {
                return person.getAge();
            }
        };
        System.out.println(supplier2.get());
    }

    private static void printString(MyPrintable data){
        data.print("Hello,World");
    }

    public static void main(String[] args) {
        //正常写法:
        printString(new MyPrintable() {
            @Override
            public void print(String str) {
                System.out.println(str);
            }
        });
        //lambda表达式写法:
        printString(s-> System.out.println(s));

        //改进:
        //方法引用
        //双冒号::为引用运算符，而它所在的表达式被称为方法引用。
        //如果Lambda要表达的函数方案已经存在于某个方法的实现中，那么则可以通过双冒号来引用该方法作为Lambda的替代者。
        printString(System.out::print);
    }
}

class Person{
    private String name="Jinbiao";
    private Integer age=23;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
