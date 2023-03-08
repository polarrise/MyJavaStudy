package com.powersi.dao;

import com.powersi.controller.springmvcStudy.uploadDownload.XiaofaUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface XiaofaUserDao {

    List<XiaofaUser> getXiaofaUsers(Map map);

}
