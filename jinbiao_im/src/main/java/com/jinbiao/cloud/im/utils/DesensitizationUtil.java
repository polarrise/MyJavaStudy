package com.jinbiao.cloud.im.utils;

import lombok.val;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: lcx
 * @date 22/9/22 15:15
 * @description 字符脱敏工具类
 */
public class DesensitizationUtil {

  /**
   * 六位以及六位以上的字符/字母/数字组合(两种即两种以上即可)
   *
   * @param text
   */
  public static String exceedSixCharacter(String text) {
    return text.replaceAll(RegexpUtil.SPECIAL_COMBINATION, "******");
  }

  /**
   * 手机号脱敏
   * @param phone
   */
  public static String phoneDesensitization(String phone) {
      return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
  }

  public static void main(String[] args) {
    String test = "H17621621326HAH18855991502";
    val result = checkText(test);
    if (result) {
      System.out.println(phoneDesensitization(test));
    }
  }

  // 判断是否包含手机号
  public static boolean checkText(String text) {
    text = Pattern.compile("[^0-9]").matcher(text.trim()).replaceAll("");
    if (text.length() < 11) {
      return false;
    }
    char[] chars = text.toCharArray();
    ArrayList<String> phoneList = new ArrayList<>();//所有11位数字的集合
    for (int i = 0; i < chars.length; i++) {
      StringBuilder stringBuilder = new StringBuilder();
      for (int j = 0; j < 11; j++) {
        if (i + j < chars.length) {
          stringBuilder.append(chars[i + j]);
        }
      }
      if (stringBuilder.length() == 11) {
        phoneList.add(stringBuilder.toString());
      }
    }
    boolean result = false;
    for (String phone : phoneList) {
      val compile = Pattern.compile(RegexpUtil.REGEXP_MOBILE);
      Matcher mat = compile.matcher(phone);
      if (mat.matches()) {
        result = true;
      }
    }
    return result;
  }
}
