package com.fours.mapper;

import com.fours.domain.Log;
import com.fours.query.LogQuery;
import com.fours.query.SettedMapQuery;
import com.github.pagehelper.Page;

import java.sql.SQLException;

/**
 * 日志的接口
 */
public interface LogMapper extends BaseMapper<Log> {
    //添加日志是否成功的方法
    boolean addLog(Log log) throws SQLException;

}