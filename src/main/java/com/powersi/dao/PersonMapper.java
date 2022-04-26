package com.powersi.dao;

import com.powersi.entity.PersonDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonMapper {
    List<PersonDo> getAllPerson(Map map);
}
