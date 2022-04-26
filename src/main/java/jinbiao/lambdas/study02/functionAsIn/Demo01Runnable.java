package jinbiao.lambdas.study02.functionAsIn;

public class Demo01Runnable {
    //定义一个方法startThread,方法的参数使用函数式接口Runnable
    public static void startThread(Runnable run){
        //开启多线程
        new Thread(run).start();
    }

    public static void main(String[] args) {
        //调用startThread方法,方法的参数是一个接口,那么我们可以传递这个接口的匿名内部类
        Demo01Runnable.startThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程启动");
            }
        });

        Demo01Runnable.startThread(()->{
            System.out.println("111");
        });
    }
}
