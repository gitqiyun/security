package com.security;

import com.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author qiyun
 * @date 2019/7/15
 * @描述 web应用security配置中心,demo中除了获取用户是自定义的，登陆页、加密、登陆成功和失败处理等等都是采用默认设置
 */
@EnableWebSecurity
public class MyWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    //用户登录认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //用户数据来源用userService获取，密码加密用默认的BCryptPasswordEncoder
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }


    //配置url权限和http相关的内容
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/visitor/**").permitAll()
                .anyRequest().authenticated()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin().and()
                .httpBasic();

    }
}
