package com.powersi.annotation;

import java.lang.annotation.*;

/**
 * @author WangJinbiao
 * @date 2023/2/19 23:19
 * @desc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)  //里面可以写ElementType.TYPE代表该注解写在类上面，ElementType.METHOD表示写在方法上
@Documented
public @interface DealResult {
    String name();
    String value();
}
