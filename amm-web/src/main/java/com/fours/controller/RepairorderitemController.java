package com.fours.controller;

import com.fours.util.JsonResult;
import com.fours.util.PageResult ;
import com.fours.domain.Repairorderitem;
import com.fours.query.RepairorderitemQuery;
import com.fours.service.IRepairorderitemService;
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
@RequestMapping("/repairorderitem")
public class RepairorderitemController {

    @Autowired
    private IRepairorderitemService repairorderitemService;

    @RequestMapping("/index")
    public String index() {
        return "/repairorderitem/repairorderitem";
    }

    @RequestMapping("/page")
    @ResponseBody//json
    public PageResult list(RepairorderitemQuery repairorderitemQuery) {
        return repairorderitemService.queryByPage(repairorderitemQuery);
    }

    // repairorderitem/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public JsonResult delete(Long id) {
        try {
            repairorderitemService.deleteByPrimaryKey(id);
            return JsonResult.getJsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
    }

    // repairorderitem/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public JsonResult saveOrUpdate(Repairorderitem repairorderitem) {
        if (repairorderitem != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (repairorderitem.getId() != null) {
                    //修改
                    repairorderitemService.updateByPrimaryKey(repairorderitem);
                } else {
                    //添加
                    repairorderitemService.insert(repairorderitem);
                }
            return JsonResult.getJsonResult();
            } catch (Exception e) {
                e.printStackTrace();
                return JsonResult.error("明细添加失败");
            }
        }
        return null;
    }
}
