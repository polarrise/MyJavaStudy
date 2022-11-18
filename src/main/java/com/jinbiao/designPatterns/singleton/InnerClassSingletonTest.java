package com.jinbiao.designPatterns.singleton;

public class InnerClassSingletonTest {
    public static void main(String[] args) {
//        System.out.println(InnerClassSingleton.name);   // 1会输出,2不会输出。内部类SingletonHolder并不会初始化

        //只有真正去调用getInstance才会初始化内部类SingletonHolder,本质也是一种延迟加载，不然SingletonHolder永远也不会加载
        System.out.println(InnerClassSingleton.getInstance());
    }
}

class InnerClassSingleton{
    public static String name="Jinbiao";
    static {
        System.out.println("InnerClassSingleton init ");   //1
    }
    private static class SingletonHolder{
        static {
            System.out.println("SingletonHolder init ");  //2
        }
        private static InnerClassSingleton instance=new InnerClassSingleton();
    }
    private InnerClassSingleton(){

    }
    public static InnerClassSingleton getInstance(){
        return SingletonHolder.instance;
    }
}
