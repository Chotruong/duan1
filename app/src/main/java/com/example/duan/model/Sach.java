package com.example.duan.model;

public class Sach {

    private int maSach;
    private String tenSach;
    private int giaThue;
    private int maLoai;
    private int soLuongdamuon;
    private String tenloai;

    public Sach(int maSach, String tenSach, int giaThue, int maLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
    }

    public Sach(int maSach, String tenSach, int soLuongdamuon) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soLuongdamuon = soLuongdamuon;
    }

    public Sach(int maSach, String tenSach, int giaThue, int maLoai, String tenloai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
        this.tenloai = tenloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public int getSoLuongdamuon() {
        return soLuongdamuon;
    }

    public void setSoLuongdamuon(int soLuongdamuon) {
        this.soLuongdamuon = soLuongdamuon;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}
