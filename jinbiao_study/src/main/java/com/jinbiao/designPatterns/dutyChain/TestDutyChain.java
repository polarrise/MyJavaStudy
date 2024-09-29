package com.jinbiao.designPatterns.dutyChain;

public class TestDutyChain {
    public static void main(String[] args) {
        DutyFilterChain filterChain = new DutyFilterChain();
        filterChain.addFilter(new SpecificHandler1())
                   .addFilter(new SpecificHandler2())
                   .addFilter(new SpecificHandler3())
                   .doFilter();
    }
}
