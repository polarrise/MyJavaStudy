package jinbiao.huaweiOd.kaoshi;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void test(String [] arr){
        List<String> list=new ArrayList<String>();

        for (int i=0;i<arr.length;i++){
            for (int j=i+1;j<arr.length;j++){
                String[] pre = arr[i].split(" ");
                String[] next = arr[j].split(" ");
                if(Integer.valueOf(pre[0])<Integer.valueOf(next[0]) && Integer.valueOf(pre[1])<=Integer.valueOf(next[1])  && Integer.valueOf(pre[1])>Integer.valueOf(next[0])){
                    list.add(Integer.valueOf(next[0])+""+Integer.valueOf(pre[1]));  //c b  交集
                }
                if(Integer.valueOf(pre[1])== Integer.valueOf(next[0])){
                    list.add(Integer.valueOf(pre[1])+""+Integer.valueOf(next[0]));  //c=b   交点
                }
                if(Integer.valueOf(pre[0])>= Integer.valueOf(next[0]) &&Integer.valueOf(pre[1])<= Integer.valueOf(next[1])){
                    list.add(Integer.valueOf(pre[0])+""+Integer.valueOf(pre[1]));  //取子集
                }
            }
        }
        System.out.println(list);
        String [] reArr = new String[list.size()];
        for(int i=0;i<list.size();i++){
            reArr[i]=list.get(i);
        }
        
        List<String> relist=new ArrayList<String>();
        for (int i=0;i<reArr.length-1;i++){
                String[] pre = reArr[i].split("");
                String[] next = reArr[i+1].split("");
                if(Integer.valueOf(pre[0])<Integer.valueOf(next[0]) && Integer.valueOf(pre[1])<=Integer.valueOf(next[1])  && Integer.valueOf(pre[1])>Integer.valueOf(next[0])){
                    relist.add(Integer.valueOf(next[0])+""+Integer.valueOf(pre[1]));  //c b  交集
                }
                if(Integer.valueOf(pre[1])== Integer.valueOf(next[0])){
                    relist.add(Integer.valueOf(pre[1])+""+Integer.valueOf(next[0]));  //c=b   交点
                }
                if(Integer.valueOf(pre[0])>= Integer.valueOf(next[0]) &&Integer.valueOf(pre[1])<= Integer.valueOf(next[1])){
                    relist.add(Integer.valueOf(pre[0])+""+Integer.valueOf(pre[1]));  //取子集
                }
        }
        System.out.println(relist);
    }

    public static void main(String[] args) {
        String [] arr={"0 3","1 3","3 5","3 6"};
        test(arr);
    }
}
