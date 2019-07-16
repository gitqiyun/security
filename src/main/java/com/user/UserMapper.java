package com.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author qiyun
 * @date 2019/7/15
 * @描述
 */
@Service
public class UserMapper {
    User findUserByLoginName(String username){
        User user=null;
        if("admin".equals(username)){
            user=new User();
            user.setUsername(username);
            //注册时密码加密要与spirng security配置中心配置的加密方式一致
            user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        }
        return user;

    }
}
