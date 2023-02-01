package com.powersi.component;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 客户端ws请求类型
 */
public class WSReqTypeConstant {
    // 心跳检测
    public static final int PING = 0;
    // 连接
    public static final int LOGIN = 1;
    // 发送消息
    public static final int SEND_MSG = 2;
    // 签收
    @Deprecated
    public static final int RECEIVE = 3;
    // 切换窗口
    public static final int CHANGE_TAB = 4;
    // 聊天窗口返回至列表页
    public static final int CHANGE_TO_LIST = 41;
    // 聊天窗口返回至列表页前
    public static final int CHANGE_TO_LIST_BEFORE = 42;
    // 取消排队
    public static final int CANCEL_QUEUE = 6;
    // 更新客服/律师在线状态
    public static final int SET_ONLINE_STATE = 7;
    // 关闭连接
    public static final int CLOSE = 8;
    // 工单关联案情
    public static final int BING_WORK_ORDER_CASE = 9;
    // 正在输入
    public static final int TYPING = 10;
    // 连接律师
    public static final int CONNECT_LAWYER = 11;
    // 律师对应的用户列表
    public static final int LAWYER_USER_MAP = 12;
    // 律师端-律师和用户聊天页
    public static final int LAWYER_USER_CHAT = 13;
    // 用户端-消息盒子列表页
    public static final int MESSAGE_BOX_LIST = 14;
    // 用户端-付费订单服务
    public static final int PAID_ORDER_SERVICE = 15;
    // 律师端-发起电话授权
    public static final int AGENT_OPEN_PHONE_AUTH = 16;
    // 律师端-发起无限提问
    public static final int AGENT_OPEN_UNLIMITED_ASK = 17;
    // 撤回消息
    public static final int WITHDRAW_MESSAGE = 18;
    //video视频坐席状态更新
    public static final int VIDEO_STATUS_UPDATE = 19;
    //trtc获取房间号并推送空闲客服
    public static final int VIDEO_GET_ROOMID_AND_IDEL_AGENT = 20;
    //接听来电
    public static final int VIDEO_ANSWER_CALL = 21;
    //用户挂断通话
    public static final int VIDEO_HANG_UP = 22;
    //视频通话用户端登录
    public static final int VIDEO_USER_LOGIN = 23;

    //用户收到房间号后回调
    public static final int VIDEO_GET_ROOMID_CALLBACK = 24;

    public static final int MESSAGE_CONFIRMATION = 25;

    public static final int Meeting_TYPE = 77;

    // 管理平台-在线状态登录
    public static final int MANAGEMENT_AGENT_LOGIN = 100;

    @Getter
    @AllArgsConstructor
    public enum WSMsgType {
        /**
         * 文本
         */
        TEXT(1),
        /**
         * 图片
         */
        IMAGE(2),
        /**
         * 文件
         */
        FILE(3),
        /**
         * 订单
         */
        ORDER(4),
        /**
         * 优惠券
         */
        COUPON(5),
        /**
         * 产品
         */
        PRODUCT(6),
        /**
         * 消息卡片
         */
        CARD(7),
        /**
         * 文书模板/计算器/风险评估等工具
         */
        Tool(8),
        /**
         * 音频
         */
        VOICE(9),
        /**
         * 留言
         */
        LeaveMessage(10),
        /**
         * 视频
         */
        VIDEO(11),
        /**
         * 商品
         */
        COMMODITY(12),
        /**
         * 诉讼咨询卡
         */
        LitigationAdvisoryCard(13);
        private int type;
    }
}
