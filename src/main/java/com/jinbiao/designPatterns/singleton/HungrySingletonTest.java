package com.jinbiao.designPatterns.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class HungrySingletonTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        Class<HungrySingleton> hungrySingletonClass = HungrySingleton.class;       不会初始化HungrySingleton类

//          System.out.println(HungrySingleton.name);        //会初始化HungrySingleton类
        //通过类加载来获取实例
        HungrySingleton hungrySingleton = HungrySingleton.getInstance();

        //通过反射获取实例
        Constructor<HungrySingleton> declaredConstructor = HungrySingleton.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        HungrySingleton instance = declaredConstructor.newInstance();
        System.out.println(hungrySingleton==instance);
    }
}

/**
 * 构造方法是私有的，则意味着无法从除自身之外的任何类访问它。这是Java提供的访问控制机制。
 */
class HungrySingleton {  //饿汉模式
    static {
        System.out.println("HungrySingleton init ");
    }
    public static  String name="Jinbiao";

    private static HungrySingleton instance=new HungrySingleton();

    private HungrySingleton(){
        if(instance!=null){        //防反射侵入
            throw new RuntimeException("单例不允许多个实例。。");
        }
    }
    public static HungrySingleton getInstance(){
        return instance;
    }
}

