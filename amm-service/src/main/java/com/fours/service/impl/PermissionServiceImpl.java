package com.fours.service.impl;


import com.fours.mapper.BaseMapper;
import com.fours.mapper.ResourceMapper;
import com.fours.domain.Permission;
import com.fours.mapper.PermissionMapper;
import com.fours.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: wenbing
 * @Date: 2018/10/17 14:45
 * @Version 1.0
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class PermissionServiceImpl extends IBaseServiceImpl<Permission> implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    //注入resourceMapper的mapper接口
    @Autowired
    private ResourceMapper ResourceMapper;

    @Override
    BaseMapper getBaseMapper(){
        return permissionMapper;
    }


    /**
     * 添加
     * @param permission
     */
    @Override
    public void insertPermission(Permission permission) {
        //先插入资源路径等，然后获得返回的id，插入到permission的表中
        ResourceMapper.insert(permission.getResource());
        //插入资源后再插入到permission中
        permissionMapper.insert(permission);
    }

    @Override
    public void deleteById(Permission permission) {
        permissionMapper.deleteById(permission);
    }
}
