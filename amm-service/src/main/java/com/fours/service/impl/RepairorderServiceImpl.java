package com.fours.service.impl;


import com.fours.domain.Repairorderitem;
import com.fours.mapper.BaseMapper;
import com.fours.service.impl.IBaseServiceImpl;
import com.fours.domain.Repairorder;
import com.fours.mapper.RepairorderMapper;
import com.fours.service.IRepairorderService;
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
public class RepairorderServiceImpl extends IBaseServiceImpl<Repairorder> implements IRepairorderService {
    @Autowired
    private RepairorderMapper repairorderMapper;

    @Override
    BaseMapper getBaseMapper(){
        return repairorderMapper;
    }

    @Override
//    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Repairorder queryByItems(Long id) {
        return repairorderMapper.queryByItems(id);
    }

    @Override
    public void updateZt(Long id) {
         repairorderMapper.updateByZt(id);
    }

    @Override
    public void updateZt2(Long id) {
        repairorderMapper.updateByZt2(id);
    }
}
