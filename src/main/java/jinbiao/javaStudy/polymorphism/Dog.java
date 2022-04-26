package jinbiao.javaStudy.polymorphism;

/**
 * @author jinbiao
 * @date 2022/2/10
 * @apiNote
 */
public class Dog extends Animal{
    public void eat(){
        System.out.println("狗吃东西--");
    }
    public void bark(){
        System.out.println("狗叫--");
    }
}
