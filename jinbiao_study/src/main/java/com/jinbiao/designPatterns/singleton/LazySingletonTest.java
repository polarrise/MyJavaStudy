package com.jinbiao.designPatterns.singleton;

import java.io.*;

/**
 * 懒汉模式:延迟加载,只有在真正使用的时候(调用getInstance()方法),才开始实例化
 */
public class LazySingletonTest {
    public static void main(String[] args) throws FileNotFoundException{
//        Demo demo=new Demo();
        //1.堆内存开辟空间

        //2.初始化空间

        //3.赋值demo引用变量
        LazySingleton instance=LazySingleton.getInstance();
        //把实例序列化到test文件
//        try ( ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("test"))){
//            oos.writeObject(instance);
//        }catch (FileNotFoundException e){
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //反序列化:从test文件反序列化得到实例
        try ( ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/test"))){
            LazySingleton lazySingleton = (LazySingleton) ois.readObject();
            System.out.println(instance==lazySingleton);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 构造方法是私有的，则意味着无法从除自身之外的任何类访问它。这是Java提供的访问控制机制。
 * 通过反射也不行!  除非一些特权用户
 */
class LazySingleton implements Serializable {
    private volatile static LazySingleton instance;

    public String name="Jinbiao";

    private LazySingleton(){

    }
    //同一时间只有一个线程访问到这个代码块,加锁的目的是为了防止多次实例化
    public static LazySingleton getInstance(){   //不加锁的话，会存在线程安全问题, 多个线程访问到这段代码的时候就会new多少个对象
        if(null==instance){
            synchronized (LazySingleton.class){   //双重加锁,不这样的话,T1,T2线程进来调用getInstance方法,instance开始都为null,都能访问到这段代码
                if(null==instance){
                    instance=new LazySingleton();
                    //1.在堆内存中开辟内存空间
                    //2.给成员属性初始化
                    //3.赋值给对象的引用instance。   这个引用指向堆内存空间
                }
            }
        }
        return instance;
    }
    public Object readResolve() throws ObjectStreamException {
        return getInstance();
    }
}
