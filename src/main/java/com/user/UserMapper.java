package com.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiyun
 * @date 2019/7/15
 * @描述
 */
@Service
public class UserMapper {
    User findUserByLoginName(String username){
        User user=null;
        List<Role> roles=new ArrayList<>();
        //模拟数据库数据
        if("admin".equals(username)){
            user=new User();
            user.setUsername(username);
            //注册时密码加密要与spirng security配置中心配置的加密方式一致
            user.setPassword(new BCryptPasswordEncoder().encode("admin123"));
            Role role =new Role();
            role.setName("ADMIN");
            roles.add(role);
            user.setRoles(roles);
        }else if ("user".equals(username)){
            user=new User();
            user.setUsername(username);
            //注册时密码加密要与spirng security配置中心配置的加密方式一致
            user.setPassword(new BCryptPasswordEncoder().encode("user123"));
            Role role =new Role();
            role.setName("USER");
            roles.add(role);
            user.setRoles(roles);
        }
        return user;

    }
}
