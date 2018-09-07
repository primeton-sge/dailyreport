package com.primeton.dailyreport.util;

import com.primeton.dailyreport.dao.pojo.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 导出工具类
 * @author kongyuanyuan 2018.8.24
 */
public class ExportUtil {
    public static Workbook export(List objectList, String fileName) {
        Workbook workBook = null;
        try {
//            File exportFile = new File("详细日报明细.xls");
            File templetFile= new File(System.getProperty("user.dir")+"/exportTemplate/" + fileName);
//            if( !exportFile.exists() ) {
//                exportFile.createNewFile();
//            }
//            FileOutputStream out = new FileOutputStream(exportFile);
            FileInputStream fis = new FileInputStream(templetFile);
            workBook = new HSSFWorkbook(fis);
            CellStyle style = workBook.createCellStyle();
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setAlignment(HorizontalAlignment.CENTER);

            Sheet sheet= workBook.getSheetAt(0);

            for(int i = 0; i < objectList.size(); i++) {
                Object object = objectList.get(i);
                Row row = sheet.createRow(i + 2);
                if(object instanceof MonthSum) {
                    MonthSum monthSum = (MonthSum) object;
                    row.createCell(0).setCellValue(monthSum.getProjectname());
                    row.getCell(0).setCellStyle(style);
                    row.createCell(1).setCellValue(monthSum.getMan_hoursSum());
                    row.getCell(1).setCellStyle(style);
                    row.createCell(2).setCellValue(monthSum.getDate());
                    row.getCell(2).setCellStyle(style);
                } else if(object instanceof ProjectSum) {
                    ProjectSum projectSum = (ProjectSum) object;
                    row.createCell(0).setCellValue(projectSum.getRealname());
                    row.getCell(0).setCellStyle(style);
                    row.createCell(1).setCellValue(projectSum.getMan_hoursSum());
                    row.getCell(1).setCellStyle(style);
                    row.createCell(2).setCellValue(projectSum.getDate());
                    row.getCell(2).setCellStyle(style);
                } else if(object instanceof PersonalSum) {
                    PersonalSum personalSum = (PersonalSum) object;
                    row.createCell(0).setCellValue(personalSum.getProjectname());
                    row.getCell(0).setCellStyle(style);
                    row.createCell(1).setCellValue(personalSum.getMan_hoursSum());
                    row.getCell(1).setCellStyle(style);
                    row.createCell(2).setCellValue(personalSum.getDate());
                    row.getCell(2).setCellStyle(style);
                } else {
                    DailyInfoPlus dailyInfoPlus = (DailyInfoPlus) object;
                    row.createCell(0).setCellValue(dailyInfoPlus.getProjectname());
                    row.getCell(0).setCellStyle(style);
                    row.createCell(1).setCellValue(dailyInfoPlus.getRealname());
                    row.getCell(1).setCellStyle(style);
                    row.createCell(2).setCellValue(dailyInfoPlus.getText());
                    row.getCell(2).setCellStyle(style);
                    row.createCell(3).setCellValue(dailyInfoPlus.getMan_hours());
                    row.getCell(3).setCellStyle(style);
                    row.createCell(4).setCellValue(dailyInfoPlus.getDate());
                    row.getCell(4).setCellStyle(style);
                }
            }
//            workBook.write(out);
//            out.flush();
//            out.close();
//            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workBook;
    }

    public static void main(String[] args) {
        List<DailyInfoPlus> list = new ArrayList<>();
        DailyInfoPlus dailyInfoPlus = new DailyInfoPlus();
        dailyInfoPlus.setProjectname("001");
        dailyInfoPlus.setRealname("002");
        dailyInfoPlus.setText("003");
        dailyInfoPlus.setMan_hours(8);
        dailyInfoPlus.setDate("2018-08-31");
        list.add(dailyInfoPlus);
    }
}
