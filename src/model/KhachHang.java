package model;

public class KhachHang extends Nguoi {
    private int diemTichLuy;
    private String loaiKhach;

    public static int demSoKhach = 0;

    public KhachHang() {
        super();
        diemTichLuy = 0;
        loaiKhach = "Thuong";
        demSoKhach++;
    }

    public KhachHang(String ma, String hoTen, int namSinh,
                     String gioiTinh, String soDienThoai, String diaChi,
                     int diemTichLuy, String loaiKhach) {
        super(ma, hoTen, namSinh, gioiTinh, soDienThoai, diaChi);
        this.diemTichLuy = diemTichLuy;
        this.loaiKhach = loaiKhach;
        demSoKhach++;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("Ma khach: " + getMa());
        System.out.println("Ho ten: " + getHoTen());
        System.out.println("Nam sinh: " + getNamSinh());
        System.out.println("Gioi tinh: " + getGioiTinh());
        System.out.println("So dien thoai: " + getSoDienThoai());
        System.out.println("Dia chi: " + getDiaChi());
        System.out.println("Diem tich luy: " + diemTichLuy);
        System.out.println("Loai khach: " + loaiKhach);
    }

    @Override
    public String getVaiTro() {
        return "KHACH";
    }

    public int getDiemTichLuy() {
        return diemTichLuy;
    }

    public void setDiemTichLuy(int diemTichLuy) {
        this.diemTichLuy = diemTichLuy;
    }

    public String getLoaiKhach() {
        return loaiKhach;
    }

    public void setLoaiKhach(String loaiKhach) {
        this.loaiKhach = loaiKhach;
    }
}
