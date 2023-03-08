package com.jinbiao.cloud.im.utils;

/**
 * @author: lcx
 * @date: 2019/11/16 20:41
 * @description: 正则表达式校验根据类
 */
public class RegexpUtil {

  public static final String EMPLOYEE_ID_REGEX = "^[a-z0-9A-Z]{1,50}$"; // employee_id

  public static final String REGEXP_MOBILE = "^1\\d{10}$"; // 手机号

  public static final String REGEXP_PHONE = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\\\d{8}$";

  public static final String REGEXP_MOBILE_NEW =
      "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";

  public static final String EMAIL_REGEX =
      "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"; // 邮箱

  public static final String REGEXP_TEL = "^0\\d{2,3}-\\d{7,8}$"; // 电话号码

  public static final String USER_TYPE_REGEX = "^[0-9]{2}$"; // 用户类型

  public static final String PASSWORD_REGEX = "^[a-z0-9A-Z]{6,16}$"; // 密码 字母或数字

  public static final String PASSWORD_REGEX_PLUS =
      "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$"; // 密码 包含字母和数字

  public static final String ENTERPRISE_ID_REGEX = "^[0-9]{10}$"; // 企业号

  public static final String USER_BANKCARD_REGEX = "^[0-9]{12,19}$";

  /** 企业名称 */
  public static final String REGEXP_ENT_NAME = "^[\u4e00-\u9fa5a-zA-Z\\（\\）]{2,32}$";
  /** 真实姓名 */
  public static final String REGEXP_NAME = "^[\u4e00-\u9fa5a-zA-Z]{2,18}$";
  /** 中文姓名 */
  public static final String REGEXP_NAME_CN = "^[\u4e00-\u9fa5\\s·]+$"; // 汉字  · 空格 是合法的
  /** 英文姓名 */
  public static final String REGEXP_NAME_EN = "^([a-zA-Z\\s]+)$"; // 英文 空格

  /** 身份证 */
  public static final String REGEXP_ID =
      "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])"
          + "\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))"
          + "(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
  /** 座机号 */
  public static final String REGEXP_TELEPHONE = "^0\\d{2,3}-?\\d{7,8}$";
  /** 银行卡号 */
  public static final String REGEXP_BANKCARD_NO = "^([1-9]\\d{14,18})$";
  /** 对公银行账户 */
  public static final String REGEXP_BANKCARD_NO_2 = "^([\\d+\\-\\d+]{1,30}|\\d{1,30})$";
  /** 邮箱 */
  public static final String REGEXP_EMAIL =
      "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
  /** 员工编号 */
  public static final String REGEXP_EMPLOYEE_ID = "^[a-z0-9A-Z]{1,50}$";
  /** 日期 */
  public static final String REGEXP_DATE =
      "^(^(\\d{4}|\\d{2})(\\-|\\/|\\.)\\d{1,2}\\3\\d{1,2}$)|(^\\d{4}年\\d{1,2}月\\d{1,2}日$)$";
  /** 金额 */
  public static final String REGEXP_MONEY =
      "^(?!0+(?:\\.0+)?$)(?:[1-9]\\d{0,6}|0)(?:\\.\\d{1,2})?$";
  /** 15位营业执照注册号 */
  public static final String COM_ID_15 = "^[a-z0-9A-Z]{15}$";
  /** 18位多证合一企业统一社会代码 */
  public static final String COM_ID_18 = "^[a-z0-9A-Z]{18}$";

  /** 6位密码重复校验 */
  public static final String PASSWORD_REPEAT = "^(?=.*\\d+)(?!.*?([\\d])\\1{5})[\\d]{6}$";

  /** 特殊符号校验 */
  public static final String SPECIAL_SYMBOL =
      "`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？";

  public static final String LETTER = "a-zA-Z";

  /** 六位以及六位以上的字符/字母/数字组合(两种即两种以上即可)*/
  public static final String SPECIAL_COMBINATION = String.format("(?=.*[%s])(?=.*\\d)[%s\\d]{6,}|(?=.*[%s])(?=.*[%s])[%s%s]{6,}|(?=.*\\d)(?=.*[%s])[\\d%s]{6,}|(?=.*[%s])(?=.*\\d)(?=.*[%s])[%s\\d%s]{6,}",
      LETTER,LETTER,
      LETTER,SPECIAL_SYMBOL,LETTER,SPECIAL_SYMBOL,
      SPECIAL_SYMBOL,SPECIAL_SYMBOL,
      LETTER,SPECIAL_SYMBOL,LETTER,SPECIAL_SYMBOL
  );
}
