package com.inv;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Delete {
    public String deleteRecord(String bno) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int i = 0;

        try {
            String url = "jdbc:mysql://localhost:3306/servlet";
            String user = "root";
            String pwd = "root";

            String sql = "delete from booking where bno=?";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pwd);
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, bno);

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
