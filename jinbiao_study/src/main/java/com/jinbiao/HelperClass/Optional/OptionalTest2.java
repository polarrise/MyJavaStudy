package com.jinbiao.HelperClass.Optional;

import lombok.Data;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class OptionalTest2 {
    public User test(){
        User user=new User();
        if (Optional.ofNullable(user).isPresent()){
            System.out.println("User不为空");
            //写不为空的逻辑
            user.setName("Jinbiao");
            user.setAge(2);
            user.setFlag(true);
            User user1 = Optional.ofNullable(user).get();
            System.out.println(user1);
            return user1;
        }else{
            //写为空的逻辑
            System.out.println("User为空");
            return null;
        }
    }

    public User test2(){
        User user = new User("rise",24,true);
        Optional.ofNullable(user).map(p->{
            System.out.println("p不为空");
            p.setFlag(false);
            User user1 = Optional.ofNullable(p).get();
            System.out.println(user1);
            return p;
        }).orElseGet(()->new User());
        return user;
    }

    private User filterUser(){
        User user = new User("rise",24,false);
        User filterUser = Optional.ofNullable(user).filter(p -> {
            if (p.getFlag()) {
                return true;
            }
            return false;
        }).orElse(new User());
        System.out.println("过滤掉flag = false之后："+filterUser);
        return filterUser;
    }
    public static void main(String[] args) {
        OptionalTest2 optionalTest2 = new OptionalTest2();
        optionalTest2.test();

        User user1 = new User("rise1",23,true);
        User user2 = new User("rise2",24,false);
        //List<User> UserList = new ArrayList(){{ add(User1);add(User); }};
        List<User> UserList = new ArrayList<User>();
        Collections.addAll(UserList, user1,user2);
        optionalTest2.test2();

        optionalTest2.filterUser();

        List<User> filterList = UserList.stream().filter(user -> {
             if(user.getFlag().equals(true) && user.getName().equals("rise1")){
                 return true;
             }
            return false;
        }).collect(toList());

        System.out.println(filterList);

    }
}

@Data
class User {
    private String name;
    private Integer age;
    private Boolean flag;
    public User(String name, Integer age,Boolean flag) {
        this.name = name;
        this.age = age;
        this.flag = flag;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", flag=" + flag +
            '}';
    }
}