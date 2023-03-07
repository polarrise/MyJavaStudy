package com.jinbiao.javaStudy.customizeClassLoader;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @Author：Jinbiao
 * @Date：2023/3/2 0:57
 * @Desc：打破双亲委派机制测试,不让父加载器加载类，自己来加载类.沙箱安全机制示例，尝试打破双亲委派机制，用自定义类加载器加载我们自己实现的 java.lang.String.class
 *
 * 沙箱安全机制:
 * java.lang.SecurityException: Prohibited package name: java.lang
 */
public class BreakParentDelegation extends ClassLoader{

    private String classPath;

    public BreakParentDelegation(String classPath){
        this.classPath = classPath;
    }

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
                c = findClass(name);

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
        BreakParentDelegation myClassLoader = new BreakParentDelegation("E:/test");

        //E盘创建 test/com/tuling/User,将User.class丢入该目录： ps注意：java类是在包下面的，编译后会根据 包路径+类名.class 去E盘找
        Class clazz = myClassLoader.loadClass("java.lang.String");

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
