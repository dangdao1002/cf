/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import Connect.JDBCConnection;
import DAO.HoaDonDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.MsgBox;

/**
 *
 * @author Admin
 */
public class DanhMucMN extends javax.swing.JPanel {

    /**
     * Creates new form DanhMuc
     */
    String maNV;

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    List<HoaDon> hdList = new ArrayList<>();
    List<TaiKhoan> tkList = new ArrayList<>();
    List<ChiTietHoaDon> cthdList = new ArrayList<>();
    DefaultTableModel tableModel;
    Connection conn = null;
    PreparedStatement pre = null;
    ResultSet rs = null;

    public DanhMucMN() {
        initComponents();
        tableModel = (DefaultTableModel) tblDanhSach.getModel();
        txtDonGia.setEnabled(false);
        txtMaSP.setEnabled(false);
        txtTongTien.setEditable(false);
        btnXoa.setEnabled(false);
        txtMaNV.setEnabled(false);
        LoadTenSP();
    }

    private void LoadTenSP() {
        cbTenSP.removeAllItems();
        try {
            conn = JDBCConnection.getConnection();
            String sql = "select tenSP from SanPham";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            while (rs.next()) {
                cbTenSP.addItem(rs.getString("tenSP").trim());
            }
        } catch (SQLException ex) {
            MsgBox.alert(this, "Lỗi" + ex);
        }
    }

    private int getSoLuong() {
        int soluong = 0;
        try {
            conn = JDBCConnection.getConnection();
            String sql = "select soLuong from SanPham where maSP = ?";

            pre = conn.prepareStatement(sql);
            pre.setString(1, txtMaSP.getText());
            rs = pre.executeQuery();
            while (rs.next()) {
                soluong = rs.getInt("soLuong");
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
        return soluong;
    }

    private void getSoluongCon() {

        try {
            conn = JDBCConnection.getConnection();
            String sql = "select soLuong from SanPham where maSP = ?";

            pre = conn.prepareStatement(sql);
            pre.setString(1, txtMaSP.getText());
            rs = pre.executeQuery();
            while (rs.next()) {
                lblSoLuongCon.setText("Số lượng còn: " + rs.getInt("soLuong"));
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

    }

    private boolean checkMaHD() {
        try {
            conn = JDBCConnection.getConnection();
            String sqlCheck = "SELECT maHD FROM HoaDon";
            PreparedStatement pre = conn.prepareStatement(sqlCheck);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                if (this.txtMaHoaDon.getText().trim().equalsIgnoreCase(rs.getString("maHD").toString().trim())) {
                    return false;
                }
            }
        } catch (Exception ex) {
            MsgBox.alert(this, "Lỗi: " + ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DanhMucMN.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }

    private void tongThanhTien() {
        try {
            conn = JDBCConnection.getConnection();
            String sql = "select sum(soLuongBan * donGia) as 'thanhTien'\n"
                    + "from ChiTietHoaDon inner join SanPham on SanPham.maSP = ChiTietHoaDon.maSP\n"
                    + "where maHD = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, cbMaHD.getSelectedItem().toString().trim());
            rs = pre.executeQuery();
            while (rs.next()) {
                txtTongTien.setText(String.valueOf(rs.getFloat("thanhTien")));
            }
        } catch (SQLException ex) {
            MsgBox.alert(this, "Lỗi: " + ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DanhMucMN.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void loadDataTable() {
        try {
            conn = JDBCConnection.getConnection();
            String sql = "select ChiTietHoaDon.maSP, tenSP, soLuongBan, donGia, soLuongBan * donGia as 'thanhTien'\n"
                    + "from ChiTietHoaDon inner join SanPham on SanPham.maSP = ChiTietHoaDon.maSP\n"
                    + "where maHD = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, cbMaHD.getSelectedItem().toString().trim());
            rs = pre.executeQuery();
            tableModel.setRowCount(0);
            while (rs.next()) {

                Vector arr = new Vector();
                arr.add(rs.getString("maSP"));
                arr.add(rs.getString("tenSP"));
                arr.add(rs.getInt("soLuongBan"));
                arr.add(rs.getFloat("donGia"));
                arr.add(rs.getString("thanhTien"));

                tableModel.addRow(arr);

            }
        } catch (SQLException ex) {
            MsgBox.alert(this, "Lỗi: " + ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DanhMucMN.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void loadMaHD() {
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet rs = null;
        cbMaHD.removeAllItems();
        try {
            conn = JDBCConnection.getConnection();
            String sql = "select * from HoaDon where maNV = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, txtMaNV.getText().trim());
            rs = pre.executeQuery();
            while (rs.next()) {
                cbMaHD.addItem(rs.getString("maHD").trim());
            }
        } catch (SQLException ex) {
            MsgBox.alert(this, "Lỗi: " + ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSoDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dateNgayLap = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtTenKhachHang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtSoLuongBan = new javax.swing.JTextField();
        cbTenSP = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtTongTien = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnSapXep = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThemHoaDon = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        cbMaHD = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        lblSoLuongCon = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Quản lý bán hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(446, 446, 446))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhập hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel2.setText("Số điện thoại");

        jLabel4.setText("Địa chỉ");

        jLabel5.setText("Ngày lập");

        jLabel11.setText("Mã hoá đơn");

        jLabel14.setText("Mã nhân viên");

        txtMaNV.setEnabled(false);

        jLabel3.setText("Tên khách hàng");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel11)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateNgayLap, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel4))
                            .addComponent(jLabel3))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(txtDiaChi)
                    .addComponent(txtTenKhachHang))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(jLabel5)
                    .addComponent(dateNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhập đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel7.setText("Tên sản phẩm");

        jLabel8.setText("Đơn giá");

        jLabel9.setText("Số lượng bán");

        cbTenSP.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbTenSPPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel13.setText("Mã sản phẩm");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(22, 22, 22)
                        .addComponent(txtMaSP))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cbTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(txtSoLuongBan))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cbTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Tổng tiền hoá đơn");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel12)
                .addGap(34, 34, 34)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnThem.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/them.png"))); // NOI18N
        btnThem.setText("Thêm hàng");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/reset.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnSapXep.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSapXep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sort.png"))); // NOI18N
        btnSapXep.setText("Sắp xếp theo giá");
        btnSapXep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapXepActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xoa.png"))); // NOI18N
        btnXoa.setText("Xoá hàng");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThemHoaDon.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnThemHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sua.png"))); // NOI18N
        btnThemHoaDon.setText("Thêm hóa đơn");
        btnThemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnThemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(btnSapXep)
                .addGap(78, 78, 78))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSapXep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThemHoaDon))
                .addContainerGap())
        );

        tblDanhSach.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Số lượng bán", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSach.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSach);

        cbMaHD.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbMaHDPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Mã hóa đơn");

        lblSoLuongCon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(cbMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSoLuongCon)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblSoLuongCon, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbTenSPPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbTenSPPopupMenuWillBecomeInvisible
        try {
            conn = JDBCConnection.getConnection();
            String sql = "select * from SanPham where tenSP = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, cbTenSP.getSelectedItem().toString());
            rs = pre.executeQuery();
            while (rs.next()) {
                txtMaSP.setText(rs.getString("maSP").trim());
                txtDonGia.setText(rs.getString("donGia").trim());
            }
        } catch (SQLException ex) {
            MsgBox.alert(this, "Lỗi: " + ex);
        } catch (Exception ex) {
            MsgBox.alert(this, "Chưa có sản phẩm nào");
        }
        getSoluongCon();
    }//GEN-LAST:event_cbTenSPPopupMenuWillBecomeInvisible

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:

        if (txtMaSP.getText().isEmpty() && txtDonGia.getText().isEmpty()) {
            MsgBox.alert(this, "Chọn tên hàng cần thêm");
            return;
        }
        if (txtSoLuongBan.getText().isEmpty()) {
            MsgBox.alert(this, "Số lượng bán không được trống");
            return;
        }

        int soLuong = Integer.parseInt(txtSoLuongBan.getText());
        if (soLuong <= 0) {
            MsgBox.alert(this, "Số lượng bán phải lớn hơn 0");
            return;
        }
        if (soLuong > getSoLuong()) {
            MsgBox.alert(this, "Số lượng bán lớn hơn số lượng hàng đang có");
            return;
        }

        try {
            String maHD = cbMaHD.getSelectedItem().toString().trim();
            String maSP = txtMaSP.getText().trim();
            int soLuongBan = Integer.parseInt(txtSoLuongBan.getText().trim());
            ChiTietHoaDon cthd = new ChiTietHoaDon(maHD, maSP, soLuongBan);
            conn = JDBCConnection.getConnection();
            String sql1 = "insert into ChiTietHoaDon values(?,?,?)";
            pre = conn.prepareStatement(sql1);
            pre.setString(1, cthd.getMaHD());
            pre.setString(2, cthd.getMaSP());
            pre.setInt(3, cthd.getSoLuongBan());
            int n = pre.executeUpdate();

            if (n > 0) {
                String sql2 = "update SanPham set soLuong = soLuong - ? where maSP = ?";
                pre = conn.prepareStatement(sql2);
                pre.setInt(1, cthd.getSoLuongBan());
                pre.setString(2, cthd.getMaSP());
                pre.executeUpdate();
            }
        } catch (SQLException ex) {
            MsgBox.alert(this, "Trùng đơn hàng");
        } catch (NumberFormatException ex) {
            MsgBox.alert(this, "Số lượng bán phải là số và không trống!");
        } catch (Exception ex) {
            MsgBox.alert(this, "Khoá chính");
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

        btnXoa.setEnabled(false);
        loadDataTable();
        tongThanhTien();
        getSoluongCon();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtMaHoaDon.setText("");
        txtSoDT.setText("");
        txtTenKhachHang.setText("");
        txtDiaChi.setText("");
        dateNgayLap.setDate(null);
        txtMaSP.setText("");
        txtDonGia.setText("");
        txtSoLuongBan.setText("");
        LoadTenSP();
        btnXoa.setEnabled(false);
        txtMaNV.setText(this.getMaNV());
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnSapXepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapXepActionPerformed
        // TODO add your handling code here:
        try {
            conn = JDBCConnection.getConnection();
            String sql = "select ChiTietHoaDon.maSP, tenSP, soLuongBan, donGia, soLuongBan * donGia as 'thanhTien'\n"
                    + "from ChiTietHoaDon inner join SanPham on SanPham.maSP = ChiTietHoaDon.maSP\n"
                    + "where maHD = ?\n"
                    + "order by donGia\n";
            pre = conn.prepareStatement(sql);
            pre.setString(1, cbMaHD.getSelectedItem().toString().trim());
            rs = pre.executeQuery();
            tableModel.setRowCount(0);
            while (rs.next()) {
                Vector arr = new Vector();
                arr.add(rs.getString("maSP"));
                arr.add(rs.getString("tenSP"));
                arr.add(rs.getInt("soLuongBan"));
                arr.add(rs.getFloat("donGia"));
                arr.add(rs.getString("thanhTien"));
                tableModel.addRow(arr);
            }
        } catch (SQLException ex) {
            MsgBox.alert(this, "Lỗi" + ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DanhMucMN.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        btnXoa.setEnabled(false);
    }//GEN-LAST:event_btnSapXepActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        String maHD = cbMaHD.getSelectedItem().toString().trim();
        String maSP = txtMaSP.getText().trim();
        int soLuongBan = Integer.parseInt(txtSoLuongBan.getText().trim());
        ChiTietHoaDon cthd = new ChiTietHoaDon(maHD, maSP, soLuongBan);
        try {
            conn = JDBCConnection.getConnection();
            String sql1 = "delete from ChiTietHoaDon where maSP = ? and maHD = ?";
            pre = conn.prepareStatement(sql1);
            pre.setString(1, cthd.getMaSP());
            pre.setString(2, cthd.getMaHD());

            int n = pre.executeUpdate();
            if (n > 0) {
                String sql2 = "update SanPham set soLuong = soLuong + ? where maSP = ?";
                pre = conn.prepareStatement(sql2);
                pre.setInt(1, cthd.getSoLuongBan());
                pre.setString(2, cthd.getMaSP());
                pre.executeUpdate();
            }

        } catch (Exception ex) {
            MsgBox.alert(this, "Lỗi" + ex);
        }
        btnXoa.setEnabled(false);
        loadDataTable();
        tongThanhTien();
        getSoluongCon();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHoaDonActionPerformed
        // TODO add your handling code here:
        if (txtMaHoaDon.getText().isEmpty()) {
            MsgBox.alert(this, "Mã hóa đơn không được trống");
            return;
        }
        if (txtSoDT.getText().isEmpty()) {
            MsgBox.alert(this, "Số điện thoại không được trống");
            return;
        }
        if (txtTenKhachHang.getText().isEmpty()) {
            MsgBox.alert(this, "Tên khách hàng không được trống");
            return;
        }
        if (dateNgayLap.getDate() == null) {
            MsgBox.alert(this, "Ngày lập không được trống");
            return;
        }
        if (txtDiaChi.getText().isEmpty()) {
            MsgBox.alert(this, "Địa chỉ không được trống");
            return;
        }
        try {
            String maHD = txtMaHoaDon.getText().trim();
            String maNV = txtMaNV.getText().trim();
            String soDT = txtSoDT.getText().trim();
            String tenKH = txtTenKhachHang.getText().trim();
            String diaChi = txtDiaChi.getText().trim();

            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String ngayLap = dateFormat.format(dateNgayLap.getDate());

            if (checkMaHD()) {
                HoaDon themHD = new HoaDon(maHD, maNV, ngayLap, soDT, tenKH, diaChi);
                HoaDonDao.insertHD(themHD);
                MsgBox.alert(this, "Thêm hóa đơn thành công");
            } else {
                MsgBox.alert(this, "Mã hoá đơn không được trùng");
                return;
            }
        } catch (Exception ex) {
            MsgBox.alert(this, "Lỗi" + ex);
        }
        loadMaHD();
    }//GEN-LAST:event_btnThemHoaDonActionPerformed

    private void tblDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMouseClicked
        // TODO add your handling code here:
        int row = tblDanhSach.getSelectedRow();
        if (row >= 0) {
            txtMaSP.setText(tableModel.getValueAt(row, 0).toString().trim());
            cbTenSP.setSelectedItem(tableModel.getValueAt(row, 1).toString().trim());
            txtSoLuongBan.setText(tableModel.getValueAt(row, 2).toString().trim());
            txtDonGia.setText(tableModel.getValueAt(row, 3).toString().trim());
        }
        btnXoa.setEnabled(true);
        getSoluongCon();
    }//GEN-LAST:event_tblDanhSachMouseClicked

    private void cbMaHDPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbMaHDPopupMenuWillBecomeInvisible
        //Đổ dữ liệu lên textFiel khi chọn combox MaHD
        try {
            conn = JDBCConnection.getConnection();
            String sql = "select * from HoaDon where maHD = ?";
            pre = conn.prepareStatement(sql);
            pre.setString(1, cbMaHD.getSelectedItem().toString().trim());
            rs = pre.executeQuery();
            while (rs.next()) {
                txtMaHoaDon.setText(rs.getString("maHD").trim());
                txtSoDT.setText(rs.getString("soDT").toString().trim());
                txtTenKhachHang.setText(rs.getString("tenKH").toString().trim());
                txtMaNV.setText(rs.getString("maNV").toString().trim());
                dateNgayLap.setDate(rs.getDate("ngayLap"));
                txtDiaChi.setText(rs.getString("diaChi").toString().trim());
            }
            loadDataTable();
            tongThanhTien();
        } catch (SQLException ex) {
            MsgBox.alert(this, "Lỗi" + ex);
        } catch (Exception ex) {
            MsgBox.alert(this, "Chưa có hoá đơn nào");
        } finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DanhMucMN.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DanhMucMN.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        btnXoa.setEnabled(false);
    }//GEN-LAST:event_cbMaHDPopupMenuWillBecomeInvisible


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSapXep;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemHoaDon;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbMaHD;
    private javax.swing.JComboBox<String> cbTenSP;
    private com.toedter.calendar.JDateChooser dateNgayLap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSoLuongCon;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoDT;
    private javax.swing.JTextField txtSoLuongBan;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
