package com.fours.service;

import com.fours.query.BaseQuery;
import com.fours.util.PageResult;

import java.util.List;

public interface IBaseService<T> {
    int deleteByPrimaryKey(Long id);

    int insert(T record);

    T selectByPrimaryKey(Long id);

    List<T> selectAll();

    int updateByPrimaryKey(T record);

    PageResult queryByPage(BaseQuery query);
}
