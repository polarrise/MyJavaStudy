package com.jinbiao.lambdas.study02.functionAsIn;

import java.util.function.Supplier;

public class GetMax {
    public static int getMaxNum(Supplier<Integer> supplier){
        return supplier.get();
    }

    public static void main(String[] args) {
        int[] arr = {-1,0,1,2,3};
        int maxValue = getMaxNum(()->{
            int max = arr[0];
            for(int i=0;i<arr.length;i++){
                if(arr[i]>max){
                    max = arr[i];
                }
            }
            return max;
        });
        System.out.println("数组元素的最大值是："+maxValue);
    }
}
