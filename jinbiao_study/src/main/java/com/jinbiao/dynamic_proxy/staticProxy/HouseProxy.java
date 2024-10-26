package com.jinbiao.dynamic_proxy.staticProxy;

public class HouseProxy extends Landlord {
    //将被代理对象声明为成员变量
    private Landlord landlord;

    public HouseProxy(Landlord landlord) {
        this.landlord = landlord;
    }

    @Override
    public void rentHouse() {

        //开始代理
        System.out.println("我是中介, 开始代理");

        //代理房东出租房⼦
        landlord.rentHouse();

        //代理结束
        System.out.println("我是中介, 代理结束");
    }

    public static void main(String[] args) {

        Landlord landlord = new Landlord();
        HouseProxy houseProxy = new HouseProxy(landlord);
        houseProxy.rentHouse();
    }
}
