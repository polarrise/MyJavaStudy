package com.jinbiao.spring_study.transactionTest;

import org.apache.ibatis.annotations.Insert;

public interface UserDao {

    @Insert("insert into jinbiao_user values (1,'rise1','wang1234..','10086','小程序')")
    void insertUser();
}
