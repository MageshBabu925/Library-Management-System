package com.inv;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class View {
	public List<Book> fetchBookingsFromDatabase() throws ClassNotFoundException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Book> books = new ArrayList<Book>();

        try {
        	String query = "jdbc:mysql://localhost:3306/servlet";
        	String user = "root";
        	String pwd = "root";
        	
            String sql = "SELECT bno, bname, author, lan, price FROM booking order by bno";
            Class.forName("com.mysql.jdbc.Driver");
        	con = DriverManager.getConnection(query,user,pwd);
        	pstmt = con.prepareStatement(sql);
            rs= pstmt.executeQuery(); 

            while (rs.next()) {
            	Book b = new Book();
                b.setBno(rs.getInt(1));
                b.setBname(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setLanguage(rs.getString(4));
                b.setPrice(rs.getInt(5));
//                System.out.println(b);
                books.add(b);
                }
        }
        catch(SQLException e) {
        	e.printStackTrace();
        }
        System.out.println(books);
		return books;
		
	}
}
