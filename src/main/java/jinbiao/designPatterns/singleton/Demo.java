package jinbiao.designPatterns.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 怎么理解构造函数私有化,不可以再通过直接new获得对象了,无法获得对象也就无法通过对象获得这个类的任何信息了(共有属性,get/set 私有属性)
 * 只能通过类名.属性。 获取到类的信息了
 */
public class Demo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
          //如果把LazySingleton类的构造器换成公有的, 因为加了static的缘故,尽管有两个对象demo1,demo2,但是他们调用得到的实例instance一样的!!!(第一次调用的时候new的)
//        LazySingleton demo1=new LazySingleton();
//        LazySingleton instance1 = demo1.getInstance();
//        LazySingleton demo2=new LazySingleton();
//        LazySingleton instance2 = demo2.getInstance();
//        System.out.println(instance1==instance2);    //true
        //怎么理解:
        //静态属性,静态方法,静态代码块 只会在类首次加载的时候初始化一次。 是属于类所有
        //我们发现，给instance属性加了static关键字之后，LazySingleton对象就不再拥有instance属性了，instance属性会统一交给LazySingleton类去管理，
        // 即多个LazySingleton对象只会对应一个instance属性，一个对象如果对instance属性做了改变，其他的对象都会受到影响。


        //测试反射能不能获得构造器,创建对象,获取属性    ->答案是不能的,构造器都拿不到
            Class pClass1 = Class.forName("cn.how2j.springcloud.designPatterns.singleton.LazySingleton");
            Constructor[] cts =pClass1.getConstructors();
            System.out.println(cts.length);
      //  一般的外部调用，编译器会校验，直接提示编译错误。而正常的反射也是找不到私有构造函数的，所以上面的输出为0。
      //  但是一些特权用户可以通过反射来访问私有构造函数，并且实例化：
//            LazySingleton instance = (LazySingleton)cts[0].newInstance();
//            System.out.println(instance.name);

        }
}
