package com.powersi.service;


import com.powersi.entity.PersonDo;

import java.util.List;
import java.util.Map;

public interface PersonService {
    List<PersonDo> getAllPerson(Map map);
}
