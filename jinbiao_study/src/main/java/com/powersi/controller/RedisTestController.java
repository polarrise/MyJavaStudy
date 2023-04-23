package com.powersi.controller;

import com.powersi.common.api.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author：Jinbiao
 * @Date：2023/4/23 16:04
 * @Desc：Redis主从架构+哨兵模式 测试。
 */

@RestController
public class RedisTestController {

    private static final Logger logger = LoggerFactory.getLogger(RedisTestController.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 测试节点挂了哨兵重新选举新的master节点，客户端是否能动态感知到
     * 新的master选举出来后，哨兵会把消息发布出去，客户端实际上是实现了一个消息监听机制，
     * 当哨兵把新master的消息发布出去，客户端会立马感知到新master的信息，从而动态切换访问的masterip
     *
     * 测试A结果:当把服务器master节点kill掉之后，测试结果，在重新选举新的master节点途中会写不了。直到选举完，又恢复了，可以写了
     * @throws InterruptedException
     */
    @RequestMapping("/test_sentinel")
    public void testSentinel(){
        int i = 1;
        while (true){
            try {
                stringRedisTemplate.opsForValue().set("zhuge"+i, i+"");
                System.out.println("设置key："+ "zhuge" + i);
                i++;
                Thread.sleep(1000);
            }catch (Exception e){
                logger.error("错误：", e);
            }
        }
    }

    @RequestMapping("/testSentinelGetValue")
    public CommonResult<String> testSentinelGetValue() {
        return CommonResult.success(stringRedisTemplate.opsForValue().get("jinbiao"));
    }

    /**
     * 测试集群模式
     * @return
     */
    @RequestMapping("/test_cluster")
    public CommonResult<String>  testCluster() {
        //stringRedisTemplate.opsForValue().set("jinbiao", "666");
        return CommonResult.success(stringRedisTemplate.opsForValue().get("jinbiao"));
    }

}
