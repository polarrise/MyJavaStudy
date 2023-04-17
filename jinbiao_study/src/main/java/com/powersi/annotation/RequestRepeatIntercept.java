package com.powersi.annotation;

import java.lang.annotation.*;

/**
 * @Author：Jinbiao
 * @Date：2023/4/17 17:42
 * @Desc：
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestRepeatIntercept {
    String value();
}
