package com.powersi.dao;

import com.powersi.entity.CaseCenter;
import com.powersi.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface PersonMapper {

    List<CaseCenter> getAllCase(Map map);

    CaseCenter getCaseInfoById(Long id);

    User getUserInfoById(Long id);
}
