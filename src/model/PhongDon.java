package model;

public class PhongDon extends Phong {
    private double phuThu;

    public PhongDon() {
        super();
        phuThu = 0;
    }

    public PhongDon(String maPhong, String tenPhong, int tang,
                    String trangThai, double giaCoBan, double phuThu) {
        super(maPhong, tenPhong, tang, trangThai, giaCoBan);
        this.phuThu = phuThu;
    }

    @Override
    public double tinhGia(int soDem) {
        return (getGiaCoBan() + phuThu) * soDem;
    }

    public double getPhuThu() {
        return phuThu;
    }

    public void setPhuThu(double phuThu) {
        this.phuThu = phuThu;
    }
}
