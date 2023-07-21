package com.jinbiao.javaStudy.spi;

import java.util.ServiceLoader;

/**
 * @Author：Jinbiao
 * @Date：2023/5/26 21:49
 * @Desc：SPI全名Service Provider interface，翻译过来就是“服务提供接口”,再说简单就是提供某一个服务的接口， 提供给服务开发者或者服务生产商来进行实现。
 * API 全称Application Programming Interface， 翻译为“应用程序接口”，指的是应用程序为外部提供服务的接口
 *
 * 简而言之:
 * API:服务提供方提供接口,调用方进行调用
 * SPI:接口是由服务调用方提供,并且由服务提供方进行实现时,服务调用方就可以根据自己的需要选择特定实现，而不用更改业务代码以获取相应的功能，这个就是SPI
 * 在这demo里面就是,SpiTest类作为调用放，提供一个接口Animal,但是它的实现是由服务方提供的,Cat类的实现和Dog类的实现，我调用方SpiTest可以选择特定的实现
 */
public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<Animal> load = ServiceLoader.load(Animal.class);
        for (Animal animal:load ) {
            animal.say();
        }
    }
}
