package com.jinbiao.cloud.mbg.mapper;

import com.jinbiao.cloud.mbg.model.Stock;
import com.jinbiao.cloud.mbg.model.StockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface StockMapper {
    long countByExample(StockExample example);

    int deleteByExample(StockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Stock record);

    int insertSelective(Stock record);

    List<Stock> selectByExample(StockExample example);

    Stock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByExample(@Param("record") Stock record, @Param("example") StockExample example);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

    /**
     * 获取库存
     * @param productId 商品编号
     * @return
     */
    @Select("SELECT id,product_id,count FROM stock WHERE product_id = #{productId}")
    Stock findByProductId(@Param("productId") String productId);

    /**
     * 扣减库存
     * @param productId 商品编号
     * @param count  扣减数量
     * @return
     */
    @Update("UPDATE stock SET count = count - #{count} WHERE product_id = #{productId}")
    int reduceStorage(@Param("productId") Integer productId, @Param("count") Integer count);

    /**
     * 冻结库存  try: 库存-扣减数量，冻结库存+扣减数量
     * @param productId 商品编号
     * @param count  扣减数量
     * @return
     */
    @Update("UPDATE stock SET count = count - #{count},freeze_count=freeze_count+#{count} WHERE product_id = #{productId}")
    int freezeStorage(@Param("productId") String productId,@Param("count") Integer count);

    /**
     * 扣减冻结的库存（真正的扣减库存）   confirm： 冻结库存-扣减数量
     * @param productId 商品编号
     * @param count 扣减数量
     * @return
     */
    @Update("UPDATE stock SET freeze_count=freeze_count-#{count} WHERE product_id = #{productId}")
    int reduceFreezeStorage(@Param("productId") String productId,@Param("count") Integer count);

    /**
     * 解冻库存  cancel: 库存+扣减数量，冻结库存-扣减数量
     * @param productId  商品编号
     * @param count  扣减数量
     * @return
     */
    @Update("UPDATE stock SET count = count + #{count},freeze_count=freeze_count-#{count} WHERE product_id = #{productId}")
    int unfreezeStorage(@Param("productId") String productId,@Param("count") Integer count);
}