package com.jinbiao.designPatterns.dutyChain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DutyFilterChain implements FilterChain{

    private List<AbstractHandler> filters = new ArrayList<>();

    @Override
    public List<AbstractHandler> getFilters() {
        return filters;
    }

    @Override
    public void doFilter() {

        for (AbstractHandler handler : getFilters()){
            if(!isCurrentHandlerDuty(handler,AbstractHandler::matched)){
                continue;
            }
            handleCurrentDuty(handler,AbstractHandler::handler);
        }
    }

    /**
     * 添加链路的处理器
     * @param handler
     * @return
     */
    public DutyFilterChain addFilter(AbstractHandler handler){
        filters.add(handler);
        return this;
    }

    /**
     * 是当前处理器的责任?
     * @param handler
     * @param predicate
     * @return
     */
    private Boolean isCurrentHandlerDuty(AbstractHandler handler, Predicate<AbstractHandler> predicate){
        return predicate.test(handler);
    }

    /**
     * 执行处理器的责任
     * @param handler
     * @param consumer
     */
    private void handleCurrentDuty(AbstractHandler handler, Consumer<AbstractHandler> consumer){
        consumer.accept(handler);
    }
}
