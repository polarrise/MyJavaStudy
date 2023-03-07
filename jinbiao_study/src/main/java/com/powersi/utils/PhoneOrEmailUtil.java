package com.powersi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author WangJinbiao
 * @date 2023/2/20 23:34
 * @desc
 */
public class PhoneOrEmailUtil {

    /**
     *  脱敏规则——手机号码(手机号码隐藏中间4位数)
     * @Description:
     * @param telnum
     * @return
     * @ReturnType String
     * @author: zzx
     * @Created 2020年6月2日 上午10:25:10
     */
    public static String phoneNum(String telnum){
        String rtnStr = telnum;
        if(isEmpty(rtnStr)||rtnStr.trim().length()<7){
            return rtnStr;
        }
        rtnStr = rtnStr.trim();
        // 校验是否为电话号码
        String regExp = "^1\\d{10}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(rtnStr);
        if(m.matches()){// 若判断为固话
//          rtnStr = replace(rtnStr,rtnStr.length()-3,"****");
            rtnStr = replace(rtnStr,4,"****");
        }
        return rtnStr;
    }

    /**
     *  脱敏规则——邮箱 (@前三位)
     * @Description:
     * @param str
     * @return
     * @ReturnType String
     * @author: zzx
     * @Created 2020年6月2日 上午10:25:10
     */
    public static String emailNum(String str){
        String rtnStr = str;
        String regExp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        Pattern p = Pattern.compile(regExp);
        Matcher matcher = p.matcher(rtnStr);
        boolean isMatched = matcher.matches();
        if(isMatched){
            int s =  rtnStr.indexOf("@");
            if(s >= 6) {
                return replace(rtnStr.trim(), s-3, "****");
            }else if(s >= 3 && s < 6) {
                return replace(rtnStr.trim(), s-1, "**");
            }else {
                return str;
            }
        }else{
            return rtnStr;
        }
    }

    /**
     * 脱敏规则
     * @Description:
     * @param str(需脱敏的字符串)
     * @param n(脱敏开始位)
     * @param newChar(脱敏掩码)
     * @return
     * @ReturnType String
     * @author: zzx
     * @Created 2020年6月2日 上午10:25:10
     */
    public static String replace (String str,int n,String newChar){
        String s1="";
        String s2="";
        try{
            s1=str.substring(0,n-1);
            s2=str.substring(n-1+newChar.length(),str.length());
            return s1+newChar+s2;
        }catch(Exception ex){
            return str;
        }
    }

    /**
     * 判断字符串是否为null || 空字符串
     *
     * @param param
     * @return
     */
    public static boolean isEmpty(String param) {
        return param == null || "".equals(param.trim());
    }
    public static String replaceChar(String str) {
        String rtnStr = str;
        if(!isEmpty(rtnStr)) {
            String regExp = "^1\\d{10}$";
            Pattern p = Pattern.compile(regExp);
            Matcher m = p.matcher(rtnStr);

            String regExp2 = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
            Pattern p2 = Pattern.compile(regExp2);
            Matcher matcher2 = p2.matcher(rtnStr);

            if(m.matches()) {
                rtnStr = phoneNum(str);
            }else if(matcher2.matches()) {
                rtnStr = emailNum(str);
            }
        }
        return rtnStr;
    }
    public static void main(String args[]) {
        String str = "1585914314@qq.com";
        System.out.println(emailNum(str));
    }
}
