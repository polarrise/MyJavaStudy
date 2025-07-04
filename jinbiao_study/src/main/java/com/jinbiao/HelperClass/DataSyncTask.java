package com.jinbiao.HelperClass;

import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: rise
 * @date: 2024-12-23 10:07:38
 * @Description: 数据同步任务
 * @Copyright: www.xshopee.com All rights reserved.
 * 注意：本内容仅限于深圳市多米诺跨境电商有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Slf4j
public class DataSyncTask {
    // 方法：执行数据同步任务
    public void syncData(String startMonth, String endMonth) {
        // 解析开始和结束日期，修正日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startMonth + "-01", formatter);
        LocalDate end = LocalDate.parse(endMonth + "-01", formatter).withDayOfMonth(1).plusMonths(1).minusDays(1); // 设置结束日期为当月最后一天

        // 如果开始和结束日期是同一个月，执行该月的查询操作
        if (start.getYear() == end.getYear() && start.getMonth() == end.getMonth()) {
            queryData(start, end);
        } else {
            // 如果跨越多个月份，执行从开始日期到结束日期前一个月的数据拉取
            List<LocalDate[]> dateRanges = getMonthlyDateRanges(start, end.minusMonths(1)); // end减去一个月
            for (LocalDate[] range : dateRanges) {
                LocalDate firstDay = range[0];
                LocalDate lastDay = range[1];
                queryData(firstDay, lastDay);
            }
        }
    }

    // 方法：获取每个月的第一天和最后一天的日期范围
    private List<LocalDate[]> getMonthlyDateRanges(LocalDate start, LocalDate end) {
        List<LocalDate[]> dateRanges = new ArrayList<>();
        LocalDate currentMonth = start;

        while (currentMonth.isBefore(end) || currentMonth.isEqual(end)) {
            LocalDate firstDay = currentMonth.withDayOfMonth(1);
            LocalDate lastDay = currentMonth.withDayOfMonth(currentMonth.lengthOfMonth());
            dateRanges.add(new LocalDate[] { firstDay, lastDay });
            currentMonth = currentMonth.plusMonths(1); // 移动到下一个月
        }

        return dateRanges;
    }

    // 模拟查询数据的方法
    private void queryData(LocalDate firstDay, LocalDate lastDay) {
        // 这里可以调用实际的查询接口
        System.out.println("查询数据，日期范围：" + firstDay + " 至 " + lastDay);

        // 5页数据，每页10条，模拟50条一批入库
        if(firstDay.toString().equals("2024-01-01")){
            List<Integer>list = new ArrayList<>(100);
            List<Integer> resultList = new ArrayList<>();
            for (int i = 1; i <= 140; i++) {
                list.add(i);
                if(i <= 10){
                    resultList.add(i);
                }
            }
            // 11页数据
            Integer pages = 14;
            int fetchdPage = 1;
            for (int i = 2; i <= pages; i++) {
                for (int j = (i-1)*10+1; j <= i*10; j++) { //1~10,2~
                    resultList.add(j);
                }
                fetchdPage++;
                if(fetchdPage == 5){
                    // 5页数据一批入库...
                    log.info(resultList.toString());
                    resultList = new ArrayList<>();
                    fetchdPage = 0 ;
                }
            }
            if(!CollectionUtils.isEmpty(resultList) && fetchdPage <5){
                log.info(resultList.toString());
            }
        }
    }

    public static void main(String[] args) {
        DataSyncTask task = new DataSyncTask();

        // 示例1：从2024-01到2024-12（查询2024-01到2024-11的数据）
        System.out.println("同步任务1：");
        task.syncData("2024-01", "2024-12");

        // 示例2：从2025-01到2025-02（查询2025-01的数据）
        System.out.println("同步任务2：");
        task.syncData("2025-01", "2025-02");

        // 不执行：
        task.syncData("2025-01", "2025-01");
        task.syncData("2024-12", "2024-12");

        // 执行：
        task.syncData("2024-12", "2025-01");

    }
}
