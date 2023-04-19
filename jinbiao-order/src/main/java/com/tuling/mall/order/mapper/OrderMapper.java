package com.tuling.mall.order.mapper;

import com.jinbiao.cloud.mbg.model.TOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Fox
 */

@Repository
public interface OrderMapper {
    
    /**
     * 保存订单
     * @param record
     * @return
     */
    @Insert("INSERT INTO t_order (id,user_id, product_id, count, status, money) VALUES (#{id},#{userId}, #{productId}, #{count}, #{status}, #{money})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(TOrder record);
    
    /**
     * 更新订单状态
     * @param id
     * @param status
     * @return
     */
    @Update("UPDATE t_order SET status = #{status} WHERE id = #{id}")
    int updateOrderStatus(@Param("id") Long id, @Param("status") int status);
    
}
