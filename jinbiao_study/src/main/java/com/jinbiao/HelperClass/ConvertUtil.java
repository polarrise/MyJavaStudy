package com.jinbiao.HelperClass;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j
public class ConvertUtil {

    private static String separator = "_";
    private static final int DEF_DIV_SCALE = 4;
    private static final Pattern PATTERN_FLOAT = Pattern.compile("\\d+.\\d*$");     // 数字包括小数
    private static final Pattern PATTERN_NUMBER = Pattern.compile("\\d+$");        // 数字


    /**
     * 驼峰转下划线
     * @Author wsp
     * @param humpName
     * @return
     */
  public static String HumpTransformUnderline(String humpName){
                if(StringUtils.isEmpty(humpName))return null;
    return   humpName.replaceAll("([a-z])([A-Z])", "$1"+separator+"$2").toLowerCase()
              .replaceAll("([0-9])([a-z])","$1"+separator+"$2");
  }

    /**
     * 驼峰转下划线
     * @Author xk
     * @param humpName
     * @return
     */
    public static String TransformUnderlineBesideNumber(String humpName){
        if(StringUtils.isEmpty(humpName))return null;
        return   humpName.replaceAll("([a-z])([A-Z])", "$1"+separator+"$2").toLowerCase()
                .replaceAll("([0-9])([A-Z])","$1"+separator+"$2");
    }


    /**
     * 根据统筹区获取对应的等级
     *
     * @param poolarea
     * @return int
     * @Author zhx
     * @Date 2021/9/4 10:15
     */

  public static int getLvByPoolarea(String poolarea){
        if(poolarea==null || poolarea.length()!=6){
            return -1;
        }
        String suffix4= poolarea.substring(2,6);
        String suffix2= poolarea.substring(4,6);
        if(suffix4!=null && "0000".equals(suffix4)){
            return 1;
        }else if(suffix2!=null && "00".equals(suffix2)){
            return 2;
        }else {
            return 3;
        }
  }

    /**
     * 根据统当前登录用户的医保区划获取长度
     *
     * @param admdvs
     * @return String
     * @Author zhx
     * @Date 2021/9/4 10:15
     */
    public static String getAreaLengthByAdmdvs(String admdvs){
        if(admdvs==null || admdvs.length()!=6){
            return "-1";
        }
        String suffix4= admdvs.substring(2,6);
        String suffix2= admdvs.substring(4,6);
        if(suffix4!=null && "0000".equals(suffix4)){
            return "4";
        }else if(suffix2!=null && "00".equals(suffix2)){
            return "6";
        }else {
            return "8";
        }
    }


    /**
     * 精确加法
     * @Created by zhx on 2021/9/8 14:42
     * @Date 2021/9/8 14:42
     * @copyright http://www.powersi.com.cn  2021 © 创智和宇
     */
    public static double add(double firValue, double sedValue) {
        BigDecimal firBD = BigDecimal.valueOf(firValue);
        BigDecimal sedBD = BigDecimal.valueOf(sedValue);
        return firBD.add(sedBD).doubleValue();
    }

    /**
     * 精确减法
     * @Created by zhx on 2021/9/8 14:42
     * @Date 2021/9/8 14:42
     * @copyright http://www.powersi.com.cn  2021 © 创智和宇
     */
    public static double sub(double firValue, double sedValue) {
        BigDecimal firBD = BigDecimal.valueOf(firValue);
        BigDecimal sedBD = BigDecimal.valueOf(sedValue);
        return firBD.subtract(sedBD).doubleValue();
    }

    /**
     * 精确乘法
     * @Created by zhx on 2021/9/8 14:42
     * @Date 2021/9/8 14:42
     * @copyright http://www.powersi.com.cn  2021 © 创智和宇
     */
    public static double mul(double firValue, double sedValue) {
        BigDecimal firBD = BigDecimal.valueOf(firValue);
        BigDecimal sedBD = BigDecimal.valueOf(sedValue);
        return firBD.multiply(sedBD).doubleValue();
    }

    /**
     * 精确除法 使用默认精度
     * @Created by zhx on 2021/9/8 14:42
     * @Date 2021/9/8 14:42
     * @copyright http://www.powersi.com.cn  2021 © 创智和宇
     */
    public static double div(double firValue, double sedValue) {
        double num = 0;
        try {
            num = div(firValue, sedValue, DEF_DIV_SCALE);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * 精确除法
     * @Created by zhx on 2021/9/8 14:42
     * @Date 2021/9/8 14:42
     * @copyright http://www.powersi.com.cn  2021 © 创智和宇
     * @param scale 精度
     * @throws IllegalAccessException
     */
    public static double div(double firValue, double sedValue, int scale) throws IllegalAccessException {
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = BigDecimal.valueOf(firValue);
        BigDecimal b2 = BigDecimal.valueOf(sedValue);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 四舍五入
     * @Created by zhx on 2021/9/8 14:42
     * @Date 2021/9/8 14:42
     * @copyright http://www.powersi.com.cn  2021 © 创智和宇
     * @param scale 小数点后保留几位
     */
    public static double round(double value, int scale) {
        double num = 0;
        try {
            num = div(value, 1, scale);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * 比较两数是否相等
     * @Created by zhx on 2021/9/8 14:42
     * @Date 2021/9/8 14:42
     * @copyright http://www.powersi.com.cn  2021 © 创智和宇
     */
    public static boolean equalTo(BigDecimal firBD, BigDecimal sedBD) {
        if (firBD == null || sedBD == null) {
            return false;
        }
        return 0 == firBD.compareTo(sedBD);
    }





    public static boolean isDigital(String code) {
        Matcher dMatcher = PATTERN_FLOAT.matcher(code);
        Matcher dMatcher1 = PATTERN_NUMBER.matcher(code);
        return dMatcher.matches() || dMatcher1.matches();
    }

    /**
     * String类型的数字转换成有精度的字符
     * @Created by zhx on 2021/9/8 14:42
     * paramet 传0.00 或 0.0000 等格式
     * @Date 2021/9/8 14:42
     * @copyright http://www.powersi.com.cn  2021 © 创智和宇
     */
    public static String stringFormat(String str,String strFormatType) {
        if(str!=null && !"".equals(str) && isDigital(str)){
            DecimalFormat format = new DecimalFormat(strFormatType);
            String strFormat = format.format(new BigDecimal(str));
            return strFormat;
        }else{
            return strFormatType;
        }
    }

}
