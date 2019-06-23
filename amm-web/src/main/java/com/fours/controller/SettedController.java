package com.fours.controller;

import com.fours.util.JsonResult;
import com.fours.util.PageResult ;
import com.fours.domain.Setted;
import com.fours.query.SettedQuery;
import com.fours.service.ISettedService;
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
@RequestMapping("/setted")
public class SettedController {

    @Autowired
    private ISettedService settedService;

    @RequestMapping("/index")
    public String index() {
        return "/setted/setted";
    }

    @RequestMapping("/page")
    @ResponseBody//json
    public PageResult list(SettedQuery settedQuery) {
        return settedService.queryByPage(settedQuery);
    }

    // setted/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public JsonResult delete(Long id) {
        try {
            settedService.deleteByPrimaryKey(id);
            return JsonResult.getJsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("删除-失败");
        }
    }

    // setted/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public JsonResult saveOrUpdate(Setted setted) {
        if (setted != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (setted.getId() != null) {
                    //修改
                    settedService.updateByPrimaryKey(setted);
                } else {
                    //添加
                    settedService.insert(setted);
                }
            return JsonResult.getJsonResult();
            } catch (Exception e) {
                e.printStackTrace();
                return JsonResult.error("结算失败");
            }
        }
        return null;
    }
}
