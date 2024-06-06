package com.inv.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inv.Delete;



public class Deleteservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    
    Delete delete = new Delete();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
            String bno = request.getParameter("bno");
            String result = delete.deleteRecord(bno);
    		
    		PrintWriter out = response.getWriter();
    		if(result.equalsIgnoreCase("success")) {
    			response.sendRedirect(request.getContextPath() + "/Viewservlet");
    		}
    		else {
    			out.println("can't delete record,something wrong");
    			RequestDispatcher rd = request.getRequestDispatcher("Main.html");
    			rd.include(request, response);
    		}
    }

       //You can also add the doPost method if needed
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}



