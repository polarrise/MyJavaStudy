package com.powersi.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.powersi.service.TestService;


@Service(version = "1.0.0",interfaceClass = TestService.class)
public class TestServiceImpl implements TestService{
    @Override
    public String ins() {
        return "insert";
    }

    @Override
    public String del() {
        return "del";
    }

    @Override
    public String upd() {
        return "upd";
    }

    @Override
    public String sel() {
        return "sel";
    }

}
