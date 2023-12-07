package com.hniu.yi.springboot_security05.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/*
端口被占用了怎么解决
netstat -ano | findstr "80"
taskkill /pid   31476 /f
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 配置没有权限访问跳转自定义页面
        http.exceptionHandling().accessDeniedPage("/unauth.html");

        // 首页所有可以访问,其他页需要有对应的权限
        http.authorizeRequests()
                .antMatchers("/pages/index.html").permitAll()
                .antMatchers("/pages/books.html")
                .hasRole("admin");
        // 没有权限默认会到登入页面，需要开启登入的页面
        // /login
        http.formLogin();

        // 注销登入
        // logout
        http.logout();

        // 注销之后跳转的首页
        http.logout().logoutSuccessUrl("/");

    }

    // 认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //  auth.jdbcAuthentication() 数据库认证
        // 内存认证
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("yi")
                .password(new BCryptPasswordEncoder().encode("030805"))
                .roles("admin");

    }
}
