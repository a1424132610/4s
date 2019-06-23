package com.fours.mapper;

import com.fours.domain.Parts;
import com.fours.query.BaseQuery;
import com.github.pagehelper.Page;

public interface PartsMapper extends BaseMapper<Parts> {

    Parts selectOne(String partsName);
}