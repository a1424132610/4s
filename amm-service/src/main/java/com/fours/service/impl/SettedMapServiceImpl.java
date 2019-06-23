package com.fours.service.impl;

import com.fours.domain.SettedMap;
import com.fours.mapper.SettedMapMapper;
import com.fours.query.BaseQuery;
import com.fours.query.SettedMapQuery;
import com.fours.service.ISettedMapService;
import com.fours.util.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettedMapServiceImpl implements ISettedMapService {

    @Autowired
    private SettedMapMapper settedMapMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(SettedMap record) {
        return 0;
    }

    @Override
    public SettedMap selectByPrimaryKey(Long id) {
        return settedMapMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SettedMap> selectAll() {
        return settedMapMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SettedMap record) {
        return 0;
    }

    @Override
    public PageResult queryByPage(BaseQuery query) {
        return null;
    }

    /**
     * 分页查询
     * @param query
     * @return
     */
    @Override
    public PageResult queryByPage(SettedMapQuery query) {
        PageHelper.startPage(query.getPage(), query.getRows());
        Page page  = settedMapMapper.selectByPage(query);
        return new PageResult(page.getTotal(),page);
    }
}
