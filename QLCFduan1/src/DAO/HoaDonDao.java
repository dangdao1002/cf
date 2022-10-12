/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.JDBCConnection;
import entity.HoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ho Nam
 */
public class HoaDonDao {
    
    static Connection conn = null;
    static PreparedStatement pre = null;
    
    public static void insertHD(HoaDon sp) {
        try {
            conn = JDBCConnection.getConnection();
            String sql = "insert into HoaDon values(?,?,?,?,?,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, sp.getMaHD());
            pre.setString(2, sp.getMaNV());
            pre.setString(3, sp.getSoDT());
            pre.setString(4, sp.getTenKH());
            pre.setString(5, sp.getNgayLap());
            pre.setString(6, sp.getDiaChi());

            pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    System.out.println("Lỗi: " + ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Lỗi: " + ex);
                }
            }
        }
    }

    public static void deleteHD(String maHD) {
        try {
            conn = JDBCConnection.getConnection();
            String sql = "delete from HoaDon where maHD = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, maHD);

            pre.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    System.out.println("Lỗi: " + ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Lỗi: " + ex);
                }
            }
        }
    }
}
