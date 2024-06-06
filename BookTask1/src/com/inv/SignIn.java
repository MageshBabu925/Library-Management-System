package com.inv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SignIn {
	public String insertRecord(String username, String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int i = 0;
		try {
			String url = "jdbc:mysql://localhost:3306/servlet";
			String user = "root";
			String pwd = "root";
			
			String sql = "insert into login values(?,?)";
			Class.forName("com.mysql.jdbc.Driver");  
			con = DriverManager.getConnection(url,user,pwd);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, email);
			
			i = pstmt.executeUpdate();
			
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		if(i > 0) {
			return "SUCCESS";
		}
		else {
			return "FAIL";
		}
		
	}

}
