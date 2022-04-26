package jinbiao.javaStudy.polymorphism;

/**
 * @author jinbiao
 * @date 2022/2/10
 * @apiNote
 */
public class Cat extends Animal{
    public void eat(){
        System.out.println("猫吃东西--");
    }
    public void bark(){
        System.out.println("猫叫--");
    }
}
