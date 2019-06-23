package com.fours.controller.role;

import com.fours.util.JsonResult;
import com.fours.util.PageResult ;
import com.fours.domain.Permission;
import com.fours.query.PermissionQuery;
import com.fours.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: wenbing
 * @Date: 2018/10/17 22:52
 * @Version 1.0
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/index")
    public String index() {
        return "/permission/permission";
    }

    @RequestMapping("/page")
    @ResponseBody//json
    public PageResult list(PermissionQuery permissionQuery) {
        return permissionService.queryByPage(permissionQuery);
    }

    @RequestMapping("/findAll")
    @ResponseBody//json
    public List<Permission> findAll() {
        return permissionService.selectAll();
    }


    // permission/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public JsonResult delete(Permission permission) {
        try {
            permissionService.deleteById(permission);
            return JsonResult.getJsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public JsonResult saveOrUpdate(Permission permission) {
        if (permission != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (permission.getId() != null) {
                    //修改
                    permissionService.updateByPrimaryKey(permission);
                } else {
                    //添加
                    permissionService.insertPermission(permission);
                }
            return JsonResult.getJsonResult();
            } catch (Exception e) {
                e.printStackTrace();
                return JsonResult.error("添加或修改失败");
            }
        }
        return null;
    }
}
