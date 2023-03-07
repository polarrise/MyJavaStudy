package com.powersi.utils;


import com.alibaba.fastjson.JSON;
import com.powersi.common.api.CommonResult;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class WebUtils {


    /**
     * 返回json数据
     *
     * @param response
     * @param object
     */
    public static void writeJson(HttpServletResponse response, CommonResult object) {
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = null;
        try {
            String data =  JSON.toJSONString(object);
            out = response.getWriter();
            out.print(data);
            out.flush();
        } catch (Exception e) {

        } finally {
            if (out != null) {
                out.close();
            }
        }
    }



}
