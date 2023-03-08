package com.jinbiao.cloud.security.domain;

import lombok.Data;

/**
 * @author Jinbiao
 */
@Data
public class UserDetails {

    private Long userId;
    private String nickname;
    private String phone;
    private String cardName;
    private String cardExplain;
    private String validDate;

}
