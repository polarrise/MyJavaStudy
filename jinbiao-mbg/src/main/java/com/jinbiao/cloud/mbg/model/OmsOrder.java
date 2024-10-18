package com.jinbiao.cloud.mbg.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class OmsOrder implements Serializable {
    private Long memberId;

    private String receivedAddress;

    private String receivedName;

    private Integer productId;

    private Integer version;


}