package com.fours.mapper;

import com.fours.domain.Menu;
import com.fours.domain.User;
import com.fours.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mapper.xml")
public class MenuMapperTest {

    @Autowired
    private MenuMapper mapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void findByMenus() {
        List<User> users = userMapper.selectAll();


        for (User user : users) {
            String s = MD5Util.createMd5("aa");
            user.setPassword(s);
            userMapper.updateByPrimaryKey(user);
        }
    }
}