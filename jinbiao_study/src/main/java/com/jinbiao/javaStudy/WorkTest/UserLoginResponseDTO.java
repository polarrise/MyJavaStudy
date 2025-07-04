package com.jinbiao.javaStudy.WorkTest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
/**
 * @author: rise
 * @date: 2025-02-13 17:05:17
 * @Description: json对象转java对象
 * @Copyright: www.xshopee.com All rights reserved.
 * 注意：本内容仅限于深圳市多米诺跨境电商有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Data
public class UserLoginResponseDTO {

    @JsonProperty("CODE")
    private String code;

    @JsonProperty("MSG")
    private String msg;

    @JsonProperty("DATA")
    private LoginInfo data;  // `LoginInfo` 作为 `UserLoginResponseDTO` 的内部类

    // Lombok 会自动生成 getter、setter、toString 等方法

    @Data
    public static class LoginInfo {  // `LoginInfo` 类作为 `UserLoginResponseDTO` 的内部类
        @JsonProperty("umno")
        private String umno;

        @JsonProperty("loginTime")
        private String loginTime;

        @JsonProperty("session")
        private String session;
    }

    public static void main(String[] args) {
        // 示例 JSON 字符串
        String json = "{\"CODE\":\"00\",\"MSG\":\"SUCCESS\",\"DATA\":{\"umno\":\"123\",\"loginTime\":\"2020-01-01 11:11:11\",\"session\":\"asda\"}}";

        try {
            // 创建 ObjectMapper 实例
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 JSON 转换为 UserLoginResponseDTO 对象
            UserLoginResponseDTO response = objectMapper.readValue(json, UserLoginResponseDTO.class);

            // 打印转换后的对象
            System.out.println(response);

            // 打印 LoginInfo 的 loginTime 格式化后的值
            System.out.println("Login Time: " + response.getData().getLoginTime());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

