package com.fours.controller.role;

import com.fours.util.JsonResult;
import com.fours.util.PageResult ;
import com.fours.domain.Role;
import com.fours.query.RoleQuery;
import com.fours.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wenbing
 * @Date: 2018/10/17 22:52
 * @Version 1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/index")
    public String index() {
        return "/role/role";
    }

    @RequestMapping("/page")
    @ResponseBody//json
    public PageResult list(RoleQuery roleQuery) {
        return roleService.queryByPage(roleQuery);
    }

    // role/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public JsonResult delete(Long id) {
        try {
            roleService.deleteByPrimaryKey(id);
            return JsonResult.getJsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
    }

    // role/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public JsonResult saveOrUpdate(Role role) {

        if (role != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (role.getId() != null) {
                    //修改
                    roleService.updatePermission(role);
                } else {
                    //添加
                    roleService.insertRole(role);
                }
            return JsonResult.getJsonResult();
            } catch (Exception e) {
                e.printStackTrace();
                return JsonResult.error("修改失败");
            }
        }
        return null;
    }
}
