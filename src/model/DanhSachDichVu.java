package model;

import interfaces.IFileIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DanhSachDichVu implements IFileIO {
    private ArrayList<DichVu> ds;

    public DanhSachDichVu() {
        ds = new ArrayList<DichVu>();
    }

    public boolean them(DichVu dichVu) {
        if (dichVu == null || tim(dichVu.getMaDichVu()) != null) {
            return false;
        }
        ds.add(dichVu);
        return true;
    }

    public boolean xoa(String ma) {
        DichVu dichVu = tim(ma);
        if (dichVu == null) {
            return false;
        }
        ds.remove(dichVu);
        return true;
    }

    public DichVu tim(String ma) {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getMaDichVu().equalsIgnoreCase(ma)) {
                return ds.get(i);
            }
        }
        return null;
    }

    public boolean sua(String ma, DichVu dichVuMoi) {
        int viTri = timViTri(ma);
        if (viTri < 0 || dichVuMoi == null) {
            return false;
        }
        ds.set(viTri, dichVuMoi);
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

    public DichVu get(int viTri) {
        if (viTri < 0 || viTri >= ds.size()) {
            return null;
        }
        return ds.get(viTri);
    }

    private int timViTri(String ma) {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getMaDichVu().equalsIgnoreCase(ma)) {
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
                DichVu dichVu = taoDichVu(p);
                if (dichVu != null) {
                    them(dichVu);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Khong doc duoc file dich vu: " + path);
        }
    }

    private DichVu taoDichVu(String[] p) {
        try {
            if (p.length < 5) {
                return null;
            }
            double gia = Double.parseDouble(p[3]);
            if (p[0].equals("AN")) {
                return new DichVuAn(p[1], p[2], gia, p[4], p[5]);
            }
            if (p[0].equals("GIATLA")) {
                return new DichVuGiatLa(p[1], p[2], gia, p[4],
                        Integer.parseInt(p[5]));
            }
            return new DichVuSpa(p[1], p[2], gia, p[4],
                    Integer.parseInt(p[5]));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void ghiFile(String path) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(path));
            for (int i = 0; i < ds.size(); i++) {
                DichVu d = ds.get(i);
                String loai = "SPA";
                String thongTin = "";
                if (d instanceof DichVuAn) {
                    loai = "AN";
                    thongTin = ((DichVuAn) d).getLoaiMon();
                } else if (d instanceof DichVuGiatLa) {
                    loai = "GIATLA";
                    thongTin = "" + ((DichVuGiatLa) d).getSoKgToiDa();
                } else if (d instanceof DichVuSpa) {
                    thongTin = "" + ((DichVuSpa) d).getThoiLuong();
                }
                pw.println(loai + "|" + d.getMaDichVu() + "|" + d.getTenDichVu()
                        + "|" + d.getDonGia() + "|" + d.getDonViTinh() + "|"
                        + thongTin);
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Khong ghi duoc file dich vu: " + path);
        }
    }
}
