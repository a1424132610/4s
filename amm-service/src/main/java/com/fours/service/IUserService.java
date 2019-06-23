package com.fours.service;

import com.fours.domain.User;

import java.util.Set;

public interface IUserService extends IBaseService<User> {
    //根据账号来查找用户名
    User selectUserByUsername(String username);
    //通过user来查找权限
    Set<String> selectPermissionById(User user);

}
