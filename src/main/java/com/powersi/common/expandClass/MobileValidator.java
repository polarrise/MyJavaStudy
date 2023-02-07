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
   * 在验证开始前调用注解里的方法，从而获取到一些注解里的参数
   * @param constraintAnnotation
   */
  @Override
  public void initialize(Mobile constraintAnnotation) {
    this.required = constraintAnnotation.required();
  }

  @Override
  public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
    if(this.required){
      // 验证
      return isMobile(value);
    }
    if (StringUtils.hasText(value)) {
      // 验证
      return isMobile(value);
    }
    return true;
  }
  private boolean isMobile(final CharSequence str) {
    Matcher m = pattern.matcher(str);
    return m.matches();
  }
}
