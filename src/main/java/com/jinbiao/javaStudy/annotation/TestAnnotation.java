package com.jinbiao.javaStudy.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author wangjinbiao
 * @date 2023/2/20 16:17
 * @desc
 */
@AnnotationForClass(value = "AnnotationForClassValue")
public class TestAnnotation {

  @AnnotationForField(value = "AnnotationForFieldValue")
  private String name;

  @AnnotationForMethod(value = "AnnotationForMethodValue")
  public String getName() {
    return this.name;
  }

  public void setName(@AnnotationForParam(value = "AnnotationForParamValue") String name) {
    this.name = name;
  }

  @AnnotationForConstructor(value = "AnnotationForConstructorValue")
  public TestAnnotation() {
  }

  // getDeclaredAnnotations 获取所有的注解
  // getDeclaredAnnotation 获取指定的注解
  public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException{

    //获取（类、接口、枚举、注解）上的注解
    Class<TestAnnotation> testClazz  = TestAnnotation.class;
    Annotation annotationForClass = testClazz.getDeclaredAnnotation(AnnotationForClass.class);
    System.out.println("类上的注解的值\t" + ((AnnotationForClass) annotationForClass).value());

    //获取类中构造方法的注解：
    Constructor<TestAnnotation> constructor = testClazz.getConstructor();
    Annotation annotationForConstructor = constructor.getDeclaredAnnotation(AnnotationForConstructor.class);
    System.out.println("类中构造方法上注解的值\t" + ((AnnotationForConstructor) annotationForConstructor).value());

    //获取类中字段的注解：
    Field nameField = testClazz.getDeclaredField("name");
    AnnotationForField annotationForField  = nameField.getDeclaredAnnotation(AnnotationForField.class);
    System.out.println("类中字段上的注解的值\t" + annotationForField.value());

    //获取类中方法的注解：
    Method getNameMethod = testClazz.getDeclaredMethod("getName");
    AnnotationForMethod annotationForMethod = getNameMethod.getDeclaredAnnotation(AnnotationForMethod.class);
    System.out.println("类中方法上的注解的值\t" + annotationForMethod.value());

    // 获取方法上参数的注解
    Method setNameMethod = testClazz.getDeclaredMethod("setName", String.class);
    AnnotationForParam annotationForParam = setNameMethod.getDeclaredAnnotation(AnnotationForParam.class);
    System.out.println("类中方法上参数的注解的值\t" + annotationForParam.value());
  }
}
