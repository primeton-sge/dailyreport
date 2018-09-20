package com.primeton.dailyreport.dao.pojo;

import java.util.Map;

public class ExcelInfo {
    private String realname;
    private Map<String, String> infoMap;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Map<String, String> getInfoMap() {
        return infoMap;
    }

    public void setInfoMap(Map<String, String> infoMap) {
        this.infoMap = infoMap;
    }
}
