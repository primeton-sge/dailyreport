package com.primeton.dailyreport.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by qinhao on 2018/7/3.
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //定义不拦截路径
        String[] pattern = new String[]{"/login.htm", "/loginSubmit.htm", "/redirectPost.htm"};
        //请求路径
        String url = request.getRequestURI();
        boolean notFilter = checkUrl(pattern, url);
        //检查请求路径
        if (!notFilter) {
            //过滤请求
            HttpSession session = request.getSession();
            Object user = session.getAttribute("dr_user");
            //判断用户是否登录
            if (user == null) {
                //3.验证cookie是否有信息
                //未登录,重定向到登录页面
                request.setAttribute("actionUrl", request.getContextPath() + "/login.htm");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/redirectPost.htm");
                dispatcher.forward(request, response);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    /**
     * 检验url是否过滤
     *
     * @param notUrls
     * @param url
     * @return
     */
    public static boolean checkUrl(String[] notUrls, String url) {
        if (url.endsWith(".css") || url.endsWith(".js") ||url.endsWith(".png")) {
            return true;
        }
        for (String str : notUrls) {
            if (url.indexOf(str) != -1) {
                return true;
            }
        }
        return false;
    }
}