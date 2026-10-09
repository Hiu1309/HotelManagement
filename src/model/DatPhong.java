package model;

public class DatPhong {
    private String maDatPhong;
    private String maKhach;
    private String maPhong;
    private String ngayNhan;
    private String ngayTra;
    private int soDem;
    private String trangThai;

    public DatPhong() {
        maDatPhong = "";
        maKhach = "";
        maPhong = "";
        ngayNhan = "";
        ngayTra = "";
        soDem = 0;
        trangThai = "Cho xac nhan";
    }

    public DatPhong(String maDatPhong, String maKhach, String maPhong,
                    String ngayNhan, String ngayTra, int soDem,
                    String trangThai) {
        this.maDatPhong = maDatPhong;
        this.maKhach = maKhach;
        this.maPhong = maPhong;
        this.ngayNhan = ngayNhan;
        this.ngayTra = ngayTra;
        this.soDem = soDem;
        this.trangThai = trangThai;
    }

    public void hienThiThongTin() {
        System.out.println("Ma dat phong: " + maDatPhong);
        System.out.println("Ma khach: " + maKhach);
        System.out.println("Ma phong: " + maPhong);
        System.out.println("Ngay nhan: " + ngayNhan);
        System.out.println("Ngay tra: " + ngayTra);
        System.out.println("So dem: " + soDem);
        System.out.println("Trang thai: " + trangThai);
    }

    public String getMaDatPhong() {
        return maDatPhong;
    }

    public void setMaDatPhong(String maDatPhong) {
        this.maDatPhong = maDatPhong;
    }

    public String getMaKhach() {
        return maKhach;
    }

    public void setMaKhach(String maKhach) {
        this.maKhach = maKhach;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(String ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }

    public int getSoDem() {
        return soDem;
    }

    public void setSoDem(int soDem) {
        this.soDem = soDem;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
