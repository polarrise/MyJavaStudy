package com.jinbiao.spring_study.dao;

import com.powersi.entity.CaseCenter;
import com.powersi.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    List<CaseCenter> getAllCase(Map map);

    CaseCenter getCaseInfoById(Long id);

    User getUserInfoById(Long id);
}
