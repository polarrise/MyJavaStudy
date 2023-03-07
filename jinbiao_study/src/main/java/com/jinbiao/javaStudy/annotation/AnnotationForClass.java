package com.jinbiao.javaStudy.annotation;

import java.lang.annotation.*;

/**
 * @author wangjinbiao
 * @date 2023/2/20 15:24
 * @desc
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AnnotationForClass {
  String value();
}
