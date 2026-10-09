package model;

public abstract class Nguoi {
    private String ma;
    private String hoTen;
    private int namSinh;
    private String gioiTinh;
    private String soDienThoai;
    private String diaChi;

    public Nguoi() {
        ma = "";
        hoTen = "";
        namSinh = 0;
        gioiTinh = "";
        soDienThoai = "";
        diaChi = "";
    }

    public Nguoi(String ma, String hoTen, int namSinh,
                 String gioiTinh, String soDienThoai, String diaChi) {
        this.ma = ma;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    public abstract void hienThiThongTin();

    public abstract String getVaiTro();

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
