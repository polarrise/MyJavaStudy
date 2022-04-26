package jinbiao.designPatterns.FactoryMethod;

/**
工厂方法模式:也是符合程序的开闭原则,open close原则,对扩展开发:加任何程序直接进行继承即可,对修改关闭:增加实现的时候,并没有对代码进行任何的修改,没有修改之前稳定的逻辑,只是加入了新的逻辑
 也符合单一职责原则:产品A1专注于做产品A1的事情，产品A2专注于做产品A2的事情，它们相互间不受影响
 */
public class FactoryMethod {
    public static void main(String[] args) {
        System.out.println("简单工厂模式=============");
        Product product1 = SimpleFactory.createProduct("1");
        product1.method1();
        Product product2 = SimpleFactory.createProduct("2");
        product2.method1();

        System.out.println("=======================");
        System.out.println("工厂方法模式=============");
        Application application=new ConcreteProductA1();
        Product object1 = application.getObject();
        object1.method1();
        Application application2=new ConcreteProductA2();
        Product object2 = application2.getObject();
        object2.method1();
    }

}

interface Product{
    public void method1();
}

class ProductA1 implements Product{
    @Override
    public void method1(){
        System.out.println("ProductA1.method1 executed. ");
    }
}

class ProductA2 implements Product{
    @Override
    public void method1(){
        System.out.println("ProductA2.method1 executed. ");
    }
}

/**
 * 简单工厂
 */
class SimpleFactory{
    public static Product createProduct(String type){
        if(type.equals("1")){
            return new ProductA1();
        }else if(type.equals("2")){
            return new ProductA2();
        }else {
            return null;
        }
    }

}

/**
 * 在引入设计模式的时候，我们需要找出稳定的部分与抽象的部分
 * 创建对象是稳定的，不稳定的就是具体的实现
 */
 abstract class Application{
    abstract Product createProduct();  //工厂方法,让createProduct()延迟到子类

     Product getObject(){
         Product product=createProduct();
         return  product;
     }
}

class ConcreteProductA1 extends Application{

    @Override
    Product createProduct() {
        return new ProductA1();
    }
}

class ConcreteProductA2 extends Application{

    @Override
    Product createProduct() {
        return new ProductA2();
    }
}
