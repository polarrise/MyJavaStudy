package com.tuling.mall.user.util;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jinbiao.cloud.common.entity.CommonResult;

/**
 * @Author：Jinbiao
 * @Date：2023/4/26 14:56
 * @Desc：
 */
public class ExceptionUtil {
    public static CommonResult<String> fallback(Integer id, Throwable e){
        return CommonResult.success("===被异常降级啦===");
    }

    public static CommonResult<String> handleException(Integer id, BlockException e){
        return CommonResult.success("===被限流啦===");
    }

}
