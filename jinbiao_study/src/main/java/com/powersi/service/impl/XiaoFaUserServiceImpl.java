package com.powersi.service.impl;

import com.powersi.controller.springmvcStudy.uploadDownload.XiaofaUser;
import com.powersi.dao.XiaofaUserDao;
import com.powersi.service.XiaoFaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class XiaoFaUserServiceImpl implements XiaoFaUserService {

    @Autowired
    private XiaofaUserDao xiaofaUserDao;

    @Override
    public List<XiaofaUser> getXiaofaUserList(Map map) {
        return xiaofaUserDao.getXiaofaUsers(map);
    }
}
