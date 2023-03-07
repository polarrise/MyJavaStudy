package com.jinbiao.javaStudy.annotation;
import java.lang.annotation.*;
/**
 * @author wangjinbiao
 * @date 2023/2/20 16:02
 * @desc
 */
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AnnotationForConstructor {
  String value();
}
