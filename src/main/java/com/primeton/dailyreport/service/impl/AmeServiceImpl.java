package com.primeton.dailyreport.service.impl;

import com.primeton.dailyreport.dao.mapper.AmeMapper;
import com.primeton.dailyreport.dao.pojo.AmeProject;
import com.primeton.dailyreport.dao.pojo.AmeTask;
import com.primeton.dailyreport.service.AmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AmeServiceImpl implements AmeService {
    @Autowired
    private AmeMapper ameMapper;

    @Override
    public List<AmeProject> queryAllAmeProject() throws SQLException {
        return ameMapper.queryAllAmeProject();
    }

    @Override
    public List<AmeTask> queryAllAmeTask() throws SQLException {
        return ameMapper.queryAllAmeTask();
    }

    @Override
    public AmeTask queryByTid(Integer ameTaskId) throws SQLException {
        return ameMapper.queryByTid(ameTaskId);
    }
}
