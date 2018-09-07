package com.primeton.dailyreport.controller.Bean;

/**
 * 页面查询参数
 * @author kongyuanyuan
 */
public class FormBean {
    private String date;
    private Integer[] pid;
    private Integer[] uid;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer[] getPid() {
        return pid;
    }

    public void setPid(Integer[] pid) {
        this.pid = pid;
    }

    public Integer[] getUid() {
        return uid;
    }

    public void setUid(Integer[] uid) {
        this.uid = uid;
    }
}
