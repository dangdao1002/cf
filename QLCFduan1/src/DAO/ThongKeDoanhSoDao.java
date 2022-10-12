/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Connect.JDBCConnection;
import entity.ChiTietHoaDon;
import entity.ThongKe;
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
public class ThongKeDoanhSoDao {
    public static List<ChiTietHoaDon> getData(){
        List<ChiTietHoaDon> cthdList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            String sql = "select ChiTietHoaDon.maHD, HoaDon.maNV,tenNV, tenKH, ngayLap, sum(soLuongBan * donGia) as 'thanhTien'\n" +
"from ChiTietHoaDon inner join SanPham on SanPham.maSP = ChiTietHoaDon.maSP inner join HoaDon on ChiTietHoaDon.maHD = HoaDon.maHD, NhanVien\n" +
"where NhanVien.maNV = HoaDon.maNV\n" +
"group by ChiTietHoaDon.maHD, HoaDon.maNV, tenNV, tenKH, ngayLap";
            conn = JDBCConnection.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon ct = new ChiTietHoaDon(rs.getString("maHD"), rs.getString("maNV"),rs.getString("tenNV"), rs.getString("tenKH"), rs.getString("ngayLap"), rs.getFloat("thanhTien"));
                cthdList.add(ct);
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
        return cthdList;
    }
    
    public static List<ChiTietHoaDon> findByDate(String ngayLap){
        List<ChiTietHoaDon> cthdList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            String sql = "select ChiTietHoaDon.maHD, HoaDon.maNV, tenNV, tenKH, ngayLap, sum(soLuongBan * donGia) as 'thanhTien'\n" +
"from ChiTietHoaDon inner join SanPham on SanPham.maSP = ChiTietHoaDon.maSP inner join HoaDon on ChiTietHoaDon.maHD = HoaDon.maHD, NhanVien\n" +
"where NhanVien.maNV = HoaDon.maNV and ngayLap = ?\n" +
"group by ChiTietHoaDon.maHD, HoaDon.maNV, tenNV, tenKH, ngayLap";
            conn = JDBCConnection.getConnection();
            pre = conn.prepareStatement(sql);
            pre.setString(1, ngayLap);
            rs = pre.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon ct = new ChiTietHoaDon(rs.getString("maHD"), rs.getString("maNV"),rs.getString("tenNV"), rs.getString("tenKH"), rs.getString("ngayLap"), rs.getFloat("thanhTien"));
                cthdList.add(ct);
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
        return cthdList;
    }
    
    public static List<ChiTietHoaDon> sortByTT() {
        List<ChiTietHoaDon> cthds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            String sql = "select ChiTietHoaDon.maHD, HoaDon.maNV, tenNV, tenKH, ngayLap, sum(soLuongBan * donGia) as 'thanhTien'\n" +
                "from ChiTietHoaDon inner join SanPham on SanPham.maSP = ChiTietHoaDon.maSP inner join HoaDon on ChiTietHoaDon.maHD = HoaDon.maHD, NhanVien\n" +
                "where NhanVien.maNV = HoaDon.maNV\n" +
                "group by ChiTietHoaDon.maHD, HoaDon.maNV, tenNV, tenKH, ngayLap\n" +
                "order by thanhTien";
            conn = JDBCConnection.getConnection();
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon ct = new ChiTietHoaDon(rs.getString("maHD"), rs.getString("maNV"),rs.getString("tenNV"), rs.getString("tenKH"), rs.getString("ngayLap"), rs.getFloat("thanhTien"));
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
    
    public static List<ThongKe> getTongTien() {
        List<ThongKe> tkList = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "select sum(soLuongBan*donGia) as 'tongTien'\n"
                    + "from SanPham inner join ChiTietHoaDon on SanPham.maSP = ChiTietHoaDon.maSP";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                ThongKe tk = new ThongKe();
                tk.setThanhTien(Float.parseFloat(rs.getString("tongTien")));
                tkList.add(tk);
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
        return tkList;
    }
}
