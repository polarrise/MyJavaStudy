package com.jinbiao.lambdas.Stream;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortedList {
    public static Comparator<QueueMsgVO> getComparator(){
        //完整写法:
        //方法的返回值类型是一个接口,那么我们可以返回这个接口的匿名内部类
        return new Comparator<QueueMsgVO>() {
            @Override
            public int compare(QueueMsgVO o1, QueueMsgVO o2) {
                //按照字符串的降序排序
                if (o1.isFree() ^ o2.isFree()) {
                    return o1.isFree() ? -1 : 1;
                } else {
                    return 0;
               }
            }
        };
    }

    public static void main(String[] args) {
        QueueMsgVO vo1=new QueueMsgVO("service1",true);
        QueueMsgVO vo2=new QueueMsgVO("service2",false);
        QueueMsgVO vo3=new QueueMsgVO("service3",true);
        QueueMsgVO vo4=new QueueMsgVO("service4",false);
        List<QueueMsgVO> list=new ArrayList<>();
        list.add(vo1);
        list.add(vo2);
        list.add(vo3);
        list.add(vo4);
        // 排序
        list.sort((o1, o2) -> {
            if (o1.isFree() ^ o2.isFree()) {  //1 ^ 0 =1         0 ^ 1 =1
                return o1.isFree() ? -1 : 1;   //-1 不用排序,         1:需要排序
            } else {
                return 0;
            }
        });
//        final Comparator<QueueMsgVO> comparator = getComparator();
//        list.sort(comparator);
        System.out.println(list);
    }
}

@Data
class QueueMsgVO{
    private String name;
    private boolean isFree;
    public QueueMsgVO(String name,boolean isFree){
        this.name=name;
        this.isFree=isFree;
    }
}
