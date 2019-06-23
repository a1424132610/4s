package com.fours.service.impl;


import com.fours.domain.Resource;
import com.fours.mapper.BaseMapper;
import com.fours.mapper.ResourceMapper;
import com.fours.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: wenbing
 * @Date: 2018/10/17 14:45
 * @Version 1.0
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class ResourceServiceImpl extends IBaseServiceImpl<Resource> implements IResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    BaseMapper getBaseMapper(){
        return resourceMapper;
    }

}
