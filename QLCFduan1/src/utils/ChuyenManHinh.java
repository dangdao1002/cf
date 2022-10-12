/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import ui.HD;
import ui.QuanLyNhanVien;
import ui.KH;
import ui.DanhMucMN;
import ui.TrangChu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ui.QuanLySanPham;
import ui.ThongKeDoanhThu;
import ui.TK;

/**
 *
 * @author ADMIN
 */
public class ChuyenManHinh {
    
    private JPanel Chinh;
    private String kindSelected = "";
    private List<DanhMuc>listItem = null;

    public ChuyenManHinh(JPanel jpnChinh) {
        this.Chinh = jpnChinh;
    }
    
    public void setView(JPanel jpnItem, JLabel jlbItem){
        kindSelected = "TrangChu";
        jpnItem.setBackground(new Color(132,112,255));
        jlbItem.setBackground(new Color(132,112,255));
        Chinh.removeAll();
        Chinh.setLayout(new BorderLayout());
        Chinh.add(new TrangChu());
        Chinh.validate();
        Chinh.repaint();
        
    }
    public void setEvent(List<DanhMuc>listItem){
        this.listItem = listItem;
        for(DanhMuc item : listItem){
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }
    
    class LabelEvent implements MouseListener{
        
        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "TrangChu":
                    node = new TrangChu();
                    break;
                case "Menu":
                   node = new QuanLySanPham();
                    break; 
                case "NhanVien":
                   node = new QuanLyNhanVien();
                    break; 
                case "KhachHang":
                   node = new KH();
                    break; 
                case "ThongKe":
                    node = new ThongKeDoanhThu();
                    break;   
                case "HoaDon":
                   node = new HD();
                    break;
                case "DanhMuc":
                   node = new DanhMucMN();
                    break;
                case "TaiKhoan":
                    node = new TK();
                    break;
                
                //more
                default:
                    break;
            }
            
            Chinh.removeAll();
            Chinh.setLayout(new BorderLayout());
            Chinh.add (node);
            Chinh.validate();
            Chinh.repaint();
            setChangeBackgroud(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(132,112,255));
        jlbItem.setBackground(new Color(132,112,255));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(132,112,255));
        jlbItem.setBackground(new Color(132,112,255));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!kindSelected.equalsIgnoreCase(kind)){
                jpnItem.setBackground(new Color(204,204,255));
                jlbItem.setBackground(new Color(204,204,255));
                
            }
        }
        
    }
    
    private void setChangeBackgroud(String kind){
        for(DanhMuc item : listItem){
            if (item.getKind().equalsIgnoreCase(kind)){
                item.getJpn().setBackground(new Color(132,112,255));
                item.getJlb().setBackground(new Color(132,112,255));
            } else {
                item.getJpn().setBackground(new Color(204,204,255));
                item.getJlb().setBackground(new Color(204,204,255));
            }
        }
    }
    
   
}

