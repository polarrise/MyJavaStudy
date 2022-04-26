package jinbiao.javaStudy;


import org.apache.commons.lang3.StringUtils;

public class StringUtilsTest {
    public static void main(String[] args) {
        String str="";
        //字符串是空字符串或者null则 返回true
        boolean result = StringUtils.isBlank(str);
        System.out.println(result);

        //字符串不是空字符串或者不为null则 返回true
        String str2=null;
        boolean result2 = StringUtils.isNotBlank(str);
        System.out.println(result2);
    }
}
