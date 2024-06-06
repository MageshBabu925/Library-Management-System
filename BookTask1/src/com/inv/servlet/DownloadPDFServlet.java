package com.inv.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inv.Book;
import com.inv.View;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class DownloadPDFServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Set response content type
            response.setContentType("application/pdf");

            // Set response header for file attachment
            response.setHeader("Content-Disposition", "attachment; filename=books.pdf");

            // Retrieve book data from the database
            View viewRecord = new View();
            List<Book> books = viewRecord.fetchBookingsFromDatabase();

            // Create PDF document
            Document document = new Document(PageSize.A4.rotate());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            // Open document
            document.open();

            // Create table
            PdfPTable table = new PdfPTable(5); // 5 columns
            table.setWidthPercentage(100);

            // Add table headers
            table.addCell("Book No");
            table.addCell("Book Name");
            table.addCell("Author");
            table.addCell("Language");
            table.addCell("Price");

            // Add data to the table
            for (Book book : books) {
                table.addCell(String.valueOf(book.getBno()));
                table.addCell(book.getBname());
                table.addCell(book.getAuthor());
                table.addCell(book.getLanguage());
                table.addCell(String.valueOf(book.getPrice()));
            }

            // Add table to the document
            document.add(table);

            // Close document
            document.close();

            // Write PDF content to response output stream
            response.getOutputStream().write(baos.toByteArray());
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "The requested resource encountered an internal server error");
        }
    }
}


package com.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.classes.ViewDao;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.model.Capacitor;

@WebServlet("/DownloadPDFServlet")
public class DownloadPDFServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ViewDao view = new ViewDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=capacitors.pdf");

        try (OutputStream out = response.getOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            List<Capacitor> capacitors = view.fetchCapacitorsFromDatabase();
            for (Capacitor capacitor : capacitors) {
                document.add(new Paragraph("Circle: " + capacitor.getCircle()));
                document.add(new Paragraph("Division: " + capacitor.getDivision()));
                document.add(new Paragraph("Sub Division: " + capacitor.getSubDivision()));
                document.add(new Paragraph("Section: " + capacitor.getSection()));
                document.add(new Paragraph("Substation Type: " + capacitor.getSubstationType()));
                document.add(new Paragraph("Substation Name: " + capacitor.getSubstationName()));
                document.add(new Paragraph("EHT Name: " + capacitor.getEhtName()));
                document.add(new Paragraph("EHT Feeders: " + capacitor.getEhtFeeders()));
                document.add(new Paragraph("Capacitor Available: " + capacitor.getCapacitorAvailable()));
                document.add(new Paragraph("Capacitor Bank Type: " + capacitor.getCapacitorBankType()));
                document.add(new Paragraph("Capacitor Bank Make: " + capacitor.getCapacitorBankMake()));
                document.add(new Paragraph("Full Rated: " + capacitor.getFullRated()));
                document.add(new Paragraph("Cell Rated: " + capacitor.getCellRated()));
                document.add(new Paragraph("Number of Cells: " + capacitor.getNumberOfCells()));
                document.add(new Paragraph("Work Status: " + capacitor.getWorkStatus()));
                document.add(new Paragraph("Remarks: " + capacitor.getRemarks()));
                document.add(new Paragraph("From Date: " + capacitor.getFromDate()));
                document.add(new Paragraph("To Date: " + capacitor.getToDate()));
                document.add(new Paragraph("\n"));
            }

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
