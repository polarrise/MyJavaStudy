package jinbiao.lambdas.MethodReference.InstanceConstructor;

import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Supplier;

/**类的构造器引用:
 * 由于构造器的名称与类名完全一样，并不固定。所以构造器引用使用 类名称::new 的格式表示
 */
public class Demo {
    @Test
    public void test3(){   //构造一个person对象
        Supplier<Person> personSupplier = ()->new Person();
        //构造器引用 此处引用的是无参构造器 ，因为Supplier中的get方法没有参数
        Supplier<Person> personSupplier1 = Person::new;
        Person person = personSupplier1.get();
        System.out.println("姓名: "+person.getName()+";年龄: "+person.getAge());
    }
    @Test
    public void test4(){    //根据年龄20构造一个person对象
        Function<Integer,Person> personFunction = (x)->new Person(x);
        //构造器引用 此处引用的是整型的一个参数的构造器 ，因为Function中的apply方法只有一个参数
        Function<Integer,Person> personFunction1 = Person::new;
        Person person = personFunction1.apply(20);
        System.out.println("年龄: "+person.getAge());
    }
    //传递姓名和PersonBuilder接口，通过姓名创建Person对象
    public static void printName(String name,PersonBuilder personBuilder){
        Person person = personBuilder.builderPerson(name);
        System.out.println(person.getName());
    }

    public static void main(String[] args) {
        printName("WangJinbiao",str->new Person(str));

        /**
         * 使用方法引用：
         * 1.构造方法new Person(String name)已知
         * 2.创建对象已知
         * 可以使用Person引用new创建对象
         */
        printName("WangJinbiao",Person::new);
        /**使用方法引用：类的构造器引用
         * 内部就是 1.lambda表达式 作为一个PersonBuilder对象(实际是Demo类型对象),传入到printName方法中
         *         2.lambda表达式调用内部的方法体实现
         *         3.方法体(也就是接口里抽象方法的实现)。通过new Person(name)得到了一个person对象
         */
    }
}
