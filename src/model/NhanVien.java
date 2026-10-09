package model;

public abstract class NhanVien extends Nguoi {
    private double luongCoBan;
    private String ngayVaoLam;

    public NhanVien() {
        super();
        luongCoBan = 0;
        ngayVaoLam = "";
    }

    public NhanVien(String ma, String hoTen, int namSinh,
                    String gioiTinh, String soDienThoai, String diaChi,
                    double luongCoBan, String ngayVaoLam) {
        super(ma, hoTen, namSinh, gioiTinh, soDienThoai, diaChi);
        this.luongCoBan = luongCoBan;
        this.ngayVaoLam = ngayVaoLam;
    }

    public abstract double tinhLuong();

    @Override
    public void hienThiThongTin() {
        System.out.println("Ma nhan vien: " + getMa());
        System.out.println("Ho ten: " + getHoTen());
        System.out.println("Nam sinh: " + getNamSinh());
        System.out.println("Gioi tinh: " + getGioiTinh());
        System.out.println("So dien thoai: " + getSoDienThoai());
        System.out.println("Dia chi: " + getDiaChi());
        System.out.println("Luong co ban: " + luongCoBan);
        System.out.println("Ngay vao lam: " + ngayVaoLam);
        System.out.println("Luong thuc nhan: " + tinhLuong());
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public String getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(String ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }
}
