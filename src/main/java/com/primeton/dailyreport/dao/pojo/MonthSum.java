package com.primeton.dailyreport.dao.pojo;

import java.util.Date;

public class MonthSum {
    private int man_hoursSum;
    private String projectname;
    private String date;

    public int getMan_hoursSum() {
        return man_hoursSum;
    }

    public void setMan_hoursSum(int man_hoursSum) {
        this.man_hoursSum = man_hoursSum;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
