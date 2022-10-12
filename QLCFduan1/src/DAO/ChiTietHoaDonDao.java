/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.JDBCConnection;
import entity.ChiTietHoaDon;
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
public class ChiTietHoaDonDao {

    public static List<ChiTietHoaDon> getTTHD(String maNV) {

        List<ChiTietHoaDon> cthds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;

        String sql = "select ChiTietHoaDon.maHD, ngayLap, maNV, tenKH, sum(soLuongBan * donGia) as 'thanhTien'\n"
            + "from ChiTietHoaDon inner join SanPham on SanPham.maSP = ChiTietHoaDon.maSP\n"
            + "inner join HoaDon on ChiTietHoaDon.maHD = HoaDon.maHD\n"
            + "where maNV = ?\n"
            + "group by ChiTietHoaDon.maHD,ngayLap, maNV, tenKH";
        try {
            conn = JDBCConnection.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, maNV);
            rs = pre.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon ct = new ChiTietHoaDon(rs.getString("maHD"), rs.getString("ngayLap"), rs.getString("maNV"),rs.getString("tenKH"), rs.getFloat("thanhTien"));
                cthds.add(ct);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ChiTietHoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ChiTietHoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return cthds;
    }

    public static List<ChiTietHoaDon> findByMaHD(String maHD, String maNV) {
        List<ChiTietHoaDon> cthds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;

        String sql = "select ChiTietHoaDon.maHD, ngayLap, maNV, tenKH, sum(soLuongBan * donGia) as 'thanhTien'\n" +
                "from ChiTietHoaDon inner join SanPham on SanPham.maSP = ChiTietHoaDon.maSP inner join HoaDon on ChiTietHoaDon.maHD = HoaDon.maHD\n" +
                "where ChiTietHoaDon.maHD = ? and maNV = ?\n" +
                "group by ChiTietHoaDon.maHD,ngayLap, maNV, tenKH";

        try {
            conn = JDBCConnection.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, maHD);
            pre.setString(2, maNV);
            rs = pre.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon ct = new ChiTietHoaDon(rs.getString("maHD"), rs.getString("ngayLap"), rs.getString("maNV"),rs.getString("tenKH"), rs.getFloat("thanhTien"));
                cthds.add(ct);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ChiTietHoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ChiTietHoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return cthds;
    }
    public static void insertCTHD(ChiTietHoaDon cthd) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "insert into ChiTietHoaDon values(?,?,?)";
            pre = conn.prepareStatement(sql);
            pre.setString(1, cthd.getMaHD());
            pre.setString(2, cthd.getMaSP());
            pre.setInt(3, cthd.getSoLuongBan());

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
    
    public static void deleteCTHD(String maHD) {
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "delete from ChiTietHoaDon where maHD = ?";
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
    
    public static List<ChiTietHoaDon> sortByDate(String maNV) {
        List<ChiTietHoaDon> cthds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            String sql = "select ChiTietHoaDon.maHD, ngayLap, maNV, tenKH, sum(soLuongBan * donGia) as 'thanhTien'\n" +
                    "from ChiTietHoaDon inner join SanPham on SanPham.maSP = ChiTietHoaDon.maSP inner join HoaDon on ChiTietHoaDon.maHD = HoaDon.maHD\n" +
                    "where maNV = ?\n" +
                    "group by ChiTietHoaDon.maHD,ngayLap, maNV, tenKH\n" +
                    "order by ngayLap";
            conn = JDBCConnection.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, maNV);
            rs = pre.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon ct = new ChiTietHoaDon(rs.getString("maHD"), rs.getString("ngayLap"), rs.getString("maNV"),rs.getString("tenKH"), rs.getFloat("thanhTien"));
                cthds.add(ct);
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex);
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ChiTietHoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ChiTietHoaDonDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return cthds;
    }
}
