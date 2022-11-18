package com.jinbiao.lambdas.MethodReference.superReMethod;

public class Child extends Parent{
    @Override
    public void hello() {
        System.out.println("hello，I'm Child");
    }

    public void method(MyMeet myMeet){
        myMeet.meet();
    }

    public void show(){
        method(new MyMeet() {
            @Override
            public void meet() {
                Parent parent = new Parent();
                parent.hello();
            }
        });

        method(()->{
            Parent parent = new Parent();
            parent.hello();    //"hello，I'm Parent"
        });

        //使用super关键字调用父类
        method(()->super.hello());

        /**
         * 使用方法引用：使用super引用父类的成员方法：
         * 1.super已经存在
         * 2.父类的成员方法hello已经存在
         * 可以直接使用super引用父类的成员方法
         */
        method(super::hello);

        /**使用方法引用：使用super引用父类的成员方法：
         * 内部就是 1.lambda表达式 作为一个MyMeet对象,传入到method方法中
         *         2.lambda表达式调用内部的方法体实现
         *         3.方法体(也就是接口里抽象方法的实现)。通过super调用了父类的成员方法
         */
    }

    public static void main(String[] args) {
        new Child().show();
    }
}

