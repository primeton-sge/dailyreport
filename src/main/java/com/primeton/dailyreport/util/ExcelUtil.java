package com.primeton.dailyreport.util;

import com.primeton.dailyreport.dao.pojo.ExcelInfo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtil {
    public static File createExcelTemplate(int year, int month, List<ExcelInfo> excelInfoList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        Sheet sheet = workbook.createSheet("0");
        sheet.setColumnWidth(0, 3500);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.index);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle2.setFillForegroundColor(new XSSFColor(new java.awt.Color(250, 191, 143)));
        //cellStyle2.setFillForegroundColor(new XSSFColor(250, 191, 143));
        cellStyle2.setBorderBottom(BorderStyle.THIN);
        cellStyle2.setBorderLeft(BorderStyle.THIN);
        cellStyle2.setBorderRight(BorderStyle.THIN);
        cellStyle2.setBorderTop(BorderStyle.THIN);
        cellStyle2.setAlignment(HorizontalAlignment.CENTER);

        CellStyle cellStyle3 = workbook.createCellStyle();
        cellStyle3.setBorderBottom(BorderStyle.THIN);
        cellStyle3.setBorderLeft(BorderStyle.THIN);
        cellStyle3.setBorderRight(BorderStyle.THIN);
        cellStyle3.setBorderTop(BorderStyle.THIN);
        cellStyle3.setAlignment(HorizontalAlignment.RIGHT);

        CellStyle cellStyle4 = workbook.createCellStyle();
        cellStyle4.setBorderBottom(BorderStyle.THIN);
        cellStyle4.setBorderLeft(BorderStyle.THIN);
        cellStyle4.setBorderRight(BorderStyle.THIN);
        cellStyle4.setBorderTop(BorderStyle.THIN);
        cellStyle4.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle cellStyle5 = workbook.createCellStyle();
        cellStyle5.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle5.setFillForegroundColor(new XSSFColor(new java.awt.Color(250, 191, 143)));
        cellStyle5.setBorderBottom(BorderStyle.THIN);
        cellStyle5.setBorderLeft(BorderStyle.THIN);
        cellStyle5.setBorderRight(BorderStyle.THIN);
        cellStyle5.setBorderTop(BorderStyle.THIN);
        cellStyle5.setAlignment(HorizontalAlignment.RIGHT);

        CellStyle cellStyle6 = workbook.createCellStyle();
        cellStyle6.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle6.setFillForegroundColor(IndexedColors.PALE_BLUE.index);
        cellStyle6.setBorderBottom(BorderStyle.THIN);
        cellStyle6.setBorderLeft(BorderStyle.THIN);
        cellStyle6.setBorderRight(BorderStyle.THIN);
        cellStyle6.setBorderTop(BorderStyle.THIN);
        cellStyle6.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle cellStyle7 = workbook.createCellStyle();
        cellStyle7.setBorderBottom(BorderStyle.THIN);
        cellStyle7.setBorderLeft(BorderStyle.THIN);
        cellStyle7.setBorderRight(BorderStyle.THIN);
        cellStyle7.setBorderTop(BorderStyle.THIN);
        cellStyle7.setAlignment(HorizontalAlignment.CENTER);
        cellStyle7.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle7.setFont(font);

        CellStyle cellStyle8 = workbook.createCellStyle();
        cellStyle8.setBorderTop(BorderStyle.THIN);

        Row row = sheet.createRow(0);
        for(int i = 0; i <= excelInfoList.size(); i++) {
            if(i == 0) {
                sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
                row.createCell(0).setCellValue(month + "月份");
                row.getCell(0).setCellStyle(cellStyle);
            } else {
                sheet.addMergedRegion(new CellRangeAddress(0,0,3 * i - 1,3 * i + 1));
                row.createCell(3 * i - 1).setCellValue(excelInfoList.get(i - 1).getRealname());
                row.getCell(3 * i - 1).setCellStyle(cellStyle);
            }
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH, month-1);
        int dayNumOfMonth = getDaysByYearMonth(year, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);// 从一号开始
        for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {
            Date d = cal.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Row rowTemp = sheet.createRow(i + 1);
            if(getWeekOfDate(d).equals("日") || getWeekOfDate(d).equals("六")) {
                rowTemp.createCell(0).setCellValue(simpleDateFormat.format(d));
                rowTemp.getCell(0).setCellStyle(cellStyle2);
                rowTemp.createCell(1).setCellValue(getWeekOfDate(d));
                rowTemp.getCell(1).setCellStyle(cellStyle2);
                for(int j = 1; j <= excelInfoList.size(); j++) {
                    rowTemp.createCell(3 * j - 1);
                    rowTemp.getCell(3 * j - 1).setCellStyle(cellStyle5);
                    rowTemp.createCell(3 * j);
                    rowTemp.getCell(3 * j).setCellStyle(cellStyle5);
                    rowTemp.createCell(3 * j + 1).setCellValue(0.0);
                    rowTemp.getCell(3 * j + 1).setCellStyle(cellStyle5);
                }
            } else {
                rowTemp.createCell(0).setCellValue(simpleDateFormat.format(d));
                rowTemp.getCell(0).setCellStyle(cellStyle4);
                rowTemp.createCell(1).setCellValue(getWeekOfDate(d));
                rowTemp.getCell(1).setCellStyle(cellStyle4);
                for(int j = 1; j <= excelInfoList.size(); j++) {
                    rowTemp.createCell(3 * j - 1).setCellValue("9:00");
                    rowTemp.getCell(3 * j - 1).setCellStyle(cellStyle3);
                    rowTemp.createCell(3 * j);
                    rowTemp.getCell(3 * j).setCellStyle(cellStyle3);
                    rowTemp.createCell(3 * j + 1).setCellValue(0.0);
                    rowTemp.getCell(3 * j + 1).setCellStyle(cellStyle3);
                }
            }
        }
        Row sumRow = sheet.createRow(dayNumOfMonth + 1);
        sumRow.createCell(0).setCellValue("月合计：");
        sumRow.getCell(0).setCellStyle(cellStyle6);
        sumRow.createCell(1);
        sumRow.getCell(1).setCellStyle(cellStyle6);
        for(int i = 0; i < excelInfoList.size(); i++) {
            sumRow.createCell(3 * i + 2);
            sumRow.getCell(3 * i + 2).setCellStyle(cellStyle6);
            sumRow.createCell(3 * i + 3);
            sumRow.getCell(3 * i + 3).setCellStyle(cellStyle6);
            sumRow.createCell(3 * i + 4);
            sumRow.getCell(3 * i + 4).setCellStyle(cellStyle6);
            sumRow.getCell(3 * i + 4).setCellFormula("SUM(" + CellReference.convertNumToColString(3 * i + 4) + "2:"
                    + CellReference.convertNumToColString(3 * i + 4) + (dayNumOfMonth + 1) + ")");
        }
        Row daySumRow = sheet.createRow(dayNumOfMonth + 2);
        daySumRow.createCell(3 * excelInfoList.size() + 1);
        daySumRow.getCell(3 * excelInfoList.size() + 1).setCellStyle(cellStyle4);
        daySumRow.getCell(3 * excelInfoList.size() + 1).
                setCellFormula(CellReference.convertNumToColString(3 * excelInfoList.size() + 1) + (dayNumOfMonth + 2)  + "/8");
        Row endRow1 = sheet.createRow(dayNumOfMonth + 3);
        Row endRow2 = sheet.createRow(dayNumOfMonth + 4);
        sheet.addMergedRegion(new CellRangeAddress(dayNumOfMonth + 3,dayNumOfMonth + 4,0,0));
        endRow1.createCell(0).setCellValue("合计：");
        endRow1.getCell(0).setCellStyle(cellStyle7);
        endRow1.createCell(1);
        endRow1.getCell(1).setCellStyle(cellStyle7);
        endRow1.getCell(1).setCellFormula("SUM(" + CellReference.convertNumToColString(4) + (dayNumOfMonth + 2) + ":"
                + CellReference.convertNumToColString(3 * excelInfoList.size() + 1) + (dayNumOfMonth + 2) + ")");
        endRow1.createCell(2).setCellValue("小时");
        endRow1.getCell(2).setCellStyle(cellStyle4);
        endRow2.createCell(1);
        endRow2.getCell(1).setCellStyle(cellStyle7);
        endRow2.getCell(1).setCellFormula("B" + (dayNumOfMonth + 3) + "/8/20.83");
        endRow2.createCell(2).setCellValue("人月");
        endRow2.getCell(2).setCellStyle(cellStyle4);
        Row endRow3 = sheet.createRow(dayNumOfMonth + 5);
        endRow3.createCell(0).setCellStyle(cellStyle8);
        workbook.setSheetName(0, year +"年" + month + "月");
        cal.add(Calendar.DATE, -1);
        String date = new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());
        File file = null;
        try {
            file = new File(System.getProperty("user.dir") +
                    "/template" + date.substring(0, 4) + date.substring(5, 7) + ".xlsx");
            if(!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileoutputStream = new FileOutputStream(file);
            workbook.write(fileoutputStream);
            fileoutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static boolean appendExcel(List<ExcelInfo> excelInfoList, List<Date> dates, String lastMonday, String currentMonday) {
        boolean flag = false;
//        if(!startdate.substring(5, 7).equals(enddate.substring(5, 7))) {
//            File file = createExcelTemplate(Integer.parseInt(enddate.substring(0, 4)),
//                    Integer.parseInt(enddate.substring(0, 4)), excelInfoList);
//            flag = true;
//        }
        File lastfile = new File(System.getProperty("user.dir") +
                "/template" + lastMonday.substring(0, 4) + lastMonday.substring(5 , 7) + ".xlsx");
        if(!lastfile.exists()) {
            lastfile = createExcelTemplate(Integer.parseInt(lastMonday.substring(0, 4)),
                    Integer.parseInt(lastMonday.substring(5 , 7)), excelInfoList);
        }
        FileInputStream in = null;
        Workbook workbook = null;
        CreationHelper factory = null;
        try {
             in = new FileInputStream(lastfile); // 文件流
             workbook = new XSSFWorkbook(in);
             factory = workbook.getCreationHelper();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Sheet sheet = workbook.getSheetAt(0);
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = factory.createClientAnchor();
        for(int i = 0; i < excelInfoList.size(); i++) {
            ExcelInfo excelInfo = excelInfoList.get(i);
            for(int j = 0; j < dates.size(); j++) {
                Date date = dates.get(j);
                String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
                if(dateString.equals(currentMonday)) {
                    break;
                }
                String data = excelInfo.getInfoMap().get(dateString);
                int rownum = Integer.parseInt(dateString.substring(dateString.length() - 2 ,dateString.length()));
                if(data == null) {
                    if(!getWeekOfDate(date).equals("日") && !getWeekOfDate(date).equals("六")) {
                        sheet.addMergedRegion(new CellRangeAddress(rownum,rownum,3 * i + 2,3 * i + 3));
                        sheet.getRow(rownum).getCell(3 * i + 2).setCellValue("请假");
                        sheet.getRow(rownum).getCell(3 * i + 2).setCellStyle(cellStyle);
                    }
                } else {
                    String[] datas = data.split("@");
                    int man_hour = Integer.parseInt(datas[0]);
                    sheet.getRow(rownum).getCell(3 * i + 4).setCellValue(man_hour);
                    if(man_hour >= 10) {
                        Comment comment = drawing.createCellComment(anchor);
                        RichTextString str = factory.createRichTextString(datas[1]);
                        comment.setString(str);
                        sheet.getRow(rownum).getCell(3 * i + 4).setCellComment(comment);
                    }
                    sheet.getRow(rownum).getCell(3 * i + 3).setCellValue(man_hour + 10 + ":00");
                    if(getWeekOfDate(date).equals("日") || getWeekOfDate(date).equals("六")) {
                        sheet.getRow(rownum).getCell(3 * i + 2).setCellValue("9:00");
                    }
                }
            }
        }
        try {
            FileOutputStream os = new FileOutputStream(lastfile);
            os.flush();
            workbook.write(os);
            in.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    //获取指定月份的天数
    public static int getDaysByYearMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        int maxDate = cal.get(Calendar.DATE);
        return maxDate;
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    public static String getColumnName(int num) {
//        String columnName = "";
//        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        while(num / 26 != 0) {
//            if(num % 26 == 0) {
//                columnName += "Z";
//            } else {
//                columnName += base.charAt(num % 26 - 1);
//            }
//            num /= 26;
//        }
//        columnName += base.charAt(num - 1);
//        StringBuffer sb = new StringBuffer(columnName);
//        sb.reverse();
//        return sb.toString();
        int excelColNum = num + 1;
        StringBuilder colRef = new StringBuilder(2);
        int colRemain = excelColNum;

        while(colRemain > 0) {
            int thisPart = colRemain % 26;
            if (thisPart == 0) {
                thisPart = 26;
            }

            colRemain = (colRemain - thisPart) / 26;
            char colChar = (char)(thisPart + 64);
            colRef.insert(0, colChar);
        }

        return colRef.toString();
    }

    public static File cloneFile(String path, String currentMonday) {
        int n = 0;
        File copy = new File(path.substring(0, path.length() - 19) + "普元管控开发人员考勤工作量统计表-" +
            currentMonday.substring(0, 4) + currentMonday.substring(5 , 7) + currentMonday.substring(8, 10) + ".xlsx");
        try {
            FileInputStream fis = new FileInputStream(path);
            FileOutputStream fos = new FileOutputStream(copy);
            byte[] bs = new byte[1024*1024];
            while ((n = fis.read(bs)) != -1) {
                fos.write(bs,0,n);
            }
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return copy;
    }

    public static void main(String[] args) {
        System.out.println("SUM(" + getColumnName(5) + "2:"
                + getColumnName(5) + (30 + 1) + ")");
    }

}

