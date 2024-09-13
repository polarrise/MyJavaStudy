package com.powersi.common.expandClass;

import com.powersi.annotation.Mobile;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangjinbiao
 * @date 2023/2/7 11:46
 * @desc
 * 注解校验器
 */
public class MobileValidator implements ConstraintValidator<Mobile, CharSequence> {

  private boolean required = false;

  private final Pattern pattern = Pattern.compile("^1[34578][0-9]{9}$"); // 验证手机号

  /**
   * 在验证开始前调用注解里的方法，从而获取到一些注解里的参数,required 默认为true也就是开启手机号校验
   * @param constraintAnnotation
   */
  @Override
  public void initialize(Mobile constraintAnnotation) {
    this.required = constraintAnnotation.required();
  }

  /**
   * 真正执行的校验方法 true? 开启校验 ： 直接不校验=>通过
   * @param value object to validate
   * @param context context in which the constraint is evaluated
   * @return
   */
  @Override
  public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
      return this.required ? isMobile(value) : Boolean.TRUE;
  }

  /**
   * 正则校验手机号
   * @param str
   * @return
   */
  private boolean isMobile(final CharSequence str) {
    return pattern.matcher(str).matches();
  }
}
