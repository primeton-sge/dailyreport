package com.primeton.dailyreport.dao.mapper;

import com.primeton.dailyreport.dao.pojo.AmeProject;
import com.primeton.dailyreport.dao.pojo.AmeTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface AmeMapper {
/**
*test pull request
*/
    List<AmeProject> queryAllAmeProject() throws SQLException;

    List<AmeTask> queryAllAmeTask() throws SQLException;

    AmeTask queryByTid(@Param("ameTaskId") Integer ameTaskId) throws SQLException;
}
