package model;

public class KeToan extends NhanVien {
    private double thuong;

    public KeToan() {
        super();
        thuong = 0;
    }

    public KeToan(String ma, String hoTen, int namSinh,
                  String gioiTinh, String soDienThoai, String diaChi,
                  double luongCoBan, String ngayVaoLam, double thuong) {
        super(ma, hoTen, namSinh, gioiTinh, soDienThoai, diaChi,
              luongCoBan, ngayVaoLam);
        this.thuong = thuong;
    }

    @Override
    public double tinhLuong() {
        return getLuongCoBan() + thuong;
    }

    @Override
    public String getVaiTro() {
        return "KETOAN";
    }

    public double getThuong() {
        return thuong;
    }

    public void setThuong(double thuong) {
        this.thuong = thuong;
    }
}
