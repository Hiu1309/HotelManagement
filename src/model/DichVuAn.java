package model;

public class DichVuAn extends DichVu {
    private String loaiMon;

    public DichVuAn() {
        super();
        loaiMon = "";
    }

    public DichVuAn(String maDichVu, String tenDichVu,
                    double donGia, String donViTinh, String loaiMon) {
        super(maDichVu, tenDichVu, donGia, donViTinh);
        this.loaiMon = loaiMon;
    }

    public String getLoaiMon() {
        return loaiMon;
    }

    public void setLoaiMon(String loaiMon) {
        this.loaiMon = loaiMon;
    }
}
