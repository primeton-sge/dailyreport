package com.primeton.dailyreport.service;

import com.primeton.dailyreport.dao.pojo.AmeProject;
import com.primeton.dailyreport.dao.pojo.AmeTask;

import java.sql.SQLException;
import java.util.List;

public interface AmeService {
    List<AmeProject> queryAllAmeProject() throws SQLException;

    List<AmeTask> queryAllAmeTask() throws SQLException;

    AmeTask queryByTid(Integer ameTaskId) throws SQLException;
}
