package com.powersi.controller;


import com.jinbiao.manyThreads.tulinSchool.juc.lock.readWriteLock.Cache;
import com.powersi.annotation.DealResult;
import com.powersi.annotation.Mobile;
import com.powersi.common.api.CommonResult;
import com.powersi.entity.CaseCenter;
import com.powersi.enums.JudgeTaskFinishedWaysEnum;
import com.powersi.qo.CaseQO;
import com.powersi.qo.UserInfoQO;
import com.powersi.service.CaseCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/caseCenterApi")
@Api(value = "CaseCenter类Api文档", tags = {"查询CaseCenterController"})
@Validated
public class CaseCenterController {
    @Autowired
    private CaseCenterService caseCenterService;

    @RequestMapping("caseCenter")
    @ApiOperation(value = "根据ID查询Person", notes = "")
    public CommonResult<List<CaseCenter>> getAllCase(@RequestBody Map<String, Object> map) {
        return CommonResult.success(caseCenterService.getAllCase(map));
    }

    @GetMapping("getCaseInfoById")
    @ApiOperation(value = "查询案情详情", notes = "查询案情详情")
    public CommonResult<Map> getCaseInfoById(Long id) throws ExecutionException, InterruptedException {
        return CommonResult.success(caseCenterService.getCaseInfoById(id));
    }

    @GetMapping("testFuture")
    @ApiOperation(value = "查询案情详情", notes = "查询案情详情")
    public CommonResult<Map> testFuture(Long id) throws ExecutionException, InterruptedException {
        return CommonResult.success(caseCenterService.testFuture(id));
    }

    @GetMapping("testTaskIfFinished")
    @ApiOperation(value = "测试线程池任务是否执行完成", notes = "测试线程池任务是否执行完成")
    public CommonResult<Map> testTaskIfFinished(Long id, @RequestParam @NotNull JudgeTaskFinishedWaysEnum judgeTaskFinishedWay) throws ExecutionException, InterruptedException {
        return CommonResult.success(caseCenterService.testTaskIfFinished(id, judgeTaskFinishedWay));
    }

    @GetMapping("testCompletableFuture")
    @ApiOperation(value = "查询案情详情", notes = "查询案情详情")
    public CommonResult<Map> testCompletableFuture(Long id) throws Exception {
        return CommonResult.success(caseCenterService.testCompletableFuture(id));
    }

    /**
     * Get 请求的参数接收一般依赖这两个注解，但是处于 url 有长度限制和代码的可维护性，超过 5 个参数尽量用实体来传参。
     * 对 @PathVariable 和 @RequestParam 参数进行校验需要在入参声明约束的注解。
     * 如果校验失败，会抛出 MethodArgumentNotValidException 异常。
     *
     * @param num
     * @return 校验: 1000>= num的值>=1
     */
    @GetMapping("/{num}")
    public CommonResult<Integer> detail(@PathVariable("num") @Min(1) @Max(1000) Integer num) {
        return CommonResult.success(num * num);
    }

    @GetMapping("/getByEmail")
    @DealResult(name = "脱敏返回值邮箱号", value = {"phone", "email"})
    public CommonResult<CaseQO> getByEmail(@RequestParam @NotBlank @Email String email, @NotBlank String phone) {
        CaseQO caseQO = new CaseQO();
        caseQO.setPhone(phone);
        caseQO.setEmail(email);
        return CommonResult.success(caseQO);
    }

    @PostMapping("/test-validation")
    public CommonResult<Map> testValidation(@RequestBody @Validated CaseQO caseQO) throws Exception {
        return CommonResult.success(caseCenterService.testFuture(caseQO.getId()));
    }

    /**
     * 测试自定义注解
     *
     * @param phone
     * @return
     */
    @GetMapping("/test-customValidation")
    public CommonResult<String> testCustomValidation(@Validated @Mobile String phone) {
        return CommonResult.success(phone);
    }

    private static Map<Long, String> map = new HashMap<Long, String>();

    {
        map.put(1L, "111");
        map.put(2L, "222");
    }

    /**
     * 理解本地缓存Map--
     *
     * @param userInfoQO
     * @return
     */
    @PostMapping("/underStandCacheMap")
    @ResponseBody
    public CommonResult<Object> getUserDetailInfo(@RequestBody UserInfoQO userInfoQO) {
        map.put(userInfoQO.getId(), userInfoQO.getPhone());
        Cache.put(userInfoQO.getId().toString(), userInfoQO.getPhone());

        System.out.println(Cache.map);
        return CommonResult.success(map);
    }

}
