package com.powersi.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author：Jinbiao
 * @Date：2023/7/28 22:25
 * @Desc：
 */
@Repository
public interface EmployeeRepository extends ElasticsearchRepository<Employee, Long> {
    List<Employee> findByName(String name);

}
