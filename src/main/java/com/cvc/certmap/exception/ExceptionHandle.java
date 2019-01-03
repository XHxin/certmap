package com.cvc.certmap.exception;

import com.cvc.certmap.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @auther xiehuaxin
 * @create 2018-12-24 11:43
 * @todo
 */
@RestControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler
    public Result handler(Exception e) {
        if(e instanceof ApiException) {
            ApiException exception = (ApiException) e;
            return new Result(exception.getCode(),exception.getMessage());
        }
        return new Result(500,e.getMessage());
    }
}
