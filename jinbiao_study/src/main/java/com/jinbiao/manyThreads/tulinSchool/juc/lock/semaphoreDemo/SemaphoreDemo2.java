package com.jinbiao.manyThreads.tulinSchool.juc.lock.semaphoreDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author：Jinbiao
 * @Date：2023/3/12 15:36
 * @Desc：Semaphore实现数据库连接池
 *
 * Semaphore常见应用场景总结：
 * 1.限流：Semaphone可以用于限制对共享资源的并发访问数量，以控制系统的流量
 * 2.资源池：Semaphore可以用于实现资源池，以维护一组有限的共享资源
 */
public class SemaphoreDemo2 {
    public static void main(String[] args) {
        //初始化连接池大小：
        final ConnectPool pool = new ConnectPool(2);
        ExecutorService executorService = Executors.newCachedThreadPool();

        //5个线程并发来争抢连接资源
        for (int i = 0; i < 5; i++) {
            final int id = i + 1;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Connection connect = null;
                    try {
                        System.out.println("线程" + id + "等待获取数据库连接");

                        // 总共2个数据库连接，有5个线程，只会有2个线程成功获取到数据库连接...
                        connect = pool.openConnect();
                        System.out.println("线程" + id + "已拿到数据库连接:" + connect);

                        //进行数据库操作2秒...然后释放连接
                        Thread.sleep(2000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("线程" + id + "释放数据库连接:" + connect);
                        pool.releaseConnect(connect);
                    }

                }
            });
        }
    }
}

class ConnectPool {

    private int size;
    private Connection[] connections;

    //记录对应下标的Connection是否已被使用
    private boolean[] connectionFlag;
    //信号量对象
    private Semaphore semaphore;

    /**
     * 初始化连接池大小：
     * @param size
     */
    public ConnectPool(int size) {
        this.size = size;
        this.connections = new Connection[size];
        this.connectionFlag = new boolean[size];
        this.semaphore = new Semaphore(size,true);
        initConnets(); //初始化连接池
    }

    private void initConnets(){
        for (int i = 0; i < this.size; i++) {
            connections[i] = new Connection();
        }
    }

    /**
     * 获取数据库连接
     */
    public Connection openConnect() throws InterruptedException {
        //得先获得使用许可证，如果信号量为0，则拿不到许可证，一直阻塞直到能获得
        semaphore.acquire();
        return getConnection();
    }

    private synchronized Connection getConnection() {
        for (int i = 0; i < connectionFlag.length; i++) {
            if (!connectionFlag[i]) {
                //标记该连接已被使用
                connectionFlag[i] = true;
                return connections[i];
            }
        }
        return null;
    }

    /**
     * 释放某个数据库连接
     */
    public synchronized void releaseConnect(Connection connect) {
        for (int i = 0; i < this.size; i++) {
            if (connect == connections[i]) {
                connectionFlag[i] = false;
                semaphore.release();
            }
        }
    }
}

/**
 * 数据库连接
 */
class Connection {
    private static int count = 1;
    private int id = count++;

    public Connection() {
        //假设打开一个连接很耗费资源，需要等待1秒
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("连接#" + id + "#已与数据库建立通道！");
    }

    @Override
    public String toString() {
        return "#" + id + "#";
    }

}