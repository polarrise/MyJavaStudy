package com.jinbiao.HelperClass;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Arrays;
import java.util.List;

/**
 * @author: rise
 * @date: 2024-12-24 10:52:44
 * @Description: excel实现表格导出样式demo
 * @Copyright: www.xshopee.com All rights reserved.
 * 注意：本内容仅限于深圳市多米诺跨境电商有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class ExcelExportExample {

    public static void main(String[] args) {
        // 模拟数据
        List<MyData> data = getData();

        // 写入Excel文件
        String fileName = "example.xlsx";

        // 创建表格样式
        HorizontalCellStyleStrategy styleStrategy = new HorizontalCellStyleStrategy(createHeadStyle(), createContentStyle());

        // 创建EasyExcel写对象并设置样式
        EasyExcel.write(fileName, MyData.class)
                .registerWriteHandler(styleStrategy) // 设置样式
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(30))
                .sheet("数据表格")
                .doWrite(data);
    }

    // 自定义表头样式
    private static WriteCellStyle createHeadStyle() {
        WriteCellStyle headStyle = new WriteCellStyle();

        // 设置表头字体样式
        WriteFont font = new WriteFont();
        font.setBold(true);  // 加粗
        font.setFontHeightInPoints((short) 16);  // 设置字体大小
        font.setColor(IndexedColors.WHITE.getIndex());  // 设置字体颜色
        headStyle.setWriteFont(font);

        // 设置背景色（渐变背景）
        headStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());  // 设置背景色为深蓝
        headStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);  // 实心填充背景

        // 设置对齐方式
        headStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);  // 水平居中
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);  // 垂直居中

        return headStyle;
    }

    // 自定义内容样式
    private static WriteCellStyle createContentStyle() {
        WriteCellStyle contentStyle = new WriteCellStyle();

        // 设置内容区字体样式
        WriteFont font = new WriteFont();
        font.setFontHeightInPoints((short) 13);  // 设置字体大小
        contentStyle.setWriteFont(font);

        // 设置单元格对齐方式
        contentStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);  // 水平居中
        contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);  // 垂直居中

        // 设置边框样式
        contentStyle.setBorderBottom(BorderStyle.valueOf((short) 1)); // 设置底部边框为 THIN (值为 1)
        contentStyle.setBorderLeft(BorderStyle.valueOf((short) 1));   // 设置左边边框为 THIN
        contentStyle.setBorderRight(BorderStyle.valueOf((short) 1));  // 设置右边边框为 THIN
        contentStyle.setBorderTop(BorderStyle.valueOf((short) 1));    // 设置顶部边框为 THIN

        // 设置内容区的背景色（浅色背景）
        contentStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());  // 浅黄色背景
        contentStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);  // 实心填充背景

        return contentStyle;
    }

    // 获取模拟数据
    private static List<MyData> getData() {
        return Arrays.asList(  new MyData(1, "张三", 1000, "2024-01-01"),
                new MyData(2, "李四", 2000, "2024-01-02"),
                new MyData(3, "王五", 3000, "2024-01-03"));
    }

    // 数据类
    static class MyData {
        private Integer id;
        private String name;
        private Integer amount;
        private String date;

        public MyData(Integer id, String name, Integer amount, String date) {
            this.id = id;
            this.name = name;
            this.amount = amount;
            this.date = date;
        }

        // getter 和 setter 方法
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}