package com.jinbiao.javaStudy;

import com.jinbiao.javaStudy.SystemOut.MobileUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringReplaceTest {
  private static final Map<Character, Integer > convertUpperCaseMap  = new HashMap<Character, Integer>(){{
    put('0',0);put('零',0); put('〇',0);
    put('1',1);put('一',1);put('壹',1);put('①',1);
    put('2',2);put('二',2);put('贰',2);put('②',2);
    put('3',3);put('三',3);put('叁',3);put('③',3);
    put('4',4);put('四',4);put('肆',4);put('④',4);
    put('5',5);put('五',5);put('伍',5);put('⑤',5);
    put('6',6);put('六',6);put('陆',6);put('⑥',6);
    put('7',7);put('七',7);put('柒',7);put('柒',7);
    put('8',8);put('八',8);put('捌',8);put('⑧',8);
    put('9',9);put('九',9);put('玖',9);put('⑨',9);
  }};

  private static final List<String> keyWords = Arrays.asList("手机号","微信","vx","号码", "手机","qq","邮箱","电话","@","email","phone","线下");

  //大写数字转成数字
  private static String convertLowerCase(String certificate){
    String result="";
    String numStr="";
    char[] chars = certificate.toCharArray();
    for(int i=0;i<chars.length;i++){
      if(convertUpperCaseMap.get(chars[i])!=null){
        result+=convertUpperCaseMap.get(chars[i]);
        numStr+=convertUpperCaseMap.get(chars[i]);
        continue;
      }
      result+=certificate.charAt(i);
    }
    System.out.println(result);
    System.out.println(numStr);
    System.out.println("是否是手机号:"+MobileUtil.checkPhone(numStr));

    return result;
  }



  private static boolean matchCondition(String certificate) {
    String regEx="[^0-9]";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(certificate);
    //words表示数字是哪些
    String words=m.replaceAll("").trim();

    //产品说,凭证里面如果包含8位及8位以上数字,让数字都变成*
    if(words.length()>=8){
      return Boolean.TRUE;
    }
    for (String keyWord : keyWords) {
      if(certificate.contains(keyWord)){
        return Boolean.TRUE;
      }
    }
    return Boolean.FALSE;
  }

  public static void main(String[] args) {
    String str="费用协商，王律师136+6227+5637";
    System.out.println( str.replace("136+6227+5637","******"));

    convertLowerCase("号码 一柒陆73陆3⑤零九④");

    System.out.println(matchCondition(" 12一六六二2零2零23肆①叁4②"));




  }
}
