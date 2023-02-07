package com.powersi.annotation;

import com.powersi.common.expandClass.MobileValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author wangjinbiao
 * @date 2023/2/7 11:42
 * @desc
 *
 * 自定义校验规则需要做两件事情：
 * 自定义注解类，定义错误信息和一些其他需要的内容
 * 注解校验器，定义判定规则
 *
 * 可以把注解理解为代码里的特殊标记，这些标记可以在编译，类加载，运行时被读取，并执行相应的处理。
 * 通过注解开发人员可以在不改变原有代码和逻辑的情况下在源代码中嵌入补充信息。
 */
//自定义注解类
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = MobileValidator.class)
public @interface Mobile {

  boolean required() default true;

  /**
   * 校验不通过返回的提示信息
   */
  String message() default "不是一个手机号码格式";

  /**
   * Constraint要求的属性，用于分组校验和扩展，留空就好
   */
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
