package com.jobportal.ExceplExporter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.jobportal.entity.Job;

@Service
public class JobExcelExporter {

    public static ByteArrayInputStream JobListToExcelFile(List<Job> job) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("jobs");

            Row row = sheet.createRow(0);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // Creating header
            Cell cell = row.createCell(0);
            cell.setCellValue("Job Id");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(1);
            cell.setCellValue("Job Title");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(2);
            cell.setCellValue("Job Desc");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(3);
            cell.setCellValue("Skills ");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(4);
            cell.setCellValue("Qualification");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(5);
            cell.setCellValue("Vancancy");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(6);
            cell.setCellValue("Last Date Sumbit");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(7);
            cell.setCellValue("created date");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(8);
            cell.setCellValue("updated date");
            cell.setCellStyle(headerCellStyle);

            cell = row.createCell(9);
            cell.setCellValue("status");
            cell.setCellStyle(headerCellStyle);

            for (int i = 0; i < job.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
                dataRow.createCell(0).setCellValue(job.get(i).getJobId());
                dataRow.createCell(1).setCellValue(job.get(i).getJobTitle());
                dataRow.createCell(2).setCellValue(job.get(i).getJobDesc());
                dataRow.createCell(3).setCellValue(job.get(i).getSkills());
                dataRow.createCell(4).setCellValue(job.get(i).getQualification());
                System.out.println("he");
                System.out.println(job.get(i).getVacancy());
                dataRow.createCell(5).setCellValue(job.get(i).getVacancy());
            
                dataRow.createCell(6).setCellValue(DATE_FORMAT.format(job.get(i).getLastDateSubmit()));
                dataRow.createCell(7).setCellValue(DATE_FORMAT.format(job.get(i).getCreateDate()));
                dataRow.createCell(8).setCellValue(DATE_FORMAT.format(job.get(i).getUpdateDate()));
                
                dataRow.createCell(9).setCellValue(job.get(i).getStatus());
            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}