package com.fours.mapper;

import com.fours.domain.Permission;
import com.fours.domain.Role;
import com.fours.domain.User;
import com.fours.query.BaseQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mapper.xml")
public class PermissionMapperTest {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    public void insert() {
        BaseQuery query = new BaseQuery();
        List<Role> list = roleMapper.selectByPage(query);
        list.forEach(e -> System.out.println(e));
        /*List<Permission> list = permissionMapper.selectPermissionByRoleId(5L);
        for (Permission permission : list) {
            System.out.println(permission);
        }*/
    }
}