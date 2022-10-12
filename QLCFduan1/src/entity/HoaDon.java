/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


/**
 *
 * @author Ho Nam
 */
public class HoaDon extends NhanVien {
    private String maHD;
    private String soDT;
    private String tenKH;
    private String diaChi;
    private String ngayLap;
    private String tongTien;
    
    public HoaDon() {
    }

    public HoaDon(String maHD, String maNV, String ngayLap, String soDT, String tenKH, String diaChi) {
        super(maNV);
        this.soDT = soDT;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.maHD = maHD;
        this.ngayLap = ngayLap;
    }

    public HoaDon(String maNV, String ngayLap, String tenKH){
        super(maNV);
        this.tenKH = tenKH;
        this.ngayLap = ngayLap;
    }

    public HoaDon(String maNV, String tenNV, String tenKH, String ngayLap) {
        super(maNV, tenNV);
        this.tenKH = tenKH;
        this.ngayLap = ngayLap;
    }

    
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }
}
