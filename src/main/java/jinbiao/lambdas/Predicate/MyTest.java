package jinbiao.lambdas.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Jinbiao
 * @date 2022/4/7
 * @apiNote
 */
public class MyTest {
    public static ArrayList<String> filter(String[] arr, Predicate<String> pre1, Predicate<String> pre2) {
        ArrayList<String> list = new ArrayList<>();
        for (String s : arr) {
            boolean b = pre1.and(pre2).test(s);
            if (b) {
                list.add(s);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String[] array = {"迪丽热巴,女", "古力娜扎,女", "佟丽娅,女", "赵丽颖,女"};
        ArrayList<String> list = filter(array,
                s -> s.split(",")[1].equals("女"),
                s -> s.split(",")[0].length() == 4);
        for(String s : list){
            System.out.println(s);
        }

        List <String>result=new ArrayList<String>();
        for(String s : array){
            boolean ifTrue = new Predicate<String>() {
                @Override
                public boolean test(String s) {
                    return s.split(",")[0].length() == 4;
                }
            }.and(new Predicate<String>() {
                @Override
                public boolean test(String s) {
                    return s.split(",")[1].equals("女");
                }
            }).test(s);
            if(ifTrue){
                result.add(s);
            }
        }
        System.out.println("在方法体里完成测试:"+result);
    }
}
