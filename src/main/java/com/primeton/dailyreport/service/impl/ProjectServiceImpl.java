package com.primeton.dailyreport.service.impl;

import com.primeton.dailyreport.dao.mapper.ProjectMapper;
import com.primeton.dailyreport.dao.pojo.Project;
import com.primeton.dailyreport.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<Project> selectAll() throws SQLException {
        return projectMapper.selectAll();
    }

    @Override
    public void add(Project project) throws SQLException {
        projectMapper.add(project);
    }

    @Override
    public void delete(int pid) throws SQLException {
        projectMapper.delete(pid);
    }
}
