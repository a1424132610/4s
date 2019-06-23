package com.fours.controller;

import com.fours.domain.Parts;
import com.fours.query.BaseQuery;
import com.fours.service.IPartsService;
import com.fours.util.JsonResult;
import com.fours.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/parts")
public class PartsController {
    @Autowired
    private IPartsService partsService;


//    //查询所有
//    @RequestMapping("/listAll")
//    @ResponseBody
//    public List<Parts> listAll(){
//        return partsService.selectAll();
//    }
    //主页跳转
    @RequestMapping("/index")
    public String index(){
        return "parts/parts";
    }

    //分页专用查询
    @RequestMapping("/selectAll")
    @ResponseBody
    public PageResult selectAll(BaseQuery query){
        //调用Service层中分页查询方法
        System.out.println(query);
        PageResult pageResult = partsService.queryByPage(query);
        return pageResult;
    }
    @RequestMapping("/getAll")
    @ResponseBody
    public List getAll(){
        //调用Service层中分页查询方法
        List<Parts> parts = partsService.selectAll();
        return parts;
    }
    @RequestMapping("/getOne")
    @ResponseBody
    public Parts getOne(String inputName){
        //调用Service层中分页查询方法
        Parts parts = partsService.selectOne(inputName);
        return parts;
    }
    //    增加/修改方法
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate( Parts parts){
        System.out.println(parts);
        try {
            //如果有ID就是修改
            if (parts.getId() !=null){
                //调用修改方法
                partsService.updateByPrimaryKey(parts);
            }else {
                //没传ID就是修改
                partsService.insert(parts);
            }
            return JsonResult.getJsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            //返回失败信息
            return JsonResult.error("系统异常,我们正在殴打程序员..");
        }
    }
      /*
        删除
       */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id){
        try {
            //调用删除方法
            partsService.deleteByPrimaryKey(id);
            //返回删除成功信息给前台
            return JsonResult.getJsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            //返回失败信息
            return JsonResult.error("不知道怎么回事就是修改失败了");
        }
    }
}
