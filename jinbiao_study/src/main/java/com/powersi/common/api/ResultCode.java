package com.powersi.common.api;

/**
 * 枚举了一些常用API操作码
 * Created by macro on 2019/4/19.
 */
public enum ResultCode implements IErrorCode {

    SUCCESS(200, "操作成功"),

    FAILED(500, "操作失败"),

    VALIDATE_FAILED(402, "参数检验失败"),

    UNAUTHORIZED(401, "未登录或登录已过期，请重新登录~"),

    FORBIDDEN(403, "没有相关权限"),

    NOINFORMATIONCABINET(10001,"无此机柜信息"),

    SIGN_EXPIRED(405,"签名过期"),

    REPLAY_ERROR(406,"重复请求");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
