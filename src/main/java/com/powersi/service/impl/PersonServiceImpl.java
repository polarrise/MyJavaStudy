package com.powersi.service.impl;

import com.powersi.entity.PersonDo;
import com.powersi.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author com.jinbiao
 * @date 2021/11/24
 * @apiNote
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public List<PersonDo> getAllPerson(Map map) {
        List list=new ArrayList();
        list.add(new PersonDo(1,"jack"));
        list.add(new PersonDo(2,"rise"));
        return list;
    }
}
