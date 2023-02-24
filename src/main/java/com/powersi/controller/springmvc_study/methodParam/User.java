package com.powersi.controller.springmvc_study.methodParam;

import lombok.Data;

@Data
public class User {

    private String username;

    private String password;

    private String phone;

    private String email;

    private String source;

    public User(String username, String password, String source) {
        this.username = username;
        this.password = password;
        this.source = source;
    }
}
