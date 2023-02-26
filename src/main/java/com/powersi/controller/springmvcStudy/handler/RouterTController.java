//package com.powersi.controller.springmvc_study.handler;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.function.*;
//
//@Configuration
//public class RouterTController {
//    @Bean("tcontroller")
//    RouterFunction<ServerResponse> home() {
//        return RouterFunctions.route(RequestPredicates.GET("/hello"), request -> {
//            System.out.println("-----hello world-");
//            return ServerResponse.ok().body("hello world");
//        });
//    }
//}
