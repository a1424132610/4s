package com.fours.service.impl;

import com.fours.domain.Parts;
import com.fours.mapper.BaseMapper;
import com.fours.mapper.PartsMapper;
import com.fours.query.BaseQuery;
import com.fours.service.IPartsService;
import com.fours.util.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PartsServiceImpl extends IBaseServiceImpl<Parts> implements IPartsService {
    @Autowired
    private PartsMapper partsMapper;

    @Override
    BaseMapper getBaseMapper() {
        return partsMapper;
    }

    @Override
    public Parts selectOne(String partsName) {
        return partsMapper.selectOne(partsName);
    }
}
