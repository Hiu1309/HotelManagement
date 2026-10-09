package model;

public class DichVuSpa extends DichVu {
    private int thoiLuong;

    public DichVuSpa() {
        super();
        thoiLuong = 0;
    }

    public DichVuSpa(String maDichVu, String tenDichVu,
                     double donGia, String donViTinh, int thoiLuong) {
        super(maDichVu, tenDichVu, donGia, donViTinh);
        this.thoiLuong = thoiLuong;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }
}
