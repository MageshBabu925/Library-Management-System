package com.inv;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Update {
    public String updateRecord(int bno, String bname, String author, String lan, int price) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int i = 0;

        try {
            String url = "jdbc:mysql://localhost:3306/servlet";
            String user = "root";
            String pwd = "root";

            String sql = "update booking set bname=?, author=?, lan=?, price=? where bno=?";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pwd);
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, bname);
            pstmt.setString(2, author);
            pstmt.setString(3, lan);
            pstmt.setInt(4, price);            
            pstmt.setInt(5, bno);

            i = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (i > 0) {
            return "SUCCESS";
        } else {
            return "FAIL";
        }
    }
}
