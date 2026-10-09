package model;

import interfaces.IFileIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DanhSachPhong implements IFileIO {
    private Phong[] ds;
    private int soLuong;

    public DanhSachPhong() {
        ds = new Phong[10];
        soLuong = 0;
    }

    public boolean them(Phong phong) {
        if (phong == null || tim(phong.getMaPhong()) != null) {
            return false;
        }
        if (soLuong == ds.length) {
            Phong[] mangMoi = new Phong[ds.length * 2];
            // Chep ds vao mangMoi: mang nguon, vi tri nguon, dich, vi tri dich, so phan tu.
            System.arraycopy(ds, 0, mangMoi, 0, soLuong);
            ds = mangMoi;
        }
        ds[soLuong] = phong;
        soLuong++;
        return true;
    }

    public boolean xoa(String maPhong) {
        int viTri = timViTri(maPhong);
        if (viTri < 0) {
            return false;
        }
        // Don phan tu sau viTri sang trai: nguon, vi tri nguon, dich, vi tri dich, so phan tu.
        System.arraycopy(ds, viTri + 1, ds, viTri, soLuong - viTri - 1);
        ds[soLuong - 1] = null;
        soLuong--;
        return true;
    }

    public Phong tim(String maPhong) {
        for (int i = 0; i < soLuong; i++) {
            if (ds[i].getMaPhong().equalsIgnoreCase(maPhong)) {
                return ds[i];
            }
        }
        return null;
    }

    public boolean sua(String maPhong, Phong phongMoi) {
        int viTri = timViTri(maPhong);
        if (viTri < 0 || phongMoi == null) {
            return false;
        }
        ds[viTri] = phongMoi;
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

    public Phong get(int viTri) {
        if (viTri < 0 || viTri >= soLuong) {
            return null;
        }
        return ds[viTri];
    }

    private int timViTri(String maPhong) {
        for (int i = 0; i < soLuong; i++) {
            if (ds[i].getMaPhong().equalsIgnoreCase(maPhong)) {
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
                if (p.length < 6) {
                    continue;
                }
                Phong phong = taoPhong(p);
                if (phong != null) {
                    them(phong);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Khong doc duoc file phong: " + path);
        }
    }

    private Phong taoPhong(String[] p) {
        try {
            String loai = p[0];
            String ma = p[1];
            String ten = p[2];
            int tang = Integer.parseInt(p[3]);
            String trangThai = p[4];
            double gia = Double.parseDouble(p[5]);
            if (loai.equals("DON")) {
                return new PhongDon(ma, ten, tang, trangThai, gia,
                        Double.parseDouble(p[6]));
            }
            if (loai.equals("DOI")) {
                return new PhongDoi(ma, ten, tang, trangThai, gia,
                        Integer.parseInt(p[6]));
            }
            return new PhongVIP(ma, ten, tang, trangThai, gia,
                    Double.parseDouble(p[6]));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void ghiFile(String path) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(path));
            for (int i = 0; i < soLuong; i++) {
                Phong p = ds[i];
                if (p instanceof PhongDon) {
                    pw.println("DON|" + p.getMaPhong() + "|" + p.getTenPhong()
                            + "|" + p.getTang() + "|" + p.getTrangThai() + "|"
                            + p.getGiaCoBan() + "|" + ((PhongDon) p).getPhuThu());
                } else if (p instanceof PhongDoi) {
                    pw.println("DOI|" + p.getMaPhong() + "|" + p.getTenPhong()
                            + "|" + p.getTang() + "|" + p.getTrangThai() + "|"
                            + p.getGiaCoBan() + "|" + ((PhongDoi) p).getSoGiuong());
                } else if (p instanceof PhongVIP) {
                    pw.println("VIP|" + p.getMaPhong() + "|" + p.getTenPhong()
                            + "|" + p.getTang() + "|" + p.getTrangThai() + "|"
                            + p.getGiaCoBan() + "|" + ((PhongVIP) p).getPhiDichVu());
                }
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Khong ghi duoc file phong: " + path);
        }
    }
}
