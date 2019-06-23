package com.fours.service.impl;


import com.fours.mapper.BaseMapper;
import com.fours.service.impl.IBaseServiceImpl;
import com.fours.domain.Repairorderitem;
import com.fours.mapper.RepairorderitemMapper;
import com.fours.service.IRepairorderitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: wenbing
 * @Date: 2018/10/17 14:45
 * @Version 1.0
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class RepairorderitemServiceImpl extends IBaseServiceImpl<Repairorderitem> implements IRepairorderitemService {
    @Autowired
    private RepairorderitemMapper repairorderitemMapper;

    @Override
    BaseMapper getBaseMapper(){
        return repairorderitemMapper;
    }

}
