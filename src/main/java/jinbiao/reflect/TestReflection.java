package jinbiao.reflect;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 类对象，就是用于描述这种类，都有什么属性，什么方法的
 获取类对象有3种方式
 1. Class.forName
 2. Hero.class
 3. new Hero().getClass()
 */
public class TestReflection {

    /**
     * 通过反射机制，创建对象
     */
    public void getClassObject(){
        try{
            String className="cn.how2j.springcloud.reflect.Hero";  // //使用反射的方式创建对象
            Class pClass1=Class.forName(className);     //获取类对象，都会导致静态属性被初始化，而且只会执行一次。
            //   Class pClass2=Hero.class;                 //这种方式不会导致静态属性被初始化
            //   Class pClass3=new Hero().getClass();     //获取类对象，都会导致静态属性被初始化，而且只会执行一次。
            Constructor c=pClass1.getConstructor();      //构造器
            Hero h2 = (Hero) c.newInstance();          //通过构造器实例化
            h2.name="gareen";
            System.out.println(h2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 通过反射机制修改对象的属性
     * getField 只能获取public的，包括从父类继承来的字段。
     * getDeclaredField 可以获取本类所有的字段，包括private的，但是不能获取继承来的字段。 (注： 这里只能获取到private的字段，但并不能访问该private字段的值,除非加上setAccessible(true))
     */
    public void updateObjectAttr(){
        Hero h =new Hero();
        h.name="gareen";   //使用传统方式修改name的值为garen
        try{
            Field f1 = h.getClass().getDeclaredField("name");   //获取类Hero的属性叫做name的字段
            f1.set(h,"teemo");            //修改这个字段的值
            System.out.println(h.name);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void invokeMethod(){
        Hero h = new Hero();
        try {
            Method m = h.getClass().getMethod("setName", String.class);  // 获取这个名字叫做setName，参数类型是String的方法
            m.invoke(h,"琪亚娜");          // 对h对象，调用这个方法
            System.out.println(h.getName());
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void readConfigByReflect(){
        File f=new File("D:/StudySpace/springcloud/springAop-study/src/main/java/cn/how2j/springcloud/reflect/hero.config");
        try (FileReader fr = new FileReader(f)) {
            String fileContent = null;
            char[] all = new char[(int) f.length()];
            fr.read(all);
            fileContent = new String(all);
            String[] cs = fileContent.split("\r\n");
            String hero1className = cs[0];
            String hero1Name = cs[1];
            String hero2className = cs[2];
            String hero2Name = cs[3];

            //根据反射，获取hero1，并且给hero1的name字段赋值
            Class hero1Class = Class.forName(hero1className);
            Constructor hero1Constructor = hero1Class.getConstructor();
            Object hero1 = hero1Constructor.newInstance();
            Field hero1NameField = hero1Class.getField("name");
            hero1NameField.set(hero1, hero1Name);

            //根据反射，获取hero2,并且给hero2的name字段赋值
            Class hero2Class = Class.forName(hero2className);
            Constructor hero2Constructor = hero2Class.getConstructor();
            Object hero2 = hero2Constructor.newInstance();
            Field hero2NameField = hero2Class.getField("name");
            hero2NameField.set(hero2, hero2Name);

            //根据反射，获取attackHero方法，并且调用hero1的这个方法，参数是hero2
            Method attackHeroMethod = hero1Class.getMethod("attackHero", Hero.class);
            attackHeroMethod.invoke(hero1, hero2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TestReflection().getClassObject();
//        new TestReflection().invokeMethod();
//          new TestReflection().readConfigByReflect();
    }

}
