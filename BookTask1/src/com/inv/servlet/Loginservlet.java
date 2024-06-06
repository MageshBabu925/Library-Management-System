package com.inv.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inv.Login;


public class Loginservlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	Login ldao = new Login();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		String result = "";
		try {
			result = ldao.check(userName, password);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		if(result.equalsIgnoreCase("success")) {
			RequestDispatcher rd = request.getRequestDispatcher("Main.html");
			rd.forward(request, response);
		}
		else {
			out.println("Your name and password, not exists! Go and SIGN UP");
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.include(request, response);		
		}
	}

}
