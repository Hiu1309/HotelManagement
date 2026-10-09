package model;

public class LeTan extends NhanVien {
    private double phuCap;

    public LeTan() {
        super();
        phuCap = 0;
    }

    public LeTan(String ma, String hoTen, int namSinh,
                 String gioiTinh, String soDienThoai, String diaChi,
                 double luongCoBan, String ngayVaoLam, double phuCap) {
        super(ma, hoTen, namSinh, gioiTinh, soDienThoai, diaChi,
              luongCoBan, ngayVaoLam);
        this.phuCap = phuCap;
    }

    @Override
    public double tinhLuong() {
        return getLuongCoBan() + phuCap;
    }

    @Override
    public String getVaiTro() {
        return "LETAN";
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
    }
}
