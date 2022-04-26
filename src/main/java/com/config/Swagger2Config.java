package com.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author xiebq
 *
 */
@Configuration  //@Configuration 用于指定当前类是一个 spring 配置类，当创建容器时会从该类上加载注解，value 属性用于指定配置类的字节码。
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true")
public class Swagger2Config {

    @Bean
    public Docket tenderApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //api的相关描述信息，通常显示在页面的最上方
                .apiInfo(apiInfo("SpringBoot测试工程", "SpringBoot测试工程API"))
                .select()
                //设置扫描的包
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("com.powersi"))
                //设置扫描哪些controller，这里设置扫描全部，可以传入正则表达式
                .paths(PathSelectors.any())
                .build()
                .groupName("SpringBoot测试工程API");
    }
    private static ApiInfo apiInfo(String title, String desc) {
        return new ApiInfoBuilder()
                .title(title)
                .description(desc)
                .build();
    }

//    @Bean
//    public Docket createRestApi() {
//        List<Parameter> pars = new ArrayList<Parameter>();
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                .paths(PathSelectors.any())
//                .build()
//                .globalOperationParameters(pars)
//                .apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("拾花酿春 RESTful API")
//                .description("展示先做基础功能，后面再添加业务")
//                .termsOfServiceUrl("https://www.cnblogs.com/xiebq/")
//                .version("1.0")
//                .build();
//    }

}
