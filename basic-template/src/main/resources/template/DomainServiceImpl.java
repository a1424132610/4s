package com.fours.service.impl;


import com.fours.mapper.BaseMapper;
import com.fours.service.impl.IBaseServiceImpl;
import com.fours.domain.${Domain};
import com.fours.mapper.${Domain}Mapper;
import com.fours.service.I${Domain}Service;
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
public class ${Domain}ServiceImpl extends IBaseServiceImpl<${Domain}> implements I${Domain}Service {
    @Autowired
    private ${Domain}Mapper ${domain}Mapper;

    @Override
    BaseMapper getBaseMapper(){
        return ${domain}Mapper;
    }

}
