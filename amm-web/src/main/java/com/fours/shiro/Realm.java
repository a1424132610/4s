package com.fours.shiro;

import com.fours.domain.User;
import com.fours.service.IUserService;
import com.fours.utils.MD5Util;
import com.fours.utils.UserContext;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 自定义权限过滤器
 */
public class Realm extends AuthorizingRealm {

    //自动注入
    @Autowired
    private IUserService userService;

    //权限授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //通过user来查找出权限
        Set<String> set = userService.selectPermissionById(UserContext.getUser());
        //查询权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //将权限设置进对象中
        simpleAuthorizationInfo.setStringPermissions(set);
        return simpleAuthorizationInfo;
    }

    //登陆认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获得前台传进来的账号
        String username = (String)authenticationToken.getPrincipal();
        //后台数据库查询账号
        User user = userService.selectUserByUsername(username);
        //先判断是否有用户
        if (user == null) {
            return null;
        }
        //密码加密
        ByteSource source = ByteSource.Util.bytes(MD5Util.SALT);
        //进行验证
        return new SimpleAuthenticationInfo(user,user.getPassword(),source,getName());
    }
}
