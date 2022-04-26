package jinbiao.manyThreads;

/**
 * 创建多线程有3种方式，分别是
 * 继承线程类,通过线程对象调用start()方法
 * 实现Runnable接口,匿名类
 */
public class KillThread extends Thread{
    private Hero h1;
    private Hero h2;
    public KillThread(Hero h1,Hero h2){
        this.h1=h1;
        this.h2=h2;
    }

    @Override
    public void run() {
        while(!h2.isDead()){
            h1.attackHero(h2);
        }
    }

    public static void main(String[] args) {
        Hero gareen=new Hero();
        gareen.name = "盖伦";
        gareen.hp = 616;
        gareen.damage = 50;

        Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 300;
        teemo.damage = 30;

        Hero mf = new Hero();
        mf.name = "赏金猎人";
        mf.hp = 500;
        mf.damage = 65;

        Hero leesin = new Hero();
        leesin.name = "盲僧";
        leesin.hp = 455;
        leesin.damage = 80;

        KillThread killThread1=new KillThread(gareen,teemo);
        killThread1.start();

        KillThread killThread2=new KillThread(mf,leesin);
        killThread2.start();
    }
}
