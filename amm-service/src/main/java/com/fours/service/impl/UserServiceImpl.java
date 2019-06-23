package com.fours.service.impl;


import com.fours.mapper.BaseMapper;
import com.fours.service.impl.IBaseServiceImpl;
import com.fours.domain.User;
import com.fours.mapper.UserMapper;
import com.fours.service.IUserService;
import com.fours.util.LogAnno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @Author: wenbing
 * @Date: 2018/10/17 14:45
 * @Version 1.0
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class UserServiceImpl extends IBaseServiceImpl<User> implements IUserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    BaseMapper getBaseMapper(){
        return userMapper;
    }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public Set<String> selectPermissionById(User user) {
        return userMapper.selectPermissionByUser(user);
    }
}
