package com.fours.mapper;

import com.fours.query.BaseQuery;
import com.fours.util.PageResult;
import com.github.pagehelper.Page;

import java.util.List;

public interface BaseMapper<T> {
    int deleteByPrimaryKey(Long id);

    int insert(T record);

    T selectByPrimaryKey(Long id);

    List<T> selectAll();

    int updateByPrimaryKey(T record);

    Page selectByPage(BaseQuery query);
}
