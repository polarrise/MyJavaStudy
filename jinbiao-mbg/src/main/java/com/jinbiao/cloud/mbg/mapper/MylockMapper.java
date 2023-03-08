package com.jinbiao.cloud.mbg.mapper;

import com.jinbiao.cloud.mbg.model.Mylock;
import com.jinbiao.cloud.mbg.model.MylockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MylockMapper {
    long countByExample(MylockExample example);

    int deleteByExample(MylockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Mylock record);

    int insertSelective(Mylock record);

    List<Mylock> selectByExample(MylockExample example);

    Mylock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Mylock record, @Param("example") MylockExample example);

    int updateByExample(@Param("record") Mylock record, @Param("example") MylockExample example);

    int updateByPrimaryKeySelective(Mylock record);

    int updateByPrimaryKey(Mylock record);
}