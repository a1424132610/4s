package com.fours.service;

import com.fours.service.IBaseService;
import com.fours.domain.Role;

import java.util.List;

public interface IRoleService extends IBaseService<Role> {


    //根据id查询对应的权限
    List<Role> selectPermissionByRoleId(Long id);

    //插入对应的permission权限
    void insertRole(Role role);

    //修改role的权限
    void updatePermission(Role role);
}
