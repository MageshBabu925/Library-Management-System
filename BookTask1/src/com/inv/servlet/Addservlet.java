package com.inv.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inv.Add;



public class Addservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Add ad = new Add();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int  bookNo = Integer.parseInt(request.getParameter("bno"));
		String bookName = request.getParameter("bname");
		String author = request.getParameter("author");
		String language = request.getParameter("lan");
		int price =  Integer.parseInt(request.getParameter("price"));
		
		String result = ad.insertRecord(bookNo, bookName, author, language, price);
		
		
		PrintWriter out = response.getWriter();
		if(result.equalsIgnoreCase("success")) {
			out.println("Successfully added new record");
			RequestDispatcher rd = request.getRequestDispatcher("Add.html");
			rd.include(request, response);
		}
		else {
			out.println("can't add record,something wrong or already it has been existed");
			RequestDispatcher rd = request.getRequestDispatcher("Add.html");
			rd.include(request, response);
		}
	}

}
