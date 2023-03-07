package com.powersi.dao;

import com.powersi.entity.CaseCenter;
import com.powersi.entity.User;
import com.powersi.qo.UserInfoQO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonMapper {

    List<CaseCenter> getAllCase(Map map);

    CaseCenter getCaseInfoById(Long id);

    User getUserInfoById(Long id);

    User getUserInfo(UserInfoQO userInfoQO);
}
