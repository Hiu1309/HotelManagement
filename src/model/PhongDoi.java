package model;

public class PhongDoi extends Phong {
    private int soGiuong;

    public PhongDoi() {
        super();
        soGiuong = 2;
    }

    public PhongDoi(String maPhong, String tenPhong, int tang,
                    String trangThai, double giaCoBan, int soGiuong) {
        super(maPhong, tenPhong, tang, trangThai, giaCoBan);
        this.soGiuong = soGiuong;
    }

    @Override
    public double tinhGia(int soDem) {
        return getGiaCoBan() * soDem + soGiuong * 50000;
    }

    public int getSoGiuong() {
        return soGiuong;
    }

    public void setSoGiuong(int soGiuong) {
        this.soGiuong = soGiuong;
    }
}
