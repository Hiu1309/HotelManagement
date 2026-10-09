package model;

import interfaces.IFileIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DanhSachDatPhong implements IFileIO {
    private DatPhong[] ds;
    private int soLuong;

    public DanhSachDatPhong() {
        ds = new DatPhong[10];
        soLuong = 0;
    }

    public boolean them(DatPhong datPhong) {
        if (datPhong == null || tim(datPhong.getMaDatPhong()) != null) {
            return false;
        }
        if (soLuong == ds.length) {
            DatPhong[] mangMoi = new DatPhong[ds.length * 2];
            // Chep mang dat phong sang mang moi: nguon, vi tri, dich, vi tri, so phan tu.
            System.arraycopy(ds, 0, mangMoi, 0, soLuong);
            ds = mangMoi;
        }
        ds[soLuong] = datPhong;
        soLuong++;
        return true;
    }

    public boolean xoa(String ma) {
        int viTri = timViTri(ma);
        if (viTri < 0) {
            return false;
        }
        // Don phan tu sau viTri sang trai: nguon, vi tri, dich, vi tri, so phan tu.
        System.arraycopy(ds, viTri + 1, ds, viTri, soLuong - viTri - 1);
        ds[soLuong - 1] = null;
        soLuong--;
        return true;
    }

    public DatPhong tim(String ma) {
        for (int i = 0; i < soLuong; i++) {
            if (ds[i].getMaDatPhong().equalsIgnoreCase(ma)) {
                return ds[i];
            }
        }
        return null;
    }

    public boolean sua(String ma, DatPhong datPhongMoi) {
        int viTri = timViTri(ma);
        if (viTri < 0 || datPhongMoi == null) {
            return false;
        }
        ds[viTri] = datPhongMoi;
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

    public DatPhong get(int viTri) {
        if (viTri < 0 || viTri >= soLuong) {
            return null;
        }
        return ds[viTri];
    }

    private int timViTri(String ma) {
        for (int i = 0; i < soLuong; i++) {
            if (ds[i].getMaDatPhong().equalsIgnoreCase(ma)) {
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
                if (p.length >= 7) {
                    try {
                        them(new DatPhong(p[0], p[1], p[2], p[3], p[4],
                                Integer.parseInt(p[5]), p[6]));
                    } catch (Exception e) {
                        System.out.println("Bo qua dong dat phong khong hop le.");
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Khong doc duoc file dat phong: " + path);
        }
    }

    @Override
    public void ghiFile(String path) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(path));
            for (int i = 0; i < soLuong; i++) {
                DatPhong d = ds[i];
                pw.println(d.getMaDatPhong() + "|" + d.getMaKhach() + "|"
                        + d.getMaPhong() + "|" + d.getNgayNhan() + "|"
                        + d.getNgayTra() + "|" + d.getSoDem() + "|"
                        + d.getTrangThai());
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Khong ghi duoc file dat phong: " + path);
        }
    }
}
