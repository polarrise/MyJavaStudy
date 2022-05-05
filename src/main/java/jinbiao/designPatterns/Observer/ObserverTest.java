package jinbiao.designPatterns.Observer;

/**观察者模式:
 * 基本概念：观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一主题对象。
 * 这个主题对象在状态发生变化时，会通知所有观察者对象，使它们能够自动更新自己。
 * 观察者模式又叫发布-订阅(Publish/Subscribe)模式。
 *
 * Subject类：它把所有对观察者对象的引用保存在一个聚集里，每个主题都可以有任何数量的观察着。抽象主题提供一个接口，可以增加和删除观察着对象。
 * Observer类：抽象观察者，为所有的具体观察者定义一个接口，在得到主题的通知时更新自己。
 * ConcreteSubject类：具体主题，将有关状态存入具体观察者对象；在具体主题的内部状态改变时，给所有登记过的观察者发出通知。
 * ConcreteObserver类：具体观察者，实现抽象观察者角色所要求的更新接口，以便使本身的状态与主题的状态相协调。
 */

import java.util.Vector;

/**
 * 观察者模式如何使用:
 * 例如：老师有电话号码，学生需要知道老师的电话号码以便于在合适的时候拨打，在这样的组合中，老师就是一个被观察者（Subject），
 * 学生就是需要知道信息的观察者，当老师的电话号码发生改变时，学生得到通知，并更新相应的电话记录。
 */
public class ObserverTest {
    public static void main(String[] args) {
        Vector students = new Vector();
        Teacher t = new Teacher();
        for(int i= 0;i<10;i++){
            Student st = new Student("Jinbiao"+i,t);  //创建学生Jinbiao0-9,t     10个学生,一个老师
            students.add(st);
            t.attach(st);      //目标-添加观察者对象
        }
        System.out.println("Welcome to Jinbiao!" +"Observer Patterns." +"-------------------------------");
        t.setPhone("12345678");
        for(int i=0;i<3;i++){
            ((Student)students.get(i)).show();
        }
        System.out.println("老师的手机号改变后~,通知学生及时更新手机号");
        t.setPhone("87654321");
        for(int i=0;i<3;i++){
            ((Student)students.get(i)).show();
        }
    }
}

/**
 * Subject(目标，Subject):
 *目标知道它的观察者。可以有任意多个观察者观察同一个目标。
 *提供注册和删除观察者对象的接口。
 */
interface Subject {
    public void attach(Observer mObserver);
    public void detach(Observer mObserver);
    public void notice();
}

/**
 * Observer(观察者，Observer)：
 *为那些在目标发生改变时需要获得通知的对象定义一个更新接口。
 */
interface Observer {
    public void update();
}

/**
 * ConcreteSubject(具体目标，Teacher)
 * 将有关状态存入各ConcreteObserve对象。
 * 当他的状态发生改变时，向他的各个观察者发出通知。
 */
class Teacher implements Subject{
    private String phone;
    private Vector students;
    public Teacher(){
        phone = "";
        students = new Vector();
    }
    @Override
    public void attach(Observer mObserver) {
        students.add(mObserver);
    }
    @Override
    public void detach(Observer mObserver) {
        students.remove(mObserver);
    }
    @Override
    public void notice() {
        for(int i=0;i<students.size();i++){
            ((Observer)students.get(i)).update();
        }
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {   //只要老师一改变手机号,立马通知学生更新手机号为老师的最新手机号
        this.phone = phone;
        notice();
    }
}

/**
 * ConcreteObserver(具体观察者, Student)：
 * 维护一个指向ConcreteSubject对象的引用。
 * 存储有关状态，这些状态应与目标的状态保持一致。
 * 实现Observer的更新接口以使自身状态与目标的状态保持一致。
 */
class Student implements Observer{
    private String name;
    private String phone;
    private Teacher mTeacher;
    public Student(String name,Teacher t){
        this.name = name;
        mTeacher = t;
    }
    public void show(){
        System.out.println("Name:"+name+" Teacher'sphone:" + phone);
    }
    @Override
    public void update() {
        phone = mTeacher.getPhone();
    }
}

/**
 总结:观察者模式何时适用？
 当一个抽象模型有两个方面，其中一个方面依赖于另一方面。将这二者封装在独立的对象中可以使他们各自独立地改变和复用。
 当对一个对象的改变需要同时改变其它对象，而不知道具体由多少对象有待改变。
 当一个对象必须通知其他对象，而它又不能假定其他对象是谁，换言之，你不希望这些对象是紧密耦合的。让耦合的双方都依赖于抽象，而不是依赖于具体。
 */