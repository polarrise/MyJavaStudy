package com.tuling.mall.user.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.jinbiao.cloud.common.entity.CommonResult;
import com.tuling.mall.user.entity.UserEntity;
import com.tuling.mall.user.rpc.OrderFeignService;
import com.tuling.mall.user.service.UserService;
import com.tuling.mall.user.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/sentinel")
public class SentinelController {

    private static final String RESOURCE_NAME = "HelloWorld";

    @Autowired
    OrderFeignService orderFeignService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 基于API实现
     * @return
     */
    @RequestMapping(value = "/hello")
    public CommonResult<String> hello() {
        try (Entry entry = SphU.entry(RESOURCE_NAME)) {
            // 被保护的逻辑
            log.info("hello world");
            return CommonResult.success("hello world");
        } catch (BlockException ex) {
            // 处理被流控的逻辑
            log.info("blocked!");
            return  CommonResult.success("被流控了");
        }

    }

    /**
     * 定义流控规则
     */
    @PostConstruct
    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        //设置受保护的资源
        rule.setResource(RESOURCE_NAME);
        // 设置流控规则 QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置受保护的资源阈值
        rule.setCount(1);
        rules.add(rule);
        // 加载配置好的规则
        FlowRuleManager.loadRules(rules);
    }

    /**
     * @SentinelResource注解实现,***注意:要生效需要配置切面支持：配置类装配SentinelResourceAspect
     * @SentinelResource 注解用来标识资源是否被限流、降级。
     * blockHandler:  定义当资源内部发生了BlockException应该进入的方法（捕获的是Sentinel定义的异常）
     * fallback:  定义的是资源内部发生了Throwable应该进入的方法
     * exceptionsToIgnore：配置fallback可以忽略的异常
     * @return
     */
    @SentinelResource(value = RESOURCE_NAME, blockHandler = "handleException", fallback = "fallbackException")
    @RequestMapping("/hello2")
    public CommonResult<String> hello2() {

        int i = 1 / 0;

        return CommonResult.success("hello world");
    }

    public CommonResult<String> handleException(BlockException ex){
        return CommonResult.success("被流控了");
    }

    public CommonResult<String> fallbackException(Throwable t){
        return CommonResult.success("被异常降级了");
    }


    @RequestMapping(value = "/findOrderByUserId/{id}")
    @SentinelResource(value = "findOrderByUserId",
            fallback = "fallback",fallbackClass = ExceptionUtil.class,
            blockHandler = "handleException",blockHandlerClass = ExceptionUtil.class
    )
    public CommonResult findOrderByUserId(@PathVariable("id") Integer id) {
        //ribbon实现
        String url = "http://jinbiao-order/jinbiao-order/order/findOrderByUserId/"+id;
        CommonResult result = restTemplate.getForObject(url,CommonResult.class);

        if(id==4){
            throw new IllegalArgumentException("非法参数异常");
        }

        return result;
    }

    @RequestMapping("/info/{id}")
    @SentinelResource(value = "userinfo", blockHandler = "handleException")
    public CommonResult info(@PathVariable("id") Integer id){
        UserEntity user = userService.getById(id);

        if(id==4){
            throw new IllegalArgumentException("异常参数");
        }

        return CommonResult.success(user);
    }

}