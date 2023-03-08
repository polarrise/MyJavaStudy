package com.jinbiao.cloud.im.domain.ws;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString
public class WSMsgResult<T> {
    private int code;
    private String msg;
    private T data;
    public static final int LOGIN = 0;  // 普通的文本消息
    public static final int TEXT_MSG = 1;  // 普通的文本消息
    public static final int QUEUE_MSG = 2;  // 队列推送消息
    public static final int CONNECT_LAWYER = 3;  // 转接律师成功消息
    public static final int CHAT_OVER_MSG = 4;  // 聊天结束通知
    public static final int FAIL_MSG = 5;  // 消息失败
    public static final int HISTORY_MSG = 10;  // 历史消息记录
    public static final int WAITING_COUNT_MSG = 11;  // 排队等待人数
    public static final int TIMEOUT_BEFORE_MSG = 12;  // 即将超时提醒（提前30秒）
    public static final int TIMEOUT_MSG = 13;  // 超时提醒
    public static final int CANCEL_SELECTED_MSG = 14;  // 取消助理/律师端用户选中状态
    public static final int SERVER_COUNTS = 15;  // 给助理推送当前服务人数
    public static final int NO_SERVER_LAWYER = 16;  // 给用户推送当前无律师在线消息
    public static final int SELECT_AGENT_SUC = 17;  // 给用户推送选择助理成功的消息
    public static final int CANCEL_QUEUE = 19;  // 给用户推送取消排队关闭socket通知
    public static final int FORCE_DISCONNECT = 20;  // 给助理推送掉线提醒(可能是重连或者被挤掉的情况)
    public static final int HEART_BEAT = 21;  // 给助理推送心跳检测提醒
    public static final int CLEAR_BEFORE_WORK_ORDER_MSG = 22;  // 用户从离线变成会话中,不渲染离线中上个工单的聊天消息
    public static final int TEXT_MSG_TO_SENDER = 23;  // 普通的文本消息-给发送者
    public static final int TYPING = 24;      //正在输入
    public static final int LAWYER_USER_LIST = 25;      // 律师维护的用户列表
    public static final int LAWYER_USER_CHAT = 26;      // 律师和用户聊天页
    public static final int USER_LAWYER_LIST = 27;      // 用户消息盒子列表页
    public static final int CONNECT_LAWYER_FAIL = 28;  // 连接律师失败
    public static final int USER_ONLINE_TO_OFFLINE = 29;   // 结束会话/关闭连接
    public static final int USER_LOSE_ASK_COUNT = 30;   // 用户剩余提问次数
    public static final int USER_PAID_ORDER_FINISH = 31;   // 用户付款咨询结束完成
    public static final int USER_LAST_MIN = 32;   // 用户付款咨询最后三分钟提示
    public static final int USER_ASK_END = 33;   // 用户提问次数已用尽
    public static final int USER_NEXT_PAID_ORDER = 34;   // 用户下一轮付费服务
    public static final int USER_PAID_ORDER_STATUS = 35;   // 用户当前付费服务状态
    public static final int USER_PHONE_AUTH_STATUS = 36;   // 用户电话授权状态
    public static final int USER_UNLIMITED_ASK_STATUS = 37;   // 用户无限提问状态
    public static final int LOGIN_FAIL = 38;        //用户/客服/律师登录IM失败
    public static final int WITHDRAW_MESSAGE = 39;      //消息撤回
    public static final int PAID_USER_QUEUE_TIMEOUT = 40;      //付费用户排队超过三分钟
    public static final int SEND_USERINFO_AND_ROOMID = 41; // 获取空闲的客服信息和房间号
    public static final int SEND_OVER_TIME_MSG = 42; // 60s钟后推送消息告诉用户稍后再拨
    public static final int SEND_HANG_UP_MSG = 43; //推送视频通话挂断信息
    public static final int SEND_CALL_REQUEST = 44;//推送来电请求
    public static final int CANCLE_CALL_REQUEST = 45; //取消来电请求
    public static final int ANSWER_CALLED = 46; // 已经接听来电
    public static final int SEND_WORKERORDER_ID = 47; // 发送工单信息
    public static final int SEND_MESSAGE_SUCCESS = 48; // 消息发送成功
    public static final int CHECK_BUSY_STATUS = 49; //校验忙碌状态
    public static final int User_Received_Meeting_Code = 88;  // 用户确认收到视频会议消息的code

    // 管理平台--在线状态推送
    public static  final int MANAGEMENT_AGENT_ON_STATUS = 101;
    public static  final int MANAGEMENT_AGENT_SERVER_COUNTS = 102;
    public static  final int MANAGEMENT_AGENT_SKILL_LIST = 103;

    @Getter
    @AllArgsConstructor
    public enum SystemMsg {
        // 系统异常及其他特殊情况
        SYSTEM_OVER(31, "系统将在3分钟后自动结束对话"),// 对话结束
        SYSTEM_QUEUE(32, "坐席全忙，已进入等待队列，您前面还有2人，请稍等片刻。"),// 排队
        SYSTEM_CANCEL_QUEUE(33, "ss"),// 取消排队
        SYSTEM_NONE_WORK_TIME(34, "sorry，律师的工作时间为上午9:00 - 下午5:30，请在工作时间内进行咨询，谢谢！"),// 非工作时间
        SYSTEM_NO_REPLY(35, "由于长时间没有对话。系统将在3分钟后自动结束对话");// 长时间未回复
        private int code;
        private String message;
    }

    protected WSMsgResult() {
    }

    public WSMsgResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> WSMsgResult<T> success(int code, String msg, T data) {
        return new WSMsgResult<>(code, msg, data);
    }

    public static <T> WSMsgResult<T> fail(String msg) {
        return new WSMsgResult<>(FAIL_MSG, msg, null);
    }

    public static <T> WSMsgResult<T> exception(String msg) {
        return new WSMsgResult<>(FAIL_MSG, msg, null);
    }

    public static <T> WSMsgResult<T> SYSTEM_OVER() {
        return new WSMsgResult<>(SystemMsg.SYSTEM_OVER.getCode(), SystemMsg.SYSTEM_OVER.getMessage(), null);
    }

    public static <T> WSMsgResult<T> SYSTEM_QUEUE() {
        return new WSMsgResult<>(SystemMsg.SYSTEM_QUEUE.getCode(), SystemMsg.SYSTEM_QUEUE.getMessage(), null);
    }

    public static <T> WSMsgResult<T> SYSTEM_CANCEL_QUEUE() {
        return new WSMsgResult<>(SystemMsg.SYSTEM_CANCEL_QUEUE.getCode(), SystemMsg.SYSTEM_CANCEL_QUEUE.getMessage(), null);
    }

    public static <T> WSMsgResult<T> SYSTEM_NONE_WORK_TIME() {
        return new WSMsgResult<>(SystemMsg.SYSTEM_NONE_WORK_TIME.getCode(), SystemMsg.SYSTEM_NONE_WORK_TIME.getMessage(), null);
    }

    public static <T> WSMsgResult<T> SYSTEM_NO_REPLY() {
        return new WSMsgResult<>(SystemMsg.SYSTEM_NO_REPLY.getCode(), SystemMsg.SYSTEM_NO_REPLY.getMessage(), null);
    }
}
