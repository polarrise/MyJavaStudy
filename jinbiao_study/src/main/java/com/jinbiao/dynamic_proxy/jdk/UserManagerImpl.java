package com.jinbiao.dynamic_proxy.jdk;

public class UserManagerImpl implements UserManager{

    //重写新增用户方法
    public void addUser(String userName, String password) {
        System.out.println("调用了新增的方法！");
        System.out.println("传入参数为 userName: "+userName+" password: "+password);
    }
    //重写删除用户方法
    public void delUser(String userName) {
        System.out.println("调用了删除的方法！");
        System.out.println("传入参数为 userName: "+userName);
    }


    void delUser2(String userName){
        System.out.println(userName);
    };

    protected void delUser3(String userName){
        System.out.println(userName);
    };

    public static void delUser4(String userName){
        System.out.println(userName);
    };
}
