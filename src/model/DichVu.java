package model;

public class DichVu {
    private String maDichVu;
    private String tenDichVu;
    private double donGia;
    private String donViTinh;

    public DichVu() {
        maDichVu = "";
        tenDichVu = "";
        donGia = 0;
        donViTinh = "";
    }

    public DichVu(String maDichVu, String tenDichVu,
                  double donGia, String donViTinh) {
        this.maDichVu = maDichVu;
        this.tenDichVu = tenDichVu;
        this.donGia = donGia;
        this.donViTinh = donViTinh;
    }

    public void hienThiThongTin() {
        System.out.println("Ma dich vu: " + maDichVu);
        System.out.println("Ten dich vu: " + tenDichVu);
        System.out.println("Don gia: " + donGia);
        System.out.println("Don vi tinh: " + donViTinh);
    }

    public String getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(String maDichVu) {
        this.maDichVu = maDichVu;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }
}
