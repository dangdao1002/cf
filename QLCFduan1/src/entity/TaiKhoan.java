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
public class TaiKhoan {
    private String maNV;
    private String user;
    private String password;
    private String quyenTruyCap;

    public TaiKhoan() {
    }

    public TaiKhoan(String maNV, String user, String password, String quyenTruyCap) {
        this.maNV = maNV;
        this.user = user;
        this.password = password;
        this.quyenTruyCap = quyenTruyCap;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuyenTruyCap() {
        return quyenTruyCap;
    }

    public void setQuyenTruyCap(String quyenTruyCap) {
        this.quyenTruyCap = quyenTruyCap;
    }
    
}
