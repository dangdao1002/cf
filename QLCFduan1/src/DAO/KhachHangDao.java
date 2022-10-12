/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Connect.JDBCConnection;
import entity.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Admin
 */
public class KhachHangDao {
     public static List<KhachHang> getAll() {
    List<KhachHang> khList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "select * from KhachHang";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(rs.getString("maKH"), rs.getString("tenKH"), rs.getString("diaChi"));
                khList.add(kh);
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
        return khList;
    }

    public static void insert(KhachHang kh) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "insert into KhachHang values(?,?,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, kh.getMaKH());
            pre.setString(2, kh.getTenKH());
            pre.setString(3, kh.getDiaChi());

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

    public static void update(KhachHang kh) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "update KhachHang set tenKH = ?, diaChi = ? where maKH = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, kh.getTenKH());
            pre.setString(2, kh.getDiaChi());
            pre.setString(3, kh.getMaKH());

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
            String sql = "delete from KhachHang where maKH = ?";
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

    public static List<KhachHang> findByTenKH(String tenKH) {
        List<KhachHang> nvList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "select * from KhachHang where tenKH like ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%"+tenKH+"%");
            rs = pre.executeQuery();
            while (rs.next()) {
                KhachHang sp = new KhachHang(rs.getString("maKH"), rs.getString("tenKH"), rs.getString("diaChi"));
                nvList.add(sp);
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
        return nvList;
    }
}
