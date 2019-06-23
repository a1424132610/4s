package com.fours.utils;


import com.fours.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * shiro权限 将user用户装入上下文
 */
public class UserContext {

    private static final String Login = "LoginUser";

    public static void setUser(User user){
        //获得subject主对象
        Subject subject = SecurityUtils.getSubject();
        //将其设置进域对象中
        subject.getSession().setAttribute(Login, user);
    }

    public static User getUser(){
        //重域对象中获取出用户对象
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getSession().getAttribute(Login);
        return user;
    }

}
