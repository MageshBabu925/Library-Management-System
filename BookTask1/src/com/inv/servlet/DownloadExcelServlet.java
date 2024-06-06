package com.inv.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.inv.Book;
import com.inv.View;

public class DownloadExcelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            View viewrecord = new View();
            List<Book> books = viewrecord.fetchBookingsFromDatabase();

            // Create Excel workbook and sheet
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Books");

            // Add header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Book_No");
            headerRow.createCell(1).setCellValue("Book_Name");
            headerRow.createCell(2).setCellValue("Author");
            headerRow.createCell(3).setCellValue("Language");
            headerRow.createCell(4).setCellValue("Price");

            // Add data rows
            int rowNum = 1;
            for (Book book : books) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(book.getBno());
                row.createCell(1).setCellValue(book.getBname());
                row.createCell(2).setCellValue(book.getAuthor());
                row.createCell(3).setCellValue(book.getLanguage());
                row.createCell(4).setCellValue(book.getPrice());
            }

            // Set content type and attachment header
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=books.xlsx");

            // Write workbook to response output stream
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred while generating Excel file.");
        }
    }
}



package com.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.classes.ViewDao;
import com.model.Capacitor;

public class DownloadExcelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ViewDao view = new ViewDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=capacitors.xlsx");

        try (XSSFWorkbook workbook = new XSSFWorkbook(); OutputStream out = response.getOutputStream()) {
            XSSFSheet sheet = workbook.createSheet("Capacitors");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = { "Circle", "Division", "Sub Division", "Section", "Substation Type", "Substation Name",
                    "EHT Name", "EHT Feeders", "Capacitor Available", "Capacitor Bank Type", "Capacitor Bank Make",
                    "Full Rated", "Cell Rated", "Number of Cells", "Work Status", "Remarks", "From Date", "To Date" };
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Fetch capacitors from database
            List<Capacitor> capacitors = view.fetchCapacitorsFromDatabase();

            // Populate data rows
            int rowNum = 1;
            for (Capacitor c : capacitors) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(c.getCircle());
                row.createCell(1).setCellValue(c.getDivision());
                row.createCell(2).setCellValue(c.getSubDivision());
                row.createCell(3).setCellValue(c.getSection());
                row.createCell(4).setCellValue(c.getSubstationType());
                row.createCell(5).setCellValue(c.getSubstationName());
                row.createCell(6).setCellValue(c.getEhtName());
                row.createCell(7).setCellValue(c.getEhtFeeders());
                row.createCell(8).setCellValue(c.getCapacitorAvailable());
                row.createCell(9).setCellValue(c.getCapacitorBankType());
                row.createCell(10).setCellValue(c.getCapacitorBankMake());
                row.createCell(11).setCellValue(c.getFullRated());
                row.createCell(12).setCellValue(c.getCellRated());
                row.createCell(13).setCellValue(c.getNumberOfCells());
                row.createCell(14).setCellValue(c.getWorkStatus());
                row.createCell(15).setCellValue(c.getRemarks());
                row.createCell(16).setCellValue(c.getFromDate());
                row.createCell(17).setCellValue(c.getToDate());
            }

            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred while generating Excel file.");

        }
    }
}
