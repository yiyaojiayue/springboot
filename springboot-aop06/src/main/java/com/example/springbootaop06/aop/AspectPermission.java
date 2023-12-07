package com.example.springbootaop06.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component // 注入容器
@Aspect    // 定义切面
public class AspectPermission {

    @Pointcut("@annotation(com.example.springbootaop06.aop.ApiPermission)")  //设置切入点
    public void point(){

    }

    @Before("point()")
    public void before(){

        System.out.println("通过session或者redis获取我们的用户token");
        System.out.println("通token解析出用户id");
        System.out.println("再去检查是否具有权限");
    }
}
