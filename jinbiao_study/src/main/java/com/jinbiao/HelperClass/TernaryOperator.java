package com.jinbiao.HelperClass;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: rise
 * @date: 2024-12-13 14:46:37
 * @Description: 三目运算符
 * @Copyright: www.xshopee.com All rights reserved.
 * 注意：本内容仅限于深圳市多米诺跨境电商有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Slf4j
public class TernaryOperator {

    public static void testMultiCondition1(Integer param1,Integer param2){
        if(1 == param1){
            if( 2== param2){
                log.info("执行业务逻辑2");
            }else {
                log.info("执行业务逻辑3");
            }
        }else{
            log.info("执行业务逻辑4");
        }
    }

    public static void testMultiCondition2(Integer param,Integer param2){
          log.info(1 == param ? 2 == param2 ? "执行业务逻辑2":"执行业务逻辑3":"执行业务逻辑4") ;
    }

    public static void testTernaryOperator(Integer param){
        log.info( 1==param ? "执行业务逻辑1" : (2 == param) ? "执行业务逻辑2" : "执行业务逻辑3");
    }

    public static void main(String[] args) {
        testMultiCondition1(2,3);
        testMultiCondition2(1,2);
        testTernaryOperator(1);
    }
}
