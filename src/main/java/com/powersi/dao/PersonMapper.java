package com.powersi.dao;

import com.powersi.entity.CaseCenter;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface PersonMapper {
    List<CaseCenter> getAllCase(Map map);
}
