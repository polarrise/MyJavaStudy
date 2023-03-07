package com.jinbiao.HelperClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author wangjinbiao
 * @date 2022/12/9$ 15:20$
 * @desc
 */
public class ListTest {

  static class User{
     private Integer id;
     private String name;
     private String createTime;

    public User(Integer id, String name, String createTime) {
      this.id = id;
      this.name = name;
      this.createTime = createTime;
    }

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getCreateTime() {
      return createTime;
    }

    public void setCreateTime(String createTime) {
      this.createTime = createTime;
    }

    @Override
    public String toString() {
      return "User{" +
              "id=" + id +
              ", name=" + name +
              ", createTime='" + createTime + '\'' +
              '}';
    }
  }

  /**
   * list对象集合，如何根据属性去重，保留最新时间的数据吗？
   * 根据对象属性中的时间来判断，时间是字符串类型createdtime
   */
  public static void test(){
    List<User> users = new ArrayList<>();
    users.add(new User( 1, "张三", "2023-03-03 19:30:00"));
    users.add(new User( 2, "李四", "2023-03-03 19:13:00"));
    users.add(new User( 3, "张三", "2023-03-03 19:31:00"));
    users.add(new User( 4, "王五", "2023-03-03 19:21:00"));
    users.add(new User( 5, "张三", "2023-03-03 19:33:00"));
    List<User> distinctUsers = users.stream()
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
            .values()
            .stream()
            .collect(Collectors.toList());
    System.out.println(users);
    System.out.println(distinctUsers);
  }

  public static void main(String[] args) {
    List<String> list1 = new ArrayList<String>();
    list1.add("1");
    list1.add("2");
    list1.add("3");
    list1.add("5");
    list1.add("6");

    List<String> list2 = new ArrayList<String>();
    list2.add("2");
    list2.add("3");
    list2.add("7");
    list2.add("8");

    // 交集
    List<String> intersection = list1.stream().filter(item -> list2.contains(item)).collect(toList());
    System.out.println("---交集 intersection---");
    intersection.parallelStream().forEach(System.out :: println);

    // 差集 (list1 - list2)
    List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(toList());
    System.out.println("---差集 reduce1 (list1 - list2)---");
    reduce1.parallelStream().forEach(System.out :: println);

    // 并集
    List<String> listAll = list1.parallelStream().collect(toList());
    List<String> listAll2 = list2.parallelStream().collect(toList());
    listAll.addAll(listAll2);
    System.out.println("---并集 listAll---");
    listAll.parallelStream().forEachOrdered(System.out :: println);

    // 去重并集
    List<String> listAllDistinct = listAll.stream().distinct().collect(toList());
    System.out.println("---得到去重并集 listAllDistinct---");
    listAllDistinct.parallelStream().forEachOrdered(System.out :: println);

    System.out.println("---原来的List1---");
    list1.parallelStream().forEachOrdered(System.out :: println);
    System.out.println("---原来的List2---");
    list2.parallelStream().forEachOrdered(System.out :: println);


    ListTest.test();

  }

}
