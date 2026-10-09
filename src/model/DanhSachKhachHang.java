package model;

import interfaces.IFileIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DanhSachKhachHang implements IFileIO {
    private ArrayList<KhachHang> ds;

    public DanhSachKhachHang() {
        ds = new ArrayList<KhachHang>();
    }

    public boolean them(KhachHang khach) {
        if (khach == null || tim(khach.getMa()) != null) {
            return false;
        }
        ds.add(khach);
        return true;
    }

    public boolean xoa(String ma) {
        KhachHang khach = tim(ma);
        if (khach == null) {
            return false;
        }
        ds.remove(khach);
        return true;
    }

    public KhachHang tim(String ma) {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getMa().equalsIgnoreCase(ma)) {
                return ds.get(i);
            }
        }
        return null;
    }

    public boolean sua(String ma, KhachHang khachMoi) {
        int viTri = timViTri(ma);
        if (viTri < 0 || khachMoi == null) {
            return false;
        }
        ds.set(viTri, khachMoi);
        return true;
    }

    public void hienThiDanhSach() {
        for (int i = 0; i < ds.size(); i++) {
            ds.get(i).hienThiThongTin();
            System.out.println("--------------------");
        }
    }

    public int getSoLuong() {
        return ds.size();
    }

    public KhachHang get(int viTri) {
        if (viTri < 0 || viTri >= ds.size()) {
            return null;
        }
        return ds.get(viTri);
    }

    private int timViTri(String ma) {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getMa().equalsIgnoreCase(ma)) {
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
                if (p.length >= 8) {
                    try {
                        them(new KhachHang(p[0], p[1], Integer.parseInt(p[2]),
                                p[3], p[4], p[5], Integer.parseInt(p[6]), p[7]));
                    } catch (Exception e) {
                        System.out.println("Bo qua dong khach hang khong hop le.");
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Khong doc duoc file khach hang: " + path);
        }
    }

    @Override
    public void ghiFile(String path) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(path));
            for (int i = 0; i < ds.size(); i++) {
                KhachHang k = ds.get(i);
                pw.println(k.getMa() + "|" + k.getHoTen() + "|" + k.getNamSinh()
                        + "|" + k.getGioiTinh() + "|" + k.getSoDienThoai() + "|"
                        + k.getDiaChi() + "|" + k.getDiemTichLuy() + "|"
                        + k.getLoaiKhach());
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Khong ghi duoc file khach hang: " + path);
        }
    }
}
