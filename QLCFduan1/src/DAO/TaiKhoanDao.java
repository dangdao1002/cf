/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.JDBCConnection;
import entity.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ho Nam
 */
public class TaiKhoanDao {

    public static List<TaiKhoan> getAll() {
        List<TaiKhoan> taiKhoans = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;

        String sql = "select * from TaiKhoan";
        try {
            conn = JDBCConnection.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getString("maNV"), rs.getString("username"), rs.getString("pass"), rs.getString("quyenTruyCap"));
                taiKhoans.add(tk);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TaiKhoanDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return taiKhoans;
    }

    public static void insert(TaiKhoan tk) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "insert into TaiKhoan values(?,?,?,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, tk.getMaNV());
            pre.setString(2, tk.getUser());
            pre.setString(3, tk.getPassword());
            pre.setString(4, tk.getQuyenTruyCap());

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

    public static void update(TaiKhoan tk) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "update TaiKhoan set username = ?, pass = ?, quyenTruyCap = ? where maNV = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, tk.getUser());
            pre.setString(2, tk.getPassword());
            pre.setString(3, tk.getQuyenTruyCap());
            pre.setString(4, tk.getMaNV());

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

    public static void delete(String maNV) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "delete from TaiKhoan where maNV = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, maNV);

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

    public static List<TaiKhoan> sortBytenNV() {
        List<TaiKhoan> taiKhoans = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "select * from TaiKhoan order by username";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while(rs.next()){
                TaiKhoan tk = new TaiKhoan(rs.getString("maNV"), rs.getString("username"), rs.getString("pass"), rs.getString("quyenTruyCap"));
                taiKhoans.add(tk);
            }
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
        return taiKhoans;
    }
}
