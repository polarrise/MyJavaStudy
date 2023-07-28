package com.powersi.controller;

import com.alibaba.fastjson.JSONObject;
import com.powersi.common.api.CommonResult;
import com.powersi.es.Employee;
import com.powersi.es.EmployeeRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author：Jinbiao
 * @Date：2023/7/28 22:26
 * @Desc：
 */
@RestController
@RequestMapping("/testES")
@Validated
@Slf4j
public class EsTestController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @RequestMapping("testCreateIndex")
    public CommonResult testCreateIndex() {
        Employee employee = new Employee(1L,"fox666",1,32,"长沙麓谷","java architect");
        //插入文档
        employeeRepository.save(employee);

        //根据id查询
        Optional<Employee> result = employeeRepository.findById(1L);

        //根据name查询
        List<Employee> list = employeeRepository.findByName("fox666");
        return CommonResult.success(list.get(0));
    }

    @RequestMapping("testCreateIndex2")
    public CommonResult testCreateIndex2(){
        //创建索引
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(IndexCoordinates.of("employee_index"));
        if (indexOperations.exists()) {
            log.info("索引已经存在");
            return CommonResult.success("索引：employee_index已经存在");
        }else {
            //创建索引
            indexOperations.create();
            return CommonResult.success("索引：employee_index创建成功");
        }
    }

    @RequestMapping("testDeleteIndex")
    public CommonResult testDeleteIndex(){
        //删除索引
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(IndexCoordinates.of("employee_index"));
        indexOperations.delete();
        return CommonResult.success("删除索引：employee_index成功");
    }

    @RequestMapping("testQueryDocument")
    public CommonResult testQueryDocument(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //查询
        builder.withQuery(QueryBuilders.matchQuery("address","公园"));
        // 设置分页信息
        builder.withPageable(PageRequest.of(0, 5));
        // 设置排序
        builder.withSort(SortBuilders.fieldSort("age").order(SortOrder.DESC));

        SearchHits<Employee> search = elasticsearchRestTemplate.search(builder.build(), Employee.class);
        List<org.springframework.data.elasticsearch.core.SearchHit<Employee>> searchHits = search.getSearchHits();
        for (SearchHit<Employee> hit: searchHits){
            log.info("返回结果："+hit.toString());
        }
        return CommonResult.success(search);
    }


    @RequestMapping("testInsertBatch")
    public CommonResult testInsertBatch(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(2L,"张三",1,25,"广州天河公园","java developer"));
        employees.add(new Employee(3L,"李四",1,28,"广州荔湾大厦","java assistant"));
        employees.add(new Employee(4L,"小红",0,26,"广州白云山公园","php developer"));

        List<IndexQuery> queries = new ArrayList<>();
        for (Employee employee : employees) {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(employee.getId().toString());
            String json = JSONObject.toJSONString(employee);
            indexQuery.setSource(json);
            queries.add(indexQuery);
        }
        //bulk批量插入
        elasticsearchRestTemplate.bulkIndex(queries,IndexCoordinates.of("employee_batch"));
        return CommonResult.success("bulk批量插入索引：employee_batch 成功 ");
    }

    /**
     * GET /employee_batch/_search
     */
}
