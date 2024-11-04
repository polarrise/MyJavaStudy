package com.jinbiao.HelperClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author com.jinbiao
 * @date 2022/1/7
 * @apiNote
 */
@Slf4j
public class DateUtil {

    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 日期格式
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 日期转换成字符串
     * @param dateUtil
     * @return str
     */
    public static String DateToStr(DateUtil dateUtil) {
        String str = format.format(dateUtil);
        return str;
    }

    /**
     * 字符串转换成日期
     * @param str
     * @return date
     */
    public static java.util.Date StrToDate(String str) {
        java.util.Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public String dateConversion(String str) throws ParseException {
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date date=simpleDateFormat .parse(str);  //字符串转换成日期
        SimpleDateFormat simpleDateFormat2  =new SimpleDateFormat("yyyy年m月d日");
        String dateString=simpleDateFormat2.format(date).toString();
        return dateString;
    }

    /**
     * @param smallDate
     * @param bigDate
     * @desc 获取两个日期之间的天数
     */
    public static int getDaysBetween(String smallDate, String bigDate) throws ParseException {
        // 获取两个日期的时间戳
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smallDate));
        long smallTime = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bigDate));
        long bigTime = cal.getTimeInMillis();
        // 相差的日期
        long days = (bigTime - smallTime) / (1000 * 3600 * 24);
        // long转int 存在溢出情况  根据业务情况编辑catch中返回值
        try {
            return Integer.parseInt(String.valueOf(days));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 看当前日期是属于夏季还是冬季， 一年中5.1 ~ 9.30属于夏季作息， 10.01 ~ 4.30属于冬季作息
     *
     * @param date
     */
    public static void getCurrentTimeSchedule(LocalDate date){
        LocalDate startDate = LocalDate.of(date.getYear(), 5, 1);
        LocalDate endDate = LocalDate.of(date.getYear(), 10, 1);

        // 日期不在4-30号之前并且 不在10-1 之后，那就只能是5.1~ 9.30
        Boolean isInRange = date.isAfter(startDate) && date.isBefore(endDate);
        log.info(isInRange ? "当前日期所处季节：夏季":"当前日期所处季节：冬季");
    }

    /**
     * 计算两日期之前相差的毫秒数
     */
    public static void calcTimeDiff(){
        Date date1 = getTime("2024-09-29 11:05:10");
        Date date2 = getTime("2024-09-29 11:06:10");
        long time = date2.getTime() - date1.getTime();
        log.info("两日期相差时间ms:{}",time);
    }
    public static Date getTime(String date){
        try {
            return format.parse(date);
        }catch (Exception e){
            throw new RuntimeException("日期转换有误");
        }
    }

    /**
     * Date类型的时分秒与Time类型比较
     */
    public static void compareTimeWithDate(){
        // 创建一个包含 MyObject 的列表
        List<TimeRule> objectList = new ArrayList<>();

        // 使用 Calendar 添加一些示例日期
        Calendar calendar = Calendar.getInstance();

        calendar.set(2023, Calendar.NOVEMBER, 1, 17, 30, 0); // 2023-11-01 17:30:00
        objectList.add(new TimeRule(calendar.getTime()));

        calendar.set(2023, Calendar.NOVEMBER, 1, 18, 15, 0); // 2023-11-01 18:15:00
        objectList.add(new TimeRule(calendar.getTime()));

        calendar.set(2023, Calendar.NOVEMBER, 1, 19, 0, 0); // 2023-11-01 19:00:00
        objectList.add(new TimeRule(calendar.getTime()));

        Time endTime = Time.valueOf("18:00:00");
        LocalTime endLocalTime = endTime.toLocalTime();

        // 过滤出时分秒在 "18:00:00" 之后的记录
        List<TimeRule> filteredList = objectList.stream()
                .filter(obj -> {
                    Date date = obj.getLogoutDate();
                    LocalTime time = LocalTime.of(date.getHours(), date.getMinutes(), date.getSeconds());
                    return time.isAfter(endLocalTime);
                })
                .collect(Collectors.toList());

        // 输出过滤后的列表
        for (TimeRule obj : filteredList) {
            System.out.println(obj.getLogoutDate());
        }

    }

    public static void main(String[] args) throws ParseException {
        // 2021-11-11 -> 2021年11月11日
        System.out.println(new DateUtil().dateConversion("2021-11-11"));

        // 获取两个日期之间的天数
        System.out.println(getDaysBetween("2024-09-29", "2024-10-07"));

        // 看当前日期是属于夏季还是冬季， 一年中5.1 ~ 9.30属于夏季作息， 10.01 ~ 4.30属于冬季作息
        getCurrentTimeSchedule(LocalDate.of(2024, 10, 1));

        // 计算两日期之前的相差毫秒数
        calcTimeDiff();

        compareTimeWithDate();
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
class TimeRule{
    private Date logoutDate;
}