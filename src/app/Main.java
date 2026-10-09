package app;

import java.io.File;
import model.BuongPhong;
import model.DanhSachDatPhong;
import model.DanhSachDichVu;
import model.DanhSachHoaDon;
import model.DanhSachKhachHang;
import model.DanhSachNhanVien;
import model.DanhSachPhong;
import model.DanhSachTaiKhoan;
import model.DatPhong;
import model.HoaDon;
import model.KhachHang;
import model.LeTan;
import model.NhanVien;
import model.KeToan;
import model.Phong;
import model.PhongDon;
import model.PhongDoi;
import model.PhongVIP;
import model.QuanLy;
import model.TaiKhoan;

public class Main {
    private static final String DUONG_DAN_DATA = "data\\";
    private static final String FILE_PHONG = DUONG_DAN_DATA + "phong.txt";
    private static final String FILE_KHACH = DUONG_DAN_DATA + "khachhang.txt";
    private static final String FILE_NHAN_VIEN = DUONG_DAN_DATA + "nhanvien.txt";
    private static final String FILE_TAI_KHOAN = DUONG_DAN_DATA + "taikhoan.txt";
    private static final String FILE_DAT_PHONG = DUONG_DAN_DATA + "datphong.txt";
    private static final String FILE_HOA_DON = DUONG_DAN_DATA + "hoadon.txt";
    private static final String FILE_DICH_VU = DUONG_DAN_DATA + "dichvu.txt";

    private DanhSachPhong dsPhong = new DanhSachPhong();
    private DanhSachKhachHang dsKhach = new DanhSachKhachHang();
    private DanhSachNhanVien dsNhanVien = new DanhSachNhanVien();
    private DanhSachTaiKhoan dsTaiKhoan = new DanhSachTaiKhoan();
    private DanhSachDatPhong dsDatPhong = new DanhSachDatPhong();
    private DanhSachHoaDon dsHoaDon = new DanhSachHoaDon();
    private DanhSachDichVu dsDichVu = new DanhSachDichVu();

    public static void main(String[] args) {
        Main chuongTrinh = new Main();
        chuongTrinh.chay();
    }

    private void chay() {
        docDuLieu();
        taoDuLieuMau();
        boolean tiepTuc = true;
        while (tiepTuc) {
            inMenuChinh();
            int chon = Utils.nhapSo("Chon: ");
            if (chon == 1) {
                dangNhap();
            } else if (chon == 2) {
                dangKy();
            } else if (chon == 0) {
                ghiDuLieu();
                tiepTuc = false;
            } else {
                System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private void inMenuChinh() {
        System.out.println("\n===== QUAN LY KHACH SAN =====");
        System.out.println("1. Dang nhap");
        System.out.println("2. Dang ky tai khoan khach");
        System.out.println("0. Thoat");
    }

    private void docDuLieu() {
        docNeuCo(dsPhong, FILE_PHONG);
        docNeuCo(dsKhach, FILE_KHACH);
        docNeuCo(dsNhanVien, FILE_NHAN_VIEN);
        docNeuCo(dsTaiKhoan, FILE_TAI_KHOAN);
        docNeuCo(dsDatPhong, FILE_DAT_PHONG);
        docNeuCo(dsHoaDon, FILE_HOA_DON);
        docNeuCo(dsDichVu, FILE_DICH_VU);
    }

    private void docNeuCo(interfaces.IFileIO danhSach, String path) {
        if (new File(path).exists()) {
            danhSach.docFile(path);
        }
    }

    private void ghiDuLieu() {
        dsPhong.ghiFile(FILE_PHONG);
        dsKhach.ghiFile(FILE_KHACH);
        dsNhanVien.ghiFile(FILE_NHAN_VIEN);
        dsTaiKhoan.ghiFile(FILE_TAI_KHOAN);
        dsDatPhong.ghiFile(FILE_DAT_PHONG);
        dsHoaDon.ghiFile(FILE_HOA_DON);
        dsDichVu.ghiFile(FILE_DICH_VU);
    }

    private void taoDuLieuMau() {
        if (dsPhong.getSoLuong() == 0) {
            dsPhong.them(new PhongDon("P101", "Phong don 101", 1,
                    "Trong", 300000, 0));
            dsPhong.them(new PhongDoi("P201", "Phong doi 201", 2,
                    "Trong", 500000, 2));
            dsPhong.them(new PhongVIP("P301", "Phong VIP 301", 3,
                    "Trong", 1000000, 200000));
        }
        if (dsNhanVien.getSoLuong() == 0) {
            dsNhanVien.them(new LeTan("NV01", "Nguyen Le Tan", 1998,
                    "Nam", "0900000001", "Ha Noi", 7000000, "01/01/2022", 500000));
            dsNhanVien.them(new KeToan("NV02", "Tran Ke Toan", 1995,
                    "Nu", "0900000002", "Ha Noi", 8000000, "01/02/2021", 1000000));
            dsNhanVien.them(new BuongPhong("NV03", "Le Buong Phong", 1997,
                    "Nu", "0900000003", "Da Nang", 6500000, "01/03/2022", 10));
            dsNhanVien.them(new QuanLy("NV04", "Pham Quan Ly", 1990,
                    "Nam", "0900000004", "Hai Phong", 12000000, "01/01/2020", 1.5));
        }
        if (dsTaiKhoan.getSoLuong() == 0) {
            dsTaiKhoan.them(new TaiKhoan("admin", "admin123", "QUANLY", "NV04", true));
            dsTaiKhoan.them(new TaiKhoan("letan", "123456", "LETAN", "NV01", true));
            dsTaiKhoan.them(new TaiKhoan("ketoan", "123456", "KETOAN", "NV02", true));
            dsTaiKhoan.ghiFile(FILE_TAI_KHOAN);
        }
    }

    private void dangNhap() {
        String user = Utils.nhapChuoi("Ten dang nhap: ");
        String pass = Utils.nhapChuoi("Mat khau: ");
        TaiKhoan taiKhoan = dsTaiKhoan.dangNhap(user, pass);
        if (taiKhoan == null) {
            System.out.println("Sai tai khoan, mat khau hoac tai khoan bi khoa.");
            return;
        }
        System.out.println("Dang nhap thanh cong voi vai tro " + taiKhoan.getVaiTro());
        hienThiMenuVaiTro(taiKhoan);
    }

    private void dangKy() {
        String user = Utils.nhapChuoi("Ten dang nhap moi: ");
        if (dsTaiKhoan.tim(user) != null) {
            System.out.println("Ten dang nhap da ton tai.");
            return;
        }
        String pass = Utils.nhapChuoi("Mat khau: ");
        KhachHang khach = nhapKhachHang("KH" + (dsKhach.getSoLuong() + 1));
        String maKhach = khach.getMa();
        dsKhach.them(khach);
        dsTaiKhoan.them(new TaiKhoan(user, pass, "KHACH", maKhach, true));
        ghiDuLieu();
        System.out.println("Dang ky thanh cong. Ma khach cua ban: " + maKhach);
    }

    private void hienThiMenuVaiTro(TaiKhoan taiKhoan) {
        if (taiKhoan.getVaiTro().equals("KHACH")) {
            menuKhach(taiKhoan);
        } else if (taiKhoan.getVaiTro().equals("LETAN")) {
            menuLeTan();
        } else if (taiKhoan.getVaiTro().equals("KETOAN")) {
            menuKeToan();
        } else if (taiKhoan.getVaiTro().equals("BUONGPHONG")) {
            menuBuongPhong();
        } else if (taiKhoan.getVaiTro().equals("QUANLY")) {
            menuQuanLy();
        }
        TaiKhoan.dangXuat();
    }

    private void menuKhach(TaiKhoan taiKhoan) {
        boolean tiepTuc = true;
        while (tiepTuc) {
            System.out.println("\n===== MENU KHACH =====");
            System.out.println("1. Xem phong trong");
            System.out.println("2. Dat phong");
            System.out.println("3. Xem hoa don cua toi");
            System.out.println("4. Xem thong tin ca nhan");
            System.out.println("0. Dang xuat");
            int chon = Utils.nhapSo("Chon: ");
            if (chon == 1) xemPhongTrong();
            else if (chon == 2) datPhong(taiKhoan.getMaNguoiDung());
            else if (chon == 3) xemHoaDonCuaKhach(taiKhoan.getMaNguoiDung());
            else if (chon == 4) xemKhach(taiKhoan.getMaNguoiDung());
            else if (chon == 0) tiepTuc = false;
            else System.out.println("Lua chon khong hop le.");
        }
    }

    private void menuLeTan() {
        boolean tiepTuc = true;
        while (tiepTuc) {
            System.out.println("\n===== MENU LE TAN =====");
            System.out.println("1. Dat phong giup khach");
            System.out.println("2. Xem danh sach khach");
            System.out.println("3. Check-in / Check-out");
            System.out.println("4. Xem dat phong");
            System.out.println("0. Dang xuat");
            int chon = Utils.nhapSo("Chon: ");
            if (chon == 1) {
                dsKhach.hienThiDanhSach();
                datPhong(Utils.nhapChuoi("Nhap ma khach: "));
            } else if (chon == 2) dsKhach.hienThiDanhSach();
            else if (chon == 3) capNhatTrangThaiDatPhong();
            else if (chon == 4) dsDatPhong.hienThiDanhSach();
            else if (chon == 0) tiepTuc = false;
            else System.out.println("Lua chon khong hop le.");
        }
    }

    private void menuKeToan() {
        boolean tiepTuc = true;
        while (tiepTuc) {
            System.out.println("\n===== MENU KE TOAN =====");
            System.out.println("1. Lap hoa don");
            System.out.println("2. Thanh toan hoa don");
            System.out.println("3. Xem hoa don");
            System.out.println("4. Xem doanh thu");
            System.out.println("0. Dang xuat");
            int chon = Utils.nhapSo("Chon: ");
            if (chon == 1) lapHoaDon();
            else if (chon == 2) thanhToanHoaDon();
            else if (chon == 3) dsHoaDon.hienThiDanhSach();
            else if (chon == 4) System.out.println("Doanh thu: "
                    + Utils.dinhDangTien(dsHoaDon.tinhDoanhThu()));
            else if (chon == 0) tiepTuc = false;
            else System.out.println("Lua chon khong hop le.");
        }
    }

    private void menuBuongPhong() {
        boolean tiepTuc = true;
        while (tiepTuc) {
            System.out.println("\n===== MENU BUONG PHONG =====");
            System.out.println("1. Xem danh sach phong");
            System.out.println("2. Cap nhat trang thai phong");
            System.out.println("0. Dang xuat");
            int chon = Utils.nhapSo("Chon: ");
            if (chon == 1) dsPhong.hienThiDanhSach();
            else if (chon == 2) capNhatTrangThaiPhong();
            else if (chon == 0) tiepTuc = false;
            else System.out.println("Lua chon khong hop le.");
        }
    }

    private void menuQuanLy() {
        boolean tiepTuc = true;
        while (tiepTuc) {
            System.out.println("\n===== MENU QUAN LY =====");
            System.out.println("1. Quan ly phong");
            System.out.println("2. Quan ly nhan vien");
            System.out.println("3. Quan ly khach hang");
            System.out.println("4. Quan ly tai khoan");
            System.out.println("5. Xem hoa don va doanh thu");
            System.out.println("6. Xem luong nhan vien");
            System.out.println("0. Dang xuat");
            int chon = Utils.nhapSo("Chon: ");
            if (chon == 1) quanLyPhong();
            else if (chon == 2) quanLyNhanVien();
            else if (chon == 3) quanLyKhach();
            else if (chon == 4) dsTaiKhoan.hienThiDanhSach();
            else if (chon == 5) {
                dsHoaDon.hienThiDanhSach();
                System.out.println("Doanh thu: " + Utils.dinhDangTien(dsHoaDon.tinhDoanhThu()));
            } else if (chon == 6) hienThiLuong();
            else if (chon == 0) tiepTuc = false;
            else System.out.println("Lua chon khong hop le.");
        }
    }

    private void xemPhongTrong() {
        for (int i = 0; i < dsPhong.getSoLuong(); i++) {
            Phong phong = dsPhong.get(i);
            if (phong.getTrangThai().equalsIgnoreCase("Trong")) {
                phong.hienThiThongTin();
                System.out.println("--------------------");
            }
        }
    }

    private void datPhong(String maKhach) {
        if (dsKhach.tim(maKhach) == null) {
            System.out.println("Khong tim thay khach hang.");
            return;
        }
        xemPhongTrong();
        String maPhong = Utils.nhapChuoi("Nhap ma phong: ");
        Phong phong = dsPhong.tim(maPhong);
        if (phong == null || !phong.getTrangThai().equalsIgnoreCase("Trong")) {
            System.out.println("Phong khong ton tai hoac khong trong.");
            return;
        }
        String nhan = Utils.nhapChuoi("Ngay nhan (yyyyMMdd): ");
        String tra = Utils.nhapChuoi("Ngay tra (yyyyMMdd): ");
        int soDem = Utils.nhapSo("So dem: ");
        if (!hopLeKhoangNgay(nhan, tra) || !Utils.laSoDuong(soDem)) {
            System.out.println("Khoang ngay hoac so dem khong hop le.");
            return;
        }
        if (biTrungPhong(maPhong, nhan, tra)) {
            System.out.println("Phong da bi dat trong khoang ngay nay.");
            return;
        }
        String maDat = "DP" + (dsDatPhong.getSoLuong() + 1);
        dsDatPhong.them(new DatPhong(maDat, maKhach, maPhong, nhan, tra,
                soDem, "Cho xac nhan"));
        phong.setTrangThai("Da dat");
        ghiDuLieu();
        System.out.println("Dat phong thanh cong: " + maDat);
    }

    private boolean hopLeKhoangNgay(String nhan, String tra) {
        return nhan.matches("\\d{8}") && tra.matches("\\d{8}")
                && ngayToSo(nhan) < ngayToSo(tra);
    }

    private int ngayToSo(String ngay) {
        try {
            return Integer.parseInt(ngay);
        } catch (Exception e) {
            return 0;
        }
    }

    private boolean biTrungPhong(String maPhong, String nhan, String tra) {
        for (int i = 0; i < dsDatPhong.getSoLuong(); i++) {
            DatPhong d = dsDatPhong.get(i);
            boolean cungPhong = d.getMaPhong().equalsIgnoreCase(maPhong);
            boolean dangDat = !d.getTrangThai().equalsIgnoreCase("Da huy");
            boolean giaoNhau = ngayToSo(nhan) < ngayToSo(d.getNgayTra())
                    && ngayToSo(tra) > ngayToSo(d.getNgayNhan());
            if (cungPhong && dangDat && giaoNhau) return true;
        }
        return false;
    }

    private void xemHoaDonCuaKhach(String maKhach) {
        for (int i = 0; i < dsHoaDon.getSoLuong(); i++) {
            HoaDon hoaDon = dsHoaDon.get(i);
            if (hoaDon.getMaKhach().equalsIgnoreCase(maKhach)) {
                hoaDon.hienThiThongTin();
            }
        }
    }

    private void xemKhach(String maKhach) {
        KhachHang khach = dsKhach.tim(maKhach);
        if (khach == null) System.out.println("Khong tim thay thong tin.");
        else khach.hienThiThongTin();
    }

    private KhachHang nhapKhachHang(String ma) {
        String ten = Utils.nhapChuoi("Ho ten: ");
        int nam = Utils.nhapSo("Nam sinh: ");
        String gioiTinh = Utils.nhapChuoi("Gioi tinh: ");
        String dienThoai = Utils.nhapChuoi("So dien thoai: ");
        String diaChi = Utils.nhapChuoi("Dia chi: ");
        return new KhachHang(ma, ten, nam, gioiTinh, dienThoai, diaChi, 0, "Thuong");
    }

    private void capNhatTrangThaiDatPhong() {
        String ma = Utils.nhapChuoi("Ma dat phong: ");
        DatPhong datPhong = dsDatPhong.tim(ma);
        if (datPhong == null) {
            System.out.println("Khong tim thay dat phong.");
            return;
        }
        String trangThai = Utils.nhapChuoi("Trang thai moi: ");
        datPhong.setTrangThai(trangThai);
        ghiDuLieu();
    }

    private void capNhatTrangThaiPhong() {
        String ma = Utils.nhapChuoi("Ma phong: ");
        Phong phong = dsPhong.tim(ma);
        if (phong == null) {
            System.out.println("Khong tim thay phong.");
            return;
        }
        phong.setTrangThai(Utils.nhapChuoi("Trang thai moi: "));
        ghiDuLieu();
    }

    private void lapHoaDon() {
        String maDat = Utils.nhapChuoi("Ma dat phong: ");
        DatPhong datPhong = dsDatPhong.tim(maDat);
        if (datPhong == null) {
            System.out.println("Khong tim thay dat phong.");
            return;
        }
        Phong phong = dsPhong.tim(datPhong.getMaPhong());
        KhachHang khach = dsKhach.tim(datPhong.getMaKhach());
        double tienPhong = phong.tinhGia(datPhong.getSoDem());
        double giamGia = tinhGiamGia(khach, tienPhong);
        String maHoaDon = "HD" + (dsHoaDon.getSoLuong() + 1);
        dsHoaDon.them(new HoaDon(maHoaDon, maDat, datPhong.getMaKhach(),
                datPhong.getNgayTra(), tienPhong, 0, giamGia, "Chua thanh toan"));
        ghiDuLieu();
        System.out.println("Lap hoa don thanh cong: " + maHoaDon);
    }

    private double tinhGiamGia(KhachHang khach, double tienPhong) {
        if (khach == null) return 0;
        if (khach.getDiemTichLuy() >= 1000) return tienPhong * 0.1;
        if (khach.getDiemTichLuy() >= 500) return tienPhong * 0.05;
        return 0;
    }

    private void thanhToanHoaDon() {
        String ma = Utils.nhapChuoi("Ma hoa don: ");
        HoaDon hoaDon = dsHoaDon.tim(ma);
        if (hoaDon == null) {
            System.out.println("Khong tim thay hoa don.");
            return;
        }
        hoaDon.thanhToan();
        ghiDuLieu();
        System.out.println("Thanh toan thanh cong: " + Utils.dinhDangTien(hoaDon.tinhTien()));
    }

    private void quanLyPhong() {
        dsPhong.hienThiDanhSach();
        System.out.println("1. Them phong  2. Xoa phong  0. Quay lai");
        int chon = Utils.nhapSo("Chon: ");
        if (chon == 1) themPhong();
        else if (chon == 2) {
            dsPhong.xoa(Utils.nhapChuoi("Ma phong can xoa: "));
            ghiDuLieu();
        }
    }

    private void themPhong() {
        String loai = Utils.nhapChuoi("Loai phong DON/DOI/VIP: ").toUpperCase();
        String ma = Utils.nhapChuoi("Ma phong: ");
        String ten = Utils.nhapChuoi("Ten phong: ");
        int tang = Utils.nhapSo("Tang: ");
        double gia = Utils.nhapSoThuc("Gia co ban: ");
        Phong phong;
        if (loai.equals("DOI")) {
            phong = new PhongDoi(ma, ten, tang, "Trong", gia, 2);
        } else if (loai.equals("VIP")) {
            phong = new PhongVIP(ma, ten, tang, "Trong", gia, 200000);
        } else {
            phong = new PhongDon(ma, ten, tang, "Trong", gia, 0);
        }
        System.out.println(dsPhong.them(phong) ? "Them thanh cong." : "Ma phong da ton tai.");
        ghiDuLieu();
    }

    private void quanLyNhanVien() {
        dsNhanVien.hienThiDanhSach();
        String ma = Utils.nhapChuoi("Nhap ma nhan vien can xoa (bo trong de bo qua): ");
        if (!ma.equals("")) {
            dsNhanVien.xoa(ma);
            ghiDuLieu();
        }
    }

    private void quanLyKhach() {
        dsKhach.hienThiDanhSach();
        String ma = Utils.nhapChuoi("Nhap ma khach can xoa (bo trong de bo qua): ");
        if (!ma.equals("")) {
            dsKhach.xoa(ma);
            ghiDuLieu();
        }
    }

    private void hienThiLuong() {
        for (int i = 0; i < dsNhanVien.getSoLuong(); i++) {
            NhanVien nhanVien = dsNhanVien.get(i);
            System.out.println(nhanVien.getVaiTro() + " - "
                    + nhanVien.getHoTen() + ": "
                    + Utils.dinhDangTien(nhanVien.tinhLuong()));
        }
    }
}
