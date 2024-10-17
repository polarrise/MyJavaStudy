package com.jinbiao.HelperClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * @author wangjinbiao
 * @date 2024/09/29 11:10
 * @desc Map常用操作类
 */
@Slf4j
public class MapUtil {
  private static final List<Product> productList;
  private static HashMap<String, Integer> initMap;
  static {
    // 使用此方法为map赋值
    initMap = new HashMap<String, Integer>() {
      {
        put("a", 1);
        put("b", 2);
        put("c", 3);
      }
    };
    productList = new ArrayList<Product>() {
      {
        add(new Product("苹果", 5.2d));
        add(new Product("苹果", 8.0d));
        add(new Product("榴莲", 20.0d));
        add(new Product("榴莲", 25.0d));
        add(new Product( "橘子", 3.3d));
      }
    };
  }

  /**
   * 对List集合分组后取第一个Product对象
   */
  private static void groupAndGetFirst(){
    Map<String, Product> productMap = productList.stream().collect(
            groupingBy(Product::getName, collectingAndThen(toList(), value -> value.get(0)))
    );
    productMap.entrySet().forEach(a->log.info("根据名称分组后保留的第一个Product:{}",a.getValue()));
  }

  public static void main(String[] args) {
      groupAndGetFirst();
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  @Accessors(chain = true)
  static class Product{
    private String name;
    private Double price;
  }
}
