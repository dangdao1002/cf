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
public class ChiTietHoaDon extends HoaDon{
    protected String maHD;
    protected String maSP;
    protected int soLuongBan;
    protected float thanhTien;
    
    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String maHD, String maNV, String tenNV, String tenKH, String ngayLap, float thanhTien){
        super(maNV, tenNV, tenKH, ngayLap);
        this.maHD = maHD;
        this.thanhTien = thanhTien;
    }
    public ChiTietHoaDon(String maHD, String maSP, int soLuongBan, float thanhTien) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuongBan = soLuongBan;
        this.thanhTien = thanhTien;
    }
    
    public ChiTietHoaDon(String maHD, String maSP, int soLuongBan) {
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuongBan = soLuongBan;
    }

    public ChiTietHoaDon(String maHD, String ngayLap, String maNV,String tenKH, float thanhTien){
        super(maNV, ngayLap,tenKH);
        this.maHD = maHD;
        this.thanhTien = thanhTien;
    }
    
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    

}
