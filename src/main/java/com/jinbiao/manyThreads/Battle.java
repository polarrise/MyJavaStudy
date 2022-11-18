package com.jinbiao.manyThreads;

/**
 * 创建多线程有3种方式，
 * 分别是继承线程类,
 * 实现Runnable接口,通过new Thread(Runnable target).start()来开启线程
 * 匿名类
 */
public class Battle implements Runnable{
    private Hero h1;
    private Hero h2;

    public Battle(Hero h1, Hero h2){
        this.h1 = h1;
        this.h2 = h2;
    }

    public Battle() {

    }

    public void run(){
        while(!h2.isDead()){
            h1.attackHero(h2);
        }
    }

    public void test(){
        Hero gareen = new Hero();
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

        Battle battle1 = new Battle(gareen,teemo);
        new Thread(battle1).start();

        Battle battle2 = new Battle(mf,leesin);
        new Thread(battle2).start();
    }

    public void test2(){
        Hero gareen = new Hero();
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

        //匿名类
        Thread t1=new Thread(){
            public void run(){
                while (!teemo.isDead()){
                    gareen.attackHero(teemo);
                }
            }
        };
        t1.start();

        Thread t2=new Thread(){
            public void run(){
                while(!leesin.isDead()){
                    mf.attackHero(leesin);
                }
            }
        };
        B b=new B(){
           public void test(){
               System.out.println("B类");
           }
        };
        b.test();

    }

    public static void main(String[] args) {
//         new Battle().test();
         new Battle().test2();
    }
    class B{
        public void test(){

        }
    }

}
