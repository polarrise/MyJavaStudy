package com.jinbiao.javaStudy.WorkTest;

/**
 * 订单状态
 */
public enum CirculateStatusToOrderStatusEnum {
    ORDER_WAIT_TOPAY(0,0),   //传入流转状态0查订单状态为0;    待支付
    ORDER_WAITTO_ACCEPT_ORDER(1,1),   //传入1查订单状态为1;    待接单
    ORDER_FINISHED(7,5),          //传入7查订单状态为5; 已完成
    ORDER_REFUSED(8,7),         //传入8查订单状态为7; 已拒绝
    ORDER_CANCLED(9,6);      //传入9查订单状态为6; 已取消                如果传入的流转状态不是0/1/7/8/9  返回订单列表则代码处理

    private Integer circulateStatus;//流转状态
    private Integer orderStatus; //订单状态


    CirculateStatusToOrderStatusEnum(Integer circulateStatus, Integer orderStatus){
        this.circulateStatus=circulateStatus;
        this.orderStatus=orderStatus;
    }

    public Integer getDesc(){
        return circulateStatus;
    }

    public Integer getCode(){
        return orderStatus;
    }
}
