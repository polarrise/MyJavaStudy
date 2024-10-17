package com.jinbiao.HelperClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author wangjinbiao
 * @date 2024/09/29 11:10
 * @desc List常用操作类
 */
public class ListUtil {

  private static final List<User> users;
  private static final List<Integer> list1;
  private static final List<Integer> list2;
  static {
    users = new ArrayList<User>() {
      {
        add(new User( 1, "张三", "2023-03-03 19:30:00"));
        add(new User( 2, "李四", "2023-03-03 19:13:00"));
        add(new User( 3, "张三", "2023-03-03 19:31:00"));
        add(new User( 4, "王五", "2023-03-03 19:21:00"));
        add(new User( 5, "张三", "2023-03-03 19:33:00"));
      }
    };
    list1 = new ArrayList<Integer>(){
      {
        add(1);
        add(2);
        add(3);
        add(5);
        add(6);
      }
    };
    list2 = new ArrayList<Integer>(){
      {
        add(2);
        add(3);
        add(7);
        add(8);
      }
    };
  }

  /**
   * list对象集合，如何根据属性去重，保留最新时间的数据吗？
   * 根据对象属性中的时间来判断，时间是字符串类型createdtime
   */
  public static void getDistinctedNewest(){
    List<User> distinctUsers = new ArrayList<>(users.stream()
            .collect(Collectors.toMap(
                    User::getName,
                    user -> user,
                    (existingUser, newUser) -> {
                        if (newUser.getCreateTime().compareTo(existingUser.getCreateTime()) > 0) {
                            return newUser;
                        } else {
                            return existingUser;
                        }
                    }))
            .values());
    System.out.println(users);
    System.out.println(distinctUsers);
  }

  /**
   * 获取交集
   */
  public static void getIntersection(){
    List<Integer> intersection = list1.stream().filter(item -> list2.contains(item)).collect(toList());
    System.out.println("---交集 intersection---");
    intersection.parallelStream().forEach(System.out :: println);
  }

  /**
   * 获取差集 (list1 - list2)
   */
  public static void getDifference(){
    List<Integer> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(toList());
    System.out.println("---差集(list1 - list2)---");
    reduce1.parallelStream().forEach(System.out :: println);
  }

  /**
   * 获取并集(去重)
   */
  public static void getDistincteUnion(){
    list1.addAll(list2);
    List<Integer> result = list1.stream().distinct().sorted().collect(toList());
    System.out.println("---去重后并集---");
    result.parallelStream().forEach(System.out :: println);
  }


  public static void main(String[] args) {
    // 交集
    getIntersection();
    // 差集
    getDifference();
    // 去重后并集
    getDistincteUnion();

    // 根据对象属性去重保留最新时间的数据
    ListUtil.getDistinctedNewest();

  }

  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  @Accessors(chain = true)
  static class User{
    private Integer id;
    private String name;
    private String createTime;
  }
}
