package com.powersi.controller;


import com.powersi.common.api.CommonResult;
import com.powersi.entity.CaseCenter;
import com.powersi.service.CaseCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/caseCenterApi")
@Api(value = "CaseCenter类Api文档",tags={"查询CaseCenterController"})
public class CaseCenterController {
    @Autowired
    private CaseCenterService caseCenterService;

    @RequestMapping("caseCenter")
    @ApiOperation(value="根据ID查询Person", notes="")
    public CommonResult<List<CaseCenter>> getAllCase(@RequestBody Map<String,Object> map){
        return CommonResult.success(caseCenterService.getAllCase(map));
    }

    @GetMapping("getCaseInfoById")
    @ApiOperation(value="查询案情详情", notes="查询案情详情")
    public CommonResult<Map> getCaseInfoById(Long id) throws ExecutionException, InterruptedException {
        return CommonResult.success(caseCenterService.getCaseInfoById(id));
    }

    @GetMapping("testFuture")
    @ApiOperation(value="查询案情详情", notes="查询案情详情")
    public CommonResult<Map> testFuture(Long id) throws ExecutionException, InterruptedException {
        return CommonResult.success(caseCenterService.testFuture(id));
    }

    @GetMapping("testTaskIfFinished")
    @ApiOperation(value="测试线程池任务是否执行完成", notes="测试线程池任务是否执行完成")
    public CommonResult<Map> testTaskIfFinished(Long id) throws ExecutionException, InterruptedException {
        return CommonResult.success(caseCenterService.testTaskIfFinished(id));
    }

    @GetMapping("testCompletableFuture")
    @ApiOperation(value="查询案情详情", notes="查询案情详情")
    public CommonResult<Map> testCompletableFuture(Long id) throws Exception {
        return CommonResult.success(caseCenterService.testCompletableFuture(id));
    }

}
