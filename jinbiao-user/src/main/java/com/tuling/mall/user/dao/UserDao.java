package com.tuling.mall.user.dao;

import com.tuling.mall.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from t_user where id=#{id}")
    UserEntity getById(Integer id);

}
