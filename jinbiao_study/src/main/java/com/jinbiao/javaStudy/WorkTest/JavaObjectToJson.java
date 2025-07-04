package com.jinbiao.javaStudy.WorkTest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author: rise
 * @date: 2024-12-10 16:28:58
 * @Description: java对象转json转义问题
 * @Copyright: www.xshopee.com All rights reserved.
 * 注意：本内容仅限于深圳市多米诺跨境电商有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Slf4j
public class JavaObjectToJson {
    public static void main(String[] args) throws JsonProcessingException {
        // 创建一个 Person 对象，name 字段包含换行符
        Person person = new Person("\\t11", 20);

        log.info("name:"+person.getName());

        // 配置 ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        // 禁用对特殊字符的转义
        objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false);

        // 使用 Jackson 将 Person 对象转换为 JSON 字符串
        String jsonString = objectMapper.writeValueAsString(person);

        // 输出 JSON 字符串
        System.out.println(jsonString);
    }

    @AllArgsConstructor
    static class Person {
        @JsonSerialize(using = NameSerializer.class)
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}

class NameSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // 直接写入原始值
        // 处理所有常见的转义字符
        String result = value
                .replace("\\n", "\n")  // 换行符
                .replace("\\t", "\t")  // 制表符
                .replace("\\r", "\r")  // 回车符
                .replace("\\b", "\b")  // 退格符
                .replace("\\f", "\f")  // 换页符
                .replace("\\\"", "\"") // 双引号
                .replace("\\\\", "\\"); // 反斜杠
        gen.writeString(result);
    }
}


