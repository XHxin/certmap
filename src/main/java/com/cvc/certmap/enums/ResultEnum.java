package com.cvc.certmap.enums;

/**
 * @author leo
 * @date 2018/7/13 10:37
 */
public enum ResultEnum {

    UNKONW_ERROR(-1,"未知错误"),
    SUCCESS(200,"操作成功"),
    CODE_INVALID(210,"code失效"),
    DO_NOT_REPETITION(211,"请勿重复操作"),
    TOKEN_INVALID(220,"您的token已失效"),
    FAILURE(4000,"找不到资源"),
    WRONG_REQUEST_PARAMETER(4001,"请求参数有误"),
    TOKEN_MISS(4002,"token参数缺失"),
    BIND_MOBILE(4003,"请绑定手机号码"),
    MOBILE_IS_IN_USE(4004,"该手机号已被其他账号绑定"),
    REQUIRES_USER_AUTHENTICATION(401,"请求需要用户验证"),
    USERNAME_PASSWORD_ERROR(4011,"用户名或密码错误"),
    REQUIRED_METHOD_ERROR(402,"请求方法错误"),
    TIME_OUT(408,"请求超时"),
    OPERATION_FAILURE(500,"服务器操作异常"),
    HANDLE_FAILURE(505,"操作失败")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
