package com.powersi.controller;


import com.powersi.entity.PersonDo;
import com.powersi.service.PersonService;
import com.powersi.service.impl.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/testBoot")
@Api(value = "Person类Api文档",tags={"查询PersonController"})
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private PayService  payService;

    @RequestMapping("getAllPerson")
    @ApiOperation(value="根据ID查询Person", notes="")
    public List<PersonDo> getAllPerson(@RequestBody Map<String,Object> map){
        return personService.getAllPerson(map);
    }

}
