package com.primeton.dailyreport.service.impl;

import com.primeton.dailyreport.dao.mapper.DailyUserMapper;
import com.primeton.dailyreport.dao.pojo.DailyUser;
import com.primeton.dailyreport.service.DailyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DailyUserServiceImpl implements DailyUserService {

    @Autowired
    private DailyUserMapper dailyUserMapper;

    @Override
    public DailyUser queryUser(String username, String password) throws SQLException {
        return dailyUserMapper.queryUser(username, password);
    }

    @Override
    public List<DailyUser> queryAll() throws SQLException {
        return dailyUserMapper.queryAll();
    }

    @Override
    public void changePassword(String password, Integer uid) throws SQLException {
        dailyUserMapper.changePassword(password, uid);
    }

    @Override
    public void bindAme(Integer uid, String ameUsername, String amePassword, Integer ameProjectId, Integer ameTaskId) throws SQLException {
        dailyUserMapper.bindAme(uid, ameUsername, amePassword, ameProjectId, ameTaskId);
    }
}
