package com.inv.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inv.Update;

public class Updateservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Update udao = new Update();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int  bookNo = Integer.parseInt(request.getParameter("bno"));
		String bookName = request.getParameter("bname");
		String author = request.getParameter("author");
		String language = request.getParameter("lan");
		int price =  Integer.parseInt(request.getParameter("price"));
		
		String result = udao.updateRecord(bookNo, bookName, author, language, price);
		
		PrintWriter out = response.getWriter();
		if(result.equalsIgnoreCase("success")) {
			out.println("Record updated successfully..");
			response.sendRedirect(request.getContextPath() + "/Viewservlet");
			
//			RequestDispatcher rd = request.getRequestDispatcher("homePage1.html");
//			rd.include(request, response);
		}
		else {
			out.println("can't update record,something wrong");
			RequestDispatcher rd = request.getRequestDispatcher("main.html");
			rd.include(request, response);
		}
	}

}
