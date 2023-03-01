package com.jinbiao.javaStudy.customizeClassLoader;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @Author：Jinbiao
 * @Date：2023/3/1 23:54
 * @Desc：理解双亲委派机制，以及自定义类加载器:MyClassLoader
 *
 * 双亲委派机制：加载某个类时会先委托父加载器寻找目标类，找不到再委托上层父加载器加载，如果所有父加载器在自己的加载类路径下都找不到目标类，则在自己的类加载路径中查找并载入目标类。
 * 双亲委派机制说简单点就是，先找父亲(ExtClassLoader-BootStrapClassLoader)加载，都加载不到再由自己(AppClassLoader)加载
 *
 * 为什么要设计双亲委派机制?
 * 答:1.沙箱安全机制：防止自己写的类java.lang.String.class不会被重复加载，防止java类库被随意篡改
 *   2.避免累的重复加载:当父类加载器已经加载了该类时,自己就不会再去加载了，保证类加载的唯一性
 */
public class MyClassLoader extends ClassLoader{

    private String classPath;

    public MyClassLoader(String classPath){
        this.classPath = classPath;
    }

    /**
     * 通过文件输入流读取classPath路径下的类的class信息到字节数组里面返回。
     * @param name
     * @return
     * @throws Exception
     */
    private byte [] loadByte(String name) throws Exception{
        //替换类路径格式：
        name = name.replaceAll("\\.","/");
        //把该路径下的类.class文件读到文件流里面:
        FileInputStream fis = new FileInputStream(classPath + "/" +name+ ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        //读到这个字节数组里面：
        fis.read(data);
        fis.close();
        return data;
    }


    /**
     * 通过class信息的字节数组，加载这个类
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try{
            byte[] data = loadByte(name);
            //将一个字节数组转为Class对象，这个字节数组是class读取后最终的字节数组，也就是class对象的字节数组转为class对象
            return defineClass(name,data,0,data.length);
        }catch (Exception e){
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }

    /**
     * 重写类加载方法，实现自己的加载逻辑，不委派给双亲加载
     * @param name
     * @param resolve
     * @return
     * @throws ClassNotFoundException
     */

    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);

            if (c == null) {
                // If still not found, then invoke findClass in order
                // to find the class.
                long t1 = System.nanoTime();

                //让User类用自定义类加载器加载,然后沙箱安全对象java.lang.Object对象用父类加载器加载 即可完成
                if(! name.startsWith("com.jinbiao.javaStudy.customizeClassLoader")){
                    c = this.getParent().loadClass(name);
                }else{
                    c = findClass(name);
                }
                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }


    public static void main(String[] args) throws Exception {
        //初始化自定义类加载器会先初始化父类ClassLoader,会把自定义类加载器的父加载器设置为AppClassLoader
        MyClassLoader myClassLoader = new MyClassLoader("E:/test");

        //E盘创建 test/com/tuling/User,将User.class丢入该目录： ps注意：java类是在包下面的，编译后会根据 包路径+类名.class 去E盘找
        Class clazz = myClassLoader.loadClass("com.jinbiao.javaStudy.customizeClassLoader.User");

        //创建对象实例：
        Object obj = clazz.newInstance();

        //得到这个Class类对象的test方法:
        Method test = clazz.getDeclaredMethod("test", null);

        //调用对象的test方法：
        test.invoke(obj,null);

        //打印当前类加载器的名称：
        // ps：(当前项目所在target下有User.class时,User.class由AppClassLoader加载target,当前AppClassLoader下没有User.class时,由MyClassLoader加载器加载)
        System.out.println(clazz.getClassLoader().getClass().getName());

    }
}
