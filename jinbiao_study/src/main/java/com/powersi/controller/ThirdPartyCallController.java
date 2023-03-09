package com.powersi.controller;
import com.powersi.common.api.CommonResult;
import com.powersi.entity.User;
import com.powersi.qo.UserInfoQO;
import com.powersi.service.ThirdPartyCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
     * 白名单放行，通过appId简单获取私钥：
     * @param appId
     * @return
     */
    @GetMapping("/getSecret")
    @ResponseBody
    public CommonResult<String> getSecret(String appId) {
        return CommonResult.success(thirdPartyCallService.getSecret(appId));
    }

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
