package com.primeton.dailyreport.service;

import com.primeton.dailyreport.dao.pojo.Project;

import java.sql.SQLException;
import java.util.List;

public interface ProjectService {
    List<Project> selectAll() throws SQLException;

    void add(Project project) throws SQLException;

    void delete(int pid) throws SQLException;
}
