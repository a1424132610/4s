package com.fours.service.impl;


import com.fours.domain.User;
import com.fours.mapper.BaseMapper;
import com.fours.service.impl.IBaseServiceImpl;
import com.fours.domain.Menu;
import com.fours.mapper.MenuMapper;
import com.fours.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wenbing
 * @Date: 2018/10/17 14:45
 * @Version 1.0
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class MenuServiceImpl extends IBaseServiceImpl<Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    BaseMapper getBaseMapper(){
        return menuMapper;
    }

    @Override
    public List<Menu> findMenusByUser(User user) {
        List<Menu> byMenus = menuMapper.findMenusByUser(user);
        return menuMapper.findMenusByUser(user);
    }
}
