package com.jinbiao.javaStudy.annotation;
import java.lang.annotation.*;

/**
 * @author wangjinbiao
 * @date 2023/2/20 16:12
 * @desc
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AnnotationForField {
  String value();
}
