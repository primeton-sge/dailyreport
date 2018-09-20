package com.primeton.dailyreport.dao.mapper;

import com.primeton.dailyreport.dao.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

/**
 * 工时日报
 * @author kongyuanyuan 2018.8.16
 */
public interface DailyInfoMapper {
    /**
     * 根据日期，项目，用户查询工时信息
     * @param startdate
     * @param enddate
     * @param pid
     * @param uid
     * @return
     * @throws SQLException
     */
    List<DailyInfoPlus> query(@Param("startdate") String startdate, @Param("enddate") String enddate,
                              @Param("pid") Integer[] pid, @Param("uid") Integer[] uid) throws SQLException;

    /**
     * 根据所选月份查询当月各项目所花费的工时
     * @param startdate
     * @param enddate
     * @param pid
     * @return
     * @throws SQLException
     */
    List<MonthSum> querySumByMonth(@Param("startdate") String startdate, @Param("enddate") String enddate,
                                   @Param("pid") Integer pid) throws SQLException;

    /**
     * 根据所选月份查询所选的员工们在当月所选的项目中所花费的工时
     * @param startdate
     * @param enddate
     * @param pid
     * @param uid
     * @return
     * @throws SQLException
     */
    List<PersonalSum> querySumByPerson(@Param("startdate") String startdate, @Param("enddate") String enddate,
                                       @Param("pid") Integer[] pid, @Param("uid") Integer[] uid) throws SQLException;

    /**
     * 添加一条工时信息
     * @param dailyInfo
     * @throws SQLException
     */
    Integer add(DailyInfo dailyInfo) throws SQLException;

    /**
     * 更新工时信息
     * @param dailyInfo
     * @throws SQLException
     */
    void update(DailyInfo dailyInfo) throws SQLException;

    /**
     * 删除一条工时信息
     * @param did
     * @throws SQLException
     */
    void delete(@Param("did") Integer did) throws SQLException;

    /**
     * 查询当前用户填写过工时的日期
     * @param uid
     * @return
     * @throws SQLException
     */
    List<String> selectMark(@Param("uid") Integer uid) throws SQLException;

    /**
     * 根据所选月份查询所选的的项目在当月所花费的总工时
     * @param startdate
     * @param enddate
     * @param pid
     * @return
     * @throws SQLException
     */
    List<ProjectSum> querySumByPid(@Param("startdate") String startdate, @Param("enddate") String enddate,
                                   @Param("pid") Integer[] pid) throws SQLException;

    /**
     * 根据主键查询一条工时信息
     * @param did
     * @return
     * @throws SQLException
     */
    DailyInfoPlus queryByPK(@Param("did")Integer did) throws SQLException;

    /**
     * 查询上周员工及其所填写过工时日报的日期
     * @param startdate
     * @param enddate
     * @return
     * @throws SQLException
     */
    List<LeakyRecord> check(@Param("startdate") String startdate, @Param("enddate") String enddate) throws SQLException;

    /**
     * 查询某员工在当前周填写过工时日报的日期
     * @param startdate
     * @param enddate
     * @param uid
     * @return
     * @throws SQLException
     */
    List<String> checkDate(@Param("startdate") String startdate, @Param("enddate") String enddate,
                           @Param("uid") Integer uid) throws SQLException;

    /**
     * 更改已填工时flag
     * @param date
     * @param uid
     * @throws SQLException
     */
    void updateFlag(@Param("date")String date,@Param("uid")Integer uid)throws SQLException;

    /**
     * 查询当日所有员工的工时明细
     * @param date
     * @return
     * @throws SQLException
     */
    List<DailyInfoPlus> queryDaily(@Param("date")String date)throws SQLException;
}
