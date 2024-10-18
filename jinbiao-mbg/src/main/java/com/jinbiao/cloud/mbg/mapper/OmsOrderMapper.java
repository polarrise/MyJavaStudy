package com.jinbiao.cloud.mbg.mapper;

import com.jinbiao.cloud.mbg.model.OmsOrder;
import com.jinbiao.cloud.mbg.model.OmsOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OmsOrderMapper {

    int insert(OmsOrder record);


    List<OmsOrder> selectByExample(OmsOrderExample example);


    int updateByExampleSelective(@Param("record") OmsOrder record, @Param("example") OmsOrderExample example);

}