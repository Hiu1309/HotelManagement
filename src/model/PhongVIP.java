package model;

public class PhongVIP extends Phong {
    private double phiDichVu;

    public PhongVIP() {
        super();
        phiDichVu = 0;
    }

    public PhongVIP(String maPhong, String tenPhong, int tang,
                    String trangThai, double giaCoBan, double phiDichVu) {
        super(maPhong, tenPhong, tang, trangThai, giaCoBan);
        this.phiDichVu = phiDichVu;
    }

    @Override
    public double tinhGia(int soDem) {
        return (getGiaCoBan() + phiDichVu) * soDem * 1.2;
    }

    public double getPhiDichVu() {
        return phiDichVu;
    }

    public void setPhiDichVu(double phiDichVu) {
        this.phiDichVu = phiDichVu;
    }
}
