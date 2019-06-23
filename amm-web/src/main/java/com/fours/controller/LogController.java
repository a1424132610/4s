package com.fours.controller;

import com.fours.service.ILogService;
import com.fours.util.JsonResult;
import com.fours.util.PageResult ;
import com.fours.domain.Log;
import com.fours.query.LogQuery;
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
@RequestMapping("/log")
public class LogController {

    @Autowired
    private ILogService logService;

    @RequestMapping("/index")
    public String index() {
        return "/log/log";
    }

    @RequestMapping("/page")
    @ResponseBody//json
    public PageResult list(LogQuery logQuery) {
        return logService.queryByPage(logQuery);
    }

    // log/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public JsonResult delete(Long id) {
        try {
            logService.deleteByPrimaryKey(id);
            return JsonResult.getJsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
    }

    // log/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public JsonResult saveOrUpdate(Log log) {
        if (log != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (log.getId() != null) {
                    //修改
                    logService.updateByPrimaryKey(log);
                } else {
                    //添加
                    logService.insert(log);
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
