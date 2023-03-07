package com.jinbiao.manyThreads.tulinSchool;

/**
 * @Author：Jinbiao
 * @Date：2023/3/5 21:10
 * @Desc：死锁的条件，多个线程抢占多个资源，争夺锁的顺序不对、获取锁的线程不释放锁，彼此阻塞， 死锁例子demo:
 */
public class NormalDeadLock {
    private static Object No13 = new Object();//第一个锁
    private static Object No14 = new Object();//第二个锁

    //子线程，代表周瑜老师
    private static class ZhouYu extends Thread {

        private String name;

        public ZhouYu(String name) {
            this.name = name;
        }

        //第一个拿锁的方法
        @Override
        public void run() {
            Thread.currentThread().setName(name);
            try {
                String threadName = Thread.currentThread().getName();
                synchronized (No13) {
                    System.out.println(threadName + " get No13");
                    Thread.sleep(100);
                    synchronized (No14) {
                        System.out.println(threadName + " get No14");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //子线程，代表周瑜老师
    private static class Monkey extends Thread {

        private String name;

        public Monkey(String name) {
            this.name = name;
        }

        //第二个拿锁的方法
        @Override
        public void run() {
            Thread.currentThread().setName(name);
            try {
                String threadName = Thread.currentThread().getName();
                synchronized (No14) {
                    System.out.println(threadName + " get No14");
                    Thread.sleep(100);
                    synchronized (No13) {
                        System.out.println(threadName + " get No13");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ZhouYu zhouYu = new ZhouYu("ZhouYu");
        zhouYu.start();

        Monkey Monkey = new Monkey("Monkey");
        Monkey.start();
    }
}
