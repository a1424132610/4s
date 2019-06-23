package com.fours.service;

import com.fours.domain.User;
import com.fours.service.IBaseService;
import com.fours.domain.Menu;

import java.util.List;

public interface IMenuService extends IBaseService<Menu> {
    List<Menu> findMenusByUser(User user);
}
