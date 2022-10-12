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
public class NhanVien {
    private String maNV;
    private String tenNV;
    private String chucVu;
    private float luongCoBan;
    private float phuCap;

    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, String chucVu, float luongCoBan, float phuCap) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.chucVu = chucVu;
        this.luongCoBan = luongCoBan;
        this.phuCap = phuCap;
    }

    public NhanVien(String maNV) {
        this.maNV = maNV;
    }

    public NhanVien(String maNV,String tenNV) {
        this.tenNV = tenNV;
        this.maNV = maNV;
    }
    
    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public float getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(float luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public float getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(float phuCap) {
        this.phuCap = phuCap;
    }
    
    public float tinhLuong(float luongCoBan, float phuCap){
        return luongCoBan + phuCap;
    }
}
