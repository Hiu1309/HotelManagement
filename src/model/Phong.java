package model;

public abstract class Phong {
    private String maPhong;
    private String tenPhong;
    private int tang;
    private String trangThai;
    private double giaCoBan;

    public static int tongSoPhong = 0;

    public Phong() {
        maPhong = "";
        tenPhong = "";
        tang = 0;
        trangThai = "Trong";
        giaCoBan = 0;
        tongSoPhong++;
    }

    public Phong(String maPhong, String tenPhong, int tang,
                 String trangThai, double giaCoBan) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.tang = tang;
        this.trangThai = trangThai;
        this.giaCoBan = giaCoBan;
        tongSoPhong++;
    }

    public abstract double tinhGia(int soDem);

    public void hienThiThongTin() {
        System.out.println("Ma phong: " + maPhong);
        System.out.println("Ten phong: " + tenPhong);
        System.out.println("Tang: " + tang);
        System.out.println("Trang thai: " + trangThai);
        System.out.println("Gia co ban: " + giaCoBan);
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public int getTang() {
        return tang;
    }

    public void setTang(int tang) {
        this.tang = tang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public double getGiaCoBan() {
        return giaCoBan;
    }

    public void setGiaCoBan(double giaCoBan) {
        this.giaCoBan = giaCoBan;
    }
}
