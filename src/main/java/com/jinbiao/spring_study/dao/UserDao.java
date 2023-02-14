package com.jinbiao.spring_study.dao;

import com.jinbiao.spring_study.service.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author WangJinbiao
 * @date 2023/2/14 23:58
 * @desc
 */
public interface UserDao {

    //编写sql语句
    @Select("select * from xiaofa_user")
    List<User> findAll();
}
