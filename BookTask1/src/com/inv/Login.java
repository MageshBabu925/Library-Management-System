package com.inv;

import java.sql.*;

public class Login {
	public String check(String Username, String Password) throws SQLException {
		
		ResultSet rs=null;
		try {
			String query = "jdbc:mysql://localhost:3306/servlet";
			String user = "root";
			String pwd = "root";
			
			String sql = "select username,password from login where username=? AND password=?";
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con = DriverManager.getConnection(query,user,pwd);
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Username);
			pstmt.setString(2, Password);
			rs = pstmt.executeQuery();
			
			}
		catch(Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//                if (pstmt != null) pstmt.close();
//                if (con != null) con.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//		}
		if(rs.next()) {
			return "SUCCESS";
		}
		else {
			return "FAIL";
		}
	}
}
