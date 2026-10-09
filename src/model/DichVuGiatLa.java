package model;

public class DichVuGiatLa extends DichVu {
    private int soKgToiDa;

    public DichVuGiatLa() {
        super();
        soKgToiDa = 0;
    }

    public DichVuGiatLa(String maDichVu, String tenDichVu,
                        double donGia, String donViTinh, int soKgToiDa) {
        super(maDichVu, tenDichVu, donGia, donViTinh);
        this.soKgToiDa = soKgToiDa;
    }

    public int getSoKgToiDa() {
        return soKgToiDa;
    }

    public void setSoKgToiDa(int soKgToiDa) {
        this.soKgToiDa = soKgToiDa;
    }
}
