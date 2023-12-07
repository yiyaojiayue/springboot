package com.hniu.yi.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ObjectExceptionAdvice {

    @ExceptionHandler
    public R doOtherException(Exception e){
        /*
         记录日志
         发送消息
         发送邮件给开发人员，ex对象发送给开发人员
         */
    e.printStackTrace();
        return new R("系统错误,请稍后在试！");
    }


}
