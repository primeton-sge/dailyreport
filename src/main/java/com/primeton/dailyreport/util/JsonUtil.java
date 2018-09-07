package com.primeton.dailyreport.util;

import com.primeton.dailyreport.controller.Bean.FormBean;

/**
 * Json处理类
 * @author kongyuanyuan 2018.8.23
 */
public class JsonUtil {
    public static FormBean parseToFormBean(String json) {
        FormBean formBean = new FormBean();
        String str = json.substring(1, json.length() - 1);
        StringBuffer sb = new StringBuffer(str);
        boolean flag = false;
        for(int i = 0; i < sb.length(); i++) {
            if(sb.charAt(i) == '[') {
                flag = true;
            }
            if(sb.charAt(i) == ']') {
                flag = false;
            }
            if(!flag && sb.charAt(i) == ',') {
                sb.setCharAt(i , '@');
            }
        }
        String[] datas = sb.toString().split("@");
        //获取日期
        String[] arg1 = datas[0].split(":");
        formBean.setDate(arg1[1].substring(1, arg1[1].length() - 1));
        if(datas.length == 3) {
            //获取项目编号数组
            String[] arg2 = datas[1].split(":");
            String pidArray = arg2[1];
            if(pidArray.startsWith("[")) {
                pidArray = pidArray.substring(1, pidArray.length() - 1);
                String[] pids = pidArray.split(",");
                Integer[] pid = new Integer[pids.length];
                for(int i = 0; i < pids.length; i++) {
                    pid[i] = Integer.parseInt(pids[i].substring(1, pids[i].length() - 1));
                }
                formBean.setPid(pid);
            } else {
                Integer[] pid = new Integer[1];
                pid[0] = Integer.parseInt(pidArray.substring(1, pidArray.length() - 1));
                formBean.setPid(pid);
            }
            //获取用户编号数组
            String[] arg3 = datas[2].split(":");
            String uidArray = arg3[1];
            if(uidArray.startsWith("[")) {
                uidArray = uidArray.substring(1, uidArray.length() - 1);
                String[] uids = uidArray.split(",");
                Integer[] uid = new Integer[uids.length];
                for(int i = 0; i < uids.length; i++) {
                    uid[i] = Integer.parseInt(uids[i].substring(1, uids[i].length() - 1));
                }
                formBean.setUid(uid);
            } else {
                Integer[] uid = new Integer[1];
                uid[0] = Integer.parseInt(uidArray.substring(1, uidArray.length() - 1));
                formBean.setUid(uid);
            }
        } else if(datas.length == 2) {
            String[] arg2 = datas[1].split(":");
            if(arg2[0].equals("\"pid\"")) {
                String pidArray = arg2[1];
                if(pidArray.startsWith("[")) {
                    pidArray = pidArray.substring(1, pidArray.length() - 1);
                    String[] pids = pidArray.split(",");
                    Integer[] pid = new Integer[pids.length];
                    for(int i = 0; i < pids.length; i++) {
                        pid[i] = Integer.parseInt(pids[i].substring(1, pids[i].length() - 1));
                    }
                    formBean.setPid(pid);
                } else {
                    Integer[] pid = new Integer[1];
                    pid[0] = Integer.parseInt(pidArray.substring(1, pidArray.length() - 1));
                    formBean.setPid(pid);
                }
            } else {
                String uidArray = arg2[1];
                if(uidArray.startsWith("[")) {
                    uidArray = uidArray.substring(1, uidArray.length() - 1);
                    String[] uids = uidArray.split(",");
                    Integer[] uid = new Integer[uids.length];
                    for(int i = 0; i < uids.length; i++) {
                        uid[i] = Integer.parseInt(uids[i].substring(1, uids[i].length() - 1));
                    }
                    formBean.setUid(uid);
                } else {
                    Integer[] uid = new Integer[1];
                    uid[0] = Integer.parseInt(uidArray.substring(1, uidArray.length() - 1));
                    formBean.setUid(uid);
                }
            }
        }
        return formBean;
    }

    public static void main(String[] args) {
        String json = "{\"date\":\"201808\",\"pid\":[\"1\",\"2\"],\"uid\":[\"1\",\"2\"]}";
        FormBean formBean = parseToFormBean(json);
    }
}
