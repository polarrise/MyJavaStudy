package jinbiao.designPatterns.protoype;

import lombok.Data;

import java.util.ArrayList;

/**
 * 用list.set(index, element);方法，将下标为index的元素替换为element
 */
public class MyArrayList extends ArrayList {
    /**
     * 实现数组的深拷贝~  这样的话，尽管数组里面有可改变对象(company),通过clone方法拷贝的数组里面的元素:company对象也是不同滴!
     */
    @Override
    public MyArrayList clone() {
        System.out.println("this:"+this);        //this:指向当前对象
        MyArrayList cloneMyArrayList = (MyArrayList) super.clone();
        for(int i=0;i<cloneMyArrayList.size();i++){
            if(cloneMyArrayList.get(i) instanceof Company){
                try {
                    Company clone = ((Company) cloneMyArrayList.get(i)).clone();
                    cloneMyArrayList.set(i,clone);  //用list.set(index, element);方法，将下标为index的元素替换为element
                }catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
            return cloneMyArrayList;
    }

    public static void main(String[] args) {
        MyArrayList list=new MyArrayList();
        list.add("123");
        Company company=new Company("rise");
        list.add(company);
        System.out.println("原始对象:"+list);

        MyArrayList listClone = (MyArrayList) list.clone();
        System.out.println("拷贝对象:"+listClone);

        System.out.println("修改原始对象name为rise1,拷贝对象不修改。出现结果:【拷贝对象的name跟着被改了】===");
        //修改原始对象:
        Company company1 = (Company) list.get(1);
        company1.setName("rise1");
        System.out.println("原始对象:"+list);
        System.out.println("拷贝对象:"+listClone);
    }
}

@Data
class Company implements Cloneable{
    private  String name;
    public Company(String name){
        this.name=name;
    }

    @Override
    protected Company clone() throws CloneNotSupportedException {
        return (Company) super.clone();
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                '}';
    }
}