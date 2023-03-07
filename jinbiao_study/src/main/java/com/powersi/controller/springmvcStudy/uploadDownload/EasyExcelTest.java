package com.powersi.controller.springmvcStudy.uploadDownload;

import com.alibaba.excel.EasyExcel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class EasyExcelTest {


    public static final String PATH = "E:\\xml\\easyExcelDemo\\";
    public static final String fileName = "testEasyExcelWrite.xlsx";
    public static final String pathName = PATH + fileName;

    // 模拟从数据库查询出来的数据
    private static List<XiaofaUser> data() {
        List<XiaofaUser> list = new ArrayList<XiaofaUser>();
        for (int i = 0; i < 10; i++) {
            XiaofaUser user = new XiaofaUser();
            list.add(user);
            user.setUserName("张三" + i);
            user.setPhone("phone" + i);
            user.setSource("微信"+i);
        }
        return list;
    }

    @Test
    public void testEasyExcelWrite() {
        List<XiaofaUser> userList = data();
        List<XiaofaUser> excelDataList = userList.stream().map(user -> {
            XiaofaUser excelData = new XiaofaUser();
            excelData.setUserName(user.getUserName());
            excelData.setPhone(user.getPhone());
            excelData.setSource(user.getSource());
            return excelData;
        }).collect(Collectors.toList());
        //参数声明路径名和表头
        EasyExcel.write(pathName,XiaofaUser.class).sheet().doWrite(excelDataList);
    }
}

