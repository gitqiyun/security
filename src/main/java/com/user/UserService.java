package com.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author qiyun
 * @date 2019/7/15
 * @描述 用户service
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User currentUser = this.userMapper.findUserByLoginName(username);
        if (currentUser != null) {
            return currentUser;
        } else {
            throw new UsernameNotFoundException("该账户不存在");
        }
    }
}
