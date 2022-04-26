package com.powersi.test;

import java.util.ArrayList;
import java.util.List;

public class Test<E> {
    public ResultData<E> ListSplit(List<E> list,int pageNo,int pageSize) {
        List<E> newList=null;
        int total=list.size();
        newList=list.subList(pageSize*(pageNo-1), ((pageSize*pageNo)>total?total:(pageSize*pageNo)));  //0,10   10,20
        ResultData<E> resultData=new ResultData<E>();
        resultData.setStartRow(pageSize*(pageNo-1)+1);     //开始索引
        resultData.setEndRow((pageSize*pageNo)>total?total:(pageSize*pageNo));   //结束索引
        resultData.setPageNo(pageNo);         //总页数
        resultData.setPageSize(pageSize);    //每页显示记录数
        resultData.setTotal(total);         //总记录数
        resultData.setTotalNo(resultData.getTotalNo());           //总页数
        resultData.setT(newList);         //结果集
        return resultData;
    }

    public static void main(String[] args) {
        List<Integer> list =new ArrayList<>();
        for(int i=1;i<=55;i++){
            list.add(i);
        }
        Test<Integer> a=new Test<>();
        ResultData<Integer> test=a.ListSplit(list,2,10);   //返回分页的查询结果

        System.out.println(test);
    }
}
