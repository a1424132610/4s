package com.fours.controller;

import com.fours.util.JsonResult;
import com.fours.util.PageResult ;
import com.fours.domain.User;
import com.fours.query.UserQuery;
import com.fours.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wenbing
 * @Date: 2018/10/17 22:52
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/index")
    public String index() {
        return "/user/user";
    }

    @RequestMapping("/page")
    @ResponseBody//json
    public PageResult list(UserQuery userQuery) {
        return userService.queryByPage(userQuery);
    }

    // user/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public JsonResult delete(Long id) {
        try {
            userService.deleteByPrimaryKey(id);
            return JsonResult.getJsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
    }

    // user/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public JsonResult saveOrUpdate(User user) {
        if (user != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (user.getId() != null) {
                    //修改
                    userService.updateByPrimaryKey(user);
                } else {
                    //添加
                    userService.insert(user);
                }
            return JsonResult.getJsonResult();
            } catch (Exception e) {
                e.printStackTrace();
                return JsonResult.error("删除失败");
            }
        }
        return null;
    }
}
