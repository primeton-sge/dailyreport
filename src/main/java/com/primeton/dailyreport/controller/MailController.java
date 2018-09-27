package com.primeton.dailyreport.controller;

import com.primeton.dailyreport.dao.pojo.DailyInfoPlus;
import com.primeton.dailyreport.dao.pojo.DailyUser;
import com.primeton.dailyreport.dao.pojo.ExcelInfo;
import com.primeton.dailyreport.dao.pojo.LeakyRecord;
import com.primeton.dailyreport.service.DailyInfoService;
import com.primeton.dailyreport.service.DailyUserService;
import com.primeton.dailyreport.service.impl.MailServiceImpl;
import com.primeton.dailyreport.util.DateUtils;
import com.primeton.dailyreport.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MailController {
    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private DailyInfoService dailyInfoService;

    @Autowired
    private DailyUserService dailyUserService;

    //@Scheduled(cron = "0 0 9 ? * MON")
    @RequestMapping(value = {"/sendmail.htm"})
    @ResponseBody
    public String sendStatistics() {
        Map<String, Map<String, String>> excelMap = new HashMap<>();
        List<ExcelInfo> excelInfoList = new ArrayList<>();
        List<Date> dates = new ArrayList<>();
        String lastMonday = DateUtils.getCurrentMonday(-7);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,Integer.parseInt(lastMonday.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(lastMonday.substring(5, 7))-1);
        int dayNumOfMonth = ExcelUtil.getDaysByYearMonth(Integer.parseInt(lastMonday.substring(0, 4))
                , Integer.parseInt(lastMonday.substring(5, 7)));
        cal.set(Calendar.DAY_OF_MONTH, 1);// 从一号开始
        try {
            List<DailyUser> dailyUserList = dailyUserService.queryAll();
            for(DailyUser dailyUser : dailyUserList) {
                ExcelInfo excelInfo = new ExcelInfo();
                excelInfo.setRealname(dailyUser.getRealname());
                excelInfoList.add(excelInfo);
                excelMap.put(dailyUser.getRealname(), new HashMap<>());
            }
            for(int i = 0; i < dayNumOfMonth; i++) {
                dates.add(cal.getTime());
                List<DailyInfoPlus> dailyInfoPlusList =
                        dailyInfoService.queryDaily(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
                for(DailyInfoPlus dailyInfoPlus : dailyInfoPlusList) {
                    excelMap.get(dailyInfoPlus.getRealname()).
                            put(dailyInfoPlus.getDate(), dailyInfoPlus.getMan_hours() + "@" + dailyInfoPlus.getText());
                }
                cal.add(Calendar.DAY_OF_WEEK, 1);
            }
            for(ExcelInfo excelInfo : excelInfoList) {
                excelInfo.setInfoMap(excelMap.get(excelInfo.getRealname()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<ExcelInfo> controlInfo = new ArrayList<>();
        List<ExcelInfo> quoteInfo = new ArrayList<>();
        List<ExcelInfo> dataInfo = new ArrayList<>();
        for(ExcelInfo excelInfo : excelInfoList) {
            if(excelInfo.getRealname().equals("王健") || excelInfo.getRealname().equals("任治民")) {
                quoteInfo.add(excelInfo);
            } else if(excelInfo.getRealname().equals("屠欣辰") || excelInfo.getRealname().equals("杨邵川")
                    ||excelInfo.getRealname().equals("万强")) {
                dataInfo.add(excelInfo);
            } else {
                controlInfo.add(excelInfo);
            }
        }
        ExcelUtil.appendExcel(controlInfo, dates, lastMonday, DateUtils.getCurrentMonday(0));
        String path = System.getProperty("user.dir") + "/template" + lastMonday.substring(0, 4)
                + lastMonday.substring(5, 7) + ".xlsx";
        File controlFile = ExcelUtil.cloneFile(path, DateUtils.getCurrentMonday(0), "普元管控开发人员考勤工作量统计表");
        File template = new File(path);
        if(template.exists()) {
            template.delete();
        }
        ExcelUtil.appendExcel(quoteInfo, dates, lastMonday, DateUtils.getCurrentMonday(0));
        File quoteFile = ExcelUtil.cloneFile(path, DateUtils.getCurrentMonday(0), "普元报价开发人员考勤工作量统计表");
        File template2 = new File(path);
        if(template2.exists()) {
            template2.delete();
        }
        ExcelUtil.appendExcel(dataInfo, dates, lastMonday, DateUtils.getCurrentMonday(0));
        File dataFile = ExcelUtil.cloneFile(path, DateUtils.getCurrentMonday(0), "普元数据开发人员考勤工作量统计表");
        File template3 = new File(path);
        if(template3.exists()) {
            template3.delete();
        }
        List<File> fileList = new ArrayList<>();
        fileList.add(controlFile);
        fileList.add(quoteFile);
        fileList.add(dataFile);
        try {
            System.setProperty("mail.mime.splitlongparameters","false");
            mailService.send("上周考勤工作量统计", fileList, "xuning@primeton.com");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            for(File file : fileList) {
                if(file.exists()) {
                    file.delete();
                }
            }
        }
        return "success";
    }


    //@Scheduled(cron = "0 0 17 ? * FRI")
    @RequestMapping(value = "/sendremanning.htm")
    @ResponseBody
    public String sendReminding() {
        String startdate = DateUtils.getCurrentMonday(0);
        String enddate = DateUtils.getCurrentMonday(4);
        List<String> datelist = new ArrayList<>();
        for(int i = 0; i <= 4; i++) {
            datelist.add(DateUtils.getCurrentMonday(i));
        }
        Map<String, List<String>> map = new HashMap<>();
        Map<String, StringBuffer> map2 = new HashMap<>();
        List<LeakyRecord> leakyRecordList = new ArrayList<>();
        List<DailyUser> dailyUserList = new ArrayList<>();
        try {
            leakyRecordList = dailyInfoService.check(startdate, enddate);
            dailyUserList = dailyUserService.queryAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(DailyUser dailyUser : dailyUserList) {
            map.put(dailyUser.getRealname(), new ArrayList<>());
        }
        for(LeakyRecord leakyRecord : leakyRecordList) {
            map.get(leakyRecord.getName()).add(leakyRecord.getDate());
        }
        for(int i = 0; i < 5; i++) {
            for(String name : map.keySet()) {
                if(!map.get(name).contains(datelist.get(i))) {
                    if(map2.get(name) == null) {
                        map2.put(name, new StringBuffer());
                    }
                    if(DateUtils.holiday(datelist.get(i)) != 2) {
                        map2.get(name).append(datelist.get(i) + ", ");
                    }
                }
            }
        }
        for(int i = 0; i < dailyUserList.size(); i++) {
            DailyUser dailyUser = dailyUserList.get(i);
            String msg = null;
            if(map2.get(dailyUser.getRealname()).length() > 0) {
                msg = "您有以下日期" + map2.get(dailyUser.getRealname()).toString().
                        substring(0, map2.get(dailyUser.getRealname()).length() - 2) + "的工时未填写，请及时填写。\r\n" +
                        "为了方便工时的统计，也请勿忘记今日工时的填写!";
            } else {
                msg = "为了方便工时的统计，请勿忘记今日工时的填写!";
            }
            try {
                mailService.sendSimple(msg, dailyUser.getMail(), dailyUser.getRealname());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "success";
    }
}
