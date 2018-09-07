package com.primeton.dailyreport.dao.mapper;

import com.primeton.dailyreport.dao.pojo.DailyUser;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户信息查询
 * @author kongyuanyuan
 */
public interface DailyUserMapper {
    /**
     * 核实用户是否存在及账户密码是否对应正确
     * @return
     * @throws SQLException
     */
    DailyUser queryUser(@Param("username") String username, @Param("password") String password) throws SQLException;

    /**
     * 查询所有需要填写工时日报的员工
     * @return
     * @throws SQLException
     */
    List<DailyUser> queryAll() throws SQLException;

    /**
     * 修改密码
     * @param password
     * @param uid
     * @throws SQLException
     */
    void changePassword(@Param("password") String password, @Param("uid") Integer uid) throws SQLException;

    void bindAme(@Param("uid") Integer uid, @Param("ameUsername") String ameUsername, @Param("amePassword") String amePassword,
                 @Param("ameProjectId") Integer ameProjectId, @Param("ameTaskId") Integer ameTaskId) throws SQLException;
}
