package com.fours.service;

import com.fours.domain.SettedMap;
import com.fours.query.BaseQuery;
import com.fours.query.SettedMapQuery;
import com.fours.util.PageResult;

public interface ISettedMapService extends IBaseService<SettedMap>{
    PageResult queryByPage(SettedMapQuery query);
}
