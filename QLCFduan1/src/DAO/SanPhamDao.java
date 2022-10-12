/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.JDBCConnection;
import entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ho Nam
 */
public class SanPhamDao {
    public static List<SanPham> getAll() {
        List<SanPham> spList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "select * from SanPham";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString("maSP"), rs.getString("tenSP"), rs.getFloat("donGia"), rs.getInt("soLuong"));
                spList.add(sp);
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
        return spList;
    }
    
    public static void getSoLuong(){
        
    }
    public static void insert(SanPham sp) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "insert into SanPham values(?,?,?,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, sp.getMaSP());
            pre.setString(2, sp.getTenSP());
            pre.setFloat(3, sp.getDonGia());
            pre.setInt(4, sp.getSoLuong());
            
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
    
    public static void update(SanPham sp) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "update SanPham set tenSP = ?, donGia = ?, soLuong = ? where maSP = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, sp.getTenSP());
            pre.setFloat(2, sp.getDonGia());
            pre.setInt(3, sp.getSoLuong());
            pre.setString(4, sp.getMaSP());
            
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
    
    public static void delete(String maSP) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "delete from SanPham where maSP = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, maSP);
            
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
    
    public static List<SanPham> findByTenSP(String tenSP) {
        List<SanPham> spList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "select * from SanPham where tenSP like ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%"+tenSP +"%");
            rs = pre.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham(rs.getString("maSP"), rs.getString("tenSP"), rs.getFloat("donGia"), rs.getInt("soLuong"));
                spList.add(sp);
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
        return spList;
    }
}
