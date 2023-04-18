package com.tuling.mall.order.rpc;

import com.jinbiao.cloud.common.entity.CommonResult;
import feign.hystrix.FallbackFactory;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Fox
 */
@Component
@Slf4j
public class FallbackAccountFeignServiceFactory implements FallbackFactory<AccountFeignService> {
    @Override
    public AccountFeignService create(Throwable throwable) {

        return new AccountFeignService() {
            @Override
            public Boolean debit(Integer userId, int money) {
                log.info("账户服务异常降级了");
                // 解决 feign整合sentinel降级导致Seata失效的处理  此方案不可取
                //
//                if(!StringUtils.isEmpty(RootContext.getXID())){
//                    //通过xid获取GlobalTransaction调用rollback回滚
                //  可以让库存服务回滚  能解决问题吗？  绝对不能用
//                    GlobalTransactionContext.reload(RootContext.getXID()).rollback();
//                }
                return false;
            }
        };
    }
}