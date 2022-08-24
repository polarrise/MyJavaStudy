package jinbiao.SensitiveWord;

import java.util.*;

/**
 * @author wangdeqiu
 * @date 2018/11/6
 * @Dsecription: 敏感词库初始化
 */
@SuppressWarnings("rawtypes")
public class SensitiveWordInit {
  //敏感词库
  public static Map sensitiveWordMap;

  /**
   *@describe:  初始化敏感词
   *@author wangdeqiu
   *@date 2018/11/6 下午1:46
   *@param  sensitiveWords：redis/数据库中获取的敏感词
   *
   */
  public static Map initKeyWord(List<String> sensitiveWords){
    try{
      // 从敏感词集合对象中取出敏感词并封装到Set集合中
      Set<String> keyWordSet = new HashSet<String>();
      for(String s:sensitiveWords){
        keyWordSet.add(s.trim());
      }
      //将词库放到hashmap中
      addSensitiveWordToHashMap(keyWordSet);
    }catch(Exception e){
      e.printStackTrace();
    }

    return sensitiveWordMap;
  }


  /**
   *@describe:  封装敏感词库
   *@author wangdeqiu
   *@date 2018/11/6 下午1:47
   *@param  keyWordSet ：初始后的敏感词
   *
   */
  @SuppressWarnings("unchecked")
  public static void addSensitiveWordToHashMap(Set<String> keyWordSet){
    // 初始化HashMap对象并控制容器的大小
    sensitiveWordMap = new HashMap(keyWordSet.size());
    // 敏感词
    String key = null;
    // 用来按照相应的格式保存敏感词库数据
    Map nowMap = null;
    // 用来辅助构建敏感词库
    Map<String,String> newWorMap = null;
    // 使用一个迭代器来循环敏感词集合
    Iterator<String> iterator = keyWordSet.iterator();
    while (iterator.hasNext()){
      key = iterator.next();
      // 等于敏感词库，HashMap对象在内存中占用的是同一个地址，所以此nowMap对象的变化，sensitiveWordMap对象也会跟着改变
      nowMap = sensitiveWordMap;
      for(int i=0;i<key.length();i++){
        // 截取敏感词当中的字，在敏感词库中字为HashMap对象的Key键值
        char keyChar = key.charAt(i);
        // 判断这个字是否存在于敏感词库中
        Object wordMap = nowMap.get(keyChar);
        if (wordMap != null)
        {
          nowMap = (Map) wordMap;
        }else{
          newWorMap = new HashMap<String, String>();
          newWorMap.put("isEnd", "0");
          nowMap.put(keyChar, newWorMap);
          nowMap = newWorMap;
        }
        // 如果该字是当前敏感词的最后一个字，则标识为结尾字
        if (i == key.length() - 1){
          nowMap.put("isEnd", "1");
        }
        System.out.println("封装敏感词库过程："+sensitiveWordMap);
      }
      System.out.println("查看敏感词库数据:" + sensitiveWordMap);
    }
  }

  public static void main(String[] args) {
    List<String>list=new ArrayList<>();
    list.add("微信");
    list.add("号");
    list.add("手机");
    list.add("qq");
    list.add("邮箱");
    list.add("@");
    List<String> stringList = Arrays.asList("微信", "号", "手机","qq","邮箱","@");
    final Map map = initKeyWord(list);

    final Set set = map.keySet();
  }

}
