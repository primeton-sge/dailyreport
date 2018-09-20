package com.primeton.dailyreport.util;


import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by qinhao on 2018/7/3.
 * Update by kongyuanyuan on 2018.8.20
 */
public class DateUtils {

    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    public static int getMondayPlus(int days) {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6 + days;
        } else {
            return 2 - dayOfWeek + days;
        }
    }

    /**
     * 获取当前周 周一日期
     *
     * @return
     */
    public static String getCurrentMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }

    public static String getCurrentMonday(int days) {
        int mondayPlus = getMondayPlus(days);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }


    /**
     * 获取当前周 周日日期
     *
     * @return
     */
    public static String getPreviousSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }

    public static String getPreviousSunday(int days) {
        int mondayPlus = getMondayPlus(days);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }

    public static int getCurrentweek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static int getCurrentweek(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(dateStrToDate(date));
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static Date dateStrToDate(String dateStr) {
        Date date = null;
        if (StringUtils.isEmpty(dateStr))
            return null;
        Map<String, String> map = new HashMap<String, String>();
        map.put("yyyy-MM-dd", "[0-9]{4}-[0-9]{2}-[0-9]{2}");
        map.put("yyyyMMdd", "[0-9]{4}[0-9]{2}[0-9]{2}");
        map.put("yyyyMMddHHmmss", "[0-9]{8}[0-9]{6}");
        map.put("yyyy-MM-dd HH:mm:ss", "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-2]{1}[0-9]{1}:[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}");
        for (String key : map.keySet()) {
            Pattern p = Pattern.compile(map.get(key));
            Matcher m = p.matcher(dateStr);
            boolean dateFlag = m.matches();
            if (dateFlag) {
                SimpleDateFormat tempFormat = new SimpleDateFormat(key);
                try {
                    date = tempFormat.parse(dateStr);
                } catch (ParseException e) {
                    return new Date();
                }
            }
        }
        return date;
    }

    public static String formatStrDateToString(String dateStr, String formatStr) {
        Date date = dateStrToDate(dateStr);
        if (date == null)
            return "";
        if (StringUtils.isEmpty(formatStr))
            formatStr = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(date);
    }


    /**
     * 获得该月第一天
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
    }

    /**
     * 获得该月最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

    public static String getYearMonthOfToday() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        if(month < 10) {
            return String.valueOf(year) + "0" + String.valueOf(month);
        }
        return String.valueOf(year) + String.valueOf(month);
    }
    public static void main(String[] args) {
        System.out.println(getCurrentMonday(0));
    }
}
