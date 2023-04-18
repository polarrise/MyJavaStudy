package com.powersi.test;

/**
 * @Author：Jinbiao
 * @Date：2023/4/18 14:19
 * @Desc：Netty-Future Listener机制示例
 */
public class FutureListenerDemo {

    //模拟耗时异步操作的方法
    public FutureOperationComplete doSomething(){

        FutureOperationComplete futureOperationComplete=new FutureOperationComplete();

        new Thread(()->{
            System.out.println("异步执行中！");
            try {

                //模拟耗时异步操作
                System.out.println("异步操作进行中===");
                Thread.sleep(5000);

                //操作完成后回调并传参
                for (Listener listener:futureOperationComplete.getListeners()){
                    listener.operationComplete(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return futureOperationComplete;
    }

    public static void main(String[] args) {
        FutureListenerDemo futureListenerDemo=new FutureListenerDemo();

        //执行耗时异步操作的方法， 这异步操作没有执行完,也返回这个futureOperationComplete对象，供主线程去添加侦听事件
        FutureOperationComplete futureOperationComplete = futureListenerDemo.doSomething();

        //监听异步操作完成
        futureOperationComplete.addListener((x)->{
            System.out.println("异步执行完毕！传来的参数为："+x);
        });

        System.out.println("主线程继续执行");
    }
}
