package com.jinbiao.designPatterns.dutyChain;

import java.util.List;

public interface FilterChain {

    /**
     * 维护所有责任处理器
     * @return
     */
    List<AbstractHandler> getFilters();

    /**
     * 责任处理器执行逻辑
     */
    void doFilter();
}
