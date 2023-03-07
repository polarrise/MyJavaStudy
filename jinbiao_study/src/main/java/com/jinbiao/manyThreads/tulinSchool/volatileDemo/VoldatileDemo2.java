package com.jinbiao.manyThreads.tulinSchool.volatileDemo;

/**
 * @author WangJinbiao
 * @date 2023/2/10 23:09
 * @desc
 */
public class VoldatileDemo2 {

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        //Thread thread = new Thread(() -> {
        //    for (int i = 0; i <= 10000; i++) {
        //        test.increase();
        //    }
        //});
        //thread.start();
        //
        //System.out.println(test.getNum());


        long startTime = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                test.increase();
            }
        });
        t1.start();
        for(int i=0;i<10000;i++){
            test.increase();
        }
        t1.join(); //在主线程main中调t1线程的join方法,那么主线程会进入阻塞队列,直到t1线程结束或中断线程。也就是主线程执行到这行代码时，要等着t1线程执行完才会继续下面的代码
        //long endTime = System.currentTimeMillis();
        //System.out.println(String.format("%sms",endTime-startTime));
        System.out.println("t1线程和main线程对num累加后的结果:"+test.getNum());     //预期输出20000,但实际输出并不等于20000,就是存在线程安全问题


    }
}
class Test{
    volatile int num=0;

    public int getNum() {
        return num;
    }

    public void increase(){
        this.num++;
    }
}
