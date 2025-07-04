package com.jinbiao.HelperClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
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

    public static final SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm:ss");
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
        Date date1 = getYyMmDdTime("2024-09-29 11:05:10");
        Date date2 = getYyMmDdTime("2024-09-29 11:06:10");
        long time = date2.getTime() - date1.getTime();
        log.info("两日期相差时间ms:{}",time);
    }

    public static Date getYyMmDdTime(String date){
        try {
            return format.parse(date);
        }catch (Exception e){
            throw new RuntimeException("日期转换有误");
        }
    }
    public static Date getHhMmSsTime(String date){
        try {
            return timeSdf.parse(date);
        }catch (Exception e){
            throw new RuntimeException("时间转换有误");
        }
    }
    /**
     * 计算两日期，一个是Date类型，一个是Time类型。之前相差的毫秒数
     */
    public static void calcTimeDiff2(){
        // 获取2024-11-04 18:24:10的时分秒
        Date loginOutDate = getYyMmDdTime("2024-11-04 18:24:10");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loginOutDate);
        System.out.println("时："+calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("分："+calendar.get(Calendar.MINUTE));
        System.out.println("秒："+calendar.get(Calendar.SECOND));
        // 时分秒转成Date类型
        Date loginOutTimeDate = getHhMmSsTime(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
        Date loginInTimeDate = getHhMmSsTime("18:00:00");
        // 1450s
        log.info("两日期相差时间ms:{}",loginOutTimeDate.getTime() - loginInTimeDate.getTime());


        String hhmmss = format.format(loginOutDate).substring(11, 19);
        log.info("两日期相差时间ms:{}",(getHhMmSsTime(hhmmss).getTime() - loginInTimeDate.getTime()));
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
        objectList.add(new TimeRule(getYyMmDdTime("2024-11-05 11:05:10"),calendar.getTime()));

        calendar.set(2023, Calendar.NOVEMBER, 1, 18, 15, 10); // 2023-11-01 18:15:10
        objectList.add(new TimeRule(getYyMmDdTime("2024-11-05 17:05:00"),calendar.getTime()));

        calendar.set(2023, Calendar.NOVEMBER, 1, 19, 0, 5); // 2023-11-01 19:00:05
        objectList.add(new TimeRule(getYyMmDdTime("2024-11-05 18:30:00"),calendar.getTime()));

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

        TimeRule timeRule = filteredList.get(0);
        Date logInDate = timeRule.getLogInDate();
        LocalTime logInTime = LocalTime.of(logInDate.getHours(), logInDate.getMinutes(), logInDate.getSeconds());
        if(logInTime.isBefore(endLocalTime)){
            filteredList.get(0).setLogInDate(endTime);
        }
        // 输出过滤后的列表
        long totalOverTime = 0;
        for (TimeRule obj : filteredList) {
            System.out.println(obj.getLogoutDate());
            Date logInDate1 = obj.getLogInDate();
            Date logOutDate1 = obj.getLogoutDate();
            LocalTime localLogInTime = LocalTime.of(logInDate1.getHours(), logInDate1.getMinutes(), logInDate1.getSeconds());
            LocalTime localLogOutTime = LocalTime.of(logOutDate1.getHours(), logOutDate1.getMinutes(), logOutDate1.getSeconds());

            // Date类型转成LocalTime 去计算Wed Nov 01 18:15:00 CST 2023 与 18:00:00相差多少秒钟
            long between = ChronoUnit.SECONDS.between(localLogInTime, localLogOutTime);
            log.info("登录时间：{}与登出时间{}，相差{}秒",localLogInTime,localLogOutTime,between);
            totalOverTime += between;
        }
        log.info("总和:{}",totalOverTime);
    }

    /**
     * List集合里面装的对象属性是Date类型，我想看集合里面元素所在哪些年月
     */
    public static void getYearMonthInList(){
        // 创建一些示例数据
        List<Event> events = new ArrayList<>();
        events.add(new Event("Event 1", new Date(2024 - 1900, 0, 15)));  // 2024年1月15日
        events.add(new Event("Event 2", new Date(2024 - 1900, 5, 10)));  // 2024年6月10日
        events.add(new Event("Event 3", new Date(2024 - 1900, 2, 25)));  // 2024年3月25日

        // 提取年月
        Set<String> yearMonthSet = extractYearMonthFromEvents(events);

        // 输出结果
        System.out.println("Events are in the following year-months: " + yearMonthSet);
    }

    // 提取年月
    public static Set<String> extractYearMonthFromEvents(List<Event> events) {
        Set<String> yearMonthSet = new HashSet<>();

        // 遍历每个Event
        for (Event event : events) {
            // 将Date转换为LocalDate
            LocalDate localDate = event.getEventDate()
                                       .toInstant()
                                       .atZone(ZoneId.systemDefault())
                                       .toLocalDate();
            // 获取年份和月份，格式化为 yyyy-MM
            String yearMonth = String.format("%d-%02d", localDate.getYear(), localDate.getMonthValue());
            // 添加到集合中
            yearMonthSet.add(yearMonth);
        }

        return yearMonthSet;
    }

    public static void calcDifferYearMonth(String startYearMonth, String endYearMonth){
        // 获取开始和结束日期的年月
        try {
            // 解析字符串为Date对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date start = sdf.parse(startYearMonth);
            Date end = sdf.parse(endYearMonth);

            // 创建Calendar对象
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();

            // 设置开始和结束日期
            startCalendar.setTime(start);
            endCalendar.setTime(end);

            // 打印开始和结束年月
            System.out.println("开始年月: " + startYearMonth);
            System.out.println("结束年月: " + endYearMonth);
            System.out.println("包含的年月：");

            // 遍历区间的每个月
            while (startCalendar.before(endCalendar) || startCalendar.equals(endCalendar)) {
                int year = startCalendar.get(Calendar.YEAR);
                int month = startCalendar.get(Calendar.MONTH) + 1;  // 月份从0开始，所以加1

                // 输出当前的年月
                System.out.printf("%d-%02d%n", year, month);

                // 将月份加1
                startCalendar.add(Calendar.MONTH, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据计算日期匹配集合中的对象
     * eg:calculationDate在2024-09-09之后取2024-09-09这个对象，在2024-11-01之后取2024-11-01这个对象
     * @param calculationDate
     * @return
     */
    public static MyObject findMatchingObject(LocalDate calculationDate) {
        // 创建一些测试数据
        List<MyObject> list = new ArrayList<>();
        list.add(new MyObject(null, "Obj1"));            // 生效日期为 null
        list.add(new MyObject(LocalDate.of(2024, 5, 10), "Obj1"));
        list.add(new MyObject(LocalDate.of(2024, 7, 15), "Obj2"));
        list.add(new MyObject(LocalDate.of(2024, 9, 9), "Obj3"));
        list.add(new MyObject(LocalDate.of(2024, 11, 1), "Obj4"));

        // 确保list是有序的，处理null值
        list.sort((o1, o2) -> {
            if (o1.getEffectiveDate() == null) return -1;
            if (o2.getEffectiveDate() == null) return 1;
            return o1.getEffectiveDate().compareTo(o2.getEffectiveDate());
        });

        // 从倒数第二个开始往前遍历，找到满足条件的对象
        for (int i = list.size() - 1; i >= 0; i--) {
            MyObject obj = list.get(i);
            LocalDate effectiveDate = obj.getEffectiveDate();

            // 如果生效日期为null，且计算日期大于该对象的生效日期，则选择此对象
            if (effectiveDate == null || !calculationDate.isBefore(effectiveDate)) {
                return obj;
            }
        }
        // 如果没有符合条件的对象，则返回null或抛出异常
        return null;
    }

    /**
     * 获取开始年月 ~ 结束年月 之间的所有月份
     */
    public static List<YearMonth> getMonthsBetween(YearMonth  startYearMonth , YearMonth  endYearMonth) {
        List<YearMonth> months = new ArrayList<>();

        // 从起始日期的月份开始
        YearMonth start = YearMonth.from(startYearMonth);
        // 结束日期的月份
        YearMonth end = YearMonth.from(endYearMonth);

        // 使用循环从 start 月份到 end 月份
        YearMonth current = start;
        while (!current.isAfter(end)) {
            months.add(current);
            current = current.plusMonths(1);  // 增加一个月
        }
        return months;
    }

    /**
     *  字符串转换为 YearMonth
     */
    public static YearMonth convertStringToYearMonth(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        return YearMonth.parse(dateStr, formatter);
    }


    public static void main(String[] args) throws ParseException {
        // 2021-11-11 -> 2021年11月11日
        // System.out.println(new DateUtil().dateConversion("2021-11-11"));
        //
        // // 获取两个日期之间的天数
        // System.out.println(getDaysBetween("2024-09-29", "2024-10-07"));
        //
        // // 看当前日期是属于夏季还是冬季， 一年中5.1 ~ 9.30属于夏季作息， 10.01 ~ 4.30属于冬季作息
        // getCurrentTimeSchedule(LocalDate.of(2024, 10, 1));
        //
        // // 计算两日期之前的相差毫秒数
        // calcTimeDiff();
        //
        // // 计算两日期，一个是Date类型，一个是Time类型。之前相差的毫秒数
        // calcTimeDiff2();
        //
        // // Date类型的时分秒与Time类型比较
        // compareTimeWithDate();
        //
        // // List集合里面装的对象属性是Date类型，我想看集合里面元素所在哪些年月
        // getYearMonthInList();
        //
        // // 看开始年月和结束年月字符串 之间隔的年月
        // String startYearMonth = "2024-01";
        // String endYearMonth = "2025-12";
        // calcDifferYearMonth(startYearMonth,endYearMonth);
        //
        // // 需求：根据计算日期匹配刚好大于集合中对象的日期属性 那个对象
        // MyObject matchingObject = findMatchingObject(LocalDate.of(2024, 5, 9));
        // System.out.println(matchingObject);
        // MyObject matchingObject2 = findMatchingObject(LocalDate.of(2024, 9, 15));
        // System.out.println(matchingObject2);
        //
        // // 获取开始年月 ~ 结束年月 之间的所有月份
        // List<YearMonth> monthsBetween = getMonthsBetween(convertStringToYearMonth("2024-01"), convertStringToYearMonth("2024-12"));
        // System.out.println(monthsBetween);


        LocalDateTime now = LocalDateTime.now();

        // 2025年2月17号上午11:04
        LocalDateTime expireTime = LocalDateTime.of(2025, Month.FEBRUARY, 18, 13, 50,0);
        if(now.isAfter(expireTime.plusHours(1))){
            System.out.println("当前时间在目标时间1小时之后");
        }else{
            System.out.println("当前时间在目标时间1小时之前");
        }
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
class TimeRule{
    private Date logInDate;

    private Date logoutDate;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Event {
    private String eventName;
    private Date eventDate;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class MyObject{
    private LocalDate effectiveDate;  // 生效日期
    private String otherProperty;    // 其他属性
}