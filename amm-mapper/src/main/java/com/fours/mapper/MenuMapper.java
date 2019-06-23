package com.fours.mapper;

import com.fours.domain.Menu;
import com.fours.domain.User;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu>{

    List<Menu> findByMenus();
    //根据用户来查找菜单
    List<Menu> findMenusByUser(User user);
}