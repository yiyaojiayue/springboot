package com.example.springbootaop06.controller;


import com.example.springbootaop06.aop.ApiPermission;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test01Controller {



    @RequestMapping("/update")
    @ApiPermission       // aop认证
    public void update(){
        // 使用aop去在这个方法之前去检验用户是否具有这个权限
        System.out.println("执行操作！！");

    }

}
