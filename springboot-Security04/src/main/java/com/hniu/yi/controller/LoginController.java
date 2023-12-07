package com.hniu.yi.controller;

import com.hniu.yi.controller.utils.R;
import com.hniu.yi.domain.User;
import com.hniu.yi.service.LoginServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginServer loginServer;

    @PostMapping("/user/login")
    public R login(@RequestBody User user){
      return loginServer.login(user);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

}
