package com.jinbiao.lambdas.study01.NoInNoOut;

/**jdk1.8特性:
 * 接口中可以定义默认实现方法和静态方法
 * 在接口中可以使用default和static关键字来修饰接口中定义的普通方法
 *
 * 使用Lambda必须有接口，并且接口中有且仅有一个抽象方法。
 * 只有当接口中的抽象方法存在且唯一时，才可以使用Lambda，但排除接口默认方法以及声明中覆盖Object的公开方法。
 */
public interface Cook {
    default  String getName(){
        return "zhangsan";
    }

    static String getName2(){
        return "zhangsan";
    }

    public abstract void makeFood();

    public static void main(String[] args) {
           new Cook() {
            @Override
            public void makeFood() {
                System.out.println("111");
            }
        }.makeFood();
    }
}
