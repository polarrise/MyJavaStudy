package com.powersi.qo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CaseQO {

  @NotNull(message = "id不能为空")
  private Long id;

  @NotBlank(message = "用户名不能为空")
  private String userName;

  @NotBlank(message = "密码不能为空")
  @Length(min = 6, max = 20,message = "密码长度需要在6和20之间")
  private String password;

  @NotBlank(message = "邮箱不能为空")
  @Email
  private String email;
}
