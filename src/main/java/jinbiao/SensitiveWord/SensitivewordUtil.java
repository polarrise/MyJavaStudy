package jinbiao.SensitiveWord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SensitivewordUtil {

  /**
   *
   * Desc:（敏感词校验）container是否包含child
   * @param text ：要判断的文字信息
   * @return childWord：敏感词
   */
  public static String str1ContainsStr2(String text, String childWord) {
    if (text.length() < childWord.length()) {
      return text;
    }
    int maxLength = text.length();
    char first = childWord.charAt(0);
    int i = -1;
    while (++i < maxLength) {
      // 第一个是否能找到
      if (first == text.charAt(i)) {
        // 如果找到匹配之后的元素
        final String substring = text.substring(i);
        if (mathStr(text.substring(i), childWord)) {      //xx.substring(2)表示去掉前两个字符，返回一个新的字符串（只包含去掉前两个字符后剩下的字符串）
           return text.replace(childWord,"*");
        }

      }
    }
    return text;
  }

  private static boolean mathStr(String max, String min) {
    if (max.length() < min.length()) {
      return false;
    }
    int i = -1;
    int minLength = min.length();
    while (++i < minLength) {
      if (min.charAt(i) != max.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    //final String b = str1ContainsStr2("微信号polarrise", "微信");
    //final String c = str1ContainsStr2("微信号polarrise", "微信号");
    //String str="微*信*号*polarrise,1*7*6-7-3-6-3-5-0-9-4";
    String str="手机号17673635094";
    String reg = "^.*\\d{11}.*$";
    System.out.println( str.matches(reg));

    System.out.println(str.contains("手机号"));
    List<String> sensitiveWords = Arrays.asList("微","信","微信", "号", "手机","qq","邮箱",
        "@","一","壹","二","贰","三","叁","四","肆","0","1","2","3","4","5","6","7","8","9");


    if( str.matches(reg)){
      final String s = dealStr(str, sensitiveWords);
      System.out.println(s);
    }
    //List<String> sensitiveWords = Arrays.asList("微","信","微信", "号", "手机","qq","邮箱",
    //    "@","一","壹","二","贰","三","叁","四","肆","0","1","2","3","4","5","6","7","8","9");
    //
    //System.out.println( dealStr(str,sensitiveWords));
  }

  public static String dealStr(String str,List<String>sensitiveWords){
    //String regEx="[^0-9]";
    //Pattern p = Pattern.compile(regEx);
    //Matcher m = p.matcher(str);
    ////words表示数字是哪些
    //String words=m.replaceAll("").trim();
    //
    //if(words.length()<6){
    //    return str;
    //}
    for (String sensitiveWord: sensitiveWords) {
       if(str.contains(sensitiveWord)){
          str = str1ContainsStr2(str, sensitiveWord);
          dealStr(str,sensitiveWords);
       }
    }
    return str;
  }
}
