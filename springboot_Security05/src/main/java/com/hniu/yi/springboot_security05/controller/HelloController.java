package com.hniu.yi.springboot_security05.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/hello")
    public String hello2(){
        return "hello2";
    }

}
