package model;

import interfaces.IXacThuc;

public class TaiKhoan implements IXacThuc {
    private String tenDangNhap;
    private String matKhau;
    private String vaiTro;
    private String maNguoiDung;
    private boolean dangHoatDong;

    public static TaiKhoan taiKhoanHienTai;

    public TaiKhoan() {
        tenDangNhap = "";
        matKhau = "";
        vaiTro = "KHACH";
        maNguoiDung = "";
        dangHoatDong = true;
    }

    public TaiKhoan(String tenDangNhap, String matKhau,
                    String vaiTro, String maNguoiDung,
                    boolean dangHoatDong) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = maHoaMatKhau(matKhau);
        this.vaiTro = vaiTro;
        this.maNguoiDung = maNguoiDung;
        this.dangHoatDong = dangHoatDong;
    }

    @Override
    public boolean dangNhap(String user, String pass) {
        String matKhauMaHoa = maHoaMatKhau(pass);
        boolean dungTaiKhoan = tenDangNhap.equals(user);
        boolean dungMatKhau = matKhau.equals(matKhauMaHoa);

        if (dungTaiKhoan && dungMatKhau && dangHoatDong) {
            taiKhoanHienTai = this;
            return true;
        }
        return false;
    }

    // Ma hoa mat khau don gian de luu vao file
    public static String maHoaMatKhau(String matKhau) {
        String ketQua = "";
        for (int i = 0; i < matKhau.length(); i++) {
            ketQua = ketQua + (char) (matKhau.charAt(i) + 3);
        }
        return ketQua;
    }

    public static void dangXuat() {
        taiKhoanHienTai = null;
    }

    public void hienThiThongTin() {
        System.out.println("Ten dang nhap: " + tenDangNhap);
        System.out.println("Vai tro: " + vaiTro);
        System.out.println("Ma nguoi dung: " + maNguoiDung);
        System.out.println("Dang hoat dong: " + dangHoatDong);
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = maHoaMatKhau(matKhau);
    }

    public void setMatKhauDaMaHoa(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public boolean isDangHoatDong() {
        return dangHoatDong;
    }

    public void setDangHoatDong(boolean dangHoatDong) {
        this.dangHoatDong = dangHoatDong;
    }
}
