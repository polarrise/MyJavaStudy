package com.jinbiao.cloud.im.dao;


import com.jinbiao.cloud.common.vo.CaseCenter;
import com.jinbiao.cloud.common.vo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    List<CaseCenter> getAllCase(Map map);

    CaseCenter getCaseInfoById(Long id);

    User getUserInfoById(Long id);
}
