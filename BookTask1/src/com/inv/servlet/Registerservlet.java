package com.inv.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inv.SignIn;

public class Registerservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	SignIn sign = new SignIn();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username"); 
		String password = request.getParameter("password");
		
		String result = sign.insertRecord(userName, password);
		
		PrintWriter out = response.getWriter();
		if(result.equalsIgnoreCase("success")) {
			out.println("Successfully created account..");
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.forward(request, response);
		}
		else {
			out.println("can't create account");
			RequestDispatcher rd = request.getRequestDispatcher("Signin.html");
			rd.include(request, response);		
		}
	}

}
