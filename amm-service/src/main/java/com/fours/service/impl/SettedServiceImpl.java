package com.fours.service.impl;


import com.fours.mapper.BaseMapper;
import com.fours.service.impl.IBaseServiceImpl;
import com.fours.domain.Setted;
import com.fours.mapper.SettedMapper;
import com.fours.service.ISettedService;
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
public class SettedServiceImpl extends IBaseServiceImpl<Setted> implements ISettedService {
    @Autowired
    private SettedMapper settedMapper;

    @Override
    BaseMapper getBaseMapper(){
        return settedMapper;
    }

}
