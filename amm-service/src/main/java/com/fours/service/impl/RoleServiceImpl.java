package com.fours.service.impl;


import com.fours.mapper.BaseMapper;
import com.fours.service.impl.IBaseServiceImpl;
import com.fours.domain.Role;
import com.fours.mapper.RoleMapper;
import com.fours.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: wenbing
 * @Date: 2018/10/17 14:45
 * @Version 1.0
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class RoleServiceImpl extends IBaseServiceImpl<Role> implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    //钩子方法，用来获取到对应的.xml文档的，只要继承baseService都需要写
    @Override
    BaseMapper getBaseMapper(){
        return roleMapper;
    }

    /**
     * 根据id查询权限
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Role> selectPermissionByRoleId(Long id) {
        return roleMapper.selectPermissionById(id);
    }

    /**
     * 添加角色
     * @param role
     */
    @Override
    public void insertRole(Role role) {
        //先将角色信息插入进数据库对应的表中，并且获得id
        roleMapper.insert(role);
        //再根据对应的角色id，插入到权限表当中
        roleMapper.insertPermission(role);
    }

    @Override
    public void updatePermission(Role role) {
        //根据传进来的role先删除掉中间表的权限
        roleMapper.deletePermissionById(role.getId());
        //将权限插入进去
        roleMapper.insertPermission(role);
    }


}
