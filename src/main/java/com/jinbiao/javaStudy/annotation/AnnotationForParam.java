package com.jinbiao.javaStudy.annotation;
import java.lang.annotation.*;

/**
 * @author wangjinbiao
 * @date 2023/2/20 16:13
 * @desc
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AnnotationForParam {
  String value();
}
