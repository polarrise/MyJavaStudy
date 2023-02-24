package com.powersi.controller.springmvc_study.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
/**
 * 1.定义注解：
 * 注解的定义很像接口的定义。事实上与其他java接口一样，注解也会被编译成class文件。定义注解时需要一些元注解。
 *
 * 2.元注解介绍
 *  @Target详细介绍
 *  ElementType.TYPE：	接口、类、枚举、注解
 *  ElementType.FIELD：	字段、枚举的常量
 *  ElementType.METHOD:	方法
 *  ElementType.PARAMETER:	方法参数
 *  ElementType.CONSTRUCTOR:	构造函数
 *  ElementType.LOCAL_VARIABLE:	局部变量
 *  ElementType.ANNOTATION_TYPE:	注解
 *  ElementType.PACKAGE:	包
 *  @Relation详细介绍
 *  RetentionPolicy.SOURCE：	注解将被编译器丢弃，此处是源码阶段也就是javac编译时
 *  RetentionPolicy.CLASS：	注解在class中可用，但会被vm丢弃
 *  RetentionPolicy.RUNTIME：	vm运行期间也会保留注解，可以使用反射机制读取注解的信息
 *  @Documented介绍：将此注解包含在javadoc中
 *  @Inherited介绍：允许子类继承父类中的注解，千万不要误解是注解的嵌套，是Class类继承的时候，是否拥有父类的注解
 */

public @interface MyArgumentResolver {

    String name() default "";

    String value() default "";
}
