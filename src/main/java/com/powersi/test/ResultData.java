package com.powersi.test;

import java.util.List;

public class ResultData<T> {
    private int pageSize; //每页显示记录数
    private int pageNo ;//页数
    private int total; //总记录数
    private int totalNo; //总页数
    private int startRow; //开始
    private int endRow; //结束

    private List T;


    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalNo() {
        if(total%pageSize==0){
            return total/pageSize;
        }else{
            return total/pageSize+1;
        }
    }

    public void setTotalNo(int totalNo) {
        this.totalNo = totalNo;
    }

    public List getT() {
        return T;
    }

    public void setT(List t) {
        T = t;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", total=" + total +
                ", totalNo=" + totalNo +
                ", startRow=" + startRow +
                ", endRow=" + endRow +
                ", T=" + T +
                '}';
    }
}
