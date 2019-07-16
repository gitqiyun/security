package com.security.authentication;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author qiyun
 * @date 2019/7/16
 * @描述 自定义加密方式，注册时和spring security验证要一致
 */
public class MyPasswordEncoder  implements PasswordEncoder
{

    @Override
    public String encode(CharSequence rawPassword) {
        return "";
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}