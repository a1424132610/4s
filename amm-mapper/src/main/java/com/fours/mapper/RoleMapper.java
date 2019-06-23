package com.fours.mapper;

import com.fours.domain.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    //根据id查找对应的权限
    List<Role> selectPermissionById(Long id);
    //插入对应的permission权限
    void insertPermission(Role role);
    //删除中间表的权限
    void deletePermissionById(Long id);

}