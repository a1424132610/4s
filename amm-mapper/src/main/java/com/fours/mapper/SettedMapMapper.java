package com.fours.mapper;

import com.fours.domain.SettedMap;
import com.fours.query.BaseQuery;
import com.fours.query.SettedMapQuery;
import com.github.pagehelper.Page;

import java.util.List;

public interface SettedMapMapper extends BaseMapper<SettedMap> {
    Page selectByPage(SettedMapQuery query);
}