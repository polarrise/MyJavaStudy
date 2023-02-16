package com.jinbiao.HelperClass.SensitiveWord;


import java.util.*;

public class SensitivewordTool {

  //敏感词库
  public static Map sensitiveWordMap;

  //只过滤最小敏感词 //最小匹配规则，如：敏感词库["中国","中国人"]，语句："我是中国人"，匹配结果：我是[中国]人
  public static int minMatchTYpe = 1;
  //过滤所有敏感词 //最大匹配规则，如：敏感词库["中国","中国人"]，语句："我是中国人"，匹配结果：我是[中国人]
  public static int maxMatchTYpe = 2;

  /**
   *@describe: 敏感词库敏感词的数量
   *@author wangdeqiu
   *@date 2018/11/6 下午2:02
   *@param
   *
   */
  public static int getWordSize(){
    if(SensitivewordTool.sensitiveWordMap==null){

      return 0;
    }
    return SensitivewordTool.sensitiveWordMap.size();

  }


  /**
   *@describe: 是否包含敏感词
   *@author wangdeqiu
   *@date 2018/11/6 下午2:04
   *@param
   *
   */

  public static  boolean isContaintSensitiveWord(String txt,int matchType){
    boolean flag = false;
    for(int i=0;i<txt.length();i++){
      int matchFlag = checkSensitiveWord(txt, i, matchType);
      if(matchFlag>0){
        return true;
      }

    }
    return flag;
  }


  /**
   *@describe:   获取敏感词内容
   *@author wangdeqiu
   *@date 2018/11/6 下午2:07
   *@param
   *
   */
  public static Set<String> getSensitiveWord(String txt, int matchType){
    Set<String> sensitiveWordList = new HashSet<String>();
    for (int i = 0; i < txt.length(); i++)
    {
      int length = checkSensitiveWord(txt, i, matchType);
      if (length > 0)
      {
        // 将检测出的敏感词保存到集合中
        sensitiveWordList.add(txt.substring(i, i + length));
        i = i + length - 1;
      }
    }
    return sensitiveWordList;
  }


  /**
   *@describe:  替换敏感词
   *@author wangdeqiu
   *@date 2018/11/6 下午2:37
   *@param
   *
   */
  public static String replaceSensitiveWord(String txt, int matchType, String replaceChar)
  {
    String resultTxt = txt;
    Set<String> set = getSensitiveWord(txt, matchType);
    Iterator<String> iterator = set.iterator();
    String word = null;
    String replaceString = null;
    while (iterator.hasNext())
    {
      word = iterator.next();
      replaceString = getReplaceChars(replaceChar, word.length());
      resultTxt = resultTxt.replaceAll(word, replaceString);
    }

    return resultTxt;
  }

  /**
   *@describe:  替换敏感词内容
   *@author wangdeqiu
   *@date 2018/11/6 下午2:11
   *@param
   *
   */
  private static String getReplaceChars(String replaceChar, int length)
  {
    String resultReplace = replaceChar;
    for (int i = 1; i < length; i++)
    {
      resultReplace += replaceChar;
    }

    return resultReplace;
  }

  /**
   *@describe:  检查敏感词数量
   *@author wangdeqiu
   *@date 2018/11/6 下午2:11
   *@param
   *
   */
  public static int checkSensitiveWord(String txt,int beginIndex,int matchType){
    boolean flag = false;
    // 记录敏感词数量
    int matchFlag = 0;
    char word = 0;
    Map nowMap = SensitivewordTool.sensitiveWordMap;
    for (int i = beginIndex; i < txt.length(); i++){
      word = txt.charAt(i);
      // 判断该字是否存在于敏感词库中
      nowMap = (Map) nowMap.get(word);
      if (nowMap != null){
        matchFlag++;
        // 判断是否是敏感词的结尾字，如果是结尾字则判断是否继续检测
        if ("1".equals(nowMap.get("isEnd")))
        {
          flag = true;
          // 判断过滤类型，如果是小过滤则跳出循环，否则继续循环
          if (SensitivewordTool.minMatchTYpe == matchType)
          {
            break;
          }
        }
      }else
      {
        break;
      }
    }
    if (!flag)
    {
      matchFlag = 0;
    }
    return matchFlag;

  }

  /**
   *
   * Desc:敏感词过滤
   * @author wangdeqiu
   * @date 2018年11月7日 下午2:24:25
   * @param
   * @return
   */
  //public static  Set<String> sensitiveWordFiltering(String text)
  //{
  //  // 初始化敏感词库对象
  //  SensitiveWordInit sensitiveWordInit = new SensitiveWordInit();
  //  // 从数据库中获取敏感词对象集合（调用的方法来自Dao层，此方法是service层的实现类）
  //  List<String> sensitiveWords = SystemUtil.getWordToRedis();
  //  // 构建敏感词库
  //  Map sensitiveWordMap = sensitiveWordInit.initKeyWord(sensitiveWords);
  //  // 传入SensitivewordEngine类中的敏感词库
  //  SensitivewordTool.sensitiveWordMap = sensitiveWordMap;
  //  // 得到敏感词有哪些，传入2表示获取所有敏感词
  //  Set<String> set = SensitivewordTool.getSensitiveWord(text, 2);
  //  return set;
  //}

  /**
   *
   * Desc:判断是否存在敏感词
   * @author wangdeqiu
   * @date 2018年11月7日 下午2:24:38
   * @param
   * @return
   */
  //public static  boolean isExitSensitiveWord(String text)
  //{
  //  // 初始化敏感词库对象
  //  SensitiveWordInit sensitiveWordInit = new SensitiveWordInit();
  //  // 从数据库中获取敏感词对象集合（调用的方法来自Dao层，此方法是service层的实现类）
  //  List<String> sensitiveWords =SystemUtil.getWordToRedis();
  //  // 构建敏感词库
  //  Map sensitiveWordMap = sensitiveWordInit.initKeyWord(sensitiveWords);
  //  // 传入SensitivewordEngine类中的敏感词库
  //  SensitivewordTool.sensitiveWordMap = sensitiveWordMap;
  //  // 得到敏感词有哪些，传入1表示获取所有敏感词
  //  boolean isExit= SensitivewordTool.isContaintSensitiveWord(text, 1);
  //  return isExit;
  //}

}
