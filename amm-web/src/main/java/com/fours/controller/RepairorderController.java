package com.fours.controller;

import com.fours.util.JsonResult;
import com.fours.util.PageResult ;
import com.fours.domain.Repairorder;
import com.fours.query.RepairorderQuery;
import com.fours.service.IRepairorderService;
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
@RequestMapping("/repairorder")
public class RepairorderController {

    @Autowired
    private IRepairorderService repairorderService;

    @RequestMapping("/index")
    public String index() {
        return "/repairorder/repairorder";
    }

    @RequestMapping("/page")
    @ResponseBody//json
    public PageResult list(RepairorderQuery repairorderQuery) {
        return repairorderService.queryByPage(repairorderQuery);
    }

    // repairorder/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public JsonResult delete(Long id) {
        try {
            repairorderService.deleteByPrimaryKey(id);
            return JsonResult.getJsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
    }

    // repairorder/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public JsonResult saveOrUpdate(Repairorder repairorder) {
        if (repairorder != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (repairorder.getId() != null) {
                    //修改
                    repairorderService.updateByPrimaryKey(repairorder);
                } else {
                    //添加
                    repairorderService.insert(repairorder);
                }
            return JsonResult.getJsonResult();
            } catch (Exception e) {
                e.printStackTrace();
                return JsonResult.error("保存失败");
            }
        }
        return null;
    }
}
