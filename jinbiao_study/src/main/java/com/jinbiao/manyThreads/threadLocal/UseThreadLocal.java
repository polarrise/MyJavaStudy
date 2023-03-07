package com.jinbiao.manyThreads.threadLocal;

/**
 * @Author：Jinbiao
 * @Date：2023/3/3 22:40
 * @Desc：
 */
public class UseThreadLocal {

    static ThreadLocal<String> threadLocal1 = new ThreadLocal<>();

    static ThreadLocal<Integer> threadLocal2 = new ThreadLocal<>();

    static class TestThread extends Thread{

        private Integer id;

        public TestThread(Integer id){
            this.id = id;
        }

        @Override
        public void run() {
            threadLocal1.set("线程_"+id);
            String threadName = Thread.currentThread().getName();

            if(id.equals(2)){
                threadLocal2.set(id);
            }
            System.out.println(threadName+":"+threadLocal1.get());
            System.out.println(threadName+":"+threadLocal2.get());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new TestThread(i).start();
        }
    }
}
