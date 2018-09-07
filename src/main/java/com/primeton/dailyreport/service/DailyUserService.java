package com.primeton.dailyreport.service;

import com.primeton.dailyreport.dao.pojo.DailyUser;
import java.sql.SQLException;
import java.util.List;

public interface DailyUserService {
    /**
     * 用户信息查询
     * @return
     * @throws SQLException
     */
    DailyUser queryUser(String username, String password) throws SQLException;

    List<DailyUser> queryAll() throws SQLException;

    void changePassword(String password, Integer uid) throws SQLException;

    void bindAme(Integer uid, String ameUsername, String amePassword, Integer ameProjectId, Integer ameTaskId) throws SQLException;
}
