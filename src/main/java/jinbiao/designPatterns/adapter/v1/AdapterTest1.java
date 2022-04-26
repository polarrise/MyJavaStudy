package jinbiao.designPatterns.adapter.v1;

/**
 * @author Jinbiao
 * @date 2022/4/25
 * @apiNote
 */
public class AdapterTest1 {
    public static void main(String[] args) {
        Adaptee adaptee=new Adaptee();
        Adapter adapter=new Adapter(adaptee);
        adapter.output5v();
    }
}


class Adaptee{
    public int output220v(){  //输出电压 220v
        return 220;
    }
}

interface Target{
    int output5v();       //目标电压， 比如我们的手机需要5V的电压
}

/**
 * 对象适配器模式:
 */
class Adapter implements Target{  //要把220V的电压转换成5V的电压
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee){
        this.adaptee=adaptee;
    }
    @Override
    public int output5v() {
        int i = adaptee.output220v();
        //.....
        System.out.println(String.format("原始电压: %d v  ->   输出电压:  %d v",i, 5));
        return 5;
    }
}

