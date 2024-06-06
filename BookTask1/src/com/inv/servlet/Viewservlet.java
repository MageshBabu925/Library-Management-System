package com.inv.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inv.Book;
import com.inv.View;

public class Viewservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	View viewrecord = new View();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		    List<Book> b = viewrecord.fetchBookingsFromDatabase();
		    PrintWriter out = response.getWriter();
		    String httpresponse = "<html><head><style>body { display: flex; align-items: center; justify-content: center; height: 100vh; } table {margin: 0 auto;}</style></head><body><div><table border='1'><tr><th>Book_No</th><th>Book_Name</th><th>Author</th><th>Language</th><th>Price</th><th>Update</th><th>Delete</th></tr>";

		    for (Book books : b) {
		        httpresponse += "<tr><td>" + books.getBno() + "</td><td>" + books.getBname() + "</td><td>" + books.getAuthor() + "</td><td>" + books.getLanguage() + "</td><td>" + books.getPrice() + "</td>";

		        httpresponse += "<td><a href='Update.html?bno=" + books.getBno() + "&bname=" + books.getBname() + "&author=" + books.getAuthor() + "&lan=" + books.getLanguage() + "&price=" + books.getPrice() + "'>Update</a></td>";

		        httpresponse += "<td><a href='Deleteservlet?bno=" + books.getBno() + "' onclick='return confirm(\"Are you sure you want to delete this record?\");'>Delete</a></td>";
		    }
		    httpresponse += "</table></div></body></html>";
		    httpresponse += "</table><br><a href='DownloadExcelServlet'>Download Excel</a></div></body></html>";
		    httpresponse += "</table><br><a href='DownloadPDFServlet'>Download PDF</a></div></body></html>";
		    out.println(httpresponse);
		}

		catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}
