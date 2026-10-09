package model;

import interfaces.IFileIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DanhSachNhanVien implements IFileIO {
    private NhanVien[] ds;
    private int soLuong;

    public DanhSachNhanVien() {
        ds = new NhanVien[10];
        soLuong = 0;
    }

    public boolean them(NhanVien nhanVien) {
        if (nhanVien == null || tim(nhanVien.getMa()) != null) {
            return false;
        }
        if (soLuong == ds.length) {
            NhanVien[] mangMoi = new NhanVien[ds.length * 2];
            // Chep mang nhan vien sang mang moi: nguon, vi tri, dich, vi tri, so phan tu.
            System.arraycopy(ds, 0, mangMoi, 0, soLuong);
            ds = mangMoi;
        }
        ds[soLuong] = nhanVien;
        soLuong++;
        return true;
    }

    public boolean xoa(String ma) {
        int viTri = timViTri(ma);
        if (viTri < 0) {
            return false;
        }
        // Don cac phan tu sau viTri sang trai: nguon, vi tri, dich, vi tri, so phan tu.
        System.arraycopy(ds, viTri + 1, ds, viTri, soLuong - viTri - 1);
        ds[soLuong - 1] = null;
        soLuong--;
        return true;
    }

    public NhanVien tim(String ma) {
        for (int i = 0; i < soLuong; i++) {
            if (ds[i].getMa().equalsIgnoreCase(ma)) {
                return ds[i];
            }
        }
        return null;
    }

    public boolean sua(String ma, NhanVien nhanVienMoi) {
        int viTri = timViTri(ma);
        if (viTri < 0 || nhanVienMoi == null) {
            return false;
        }
        ds[viTri] = nhanVienMoi;
        return true;
    }

    public void hienThiDanhSach() {
        for (int i = 0; i < soLuong; i++) {
            ds[i].hienThiThongTin();
            System.out.println("--------------------");
        }
    }

    public int getSoLuong() {
        return soLuong;
    }

    public NhanVien get(int viTri) {
        if (viTri < 0 || viTri >= soLuong) {
            return null;
        }
        return ds[viTri];
    }

    private int timViTri(String ma) {
        for (int i = 0; i < soLuong; i++) {
            if (ds[i].getMa().equalsIgnoreCase(ma)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void docFile(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String dong;
            while ((dong = br.readLine()) != null) {
                String[] p = dong.split("\\|");
                NhanVien nhanVien = taoNhanVien(p);
                if (nhanVien != null) {
                    them(nhanVien);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Khong doc duoc file nhan vien: " + path);
        }
    }

    private NhanVien taoNhanVien(String[] p) {
        try {
            if (p.length < 10) {
                return null;
            }
            String loai = p[0];
            String ma = p[1];
            String ten = p[2];
            int nam = Integer.parseInt(p[3]);
            String gt = p[4];
            String dienThoai = p[5];
            String diaChi = p[6];
            double luong = Double.parseDouble(p[7]);
            String ngay = p[8];
            if (loai.equals("LETAN")) {
                return new LeTan(ma, ten, nam, gt, dienThoai, diaChi,
                        luong, ngay, Double.parseDouble(p[9]));
            }
            if (loai.equals("KETOAN")) {
                return new KeToan(ma, ten, nam, gt, dienThoai, diaChi,
                        luong, ngay, Double.parseDouble(p[9]));
            }
            if (loai.equals("BUONGPHONG")) {
                return new BuongPhong(ma, ten, nam, gt, dienThoai, diaChi,
                        luong, ngay, Integer.parseInt(p[9]));
            }
            return new QuanLy(ma, ten, nam, gt, dienThoai, diaChi,
                    luong, ngay, Double.parseDouble(p[9]));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void ghiFile(String path) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(path));
            for (int i = 0; i < soLuong; i++) {
                NhanVien n = ds[i];
                String loai = n.getVaiTro();
                String dong = loai + "|" + n.getMa() + "|" + n.getHoTen()
                        + "|" + n.getNamSinh() + "|" + n.getGioiTinh() + "|"
                        + n.getSoDienThoai() + "|" + n.getDiaChi() + "|"
                        + n.getLuongCoBan() + "|" + n.getNgayVaoLam() + "|";
                if (n instanceof LeTan) {
                    dong += ((LeTan) n).getPhuCap();
                } else if (n instanceof KeToan) {
                    dong += ((KeToan) n).getThuong();
                } else if (n instanceof BuongPhong) {
                    dong += ((BuongPhong) n).getSoPhongPhuTrach();
                } else {
                    dong += ((QuanLy) n).getHeSoChucVu();
                }
                pw.println(dong);
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Khong ghi duoc file nhan vien: " + path);
        }
    }
}
