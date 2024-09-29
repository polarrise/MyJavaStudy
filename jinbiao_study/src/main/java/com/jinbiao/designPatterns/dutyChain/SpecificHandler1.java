package com.jinbiao.designPatterns.dutyChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpecificHandler1 extends AbstractHandler{

    @Override
    protected Boolean matched() {
        log.info("责任处理器1满足条件...");
        return true;
    }

    @Override
    protected void handler() {
        log.info("责任处理器1执行...");
    }
}
