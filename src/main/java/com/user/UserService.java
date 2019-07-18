package com.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        if (currentUser == null) {
            throw new UsernameNotFoundException("该账户不存在");
        } else {
            //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (Role role : currentUser.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            //返回的的user只要是UserDetails的子类就行，可以自定义user实现UserDetails，也可以直接用框架里的这个user
            return new org.springframework.security.core.userdetails.User
                    (currentUser.getUsername(),currentUser.getPassword(),authorities);
        }
    }
}
