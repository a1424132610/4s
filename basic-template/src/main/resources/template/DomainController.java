package com.fours.controller;

import com.fours.util.JsonResult;
import com.fours.util.PageResult ;
import com.fours.domain.${Domain};
import com.fours.query.${Domain}Query;
import com.fours.service.I${Domain}Service;
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
@RequestMapping("/${domain}")
public class ${Domain}Controller {

    @Autowired
    private I${Domain}Service ${domain}Service;

    @RequestMapping("/index")
    public String index() {
        return "/${domain}/${domain}";
    }

    @RequestMapping("/page")
    @ResponseBody//json
    public PageResult list(${Domain}Query ${domain}Query) {
        return ${domain}Service.queryByPage(${domain}Query);
    }

    // ${domain}/delete?id
    @RequestMapping("/delete")
    @ResponseBody//json
    public JsonResult delete(Long id) {
        try {
            ${domain}Service.deleteByPrimaryKey(id);
            return JsonResult.getJsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("删除失败");
        }
    }

    // ${domain}/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @ResponseBody//json
    public JsonResult saveOrUpdate(${Domain} ${domain}) {
        if (${domain} != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (${domain}.getId() != null) {
                    //修改
                    ${domain}Service.updateByPrimaryKey(${domain});
                } else {
                    //添加
                    ${domain}Service.insert(${domain});
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
