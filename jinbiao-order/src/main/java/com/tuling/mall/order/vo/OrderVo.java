package com.tuling.mall.order.vo;

import lombok.Data;

/**
 * @author Fox
 */
@Data
public class OrderVo {
    private Integer userId;
    /**商品编号**/
    private Integer productId;
    
    private Integer count;
    
    private Integer money;
}
