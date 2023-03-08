package com.jinbiao.cloud.im.dao;

import com.jinbiao.cloud.common.vo.XiaofaUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface XiaofaUserMapper {

    List<XiaofaUser> getXiaofaUsers(Map map);

}
