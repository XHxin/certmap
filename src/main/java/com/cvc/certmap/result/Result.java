package com.cvc.certmap.result;

import com.cvc.certmap.enums.ResultEnum;

import java.util.HashMap;

/**
 * @auther xiehuaxin
 * @create 2018-12-11 14:48
 * @todo
 */
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public Result() {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = (T)new HashMap<>();
    }

    public Result (T data) {
        this.code = ResultEnum.SUCCESS.getCode();
        this.msg = ResultEnum.SUCCESS.getMsg();
        this.data = data;
    }

    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.data = (T)new HashMap<>();
    }

    public Result (int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
