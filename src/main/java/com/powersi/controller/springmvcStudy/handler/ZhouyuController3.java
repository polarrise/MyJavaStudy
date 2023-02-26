package com.powersi.controller.springmvcStudy.handler;

import com.powersi.annotation.Mobile;
import com.powersi.common.api.CommonResult;
import com.powersi.controller.springmvcStudy.annotation.MyReturnValueResolver;
import com.powersi.controller.springmvcStudy.methodArgumentResolver.StringToUserEditor;
import com.powersi.controller.springmvcStudy.annotation.MyArgumentResolver;
import com.powersi.controller.springmvcStudy.methodParam.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping
@Component
@Slf4j
public class ZhouyuController3 {

    @RequestMapping(method = RequestMethod.GET, path = "/test3")
    @ResponseBody
    public CommonResult test3(@Mobile String phone) {
        return CommonResult.success("zhouyu");
    }


    /**
     * 自定义方法参数解析器1：通过 @InitBinder注解来实现。
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/testMethodArgumentResolver1")
    @ResponseBody
    public CommonResult testMethodArgumentResolver1(@RequestParam("user") User user) {  //针对user对象自定义参数解析器,让Get请求可以接受,/stringToUserEditor?user=123里面的123值赋值给user.name
        return CommonResult.success(user.getUsername());
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        //让请求参数user接收的字符串转成User对象的逻辑：
        binder.registerCustomEditor(User.class,new StringToUserEditor());
    }

    /**
     * 自定义方法参数解析器2：针对方法上加了自定义注解@MyArgumentResolver，通过自定义参数解析器对参数解析，解析完最终赋值到user对象。
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/testMethodArgumentResolver2")
    @ResponseBody
    public CommonResult testMethodArgumentResolver2(@MyArgumentResolver(name="自定义方法参数解析器") User user) {
        return CommonResult.success(user);
    }

    /**
     * 自定义方法参数解析器2：针对方法上加了自定义注解@MyArgumentResolver，通过自定义参数解析器对参数解析，解析完最终赋值到user对象。
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/testMethodReturnValueResolver")
    @MyReturnValueResolver(name="自定义方法返回解析器",value = {"email","phone"})
    public CommonResult testMethodReturnValueResolver(@MyArgumentResolver(name="自定义方法参数解析器") User user) {
        return CommonResult.success(user);
    }

}
