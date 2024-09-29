package com.jinbiao.designPatterns.dutyChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpecificHandler2 extends AbstractHandler{

    @Override
    protected Boolean matched() {
        log.info("责任处理器2不满足条件,不执行处理器...");
        return false;
    }

    @Override
    protected void handler() {
        log.info("责任处理器2执行...");
    }
}
