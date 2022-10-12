/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Admin
 */
public class KhachHang {
     private String maKH;
    private String tenKH;
    private String diaChi;

    public KhachHang() {
    
    }
    public String getMaKH() {
        return maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public KhachHang(String maKH, String tenKH, String diaChi) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    
}
