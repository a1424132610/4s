package com.fours.controller.role;

import com.fours.util.JsonResult;
import com.fours.util.PageResult ;
import com.fours.domain.Resource;
import com.fours.query.ResourceQuery;
import com.fours.service.IResourceService;
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
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private IResourceService resourceService;

    @RequestMapping("/index")
    public String index() {
        return "/resource/resource";
    }

    @RequestMapping("/page")
    @ResponseBody//json
    public PageResult list(ResourceQuery resourceQuery) {
        return resourceService.queryByPage(resourceQuery);
    }

    // resource/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public JsonResult delete(Long id) {
        try {
            resourceService.deleteByPrimaryKey(id);
            return JsonResult.getJsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
    }

    // resource/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public JsonResult saveOrUpdate(Resource resource) {
        if (resource != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (resource.getId() != null) {
                    //修改
                    resourceService.updateByPrimaryKey(resource);
                } else {
                    //添加
                    resourceService.insert(resource);
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
