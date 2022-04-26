package jinbiao.dynamic_proxy.jdk;

/**
 * 用户管理接口
 */
public interface UserManager {
    //新增用户抽象方法
    void addUser(String userName, String password);
    //删除用户抽象方法
    void delUser(String userName);
}
