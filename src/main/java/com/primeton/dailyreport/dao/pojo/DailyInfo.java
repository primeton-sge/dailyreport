package com.primeton.dailyreport.dao.pojo;


/**
 * 每日工时信息
 * @author kongyuanyuan 2018.8.16
 */
public class DailyInfo {
    private Integer did; //编号
    private String text; //工作内容
    private int man_hours; //工时
    private String date; //日期
    private Integer uid; //用户编号
    private Integer pid; //项目编号
    private Integer flag;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMan_hours() {
        return man_hours;
    }

    public void setMan_hours(int man_hours) {
        this.man_hours = man_hours;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
}
