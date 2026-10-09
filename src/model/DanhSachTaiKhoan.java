package model;

import interfaces.IFileIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DanhSachTaiKhoan implements IFileIO {
    private ArrayList<TaiKhoan> ds;

    public DanhSachTaiKhoan() {
        ds = new ArrayList<TaiKhoan>();
    }

    public boolean them(TaiKhoan taiKhoan) {
        if (taiKhoan == null || tim(taiKhoan.getTenDangNhap()) != null) {
            return false;
        }
        ds.add(taiKhoan);
        return true;
    }

    public boolean xoa(String tenDangNhap) {
        TaiKhoan taiKhoan = tim(tenDangNhap);
        if (taiKhoan == null) {
            return false;
        }
        ds.remove(taiKhoan);
        return true;
    }

    public TaiKhoan tim(String tenDangNhap) {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getTenDangNhap().equalsIgnoreCase(tenDangNhap)) {
                return ds.get(i);
            }
        }
        return null;
    }

    public boolean sua(String tenDangNhap, TaiKhoan taiKhoanMoi) {
        int viTri = timViTri(tenDangNhap);
        if (viTri < 0 || taiKhoanMoi == null) {
            return false;
        }
        ds.set(viTri, taiKhoanMoi);
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

    public TaiKhoan get(int viTri) {
        if (viTri < 0 || viTri >= ds.size()) {
            return null;
        }
        return ds.get(viTri);
    }

    public TaiKhoan dangNhap(String user, String pass) {
        TaiKhoan taiKhoan = tim(user);
        if (taiKhoan != null && taiKhoan.dangNhap(user, pass)) {
            return taiKhoan;
        }
        return null;
    }

    private int timViTri(String tenDangNhap) {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getTenDangNhap().equalsIgnoreCase(tenDangNhap)) {
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
                if (p.length >= 5) {
                    TaiKhoan taiKhoan = new TaiKhoan();
                    taiKhoan.setTenDangNhap(p[0]);
                    taiKhoan.setMatKhauDaMaHoa(p[1]);
                    taiKhoan.setVaiTro(p[2]);
                    taiKhoan.setMaNguoiDung(p[3]);
                    taiKhoan.setDangHoatDong(Boolean.parseBoolean(p[4]));
                    them(taiKhoan);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Khong doc duoc file tai khoan: " + path);
        }
    }

    @Override
    public void ghiFile(String path) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(path));
            for (int i = 0; i < ds.size(); i++) {
                TaiKhoan t = ds.get(i);
                pw.println(t.getTenDangNhap() + "|" + t.getMatKhau() + "|"
                        + t.getVaiTro() + "|" + t.getMaNguoiDung() + "|"
                        + t.isDangHoatDong());
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Khong ghi duoc file tai khoan: " + path);
        }
    }
}
