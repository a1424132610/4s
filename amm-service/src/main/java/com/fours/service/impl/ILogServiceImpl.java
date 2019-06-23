package com.fours.service.impl;

import com.fours.domain.Log;
import com.fours.mapper.BaseMapper;
import com.fours.mapper.LogMapper;
import com.fours.service.IBaseService;
import com.fours.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ILogServiceImpl extends IBaseServiceImpl<Log> implements ILogService {

    @Autowired
    private LogMapper logMapper;

    //日志是否添加成功的接口实现类
    @Override
    public boolean addLog(Log log) throws SQLException {
        return logMapper.insert(log) > 0 ? true : false;
    }



    @Override
    BaseMapper getBaseMapper() {
        return logMapper;
    }
}
