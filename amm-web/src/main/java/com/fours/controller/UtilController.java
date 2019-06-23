package com.fours.controller;

import com.fours.domain.Maintainer;
import com.fours.domain.Parts;
import com.fours.domain.Repairorder;
import com.fours.domain.Repairorderitem;
import com.fours.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/util")
public class UtilController {
    @Autowired
    private IMaintainerService maintainerService;


    @Autowired
    private IRepairorderService repairorderService;

    @Autowired
    private IPartsService partsService;

    @RequestMapping("/queryMaintainer")
    @ResponseBody
    public List<Maintainer> queryMaintainer() throws Exception {
        return maintainerService.selectAll();
    }

    /**
     * 查询维修明细
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryRepairorderitem")
    @ResponseBody
    public Repairorder queryRepairorderitem(Long id) throws Exception {
        Repairorder list = repairorderService.queryByItems(id);
        if(list==null){
            return new Repairorder();
        }
        return list;
    }
    /**
     * 查询所以配件
     */
    @RequestMapping("/queryParts")
    @ResponseBody
    public List<Parts> queryParts() throws Exception {
        return partsService.selectAll();
    }
    /**
     * 修改状态
     */
    @RequestMapping("/updateZt")
    @ResponseBody
    public String updateZt(Long id) throws Exception {
         repairorderService.updateZt(id);
         return "";
    }
    @RequestMapping("/updateZt2")
    @ResponseBody
    public String updateZt2(Long id) throws Exception {
        repairorderService.updateZt2(id);
        return "";
    }

}
