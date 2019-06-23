package com.fours.mapper;

import com.fours.domain.Role;
import com.fours.domain.User;
import java.util.List;
import java.util.Set;

public interface UserMapper extends BaseMapper<User> {

    //通过用户名来查找user
    User selectUserByUsername(String username);

    //通过user来查找权限
    Set<String> selectPermissionByUser(User user);

}