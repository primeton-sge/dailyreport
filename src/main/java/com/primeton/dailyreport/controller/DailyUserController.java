package com.primeton.dailyreport.controller;

import com.primeton.dailyreport.controller.Base.BaseController;
import com.primeton.dailyreport.controller.Bean.Message;
import com.primeton.dailyreport.dao.pojo.AmeProject;
import com.primeton.dailyreport.dao.pojo.AmeTask;
import com.primeton.dailyreport.dao.pojo.DailyUser;
import com.primeton.dailyreport.service.AmeService;
import com.primeton.dailyreport.service.DailyUserService;
import com.primeton.dailyreport.util.AESUtil;
import com.primeton.dailyreport.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 日报用户
 * @author kongyuanyuan 2018.8.20
 */
@Controller
@RequestMapping(value = {"/user/"})
public class DailyUserController extends BaseController {

    @Autowired
    private DailyUserService dailyUserService;

    @Autowired
    private AmeService ameService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/loginSubmit.htm")
    public String doLogin(String username, String password, HttpServletRequest request, Model model) {
        String md5Passwd = MD5Util.md5Password(password);
        try {
            DailyUser user = dailyUserService.queryUser(username, md5Passwd);
            if (user == null) {
                throw new Exception("用户名或密码错误!");
            }
            HttpSession session = request.getSession();
            session.setAttribute(DR_USER, user);
        } catch (Exception e) {
            model.addAttribute(ERROR_MSG, e.getMessage());
            return "login";
        }
        return "redirect:/main/home.htm";
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout.htm")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(DR_USER);
        return "redirect:/login.htm";
    }

    @RequestMapping(value = "/startChangePassword.htm")
    public String startChange() {
        return "user/changePassword";
    }

    /**
     * 修改密码
     * @param request
     * @param password
     * @param newPassword
     * @param model
     * @return
     */
    @RequestMapping(value = "/changePassword.htm")
    public String change(HttpServletRequest request, @ModelAttribute("password") String password,
                         @ModelAttribute("newPassword") String newPassword, Model model) {
        DailyUser user = (DailyUser) request.getSession().getAttribute(DR_USER);
        DailyUser dailyUser = null;
        try {
            dailyUser = dailyUserService.queryUser(user.getUsername(), MD5Util.md5Password(password));
            if(dailyUser == null) {
                throw new Exception("密码错误");
            }
            dailyUserService.changePassword(MD5Util.md5Password(newPassword), user.getUid());
        } catch (Exception e) {
            model.addAttribute("er_msg", e.getMessage());
            return "user/changePassword";
        }
        model.addAttribute("success_msg", "修改成功");
        return "user/changePassword";
    }

    @RequestMapping(value = {"/startBindAme.htm"})
    public String startBind(Model model) {
        List<AmeProject> ameProjectList = new ArrayList<>();
        List<AmeTask> ameTaskList = new ArrayList<>();
        try {
            ameProjectList = ameService.queryAllAmeProject();
            ameTaskList = ameService.queryAllAmeTask();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("ameProjectList", ameProjectList);
        model.addAttribute("ameTaskList", ameTaskList);
        return "user/bindAme";
    }

    @RequestMapping(value = {"/bindAme.htm"})
    public String bind(HttpServletRequest request, @ModelAttribute("ameUsername") String ameUsername,
                       @ModelAttribute("amePassword") String amePassword,
                       @ModelAttribute("ameProjectId") String ameProjectId,
                       @ModelAttribute("ameTaskId") String ameTaskId, Model model) {
        DailyUser user = (DailyUser) request.getSession().getAttribute(DR_USER);
        int flag = 0;
        if(user.getAmeUsername() != null) {
            flag = 1;
        }
        try {
            dailyUserService.bindAme(user.getUid(), ameUsername, AESUtil.invokeEncryptEncode(amePassword), Integer.parseInt(ameProjectId), Integer.parseInt(ameTaskId));
        } catch (Exception e) {
            model.addAttribute(ERROR_MSG, e.getMessage());
            e.printStackTrace();
        }
        if(flag == 0) {
            model.addAttribute(SUCCESS_MSG, "绑定成功");
        } else {
            model.addAttribute(SUCCESS_MSG, "修改成功");
        }
        return "user/bindAme";
    }


}
