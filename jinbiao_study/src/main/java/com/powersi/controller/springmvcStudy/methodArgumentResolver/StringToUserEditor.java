package com.powersi.controller.springmvcStudy.methodArgumentResolver;


import com.powersi.controller.springmvcStudy.methodParam.User;

import java.beans.PropertyEditorSupport;

public class StringToUserEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println("StringToUserEditor方法参数解析器执行==");
        User user = new User(text,"123456","QQ");
        this.setValue(user);
    }
}
