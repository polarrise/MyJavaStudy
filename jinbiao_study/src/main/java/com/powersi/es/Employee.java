package com.powersi.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author：Jinbiao
 * @Date：2023/7/28 22:24
 * @Desc：
 */
@Data
@AllArgsConstructor
@Document(indexName = "employees")
public class Employee {
    @Id
    private Long id;
    @Field(type= FieldType.Keyword)
    private String name;
    private int sex;
    private int age;
    @Field(type= FieldType.Text,analyzer="ik_max_word")
    private String address;
    private String remark;
}
