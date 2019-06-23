package com.fours.service;

import com.fours.domain.Log;
import com.fours.query.LogQuery;
import com.github.pagehelper.Page;

import java.sql.SQLException;

public interface ILogService extends IBaseService<Log> {
    //添加日志是否成功的方法
    boolean addLog(Log log) throws SQLException;

}
