package com.fours.shiro;

import com.fours.domain.Resource;
import com.fours.mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义的权限过滤器
 */
public class FilterChainDefinitions {

    @Autowired
    private ResourceMapper resourceMapper;

    public Map<String,String> creatMap(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/static/**", "anon");
        map.put("/login/**", "anon");
        map.put("/images/**", "anon");
        map.put("/logout", "logout");
        //从数据库中拿出所有的权限
        List<Resource> permissions = resourceMapper.selectAll();
        for (Resource resource : permissions) {
            map.put(resource.getUrl(), "ammFilter["+resource.getSn()+"]");
        }
        map.put("/**", "authc");
        return map;
    }


}
