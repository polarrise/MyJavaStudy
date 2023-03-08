package com.jinbiao.cloud.common.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjinbiao
 * @date 2023/1/4$ 10:54$
 * @desc
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户信息")
public class User {

  private Long id;

  private String nickName;

  private String phone;

  private String source;
}
