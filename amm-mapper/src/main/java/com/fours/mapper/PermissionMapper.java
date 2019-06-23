package com.fours.mapper;

import com.fours.domain.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {

    //通过权限的id来删除资源
    void deleteById(Permission permission);

    //通过id来查找
    List<Permission> selectPermissionByRoleId(Long id);
}