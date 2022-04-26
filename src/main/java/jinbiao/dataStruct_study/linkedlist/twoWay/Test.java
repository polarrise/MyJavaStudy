package jinbiao.dataStruct_study.linkedlist.twoWay;

import java.util.LinkedList;

/**
 * @author jinbiao
 * @date 2021/12/10
 * @apiNote
 */
public class Test {

    private int size;
    private String name;
    private int age;
    public Test(String name){
        this.name=name;
    }
    public void test(){
        System.out.println(name);
    }

    @Override
    public String toString() {
        return "Test{" +
                "size=" + size +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public static void main(String[] args) {
        LinkedList list=new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list);
        Test test=new Test("Jinbiao");
        test.age=23;
        System.out.println(test);
        test.test();
    }
}
