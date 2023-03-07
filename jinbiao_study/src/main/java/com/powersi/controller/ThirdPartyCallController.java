package com.powersi.controller;
import com.powersi.common.api.CommonResult;
import com.powersi.entity.User;
import com.powersi.qo.UserInfoQO;
import com.powersi.service.ThirdPartyCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author：Jinbiao
 * @Date：2023/3/7 12:55
 * @Desc：第三方调用Controller
 */
@Controller
@RequestMapping("/thirdPartyService")
public class ThirdPartyCallController {

    @Autowired
    private ThirdPartyCallService thirdPartyCallService;

    /**
     * idea如何本地调试远程服务器的代码？
     * @param userInfoQO
     * @return
     */
    @PostMapping("/getUserDetailInfo")
    @ResponseBody
    public CommonResult<User> getUserDetailInfo(@RequestBody UserInfoQO userInfoQO) {
        return CommonResult.success(thirdPartyCallService.getUserDetailInfo(userInfoQO));
    }

}
