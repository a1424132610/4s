package com.fours.service;

import com.fours.domain.Permission;

public interface IPermissionService extends IBaseService<Permission> {

    //插入权限的同时插入resource资源
    void insertPermission(Permission permission);
    //通过传入的对象来删除权限
    void deleteById(Permission permission);

}
