package com.jinbiao.reflect.test;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 通过给测试类--我理解的反射:
 * 从配置文件里面中通过文件输入流得到类的全路径等等信息，有了类的全路径,可以通过反射可以例如类的属性,可以得到构造器可以创建对象(实例)
 * 其实仔细想想Spring源码也是一样的道理。 解析xml文件,把整个xml文件转成一个Document对象，然后开始遍历一个一个的bean标签:
 * 1.生成Document对象
 * 2.生成BeanDefinitionDocumentReader对象遍历Document
 * 3. 从Document的根元素beans标签开始解析
 * 4.遍历每一个bean标签元素
 * 5.解析每一个bean元素标签的信息
 * 6.解析并注册进beanDefinitionMap（就是一个map）
 */
public class Test {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) throws Exception {
        //非反射方式:
        //当需要从第一个业务方法切换到第二个业务方法的时候，使用非反射方式，必须修改代码，并且重新编译运行，才可以达到效果
              new Service1().doService1();
        //    new Service2().doService2();


        //反射方式:
        //从spring.txt中获取类名称和方法名称。使用反射方式，首先准备一个配置文件，就叫做spring.txt吧, 放在该目录下。 里面存放的是类的名称，和要调用的方法名。
        File springConfigFile = new File("D:/WorkSpace/springcloud/springAop-study/src/main/java/cn/how2j/springcloud/reflect/test/spring.txt");
        Properties properties= new Properties();
        properties.load(new FileInputStream(springConfigFile));
        String className = (String) properties.get("class");      //在测试类Test中，首先取出类名称和方法名，然后通过反射去调用这个方法。
        String methodName = (String) properties.get("method");   //在测试类Test中，首先取出类名称和方法名，然后通过反射去调用这个方法。

        //根据类名称获取类对象
        Class clazz = Class.forName(className);
        //根据方法名称，获取方法对象
        Method m = clazz.getMethod(methodName);
        //获取构造器
        Constructor c = clazz.getConstructor();
        //根据构造器，实例化出对象
        Object service = c.newInstance();
        //调用对象的指定方法
        m.invoke(service);

    }
}
