package com.tuling.mall.user.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:53:24
 */
@Data
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String username;

	private Integer age;

}
