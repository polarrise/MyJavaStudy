package com.powersi.controller.springmvcStudy.uploadDownload;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.jinbiao.javaStudy.hashcode.Student;
import com.powersi.common.api.CommonResult;
import com.powersi.service.XiaoFaUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/upDownload")
@Controller
@Slf4j
public class UpDownloadController {


    @Autowired
    private  XiaoFaUserService xiaoFaUserService;

    // 模拟从数据库查询出来的数据
    private List<XiaofaUser> data() {
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

    // 导出
    @PostMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        //Student.class 是按导出类  data()应为数据库查询数据，这里只是模拟
        //EasyExcel.write(response.getOutputStream(), Student.class).sheet("模板").doWrite(data());

        //写excel时注册
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), Student.class).registerConverter(new ListStringConverter()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        excelWriter.write(data(),writeSheet);
        //千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

    public static final String PATH = "E:\\";

    @PostMapping("/downloadTemplate")
    @ResponseBody
    public CommonResult<String> downloadTemplate(Map map,HttpServletResponse response) throws IOException {
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + "黑名单导出模板" + ".xlsx");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        //3、、创建工作簿
        ExcelWriterBuilder writeWork = EasyExcel.write(response.getOutputStream(), XiaofaUser.class).registerConverter(new ListStringConverter());
        //4、创建表格
        ExcelWriterSheetBuilder sheet = writeWork.sheet();
        sheet.sheetName("黑名单导出模板");
        //5、调用业务层获取数据
        List<XiaofaUser> xiaofaUserList = xiaoFaUserService.getXiaofaUserList(map);
        //6、写入数据到表格中
        //sheet.doWrite(xiaofaUserList);
        EasyExcel.write(PATH+"黑名单导出模板.xlsx",XiaofaUser.class).sheet("黑名单导出模板").doWrite(xiaofaUserList);
        return CommonResult.success("成功");
    }

    @RequestMapping(method = RequestMethod.POST, path = "/testMultipartFile")
    @ResponseBody
    public CommonResult<String> testMultipartFile(MultipartFile file,@RequestPart String name) {  //@RequestPart 只在表单里面取part
        System.out.println(name);
        System.out.println(file.getSize());
        return CommonResult.success(file.getName());
    }


    private static final String baseUrl = "E:\\WorkSpace\\baseUrl\\";

    @PostMapping("/uploadFile")
    @ResponseBody
    public CommonResult<String> uploadFile(MultipartFile file){
        //file是一个临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除
        log.info("上传的文件=>{}",file);
        //获取文件原始名称
        String originalFilename = file.getOriginalFilename();
        //使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
        String suffix=originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename= UUID.randomUUID().toString()+suffix;

        //创建一个目录对象
        File dir=new File(baseUrl);
        //判断目录是否存在
        if(!dir.exists()){
            //不存在,则新建一个目录
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(baseUrl+filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResult.success(filename);
    }

    @GetMapping("/downloadFile")
    public void downloadFile(String name, HttpServletResponse response){

        try {
            //输入流，通过输入流读取文件内容
            FileInputStream inputStream = new FileInputStream(new File(baseUrl + name));
            //输出流，通过输出流将文件写回浏览器
            ServletOutputStream outputStream = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + "黑名单导出模板" + ".xlsx");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            byte[] bytes = new byte[1024];
            int len=0;
            //读取图片的输入io流放到bytes数组中
            while((len=inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            //关闭资源
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
