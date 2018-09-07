package com.primeton.dailyreport.dao.pojo;

/**
 * 用户信息
 * @author kongyuanyuan 2018.8.16
 */
public class DailyUser {
    private Integer uid; //编号
    private String username; //登录名
    private String realname; //姓名
    private String password; //密码
    private char jurisdiction; //权限
    private String ameUsername; //ame绑定账户名
    private String amePassword; //ame绑定密码
    private Integer ameProjectId; //ame项目id
    private Integer ameTaskId; //ame项目活动id

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(char jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public String getAmeUsername() {
        return ameUsername;
    }

    public void setAmeUsername(String ameUsername) {
        this.ameUsername = ameUsername;
    }

    public String getAmePassword() {
        return amePassword;
    }

    public void setAmePassword(String amePassword) {
        this.amePassword = amePassword;
    }

    public Integer getAmeProjectId() {
        return ameProjectId;
    }

    public void setAmeProjectId(Integer ameProjectId) {
        this.ameProjectId = ameProjectId;
    }

    public Integer getAmeTaskId() {
        return ameTaskId;
    }

    public void setAmeTaskId(Integer ameTaskId) {
        this.ameTaskId = ameTaskId;
    }
}
