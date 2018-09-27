package com.primeton.dailyreport.controller;

import com.alibaba.fastjson.JSONObject;
import com.primeton.dailyreport.controller.Base.BaseController;
import com.primeton.dailyreport.controller.Bean.FormBean;
import com.primeton.dailyreport.dao.pojo.*;
import com.primeton.dailyreport.service.DailyInfoService;
import com.primeton.dailyreport.service.DailyUserService;
import com.primeton.dailyreport.service.ProjectService;
import com.primeton.dailyreport.util.DateUtils;
import com.primeton.dailyreport.util.ExportUtil;
import com.primeton.dailyreport.util.JsonUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 日报查询
 * @author kongyuanyuan 2018.8.24
 */
@Controller
@RequestMapping(value = {"/daily/"})
public class DailyInfoController extends BaseController {
    @Autowired
    private DailyInfoService dailyInfoService;

    @Autowired
    private DailyUserService dailyUserService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = {"/query.htm"})
    public String query(HttpServletRequest request, Model model, @ModelAttribute("formbean") FormBean formbean) {
        List<DailyUser> employeeList = new ArrayList<>();
        List<Project> projectList = new ArrayList<>();
        if(null == formbean.getDate()) {
            formbean.setDate(DateUtils.getYearMonthOfToday());
        }
        String year = formbean.getDate().substring(0, 4);
        String month = formbean.getDate().substring(4, 6);
        String startdate = DateUtils.getFirstDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
        String enddate = DateUtils.getLastDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
        try {
            employeeList = dailyUserService.queryAll();
            projectList = projectService.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("projectList", projectList);
        return "daily/list";
    }

    @RequestMapping(value = {"/queryList.json"}, method = RequestMethod.POST)
    @ResponseBody
    public ResultBean getDailyInfo(@RequestParam("jsonStr") String jsonStr) {
        ResultBean resultBean = new ResultBean();
        FormBean formBean = JsonUtil.parseToFormBean(jsonStr);
        String date = formBean.getDate();
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String startdate = DateUtils.getFirstDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
        String enddate = DateUtils.getLastDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
        List<DailyInfoPlus> dailyInfoPlusList = new ArrayList<>();
        List<ProjectSum> projectSumList = new ArrayList<>();
        List<PersonalSum> personalSumList = new ArrayList<>();
        List<MonthSum> monthSumList = new ArrayList<>();
        if(formBean.getPid() == null && formBean.getUid() == null) {
            resultBean.setFlag("1");
            try {
                monthSumList = dailyInfoService.querySumByMonth(startdate, enddate, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resultBean.setMonthSumList(monthSumList);
        } else if(formBean.getPid() != null && formBean.getUid() == null) {
            resultBean.setFlag("2");
            try {
                projectSumList = dailyInfoService.querySumByPid(startdate , enddate, formBean.getPid());
            } catch (Exception e) {
                e.printStackTrace();
            }
            resultBean.setProjectSumList(projectSumList);
        } else if(formBean.getPid() == null && formBean.getUid() != null) {
            resultBean.setFlag("3");
            try {
                personalSumList = dailyInfoService.querySumByPerson(startdate, enddate, null, formBean.getUid());
            } catch (Exception e) {
                e.printStackTrace();
            }
            resultBean.setPersonalSumList(personalSumList);
        } else {
            resultBean.setFlag("4");
            try {
                dailyInfoPlusList = dailyInfoService.query(startdate, enddate, formBean.getPid(), formBean.getUid());
            } catch (Exception e) {
                e.printStackTrace();
            }
            resultBean.setDailyInfoPlusList(dailyInfoPlusList);
        }
        return resultBean;
    }

    @RequestMapping(value = {"/queryCheck.json"}, method = RequestMethod.POST)
    @ResponseBody
    public List<LeakyRecord> check() {
        String startdate = DateUtils.getCurrentMonday(-7);
        String enddate = DateUtils.getCurrentMonday(-3);
        List<String> datelist = new ArrayList<>();
        for(int i = 3; i <= 7; i++) {
            datelist.add(DateUtils.getCurrentMonday(-i));
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
        for(int i = datelist.size() - 1; i >= 0; i--) {
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
        List<LeakyRecord> result = new ArrayList<>();
        for(String name : map2.keySet()) {
            LeakyRecord record = new LeakyRecord();
            record.setDate(map2.get(name).toString().substring(0, map2.get(name).length() - 2));
            record.setName(name);
            result.add(record);
        }
        return result;
    }

    @RequestMapping(value = {"/export.xls"})
    public String export(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("fromBean") FormBean formBean) {
        int flag = 0;
        List<DailyInfoPlus> dailyInfoPlusList = new ArrayList<>();
        List<ProjectSum> projectSumList = new ArrayList<>();
        List<PersonalSum> personalSumList = new ArrayList<>();
        List<MonthSum> monthSumList = new ArrayList<>();
        String year = formBean.getDate().substring(0, 4);
        String month = formBean.getDate().substring(4, 6);
        String startdate = DateUtils.getFirstDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
        String enddate = DateUtils.getLastDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
        Workbook workbook = null;
        if(formBean.getPid() == null && formBean.getUid() == null) {
            try {
                monthSumList = dailyInfoService.querySumByMonth(startdate, enddate, null);
                workbook = ExportUtil.export(monthSumList, "月度工时统计.xls");
                flag = 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(formBean.getPid() != null && formBean.getUid() == null) {
            try {
                projectSumList = dailyInfoService.querySumByPid(startdate , enddate, formBean.getPid());
                workbook = ExportUtil.export(projectSumList, "项目工时统计.xls");
                flag = 1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(formBean.getPid() == null && formBean.getUid() != null) {
            try {
                personalSumList = dailyInfoService.querySumByPerson(startdate, enddate, null, formBean.getUid());
                workbook = ExportUtil.export(personalSumList, "员工工时统计.xls");
                flag = 2;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                dailyInfoPlusList = dailyInfoService.query(startdate, enddate, formBean.getPid(), formBean.getUid());
                workbook = ExportUtil.export(dailyInfoPlusList, "详细日报明细.xls");
                flag = 3;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.setHeader("content-Type", "dailyreport/vnd.ms-excel");
        // 下载文件的默认名称
        try {
            String filename = null;
            if(flag == 0) {
                filename = "月度工时统计";
            } else if(flag == 1) {
                filename = "项目工时统计";
            } else if(flag == 2) {
                filename = "员工工时统计";
            } else {
                filename = "详细日报明细";
            }
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8") + ".xls");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //编码
        response.setCharacterEncoding("UTF-8");
        try {
            response.getOutputStream().flush();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
