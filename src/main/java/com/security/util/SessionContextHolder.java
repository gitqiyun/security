package com.security.util;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * @author qiyun
 * @date 2019/7/19
 * @描述 获取用户原理理解
 */
public class SessionContextHolder {
    public static User getCurrentUser()
    {

        return getCurrentUserInSpringSecurity();
    }

    private static User getCurrentUserInSpringSecurity()
    {
        /**
         * SecurityContext:当前线程上下文，当前线程用户信息authentication就保存在里面
         * SecurityContextHolder：整个应用就一个SecurityContextHolder，保存多个SecurityContext
         *
         * 原理过程：每个请求即一个线程进入应用，都会根据session等相关值封装成authentication保存到SecurityContext，再将
         * SecurityContext保存到SecurityContextHolder中的，相当一个map，当前线程作为主键，当前的SecurityContext作为值
         * (具体是Threadlocal实现的)
         *
         * 请求request进入系统，从request中获取session,从session获取上下文SecurityContext，如果没有登录则登录将用户信息封装为
         * authentication，将SecurityContext保存到SecurityContextHolder中，相当一个map，当前线程作为主键，当前的SecurityContext作为值
         * 如果登录了，即SecurityContext不为空，直接保存到SecurityContextHolder中，每次请求都会保存请求结束后会清空。
         *
         * HttpRequestResponseHolder holder = new HttpRequestResponseHolder(request,response);
         * SecurityContext contextBeforeChainExecution = repo.loadContext(holder);
         * Object contextFromSession = httpSession.getAttribute(springSecurityContextKey);
         *
         * 详解
         * https://www.cnblogs.com/longfurcat/p/9417912.html
         */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
        {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof User)
            {
                return (User) principal;
            }
        }
        return null;
    }

}
