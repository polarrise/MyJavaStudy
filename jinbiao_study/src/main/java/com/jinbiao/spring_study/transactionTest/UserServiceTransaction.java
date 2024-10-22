package com.jinbiao.spring_study.transactionTest;

import com.alibaba.druid.pool.DruidDataSource;
import com.powersi.common.exception.customException.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

/**
 * @author wangjinbiao
 * @date 2023/2/15 11:46
 * @desc Spring事务的大概执行流程：
 * <p>
 * class UserServiceTransactionProxy extends UserServiceTransaction{
 * <p>
 * UserServiceTransaction target;  //目标对象
 * <p>
 * public void testTransaction(){
 * //方法上加了@Transactional注解
 * //1.事务管理器新建一个数据库联系conn
 * //2.conn.autocommit = false
 * //3.target.test()   //执行sql，操作数据库逻辑
 * //4.上述发生异常则conn.rollback, 成功则conn.commit();
 * }
 * }
 */
@Slf4j
@Service
public class UserServiceTransaction {

    /**
     * 方式1：新建一个代理对象userServiceTransactionBase去保证事务不失效
     */
    @Autowired
    private UserServiceTransactionBase userServiceTransactionBase;

    /**
     * 测试内部调用其他的方法让事务不失效的情况：2.自己注入自己。因为Spring容器里面单例池获取出来的是代理对象userServiceTransaction
     */
    @Autowired
    private UserServiceTransaction userServiceTransaction;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // @Autowired
    // public static UserServiceTransaction userServiceTransaction;

    // @PostConstruct
    // public void init(){
    //     userServiceTransaction = this;
    // }
    //
    // /**
    //  * 测试事务失效1：访问权限问题:事务方法使用 static修饰方法
    //  */
    // @Transactional
    // public static void test1() {
    //     /**
    //      * 静态方法里面直接从Spring容器里面取jdbcTemplate此时会为null,会报空指针
    //      * 需要用在bean初始化前方法里面填充好的userServiceTransaction对象的jdbcTemplate。
    //      */
    //     userServiceTransaction.jdbcTemplate.execute("insert into jinbiao_user values (1,'rise1','wang1234..','10086','小程序')");
    //     throw new RuntimeException("异常啦,请回滚...");
    // }


    /**
     * 测试事务失效1：访问权限问题:事务方法使用 final 、static 修饰方法
     */
    @Transactional
    public void test() {
        userServiceTransaction.test11();
    }

    @Transactional()
    private void test11() {
        jdbcTemplate.execute("insert into jinbiao_user values (1,'rise1','wang1234..','10086','小程序')");
        throw new RuntimeException("异常啦,请回滚...");
    }

    /**
     * 测试事务失效2：方法用final修饰或者static修饰
     */
    @Transactional
    public final void test2() {
        jdbcTemplate.execute("insert into jinbiao_user values (2,'rise2','wang1234..','10086','小程序')");
        throw new RuntimeException("异常啦,请回滚...");
    }

    /**
     * 测试事务失效3：方法内部调用，发生this调用问题，
     */
    public void test3() {
        this.a();   //this对象是目标对象不是代理对象，所以不会被代理到，造成a()上面的事务注解会失效
        userServiceTransaction.a();   //this对象是目标对象不是代理对象，所以不会被代理到，造成a()上面的事务注解会失效

    }

    @Transactional
    public void a() {
        jdbcTemplate.execute("insert into jinbiao_user values (3,'rise33','wang1234..','10086','小程序')");
        throw new RuntimeException("异常啦,请回滚...");
    }

    @Autowired
    private RoleService roleService;
    /**
     * 测试事务失效4：多线程调用
     */
    @Transactional(rollbackFor = Exception.class)
    public void test4() {
        jdbcTemplate.execute("insert into jinbiao_user values (3,'rise3','wang1234..','10086','小程序')");
        new Thread(() -> {
            roleService.insertRole();
            throw new RuntimeException();
        }).start();

        // CompletableFuture.runAsync(()->{
        //     roleService.insertRole();
        //     throw new RuntimeException();
        // });
    }

    /**
     * 测试事务失效5：表不支持事务
     */
    @Transactional
    public void test5() {
        jdbcTemplate.execute("insert into jinbiao_user2 values (3,'rise3','wang1234..','10086','小程序')");
        throw new RuntimeException();
    }

    /**
     * 测试事务失效6：错误的传播特性
     * REQUIRED 如果当前上下文中存在事务，那么加入该事务，如果不存在事务，创建一个事务，这是默认的传播属性值。
     * SUPPORTS 如果当前上下文存在事务，则支持事务加入事务，如果不存在事务，则使用非事务的方式执行。
     * MANDATORY 如果当前上下文中存在事务，否则抛出异常。
     * REQUIRES_NEW 每次都会新建一个事务，并且同时将上下文中的事务挂起，执行当前新建事务完成以后，上下文事务恢复再执行。
     * NOT_SUPPORTED 如果当前上下文中存在事务，则挂起当前事务，然后新的方法在没有事务的环境中执行。
     * NEVER 如果当前上下文中存在事务，则抛出异常，否则在无事务环境上执行代码。
     * NESTED 如果当前上下文中存在事务，则嵌套事务执行，如果不存在事务，则新建事务。
     */
    @Transactional(propagation = Propagation.NEVER)
    public void test6() {
        jdbcTemplate.execute("insert into jinbiao_user values (5,'rise5','wang1234..','10086','小程序')");
    }

    /**
     * 测试事务失效7：自己吞了异常
     */
    @Transactional()
    public void test7() {
        try {
            jdbcTemplate.execute("insert into jinbiao_user values (5,'rise5','wang1234..','10086','小程序')");
            int a = 1/0;
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }

    /**
     * 测试事务失效8：手动抛了别的异常
     */
    @Transactional()
    public void test8() throws Exception {
        try {
            jdbcTemplate.execute("insert into jinbiao_user values (5,'rise5','wang1234..','10086','小程序')");
            int a = 1/0;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new Exception("手动抛出Exception");
        }
    }

    /**
     * 测试事务失效9：自定义了回滚异常
     */
    @Transactional(rollbackFor = BusinessException.class)
    public void test9() throws Exception {
            jdbcTemplate.execute("insert into jinbiao_user values (5,'rise5','wang1234..','10086')");
    }

    /**
     * 测试内部调用其他的方法让事务不失效的情况：1.再新建一个代理对象userServiceTransactionBase去调用a()
     */
    @Transactional
    public void test22() {
        jdbcTemplate.execute("insert into jinbiao_user values (3,'rise3','wang1234..','10086','小程序')");
        userServiceTransactionBase.a();   //报错 Existing transaction found for transaction marked with propagation 'never'
    }

    /**
     * 测试内部调用其他的方法让事务不失效的情况：2.自己注入自己。因为Spring容器里面单例池获取出来的是代理对象userServiceTransaction
     */
    @Transactional
    public void test222() {
        jdbcTemplate.execute("insert into jinbiao_user values (3,'rise3','wang1234..','10086','小程序')");
        userServiceTransaction.a();   //报错 Existing transaction found for transaction marked with propagation 'never'
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/xiaofa_lawyer";
        String user = "root";
        String password = "123456";

        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            // 1. 建立数据库连接
            conn = DriverManager.getConnection(url, user, password);

            // 2. 关闭自动提交，开始手动管理事务
            conn.setAutoCommit(false);

            // 3. 执行SQL操作
            String sql1 = "INSERT INTO t_user1 (name) VALUES (?)";
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setString(1, "jinbiao111");
            pstmt1.executeUpdate();

            String sql2 = "INSERT INTO t_user2 (name) VALUES (?)";
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1, "jinbiao222");
            pstmt2.executeUpdate();

            // 4. 如果SQL操作成功，提交事务
            conn.commit();
            System.out.println("事务提交成功。");

        } catch (SQLException e) {
            // 5. 如果发生异常，回滚事务
            if (conn != null) {
                try {
                    System.out.println("发生错误，回滚事务。");
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // 6. 关闭资源
            try {
                if (pstmt1 != null) pstmt1.close();
                if (pstmt2 != null) pstmt2.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    /**
     * 编程式事务1：使用 TransactionTemplate 进行编程式事务管理
     */
    public void programmingTransaction1() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    // 执行业务逻辑
                    jdbcTemplate.update("insert into t_user1 (name) values (?)", "jinbiao333");
                    log.info("事务中执行的操作");
                    // 你可以根据特定条件回滚事务
                    if (someCondition()) {
                        log.info("事务回滚");
                        status.setRollbackOnly();  // 手动回滚事务
                    }

                } catch (Exception e) {
                    log.info("事务回滚");
                    status.setRollbackOnly();  // 发生异常时自动回滚
                    throw e;
                }
            }
        });
    }

    /**
     *  判断不满足某个条件事务回滚
     * @return
     */
    private boolean someCondition() {
        return false;
    }

    /**
     * 编程式事务2：使用 PlatformTransactionManager 进行编程式事务管理
     */
    public void programmingTransaction2() {
        //定义一个数据源
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/xiaofa_lawyer");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");

        //定义一个JdbcTemplate，用来方便执行数据库增删改查
        JdbcTemplate jdbcTemplate = new JdbcTemplate(druidDataSource);
        //1.定义事务管理器，给其指定一个数据源（可以把事务管理器想象为一个人，这个人来负责事务的控制操作）
        PlatformTransactionManager platformTransactionManager = new DataSourceTransactionManager(druidDataSource);
        //2.定义事务属性：TransactionDefinition，TransactionDefinition可以用来配置事务的属性信息，比如事务隔离级别、事务超时时间、事务传播方式、是否是只读事务等等。
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        //3.获取事务：调用platformTransactionManager.getTransaction开启事务操作，得到事务状态(TransactionStatus)对象
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        //4.执行业务操作，下面就执行2个插入操作
        try {
            log.info("before:{}", jdbcTemplate.queryForList("SELECT * from t_user1"));
            jdbcTemplate.update("insert into t_user1 (name) values (?)", "jinbiao555");
            jdbcTemplate.update("insert into t_user2 (name) values (?)", "jinbiao666");
            //5.提交事务：platformTransactionManager.commit
            platformTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            //6.回滚事务：platformTransactionManager.rollback
            platformTransactionManager.rollback(transactionStatus);
        }
        log.info("after:{}", jdbcTemplate.queryForList("SELECT * from t_user1"));
    }

}

/**
 CREATE TABLE `jinbiao_user` (
 `id` int NOT NULL AUTO_INCREMENT,
 `user_name` varchar(50) DEFAULT NULL,
 `password` varchar(50) DEFAULT NULL,
 `phone` varchar(50) DEFAULT NULL,
 `source` varchar(20) DEFAULT NULL,
 PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


 CREATE TABLE `jinbiao_user2` (
 `id` int NOT NULL AUTO_INCREMENT,
 `user_name` varchar(50) DEFAULT NULL,
 `password` varchar(50) DEFAULT NULL,
 `phone` varchar(50) DEFAULT NULL,
 `source` varchar(20) DEFAULT NULL,
 PRIMARY KEY (`id`)
 ) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 CREATE TABLE `jinbiao_role` (
 `id` int NOT NULL,
 `role` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 */
