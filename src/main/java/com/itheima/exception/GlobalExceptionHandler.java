package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result ex(Exception e){
        e.printStackTrace();
        return Result.error("对不起，操作失败，请联系管理员");
    }

    @ExceptionHandler
    public Result nullPointerExceptionHandler(NullPointerException e){
        log.info(e.getMessage());
        return Result.error("对不起，操作失败，请联系管理员");
    }
}
