/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.JDBCConnection;
import entity.NhanVien;
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
public class NhanVienDao {

    public static List<NhanVien> getAll() {
        List<NhanVien> nvList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "select * from NhanVien";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), rs.getString("chucVu"), rs.getFloat("luongCoBan"), rs.getFloat("phuCap"));
                nvList.add(nv);
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

    public static void insert(NhanVien nv) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "insert into NhanVien values(?,?,?,?,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, nv.getMaNV());
            pre.setString(2, nv.getTenNV());
            pre.setString(3, nv.getChucVu());
            pre.setFloat(4, nv.getLuongCoBan());
            pre.setFloat(5, nv.getPhuCap());

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

    public static void update(NhanVien nv) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "update NhanVien set tenNV = ?, chucVu = ?, luongCoBan = ?, phuCap = ? where maNV = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, nv.getTenNV());
            pre.setString(2, nv.getChucVu());
            pre.setFloat(3, nv.getLuongCoBan());
            pre.setFloat(4, nv.getPhuCap());
            pre.setString(5, nv.getMaNV());

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
            String sql = "delete from NhanVien where maNV = ?";
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

    public static List<NhanVien> findByTenNV(String tenNV) {
        List<NhanVien> nvList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "select * from NhanVien where tenNV like ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, "%"+tenNV+"%");
            rs = pre.executeQuery();
            while (rs.next()) {
                NhanVien sp = new NhanVien(rs.getString("maNV"), rs.getString("tenNV"), rs.getString("chucVu"), rs.getFloat("luongCoBan"), rs.getFloat("phuCap"));
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
