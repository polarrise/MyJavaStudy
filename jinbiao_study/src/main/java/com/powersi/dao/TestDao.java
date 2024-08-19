package com.powersi.dao;

import com.powersi.entity.CaseCenter;
import com.powersi.entity.User;
import com.powersi.qo.UserInfoQO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestDao {

   int insertTest();

   int insertTest1();

   int insertTest2();

   int insertTest3();

}
