package jinbiao.huaweiOd.kaoshi;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public static  void  test(String [] arr){
          int count=0;
          List<String> list=new ArrayList<String> ();
        for(int i=0;i<arr.length;i++){
            if(arr[i].equals("1")){
                list.add(arr[i]);
            }else{   //遇到0则计算车子至少有多少辆
                if(list.size()%3!=0){   //7,8,10
                    count+=list.size()/3+1;         //3个1则停一辆卡车
                }
                if(list.size()%3==0){
                    count+=list.size()/3;         //3个1则停一辆卡车
                }
                list.clear();
            }
        }
        if(list.size()%3!=0){   //7,8,10
            count+=list.size()/3+1;         //3个1则停一辆卡车
        }
        if(list.size()%3==0){
            count+=list.size()/3;         //3个1则停一辆卡车
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
//        String s = sc.nextLine();
//        String[] arr = s.split(",");
        String [] arr={"1","0","1"};
        test(arr);
    }
}
