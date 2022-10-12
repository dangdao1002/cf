/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.Timer;
import utils.ChuyenManHinh;
import utils.DanhMuc;
import utils.MsgBox;

/**
 *
 * @author Admin
 */
public class NV extends javax.swing.JFrame {

    /**
     * Creates new form NV
     */
    String maNV;

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    String tenNV;

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public NV() {
        new Loading(this, true).setVisible(true);
        //setLocationRelativeTo(null);
        initComponents();
        init();
        List<DanhMuc> listItem = new ArrayList<>();
        listItem.add(new DanhMuc("TrangChu", jpnTrangchu, jlbTrangchu));
        listItem.add(new DanhMuc("Menu", jpnMenu, jlbMenu));
        listItem.add(new DanhMuc("KhachHang", jpnKhachHang, jlbKhachHang));
        //listItem.add(new DanhMuc("HoaDon", jpnHoaDon, jlbHoaDon));
        listItem.add(new DanhMuc("ThongKe", jpnThongKe, jlbThongKe));
        //listItem.add(new DanhMuc("Thoat", jpnThoat, jlbThoat));
        //listItem.add(new DanhMuc("DanhMuc", jpnDanhMuc, jlbDanhMuc));
        ChuyenManHinh controller = new ChuyenManHinh(jpnView);
        controller.setView(jpnTrangchu, jlbTrangchu);
        controller.setEvent(listItem);
    }

    private void init() {
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Date now = new Date();
                SimpleDateFormat formater = new SimpleDateFormat("hh:mm:ss a");
                String text = formater.format(now);
                lblDongHo.setText(text);
            }
        }).start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnMenuTong = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        tblNV = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        DangXuat = new javax.swing.JLabel();
        jpnTrangchu = new javax.swing.JPanel();
        jlbTrangchu = new javax.swing.JLabel();
        jpnMenu = new javax.swing.JPanel();
        jlbMenu = new javax.swing.JLabel();
        jpnKhachHang = new javax.swing.JPanel();
        jlbKhachHang = new javax.swing.JLabel();
        jpnThongKe = new javax.swing.JPanel();
        jlbThongKe = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblDongHo = new javax.swing.JLabel();
        jpnView = new javax.swing.JPanel();
        jpnDanhMuc = new javax.swing.JPanel();
        jlbDanhMuc = new javax.swing.JLabel();
        jpnHoaDon = new javax.swing.JPanel();
        jlbHoaDon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnMenuTong.setBackground(new java.awt.Color(102, 102, 102));

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));

        tblNV.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        tblNV.setForeground(new java.awt.Color(102, 0, 204));
        tblNV.setText("Cà Phê hoho");
        tblNV.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblNVAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Xin chào");

        DangXuat.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        DangXuat.setText("Đăng xuất");
        DangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DangXuatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DangXuat)
                    .addComponent(tblNV)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tblNV)
                .addGap(47, 47, 47)
                .addComponent(DangXuat)
                .addContainerGap())
        );

        jpnTrangchu.setBackground(new java.awt.Color(204, 204, 255));
        jpnTrangchu.setPreferredSize(new java.awt.Dimension(300, 50));

        jlbTrangchu.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jlbTrangchu.setForeground(new java.awt.Color(255, 255, 255));
        jlbTrangchu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home.png"))); // NOI18N
        jlbTrangchu.setText("  Trang Chủ");

        javax.swing.GroupLayout jpnTrangchuLayout = new javax.swing.GroupLayout(jpnTrangchu);
        jpnTrangchu.setLayout(jpnTrangchuLayout);
        jpnTrangchuLayout.setHorizontalGroup(
            jpnTrangchuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTrangchuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbTrangchu, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
        );
        jpnTrangchuLayout.setVerticalGroup(
            jpnTrangchuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnTrangchuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlbTrangchu, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpnMenu.setBackground(new java.awt.Color(204, 204, 255));
        jpnMenu.setPreferredSize(new java.awt.Dimension(300, 50));

        jlbMenu.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jlbMenu.setForeground(new java.awt.Color(255, 255, 255));
        jlbMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/coffee-cup.png"))); // NOI18N
        jlbMenu.setText("  Menu");

        javax.swing.GroupLayout jpnMenuLayout = new javax.swing.GroupLayout(jpnMenu);
        jpnMenu.setLayout(jpnMenuLayout);
        jpnMenuLayout.setHorizontalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnMenuLayout.setVerticalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );

        jpnKhachHang.setBackground(new java.awt.Color(204, 204, 255));
        jpnKhachHang.setPreferredSize(new java.awt.Dimension(300, 50));

        jlbKhachHang.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jlbKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        jlbKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/employees.png"))); // NOI18N
        jlbKhachHang.setText("  Khách Hàng");

        javax.swing.GroupLayout jpnKhachHangLayout = new javax.swing.GroupLayout(jpnKhachHang);
        jpnKhachHang.setLayout(jpnKhachHangLayout);
        jpnKhachHangLayout.setHorizontalGroup(
            jpnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnKhachHangLayout.setVerticalGroup(
            jpnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        jpnThongKe.setBackground(new java.awt.Color(204, 204, 255));
        jpnThongKe.setPreferredSize(new java.awt.Dimension(300, 50));

        jlbThongKe.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jlbThongKe.setForeground(new java.awt.Color(255, 255, 255));
        jlbThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bar-chart.png"))); // NOI18N
        jlbThongKe.setText("  Thống Kê");

        javax.swing.GroupLayout jpnThongKeLayout = new javax.swing.GroupLayout(jpnThongKe);
        jpnThongKe.setLayout(jpnThongKeLayout);
        jpnThongKeLayout.setHorizontalGroup(
            jpnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnThongKeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnThongKeLayout.setVerticalGroup(
            jpnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        lblDongHo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDongHo.setText("00:00:00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jpnMenuTongLayout = new javax.swing.GroupLayout(jpnMenuTong);
        jpnMenuTong.setLayout(jpnMenuTongLayout);
        jpnMenuTongLayout.setHorizontalGroup(
            jpnMenuTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnTrangchu, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
            .addComponent(jpnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
            .addComponent(jpnKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
            .addComponent(jpnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnMenuTongLayout.setVerticalGroup(
            jpnMenuTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMenuTongLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jpnTrangchu, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpnView.setBackground(new java.awt.Color(230, 230, 230));

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 935, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );

        jpnDanhMuc.setBackground(new java.awt.Color(204, 204, 255));
        jpnDanhMuc.setPreferredSize(new java.awt.Dimension(300, 50));

        jlbDanhMuc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jlbDanhMuc.setForeground(new java.awt.Color(255, 255, 255));
        jlbDanhMuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menu.png"))); // NOI18N
        jlbDanhMuc.setText("  Danh mục");
        jlbDanhMuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbDanhMucMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpnDanhMucLayout = new javax.swing.GroupLayout(jpnDanhMuc);
        jpnDanhMuc.setLayout(jpnDanhMucLayout);
        jpnDanhMucLayout.setHorizontalGroup(
            jpnDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnDanhMucLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnDanhMucLayout.setVerticalGroup(
            jpnDanhMucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        jpnHoaDon.setBackground(new java.awt.Color(204, 204, 255));
        jpnHoaDon.setPreferredSize(new java.awt.Dimension(300, 50));

        jlbHoaDon.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jlbHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        jlbHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bill.png"))); // NOI18N
        jlbHoaDon.setText("  Hóa Đơn");
        jlbHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbHoaDonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpnHoaDonLayout = new javax.swing.GroupLayout(jpnHoaDon);
        jpnHoaDon.setLayout(jpnHoaDonLayout);
        jpnHoaDonLayout.setHorizontalGroup(
            jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnHoaDonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
        );
        jpnHoaDonLayout.setVerticalGroup(
            jpnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpnMenuTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpnDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jpnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(377, 377, 377)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnMenuTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jpnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jpnView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblNVAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblNVAncestorAdded
        tblNV.setText(getTenNV());
    }//GEN-LAST:event_tblNVAncestorAdded

    private void DangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DangXuatMouseClicked
        // TODO add your handling code here:
        if (MsgBox.confirm(this, "Bạn muốn đăng xuất?")) {
            new DangNhap().setVisible(true);
        }
    }//GEN-LAST:event_DangXuatMouseClicked

    private void jlbHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbHoaDonMouseClicked
        // TODO add your handling code here:
        QuanLyHoaDon hd = new QuanLyHoaDon();
        hd.setVisible(true);
        hd.setMaNV(this.getMaNV());
    }//GEN-LAST:event_jlbHoaDonMouseClicked

    private void jlbDanhMucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbDanhMucMouseClicked
        // TODO add your handling code here:
        BanHang bh = new BanHang();
        bh.setVisible(true);
        bh.setMaNV(this.getMaNV());
    }//GEN-LAST:event_jlbDanhMucMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DangXuat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel jlbDanhMuc;
    private javax.swing.JLabel jlbHoaDon;
    private javax.swing.JLabel jlbKhachHang;
    private javax.swing.JLabel jlbMenu;
    private javax.swing.JLabel jlbThongKe;
    private javax.swing.JLabel jlbTrangchu;
    private javax.swing.JPanel jpnDanhMuc;
    private javax.swing.JPanel jpnHoaDon;
    private javax.swing.JPanel jpnKhachHang;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnMenuTong;
    private javax.swing.JPanel jpnThongKe;
    private javax.swing.JPanel jpnTrangchu;
    private javax.swing.JPanel jpnView;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel tblNV;
    // End of variables declaration//GEN-END:variables
}