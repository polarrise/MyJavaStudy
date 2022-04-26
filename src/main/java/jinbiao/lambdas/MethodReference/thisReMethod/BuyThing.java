package jinbiao.lambdas.MethodReference.thisReMethod;

public class BuyThing {
    public void buyCar(){
        System.out.println("买了一辆别摸我");
    }

    public void getSalary(MyWallet myWallet){
        myWallet.buy();
    }

    public void method(){
        getSalary(()->this.buyCar());  //1.传入一个MyWallet类型的lambda表达式,2.lambda表达式调用内部的方法体

        /**
         * 1.this已经存在
         * 2.本类的成员方法buyCar已经存在
         * 所以可以直接使用this引用本类的成员方法buyCar
         */
        getSalary(this::buyCar);

    }

    public static void main(String[] args) {
        new BuyThing().method();
    }
}
