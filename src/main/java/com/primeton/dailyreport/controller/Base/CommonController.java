package com.primeton.dailyreport.controller.Base;

import com.primeton.dailyreport.controller.Bean.Nodes;
import com.primeton.dailyreport.dao.pojo.DailyUser;
import com.primeton.dailyreport.util.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinhao on 2018/7/3.
 */
@Controller
@RequestMapping(value = {"/common/"})
public class CommonController extends BaseController {

    /**
     * 获取菜单json
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "getMenuTree.json")
    @ResponseBody
    public List<Nodes> getMenuTree(HttpServletRequest request) {
        DailyUser user = (DailyUser) request.getSession().getAttribute(DR_USER);
        List<Nodes> nodesList = new ArrayList<>();
        Nodes export = new Nodes("日报填写", "/daily/save.htm");
        Nodes query = new Nodes("日报查询", "/daily/query.htm");
        Nodes manage = new Nodes("项目管理", "/manager/query.htm");
        Nodes bind = new Nodes("绑定AME+", "/user/startBindAme.htm");
        if (user.getJurisdiction() == '2') {
            nodesList.add(export);
            nodesList.add(bind);
        } else if (user.getJurisdiction() == '1') {
            nodesList.add(query);
            nodesList.add(manage);
        } else {
            nodesList.add(export);
            nodesList.add(query);
            nodesList.add(manage);
            nodesList.add(bind);
        }
        return nodesList;
    }


    /**
     * 主页空白页
     *
     * @return
     */
    @RequestMapping(value = "main.htm")
    public String main() {
        return "main";
    }
}
