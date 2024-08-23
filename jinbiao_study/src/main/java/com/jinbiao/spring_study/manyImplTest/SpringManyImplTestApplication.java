package com.jinbiao.spring_study.manyImplTest;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wangjinbiao
 * @date 2023/2/14 14:11
 * @desc 测试Spring接口上有多个实现类，bean的依赖注入问题，总结：Spring属性注入是先byType 再byName 如果byType有多个，byName又不对会报错。 byType只有一个的话，属性名称可以随便命名。
 *
 * Spring注册bean的方式。
 * 1.加@Controller.Service,Component,Repostory等注解
 * 2.构造方法注入，@AutoWired熟悉注入，
 */
public class SpringManyImplTestApplication {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ManyImplConfig.class); //加载spring配置文件
    // 2.测试一个接口对应多个实现类bean，需要指定注入哪个bean，否则报错找到了多个:expected single matching bean but found 2
    TestController testController = (TestController)applicationContext.getBean("testController");
    testController.findAll();


  }
}
