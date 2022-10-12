/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ho Nam
 */
public class JDBCConnection {
    public static Connection getConnection(){
        String url = "jdbc:sqlserver://localhost:1433;databaseName=BTL_QuanLyBanHang";
        String username = "sa";
        String password = "songlong";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Không load được dữ liệu");
        } catch (SQLException ex) {
            System.out.println("Không kết nối được");
        }
        return null;
    }
}
