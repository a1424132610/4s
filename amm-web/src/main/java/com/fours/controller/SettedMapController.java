package com.fours.controller;

import com.fours.domain.SettedMap;
import com.fours.query.SettedMapQuery;
import com.fours.service.ISettedMapService;
import com.fours.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/settedMap")
public class SettedMapController {

    @Autowired
    private ISettedMapService settedMapService;


    @RequestMapping("/index")
    public String index(){
        return "settedmap/settedmap";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<SettedMap> list(){
        return settedMapService.selectAll();
    }

    /**
     *  列表分页查询
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public PageResult page(SettedMapQuery query){
        return settedMapService.queryByPage(query);
    }

}
