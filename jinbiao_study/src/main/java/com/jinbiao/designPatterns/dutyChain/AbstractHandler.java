package com.jinbiao.designPatterns.dutyChain;

public abstract class AbstractHandler {

    /**
     * 责任处理器是否满足执行条件
     * @return
     */
    protected abstract Boolean matched();

    /**
     * 执行责任.
     */
    protected abstract void handler();
}
