package model;

public class BuongPhong extends NhanVien {
    private int soPhongPhuTrach;

    public BuongPhong() {
        super();
        soPhongPhuTrach = 0;
    }

    public BuongPhong(String ma, String hoTen, int namSinh,
                      String gioiTinh, String soDienThoai, String diaChi,
                      double luongCoBan, String ngayVaoLam,
                      int soPhongPhuTrach) {
        super(ma, hoTen, namSinh, gioiTinh, soDienThoai, diaChi,
              luongCoBan, ngayVaoLam);
        this.soPhongPhuTrach = soPhongPhuTrach;
    }

    @Override
    public double tinhLuong() {
        return getLuongCoBan() + soPhongPhuTrach * 100000;
    }

    @Override
    public String getVaiTro() {
        return "BUONGPHONG";
    }

    public int getSoPhongPhuTrach() {
        return soPhongPhuTrach;
    }

    public void setSoPhongPhuTrach(int soPhongPhuTrach) {
        this.soPhongPhuTrach = soPhongPhuTrach;
    }
}
