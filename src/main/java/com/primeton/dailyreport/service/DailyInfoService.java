package com.primeton.dailyreport.service;

import com.primeton.dailyreport.dao.pojo.*;

import java.sql.SQLException;
import java.util.List;

public interface DailyInfoService {

    DailyInfoPlus queryByPK(Integer did) throws SQLException;

    List<DailyInfoPlus> query(String startdate, String enddate, Integer[] pid, Integer[] uid) throws SQLException;

    List<DailyInfoPlus> queryByDate(String date, Integer[] uid) throws SQLException;

    List<MonthSum> querySumByMonth(String startdate, String enddate, Integer pid) throws SQLException;

    List<PersonalSum> querySumByPerson(String startdate, String enddate, Integer[] pid, Integer[] uid) throws SQLException;

    void add(DailyInfo dailyInfo) throws SQLException;

    void update(DailyInfo dailyInfo) throws SQLException;

    List<String> selectMark(Integer uid) throws SQLException;

    void delete(Integer did) throws SQLException;

    List<ProjectSum> querySumByPid(String startdate, String enddate, Integer[] pid) throws SQLException;

    List<LeakyRecord> check(String startdate, String enddate) throws SQLException;

    List<String> checkDate(String startdate, String enddate, Integer uid) throws SQLException;

    void updateFlag(String date,Integer uid) throws SQLException;
}
