package model;

public class QuanLy extends NhanVien {
    private double heSoChucVu;

    public QuanLy() {
        super();
        heSoChucVu = 1;
    }

    public QuanLy(String ma, String hoTen, int namSinh,
                  String gioiTinh, String soDienThoai, String diaChi,
                  double luongCoBan, String ngayVaoLam,
                  double heSoChucVu) {
        super(ma, hoTen, namSinh, gioiTinh, soDienThoai, diaChi,
              luongCoBan, ngayVaoLam);
        this.heSoChucVu = heSoChucVu;
    }

    @Override
    public double tinhLuong() {
        return getLuongCoBan() * heSoChucVu;
    }

    @Override
    public String getVaiTro() {
        return "QUANLY";
    }

    public double getHeSoChucVu() {
        return heSoChucVu;
    }

    public void setHeSoChucVu(double heSoChucVu) {
        this.heSoChucVu = heSoChucVu;
    }
}
