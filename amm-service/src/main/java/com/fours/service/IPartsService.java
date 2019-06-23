package com.fours.service;


import com.fours.domain.Parts;
import com.fours.query.BaseQuery;
import com.fours.util.PageResult;

import java.util.List;

public interface IPartsService extends IBaseService<Parts> {

    Parts selectOne(String partsName);
}

