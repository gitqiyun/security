package com.security.authentication;

import com.user.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author qiyun
 * @date 2019/7/15
 * @描述 自定义认证用户
 *
 * 认证是由 AuthenticationManager 来管理的，但是真正进行认证的是 AuthenticationManager
 * 中定义的 AuthenticationProvider。AuthenticationManager 中可以定义有多个 AuthenticationProvider
 */
public class MySecurityAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;

    public MySecurityAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        return new UsernamePasswordAuthenticationToken(name, password);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
