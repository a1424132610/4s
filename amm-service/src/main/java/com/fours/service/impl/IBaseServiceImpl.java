package com.fours.service.impl;

import com.fours.mapper.BaseMapper;
import com.fours.query.BaseQuery;
import com.fours.service.IBaseService;
import com.fours.util.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public abstract class IBaseServiceImpl<T> implements IBaseService<T> {

    abstract BaseMapper getBaseMapper();

    @Override
    public int deleteByPrimaryKey(Long id) {
        return getBaseMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T record) {
        return getBaseMapper().insert(record);
    }

    @Override
    public T selectByPrimaryKey(Long id) {
        return (T) getBaseMapper().selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectAll() {
        return getBaseMapper().selectAll();
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return getBaseMapper().updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryByPage(BaseQuery query) {
        PageHelper.startPage(query.getPage(), query.getRows());
        Page page  = getBaseMapper().selectByPage(query);
        return new PageResult(page.getTotal(),page);
    }
}
