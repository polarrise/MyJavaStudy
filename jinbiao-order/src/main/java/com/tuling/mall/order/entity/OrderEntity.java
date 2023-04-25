package com.tuling.mall.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 *
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:46:19
 */
@Data
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String userId;
	/**
	 * 商品编号
	 */
	private String productId;

	private Integer count;

	private BigDecimal money;

}