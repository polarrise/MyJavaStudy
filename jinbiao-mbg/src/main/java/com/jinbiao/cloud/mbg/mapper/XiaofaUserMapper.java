package com.jinbiao.cloud.mbg.mapper;

import com.jinbiao.cloud.mbg.model.XiaofaUser;
import com.jinbiao.cloud.mbg.model.XiaofaUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface XiaofaUserMapper {
    long countByExample(XiaofaUserExample example);

    int deleteByExample(XiaofaUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(XiaofaUser record);

    int insertSelective(XiaofaUser record);

    List<XiaofaUser> selectByExample(XiaofaUserExample example);

    XiaofaUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") XiaofaUser record, @Param("example") XiaofaUserExample example);

    int updateByExample(@Param("record") XiaofaUser record, @Param("example") XiaofaUserExample example);

    int updateByPrimaryKeySelective(XiaofaUser record);

    int updateByPrimaryKey(XiaofaUser record);
}