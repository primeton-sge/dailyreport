package com.primeton.dailyreport.dao.pojo;

/**
 * 项目信息
 * @author kongyuanyuan 2018.8.16
 */
public class Project {
    private Integer pid; //项目编号
    private String projectname; //项目名
    private char validity; //是否生效

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public char getValidity() {
        return validity;
    }

    public void setValidity(char validity) {
        this.validity = validity;
    }
}
