package com.hniu.yi.service.impl;

import com.hniu.yi.controller.utils.JwtUtil;
import com.hniu.yi.controller.utils.R;
import com.hniu.yi.domain.User;
import com.hniu.yi.service.LoginServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServerImpl implements LoginServer {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public R login(User user) {

        // AuthenticationManager authenticate经行用户验证

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        // 如果认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("登入失败");
        }
        // 如果认证通过了，使用userid生成一个jwt jwt存入R返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userID = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userID);

        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        // 把完整的用户信息存入redis中，userid作为key

        return new R(true, map);
    }
}
