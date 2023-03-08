package com.jinbiao.cloud.mbg.mapper;

import com.jinbiao.cloud.mbg.model.CaseCenter;
import com.jinbiao.cloud.mbg.model.CaseCenterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CaseCenterMapper {
    long countByExample(CaseCenterExample example);

    int deleteByExample(CaseCenterExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CaseCenter record);

    int insertSelective(CaseCenter record);

    List<CaseCenter> selectByExampleWithBLOBs(CaseCenterExample example);

    List<CaseCenter> selectByExample(CaseCenterExample example);

    CaseCenter selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CaseCenter record, @Param("example") CaseCenterExample example);

    int updateByExampleWithBLOBs(@Param("record") CaseCenter record, @Param("example") CaseCenterExample example);

    int updateByExample(@Param("record") CaseCenter record, @Param("example") CaseCenterExample example);

    int updateByPrimaryKeySelective(CaseCenter record);

    int updateByPrimaryKeyWithBLOBs(CaseCenter record);

    int updateByPrimaryKey(CaseCenter record);
}