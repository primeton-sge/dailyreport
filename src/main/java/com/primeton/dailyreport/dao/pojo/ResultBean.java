package com.primeton.dailyreport.dao.pojo;

import java.util.List;

public class ResultBean {
    private String flag;
    private List<MonthSum> monthSumList;
    private List<ProjectSum> projectSumList;
    private List<PersonalSum> personalSumList;
    private List<DailyInfoPlus> dailyInfoPlusList;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<MonthSum> getMonthSumList() {
        return monthSumList;
    }

    public void setMonthSumList(List<MonthSum> monthSumList) {
        this.monthSumList = monthSumList;
    }

    public List<ProjectSum> getProjectSumList() {
        return projectSumList;
    }

    public void setProjectSumList(List<ProjectSum> projectSumList) {
        this.projectSumList = projectSumList;
    }

    public List<PersonalSum> getPersonalSumList() {
        return personalSumList;
    }

    public void setPersonalSumList(List<PersonalSum> personalSumList) {
        this.personalSumList = personalSumList;
    }

    public List<DailyInfoPlus> getDailyInfoPlusList() {
        return dailyInfoPlusList;
    }

    public void setDailyInfoPlusList(List<DailyInfoPlus> dailyInfoPlusList) {
        this.dailyInfoPlusList = dailyInfoPlusList;
    }
}
