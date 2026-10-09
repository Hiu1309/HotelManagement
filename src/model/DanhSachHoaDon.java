package model;

import interfaces.IFileIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DanhSachHoaDon implements IFileIO {
    private ArrayList<HoaDon> ds;

    public DanhSachHoaDon() {
        ds = new ArrayList<HoaDon>();
    }

    public boolean them(HoaDon hoaDon) {
        if (hoaDon == null || tim(hoaDon.getMaHoaDon()) != null) {
            return false;
        }
        ds.add(hoaDon);
        return true;
    }

    public boolean xoa(String ma) {
        HoaDon hoaDon = tim(ma);
        if (hoaDon == null) {
            return false;
        }
        ds.remove(hoaDon);
        return true;
    }

    public HoaDon tim(String ma) {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getMaHoaDon().equalsIgnoreCase(ma)) {
                return ds.get(i);
            }
        }
        return null;
    }

    public boolean sua(String ma, HoaDon hoaDonMoi) {
        int viTri = timViTri(ma);
        if (viTri < 0 || hoaDonMoi == null) {
            return false;
        }
        ds.set(viTri, hoaDonMoi);
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

    public HoaDon get(int viTri) {
        if (viTri < 0 || viTri >= ds.size()) {
            return null;
        }
        return ds.get(viTri);
    }

    public double tinhDoanhThu() {
        double doanhThu = 0;
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getTrangThai().equalsIgnoreCase("Da thanh toan")) {
                doanhThu += ds.get(i).tinhTien();
            }
        }
        return doanhThu;
    }

    private int timViTri(String ma) {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getMaHoaDon().equalsIgnoreCase(ma)) {
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
                if (p.length >= 9) {
                    try {
                        them(new HoaDon(p[0], p[1], p[2], p[3],
                                Double.parseDouble(p[4]), Double.parseDouble(p[5]),
                                Double.parseDouble(p[6]), p[8]));
                    } catch (Exception e) {
                        System.out.println("Bo qua dong hoa don khong hop le.");
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Khong doc duoc file hoa don: " + path);
        }
    }

    @Override
    public void ghiFile(String path) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(path));
            for (int i = 0; i < ds.size(); i++) {
                HoaDon h = ds.get(i);
                pw.println(h.getMaHoaDon() + "|" + h.getMaDatPhong() + "|"
                        + h.getMaKhach() + "|" + h.getNgayLap() + "|"
                        + h.getTienPhong() + "|" + h.getTienDichVu() + "|"
                        + h.getGiamGia() + "|" + h.getTongTien() + "|"
                        + h.getTrangThai());
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Khong ghi duoc file hoa don: " + path);
        }
    }
}
