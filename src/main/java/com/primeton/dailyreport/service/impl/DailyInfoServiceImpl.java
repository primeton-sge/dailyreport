package com.primeton.dailyreport.service.impl;

import com.primeton.dailyreport.dao.mapper.DailyInfoMapper;
import com.primeton.dailyreport.dao.pojo.*;
import com.primeton.dailyreport.service.DailyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DailyInfoServiceImpl implements DailyInfoService {
    @Autowired
    private DailyInfoMapper dailyInfoMapper;

    @Override
    public DailyInfoPlus queryByPK(Integer did) throws SQLException {
        return dailyInfoMapper.queryByPK(did);
    }

    @Override
    public List<DailyInfoPlus> query(String startdate, String enddate, Integer[] pid, Integer[] uid) throws SQLException {
        return dailyInfoMapper.query(startdate, enddate, pid, uid);
    }

    @Override
    public List<DailyInfoPlus> queryByDate(String date, Integer[] uid) throws SQLException {
        return dailyInfoMapper.query(date, date, null, uid);
    }

    @Override
    public List<MonthSum> querySumByMonth(String startdate, String enddate, Integer pid) throws SQLException {
        return dailyInfoMapper.querySumByMonth(startdate, enddate, pid);
    }

    @Override
    public List<PersonalSum> querySumByPerson(String startdate, String enddate, Integer[] pid, Integer[] uid) throws SQLException {
        return dailyInfoMapper.querySumByPerson(startdate, enddate, pid, uid);
    }

    @Override
    public void add(DailyInfo dailyInfo) throws SQLException {
         dailyInfoMapper.add(dailyInfo);
    }

    @Override
    public void update(DailyInfo dailyInfo) throws SQLException {
        dailyInfoMapper.update(dailyInfo);
    }

    @Override
    public List<String> selectMark(Integer uid) throws SQLException {
        return dailyInfoMapper.selectMark(uid);
    }

    @Override
    public List<ProjectSum> querySumByPid(String startdate, String enddate, Integer[] pid) throws SQLException {
        return dailyInfoMapper.querySumByPid(startdate, enddate, pid);
    }

    @Override
    public void delete(Integer did) throws SQLException {
        dailyInfoMapper.delete(did);
    }

    @Override
    public List<LeakyRecord> check(String startdate, String enddate) throws SQLException {
        return dailyInfoMapper.check(startdate, enddate);
    }

    @Override
    public List<String> checkDate(String startdate, String enddate, Integer uid) throws SQLException {
        return dailyInfoMapper.checkDate(startdate, enddate, uid);
    }

    @Override
    public void updateFlag(String date, Integer uid) throws SQLException {
        dailyInfoMapper.updateFlag(date,uid);
    }
}
