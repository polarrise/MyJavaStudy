package jinbiao.manyThreads.TulinSchool.day01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jinbiao
 * @date 2022/2/17
 * @apiNote
 */
public class A {
    int num=0;
    static int num1=0;

    AtomicInteger atomicInteger=new AtomicInteger();  //原子操作类做优化,又能优化，又能保证多线程下的同步性
    public int getNum() {
//1        return num;
        return atomicInteger.get();
    }
    //synchronized对方法上锁,锁了什么东西？   答:锁的是当前对象
//    public synchronized void increse(){
//        this.num++;
//    }
    //相当于:
    public void increse(){
//1        synchronized(this){
//            this.num++;
//        }
        atomicInteger.incrementAndGet();

        while(true){
            int oldValue=atomicInteger.get();
            int newValue=oldValue+1;
            if(atomicInteger.compareAndSet(oldValue,newValue)){
                break;
            }
        }
    }
    public synchronized static void increse1(){   //如果在静态方法前面加synchronized,相当于锁的是A.class
        synchronized(A.class){
            A.num1++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
         A a=new A();
         long start=System.currentTimeMillis();
         //线程t1:
         Thread t1=new Thread(()->{
                for(int i=0;i<10000;i++){
                    a.increse();
                }
         });
        t1.start();

        //主线程:
        for(int i=0;i<10000;i++){
            a.increse();
        }
        t1.join();//在主线程中调t1线程的join方法,那么主线程会进入阻塞队列,直到t1线程结束或中断线程。也就是主线程执行到这行代码时，要等着t1线程执行完
        long end=System.currentTimeMillis();
        System.out.println(String.format("%sms",end-start));
        System.out.println(a.getNum());
    }
}
