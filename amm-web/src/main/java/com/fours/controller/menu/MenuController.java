package com.fours.controller.menu;

import com.fours.util.JsonResult;
import com.fours.util.PageResult ;
import com.fours.domain.Menu;
import com.fours.query.MenuQuery;
import com.fours.service.IMenuService;
import com.fours.utils.UserContext;
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
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;
    //查询所有菜单列表
    @RequestMapping("/findMenus")
    @ResponseBody
    public List<Menu> findMenus(){
        return menuService.findMenusByUser(UserContext.getUser());
    }

    @RequestMapping("/index")
    public String index() {
        return "/menu/menu";
    }

    @RequestMapping("/page")
    @ResponseBody//json
    public PageResult list(MenuQuery menuQuery) {
        return menuService.queryByPage(menuQuery);
    }

    // menu/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public JsonResult delete(Long id) {
        try {
            menuService.deleteByPrimaryKey(id);
            return JsonResult.getJsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
    }

    // menu/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public JsonResult saveOrUpdate(Menu menu) {
        if (menu != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (menu.getId() != null) {
                    //修改
                    menuService.updateByPrimaryKey(menu);
                } else {
                    //添加
                    menuService.insert(menu);
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
