package com.primeton.dailyreport.dao.mapper;

import com.primeton.dailyreport.dao.pojo.Project;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface ProjectMapper {
    /**
     * 查询所有未过期的项目
     * @return
     * @throws SQLException
     */
    List<Project> selectAll() throws SQLException;

    /**
     * 添加一条项目
     * @param project
     * @throws SQLException
     */
    void add(Project project) throws SQLException;

    /**
     * 删除一条项目，实际逻辑为将是否过期标记为是，但该条数据并不删除
     * @param pid
     * @throws SQLException
     */
    void delete(@Param("pid") int pid) throws SQLException;

}
