package com.cvc.certmap.exception;

import com.cvc.certmap.enums.ResultEnum;

/**
 * @auther xiehuaxin
 * @create 2018-12-24 11:32
 * @todo
 */
public class ApiException extends RuntimeException {


    private Integer code;
    private String msg;

    public ApiException(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
