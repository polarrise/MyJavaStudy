package jinbiao.manyThreads;
/**
 * @author jinbiao
 * @date 2021/12/1
 * @apiNote
 */
public class Test implements Runnable{

    boolean stop=false;

   public void test(){

       Thread t2=new Thread(){
           public void run(){
               while(!stop){
                   doSomething();
               }
           }
       };
       t2.start();

       //匿名类
       Thread t1=new Thread(){
           public void run(){
              stop=true;
               System.out.println(stop);
           }
       };
       t1.start();

   }


    public void run() {
        if(!stop){
            doSomething();
        }
    }
    private void doSomething(){
        System.out.println("doSomething执行--");

    }




    public static void main(String[] args) {
        Test test=new Test();
        test.test();
    }
}
