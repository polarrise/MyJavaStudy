package com.powersi.model;

import lombok.Data;

@Data
public class RequestHeader {

    private String sign;

    private Long timestamp;

    private String nonceStr;

    private String appId;
}
