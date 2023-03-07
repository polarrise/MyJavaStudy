package com.jinbiao.manyThreads.tulinSchool.threadInterrupted;

/**
 * 中断线程
 */
public class EndThread extends Thread{

    private boolean cancel;

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " interrrupt flag =" + isInterrupted());
        while (!isInterrupted()) {
            //while(!Thread.interrupted()){
            //while(true){
            System.out.println(threadName + " is running");
            System.out.println(threadName + "inner interrrupt flag =" + isInterrupted());
            System.out.println(threadName + " interrrupt flag: =" + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        EndThread endThread = new EndThread();
        endThread.start();
        Thread.sleep(5);
        endThread.interrupt();//中断线程，其实设置线程的中断标识位=true
    }
}
