package com.fours.controller;

import com.fours.util.JsonResult;
import com.fours.util.PageResult ;
import com.fours.domain.Maintainer;
import com.fours.query.MaintainerQuery;
import com.fours.service.IMaintainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 维修员列表
 * @Author: wenbing
 * @Date: 2018/10/17 22:52
 * @Version 1.0
 */
@Controller
@RequestMapping("/maintainer")
public class MaintainerController {

    @Autowired
    private IMaintainerService maintainerService;
    

    @RequestMapping("/index")
    public String index() {
        return "/maintainer/maintainer";
    }

    @RequestMapping("/page")
    @ResponseBody//json
    public PageResult list(MaintainerQuery maintainerQuery) {
        return maintainerService.queryByPage(maintainerQuery);
    }

    // maintainer/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public JsonResult delete(Long id) {
        try {
            maintainerService.deleteByPrimaryKey(id);
            return JsonResult.getJsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
    }

    // maintainer/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public JsonResult saveOrUpdate(Maintainer maintainer) {
        if (maintainer != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (maintainer.getOptid() != null) {
                    //修改
                    maintainerService.updateByPrimaryKey(maintainer);
                } else {
                    //添加
                    maintainerService.insert(maintainer);
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
