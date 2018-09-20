package com.primeton.dailyreport;

import com.primeton.dailyreport.filter.LoginFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.primeton.dailyreport.dao")
public class DailyreportApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyreportApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean configFilter() {
        LoginFilter filter = new LoginFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        //配置过滤url
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*.htm");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
