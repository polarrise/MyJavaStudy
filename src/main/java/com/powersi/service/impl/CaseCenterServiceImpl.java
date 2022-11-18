package com.powersi.service.impl;

import com.powersi.dao.PersonMapper;
import com.powersi.entity.CaseCenter;
import com.powersi.service.CaseCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author com.jinbiao
 * @date 2021/11/24
 * @apiNote
 */
@Service
public class CaseCenterServiceImpl implements CaseCenterService {
  @Autowired
  PersonMapper personMapper;
    @Override
    public List<CaseCenter> getAllCase(Map map) {
        return personMapper.getAllCase(map);
    }
}
